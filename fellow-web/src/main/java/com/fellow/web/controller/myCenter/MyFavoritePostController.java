package com.fellow.web.controller.myCenter;

import com.fellow.domain.query.FavoritePostQuery;
import com.fellow.domain.vo.FavoritePostVo;
import com.fellow.service.FavoritePostService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by wubiao on 28/9/2017.
 */
@Controller
@RequestMapping(MyFavoritePostController.VIEW_PATH)
public class MyFavoritePostController extends WebAbstract<FavoritePostService> {
    public static final String VIEW_PATH = "/myCenter/myFavoritePost";

    @RequestMapping("/index")
    public String index(Model model, FavoritePostQuery favoritePostQuery) {
        condition(model, favoritePostQuery);
        return "/myCenter/myFavoritePost/index";
    }

    @RequestMapping("/more")
    public String more(Model model, FavoritePostQuery favoritePostQuery) {
        condition(model, favoritePostQuery);
        return "/myCenter/myFavoritePost/more";
    }

    private void condition(Model model, FavoritePostQuery favoritePostQuery) {
        favoritePostQuery.setPageSize(1);
        favoritePostQuery.initMysqlLimit();
        favoritePostQuery.setAccount(super.getAccount());
        favoritePostQuery.setSortColumns(" favorite_time desc");
        if (0 >= favoritePostQuery.getQueryTime()) {
            favoritePostQuery.setFavoriteTimeEnd(new Date());
        } else {
            favoritePostQuery.setFavoriteTimeEnd(new Date(favoritePostQuery.getQueryTime()));
        }
        List<FavoritePostVo> favoritePostVoList = service.selectMyFavorite(favoritePostQuery);

        long favoriteTimeEnd = 0;
        boolean hasNextPage = false;
        if (CollectionUtils.isNotEmpty(favoritePostVoList)) {
            favoriteTimeEnd = favoritePostVoList.get(favoritePostVoList.size() - 1).getFavoriteTime().getTime();
            hasNextPage = favoritePostQuery.getPageSize() == favoritePostVoList.size();
        }
        model.addAttribute("favoritePostVoList", favoritePostVoList);
        model.addAttribute("favoriteTimeEnd", favoriteTimeEnd);
        model.addAttribute("hasNextPage", hasNextPage);
    }

}
