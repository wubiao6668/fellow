/**
 * Created by wubiao on 2016/8/17.
 */
var UTIL = $.extend({}, UTIL);
UTIL.UUID = function () {
    return new Date().getTime() + "" + Math.round(Math.random(5) * 10000);
}
var defaultAjax = {
    $btnObj: false,
    maskShow: {},
    maskHide: {},
    businessName: "",
    successModal: true,
    successTrueContent: "成功!",
    successTrueTitle: "提示",
    successFalseTitle: "提示",
    successFalseContent: "失败!系统异常!",
    successCallback: function (var1) {
    },
    errorModal: true,
    errorTitle: "提示",
    errorContent: "失败!系统或网络异常!",
    type: "GET",
    cache: false,
    url: "",
    data: "",
    dataType: 'json',
    beforeSend: function () {
        if (this.$btnObj) {
            this.$btnObj.attr("disabled", true);
        }
        UTIL.maskShow(this.maskShow);
    },
    complete: function () {
        if ("" != $.trim(this.$btnObj)) {
            $(this.$btnObj).removeAttr("disabled");
        }
        UTIL.maskHide(this.maskHide);
    },
    success: function (response) {
        var alertOption = {title: this.successFalseTitle, type: "danger"};
        //信息
        var message = "";
        if (response) {
            message = response.message;
            if (response.state) {
                alertOption = {title: this.successTrueTitle, type: "success"};
                message = ("" == $.trim(message) ? this.businessName + this.successTrueContent : message );
                if (this.successCallback && this.successCallback instanceof Function) {
                    this.successCallback(response);
                }
                if (this.successModal) {
                    return;
                }
            }
        }
        message = ("" == $.trim(message) ? +this.businessName + this.successFalseContent : message );
        alertOption.content = message;
        UTIL.jqmAlert(alertOption);
    }, error: function (er1, er2, er3) {
        //debugger;
        if (this.errorModal) {
            UTIL.jqmAlert({
                title: this.errorTitle,
                type: "danger",
                content: this.businessName + this.errorContent
            });
        }
        return false;
    }
};
UTIL.ajax = function (option) {
    option = $.extend(defaultAjax, option);
    return $.ajax(option);
};
UTIL.ajaxHtml = function (option) {
    option = $.extend(defaultAjax, {
        dataType: 'html',
        success: function (html) {
            if (this.successCallback && this.successCallback instanceof Function) {
                this.successCallback(html);
            }
        }
    }, option);
    return $.ajax(option);
}
UTIL.jqmAlert = function (option) {
    if (!option) {
        option = {};
    }
    option = $.extend({
        modal: true,
        content: '操作成功',
        min_width: '200px;',
        height: 'auto;',
        width: 'auto;',
        title: '标题',
        type: 'success',
        typeClass: {
            success: 'alert-success',
            danger: 'alert-danger',
            warning: 'alert-warning',
            info: 'alert-info',
            empty: ''
        },
        buttons: [],
        buttonStr: ""
    }, option);
    var uuid = UTIL.UUID();
    var dialogId = "dialog" + uuid;
    var jqmInfoId = "jqmInfo" + uuid;
    var alertHmlt = "<div class=\"jqmWindow\" id=\"" + dialogId + "\">\n" +
        "                        <div id=\"" + jqmInfoId + "\" class=\"widget-box\" style=\"min-width: " + option.min_width + option.height + option.width + " \">\n" +
        "                            <div class=\"widget-header\">\n" +
        "                                <h5 class=\"widget-title smaller\">" + option.title + "</h5>\n" +
        "\n" +
        "                                <div class=\"widget-toolbar\">\n" +
        "                                    <a  dialogId=\"" + dialogId + "\" onclick='UTIL.jqmRemove(this)' class=\"btn btn-link\">关闭</a>" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"widget-body " + option.typeClass[option.type] + "\">\n" +
        "                                <div class=\"widget-main\">\n" +
        "                                    <p class=\"\">" + option.content + "</p>\n" +
        "                                </div><div class=\"widget-toolbox padding-8 clearfix text-center\">";
    if (option.buttons && option.buttons.length > 0) {
        for (var i = 0; i < option.buttons.length; i++) {
            var buttonTemp = option.buttons[i];
            buttonTemp.id = "btn_" + UTIL.UUID();
            alertHmlt += "                                   &nbsp;&nbsp; <button id='" + buttonTemp.id + "' class=\"" + $.trim(buttonTemp['class']) + "\">\n" +
                "                                        <span class=\"bigger-110\">" + $.trim(buttonTemp.label) + "</span>\n" +
                "                                        <i class=\"" + $.trim(buttonTemp.icon) + "\"></i>\n" +
                "                                    </button> &nbsp;&nbsp;";
        }
    }
    alertHmlt += option.buttonStr + "</div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

    $(alertHmlt).appendTo("body");

    $('#' + dialogId).jqm(option)
    $('#' + dialogId).jqmShow(); // show modal

    if (option.buttons && option.buttons.length > 0) {
        for (var i = 0; i < option.buttons.length; i++) {
            var buttonTemp = option.buttons[i];
            $("#" + buttonTemp.id).click(function () {
                if (buttonTemp.callback && buttonTemp.callback instanceof Function) {
                    var disFlag = buttonTemp.callback();
                    if (false != disFlag) {
                        $("#" + dialogId).jqmHide().remove();
                    }
                }
            });
        }
    }

    $('#' + jqmInfoId).css({
        position: 'fixed',
        left: ($(window).width() - $('#' + jqmInfoId).outerWidth()) / 2,
        top: '15%'
    });
};
UTIL.jqmInfo = function (content) {
    var option = {
        content: content,
        type: 'alert-info',
        buttons: [{
            class: "btn btn-xs btn-info",
            label: "关闭",
            icon: "ace-icon glyphicon glyphicon-ok"
        }]
    }
    UTIL.jqmAlert(option);
}

