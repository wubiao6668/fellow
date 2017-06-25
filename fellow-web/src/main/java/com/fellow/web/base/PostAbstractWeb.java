package com.fellow.web.base;

import com.alibaba.fastjson.JSON;
import com.fellow.common.constant.SystemConstant;
import com.fellow.common.db.able.post.PostAble;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.base.QueryDomain;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.LostImg;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.model.post.PostDetailDomain;
import com.fellow.domain.model.post.PostDomain;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.fellow.domain.vo.PostMapVo;
import com.fellow.domain.web.Response;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by wubiao on 2017/1/13.
 */
public abstract class PostAbstractWeb<S> extends WebAbstract<S> {

    /**
     * 发帖
     *
     * @param postDomain
     * @return
     */
    public Response publish(PostDomain postDomain) {
        log.info("publish--发帖--postDomain=" + JSON.toJSONString(postDomain));
        long startTime = System.currentTimeMillis();
        Response response = Response.newResponse();
        PostDetailDomain lostPostDetail = postDomain.getPostDetailDomain();
//        List<ImgDomain> imgList = ;
        postDomain.setImgList(new ArrayList<ImgDomain>());
        //概论
        Document document = Jsoup.parse(lostPostDetail.getPostText());
        document.outputSettings().prettyPrint(false);
        //图片是否大于8张
        Elements uploadImageElements = document.body().select(SystemConstant.IMAGE_UPLOAD_USER_SELECTOR);
        if (null != uploadImageElements && uploadImageElements.size() > 0) {
            if (uploadImageElements.size() > SystemConstant.IMAGE_UPLOAD_MAX_NUM) {
                response.setMessage("图片不能大于" + SystemConstant.IMAGE_UPLOAD_MAX_NUM + "张");
                return response;
            }
            LostImg lostImgTemp;
            String imgRootPathTemp = "";
            String imgRelativePathTemp = "";
            for (int i = 0; i < uploadImageElements.size(); i++) {
                Element elementImgTemp = uploadImageElements.get(i);
                String srcTemp = elementImgTemp.attr("src");
                if (srcTemp.startsWith("/")) {
                    imgRootPathTemp = srcTemp.substring(0, 1);
                    imgRelativePathTemp = srcTemp.substring(1);
                } else if (srcTemp.startsWith("http")) {
                    int fromIndex = srcTemp.indexOf("//") + 2;
                    int beginIndex = srcTemp.indexOf("/", fromIndex);
                    imgRootPathTemp = srcTemp.substring(0, beginIndex);
                    imgRelativePathTemp = srcTemp.substring(beginIndex + 1);
                }
                String imgName = "name_img_" + i;
                elementImgTemp.attr("src", "$!{" + imgName + "}");
                lostImgTemp = new LostImg();
                lostImgTemp.setImgType(PostImgTypeEnum.POST.getKey());
                lostImgTemp.setAccount(super.getAccount());
                lostImgTemp.setImgRootPath(imgRootPathTemp);
                lostImgTemp.setImgRelativePath(imgRelativePathTemp);
                lostImgTemp.setImgName(imgName);
                lostImgTemp.setCreateAccount(super.getAccount());
                lostImgTemp.setCreateName(super.getUserName());
                postDomain.getImgList().add(lostImgTemp);
            }
        }
        postDomain.setImgNum(postDomain.getImgList().size());
        StringBuffer briefBf = new StringBuffer();
        for (Node nodeTemp : document.body().childNodes()) {
            if (nodeTemp instanceof TextNode) {
                briefBf.append(nodeTemp.toString());
            } else if (nodeTemp instanceof Element) {
                Element elementTemp = (Element) nodeTemp;
                if ("img".equals(elementTemp.tagName().toLowerCase()) && elementTemp.attr("src").startsWith(SystemConstant.EMOTICON_PREFIX)) {
                    briefBf.append(elementTemp.toString());
                }
            }
            if (briefBf.toString().length() > SystemConstant.POST_BRIEF_TEXT_LENGTH) {
                break;
            }
        }
        String postText = document.body().html().toString();
        postDomain.setBrief(briefBf.toString());
        postDomain.setPostAccount(super.getAccount());
        postDomain.setPostUsername(super.getUserName());
        postDomain.setPostTime(new Date());
        postDomain.setCreateAccount(super.getAccount());
        postDomain.setCreateName(super.getUserName());
        //
        lostPostDetail.setPostText(postText);
        lostPostDetail.setAccount(super.getAccount());
        lostPostDetail.setCreateAccount(super.getAccount());
        lostPostDetail.setCreateName(super.getUserName());
        ((PostAble)service).publish(postDomain);
        response.setSuccess(true);
        log.info("publish--执行时间--time=" + (System.currentTimeMillis() - startTime));
        return response;
    }


