package com.fellow.dao;

import com.fellow.common.db.able.*;
import com.fellow.domain.model.FavoritePost;
import com.fellow.domain.query.FavoritePostQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritePostMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

    List<FavoritePost> selectByAccount(FavoritePostQuery favoritePostQuery);

    FavoritePost selectAccountPostIdAndType(FavoritePostQuery favoritePostQuery);

    int updateStatusById(FavoritePost favoritePost);

    int updateStatusByIdsAndAccount(FavoritePostQuery favoritePostQuery);
}