package com.fellow.web.base;

import com.fellow.common.db.able.GetByKeyAble;
import com.fellow.common.db.able.IncrementAble;
import com.fellow.common.db.able.post.PostDetailAble;
import com.fellow.common.db.able.post.PostImgAble;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.model.post.PostDetailDomain;
import com.fellow.domain.model.post.PostDomain;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by wubiao on 2017/1/12.
 */
public abstract class PostDetailAbstractWeb<S, PostS, ImgS> extends WebAbstract<S> {

    @Autowired
    private PostS postService;
    @Autowired
    private ImgS imgService;

    public String index(Long postId, Model model) {
        log.info("index--postId=" + postId);
        //浏览加1
        ((IncrementAble) postService).viewIncrement(postId);
        //1、post id 查帖子
        PostDomain post = (PostDomain)((GetByKeyAble) postService).getByKey(postId);
        post.setPostText(PostEnum.LOST_POST.value);
        //制定帖子
        wrapPost(post);
        //查询帖子详情
        PostDetailDomain postDetail = ((PostDetailAble) service).selectDetailByPostId(postId);
        //帖子查找图片
        List<ImgDomain> imgList = ((PostImgAble) imgService).selectImgByPostId(postId, PostImgTypeEnum.POST.getKey());
        if (CollectionUtils.isNotEmpty(imgList)) {
            //设置图片上下文 用户替换帖子中的图片
            Context context = new VelocityContext();
            for (ImgDomain imgTemp : imgList) {
                context.put(imgTemp.getImgName(), imgTemp.getImgRootPath() + "/" + imgTemp.getImgRelativePath());
            }
            try {
                postDetail.setPostText(VelocityUtil.mergeTextTemplate(context, velocityConfig.getVelocityEngine(), postDetail.getPostText()));
            } catch (Exception e) {
                log.error("index【" + getModule() + "】--替换帖子详情图片异常--e", e);
            }
        }
        model.addAttribute("post", post);
        model.addAttribute("postDetail", postDetail);
        return getModule() + "/index";
    }

    protected void wrapPost(PostDomain post) {}

}
