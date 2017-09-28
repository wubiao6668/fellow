package com.fellow.service;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.FavoritePost;
import com.fellow.domain.query.FavoritePostQuery;
import com.fellow.domain.vo.FavoritePostVo;

import java.util.List;

public interface FavoritePostService extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {


    List<FavoritePostVo> selectMyFavorite(FavoritePostQuery favoritePostQuery);

    int doFavorite(FavoritePost favoritePost);

    int updateStatusByIdsAndAccount(FavoritePostQuery favoritePostQuery);

}
