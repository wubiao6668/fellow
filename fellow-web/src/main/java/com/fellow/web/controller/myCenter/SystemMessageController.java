package com.fellow.web.controller.myCenter;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.ContentTypeEnum;
import com.fellow.domain.enums.MessageTypeEnum;
import com.fellow.domain.enums.SystemMessageStatusEnum;
import com.fellow.domain.enums.SystemMessageTypeEnum;
import com.fellow.domain.model.SystemMessage;
import com.fellow.domain.model.SystemMessageDetail;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.fellow.domain.query.SystemMessageDetailQuery;
import com.fellow.domain.query.SystemMessageQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.SystemMessageDetailService;
import com.fellow.service.SystemMessageService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wubiao on 2016/11/24.
 */
@Controller
@RequestMapping(SystemMessageController.VIEW_PATH)
public class SystemMessageController extends WebAbstract<SystemMessageService> {
    public static final String VIEW_PATH = "/myCenter/systemMessage";
    @Resource
    private SystemMessageDetailService systemMessageDetailService;

    @RequestMapping(value = "/index")
    public String index(Model model) {
        SystemMessageQuery messageQuery = new SystemMessageQuery();
        messageQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
//        messageQuery.setPage(1);
        resolveMessage(messageQuery, model);
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/loadSystemMessage")
    public String loadSystemMessage(SystemMessageQuery messageQuery, Model model) {
        messageQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        resolveMessage(messageQuery, model);
        return VIEW_PATH + "/systemMessageList";
    }

    private void resolveMessage(SystemMessageQuery messageQuery, Model model) {
        messageQuery.initMysqlLimit();
        messageQuery.setSendAccount(super.getAccount());
        messageQuery.setReceiveAccount(super.getAccount());
        List<SystemMessage> systemMessageList = service.selectSystemMessage(messageQuery);
        final List<Long> sendHadReadIdList = new ArrayList<Long>();
        final List<Long> receiveHadReadIdList = new ArrayList<Long>();
        if (CollectionUtils.isNotEmpty(systemMessageList)) {
            for (SystemMessage systemMessageTemp : systemMessageList) {
                if (super.getAccount().equals(systemMessageTemp.getSendAccount())) {
                    if (systemMessageTemp.getSendDetailUnreadNum() >= 1) {
                        sendHadReadIdList.add(systemMessageTemp.getId());
                    }
                } else {
                    if (systemMessageTemp.getReceiveDetailUnreadNum() >= 1) {
                        receiveHadReadIdList.add(systemMessageTemp.getId());
                    }
                }
                if (systemMessageTemp.getMessageType().intValue() == SystemMessageTypeEnum.FRIEND_REQUEST.getKey()) {
                    if (super.getAccount().equals(systemMessageTemp.getSendAccount())) {
                        systemMessageTemp.setTitleTip(SystemConstant.SYSTEM_MESSAGE_TITLE_FRIEND_TO);
                        systemMessageTemp.setMessageType(SystemMessageTypeEnum.FRIEND_REQUEST_TO.getKey());
                        systemMessageTemp.setReceiveDetailUnreadNum(systemMessageTemp.getSendDetailUnreadNum());
                        systemMessageTemp.setSendAccount(systemMessageTemp.getReceiveAccount());
                    } else {
                        systemMessageTemp.setTitleTip(SystemConstant.SYSTEM_MESSAGE_TITLE_FRIEND_FROM);
                        systemMessageTemp.setMessageType(SystemMessageTypeEnum.FRIEND_REQUEST_FROM.getKey());
                    }
                    if (super.getAccount().equals(systemMessageTemp.getLastAccout())) {
                        systemMessageTemp.setMessage("我：" + VelocityUtil.mergeEmoticon(systemMessageTemp.getMessage()));
                    } else {
                        systemMessageTemp.setMessage("对方：" + VelocityUtil.mergeEmoticon(systemMessageTemp.getMessage()));
                    }
                } else {
                    systemMessageTemp.setTitleTip(SystemConstant.SYSTEM_MESSAGE_TITLE_NOTICE_FROM);
                }
            }
        }
        long count = service.selectSystemMessageCount(messageQuery);
        PaginatorImpl paginator = new PaginatorImpl(messageQuery.getPage(), messageQuery.getPageSize(), (int) count);
        final SystemMessage updateSendHadReadMessage = new SystemMessage();
        updateSendHadReadMessage.setUpdateAccount(super.getAccount());
        updateSendHadReadMessage.setUpdateName(super.getUserName());
        updateSendHadReadMessage.setSendAccount(super.getAccount());
        updateSendHadReadMessage.setIdList(sendHadReadIdList);

        final SystemMessage updateReceiveHadReadMessage = new SystemMessage();
        updateReceiveHadReadMessage.setUpdateAccount(super.getAccount());
        updateReceiveHadReadMessage.setUpdateName(super.getUserName());
        updateReceiveHadReadMessage.setReceiveAccount(super.getAccount());
        updateReceiveHadReadMessage.setIdList(receiveHadReadIdList);
        new Thread() {
            @Override
            public void run() {
                if (CollectionUtils.isNotEmpty(sendHadReadIdList)) {
                    service.updateSendHadRead(updateSendHadReadMessage);
                }
                if (CollectionUtils.isNotEmpty(receiveHadReadIdList)) {
                    service.updateReceiveHadRead(updateReceiveHadReadMessage);
                }
            }
        }.start();

        model.addAttribute("messageList", systemMessageList);
        model.addAttribute("paginator", paginator);
        model.addAttribute("sysMessTypeEnumMap", SystemMessageTypeEnum.getEnumMap());
        model.addAttribute("sysMessStatusEnumMap", SystemMessageStatusEnum.getEnumMap());
    }

    @RequestMapping(value = "/loadMoreSysteMessage", method = {RequestMethod.POST})
    @ResponseBody
    public Response loadMoreSysteMessage(SystemMessageDetailQuery systemMessageDetailQuery) {
        Response response = Response.newResponse();
        systemMessageDetailQuery.setPage(1);
        systemMessageDetailQuery.setPageSize(20);
        systemMessageDetailQuery.initMysqlLimit();
        systemMessageDetailQuery.setSortColumns(" id DESC");
        systemMessageDetailQuery.setReceiveAccount(super.getAccount());
        List<SystemMessageDetail> messageDetailList = systemMessageDetailService.selectMySytemMessage(systemMessageDetailQuery);
        if (CollectionUtils.isNotEmpty(messageDetailList)) {
            for (SystemMessageDetail detailTemp : messageDetailList) {
                if (detailTemp.getSendAccount().equals(super.getAccount())) {
                    detailTemp.setMessage("我：" + VelocityUtil.mergeEmoticon(detailTemp.getMessage()));
                } else {
                    detailTemp.setMessage("对方：" + VelocityUtil.mergeEmoticon(detailTemp.getMessage()));
                }
            }
        }
        response.setObject(messageDetailList);
        response.setSuccess(true);
        response.setBody(messageDetailList.size() == systemMessageDetailQuery.getPageSize() ? true : false);
        return response;
    }

    @RequestMapping(value = "/replySystemMessage", method = RequestMethod.POST)
    @ResponseBody
    public Response replySystemMessage(SystemMessageDetail systemMessageDetail) {
        Response response = Response.newResponse();
        SystemMessage message = (SystemMessage) service.getByKey(systemMessageDetail.getMessageId());
        if (null == message || (!super.getAccount().equals(message.getSendAccount()) && super.getAccount().equals(message.getReceiveAccount()))) {
            response.setMessage("发送消息失败！");
        } else {
            //TODO 是否为黑名单
            systemMessageDetail.setSendTime(new Date());
            systemMessageDetail.setSendAccount(super.getAccount());
            systemMessageDetail.setReceiveAccount(super.getAccount().equals(message.getSendAccount()) ? message.getReceiveAccount() : message.getSendAccount());
            systemMessageDetail.setMessageType(MessageTypeEnum.USER.getKey());
            systemMessageDetail.setContentType(ContentTypeEnum.TEXT.getKey());
            systemMessageDetail.setCreateAccount(super.getAccount());
            systemMessageDetail.setCreateName(super.getUserName());
            systemMessageDetailService.insertSelective(systemMessageDetail);
            systemMessageDetail.setMessage("我：" + VelocityUtil.mergeEmoticon(systemMessageDetail.getMessage()));
            response.setObject(systemMessageDetail);
            response.setSuccess(true);
        }
        return response;
    }

    @RequestMapping(value = "/deleteMessage")
    @ResponseBody
    public Response deleteMessage(Long[] ids) {
        Response response = Response.newResponse();
        if (ArrayUtils.isEmpty(ids) || ids.length >= SystemConstant.DEFAULT_PAGESIZE) {
            response.setMessage("参数非法！");
            return response;
        }
        SystemMessageQuery messageQuery = new SystemMessageQuery();
        messageQuery.setIdList(Arrays.asList(ids));
        List<SystemMessage> systemMessageList = service.selectMessageByIds(messageQuery);
        List<Long> sendIdList = new ArrayList<Long>();
        List<Long> receiveIdList = new ArrayList<Long>();
        for (SystemMessage messageTemp : systemMessageList) {
            if (super.getAccount().equals(messageTemp.getSendAccount())) {
                sendIdList.add(messageTemp.getId());
            }
            if (super.getAccount().equals(messageTemp.getReceiveAccount())) {
                receiveIdList.add(messageTemp.getId());
            }
        }
        SystemMessage sendMessage = null;
        SystemMessageDetail sendMessageDetail = null;
        if (sendIdList.size() > 0) {
            sendMessage = new SystemMessage();
            sendMessage.setSendAccount(super.getAccount());
            sendMessage.setUpdateAccount(super.getAccount());
            sendMessage.setUpdateName(super.getUserName());
            sendMessageDetail = new SystemMessageDetail();
            sendMessage.setSystemMessageDetail(sendMessageDetail);
            sendMessageDetail.setSendAccount(super.getAccount());
            sendMessageDetail.setUpdateAccount(super.getAccount());
            sendMessageDetail.setUpdateName(super.getUserName());
            if (sendIdList.size() == 1) {
                sendMessage.setId(sendIdList.get(0));
                sendMessageDetail.setMessageId(sendIdList.get(0));
            } else {
                sendMessage.setIdList(sendIdList);
                sendMessageDetail.setMessageIdList(sendIdList);
            }
        }
        SystemMessage receiveMessage = null;
        SystemMessageDetail receiveMessageDetail = null;
        if (receiveIdList.size() > 0) {
            receiveMessage = new SystemMessage();
            receiveMessage.setReceiveAccount(super.getAccount());
            receiveMessage.setUpdateAccount(super.getAccount());
            receiveMessage.setUpdateName(super.getUserName());
            receiveMessageDetail = new SystemMessageDetail();
            receiveMessage.setSystemMessageDetail(receiveMessageDetail);
            receiveMessageDetail.setReceiveAccount(super.getAccount());
            receiveMessageDetail.setUpdateAccount(super.getAccount());
            receiveMessageDetail.setUpdateName(super.getUserName());
            if (receiveIdList.size() == 1) {
                receiveMessage.setId(receiveIdList.get(0));
                receiveMessageDetail.setMessageId(receiveIdList.get(0));
            } else {
                receiveMessage.setIdList(receiveIdList);
                receiveMessageDetail.setMessageIdList(receiveIdList);
            }
        }
        service.deleteMessageByAccount(sendMessage, receiveMessage);
        response.setSuccess(true);
        return response;
    }

}
