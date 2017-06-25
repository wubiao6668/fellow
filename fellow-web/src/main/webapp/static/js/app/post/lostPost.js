$(function () {
    //loadPostList(1, 3);
    //datepicker plugin
    //link
    $('.date-picker').datepicker({
        autoclose: true,
        language: 'zh-CN',
        todayHighlight: true
    })
    //override dialog's title function to allow for HTML titles
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title =
                this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));

});
function publishPost(obj){
    $.get("/post/lost/post/publishPost",function(messageHtml){
        bootbox.dialog({
//            title: '<h4 class="modal-title" ></h4>',
            message: messageHtml,
            size: "large"
        });
    });
}

function loadPostList(page) {
    var southWest = map.getBounds().getSouthWest();
    var northEast = map.getBounds().getNorthEast();
    var data= "postLatStart=" + southWest.lat + "&postLatEnd=" + northEast.lat + "&postLngStart=" + southWest.lng + "&postLngEnd=" + northEast.lng + "&page=" + $.trim(page) ;
    var request = {
        url: "/post/lost/post/queryPostMapList",
        data:data,
        loadList:"loadPostList",
        successCallBack:function(resultMap){
            iniMap(resultMap.lostPostMap);
        },
        pUrl: "/post/lost/post/paginator",
        pData: data,
    };
    UTIL.loadListAndPaginator(request);
}

function gotoPage(obj) {
    var gotoPageTextValue = $.trim($("#gotoPageText").val());
    var page = gotoPageTextValue;
    if ("" == gotoPageTextValue || isNaN(gotoPageTextValue) || 0 >= parseInt(gotoPageTextValue)) {
        page = 1
    } else if (parseInt($(obj).attr("#totalPages")) < parseInt(gotoPageTextValue)) {
        page = $(obj).attr("#totalPages");
    }
    var limit = $(obj).attr("limit");
    loadPostList(page, limit);
}

function showMapDetail(obj) {
    $(obj).find(".map_post_detail").show();
}
function hideMapDetail(obj) {
    $(obj).find(".map_post_detail").hide();
}

function switchPickLostDate(obj) {
    var switchIndex = $(obj).attr("switchIndex");
    $("[name^='pickLostDate']").hide();
    var curSwitchObj = $("[name='pickLostDate" + switchIndex + "']");
    curSwitchObj.show();
}

function selectPickLostDate() {
    var pickLostDateRadio = $("input[name='pickLostDateRadio']:checked");
    var curSwitchObj = $("[name='pickLostDate" + pickLostDateRadio.attr("switchIndex") + "']");
    var startTime = curSwitchObj.find("input:eq(0)").val();
    var endTime = curSwitchObj.find("input:eq(1)").val();
    var pickLostTime = "开始:";
    if ("" != startTime) {
        pickLostTime += startTime;
    } else {
        pickLostTime += "不限";
    }
    pickLostTime += ",结束:";
    if ("" != endTime) {
        pickLostTime +=  endTime;
    }else{
        pickLostTime += "不限";
    }
    $("#pickLostRandDateId").val(pickLostTime);
    hideTree();
}

function selectSingleTopic(obj){
    $(obj).closest(".radio_btn_group").find("button").attr("class","btn btn-white btn-yellow btn-sm");
    $(obj).attr("class","btn  btn-primary btn-sm selected");
}

function selectMutilTopic(obj){
    $(obj).closest(".radio_btn_group").find("button").attr("class","btn btn-white btn-yellow btn-sm");
    if("" != $.trim($(obj).find("[name='firstChlidren']").html())){
        $(obj).attr("class","btn  btn-primary btn-sm selected");
    }
}

function mutilTopicSelect (obj){
    $(obj).closest(".btn-group").find("[name='firstChlidren']").html("--"+$(obj).html());
    $(obj).closest(".btn-group").find("button").eq(0).attr("class","btn  btn-primary btn-sm selected");
    $(obj).closest(".btn-group").find("button").eq(0).attr("secondeValue",$(obj).attr("value"));
}