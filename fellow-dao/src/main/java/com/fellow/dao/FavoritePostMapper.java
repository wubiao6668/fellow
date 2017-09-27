package com.fellow.dao;

import com.fellow.common.db.able.*;
import org.springframework.stereotype.Repository;

@Repository
public interface  FavoritePostMapper extends DeleteAble, DeletePhysicalAble, FindListAble,
        GetByKeyAble, InsertAble, InsertSelectiveAble, UpdateAble, UpdateByOptimisticLockAble {

}