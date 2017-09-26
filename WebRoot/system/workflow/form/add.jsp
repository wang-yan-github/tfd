<%@page import="tfd.system.unit.account.data.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<% 
String userName= request.getSession().getAttribute("USER_NAME").toString();
String userId = request.getSession().getAttribute("USER_ID").toString();
String deptId = request.getSession().getAttribute("DEPT_ID").toString();
String deptName= request.getSession().getAttribute("DEPT_NAME_SHORT").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/form/js/add.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/addform.css"></link>
<script type="text/javascript">
var zNodes;
var flowTypeNodes;
var deptId="<%=deptId%>";
var deptName="<%=deptName%>";
</script>

<style type="text/css">
html,body
{
height: 100%;
}
#menuContent,#menuFlowType{
width: 200px;
height: 200px;
overflow: auto;
display: none;
position: absolute;
border: solid #CCC 1px;
background-color:white; 
z-index: 999;
	}
</style>
</head>
<body style="margin-top: 10px;">
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1000;">
<ul id="treeDemo" class="ztree" style="margin-top:0; "></ul>
</div>
<div id="menuFlowType" class="menuFlowType" style="display:none; position: absolute;z-index:1000;">
<ul id="flowTypeDemo" class="ztree" style="margin-top:0;"></ul>
</div>
<div align="center" class="widget" >
<form id="form1" name="form1" class="form-horizontal"  action="<%=contextPath%>/tfd/system/workflow/form/act/WorkFlowFormAct/AddWorkFlowFormAct.act" style="width: 80%;">
<div class="widget-header bordered-bottom bordered-sky">
<span class="widget-caption">新建表单</span>
</div>
<div class="panel-body">
<table class="table table-striped  table-condensed" >
<tr>
<td width="150px;">表单名称：</td>
<td><div class="col-xs-10 form-group"><input id="formName" name="formName" type="text" class="form-control"/></div></td>
<td width="150px">数据表名称：</td>
<td><div class="col-xs-10 form-group"><input id="formTableName" name="formTableName" type="text" class="form-control"/></div></td>
</tr>
<tr>
<td >创建人员：</td>
<td ><div class="col-xs-10"><input id="formUserName" name="formUserName" type="text" value="<%=userName%>" readonly class="form-control"/>
<input id="formCreateName" name="formCreateName" type="hidden" value="<%=userId%>"  class="form-control"/>
</div></td>
<td >流程分类：</td>
<td >
 <div class="col-xs-10">
 <select id="formType" name="formType" class="form-control">
 <option value="workflow">系统工作流</option>
 <option value="project">项目管理</option>
 </select>
 </div>
</td>
</tr>
<tr>
<td >所属层级：</td>
<td >
<div class="col-xs-10 form-group">
<input id="workFlowTypeId" name="workFlowTypeId" type="hidden" >
<input id="workFlowTypeName" name="workFlowTypeName" type="text" value="" onclick="flowTypeShowMenu()" readonly="readonly" class="form-control" placeholder="请选择层级..." >
</div>
</td>
<td >所属部门：</td>
 <td >
<div class="col-xs-10 form-group">
<input id="formDeptId" name="formDeptId" type="hidden"/>
<input id="formDept" name="formDept" type="text"  value="" onclick="showMenu();"  readonly class="form-control"  placeholder="请选择部门..." />
</div>
 </td>
</tr>
</table>
</div>
<br>
<div align="center"><button type="submit" class="btn btn-primary" disabled="disabled">确定</button></div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			formName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '表单名称不能为空！'
                    }
                }
            },
            workFlowTypeName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '表单层级不能为空！'
                    }
                }
            },
            formTableName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '数据表名不能为空！'
                    },
            regexp: {
                regexp: /^[a-zA-Z\.]+$/,
                message: '数据表名必须全部为字母！'
            }
                }
            }            
		}
	}).on('success.form.bv',function(e){
		 //e.preventDefault();
			
	     // Get the form instance
	    // var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     //var bv = $form.data('bootstrapValidator');
	     parent["left"].location=contextPath+ "/system/workflow/form/formtree.jsp";
		}); 
});
</script>
</BODY>
</HTML>	
