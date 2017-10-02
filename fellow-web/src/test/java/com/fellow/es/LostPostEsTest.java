package com.fellow.es;

import com.fellow.domain.constant.EsConstant;
import com.fellow.domain.es.LostPostEsDomain;
import org.apache.commons.lang.RandomStringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by wubiao on 30/9/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:elsaticsearch.xml"})
public class LostPostEsTest {
    @Resource
    private LostPostEsRepository lostPostEsRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource
    private LostPostRepository lostPostRepository;

    @Test
    public void createIndexTest() {
        elasticsearchTemplate.deleteIndex(LostPostEsDomain.class);
        elasticsearchTemplate.createIndex(LostPostEsDomain.class);
        elasticsearchTemplate.putMapping(LostPostEsDomain.class);
        beforeTest();
    }

    @Test
    public void testRepository() {
        LostPostEsDomain lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "寒防霾口罩可以重复使用吗？");
        lostPostEsDomain.setTitleSuggest( "寒防霾口罩可以重复使用吗？");
        lostPostEsDomain.setPostText("我们的建议是：一次使用后就丢弃。原因：为了达到有效过滤效果，防霾口罩的密闭性都相对高，我们佩戴的时候会呼出水汽，夹带着口腔里的微生物，温暖湿润又封闭的口罩内侧，这时候就变成了一个优秀的细菌培养皿。细菌的繁殖生长速度普遍惊人，所以口罩用过一次后，再戴多久，细菌就陪你多久，而且会越来越多。2.可更换式半面罩，按说明书使用即可。可更换滤芯的防霾口罩在包装上一般会有如下说明\uD83D\uDC47");
        lostPostRepository.save(lostPostEsDomain);
    }


    @Test
    public void testRepPage() {
        QueryBuilder queryBuilder = multiMatchQuery("血压下降", "title", "postText").analyzer(EsConstant.IK_PINYIN_ANALYZER_NAME);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder).withHighlightFields()
                .withHighlightFields(new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>").requireFieldMatch(false))
                .withHighlightFields(new HighlightBuilder.Field("postText").preTags("<em>").postTags("</em>").requireFieldMatch(true))
//                .withPageable(new PageRequest(0, 20))
                .build();
//        Page<LostPostEsDomain> postEsDomainPage = lostPostRepository.search(queryBuilder, new PageRequest(0, 20));
        Iterable<LostPostEsDomain> postEsDomainPage = lostPostRepository.search(queryBuilder);
        System.out.println(postEsDomainPage);
    }


    @Test
    public void deleteIndexTest() {
        elasticsearchTemplate.deleteIndex(LostPostEsDomain.class);
    }

    @Test
    public void beforeTest() {
        LostPostEsDomain lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "寒潮预警！21款儿童秋衣秋裤评测，宝妈速来~");
        lostPostEsDomain.setTitleSuggest( "寒潮预警！21款儿童秋衣秋裤评测，宝妈速来~");
        lostPostEsDomain.setPostText("万水千山总是情，没穿秋裤行不行？不行！秋风瑟瑟，又到了你妈觉得你冷的季节。响应宝爸宝妈的号召，宝宝干妈组又一力作——儿童秋衣秋裤评测，正式出炉！");
        IndexQuery iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        ArrayList<IndexQuery> inserts = new ArrayList<IndexQuery>();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "辟谣｜超薄纸尿裤透气性一定好吗？");
        lostPostEsDomain.setTitleSuggest( "辟谣｜超薄纸尿裤透气性一定好吗？");
        lostPostEsDomain.setPostText("宝宝的“屁事”，妈妈的心事，如果纸尿裤透气不好，简直其罪当诛。于是，全天下的纸尿裤一夜间都有了共同的名字——薄。似乎只有够薄，才能戳中妈妈的心，赶走屁屁的闷");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "测了38款常温牛奶，这三款一生推！");
        lostPostEsDomain.setTitleSuggest( "测了38款常温牛奶，这三款一生推！");
        lostPostEsDomain.setPostText("小朋友长个要喝牛奶；睡眠不好要喝牛奶；老人补钙也要喝牛奶~这么一看，牛奶确实跟我们的生活息息相关。那么，面对超市琳琅满目的牛奶，我们到底该如何选择呢？不同品牌，不同标签的牛奶之间到底有神马区别啊？这实在是一个令人抓狂的问题。在你们嗷嗷的呐喊声中，真·有钱的评测君决定帮你们测一测。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "九成家长都不知道，安全座椅应该这样选");
        lostPostEsDomain.setTitleSuggest( "九成家长都不知道，安全座椅应该这样选");
        lostPostEsDomain.setPostText("宝妈@梧桐显然明白，儿童安全座椅对孩子有多重要。虽然它在中国大陆的普及率还不及3%，但无数的汽车碰撞测试、汽车事故调查数据都在证明，它能够在汽车事故中保护儿童乘客的生命安全。国内儿童安全座椅市场水深坑多，商场专柜陈列的旗舰款价格多在2000元以上，可与此同时，淘宝上不足百元的安全座椅也销量可观，到底什么样的安全座椅才能保护孩子？\n");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "12款无绳吸尘器良心评测，但结果很扎心");
        lostPostEsDomain.setTitleSuggest( "12款无绳吸尘器良心评测，但结果很扎心");
        lostPostEsDomain.setPostText("\n" +
                "作为一个不专一的数码控，我最近又开始涉足了新的领域——无绳吸尘器。为了满足我不断探索的好奇心，前段时间我借人借物借场地验证了一下戴森的吸力，写了《大骗纸，戴森吸尘器能力与广告不符，谁知其他牌子更差！》。相信大家一定意犹未尽，之后这段时间我便联合品质电器小组，对市面上热销的12款无绳吸尘器进行了体验评测。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "问天问大地，为什么荧光增白剂致癌这个谣言怎么辟都不倒？？");
        lostPostEsDomain.setTitleSuggest( "问天问大地，为什么荧光增白剂致癌这个谣言怎么辟都不倒？？");
        lostPostEsDomain.setPostText("荧光是一种冷发光的物理现象，是吸收一定波长的入射光（通常是紫外线或X射线）后发出更长波长的出射光（通常为可见光），比如，吸收蓝光发出绿光，吸收绿光发出红光……最常见的是吸收紫外光发出蓝光。如果停止入射光，发光现象也随之消失。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "用卫生棉条会破处吗？会疼吗？塞不进去怎么办？一篇终结所有疑惑！");
        lostPostEsDomain.setTitleSuggest( "用卫生棉条会破处吗？会疼吗？塞不进去怎么办？一篇终结所有疑惑！");
        lostPostEsDomain.setPostText("这里我们就不讨论处女情结是否是落后糟粕了，因为涉及太多传统与现代观念的碰撞，撞不好就会引发一场撕×，只想说：我的身体我做主，大清虽然亡了，但是否在乎这层膜完全是自己的事。坚决反对处女价值论，不代表必须接受处女膜无用论。对于经期女性而言，由于使用了高吸附力的棉条且长时间不更换，阴道壁因为卫生棉条的吸收而过分干燥，增加了活动时撕裂阴道软组织的可能性，给金黄色葡萄球菌进入人体血液循环开了方便之门。患者会产生喉咙痛、发烧、关节及肌肉酸痛、血压下降的症状，严重者甚至休克。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "14款儿童酱油评测，卖点全部打脸！");
        lostPostEsDomain.setTitleSuggest( "14款儿童酱油评测，卖点全部打脸！");
        lostPostEsDomain.setPostText("这几年儿童酱油突然流行起来，虽然价格不怎么亲民，但销量傲人且坚挺。多数宝爸宝妈的心理是：只要低盐低钠、安全营养，适合宝宝发育和成长，贵，也要买！（悲壮脸~）为了增加宝贝的食欲，很多妈妈会考虑给娃的饭里加点“鲜”，儿童酱油就成了蒸蛋羹、拌米饭的首选，因为低盐低钠啊。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "11款扫地机器人竞赛，小米活好、美的“智障”、还发现几个戏精……");
        lostPostEsDomain.setTitleSuggest( "11款扫地机器人竞赛，小米活好、美的“智障”、还发现几个戏精……");
        lostPostEsDomain.setPostText("据说这款“石头扫地机器人”具备了扫拖一体、划区清扫、超强性能、传感器升级四大优势，而且还实现了划区清扫和指哪去哪清扫的功能。至于到底好不好用评测君就不知道了，石头扫地机器人虽然还没上市，但是小米1代扫地机器人在我们的评测名单之中~为了挑选出最好的扫地机器人，评测君选购了11款千元价位的入门级机器，是骡子是马，拉出来溜溜见分晓！");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "面膜敷的好，年轻不显老，可你真的敷对了么？");
        lostPostEsDomain.setTitleSuggest( "面膜敷的好，年轻不显老，可你真的敷对了么？");
        lostPostEsDomain.setPostText("面膜是现在许多女性必备的美丽工具，有补水保湿、促进肌肤活力的功效。只要几分钟，肌肤立刻改头换面、光滑紧致，看起来更明亮。当肌肤缺水疲惫时，只要用面膜敷上一敷，立即就可以感受容光焕发的效果。涂抹式面膜里我们还可以分为水洗和免洗两种。水洗面膜的清洁和保湿功能都比较好，但是将干燥后的膏状产品洗去较麻烦，因此这类产品使用时不是很方便。而免洗睡眠面膜在使用上就会方便很多，在睡觉的时候就可以帮助肌肤更好的锁水保湿。但记得只要均匀地涂一层就可以啦，太多了会导致肌肤不堪重负，适得其反。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "牙齿美白小窍门到底靠不靠谱？");
        lostPostEsDomain.setTitleSuggest( "牙齿美白小窍门到底靠不靠谱？");
        lostPostEsDomain.setPostText("这显然是有代价的：翻翻美白牙贴的买家评论区，会发现牙贴使用过程中经常会伴随牙齿酸软的感觉，这是轻微的牙本质过敏症状，主要来自于美白牙贴最核心的有效成分——低浓度的过氧化物（一般是过氧化氢or过氧化脲），它们在通过氧化还原作用漂白牙齿的同时，也会对牙釉质和更加脆弱的牙龈产生刺激。");
        iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        inserts.add(iq);
        elasticsearchTemplate.bulkIndex(inserts);
    }

    @Test
    public void saveTest() {
//        for (int i = 0; i < 100; i++) {
        LostPostEsDomain lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.nanoTime());
        lostPostEsDomain.setTitle( "牙齿美白小窍门到底靠不靠谱？");
        lostPostEsDomain.setPostText("这显然是有代价的：翻翻美白牙贴的买家评论区，会发现牙贴使用过程中经常会伴随牙齿酸软的感觉，这是轻微的牙本质过敏症状，主要来自于美白牙贴最核心的有效成分——低浓度的过氧化物（一般是过氧化氢or过氧化脲），它们在通过氧化还原作用漂白牙齿的同时，也会对牙釉质和更加脆弱的牙龈产生刺激。");
        lostPostEsRepository.save(lostPostEsDomain);
//        }
    }

    @Test
    public void testTerm() {
        QueryBuilder termQueryBuilder = termQuery("id", "60670868678971");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQueryBuilder).build();
        List<LostPostEsDomain> lostPostDomain = elasticsearchTemplate.queryForList(searchQuery, LostPostEsDomain.class);
        System.out.print(lostPostDomain);
    }

    @Test
    public void matchPhraseQueryTest() {
        QueryBuilder termQueryBuilder = matchQuery("title", "透气 年轻 处女");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQueryBuilder).build();
        List<LostPostEsDomain> lostPostDomain = elasticsearchTemplate.queryForList(searchQuery, LostPostEsDomain.class);
        System.out.print(lostPostDomain);
    }


    @Test
    public void testQuery() {
//        QueryBuilder queryBuilder = queryStringQuery("超薄纸尿裤透气性一定好吗").analyzer(EsConstant.IK_PINYIN_ANALYZER_NAME);
        QueryBuilder queryBuilder = multiMatchQuery("进去", "title", "postText").analyzer(EsConstant.IK_PINYIN_ANALYZER_NAME);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder).withHighlightFields()
                .withHighlightFields(new HighlightBuilder.Field("*").preTags("<em>").postTags("</em>").requireFieldMatch(false))
