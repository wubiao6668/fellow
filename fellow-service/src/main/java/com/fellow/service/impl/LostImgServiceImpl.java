/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.dao.LostImgMapper;
import com.fellow.dao.LostPostDetailMapper;
import com.fellow.dao.LostPostMapper;
import com.fellow.dao.LostPostReplyMapper;
import com.fellow.service.LostImgService;
import com.fellow.service.base.PostServiceAbstract;
import org.springframework.stereotype.Service;

@Service
public class LostImgServiceImpl extends PostServiceAbstract<LostImgMapper,LostPostMapper, LostPostDetailMapper, LostPostReplyMapper, LostImgMapper> implements LostImgService{

//    @Override
//    public int delete(BaseDomain entity) {
//        return 0;
//    }

//    @Override
//    public int delete(LostImg entity) {
//        return 0;
//    }


}
