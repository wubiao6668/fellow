/**
 * Created by wubiao on 2016/9/18.
 */


function ComplexCustomOverlay(post) {
    this._point = new BMap.Point(post.postLng, post.postLat);
    this._post = post;
}
ComplexCustomOverlay.prototype = new BMap.Overlay();
ComplexCustomOverlay.prototype.initialize = function (map) {
    var post = this._post;
    this._map = map;
    var div = this._div = document.createElement("div");
    div.style.position = "absolute";
//        div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
    div.style.background = "#f7f7f7 linear-gradient(to bottom, #ffffff 0%, #eeeeee 100%) repeat-x scroll 0 0";
    div.style.border = "1px solid #BC3B3A";
    div.style.color = "#669fc7";
    div.style.height = "28px";
    div.style.padding = "0px 2px 2px 2px";
    div.style.minHeight = "28px";
    div.style.lineHeight = "22px";
    div.style.whiteSpace = "nowrap";
    div.style.MozUserSelect = "none";
    div.style.fontSize = "12px";
    var jqMapPost = $('<div  style="z-index:9999;padding: 6px 10px;display: block" onmouseover="showMapDetail(this)" onmouseout="hideMapDetail(this)"></div>');

//        jqMapPost.append($("#popover544484").html());
    var jqContent = $('<div class="popover-content map_post_detail_sc"></div>');

    $.each($("div[maphtmlname='" + post.mapHtmlName + "']"), function () {
        var jqProfile = $('<div class="profile-activity clearfix"></div>');
        var jqPostDetailHtml = $($(this).html());
        var colorboxName = "map_" + jqPostDetailHtml.find('.ace-thumbnails[data-rel-name^="colorbox_"]').attr("data-rel-name");
        jqPostDetailHtml.find('.ace-thumbnails[data-rel-name^="colorbox_"]').attr("data-rel-name", colorboxName);
        jqPostDetailHtml.find('a.cboxElement').attr("data-rel", colorboxName);
        jqProfile.append(jqPostDetailHtml);
        jqContent.append(jqProfile);
//            console.info($(this).html());
    });

    var jqPopover = $('<div role="tooltip" class="popover fade top in map_post_detail" style="position: absolute;top: -83px;width: 575px;max-width: 600px;z-index: 999999;">').append(jqContent);
    jqMapPost.append(jqPopover);
    var imgStr;
    var titleStr = '<span>' + post.title + '</span>';
    var nameStr = '<a href="#">' + post["postUsername"] + '</a>';
    var postTimeStr = '<span class="time"><i class="ace-icon fa fa-clock-o bigger-110"></i>' + post["postTimeString"] + '</span>';
    if (post.postCount <= 1) {
        imgStr = '<img  src="' + $.trim(post.postUser.avatar) + '" alt="' + post.postUsername + '" style="border: 2px solid #5293c4;border-radius: 100%;max-width: 22px;">';
    } else {
        imgStr = '<span class="badge badge-info">' + post.postCount + '</span>';
        titleStr += "…";
        nameStr += "…";
        postTimeStr += "…";
    }
    jqMapPost.append(imgStr);
    jqMapPost.append(titleStr);
    jqMapPost.append(nameStr);
    jqMapPost.append(postTimeStr);
//        console.info(jqMapPost[0]);
    div.appendChild(jqMapPost[0]);
    var that = this;
    var arrow = this._arrow = document.createElement("div");
    arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
    arrow.style.position = "absolute";
    arrow.style.width = "11px";
    arrow.style.height = "10px";
    arrow.style.top = "26px";
    arrow.style.left = "10px";
    arrow.style.overflow = "hidden";
    div.appendChild(arrow);

//        div.onmouseover = function(){
//            this.style.backgroundColor = "#6BADCA";
//            this.style.borderColor = "#0000ff";
//            this.getElementsByTagName("span")[0].innerHTML = that._overText;
//            arrow.style.backgroundPosition = "0px -20px";
//        }

//        div.onmouseout = function(){
//            this.style.backgroundColor = "#EE5D5B";
//            this.style.borderColor = "#BC3B3A";
//            this.getElementsByTagName("span")[0].innerHTML = that._text;
//            arrow.style.backgroundPosition = "0px 0px";
//        }

    map.getPanes().labelPane.appendChild(div);

    return div;
}
ComplexCustomOverlay.prototype.draw = function () {
    var map = this._map;
    var pixel = map.pointToOverlayPixel(this._point);
    this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
    this._div.style.top = pixel.y - 30 + "px";
}