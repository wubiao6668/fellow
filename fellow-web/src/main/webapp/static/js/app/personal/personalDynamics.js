$(function () {
    ace.settingFunction.main_container_fixed(null, true);
    $(".dropz").dropzone({
        url: "/personal/home/uploadImge",
        addRemoveLinks: false,
        maxFiles: 10,
        maxFilesize: 5,
        previewsContainer: false,
        acceptedFiles: ".BMP,.JPG,.JPEG,.PNG,.GIF",
        success: function (v1, reponse) {
            if (reponse && reponse.success) {
                debugger;
                var imgJson = reponse.object;
                var jqImgHtml = $($("#replyImagListTemplateId").html());
                jqImgHtml.find("input[name='imgStrs']").val(imgJson.root + imgJson.relative);
                jqImgHtml.find("img").attr("src", imgJson.root + imgJson.relative);
                $("#publishImagListId").append(jqImgHtml);
//                $('#area2').html($('#area2').html() + "<img class='user'  src='" + imgJson.root + imgJson.relative + "' />");
            }
        }
    });
    loadFriendDynamics();
})
function showHideComment(obj) {
    var jqCommentReplyObj = $(obj).parent().prev();
    $(jqCommentReplyObj).toggle("fast",
        function () {
            if ($(obj).find("i").hasClass("fa-angle-double-down")){
                $(obj).html('<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>展开评论');
            }else{
                $(obj).html('<i class="ace-icon fa fa-angle-double-down icon-only bigger-110"></i>收起评论');
            }
        }
    );

}
function showUpUser(obj){
    $.get("/personal/home/dynamics/showUpUser?personalId=" + $(obj).attr("personalId"),function(message){
        bootbox.alert(message, null);
    });
}

function publishLineTime(obj) {
    var timelineJson = $("#timelimeFormId").serializeJSON();
    timelineJson.content = $('#timelineArea').val();
    $.ajax({
        url: "/personal/home/dynamics/publish",
        type: 'POST',
        data: timelineJson,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#timelimeFormId")[0].reset();
                    $("#publishImagListId").html("");
                    $("#peronalDynamicTimelineId").html("");
                    var loadBtnJq = $("#loadMoreDynamic");
                    loadBtnJq.attr("page",0);
                    loadBtnJq.attr("idLt","");
                    loadBtnJq.attr("count",0);
                    loadFriendDynamics();
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "发布动态失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
    return false;
}
function replyImgDelete(obj) {
    $(obj).parent().parent().remove();
}
var em1 = $('#emoji1').emoji({
    insertAfter: function (item) {
        $('#timelineArea').insertContent('${_' + item.name + '_}')
    }
});
/**
 * Created by wubiao on 2017/3/7.
 */
function loadFriendDynamics(){
    $loadBtn = $("#loadMoreDynamic");
    var count = $.trim($loadBtn.attr("count"));
    var idLt = $.trim($loadBtn.attr("idLt"));
    $.ajax({
        url: "/personal/home/dynamics/queryDynamics",
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
        url: "/personal/home/dynamics/selectPerDynCommentByPersonalId",
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
        url: "/personal/home/dynamics/commentReply",
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







































































