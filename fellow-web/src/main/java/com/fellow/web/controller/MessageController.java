package com.fellow.web.controller;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.file.ThumbnailsUtil;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.*;
import com.fellow.domain.model.Message;
import com.fellow.domain.model.MessageDetail;
import com.fellow.domain.model.MessageImg;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.fellow.domain.query.MessageDetailQuery;
import com.fellow.domain.query.MessageQuery;
import com.fellow.domain.vo.MessageCount;
import com.fellow.domain.web.Response;
import com.fellow.domain.web.array.MessageDetailArray;
import com.fellow.service.MessageDetailService;
import com.fellow.service.MessageImgService;
import com.fellow.service.MessageService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * Created by wubiao on 2016/11/4.
 */
@Controller
@RequestMapping(MessageController.VIEW_PATH)
public class MessageController extends WebAbstract<MessageService> {
    public static final String VIEW_PATH = "/message/personal";

    @Resource
    private MessageDetailService messageDetailService;
    @Resource
    private MessageImgService messageImgService;

    @RequestMapping("/index")
    public String index() {
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/findPersonMessage", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Response findPersonMessage(MessageQuery messageQuery) {
        Map<String, String> sortMap = new HashMap<String, String>();
        sortMap.put("unRead", "unReadNum DESC");
        sortMap.put("updateTime", "send_time DESC");
        if (StringUtils.isNotBlank(sortMap.get(messageQuery.getSortColumns()))) {
            messageQuery.setSortColumns(sortMap.get(messageQuery.getSortColumns()));
        } else {
            messageQuery.setSortColumns(sortMap.get("unRead"));
        }
        Response response = Response.newResponse();
        messageQuery.setLongAccount(super.getAccount());
        messageQuery.setShortAccount(super.getAccount());
        messageQuery.initMysqlLimit();
        List<Message> messageList = service.selectPersonMessage(messageQuery);
        if (CollectionUtils.isNotEmpty(messageList)) {

            for (Message messageTemp : messageList) {
                try {
                    if (super.getAccount().equals(messageTemp.getLongAccount())) {
                        messageTemp.getOtherUser().setAccount(messageTemp.getShortAccount());
                    } else {
                        messageTemp.getOtherUser().setAccount(messageTemp.getLongAccount());
                    }
                    messageTemp.getSelfUser().setAccount(super.getAccount());
                    if (messageTemp.getContentType().intValue() == ContentTypeEnum.IMG.getKey()) {
                        continue;
                    }
                    messageTemp.setMessage(VelocityUtil.mergeEmoticon(messageTemp.getMessage()));
                } catch (Exception e) {
                    log.error("findPersonMessage--表情失败--e=", e);
                }
            }

        }
        MessageCount messageCount = service.selectPersonMessageCount(messageQuery);
        VelocityEngine velocityEngine = velocityConfig.getVelocityEngine();
        PaginatorImpl paginatorImpl = new PaginatorImpl(messageQuery.getPage(), messageQuery.getPageSize(), messageCount.getCount());
        Context context = new VelocityContext();
        context.put("messageList", messageList);
        context.put("paginatorImpl", paginatorImpl);
        context.put("collectionUtils", new CollectionUtils());
        Writer writer = new StringWriter();
        velocityEngine.mergeTemplate("/message/personal/messageList.vm", "UTF-8", context, writer);
        response.setBody(writer.toString());
        response.setSuccess(true);
        response.setObject(messageCount.getUnReadNum());
        return response;
    }

    @RequestMapping("/showMessageDetail")
    public String showMessageDetail(MessageDetailQuery messageDetailQuery, Model model) {
        messageDetailQuery.setPageSize(4);
        messageDetailQuery.setReceiveAccount(super.getAccount());
        messageDetailQuery.initMysqlLimit();
        messageDetailQuery.setSortColumns(" id desc");
        List<MessageDetail> messageDetailPageList = messageDetailService.selectMessagesAndSetHadRead(messageDetailQuery);
        loadDetailList(messageDetailQuery, model, messageDetailPageList);
        return VIEW_PATH + "/messageDetail";
    }

