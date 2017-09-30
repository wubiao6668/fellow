package com.fellow.es;

import com.fellow.domain.es.LostPostEsDomain;

/**
 * Created by wubiao on 30/9/2017.
 */
public interface LostPostEsRepository {

    boolean save(LostPostEsDomain lostPostEsDomain);
}
