package com.fellow.domain.vo;

import com.fellow.domain.model.FavoritePost;
import com.fellow.domain.model.User;
import com.fellow.domain.model.post.PostDomain;

import java.io.Serializable;

/**
 * Created by wubiao on 27/9/2017.
 */
public class FavoritePostVo extends FavoritePost implements Serializable {
    private static final long serialVersionUID = 2393330329638534636L;

    private User accountUser = new User();

    private PostDomain postDomain;

    public User getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(User accountUser) {
        this.accountUser = accountUser;
    }

    public PostDomain getPostDomain() {
        return postDomain;
    }

    public void setPostDomain(PostDomain postDomain) {
        this.postDomain = postDomain;
    }
}