//                .withHighlightFields(new HighlightBuilder.Field("postText").preTags("<em>").postTags("</em>").requireFieldMatch(true))
                .withPageable(new PageRequest(0, 20))
                .build();
        Page<LostPostEsDomain> lostPostEsDomainPage = elasticsearchTemplate.queryForPage(searchQuery, LostPostEsDomain.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                System.out.println("本次查询共耗时:" + response.getTookInMillis() + "毫秒.");
                List<LostPostEsDomain> books = new ArrayList<LostPostEsDomain>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    LostPostEsDomain book = new LostPostEsDomain();
                    Map<String, Object> source = searchHit.getSource();
                    book.setId((Long) source.get("id"));
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                    HighlightField highlightNameField = highlightFields.get("postText");
                    if (highlightNameField != null && highlightNameField.fragments() != null) {
                        book.setPostText(highlightNameField.fragments()[0].string());
                    } else {
                        book.setPostText((String) source.get("postText"));
                    }
                    HighlightField highlightDescField = highlightFields.get("title");
                    if (highlightDescField != null && highlightDescField.fragments() != null) {
                        book.setTitle(highlightDescField.fragments()[0].string());
                    } else {
                        book.setTitle((String) source.get("title"));
                    }
                    books.add(book);
                }
                if (books.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) books);
                }
                return null;
            }
        });
        System.out.print(lostPostEsDomainPage);
    }

    @Test
    public void testSuggest() {
        SuggestBuilder.SuggestionBuilder<CompletionSuggestionBuilder>  suggestBuilder = new CompletionSuggestionBuilder("sdfsd");
        suggestBuilder.text("重复");
        suggestBuilder.field("titleSuggest");
        suggestBuilder.analyzer(EsConstant.IK_PINYIN_ANALYZER_NAME);
        suggestBuilder.size(10);
//        suggestBuilder.toXContent();
        SuggestResponse suggestResponse = elasticsearchTemplate.suggest(suggestBuilder,LostPostEsDomain.class);
        System.out.println(suggestResponse);
    }


}
