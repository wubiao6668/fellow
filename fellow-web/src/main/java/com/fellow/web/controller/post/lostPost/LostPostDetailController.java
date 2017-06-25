package com.fellow.web.controller.post.lostPost;

import com.fellow.common.annotation.NoNeedLogin;
import com.fellow.domain.enums.LostPosGoodTypeEnum;
import com.fellow.domain.enums.LostPostTypeEnum;
import com.fellow.domain.enums.PostPlaceTypeEnum;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.model.post.PostDomain;
import com.fellow.service.LostImgService;
import com.fellow.service.LostPostDetailService;
import com.fellow.service.LostPostService;
import com.fellow.web.base.PostDetailAbstractWeb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 2016/9/17.
 */
@RequestMapping(LostPostDetailController.VIEW_PATH)
@Controller
public class LostPostDetailController extends PostDetailAbstractWeb< LostPostDetailService, LostPostService, LostImgService> {
    public static final String VIEW_PATH = "/post/lost/postDetail";


    @RequestMapping("/index")
    @NoNeedLogin
    @Override
    public String index(Long postId, Model model) {
        return super.index(postId, model);
    }

    @Override
    protected void wrapPost(PostDomain post) {
        LostPost lostPost = ((LostPost) post);
        lostPost.setPostTypeText(LostPostTypeEnum.getValueByKey(lostPost.getPostType()));
        lostPost.setGoodTypeText(LostPosGoodTypeEnum.getValueByKey(lostPost.getGoodType()));
        lostPost.setPlaceTypeText(PostPlaceTypeEnum.getValueByKey(lostPost.getPlaceType()));
        super.wrapPost(lostPost);
    }
}
