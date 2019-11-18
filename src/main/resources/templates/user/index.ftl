<html>
<#include "common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="${userInfo.id}">
                        <div class="form-group">
                            <label>姓名</label>
                            <input name="username" type="text" class="form-control" value="${(userInfo.username)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>密码</label>
                            <input name="password" type="text" class="form-control" value="${(userInfo.password)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>email</label>
                            <input name="email" type="text" class="form-control" value="${(userInfo.email)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>手机号</label>
                            <input name="phone" type="text" class="form-control" value="${(userInfo.phone)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>密保问题</label>
                            <input name="question" type="text" class="form-control" value="${(userInfo.question)!''}"/>
                        </div>
                        <div class="form-group">
                        <label>答案</label>
                        <input name="answer" type="text" class="form-control" value="${(userInfo.answer)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <input name="role" type="text" class="form-control" value="${(userInfo.role)!''}"/>
                        </div>
                        <input hidden type="text" name="productId" value="${(userInfo.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>
<script>

    $(function () {
        var initialPreview = [];
        if ('${(productInfo.productIcon)!""}' != '') {
            initialPreview = "<img class='kv-preview-data file-preview-image' src='${(productInfo.productIcon)!""}'>"
        }

        $("#input-id").fileinput({
            uploadUrl: '/sell/image/upload',
            language: 'zh',
            browseClass: "btn btn-primary btn-block",
            showCaption: false,
            showRemove: false,
            showUpload: false,
            allowedFileExtensions: [ 'jpg', 'jpeg', 'png', 'gif' ],
            maxFileSize: 1024,
            autoReplace: true,
            overwriteInitial: true,
            maxFileCount: 1,
            initialPreview: initialPreview,
        });
    });
    //上传完成设置表单内容
    $('#input-id').on('fileuploaded', function(event, data, previewId, index) {
        if (data.response.code != 0) {
            alert(data.response.msg)
            return
        }
        $('#productIcon').val(data.response.data.fileName)
    });
</script>
</body>
</html>