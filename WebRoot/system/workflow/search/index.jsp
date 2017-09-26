<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>工作流查询</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/search/js/index.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/maintenance.css"></link>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
</head>
<body style="overflow-y: hidden;margin:0px">
<div>
<img  src="<%=imgPath%>/workflow/query.png" style="width: 40px;height: 40px;padding-left: 5px;"><span>流程维护</span>
</div>
<div class="list-group-item"  style="padding: 0px;">
			 <table class="table table-striped table-condensed">
			<tr>
			<td width="150px">流水号：</td>
			<td><div class="col-xs-10"><input type="text" id="runId" name="runId"  class="form-control"/></div></td>
			<td width="150px">流程名称：</td>
			<td><div class="col-xs-10"><input type="text" id="flowName" name="flowName" class="form-control"/></div></td>
			<td width="150px">流程类型：</td>
			<td><div class="col-xs-10">
			<select id="flowType" name="flowType"  class="form-control">
			<option value="1" selected="selected">固定流程</option>
			<option value="2">自由流程</option>
			</select>
			</div></td>
			</tr>
			<tr>
			<td>流程标题：</td>
			<td><div class="col-xs-10"><input type="text" name="runName" id="runName" class="form-control"/></div></td>
			<td>流程状态：</td>
			<td>
			<div class="col-xs-10"><select name="flowStatus" id="flowStatus" class="form-control">
			<option value="0" selected="selected">进行中的</option>
			<option value="1">已结束的</option>
			<option value="2">已挂起的</option>
			<option value="" >全部</option>
			</select></div></td>
			<td>指定发起人：</td>
			<td>
			<div class="col-xs-10"><input type="hidden" name="beginUserId" id="beginUserId"  class="form-control"/><input type="text" name="beginUserName"  id="beginUserName" class="form-control"/></div><a style="line-height: 30px;" onclick="userinit(['beginUserId','beginUserName']);">选择</a>
			</td>
			</tr>
			<tr>
			<td>管理范围：</td>
			<td><div class="col-xs-10">
			<select name="range" id="range" class="form-control">
				<option>管理范围</option>
			</select></div>
			</td>
			<td>时间范围：</td>
			<td colspan="3" >
			<div style="float: left;"  class="col-xs-4"><input style="cursor: pointer;"  type="text" name="beginTime" id="beginTime" 	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control"/></div>
			<div style="float: left;line-height: 30px;">至</div>
			<div style="float: left;"  class="col-xs-4"><input style="cursor: pointer;" type="text" name="endTime" id="endTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control"/></div>
			</td>
			</tr>
			</table>
</div>
<br>
<div align="center">
<button type="button" onclick="queryworkflow();" class="btn btn-primary">查询</button>
<button type="button" onclick="senior();" class="btn btn-success">高级</button>
</div>
<br>
<div class="well with-header with-footer">
                                <div class="header bg-warning">
                                    查询结果
                                 <div style="float:right;">
								    	<div class="btn-group btn-group-xs" role="group" aria-label="...">
										  <button type="button" class="btn btn-default" onclick="impout();">导出</button>
										</div>
						    	</div>
                                </div>
                                <div id="myTable" name="myTable" >
                                </div>
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
                            </div>
   
    




   <div id="modaldialog"></div>
</body>
</html>