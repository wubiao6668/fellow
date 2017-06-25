/**
 * Created by wubiao on 2016/9/21.
 */
$(function () {
    ace.settingFunction.main_container_fixed(null, true);
    $(".post_detail img").wrap(function () {
        return '<a class="gallery" href="' + $(this).attr("src") + '"/>';
    });
    setTimeout(function () {
        initColorbox("gal", 'a.gallery');
    }, 500);
    loadPostReplyList(1);
})

$(".dropz").dropzone({
    url: "/post/lost/post/uploadImge",
    addRemoveLinks: false,
    maxFiles: 10,
    maxFilesize: 5,
    previewsContainer: false,
    acceptedFiles: ".BMP,.JPG,.JPEG,.PNG,.GIF",
    success: function (v1, reponse) {
        if (reponse && reponse.success) {
            var imgJson = reponse.object;
            var jqImgHtml = $($("#replyImagListTemplateId").html());
            jqImgHtml.find("input[name='imgStrs']").val(imgJson.root + imgJson.relative);
            jqImgHtml.find("img").attr("src", imgJson.root + imgJson.relative);
            $("#replyImagListId").append(jqImgHtml);
//                $('#area2').html($('#area2').html() + "<img class='user'  src='" + imgJson.root + imgJson.relative + "' />");
        }
    }
});

var em1 = $('#emoji1').emoji({
    insertAfter: function (item) {
        $('#area1').insertContent('${_' + item.name + '_}')
    }
});

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

function reply() {
    var replyJson = $("#lostPostFormReply").serializeJSON();
    replyJson.content = $('#area1').val();
    $.ajax({
        url: "/post/lost/postReply/reply",
        type: 'POST',
        data: replyJson,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#replyImagListId").html("");
                    $("#area1").val("");
                    loadPostReplyList(1);
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
    return false;
}

function replyImgDelete(obj) {
    $(obj).parent().parent().remove();
}

function loadPostReplyList(page) {
    $.ajax({
        url: "/post/lost/postReply/queryReply",
        type: 'POST',
        data: "postId=" + $("#postId").val() + "&page=" + $.trim(page) + "&sortColumns=" + $("#replySortFieldId").val(),
        dataType: "html",
        success: function (result) {
            $("#lostPostList").html(result);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}

function gotoPage(obj) {
    var gotoPageTextValue = $.trim($("#gotoPageText").val());
    var page = gotoPageTextValue;
    if ("" == gotoPageTextValue || isNaN(gotoPageTextValue) || 0 >= parseInt(gotoPageTextValue)) {
        page = 1
    } else if (parseInt($(obj).attr("totalPages")) < parseInt(gotoPageTextValue)) {
        page = $(obj).attr("totalPages");
    }
    var limit = $(obj).attr("limit");
    loadPostReplyList(page);
}


function commentReply(obj) {
    $.ajax({
        url: "/post/lost/postCommentReply/reply",
        type: 'POST',
        data: $(obj).parent().parent().find(".form-field").serializeJSON(),
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).closest(".popover ").prev().click();
                    $(obj).closest(".profile-activity").find("[name='commentReplyList']").prepend(result.body);
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

function loadCommentReply(obj) {
    $.ajax({
        url: "/post/lost/postCommentReply/loadReply",
        type: 'POST',
        data: "postId=" + $(obj).attr("postId") + "&replyId=" + $(obj).attr("replyId") + "&postType=" + $(obj).attr("postType") + "&maxId=" + $(obj).attr("maxId") + "&limit=" + $(obj).attr("limit"),
        dataType: "json",
        success: function (response) {
            //信息
            var message = "";
            if (response) {
                //debugger;
                message = response.message;
                if (response.success) {
                    $(obj).parent().parent().find("div[name='commentReplyList']").append(response.body);
                    loadPopoverAndEmoji();

                    if (response.object && response.object.hasNextPage) {
                        $(obj).attr("maxId", $.trim(response.object.maxId));
                    } else {
                        $(obj).parent().hide();
                    }
                    return;
                }
            }
            message = ("" == $.trim(message) ? "加载回复列表失败！" : message );
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

function setSortField(field) {
    $('#replySortFieldId').val(field);
    loadPostReplyList(1);
}

function deleteReplyByAccount(obj,replyId, postId) {
    $.ajax({
            url: "/post/lost/postCommentReply/loadReply",
            type: 'POST',
            data: "id=" + replyId + "&postId=" + postId,
            dataType: "json",
            success: function (response) {
                //信息
                var message = "";
                if (response) {
                    //debugger;
                    message = response.message;
                    if (response.success) {
                        $(obj).closest(".profile-activity").remove();
                        return;
                    }
                }
                message = ("" == $.trim(message) ? "删除评论失败！" : message );
                alert(message);
            },
            error: function (request, ajaxOptions, thrownError) {
                alert("系统或网络异常！");
            }
        }
    )
    ;
}

function deleteReplyByAccount(obj,replyId, postId) {
    $.ajax({
            url: "/post/lost/postReply/deleteReplyByAccount",
            type: 'POST',
            data: "id=" + replyId + "&postId=" + postId,
            dataType: "json",
            success: function (response) {
                //信息
                var message = "";
                if (response) {
                    //debugger;
                    message = response.message;
                    if (response.success) {
                        $(obj).closest(".profile-activity").remove();
                        return;
                    }
                }
                message = ("" == $.trim(message) ? "删除评论失败！" : message );
                alert(message);
            },
            error: function (request, ajaxOptions, thrownError) {
                alert("系统或网络异常！");
            }
        }
    );
}

function deleteReplyById(obj,id,postId,replyId){
    $.ajax({
            url: "/post/lost/postCommentReply/deleteReplyById",
            type: 'POST',
            data: "id=" + id + "&postId=" + postId+ "&replyId=" + replyId,
            dataType: "json",
            success: function (response) {
                //信息
                var message = "";
                if (response) {
                    //debugger;
                    message = response.message;
                    if (response.success) {
                        $(obj).closest("[name='commentReplyDiv']").remove();
                        return;
                    }
                }
                message = ("" == $.trim(message) ? "删除评论失败！" : message );
                alert(message);
            },
            error: function (request, ajaxOptions, thrownError) {
                alert("系统或网络异常！");
            }
        }
    );
}