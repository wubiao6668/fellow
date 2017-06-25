/**
 * Created by wubiao on 2016/10/19.
 */
$(function () {
    //baseInfoEditorSave();
    $('.date-picker').datepicker({
        autoclose: true,
        todayHighlight: true
    })
});

function showErrorAlert (reason, detail) {
    var msg='';
    if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
    else {
        //console.log("error uploading file", reason, detail);
    }
    $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+
        '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
}
function baseInfoEditor() {
    $("#baseInfoBtnId").show();
    $("[name='baseInfoRow']").find("[fieldVisible='field-editable']").show();
    $("[name='baseInfoRow']").find("[fieldVisible='field-editdisable']").hide();
}

function baseInfoEditorSave() {
    $.ajax({
        url: "/personal/home/baseInfo/updateBaseInfo",
        type: 'POST',
        data: $("#baseInfoFrom").serializeJSON(),
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#baseInfoBtnId").hide();
                    $("[name='baseInfoRow']").find("[fieldVisible='field-editable']").hide();
                    $("[name='baseInfoRow']").find("[fieldVisible='field-editdisable']").show();
                    $("#usernameText").text($("#username").val());
                    $("#sexText").text($("#sex option:selected").text());
                    $("#educationText").text($("#education option:selected").text());
                    if ("0" != $("#profession").val()) {
                        $("#professionText").text($("#profession option:selected").text());
                    }
                    $("#relationshipText").text($("#relationshipStatus option:selected").text());

                    $("#domicileText").text($("#domicileFirst option:selected").text() + "," + $("#domicileSecond option:selected").text());
                    var hometownText = ""
                    if ("" != $("#hometownFirst").val()) {
                        hometownText += $("#hometownFirst option:selected").text();
                        if ("" != $("#hometownSecond").val()) {
                            hometownText += $("#hometownSecond option:selected").text();
                        }
                    }
                    $("#hometownText").text(hometownText);
                    $("#birthdayText").text($("#birthday").val());
                    $("#heightText").text($("#height option:selected").text());
                    $("#weightText").text($("#weight").val());
                    $("#workUnitText").text($("#workUnit").val());
                    $("#workUnitText").text($("#workUnit").val());
                    $("#monthlyIncomeText").text($("#monthlyIncome option:selected").text());
                    $("#majorTextSpanText").text($("#majorText").val());
                    $("#collegeSchoolIdSpanText").text($("#collegeSchoolIdText").val());
                    $("#collegeYearText").text($("#collegeYear").val());
                    return false;
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

function selectCollegeSchoolDialog(obj){
    var url = "/selectSchool";
    $.get(url, function (data) {
        bootbox.dialog({
            title: '<div id="classifyWin"><span class="glyphicon glyphicon-pencil "></span>选择学校</div>',
            message: data,
            //size:"small"
            size: "large",
            buttons: {
                Cancel: {
                    label: "取消",
                    className: "btn btn-gray",
                    callback: function () {
                    }
                }
            }
        });
    });
}
function majorDialog(obj){
    var url = "/selectMajor";
    $.get(url, function (data) {
        bootbox.dialog({
            title: '<div id="classifyWin"><span class="glyphicon glyphicon-pencil "></span>选择专业</div>',
            message: data,
            //size:"small"
            size: "large",
            buttons: {
                Cancel: {
                    label: "取消",
                    className: "btn btn-gray",
                    callback: function () {
                    }
                }
            }
        });
    });
}

function selectCollegeSchool(obj){
    $("#collegeSchoolIdText").val($(obj).text());
    $("#collegeSchoolId").val($(obj).attr("schoolId"));
    $("#collegeSchoolFirst").val($(obj).attr("schoolFirst"));
    bootbox.hideAll();
}
function selectMajor(obj){
    $("#majorText").val($(obj).text());
    $("#majorId").val($(obj).attr("majorId"));
    $("#majorFirst").val($(obj).attr("majorFirst"));
    bootbox.hideAll();
}