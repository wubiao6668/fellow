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
import com.fellow.service.LostPostDetailService;
import com.fellow.service.base.PostServiceAbstract;
import org.springframework.stereotype.Service;

@Service
public class LostPostDetailServiceImpl extends PostServiceAbstract<LostPostDetailMapper,LostPostMapper,LostPostDetailMapper,LostPostReplyMapper,LostImgMapper> implements LostPostDetailService {


}
