/**
 * Created by wubiao on 2016/9/18.
 */
function mapPostDetailSc(obj) {
    $('.map_post_detail_sc').ace_scroll({
        size: 250,
        'observeContent': true,
        'show': true,
        'alwaysVisible': true,
        'hideOnIdle': false,
        'mouseWheelLock': true,
        'hideOnIdle': true,
        'hideDelay': 0,
        'hoverReset': true,
        'reset': true, //true= set scrollTop = 0
        'lockAnyway': true
    });
}

function initColorbox(colorbox, selector) {
    var $overflow = '';
    var colorbox_params = {
        rel: colorbox,
        reposition: true,
        scalePhotos: true,
        scrolling: false,
        previous: '<i class="ace-icon fa fa-arrow-left"></i>',
        next: '<i class="ace-icon fa fa-arrow-right"></i>',
        close: '&times;',
        current: '当前第{current} 页，共{total}页',
        maxWidth: '100%',
        maxHeight: '100%',
        onOpen: function () {
            $overflow
                = document.body.style.overflow;
            document.body.style.overflow = 'hidden';
        },
        onClosed: function () {
            document.body.style.overflow = $overflow;
        },
        onComplete: function () {
            $.colorbox.resize();
        }
    };
    $(selector).colorbox(colorbox_params);
    $().colorbox(colorbox_params);
    $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
}
function iniMap(mapNameHtmls) {
    setTimeout(function () {
        map.clearOverlays();
        var myCompOverlay = "";
        var txt = "";
        if (mapNameHtmls && mapNameHtmls.length > 0) {
            for (var i = 0; i < mapNameHtmls.length; i++) {
                myCompOverlay = new ComplexCustomOverlay(mapNameHtmls[i]);
                map.addOverlay(myCompOverlay);
            }
        }
        setTimeout(function () {
            mapPostDetailSc();
            $.each($('.ace-thumbnails[data-rel-name^="map_colorbox_"]'), function () {
                var colorbox = $(this).attr("data-rel-name");
                initColorbox(colorbox, '.ace-thumbnails [data-rel="' + colorbox + '"]');
            });
        }, 1)
    }, 1)
}

/**
 * 点赞
 * @param url
 */
function upIncrement(obj,url){
    $(obj).attr("disabled",true);
    $.ajax({
        url: url,
        type: 'get',
        dataType: "json",
        success: function (result) {
            $(obj).removeAttr("disabled");
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).find("[name='num']").html(parseInt($(obj).find("[name='num']").html()) +  1);
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "赞贴失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            $(obj).removeAttr("disabled");
            alert("系统或网络异常！");
        }
    });
}
/**
 * 踩贴
 * @param url
 */
function downIncrement(obj,url){
    $(obj).attr("disabled",true);
    $.ajax({
        url: url,
        type: 'get',
        dataType: "json",
        success: function (result) {
            $(obj).removeAttr("disabled");
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).find("[name='num']").html(parseInt($(obj).find("[name='num']").html()) +  1);
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "踩贴失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            $(obj).removeAttr("disabled");
            alert("系统或网络异常！");
        }
    });
}
/**
 * 爱心
 * @param url
 */
function loveIncrement(obj,url){
    $(obj).attr("disabled",true);
    $.ajax({
        url: url,
        type: 'get',
        dataType: "json",
        success: function (result) {
            $(obj).removeAttr("disabled");
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).find("[name='num']").html(parseInt($(obj).find("[name='num']").html()) +  1);
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "爱心失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            $(obj).removeAttr("disabled");
            alert("系统或网络异常！");
        }
    });
}

