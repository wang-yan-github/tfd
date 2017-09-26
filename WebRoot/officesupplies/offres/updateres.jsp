<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建办公用品</title>
<%String resId=request.getParameter("resId"); %>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offres/js/updateres.js"></script>

<script type="text/javascript">
var resId="<%=resId%>";
</script>
</head>
<body>
<form id="offresform" name="offresform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;">
<a style="cursor: auto;" class="list-group-item active">办公用品信息编辑</a>
   <table class="table table-striped table-condensed">
    <tr>
   <td width="15%">
   办公用品名称：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
   <input type="hidden" id="resId" name="resId"/>
<input type="text" id="resName" name="resName" class="form-control "  placeholder="请输入名称......">
</div>
   </td>
   <td width="15%">
   规格型号：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="resFormat" name="resFormat" class="form-control ">
</div>
   </td>
   </tr>
    <tr>
   <td width="15%">
   登记类型：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<select  id="resType" name="resType" class="form-control " >
<option selected="selected" value="">请选择</option>
<option value="领用">领用</option>
<option value="借用">借用</option>
</select>
</div>
   </td>
   <td width="15%">
   计量单位：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="resUnit" name="resUnit" class="form-control " >
</div>
   </td>
   </tr>
    <tr>
   <td width="15%">
   办公用品库：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<select id="libraryId" name="libraryId" class="form-control " onchange="getlibclassify();" >
<option selected="selected" value="">请选择</option>
</select>
</div>
   </td>
   <td width="15%">
  单价：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="resPrice" name="resPrice" class="form-control " >
</div>
   </td>
   </tr>
    <tr>
   <td width="15%">
   办公用品类别：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<select id="classifyId" name="classifyId" class="form-control " >
<option selected="selected" value="">请选择</option>
</select>
</div>
   </td>
   <td width="15%">
  供应商：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="resSupplier" name="resSupplier" class="form-control " >
</div>
   </td>
   </tr>
     <tr>
   <td width="15%">
  当前库存：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="beforeStock" name="beforeStock" class="form-control " >
</div>
   </td>
    <td width="15%">
  审批人员：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
   <input type="hidden" id="approveStaff" name="approveStaff" readonly="readonly" class="form-control "  >
<input type="text" id="userName" name="userName" readonly="readonly" class="form-control "  >
</div>
   </td>
   </tr>
     <tr>
   <td width="15%">
   最低警戒库存：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="minStock" name="minStock" class="form-control "  >
</div>
   </td>
   <td width="15%">
  登记权限（用户）：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
			<textarea rows="3" id="userPriv" name="userPriv" class="form-control " style="display:none;"  ></textarea>
			<textarea rows="3" id="staffName" name="staffName" class="form-control " readonly="readonly" ></textarea>
			</div>
			<div style="margin-top: 40px;">
         <a href="javascript:void(0);" onclick="userinit(['userPriv','staffName']);">添加人员</a>
         </div>
   </td>
   </tr>
     <tr>
  <td width="15%">
   最高警戒库存：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
<input type="text" id="maxStock" name="maxStock" class="form-control "  >
</div>
   </td>
    <td width="15%">
   物品调拨人员：
   </td>
   <td width="35%">
   <div class="col-xs-10 form-group">
   <input type="hidden" id="dispatchStaff" name="dispatchStaff" readonly="readonly" class="form-control "  >
<input type="text" id="disName" name="disName" readonly="readonly" class="form-control "  >
</div>
   </td>
   </tr>
   <tr>
   <td width="15%" >
  登记权限（部门）：
   </td>
   <td width="35%" colspan="3">
   <textarea rows="3" cols="40" name="deptPriv" id="deptPriv" style="display: none;"></textarea>
   <div class="col-xs-10 form-group">
<textarea rows="3" cols="40" name="deptName" id="deptName"  class="form-control" readonly="readonly"></textarea>
</div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="deptinit(['deptPriv','deptName'],'false','deptNameval');">添加部门</a>
</div>
   </td>
   </tr>
   <tr>
   <td>附件：</td>
   <td colspan="3">
   <div id="attachDiv" name="attachDiv"></div>
   </td>
   </tr>
    <tr>
   <td width="15%">
附件选择：
   </td>
   <td width="35%" colspan="3">
<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('office','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>

</div>
   </td>
   </tr>
   </table>
    </div>
   <div align="center">
  <button type="submit" name="send" class="btn btn-primary ">保存</button>
<button type="button" class="btn btn-danger" onclick="delres();">删除</button>
 </div>
 </form>
   <div id="modaldialog"></div>
   <script type="text/javascript">
   $(document).ready(function() {
   $("#offresform").bootstrapValidator({
	   message: 'Pas valide',
	   container: 'tooltip',
	   feedbackIcons: {
	       valid: 'glyphicon glyphicon-ok',
	       invalid: 'glyphicon glyphicon-remove',
	       validating: 'glyphicon glyphicon-refresh'
	   },
	   submitButtons: 'button[type="submit"]',
	   fields: {
		   resName: {
	           validators: {
	          	 container: 'popover',
	               notEmpty: {
	                   message: '不能为空'
	               }
	           }
	       },resFormat: {
	           validators: {
	          	 container: 'popover',
	               notEmpty: {
	                   message: '不能为空'
	               }
	           }
	       },resUnit: {
	           validators: {
		          	 container: 'popover',
		               notEmpty: {
		                   message: '不能为空'
		               }
		           }
		       },resPrice: {
		           validators: {
			          	 container: 'popover',
			               notEmpty: {
			                   message: '不能为空'
			               },numeric: {
			                   message: '请填写数字'
			               }
			           }
			       },resSupplier: {
			           validators: {
				          	 container: 'popover',
				               notEmpty: {
				                   message: '不能为空'
				               }
				           }
				       },beforeStock: {
				           validators: {
					          	 container: 'popover',
					               notEmpty: {
					                   message: '不能为空'
					               },integer: {
					                   message: '请填写整数'
					               }
					           }
					       },minStock: {
					           validators: {
						          	 container: 'popover',
						               notEmpty: {
						                   message: '不能为空'
						               },integer: {
						                   message: '请填写整数'
						               }
						           }
						       },maxStock: {
						           validators: {
							          	 container: 'popover',
							               notEmpty: {
							                   message: '不能为空'
							               },integer: {
							                   message: '请填写整数'
							               }
							           }
							       },deptName: {
							           validators: {
								          	 container: 'popover',
								               notEmpty: {
								                   message: '不能为空'
								               }
								           }
								       },libraryId:{
								    	   validators: {
									          	 container: 'popover',
									               notEmpty: {
									                   message: '不能为空'
									               }
									           }
								       },classifyId:{
								    	   validators: {
									          	 container: 'popover',
									               notEmpty: {
									                   message: '不能为空'
									               }
									           }
								       },resType:{
								    	   validators: {
									          	 container: 'popover',
									               notEmpty: {
									                   message: '不能为空'
									               }
									           }
								       }
	   }
	   }).on('success.form.bv',function(e){
			 e.preventDefault();
				
	         // Get the form instance
	         var $form = $(e.target);

	         // Get the BootstrapValidator instance
	         var bv = $form.data('bootstrapValidator');
	         updateres();
			});
   });
   </script>
</body>
</html>