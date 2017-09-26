<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本流程</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_standard/ckeditor.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/workflow/js/add.js"></script> 
<script type="text/javascript">
var flowTypeNodes;
var zNodes;
</script>
<style type="text/css">
html,body
{
height: 100%;
}
#menuFlowType,#menuContent{
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
<div id="menuContent">
<ul id="treeDemo" class="ztree" style="margin-top:0; "></ul>
</div>
<div id="menuFlowType">
<ul id="flowTypeDemo" class="ztree" style="margin-top:0;"></ul>
</div>
<form id="form1" method="post"  class="form-horizontal"  action="<%=contextPath%>/tfd/system/workflow/workflow/act/WorkFlowAct/createWorkFlowAct.act">
<div align="center">
<div style="width: 98%;border: 1px solid silver;box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);">
   <div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion"   href="#collapseOne">
          流程基本属性
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
    <div class="panel-body" style="font-size: 14px;">
       
<table class="table table-striped table-condensed">
<tr>
<td width="150px">流程名称：</td>
<td><div class="col-xs-8 form-group"><input type="text" id="flowName" name="flowName" value="" class="form-control"/></div></td>
<td width="150px">流程类型：</td>
<td><div class="col-xs-8">
<select id="flowType" name="flowType" class="form-control">
<option value="1">固定流程</option>
<option value="2">自由流程</option>
<option value="3">特种流程</option>
</select></div>
</tr>
<tr>
<td>流程排序号：</td>
<td><div class="col-xs-8 form-group"><input type="text" id="sortId" name="sortId" value=""  class="form-control"/></div></td>
<td>所属类型：</td>
<td>
<div class="col-xs-8">
<div id="moduleId" ></div>
 </div>
</div>
</td>
</tr>
<tr>
<td>流程分类：</td>
<td>
<div class="col-xs-8 form-group">
	<input type="hidden" id="workFlowTypeId" name="workFlowTypeId"/>
	<input type="text"  id="workFlowTypeName" name="workFlowTypeName"  readonly value="" onclick="flowTypeShowMenu()" class="form-control"/>
</div>
</td>
<td>流程表单：</td>
<td>
<div class="col-xs-8  form-group">
      <input type="hidden" id="formId" name="formId"/>
       <input type="text"  id="formName" name="formName" readonly value="" onclick="showMenu();" class="form-control"/>
</div>
   <span style="float: left; margin-top: 10px;cursor: pointer;" onclick="readForm();">查看表单</span>
</td>
</tr>
</table>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion"  href="#collapseTwo">
          流程高级设置
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse">
      <div class="panel-body" style="font-size: 14px;">
<table class="table table-striped table-condensed">
<tr>
<td width="150px">启用级别审批
</td>
<td>
<div class="col-xs-8">
<select id="leavePass" name="leavePass" class="form-control">
<option value="0">否</option>
<option value="1">是</option>
</select>
</div>
</td>
<td width="150px">更新锁定：
</td>
<td>
<div class="col-xs-8">
<select id="flowLock" name="flowLock" class="form-control">
<option value="0">解锁</option>
<option value="1">锁定</option>
</select>
</div>
</td>
</tr>
<tr>
<td>委托规则：</td>
<td>
<div class="col-xs-8">
<select id="freeToOther" name="freeToOther" class="form-control">
<option value="1">自由委托</option>
<option value="2">经办权限委托</option>
<option value="3">经办人委托</option>
<option value="0">禁止委托</option>
</select>
</div>
</td>
<td>启用附件：</td>
<td>
<div class="col-xs-8">
<select id="flowDoc" name="flowDoc" class="form-control">
<option value="1">启用</option>
<option value="0">不启用</option>
</select>
</div>
</td>
</tr>
</table>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseThree">
          其它设置
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse">
      <div class="panel-body" style="font-size: 14px;">
<table class="table table-striped table-condensed">
<tr>
<td width="150px">文号生成器：</td>
<td><div class="col-xs-8"><input type="text" name="autoCode" id="autoCode" class="form-control"></div></td>
<td width="150px">流程计数器：</td>
<td><div class="col-xs-8"><input type="text" name="autoNum" id="autoNum" class="form-control"></div></td>
</tr>
<tr>
<td>流程传阅：</td>
<td><div class="col-xs-8"><input type="text" name="sendToUser" id="sendToUser" class="form-control"></div></td>
<td >流程公布：</td>
<td><div class="col-xs-8"  style="float:left; "><input type="checkbox" name="sendToModule" id ="sendToModule" >通知公告<input type="checkbox" name="" id ="" >新闻</div></td>
</tr>
<tr>
<td>归档位置：</td>
<td><div class="col-xs-8"><input type="text" name="savePath" id="savePath" class="form-control"></div></td>
<td>归档方式：</td>
<td><div class="col-xs-8"><select id="saveFile" name="saveFile" class="form-control">
<option value="0">关闭归档</option>
<option value="1">人工归档</option>
<option value="2">自动归档</option>
</select>
</div></td>
</tr>
<tr>
<td>子流程等待：</td>
<td><div class="col-xs-8"><select name="flowWait" id="flowWait" class="form-control">
<option value="0" >不等待子流程</option>
<option value="1">等待子流程</option>
<option value="2">指定等待</option>
</select></div></td>
<td></td>
<td></td>
</tr>
<tr>
<td >绑定字段：</td>
<td ><div class="col-xs-8"><select id="fields" class="form-control"></select></div></td>
<td >被绑定字段：</td>
<td ></td>
</tr>
<tr>
<td >选择流程：</td>
<td ></td>
<td >选择字段：</td>
<td ></td>
</tr>
<tr>
<td>流程说明：</td>
<td colspan="3">
<textarea id="editor" name="editor"></textarea>
<script type="text/javascript">CKEDITOR.replace('editor',{toolbar : [
                                                                     ['Source','NewPage','Preview'],     
                                                                     ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],
                                                                     ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
                                                                     ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],     
                                                                     '/',     
                                                                     ['Styles','Format'],     
                                                                     ['Bold','Italic','Strike'],     
                                                                     ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote']  
                                                                     ]});
                                                                     </script>
</td>
</tr>
</table>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</br>

<div align="center"><button type="submit" class="btn btn-primary"  disabled="disabled">确定</button></div> 
</form>
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
			flowName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '流程名称不能为空！'
                    }
                }
            },
            workFlowTypeName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '流程分类不能为空！'
                    }
                }
            },
            formName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '流程表单不能为空！'
                    }
                }
            },
            sortId: {
                validators: {
                	container: 'popover',
                	numeric: {
                        message: '请填写正确的数字！'
                    },
			        notEmpty: {
			            message: '请填写正确的数字！'
			        }
                }
            }
		}
	}).on('success.form.bv',function(e){
		 //e.preventDefault();
			
	     // Get the form instance
	    // var $form = $(e.target);

	     // Get the BootstrapValidator instance
	   //  var bv = $form.data('bootstrapValidator');
	     parent["left"].location=contextPath+ "/system/workflow/workflow/prcstree.jsp";
		});
});
</script>
</body>
</html>