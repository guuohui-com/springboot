<html>

<#include  "common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "common/nav.ftl">
    <h1>欢迎，${username},登录</h1>
    <a href="/business/commonuser">查看普通用户</a>
    <a href="/business/user/category/find">查看category信息</a>
    <a href="/business/selectProductAll/1">查看product信息</a>
    <a href="/business/loginOut">退出登录</a>
</div>
</body>
</html>