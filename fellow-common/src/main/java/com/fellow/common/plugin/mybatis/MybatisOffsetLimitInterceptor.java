/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.common.plugin.mybatis;

import com.fellow.domain.mybatis.PageListImpl;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.github.miemiedev.mybatis.paginator.dialect.Dialect;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.support.PropertiesHelper;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;


/**
 * 为MyBatis提供基于方言(Dialect)的分页查询的插件
 * <p/>
 * 将拦截Executor.query()方法实现分页方言的插入.
 *
 * @author badqiu
 * @author miemiedev
 */

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisOffsetLimitInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(MybatisOffsetLimitInterceptor.class);
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;

    static ExecutorService Pool;
    String dialectClass;
    boolean asyncTotalCount = false;

    public Object intercept(final Invocation invocation) throws Throwable {
        final Executor executor = (Executor) invocation.getTarget();
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
        final PageBounds pageBounds = new PageBounds(rowBounds);

        if (pageBounds.getOffset() == RowBounds.NO_ROW_OFFSET
                && pageBounds.getLimit() == RowBounds.NO_ROW_LIMIT
                && pageBounds.getOrders().isEmpty()) {
            return invocation.proceed();
        }

        final Dialect dialect;
        try {
            Class clazz = Class.forName(dialectClass);
            Constructor constructor = clazz.getConstructor(MappedStatement.class, Object.class, PageBounds.class);
            dialect = (Dialect) constructor.newInstance(new Object[]{ms, parameter, pageBounds});
        } catch (Exception e) {
            throw new ClassNotFoundException("Cannot create dialect instance: " + dialectClass, e);
        }

        final BoundSql boundSql = ms.getBoundSql(parameter);

        queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, dialect.getPageSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
        queryArgs[PARAMETER_INDEX] = dialect.getParameterObject();
        queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

        Boolean async = pageBounds.getAsyncTotalCount() == null ? asyncTotalCount : pageBounds.getAsyncTotalCount();
        Future<List> listFuture = call(new Callable<List>() {
            public List call() throws Exception {
                return (List) invocation.proceed();
            }
        }, async);


        if (pageBounds.isContainsTotalCount()) {
            Callable<PaginatorImpl> countTask = new Callable() {
                public Object call() throws Exception {
                    Integer count;
                    Cache cache = ms.getCache();
                    if (cache != null && ms.isUseCache() && ms.getConfiguration().isCacheEnabled()) {
                        CacheKey cacheKey = executor.createCacheKey(ms, parameter, new PageBounds(), copyFromBoundSql(ms, boundSql, dialect.getCountSQL(), boundSql.getParameterMappings(), boundSql.getParameterObject()));
                        count = (Integer) cache.getObject(cacheKey);
                        if (count == null) {
//                            count = SQLHelp.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
                            count = findCount(ms,parameter);
                            cache.putObject(cacheKey, count);
                        }
                    } else {
//                        count = SQLHelp.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
                        count = findCount(ms,parameter);
                    }
                    return new PaginatorImpl(pageBounds.getPage(), pageBounds.getLimit(), count);
                }
            };
            Future<PaginatorImpl> countFutrue = call(countTask, async);
            return new PageListImpl(listFuture.get(), countFutrue.get());
        }

        return listFuture.get();
    }

    private <T> Future<T> call(Callable callable, boolean async) {
        if (async) {
            return Pool.submit(callable);
        } else {
            FutureTask<T> future = new FutureTask(callable);
            future.run();
            return future;
        }
    }

    private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql,
                                           String sql, List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, parameterMappings, parameter);
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
                                      String sql, List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, parameterMappings, parameter);
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    //see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
        String dialectClass = propertiesHelper.getRequiredString("dialectClass");
        setDialectClass(dialectClass);

        setAsyncTotalCount(propertiesHelper.getBoolean("asyncTotalCount", false));

        setPoolMaxSize(propertiesHelper.getInt("poolMaxSize", 0));

    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    public void setDialectClass(String dialectClass) {
        logger.debug("dialectClass: {} ", dialectClass);
        this.dialectClass = dialectClass;
    }

    public void setAsyncTotalCount(boolean asyncTotalCount) {
        logger.debug("asyncTotalCount: {} ", asyncTotalCount);
        this.asyncTotalCount = asyncTotalCount;
    }

    public void setPoolMaxSize(int poolMaxSize) {

        if (poolMaxSize > 0) {
            logger.debug("poolMaxSize: {} ", poolMaxSize);
            Pool = Executors.newFixedThreadPool(poolMaxSize);
        } else {
            Pool = Executors.newCachedThreadPool();
        }
    }
    private Integer findCount(MappedStatement ms, Object queryParam) {
        final Configuration configuration = ms.getConfiguration();

        SqlSession sqlSession = openSessionFromDataSource(configuration, null, false);

        Integer totalCount = sqlSession.selectOne(ms.getId() + "Count", queryParam);

        return totalCount;
    }
    private SqlSession openSessionFromDataSource(Configuration configuration, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            final Executor executor = configuration.newExecutor(tx, configuration.getDefaultExecutorType());
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            closeTransaction(tx); // may have fetched a connection so lets call close()
            throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
        } finally {
            ErrorContext.instance().reset();
        }
    }

    private TransactionFactory getTransactionFactoryFromEnvironment(Environment environment) {
        if (environment == null || environment.getTransactionFactory() == null) {
            return new ManagedTransactionFactory();
        }
        return environment.getTransactionFactory();
    }

    private void closeTransaction(Transaction tx) {
        if (tx != null) {
            try {
                tx.close();
            } catch (SQLException ignore) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }
}