    /**
     * 帖子查找
     * @param q
     * @return
     */
    public Response queryPostMapList(QueryDomain q, String template) {
        log.info("queryPost--帖子查找--q=" + JSON.toJSONString(q));
        Response response = Response.newResponse();
        q.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        q.initMysqlLimit();
        List<PostDomain> postList = ((PostAble) service).selectPost(q);
        Map<String, PostMapVo> lostPostMap = new HashMap<String, PostMapVo>();
        Context context = new VelocityContext();
        if (CollectionUtils.isNotEmpty(postList)) {
            String latLngKey = "";
            int index = 1000;
            for (PostDomain mTemp : postList) {
                latLngKey = mTemp.getPostLat().toString() + "_" + mTemp.getPostLng().toString();
                PostMapVo postMapVoTemp = lostPostMap.get(latLngKey);
                if (null == postMapVoTemp) {
                    postMapVoTemp = new PostMapVo();
                    postMapVoTemp.setMapHtmlName("mapPost" + (index++) + RandomStringUtils.randomAlphabetic(4));
                    postMapVoTemp.setPostCount(1);
                    postMapVoTemp.setPostLng(mTemp.getPostLng());
                    postMapVoTemp.setPostLat(mTemp.getPostLat());
                    postMapVoTemp.setTitle(mTemp.getTitle());
                    postMapVoTemp.setPostAccount(mTemp.getPostAccount());
                    postMapVoTemp.setPostUsername(mTemp.getPostUser().getUsername());
                    postMapVoTemp.setPostTime(mTemp.getPostTime());
                    lostPostMap.put(latLngKey, postMapVoTemp);
                } else {
                    postMapVoTemp.setPostCount(postMapVoTemp.getPostCount() + 1);
                }
                mTemp.setMapHtmlName(postMapVoTemp.getMapHtmlName());
                //设置表情
                mTemp.setBrief(VelocityUtil.mergeEmoticon(mTemp.getBrief()));
            }
        }
        context.put("postList", postList);
        context.put("collectionUtils", new CollectionUtils());
        response.setBody(VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(),template));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("lostPostMap", lostPostMap.values());
        resultMap.put("isHadPre", q.getPage() > 1);
        resultMap.put("isHadNext", q.getPageSize() == postList.size());
        response.setObject(resultMap);
        response.setSuccess(true);
        return response;
    }

    /**
     * 帖子查找
     * @param q
     * @return
     */
    public Response queryListPost(QueryDomain q,String template) {
        log.info("queryPost--帖子查找--q=" + JSON.toJSONString(q));
        Response response = Response.newResponse();
        q.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        q.initMysqlLimit();
        List<PostDomain> postList = ((PostAble) service).selectPost(q);
        Context context = new VelocityContext();
        if (CollectionUtils.isNotEmpty(postList)) {
            String latLngKey = "";
            int index = 1000;
            for (PostDomain mTemp : postList) {
                latLngKey = mTemp.getPostLat().toString() + "_" + mTemp.getPostLng().toString();
                //设置表情
                mTemp.setBrief(VelocityUtil.mergeEmoticon(mTemp.getBrief()));
            }
        }
        context.put("postList", postList);
        context.put("collectionUtils", new CollectionUtils());
        response.setBody(VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), template));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("isHadPre", q.getPage() > 1);
        resultMap.put("isHadNext", q.getPageSize() == postList.size());
        response.setObject(resultMap);
        response.setSuccess(true);
        return response;
    }


    public Response paginator(QueryDomain queryDomain) {
        Response response = Response.newResponse();
        long totalCount = ((PostAble) service).selectPostCount(queryDomain);
        PaginatorImpl paginator = new PaginatorImpl(queryDomain.getPage(), SystemConstant.DEFAULT_PAGESIZE, (int) totalCount);
        Context context = new VelocityContext();
        context.put("paginatorImpl", paginator);
        String paginatorString = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/post/post_paginator.vm");
        response.setBody(paginatorString);
        response.setSuccess(true);
        return response;
    }

}
