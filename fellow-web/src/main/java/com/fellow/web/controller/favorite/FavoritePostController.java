package com.fellow.web.controller.favorite;

import com.fellow.domain.model.FavoritePost;
import com.fellow.domain.query.FavoritePostQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.FavoritePostService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by wubiao on 28/9/2017.
 */
@RequestMapping(FavoritePostController.VIEW_PATH)
public class FavoritePostController extends WebAbstract<FavoritePostService> {
    public static final String VIEW_PATH = "/favorite/post";


    @RequestMapping("/doFavorite")
    @ResponseBody
    public Response doFavorite(FavoritePost favoritePost) {
        Response response = Response.newResponse();
        int row = service.doFavorite(favoritePost);
        response.setBody(row);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/bathCancel")
    @ResponseBody
    public Response bathCancel(Long[] ids) {
        Response response = Response.newResponse();
        if (ArrayUtils.isNotEmpty(ids)) {
            FavoritePostQuery favoritePostQuery = new FavoritePostQuery();
            favoritePostQuery.setAccount(super.getAccount());
            favoritePostQuery.setIdSet(new HashSet<Long>(Arrays.asList(ids)));
            int row = service.updateStatusByIdsAndAccount(favoritePostQuery);
            response.setBody(row);
            response.setSuccess(true);
        }
        return response;
    }


}
