<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String formId=request.getParameter("formId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>表单信息修改</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/form/js/manage.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/addform.css"></link>
<script type="text/javascript">
var formId="<%=formId%>";
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
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
<ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
</div>
<div id="menuFlowType" class="menuFlowType" style="display:none; position: absolute;">
<ul id="flowTypeDemo" class="ztree" style="margin-top:0;"></ul>
</div>
<div align="center" class="widget" >
<form name="form1" id="form1" class="form-horizontal"  action="<%=contextPath%>/tfd/system/workflow/form/act/WorkFlowFormAct/updateWorkFlowFormAct.act" style="width: 80%;">
<div class="widget-header bordered-bottom bordered-sky">
<span class="widget-caption">修改表单</span>
</div>
<input type="hidden" id="formId" name="formId" value="<%=formId%>"/>
<div class="panel-body">
<table class="table table-striped table-condensed">
<tr>
 <td width="150px">表单名称：</td>
 <td><div class="col-xs-10 "><input type="text" id="formName" name="formName" value="" class="form-control"/></div></td>
 <td width="150px">数据表名称：</td>
 <td><div class="col-xs-10"><input type="text" id="formTableName" name="formTableName" value="" readonly class="form-control"/></div></td>
 </tr>
  <tr>
 <td>创建人员：</td>
 <td><div class="col-xs-10">
 <input id="formUserName" name="formUserName" type="text" value="" readonly class="form-control"/>
 <input type="hidden" id="formCreateUser" name="formCreateUser" value=""  readonly/>
 
 
 </div></td>
  <td>流程分类：</td>
 <td>
 <div class="col-xs-10">
 <select id="formType" name="formType" class="form-control">
 <option value="workflow">系统工作流</option>
 <option value="project">项目管理</option>
 </select>
 </div>
 </td>
 </tr>
   <tr>
 <td>流程层级：</td>
 <td>
 <div class="col-xs-10">
 <input type="text" id="workFlowTypeId" name="workFlowTypeId" style="display:none;">
 <input type="text" id="workFlowTypeName" name="workFlowTypeName" readonly value="" onclick="flowTypeShowMenu()" class="form-control">
 </div>
 </td>
<td>所属部门：</td>
 <td>
 <div class="col-xs-10 form-group">
 <input type="text" id="formDeptId" name="formDeptId" value="" style="display:none;">
 <input type="text" id="formDeptName" name="formDeptName" readonly value="" onclick="showMenu();"  class="form-control">
 </div>
 </td>
 </tr>
 </table>
 </div>
 <br>
<div align="center"><button type="button" onclick="designForm()" class="btn btn-primary">设计表单</button>
<button type="submit" class="btn btn-primary">保存</button>
<button type="button" class="btn btn-warning" onclick="readForm();">预览表单</button>
</div>
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
            }
		},
        workFlowTypeName: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '表单层级不能为空！'
                }
            }
        }
	}).on('success.form.bv',function(e){
		 //e.preventDefault();
			
	     // Get the form instance
	    // var $form = $(e.target);

	     // Get the BootstrapValidator instance
	   //  var bv = $form.data('bootstrapValidator');
	     parent["left"].location=contextPath+ "/system/workflow/form/formtree.jsp";
		}); 
})
</script>
</body>
</html>