<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>待办工作</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/worklist/js/doingwork.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
</head>
<body onload="doinit();">
<table class="table table-striped  table-condensed" >
<tr>
<td width="100px" nowrap="nowrap">流程名称：</td>
<td><div class="col-xs-12"><input class="form-control" name="flowName"  id="flowName"/></div></td>
<td width="100px" nowrap="nowrap">流程标题：</td>
<td><div class="col-xs-12"><input class="form-control" name="flowTitle" id="flowTitle"/></div></td>
<td width="100px" nowrap="nowrap">流水号：</td>
<td><div class="col-xs-12"><input class="form-control" name="runId" id="runId"/></div></td>				
<td width="100px" nowrap="nowrap">发起人：</td>
<td><div class="col-xs-10" style="float: left;">
<input class="form-control" name="beginUser" id="beginUser"  type="hidden"/>
<input class="form-control" name="userName" id="userName" readonly="readonly"/>
</div><a href="javascript:void(0);" onclick="userinit(['beginUser','userName'],'true');" style="line-height: 30px;">选择</a></td>
</tr>
</table>
<div align="center"><button type="button" class="btn btn-primary" onclick="doinit();">查询</button></div>
<div id="myTable" name="myTable"></div>
<table class="MessageBox" style="margin-top:3px;" align="center" width="440" cellpadding="0" cellspacing="0" id="infotable">
									   <tbody><tr class="head-no-title">
									      <td class="left"></td>
									      <td class="center">
									      </td>
									      <td class="right"></td>
									   </tr>
									   <tr class="msg">
									      <td class="left"></td>
									      <td class="center info">
									         <div class="msg-content">暂无信息！</div>
									      </td>
									      <td class="right"></td>
									   </tr>
									   <tr class="foot">
									      <td class="left"></td>
									      <td class="center"><b></b></td>
									      <td class="right"></td>
									   </tr>
									   </tbody>
							</table>
<div id="modaldialog"></div>
</body>
</html>
