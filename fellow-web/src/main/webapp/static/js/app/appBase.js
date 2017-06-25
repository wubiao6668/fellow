/**
 * Created by wubiao on 2016/8/12.
 */
$(function () {
    /**
     * 序列化参数
     * @returns {{}}
     */
    $.fn.serializeJSON = function () {
        var serializeObj = {};
        $.each(this.serializeArray(), function (index) {
            if (serializeObj[this['name']]) {
                serializeObj[this['name']] = serializeObj[this['name']] + "," + this['value'];
            } else {
                serializeObj[this['name']] = this['value'];
            }
        });
        return serializeObj;
    };

    /**
     * 设置表单包含name属性的标签，如果为input设置value 其它设置html
     * @param json
     */
    $.fn.loadFormField = function(json){
        $.each($(this.selector).find("[name]"),function(){
            if($(this).is("input")){
                $(this).val($.trim(json[$(this).attr("name")]));
            }else{
                $(this).html($.trim(json[$(this).attr("name")]));
            }
        });
    }
    /**
     * json传转成 提交参数
     * @param json
     * @param isArray 后台是否为数据接收
     * @returns {string}
     */
    $.jsonToParam = function (json, isArray) {
        var param = "";
        for (var key in json) {
            var keySplit = key.split(".");
            if (keySplit.length > 1) {
                if (isArray) {
                    var values = json[key].split(",");
                    for (var i = 0; i < values.length; i++) {
                        param = param + ("&" + keySplit[0] + "[" + i + "]." + keySplit[1] + "=" + values[i] );
                    }
                } else {
                    param = param + "&" + key + "=" + json[key];
                }
            } else {
                param = param + "&" + key + "=" + json[key];
            }
        }
        return param;
    }
});





/**
 * 点击选择按钮显示架构树
 */
function showUserOrgTree(panelId) {
    $("#" + panelId).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}
/**
 * 隐藏树及绑定树隐藏事件
 */
function hideTree() {
    $("[name='downPanel']").fadeOut("fast");
    $(".body").unbind("mousedown", onBodyDown);
}
/**
 * 隐藏树
 * @param event
 */
function onBodyDown(event) {
    if (!(event.target.name == "downPanel" || event.target.name == "downPanel" || $(event.target).parents("[name='downPanel']").length > 0)) {
        hideTree();
    }
}