/**
 * Created by wubiao on 2016/11/2.
 */
$(function () {
    initWysiwyg();
    $("#photoImageDrop").dropzone({
        url: "/personal/home/aboutMe/uploadImge",
        addRemoveLinks: false,
        maxFiles: 10,
        maxFilesize: 5,
        previewsContainer: false,
        acceptedFiles: ".BMP,.JPG,.JPEG,.PNG,.GIF",
        success: function (v1, reponse) {
            if (reponse && reponse.success) {
                var imgJson = reponse.object;
                var jqImgHtml = $($("#photoImageTemplateDivId").html());
                jqImgHtml.find("img[name='imgSrc']").attr("src", imgJson.root + imgJson.relative);
                jqImgHtml.find(".addIdAttr").attr("id", imgJson.id);
                $("#mePhotoImageId").append(jqImgHtml);
            } else {
                var message = (null != reponse ? reponse.message : "");
                message = ("" == $.trim(message) ? "上传个人形象片失败！" : message );
                alert(message);
            }
        }
    });
});
function aboutMeEditor() {
    $("#aboutMeBtnId").show();
    $("[name='aboutMeRow']").find("[fieldVisible='field-editable']").show();
    $("[name='aboutMeRow']").find("[fieldVisible='field-editdisable']").hide();
}

function aboutMeEditorSave() {
    var aboutMe = $("#aboutMeEditorId").html();
    $.ajax({
        url: "/personal/home/aboutMe/updateAboutMe",
        type: 'POST',
        data: {aboutMe: aboutMe},
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#aboutMeEditorIdText").html(aboutMe);
                    $("#aboutMeBtnId").hide();
                    $("[name='aboutMeRow']").find("[fieldVisible='field-editable']").hide();
                    $("[name='aboutMeRow']").find("[fieldVisible='field-editdisable']").show();
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
}
function addSkillEditor(obj) {
    if ($("#addSkillEditor").is(":hidden")) {
        var val = 100;
        var progressDiv = $("#addSkillSliderId").parent().parent();
        progressDiv.find("[name='progressBar']").attr("style", "width:" + val + "%");
        progressDiv.find("[name='percentage']").text(val + "%");
        $("#addSkillEditor").show();
//            $( "#addSkillSliderId" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
//                var value = parseInt( $( this ).text(), 10 );
        $("#addSkillSliderId").css({width: '100%', 'float': 'left', margin: '15px 0px'}).slider({
            value: val,
            range: "min",
            animate: true,
            min: 50,
            max: 100,
            step: 1,
            slide: function (event, ui) {
                val = parseInt(ui.value);
                var progressDiv = $("#addSkillSliderId").parent().parent();
                progressDiv.find("[name='skillNum']").val(val);
                progressDiv.find("[name='progressBar']").attr("style", "width:" + val + "%");
                progressDiv.find("[name='percentage']").text(val + "%");
            }
        });
//            });
    }
}
function addSkillBtn(obj) {
    var formJson = $("#addSkillEditor").find(".form-field").serializeJSON();
    $.ajax({
        url: "/personal/home/aboutMe/addSkill",
        type: 'POST',
        data: formJson,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#addSkillEditor").hide();
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "设置技能失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}
function skillEditor(obj) {
    $("#skillEditorSaveBtnId").show();
    $.each($("#skillPersonalDiv [name='skillEditorSlider']"), function () {
        $(this).show();
        var progressRow = $(this).parent();
        progressRow.find("[name='skillDelete']").show();
        var progressBarObj = progressRow.find("[name='progressBar']");
        var percentageObj = progressRow.find("[name='percentage']");
        // read initial values from markup and remove that
        $(this).css({width: '100%', 'float': 'left', 'margin-bottom': '20px'}).slider({
            value: parseInt(percentageObj.attr("percentage")),
            range: "min",
            animate: true,
            min: 50,
            max: 100,
            step: 1,
            slide: function (event, ui) {
                var val = parseInt(ui.value);
                progressRow.find("[name='skillNum']").val(val);
                progressRow.find("[name='userSkills.skillNum']").val(val);
                progressBarObj.attr("style", "width:" + val + "%");
                percentageObj.text(val + "%");
                percentageObj.attr('percentage', val);
            }
        });
    });
}
function initWysiwyg() {
    $('#aboutMeEditorId').ace_wysiwyg({
        toolbar: [
            {name: 'bold', className: 'btn-info'},
            {name: 'italic', className: 'btn-info'},
            {name: 'strikethrough', className: 'btn-info'},
            {name: 'underline', className: 'btn-info'},
            null,
            {name: 'insertunorderedlist', className: 'btn-success'},
            {name: 'insertorderedlist', className: 'btn-success'},
            {name: 'outdent', className: 'btn-purple'},
            {name: 'indent', className: 'btn-purple'},
            null,
            'foreColor'
        ],
        'wysiwyg': {
            fileUploadError: showErrorAlert
        }
    }).prev().addClass('wysiwyg-style2');
}
function showErrorAlert(reason, detail) {
    var msg = '';
    if (reason === 'unsupported-file-type') {
        msg = "Unsupported format " + detail;
    }
    else {
        //console.log("error uploading file", reason, detail);
    }
    $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>' +
        '<strong>File upload error</strong> ' + msg + ' </div>').prependTo('#alerts');
}
function skillEditorSave() {
    var formJson = $("#skillPersonalDiv").find(".form-field").serializeJSON();
    formJson = $.jsonToParam(formJson, true);
    console.info(formJson);
    $.ajax({
        url: "/personal/home/aboutMe/updateSkill",
        type: 'POST',
        data: formJson,
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $("#skillEditorSaveBtnId").hide();
                    $("#skillPersonalDiv [name='skillEditorSlider']").hide();
                    $("#skillPersonalDiv [name='skillDelete']").hide();
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "修改技能失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}


function deletePhotoImage(obj) {
    $.ajax({
        url: "/personal/home/aboutMe/deletePersonalPhoto",
        type: 'POST',
        data: "id=" + $(obj).attr("id"),
        dataType: "json",
        success: function (result) {
            //信息
            var message = "";
            if (result) {
                message = result.message;
                if (result.success) {
                    $(obj).closest("li").remove();
                    return false;
                }
            }
            message = ("" == $.trim(message) ? "删除个人形象片失败！" : message );
            alert(message);
        },
        error: function (request, ajaxOptions, thrownError) {
            alert("系统或网络异常！");
        }
    });
}