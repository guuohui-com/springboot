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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>主图片</th>
                            <th>子图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>类目</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfo as productInfo>
                        <tr>
                            <td>${productInfo.id}</td>
                            <td>${productInfo.name}</td>
                            <td><img height="100" width="100" src="http://localhost:8080/uploadpic/${productInfo.mainImage}" alt=""></td>
                            <td><img class="subpic" height="100" width="100" src="http://localhost:8080/uploadpic/${productInfo.subPic[0]}" alt=""></td>
                            <td>${productInfo.price}</td>
                            <td>${productInfo.stock}</td>
                            <td>${productInfo.categoryId}</td>
                            <td>
                             <#if productInfo.status == 1>
                             已上架
                             <#else>
                             未上架
                             </#if>
                            </td>
                            <td>${productInfo.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${productInfo.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td><a href="/business/updateProduct/${productInfo.id}">修改</a></td>
                            <td>
                                <a href="/business/upperShlef/${productInfo.id}">上架</a>
                                <a href="/business/downShlef/${productInfo.id}">下架</a>
                                <a href="/business/deleteproduct/${productInfo.id}">删除</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <a href="/business/insertproduct">插入一条商品</a>
            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if pageInfo.currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/business/selectProductAll/${pageInfo.currentPage - 1}">上一页</a></li>
                    </#if>
                    <#list 1..pageInfo.getTotalPage() as index>
                        <#if pageInfo.currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/business/selectProductAll/${index}">${index}</a></li>
                        </#if>
                    </#list>
                    <#if pageInfo.currentPage gte pageInfo.getTotalPage()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/business/selectProductAll/${currentPage + 1}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    $(function () {
        var i=0;
        setInterval(function () {
            i++
            $(".subpic").attr("src","http://localhost:8014/uploadpic/${productInfo.subPic[i]}");
            if(i=${productInfo.size()}){
                i=0;
            }
        },2000)
    })
</script>
</body>
</html>