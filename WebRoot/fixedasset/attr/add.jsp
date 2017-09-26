<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/jquery/ztree/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery-1.9/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/jquery/ztree/js/jquery.ztree.all-3.5.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="js/add.logic.js"></script>
<script>
</script>
<title>资产入库</title>
	<style>
		html,body{
			height:100%;margin:0px;padding:0px;
		}
		html{overflow:hidden;}
		body{background-color:white;}
		*{font-size:14px;}
		.background{
			position:absolute;width:100%;height:100%;
   			background:black;
			filter: alpha(opacity =   10);
			-moz-opacity: 0.1;
			-khtml-opacity: 0.1;
			opacity: 0.1;
			z-index:10;
		}
		#asset-form{
			height:90%;width:80%;position:absolute;top:5%;left:10%;
   			background:white;
			z-index:11;
		}
		#body{height:100%;width:100%;}
		.form-field-c{margin:0px auto;margin-top:10px;}
		.form-field-c td{padding:10px 0px;}
		.form-field-desc{width:100px;font-size:14px;letter-spacing:1px;}
		.form-field-c input.easyui-textbox{width:220px;}
		#top{line-height:55px;text-align:center;font-size:25px;letter-spacing:2px;}
		a.easyui-linkbutton{padding:3px 5px;letter-spacing:5px;}
		a#opt-add.easyui-linkbutton{padding:3px 10px;}
		#bottom{text-align:center;}
		span.tabs-title{font-size:15px;letter-spacing:1px;}
		
		
	</style>
	<style>
		.asset-image-body{width:80%;height:90%;margin-left:10%;margin-top:5%;position:relative;}
		.asset-image-panel-title,.asset-image-panel{position:absolute;}
		.asset-image-panel-title{
			width:150px;height:30px;line-height:30px;z-index:10;text-align:center;
			left:50px;background-color:white;letter-spacing:1px;font-size:14px;
		}
		.asset-image-panel{
			top:15px;left:0px;right:0px;bottom:10px;border:solid 1px #F7F7F7;border-radius:4px;
		}
	</style>
   	<style>
   		.asset-image-upload{
   			height:100px;margin-top:20px;width:50%;margin-left:25%;border:solid 1px #F7F7F7;
   			position:relative;overflow:hidden;border-radius:4px;
  			}
   		.asset-image-c{overflow:auto;position:absolute;top:130px;left:10px;right:10px;bottom:10px;}
   		.asset-image{width:100%;height:50%;}
   		.asset-a-image{width:30%;position:relative;display:inline-block;border:solid 1px #F7F7F7;}
   		.asset-a-image img{width:100%;}
   		.asset-a-image-screen{
   			position:absolute;width:100%;height:100%;z-index:10;
   			background:#ccc;
			filter: alpha(opacity =   20);
			-moz-opacity: 0.2;
			-khtml-opacity: 0.2;
			opacity: 0.2;
   		}
   		.asset-a-image-delete{
   			width:30px;height:30px;line-height:30px;text-align:center;
   			position:absolute;right:0px;top:0px;font-size:25px;color:red;cursor:pointer;
   			z-index:11;
  			}
   		.asset-a-image-delete:hover{color:#ED0505;}
   	</style>
</head>
<body>
	<div class="background"></div>
	<form name="asset-form" id="asset-form">
		<div class="easyui-layout" id="body" data-options="fit:true">
			<div id="top" data-options="region:'north',height:60,border:false">
				<input type="hidden" name="fixedassetRecord_seqId"/>
				<input type="hidden" name="fixedassetRecord_assetId"/>
				<input type="hidden" name="fixedassetRecord_recordType"/>
				<input type="hidden" name="fixedassetRecord_registrationDate"/>
				<input type="hidden" name="fixedassetRecord_registrationUser"/>
				资产入库
			</div>
			<div id="center" data-options="region:'center',border:false" style="border-right:solid 1px #F8F8F8;">
				<div class="easyui-tabs" id="center-tabs" data-options="fit:true,tabHeight:40,tabPosition:'left'">   
				    <div title="基本信息" data-options="tabWidth:200">
				    	<input type="hidden" name="fixedassetAttr_seqId"/>
				    	<table class="form-field-c" cellpadding="0" cellspacing="0">
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_assetNo">资产编号:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-textbox easyui-validatebox" name="fixedassetAttr_assetNo" data-options="required:true,missingMessage:'不可为空！'"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_assetName">资产名称:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-textbox easyui-validatebox" name="fixedassetAttr_assetName" data-options="required:true,missingMessage:'不可为空！'"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_standard">规格型号:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-textbox" name="fixedassetAttr_standard" data-options="multiline:true,height:50"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_manufacture">生产厂家:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-textbox" name="fixedassetAttr_manufacture" data-options="multiline:true,height:50"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_exFactoryDate">出厂日期:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-datebox" name="fixedassetAttr_exFactoryDate"/> 
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_serviceLife">使用年限:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-numberspinner" name="fixedassetAttr_serviceLife" data-options="min:1,max:1000"/> 
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_unitPrice">单价:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-numberbox" name="fixedassetAttr_unitPrice" data-options="min:0,precision:2,groupSeparator:','"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetAttr_remark">备注:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-textbox" name="fixedassetAttr_remark" data-options="multiline:true,height:80"/>
				    			</td>
				    		</tr>
				    	</table>
				    </div>   
				    <div title="资产图片" data-options="tabWidth:200">
				    	<input type="hidden" name="fixedassetAttr_image"/>
				    	<div class="asset-image-body">
				    		<div class="asset-image-panel-title">资产快照</div>
				    		<div class="asset-image-panel">
				    			<div class="asset-image-upload">
									<div id="attachDiv" style="display:none;"></div>
									<div id="fsUploadProgress"></div>
									<div style="position:absolute;left:10px;bottom:0px;">
										<button id="spanButtonPlaceHolder"></button>
										<button id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  style="background-color:#00A1E7;margin:0px;padding:0px;border:none;width:50px;height:28px;color:white;fong-size:20px;position:absolute;top:0px;"/>取消</button>
									</div>
				    			</div>
				    			<div class="asset-image-c">
				    				<div class="asset-image">
				    				</div>
				    			</div>
				    		</div>
				    	</div>
				    </div>   
				    <div title="其他信息" data-options="tabWidth:200">
				    	<input type="hidden" name="fixedassetRelation_seqId"/>
						<input type="hidden" name="fixedassetRelation_assetId"/>
						
						<table class="form-field-c" cellpadding="0" cellspacing="0">
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_assetType">资产类别:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-combo" name="fixedassetRelation_assetType" id="fixedassetRelation_assetType" data-options="editable:false"/>
				    				<div class="ztree" id="asset-type-tree"></div>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_assetSource">资产来源:</label>
				    			</td>
				    			<td>
				    				<select class="easyui-combobox" name="fixedassetRelation_assetSource">
				    				</select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_useDept">使用部门:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-textbox" name="fixedassetRelation_useDept" readonly="readonly" />
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_useSituation">使用情况:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-textbox" name="fixedassetRelation_useSituation" data-options="multiline:true,height:50"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_storageLocation">存放地点:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-textbox" name="fixedassetRelation_storageLocation" data-options="multiline:true,height:50"/> 
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_useUser">使用人员:</label>
				    			</td>
				    			<td class="form-field-desc">
				    				<input class="easyui-textbox" name="fixedassetRelation_useUser"/> 
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_number">数量:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-numberbox" name="fixedassetRelation_number" data-options="min:1"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_postingDate">入账日期:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-datetimebox" name="fixedassetRelation_postingDate">
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_unit">计量单位:</label>
				    			</td>
				    			<td>
				    				<select class="easyui-combobox" name="fixedassetRelation_unit">
				    				</select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="form-field-desc">
				    				<label for="fixedassetRelation_netSalvage">净残值率:</label>
				    			</td>
				    			<td>
				    				<input class="easyui-numberbox" name="fixedassetRelation_number" data-options="min:0,precision:3"/>
				    			</td>
				    		</tr>
				    	</table>
				    </div>   
				</div>
			</div>
			<div id="bottom" data-options="region:'south',height:50,border:false" style="line-height:50px;overflow:hidden;border-top:solid 1px #F8F8F8;">
				<a class="easyui-linkbutton" id="opt-add">入库</a>
			</div>
		</div>
	</form>
</body>
</html>