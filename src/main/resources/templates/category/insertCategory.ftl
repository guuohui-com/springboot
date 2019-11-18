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
                    <form role="form" method="post" action="/business/user/category/insertcategory">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="name" type="text" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>status</label>
                            <input name="status" type="number" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>所属父id</label>
                            <input name="parentId" type="number" class="form-control" />
                        </div>
                        <#--<div class="form-group">&ndash;&gt;
                            <label>创建时间</label>&ndash;&gt;
                            <input name="createTime" type="datetime-local" class="form-control" />
                        </div>-->
                        <input hidden type="text" name="id">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>