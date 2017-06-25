/**
 * Created by wubiao on 2016/11/11.
 */
$(function () {
    $('#message-scroll').ace_scroll({size: 400});
    $('#message-scroll').ace_scroll("end");
});

function loadMessageDetail(page,pageSize){
    $("#loadMoreDetailId").attr("disabled",true);
    var param = {};
    param.page = page;
    param.pageSize = pageSize;
    param.sendAccount = $("#sendAccount").val();
    param.maxId = $("#maxId").val();
    $.ajax({
        url: "/message/personal/findMessageDetailList",
        type: 'POST',
        data: param,
        dataType: "html",
        success: function (messageDetailHtml) {
            $("#loadMoreDetailId").parent().remove();
            $("#chatListId").prepend(messageDetailHtml);
            //$('#message-scroll').ace_scroll("end");/
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}
function loadMessageReceiveDetail(page,pageSize){
    if("" == $.trim($("#sendAccount").val())){
        return false;
    }
    var param = {};
    param.page = page;
    param.pageSize = pageSize;
    param.sendAccount = $("#sendAccount").val();
    param.minId = $("#minId").val();
    $.ajax({
        url: "/message/personal/findReceiveMessages",
        type: 'POST',
        data: param,
        dataType: "html",
        success: function (messageDetailHtml) {
            if("" != $.trim(messageDetailHtml)){
                $("#minId").remove();
                $("#chatListId").append(messageDetailHtml);
                $('#message-scroll').ace_scroll("end");
            }
        },
        error: function (request, ajaxOptions, thrownError) {
            //alert("系统或网络异常！");
        }
    });
}
function sendMessage(obj){
    var timelineJson = $("#timelimeFormId").serializeJSON();
    timelineJson.message = $("#timelineArea").val();
    timelineJson.receiveAccout = $("#sendAccount").val();
    $.ajax({
        url: "/message/personal/sendMessageText",
        type: 'POST',
        data: timelineJson,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#noMessageChatDiv").remove();
                    var chatLiObj = $($("#chatLiTemplateId").html());
                    chatLiObj.find("span[name='liTime']").html(result.object);
                    chatLiObj.find("p[name='liMessage']").html(result.body.content);
                    chatLiObj.attr("msgId",result.body.id);
                    chatLiObj.attr("receiveAccount",result.body.receiveAccount);
                    chatLiObj.attr("sendAccount",result.body.sendAccount);
                    $("#timelineArea").val("");
                    $("#chatListId").append(chatLiObj);
                    $('#message-scroll').ace_scroll("end");
                    return;
                }
            }
            message = ("" == $.trim(message) ? "发送消息失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
    return false;
}

$(".dropz").dropzone({
    url: "/message/personal/sendMessageImge?receiveAccount=" + $("#sendAccount").val(),
    addRemoveLinks: false,
    maxFiles: 10,
    maxFilesize: 5,
    previewsContainer: false,
    acceptedFiles: ".BMP,.JPG,.JPEG,.PNG,.GIF",
    success: function (v1, reponse) {
        if (reponse && reponse.success) {
            var chatLiObj = $($("#chatLiTemplateId").html());
            chatLiObj.find("span[name='liTime']").html(reponse.object.time);
            chatLiObj.find("p[name='liMessage']").html("<img src='" + reponse.object.img + "'>");
            chatLiObj.attr("msgId",reponse.object.id);
            chatLiObj.attr("receiveAccount",reponse.object.receiveAccount);
            chatLiObj.attr("sendAccount",reponse.object.sendAccount);
            $("#timelineArea").val("");
            $("#chatListId").append(chatLiObj);
            $('#message-scroll').ace_scroll("end");
            return;
        }else{
            alert(reponse.message);
        }
    }
});

var em1 = $('#emoji1').emoji({
    insertAfter: function (item) {
        $('#timelineArea').insertContent('${_' + item.name + '_}')
    }
});

