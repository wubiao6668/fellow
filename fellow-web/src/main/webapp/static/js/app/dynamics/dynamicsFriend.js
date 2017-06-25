/**
 * Created by wubiao on 2017/3/7.
 */
function loadFriendDynamics(){
    $loadBtn = $("#loadMoreDynamic");
    var count = $.trim($loadBtn.attr("count"));

    var idLt = $.trim($loadBtn.attr("idLt"));
    $.ajax({
        url: "/dynamics/friend/queryDynamics",
        type: 'POST',
        data: "&count=" + count +"&idLt=" + idLt,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    if (!result.object.isHadNext){
                        $loadBtn.hide();
                    }
                    $("#peronalDynamicTimelineId").append($.trim(result.body));
                    $loadBtn.attr("idLt",$.trim(result.object.idLt));
                    $loadBtn.attr("count",2);
                    return;
                }
            }
            message = ("" == $.trim(message) ? "加载动态失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
    return false;
}

function loadDynamicsReply(obj){
    var idLt = $.trim($(obj).attr("idLt"));
    var personalId = $.trim($(obj).attr("personalId"));
    $.ajax({
        url: "/dynamics/friend/selectPerDynCommentByPersonalId",
        type: 'POST',
        data: "personalId=" + personalId + "&idLt=" + idLt,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    if (!result.object.isHadNext){
                        $(obj).hide();
                    }
                    $(obj).closest("[name='commentDiv']").find("[name='commentListDiv']").append($.trim(result.body));
                    $(obj).attr("idLt",$.trim(result.object.idLt));
                    return;
                }
            }
            //message = ("" == $.trim(message) ? "加载动态失败！" : message );
            //alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}

function loadReplyEmoji() {
    $.each($('[name="emojiReply"]'), function () {
        var obj = this;
        if ($(obj).find(".emoji-inner").length < 1){
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
        }

    });
}

function commentReply(obj){
    var data = $(obj).parent().parent().find(".form-field").serializeJSON();
    var param = {};
    param.personalId=data.replyId;
    param.toAccount=data.toAccount;
    param.content=data.content;
    $.ajax({
        url: "/dynamics/friend/commentReply",
        type: 'POST',
        data: param,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).closest(".popover ").prev().click();
                    $(obj).closest("[name='commentDiv']").find("[name='commentListDiv']").prepend(result.body);
                    loadPopoverAndEmoji();
                    return;
                }
            }
            message = ("" == $.trim(message) ? "回复失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}


function loadPopoverAndEmoji() {
    setTimeout(function () {
        $('[data-rel=popover]').popover({html: true});
        $('[data-rel=popover]').on('shown.bs.popover', function () {
            loadReplyEmoji();
        })
    }, 500);
}

/**
 * 爱心
 * @param url
 */
function commentThumbs(obj,url){
    //$("[name='commentThumbsBtn']").attr("disabled",true);
    var $thumbsBtn = $("[name='commentThumbsBtn'] i.red");
    //console.info($(obj).find("i.red").length);
    //$.each(,function(){
    //    $(this).find("")
    //});
    //return;
    var  thumbsType;
    var num = 0;
    var redNum = 0;
    if ($(obj).find("i.red").length > 0){
        thumbsType = $(obj).attr("thumbsCancel");
        num = -1;
    }else{
        thumbsType = $(obj).attr("thumbsType");
        num = + 1;
    }
    $.ajax({
        url: url + "&thumbsType=" + thumbsType,
        type: 'get',
        dataType: "json",
        success: function (result) {
            $([name='commentThumbsBtn']).removeAttr("disabled");
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).find("[name='num']").html(parseInt($(obj).find("[name='num']").html()) + num);
                    if ($(obj).find("i.red").length > 0){
                        $(obj).find("i").removeClass("red");
                    }else{
                        $(obj).find("i").addClass("red");
                        $thumbsBtn.removeClass("red");
                        $thumbsBtn.html(parseInt($thumbsBtn.html()) - 1);
                    }
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "爱心失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            $([name='commentThumbsBtn']).removeAttr("disabled");
            alert("系统或网络异常！");
        }
    });
}







































































