/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.service.impl;

import com.fellow.common.util.VelocityUtil;
import com.fellow.dao.LostImgMapper;
import com.fellow.dao.LostPostDetailMapper;
import com.fellow.dao.LostPostMapper;
import com.fellow.dao.LostPostReplyMapper;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.query.LostPostReplyQuery;
import com.fellow.domain.vo.LostPostReplyVo;
import com.fellow.service.LostPostReplyService;
import com.fellow.service.base.PostServiceAbstract;
import javafx.print.PageOrientation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LostPostReplyServiceImpl extends PostServiceAbstract<LostPostReplyMapper, LostPostMapper, LostPostDetailMapper, LostPostReplyMapper, LostImgMapper> implements LostPostReplyService {

    @Resource
    private LostPostMapper lostPostMapper;

    @Transactional
    @Override
    public List<LostPostReplyVo> selectReplyAccount(LostPostReplyQuery replyQuery) {
        //查询我的评论
        List<LostPostReplyVo> replyList = repository.selectReplyAccount(replyQuery);
        Set<Long> imgReplyIdSet = new HashSet<Long>();
        if (CollectionUtils.isNotEmpty(replyList)) {
            Set<Long> postIdSet = new HashSet<Long>();
            for (LostPostReplyVo replyTemp : replyList) {
                postIdSet.add(replyTemp.getPostId());
                if (null != replyTemp.getImgNum() && replyTemp.getImgNum() > 0) {
                    imgReplyIdSet.add(replyTemp.getId());
                }
                try {
                    replyTemp.setContent(VelocityUtil.mergeEmoticon(replyTemp.getContent()));
                } catch (Exception e) {
                    log.error("selectReplyAccount--设置表情失败--e=", e);
                }
            }
            //查询帖子标题
            List<LostPost> lostPostTitleList = lostPostMapper.selectPostTitleByIds(postIdSet);
            if (CollectionUtils.isNotEmpty(lostPostTitleList)) {
                for (LostPostReplyVo replyTemp : replyList) {
                    for (LostPost postTileTemp : lostPostTitleList) {
                        if (postTileTemp.getId().longValue() == replyTemp.getPostId().longValue()) {
                            replyTemp.setTitle(postTileTemp.getTitle());
                            continue;
                        }
                    }
                }
            }

            //查找图片
            if (CollectionUtils.isNotEmpty(imgReplyIdSet)) {
                List<ImgDomain> imgDomainList = imgRepository.selectImgByPostIdAndReplyIds(null, imgReplyIdSet, null);
                if (CollectionUtils.isNotEmpty(imgDomainList)) {
                    for (LostPostReplyVo replyTemp : replyList) {
                        replyTemp.setImgList(new ArrayList<ImgDomain>());
                        for (ImgDomain imgDomainTemp : imgDomainList) {
                            if (replyTemp.getId().longValue() == imgDomainTemp.getReplyId().longValue()) {
                                replyTemp.getImgList().add(imgDomainTemp);
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return replyList;
    }

    @Transactional(readOnly = true)
    @Override
    public long selectReplyAccountCount(LostPostReplyQuery replyQuery) {
        return repository.selectReplyAccountCount(replyQuery);
    }


    @Override
    public Map<Long, LostPostReply> selectByIds(LostPostReplyQuery replyQuery) {
        Map<Long,LostPostReply>  postReplyMap =  repository.selectByIds(replyQuery);
        if (MapUtils.isNotEmpty(postReplyMap)){
            for (LostPostReply lostPostReplyTemp :  postReplyMap.values()){
                if (lostPostReplyTemp.getImgNum() > 0) {
                    List<ImgDomain> imgDomainList = imgRepository.selectImgByPostId(lostPostReplyTemp.getId(), PostImgTypeEnum.POST.getKey());
                    lostPostReplyTemp.setImgList(imgDomainList);
                }
                lostPostReplyTemp.setContent(VelocityUtil.mergeEmoticon(lostPostReplyTemp.getContent()));
            }
        }
        return postReplyMap;
    }

    @Override
    public int updateAttitudeInfo(LostPostReply lostPostReply) {
        return repository.updateAttitudeInfo(lostPostReply);
    }
}



















