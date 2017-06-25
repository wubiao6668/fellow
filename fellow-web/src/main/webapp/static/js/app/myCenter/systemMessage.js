$(function () {
    setTimeout(function () {
        $('[data-rel=popover]').popover({html: true});
        $('[data-rel=popover]').on('shown.bs.popover', function () {
            loadReplyEmoji();
        })
    }, 500);
})
function showHideMessage(obj) {
    var jqCommentReplyObj = $(obj).parent().prev();
    $(jqCommentReplyObj).toggle(
        "fast",
        function () {
            if ($(obj).find("i").hasClass("fa-angle-double-down")) {
                $(obj).html('展开<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>');
            } else {
                $(obj).html('收起<i class="ace-icon fa fa-angle-double-down icon-only bigger-110"></i>');
            }
        }
    );
}
function loadReplyEmoji() {
    $.each($('[name="emojiReply"]'), function () {
        var obj = this;
        $(this).emoji({
            insertAfter: function (item) {
                //$('#area1').insertContent('${_' + item.name + '_}')
                //popover-title
                $(obj).parent().parent().find("textarea").insertContent('${_' + item.name + '_}')
                //console.info(this);
                //console.info(item.name);
                //console.info(obj);

            }
        });
    });
}

function loadSystemMessageList(page) {
    //systemMessageListId
    $.get("/myCenter/systemMessage/loadSystemMessage?page=" + page, function (html) {
        $("#systemMessageListId").html(html);
    });
}

function gotoMessagePage(obj) {
    var gotoPageTextValue = $.trim($("#gotoPageText").val());
    var page = gotoPageTextValue;
    if ("" == gotoPageTextValue || isNaN(gotoPageTextValue) || 0 >= parseInt(gotoPageTextValue)) {
        page = 1
    } else if (parseInt($(obj).attr("totalPages")) < parseInt(gotoPageTextValue)) {
        page = $(obj).attr("totalPages");
    }
    loadSystemMessageList(page);
}

function loadMoreSystemMessage(obj) {
    var $messageListLi = $(obj).closest("[name='messageListLi']");
    $messageListLi.find("[name='messageDetailToggle']").show();
    var maxId = $(obj).attr("maxId");
    var messageId = $(obj).attr("messageId");
    var param = {messageId: messageId};
    if ("" != $.trim(maxId)) {
        param.maxId = maxId;
    }
    $.ajax({
        url: "/myCenter/systemMessage/loadMoreSysteMessage",
        type: 'POST',
        data: param,
        dataType: "json",
        success: function (response) {
            var message = "";
            if (response) {
                message = response.message;
                if (response.success) {
                    $messageListLi.find("[name='messageTextP']").remove();
                    var dataObject = response.object;
                    if (dataObject && dataObject.length > 0) {
                        var $listDiv = $messageListLi.find("[name='listDiv']");
                        for (var i = 0; i < dataObject.length; i++) {
                            $listDiv.append('<p class="no-margin">' + dataObject[i].message + '</p>');
                        }
                        $(obj).attr("maxId", dataObject[dataObject.length - 1].id);
                    }
                    if (!response.body) {
                        $(obj).parent().hide();
                    }
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "查询更多失败！" : message);
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}

function replySystemMessage(obj,btnId) {
    var $popoverContent = $(obj).closest(".popover-content");
    var param = $popoverContent.find(".form-field").serializeJSON();
    $.ajax({
        url: "/myCenter/systemMessage/replySystemMessage",
        type: 'POST',
        data: param,
        dataType: "json",
        success: function (response) {
            var message = "";
            if (response) {
                message = response.message;
                if (response.success) {
                    var $messageListLi = $(obj).closest("[name='messageListLi']");
                    $messageListLi.find("[name='listDiv']").html('<p class="no-margin" name="messageTextP">'+response.object.message+'</p>');
                    $messageListLi.find("[name='lookForMore']").show();
                    $messageListLi.find("[name='lookForMoreA']").attr("maxId",response.object.id);
                    $("#replyPopover_" + btnId).click();
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "回复失败！" : message);
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });

}

function closeRely(btnId){
    $("#replyPopover_" + btnId).click();
}