UTIL.jqmConfirm = function (title, content, callback) {
    var option = {
        title: title,
        content: content,
        type: 'alert-warning',
        buttons: [{
            class: "btn btn-xs btn-danger",
            label: "取消",
            icon: "ace-icon fa fa-times"
        }, {
            class: "btn btn-xs btn-info",
            label: "确认",
            icon: "ace-icon glyphicon glyphicon-ok",
            callback: callback
        }]
    }
    UTIL.jqmAlert(option);
}
UTIL.jqmAjax = function () {

}
UTIL.jqmFun = function (obj) {
    var fn = eval($(obj).attr("method"));
    if (false == fn) {
        return;
    }
    UTIL.jqmRemove(obj);
}
UTIL.jqmRemove = function (obj) {
    $("#" + $(obj).attr("dialogId")).jqmHide().remove();
}
UTIL.jqmFunEmpty = function () {
    return true;
}

/**
 * 菜单
 * @param obj
 * @param rootDiv
 */
UTIL.appMenu = function (obj, rootDiv) {
    $("#app_menu").find(".active").removeClass("active");
    $(obj).parent().find(".open").removeClass("open");
    $(obj).parent().addClass("active");
    $("#" + rootDiv).addClass("active");
    var menuNames = new Array();
    menuNames.push($.trim($(obj).text()));
    var max_menu_level = 0;
    var openClosest = obj;
    while (max_menu_level < 6) {
        var openClosest = $(openClosest).parent().closest(".open");
        var menuName = $.trim($(openClosest).find("a").eq(0).text());
        if ("" == menuName) {
            max_menu_level = 12;
        } else {
            menuNames.push(menuName);
            max_menu_level++;
        }
    }
    menuNames.reverse();
    UTIL.setBreadcrumb(menuNames);
    UTIL.ajaxHtml({
        businessName: "加载" + menuNames[menuNames.length - 1] + "页面",
        successModal: false,
        url: $(obj).attr("data-url"),
        dataType: "html",
        successCallback: function (body) {
            $("#app-page-content-area").html(body);
        }
    });
}

/**
 * 显示遮罩
 * @param option
 */
UTIL.maskShow = function (option) {
    if (!option) {
        option = {};
    }
    option = $.extend({
        icon: "fa fa-spin fa-spinner fa-3x orange",
        content: "加载中...",
        selector: "#app-page-content-area"
    }, option);
    UTIL.maskHide(option);
    var $overlay = $('<div style="z-index: 99999" class="ajax-loading-overlay appMask"><i class="ajax-loading-icon ' + option.icon + '"></i><i class="fa-2x orange" style="left: 18px; position: relative; top: 8px;">' + option.content + '</i></div>')
    $(option.selector).append($overlay);
}

/**
 * 隐藏遮罩
 * @param hideId
 */
UTIL.maskHide = function (option) {
    if (!option) {
        option = {};
    }
    option = $.extend({selector: "*"}, option);
    $(option.selector).find(".appMask").remove();
}

/**
 * 设置面包屑
 * @param breads
 */
