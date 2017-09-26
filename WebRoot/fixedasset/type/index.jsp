<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资产类别管理</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/jquery/ztree/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="js/index.logic.js"></script>
<style>
	html,body{
		height:100%;margin:0px;padding:0px;
	}
	html{overflow:hidden;}
	#body{height:100%;width:100%;}
	.form-field-c{margin-left:10px;margin-top:10px;}
	.form-field-c td{padding:10px 0px;}
	.form-field-desc{width:100px;font-size:14px;letter-spacing:1px;}
	.form-field-c input.easyui-textbox{width:220px;}
	.form-field-c input.easyui-combo{width:220px;}
	.form-field-c input.easyui-numberbox{width:220px;}
	
	.textbox-field{width:100%;height:100%;}
	.textbox-parentId{position:relative;}
	#top{line-height:45px;}
	.padding-left-20{padding-left:20px;}
	.padding-left-10{padding-left:10px;}
	#opt-edit-c{display:none;}
	
	#search-box{padding:5px 0px 5px 5px;}
</style>
</head>
<body>
	<div class="easyui-layout" id="body" data-options="fit:true">
		<div data-options="region:'north',height:50,border:false" id="top">
			<span class="padding-left-20"></span>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="opt-add-view">新建</a>
			<span class="padding-left-10"></span>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="opt-batch-delete">批量删除</a>
			<span class="padding-left-10"></span>
			<a class="easyui-linkbutton" id="opt-batch-import">批量导入</a>  
			<span class="padding-left-10"></span>
			<a class="easyui-linkbutton" id="opt-export">导出</a>    
		</div>
		<div data-options="region:'center'">
			<div class="easyui-layout" id="center-body" data-options="fit:true">
				<div data-options="region:'west',width:200,border:false" style="border-right:solid 1px #F8F8F8;">
					<div id="search-box">
						<input class="easyui-searchbox" data-options="prompt:'请输入类别名称...'"/>
					</div>
					<div class="ztree" id="type-tree"></div>
				</div>
				<div data-options="region:'center',border:false" id="center-body-center">
					<form id="asset-type-form">
						<input type="hidden" name="seqId"/>
						<input type="hidden" name="levelId"/>
						
						<table class="form-field-c" cellpadding="0" cellspacing="0">
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="typeName">类别名称:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-textbox easyui-validatebox" id="typeName" name="typeName" data-options="height:25,required:true,missingMessage:'请填写！'"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="parentId">父级类别:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<div class="textbox-field textbox-parentId">
					    				<input class="easyui-combo" name="parentId" id="parentId" data-options="editable:false,height:25"/>
					    				<div class="ztree" id="parent-tree"></div>
				    				</div>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="unit">计量单位:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-textbox" name="unit" id="unit" data-options="multiline:true,height:50,prompt:'多个请用‘|’分割'"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="netSalvage">净残值率:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-numberbox" name="netSalvage" id="netSalvage" data-options="precision:3,min:0,max:0.999,value:0"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td colspan="2" style="text-align:center;">
				    				<span id="opt-add-c">
					    				<a class="easyui-linkbutton" id="opt-add" data-options="iconCls:'icon-add'">添加</a>
				    				</span>
				    				<span id="opt-edit-c">
					    				<a class="easyui-linkbutton" id="opt-save" data-options="iconCls:'icon-save'">保存</a>
										<span class="padding-left-10"></span>	
					    				<a class="easyui-linkbutton" id="opt-delete" data-options="iconCls:'icon-remove'">删除</a>
				    				</span>
				    			</td>
				    		</tr>
			    		</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>