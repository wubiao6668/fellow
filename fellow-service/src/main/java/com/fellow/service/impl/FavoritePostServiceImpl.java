package com.fellow.service.impl;

import com.fellow.dao.FavoritePostMapper;
import com.fellow.domain.model.FavoritePost;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.query.FavoritePostQuery;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.domain.vo.FavoritePostVo;
import com.fellow.service.FavoritePostService;
import com.fellow.service.LostPostService;
import com.fellow.service.base.ServiceAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FavoritePostServiceImpl extends ServiceAbstract<FavoritePostMapper> implements FavoritePostService {

    @Resource
    private LostPostService lostPostService;

    @Override
    public List<FavoritePostVo> selectMyFavorite(FavoritePostQuery favoritePostQuery) {
        List<FavoritePost> favoritePostList = repository.selectByAccount(favoritePostQuery);
        List<FavoritePostVo> favoritePostVoList = new ArrayList<FavoritePostVo>();
        if (CollectionUtils.isNotEmpty(favoritePostList)) {
            Set<Long> idSet = new HashSet<Long>();
            FavoritePostVo favoritePostVoTemp = null;
            for (FavoritePost favoritePost : favoritePostList) {
                idSet.add(favoritePost.getPostId());
                favoritePostVoTemp = new FavoritePostVo();
                BeanUtils.copyProperties(favoritePost, favoritePostVoTemp);
                favoritePostVoList.add(favoritePostVoTemp);
            }
            LostPostQuery lostPostQuery = new LostPostQuery();
            lostPostQuery.setIdSet(idSet);
            Map<Long, LostPost> lostPostMap = lostPostService.selectByIds(lostPostQuery);
            if (MapUtils.isNotEmpty(lostPostMap)) {
                for (FavoritePostVo favoritePostVo : favoritePostVoList) {
                    favoritePostVo.setPostDomain(lostPostMap.get(favoritePostVo.getPostId()));
                }
            }
        }
        return favoritePostVoList;
    }


    @Override
    public int doFavorite(FavoritePost favoritePost) {
        FavoritePostQuery favoritePostQuery = new FavoritePostQuery();
        favoritePostQuery.setAccount(favoritePost.getAccount());
        favoritePostQuery.setPostId(favoritePost.getPostId());
        favoritePostQuery.setType(favoritePost.getType());
        FavoritePost dbFavoritePost = repository.selectAccountPostIdAndType(favoritePostQuery);
        int row = 0;
        if (null != dbFavoritePost) {
            favoritePost.setId(dbFavoritePost.getId());
            row = repository.updateStatusById(favoritePost);
        } else {
            row = repository.insertSelective(favoritePost);
        }
        return row;
    }

    @Override
    public int updateStatusByIdsAndAccount(FavoritePostQuery favoritePostQuery) {
        return repository.updateStatusByIdsAndAccount(favoritePostQuery);
    }
}




