UTIL.setBreadcrumb = function (breads) {
    var breadHtml = "";
    if (breads) {
        for (var i = 0; i < breads.length; i++) {
            if (0 == i) {
                breadHtml += '<li class="active"> <i class="ace-icon fa fa-home home-icon"></i><a href="#">' + breads[i] + '</a></li>';
            } else if (breads.length - 1 == i) {
                breadHtml += '<li class="active">' + breads[i] + '</li>';
            } else {
                breadHtml += '<li><a href="#">' + breads[i] + '</a></li>';
            }
        }
    }
    $("#app_breadcrumb").html(breadHtml);
}

/**
 * 加载列表及分页
 * @param option
 */
UTIL.loadListAndPaginator = function(option){
    if (!option) {
        option = {};
    }
    option = $.extend({
        paginSelector: "div[name='paginator']",
        //paginUlSelector:"[name='paginationUl']",
        maskSelector: "#mask_row_id",
        listHmltSelector: "#listHtmlId",
        page: 1,
        url: "",
        type: "POST",
        data: "",
        dataType: "json",
        loadList: "loadList",
        successCallBack: function () {

        },
        pUrl: "",
        pType: "POST",
        pData: "",
        pDataType: "json",
        pLoadList: "loadList",
        pSuccessCallBack: function () {

        }
    }, option);
    $(option.paginSelector).attr("index", "paginator_" + option.page);
    $(option.paginSelector + "  [name='paginationUl']").find("li:gt(1)").remove();
    UTIL.maskHide({selector: option.maskSelector});
    $.ajax({
        url: option.url,
        type: option.type,
        beforeSend: function () {
            UTIL.maskShow({selector: option.maskSelector});
        },
        complete: function () {
            UTIL.maskHide({selector: option.maskSelector});
        },
        data: option.data,
        dataType: option.dataType,
        success: function (result) {
            var message = "";
            if (result) {
                message = result.messge;
                if (result.success) {
                    $(option.listHmltSelector).html(result.body);
                    var resultMap = result.object;
                    if (resultMap) {
                        if ($(option.paginUlSelector).find("li:gt(1)").length <= 0) {
                            if (resultMap.isHadPre) {
                                $(option.listHmltSelector).find("[name='pagePre']").parent().removeClass("disabled");
                                $(option.listHmltSelector).find("[name='pagePre']").attr("onclick", option.loadList + "(" + (parseInt(option.page) - 1) + ")");
                            } else {
                                $(option.listHmltSelector).find("[name='pagePre']").parent().addClass("disabled");
                                $(option.listHmltSelector).find("[name='pagePre']").removeAttr("onclick");
                            }
                            if (resultMap.isHadNext) {
                                $(option.listHmltSelector).find("[name='pageNext']").parent().removeClass("disabled");
                                $(option.listHmltSelector).find("[name='pageNext']").attr("onclick", option.loadList + "(" + (parseInt(option.page )+ 1) + ")");
                            } else {
                                $(option.listHmltSelector).find("[name='pageNext']").parent().addClass("disabled");
                                $(option.listHmltSelector).find("[name='pageNext']").removeAttr("onclick");
                            }
                        }
                    }
                    if (option.successCallBack && option.successCallBack instanceof Function) {
                        option.successCallBack(resultMap);
                    }
                    return;
                }
            }
            message = "" == $.trim(message) ? "查询失败！" : message;
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
    $.ajax({
        url: option.pUrl,
        type: option.pType,
        data: option.pData,
        dataType: option.pDataType,
        success: function (result) {
            //var message = "";
            if (result) {
                //message = result.messge;
                if (result.success) {
                    $(option.paginSelector).html(result.body);
                    return;
                }
                if (option.pSuccessCallBack && option.pSuccessCallBack instanceof Function) {
                    option.pSuccessCallBack();
                }
            }
            //message = "" == $.trim(message)?"查询失败！":message;
            //alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            //alert("系统或网络异常！");
        }
    });
}


/**
 * 坐标转地址
 */
UTIL.geoPointToText=function(){
    $.each($("[name='geoPointToText']"),function(){
        var spanObj = this;
        setTimeout(function(){
            var myGeo = new BMap.Geocoder();
            myGeo.getLocation( new BMap.Point($(spanObj).attr("postLng"),$(spanObj).attr("postLat")), function(rs){
                var addComp = rs.addressComponents;
                $(spanObj).html(addComp.province  + addComp.city +  addComp.district + addComp.street + addComp.streetNumber);
            });
        },400);
    });
}

UTIL.isEmail = function(str){
    var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
    return reg.test(str);
}