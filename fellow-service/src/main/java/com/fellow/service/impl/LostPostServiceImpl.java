/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.db.able.post.PostImgAble;
import com.fellow.common.util.VelocityUtil;
import com.fellow.dao.LostImgMapper;
import com.fellow.dao.LostPostDetailMapper;
import com.fellow.dao.LostPostMapper;
import com.fellow.dao.LostPostReplyMapper;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.service.LostPostService;
import com.fellow.service.base.PostServiceAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LostPostServiceImpl extends PostServiceAbstract<LostPostMapper, LostPostMapper, LostPostDetailMapper, LostPostReplyMapper, LostImgMapper> implements LostPostService {

    @Resource
    private LostPostDetailMapper lostPostDetailMapper;
    @Resource
    private LostImgMapper lostImgMapper;

    @Transactional(readOnly = true)
    @Override
    public long selectLostPostCount(LostPostQuery lostPostQuery) {
        return repository.selectLostPostCount(lostPostQuery);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LostPost> selectMyPost(LostPostQuery query) {
        return repository.selectMyPost(query);
    }

    @Override
    public Map<Long, LostPost> selectByIds(LostPostQuery query) {
        Map<Long, LostPost> lostPostMap = repository.selectByIds(query);
        if (MapUtils.isNotEmpty(lostPostMap)) {
            for (LostPost lostPost : lostPostMap.values()) {
                if (lostPost.getImgNum() > 0) {
                    List<ImgDomain> imgDomainList = imgRepository.selectImgByPostId(lostPost.getId(), PostImgTypeEnum.POST.getKey());
                    lostPost.setImgList(imgDomainList);
                }
                lostPost.setBrief(VelocityUtil.mergeEmoticon(lostPost.getBrief()));
            }
        }
        return lostPostMap;
    }

    //    @Transactional(readOnly = true)
//    @Override
//    public List<LostPost> findPubishList(LostPostQuery lostPostQuery) {
//        List<LostPost> postPageList = repository.selectLostPost(lostPostQuery);
//        if (CollectionUtils.isNotEmpty(postPageList)) {
//            List<Long> postIdList = new ArrayList<Long>();
//            for (LostPost lostPostTemp : postPageList) {
//                postIdList.add(lostPostTemp.getId());
//            }
//            List<LostImg> lostImgList = lostImgMapper.selectByPostIds(postIdList, PostImgTypeEnum.POST.getKey());
//            if (CollectionUtils.isNotEmpty(lostImgList)) {
//                for (LostPost lostPostTemp : postPageList) {
//                    lostPostTemp.setImgList(new ArrayList<ImgDomain>());
//                    for (LostImg lostImgTemp : lostImgList) {
//                        if (lostPostTemp.getId().longValue() == lostImgTemp.getPostId().longValue()) {
//                            lostPostTemp.getImgList().add(lostImgTemp);
//                        }
//                    }
//                }
//            }
//        }
//        return postPageList;
//    }


    @Override
    public int updateAttitudeInfo(LostPost lostPost) {
        return repository.updateAttitudeInfo(lostPost);
    }
}