    @RequestMapping("/findMessageDetailList")
    public String findMessageDetailList(MessageDetailQuery messageDetailQuery, Model model) {
        messageDetailQuery.setReceiveAccount(super.getAccount());
        messageDetailQuery.initMysqlLimit();
        messageDetailQuery.setSortColumns(" id desc");
        List<MessageDetail> messageDetailPageList = messageDetailService.selectPersonalMessages(messageDetailQuery);
        loadDetailList(messageDetailQuery, model, messageDetailPageList);
        return VIEW_PATH + "/messageDetailList";
    }

    @RequestMapping("/findReceiveMessages")
    public String findReceiveMessages(MessageDetailQuery messageDetailQuery, Model model) {
        messageDetailQuery.setReceiveAccount(super.getAccount());
        messageDetailQuery.initMysqlLimit();
        messageDetailQuery.setSortColumns(" id asc");
        List<MessageDetail> messageDetaiList = messageDetailService.selectReceiveMessagesAndSetRead(messageDetailQuery);
        if (CollectionUtils.isNotEmpty(messageDetaiList)) {
            model.addAttribute("minId", messageDetaiList.get(0).getId());
            messageReplace(messageDetaiList);
        } else {
            model.addAttribute("minId", messageDetailQuery.getMinId());
        }
        model.addAttribute("messageList", messageDetaiList);
        return VIEW_PATH + "/messageDetailReceiveList";
    }

    public void loadDetailList(MessageDetailQuery messageDetailQuery, Model model, List<MessageDetail> messageDetailPageList) {
        model.addAttribute("page", messageDetailQuery.getPage());
        model.addAttribute("pageSize", messageDetailQuery.getPageSize());
        model.addAttribute("sendAccount", messageDetailQuery.getSendAccount());
        boolean isShowMore = false;
        if (CollectionUtils.isNotEmpty(messageDetailPageList)) {
            model.addAttribute("maxId", messageDetailPageList.get(messageDetailPageList.size() - 1).getId());
            model.addAttribute("minId", messageDetailPageList.get(0).getId());
            isShowMore = messageDetailPageList.size() < messageDetailQuery.getPageSize() ? false : true;
            Collections.reverse(messageDetailPageList);
            messageReplace(messageDetailPageList);
        } else {
            model.addAttribute("minId", 0);
        }
        model.addAttribute("messageList", messageDetailPageList);
        model.addAttribute("isShowMore", isShowMore);
    }

