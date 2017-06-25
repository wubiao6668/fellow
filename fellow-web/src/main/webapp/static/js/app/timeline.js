/**
 * Created by wubiao on 2016/10/10.
 */
$(function(){
    $(".dropz").dropzone({
        url: "/personal/home/uploadImge",
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
                $("#publishImagListId").append(jqImgHtml);
//                $('#area2').html($('#area2').html() + "<img class='user'  src='" + imgJson.root + imgJson.relative + "' />");
            }
        }
    });

    loadPeronalDynamics();

})

var em1 = $('#emoji1').emoji({
    insertAfter: function (item) {
        $('#timelineArea').insertContent('${_' + item.name + '_}')
    }
});

function replyImgDelete(obj) {
    $(obj).parent().parent().remove();
}

function publishLineTime(obj) {
    debugger;
    var timelineJson = $("#timelimeFormId").serializeJSON();
    timelineJson.content = $('#timelineArea').val();
    $.ajax({
        url: "/personal/dynamics/publish",
        type: 'POST',
        data: timelineJson,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
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

function loadPeronalDynamics() {
    $loadBtn = $("#loadMoreDynamic");
    var page = $.trim($loadBtn.attr("page"));
    var limit = $.trim($loadBtn.attr("limit"));
    var idLt = $.trim($loadBtn.attr("idLt"));
    $.ajax({
        url: "/personal/dynamics/loadPersonalDynamic",
        type: 'POST',
        data: "&page=" + page + "&limit=" + limit +"&idLt=" + idLt,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#peronalDynamicTimelineId").append(result.object.html);
                    $loadBtn.attr("idLt",$.trim(result.object.idLt));
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
}