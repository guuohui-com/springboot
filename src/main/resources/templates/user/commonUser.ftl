
<html>
 <#include  "common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "common/nav.ftl">


  <table>
      <thead>普通用户信息表</thead>
      <tr>
          <th>Id</th>
          <th>姓名</th>
          <th>类型</th>
          <th>email</th>
          <th>手机号</th>
          <th>密保问题</th>
          <th>答案</th>
          <th>操作</th>
      </tr>
      <#list userInfoList as list>
      <tr>
          <th>${list.id}</th>
          <th>${list.username}</th>
          <th>${list.password}</th>
          <th>${list.email}</th>
          <th>${list.phone}</th>
          <th>${list.question}</th>
          <th>${list.answer}</th>
          <th><a href="updateCommonUser/${list.id}">修改</a>
          </th>
      </tr>
      </#list>
  </table>


  </body>
</html>
