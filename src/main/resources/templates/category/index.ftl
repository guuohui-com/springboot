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
                    <form role="form" method="post" action="${category.id}">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="name" type="text" class="form-control" value="${(category.name)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>status</label>
                            <input name="status" type="number" class="form-control" value="${(category.status)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>所属父id</label>
                            <input name="parentId" type="number" class="form-control" value="${(category.parentId)!''}"/>
                        </div>
                        <#--<div class="form-group">-->
                            <#--<label>更新时间</label>-->
                            <#--<input name="updateTime" type="text" class="form-control" value="${category.updateTime?string('yyyy-MM-dd HH:mm:ss')}"/>-->
                        <#--</div>-->
                        <input hidden type="text" name="id" value="${(category.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>