    @RequestMapping(value = "/sendMessageText", method = RequestMethod.POST)
    @ResponseBody
    public Response sendMessageText(Message message, String receiveAccount) {
        Response response = Response.newResponse();
        if (super.getAccount().compareToIgnoreCase(receiveAccount) > 0) {
            message.setLongAccount(super.getAccount());
            message.setShortAccount(receiveAccount);
            message.setShortUnreadNum(1);
            message.setLongUnreadNum(0);
        } else {
            message.setShortAccount(super.getAccount());
            message.setLongAccount(receiveAccount);
            message.setLongUnreadNum(1);
            message.setShortUnreadNum(0);
        }
        message.setSendTime(new Date());
        message.setMessageType(MessageTypeEnum.USER.getKey());
        message.setContentType(ContentTypeEnum.TEXT.getKey());
        message.setLongDelete(IsDeleteEnum.NO.getKey());
        message.setShortDelete(IsDeleteEnum.NO.getKey());

        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setSendAccount(super.getAccount());
        messageDetail.setSendTime(new Date());
        messageDetail.setReceiveAccount(receiveAccount);
        messageDetail.setIsRead(MessageReadEnum.UN_READ.getKey());
        messageDetail.setMessageType(MessageTypeEnum.USER.getKey());
        messageDetail.setMessage(message.getMessage());
        messageDetail.setContentType(ContentTypeEnum.TEXT.getKey());
        messageDetail.setCreateAccount(super.getAccount());
        messageDetail.setCreateName(super.getUserName());
        message.setMessageDetail(messageDetail);

        service.sendMessage(message);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("id", messageDetail.getId());
        resultMap.put("sendAccount", messageDetail.getSendAccount());
        resultMap.put("receiveAccount", messageDetail.getReceiveAccount());
        resultMap.put("content", VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), messageDetail.getMessage()));
        response.setBody(resultMap);
        response.setObject(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        response.setSuccess(true);

        return response;
    }


    @RequestMapping(value = "/sendMessageImge", method = RequestMethod.POST)
    @ResponseBody
    public Response sendMessageImge(String receiveAccount, MultipartFile file, HttpServletResponse servletResponse) {
        Response response = Response.newResponse();
        if (null != file && !file.isEmpty()) {
            try {
                String imageName = System.nanoTime() + RandomStringUtils.randomAlphabetic(4);
                String imageMonthDirectory = DateFormatUtils.format(new Date(), "yyyyMM");
                String parentPath = SystemConstant.IMAGE_ROOT_PATH + File.separator + imageMonthDirectory;
                String imageExtend = "jpg";
                ThumbnailsUtil.uploadImg(file, parentPath, imageName, imageExtend);
                Message message = new Message();
                if (super.getAccount().compareToIgnoreCase(receiveAccount) > 0) {
                    message.setLongAccount(super.getAccount());
                    message.setShortAccount(receiveAccount);
                    message.setShortUnreadNum(1);
                    message.setLongUnreadNum(0);
                } else {
                    message.setShortAccount(super.getAccount());
                    message.setLongAccount(receiveAccount);
                    message.setLongUnreadNum(1);
                    message.setShortUnreadNum(0);
                }
                message.setSendTime(new Date());
                message.setMessageType(MessageTypeEnum.USER.getKey());
                message.setMessage("图片");
                message.setContentType(ContentTypeEnum.IMG.getKey());
                message.setLongDelete(IsDeleteEnum.NO.getKey());
                message.setShortDelete(IsDeleteEnum.NO.getKey());

                String messageImgName = "img";

                MessageDetail messageDetail = new MessageDetail();
                messageDetail.setSendAccount(super.getAccount());
                messageDetail.setSendTime(new Date());
                messageDetail.setReceiveAccount(receiveAccount);
                messageDetail.setIsRead(MessageReadEnum.UN_READ.getKey());
                messageDetail.setMessageType(MessageTypeEnum.USER.getKey());
                messageDetail.setMessage("$!{" + messageImgName + "}");
                messageDetail.setContentType(ContentTypeEnum.IMG.getKey());
                messageDetail.setCreateAccount(super.getAccount());
                messageDetail.setCreateName(super.getUserName());
                message.setMessageDetail(messageDetail);

                MessageImg messageImg = new MessageImg();
                messageImg.setImgType(ImageTypeEnum.IMAGE.getKey());
                messageImg.setAccount(super.getAccount());
                messageImg.setImgName(messageImgName);
                messageImg.setImgRootPath(SystemConstant.IMG_ROOT_PATH_LOCALHOST);
                messageImg.setImgRelativePath(SystemConstant.IMAGE_UPLOAD_DIR + "/" + imageMonthDirectory + "/" + imageName + "." + imageExtend);
                messageImg.setCreateAccount(super.getAccount());
                messageImg.setCreateName(super.getUserName());
                message.setMessageImg(messageImg);
                service.sendMessage(message);
                Map<String, Object> imgMap = new HashMap<String, Object>();
                imgMap.put("id", messageDetail.getId());
                imgMap.put("sendAccount", messageDetail.getSendAccount());
                imgMap.put("receiveAccount", messageDetail.getReceiveAccount());
                imgMap.put("img", messageImg.getImgRootPath() + messageImg.getImgRelativePath());
                imgMap.put("time", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                response.setObject(imgMap);
                response.setSuccess(true);
            } catch (Exception e) {
                response.setMessage("不支持的图片类型");
                log.error("uploadImge--图片上传异常--e=", e);
            }
        }
        return response;
    }

    private void messageReplace(List<MessageDetail> messageDetailPageList) {
        try {
            VelocityEngine emoticonEngine = new VelocityEngine();
            Writer stringWriter = new StringWriter();
            List<Long> messageDetailIdList = new ArrayList<Long>();
            //图片id集合
            for (MessageDetail detailTemp : messageDetailPageList) {
                if (detailTemp.getContentType().intValue() == ContentTypeEnum.IMG.getKey()) {
                    messageDetailIdList.add(detailTemp.getId());
                    continue;
                }
                //图片
                detailTemp.setMessage(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), detailTemp.getMessage()));
            }
            if (CollectionUtils.isNotEmpty(messageDetailIdList)) {
                //查找图片
                List<MessageImg> messageImgList = messageImgService.selectImgByDetailIds(messageDetailIdList);
                Context context = null;
                if (CollectionUtils.isNotEmpty(messageImgList)) {
                    for (MessageDetail detailTemp : messageDetailPageList) {
                        if (detailTemp.getContentType().intValue() != ContentTypeEnum.IMG.getKey()) {
                            messageDetailIdList.add(detailTemp.getId());
                            continue;
                        }
                        for (MessageImg messageImgTemp : messageImgList) {
                            if (messageImgTemp.getMessageDetailId().longValue() == detailTemp.getId().longValue()) {
                                context = new VelocityContext();
                                context.put(messageImgTemp.getImgName(), "<img src='" + messageImgTemp.getImgRootPath() + messageImgTemp.getImgRelativePath() + "'>");
                                detailTemp.setMessage(VelocityUtil.mergeTextTemplate(context, velocityConfig.getVelocityEngine(), detailTemp.getMessage()));
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("findPersonMessage--", e);
        }
    }

    /**
     * 设置为已读
     *
     * @param accounts
     * @return
     */
    @RequestMapping(value = "/setHadRead")
    @ResponseBody
    public Response setHadRead(String[] accounts) {
        Response response = Response.newResponse();
        if (ArrayUtils.isEmpty(accounts)) {
            response.setMessage("记录不能为空！");
            return response;
        }
        messageDetailService.setAllHadReadBatch(accounts);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/deleteMessage")
    @ResponseBody
    public Response deleteMessage(String[] accounts) {
        Response response = Response.newResponse();
        if (ArrayUtils.isEmpty(accounts)) {
            response.setMessage("记录不能为空！");
            return response;
        }
        messageDetailService.deleteMessageBatch(accounts);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/deleteMessageDetail")
    @ResponseBody
    public Response deleteMessageDetail(MessageDetailArray messageDetailArrays) {
        Response response = Response.newResponse();
        MessageDetail[] messageDetails = messageDetailArrays.getMessageDetails();
        if (ArrayUtils.isEmpty(messageDetails)) {
            response.setMessage("请选择需要删除的消息！");
            return response;
        }
        List<MessageDetail> detailList = new ArrayList<MessageDetail>();
        MessageDetail updateDetail = null;
        for (MessageDetail detailTemp : messageDetails) {
            updateDetail = new MessageDetail();
            updateDetail.setId(detailTemp.getId());
            updateDetail.setSendAccount(detailTemp.getSendAccount());
            updateDetail.setReceiveAccount(detailTemp.getReceiveAccount());
            updateDetail.setUpdateAccount(super.getAccount());
            updateDetail.setUpdateName(super.getUserName());
            detailList.add(updateDetail);
        }
        messageDetailService.updateDeleteMessageByIdBatch(detailList.toArray(new MessageDetail[]{}));
        response.setSuccess(true);
        return response;
    }


}
