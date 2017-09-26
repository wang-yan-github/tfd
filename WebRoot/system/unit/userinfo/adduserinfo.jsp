<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String deptId = request.getParameter("deptId");
%>
<html>
<head>
<title>新建人员信息</title>
</head>
<body style="margin-top:0;">
<table class="TableTop" width="100%">
<tr>
      <td class="left"></td>
      <td class="center subject">新建人员信息</td>
      <td class="right"></td>
   </tr>
</table>
<table class="TableBlock no-top-border" style="width: 100%;">
<tr>
<td class="TableData">姓名：</td>
<td class="TableData"><input type="text" id="name" name="name"></td>
</tr>
<tr>
<td class="TableData">性别：</td>
<td class="TableData"><input type="text" id="sex" name="sex"></td>
</tr>
<tr>
<td class="TableData">关联账号：</td>
<td class="TableData"><input type="text" id="linkAccountId" name="linkAccountId"></td>
</tr>
<tr>
<td class="TableData"></td>
<td class="TableData"></td>
</tr>
<tr>
<td class="TableData"></td>
<td class="TableData"></td>
</tr>
</table>
</body>
</html>