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
                    <form role="form" method="post" action="${product.id}" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>产品名称</label>
                            <input name="name" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="price" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="stock" type="number" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>类别id</label>
                            <input name="categoryId" type="number" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>status</label>
                            <input name="status" type="text" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <#--<input id="productIcon" name="mainImage" type="file"/>-->
                            <div class="file-loading">
                                <input id ="input-id" name ="mainImage"type ="file" class ="file" multiple data-show-upload = "false"
                                       data-show-caption = "true" data-msg-placeholder = "选择{files}进行上传..." >
                                <#--<input id="input-id" name="mainImage" type="file" ">-->
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>

                       <#-- <div class="form-group">
                            <label>子图片</label>
                            <input id="productIcon" name="subImage" type="file"/>
                            <div class="file-loading">
                                <input id="input-id" type="file">
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>-->

                        <#--<div class="form-group">
                            <label>图片</label>
                            <input id="mainImage" name="productIcon" type="text" hidden="hidden" value="${(product.mainImage)!''}"/>
                            <div class="file-loading">
                                <input id="input-id" type="file">
                                <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                            </div>
                        </div>-->

                       <#-- <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>-->
                        <input hidden type="text" name="id">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="">插入一条商品</a>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>
<script>

    $(function () {
        var initialPreview = [];
        if ('${(productInfo.mainImage)!""}' != '') {
            initialPreview = "<img class='kv-preview-data file-preview-image' src='${(productInfo.mainImage)!""}'>"
        }

        $("#input-id").fileinput({
            //uploadUrl: '/sell/image/upload',
            language: 'zh',
            browseClass: "btn btn-primary btn-block",
            showCaption: true,
            showRemove: true,
            showUpload: true,
            allowedFileExtensions: [ 'jpg', 'jpeg', 'png', 'gif' ],
            maxFileSize: 102400,
            autoReplace: true,
            overwriteInitial: true,
            maxFileCount: 10,
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