<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
</head>
<body>
<table>
<tr>
<td>名称</td>
<td>操作</td>
</tr>
<tr>
<td>添加用户</td>
<td><a   href="<%=contextPath%>/api/adduser/AddTestUser/addUserAct.act">添加用户</a></td>
</tr>
<tr>
<td>添加流程 </td>
<td><a  href="<%=contextPath%>/api/addworkflow/AddWorkFlow/addWorkFlow.act">添加流程</a></td>
</tr>
<tr>
<td>添加邮件 </td>
<td><a  href="<%=contextPath%>/api/adduser/AddEmail/AddEmailAct.act">添加邮件</a></td>
</tr>
<tr>
<td>添加新闻 </td>
<td><a  href="<%=contextPath%>/api/adduser/addNews/addNewsAct.act">添加新闻</a></td>
</tr>
<tr>
<td>添加日程 </td>
<td><a  href="<%=contextPath%>/api/adduser/AddCalendar/addCalendarAct.act">添加日程</a></td>
</tr>
<tr>
<td>添加公告 </td>
<td><a  href="<%=contextPath%>/api/adduser/addNotice/addNoticeAct.act">添加公告 </a></td>
</tr>
<tr>
<td>添加日志 </td>
<td><a  href="<%=contextPath%>/api/adduser/addDiary/addDiaryAct.act">添加邮件</a></td>
</tr>
<tr>
<td>添加考勤 </td>
<td><a  href="<%=contextPath%>/api/adduser/AddAttend/addAttendAct.act">添加考勤</a></td>
</tr>
>>>>>>> .r2056
</table>
</body>
</html>