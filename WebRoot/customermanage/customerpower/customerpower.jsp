<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理权限设置</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script>
$(function (){
	lookpower();
});
function userNameval(){
	$('#customerpowerform').bootstrapValidator('revalidateField', 'userName');
}
function addpower(){
	var url=contextPath+"/customermanage/act/CustomerpowerAct/addpowerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			accountId:$("#accountId").val(),
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('添加成功');
				window.location.reload();	
			}
	}
	});
}
function lookpower(){
	var url=contextPath+"/customermanage/act/CustomerpowerAct/lookpowerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			var j=1;
			var tr="";
			for(var i=0;i<data.length;i++){
				 tr+="<tr><td align=\"center\" width=\"10%\">"+j+"</td>"+
				"<td align=\"center\" width=\"60%\">"+
				"<div>"+data[i].userName+"</div></td>"+
				"<td align=\"center\" width=\"30%\"><a href=\"#\" onclick=\"delpower('"+data[i].powerId+"');\" >删除</a></td></tr>";
				j++;
			}
			$("#powerStaff").append(tr);
	}
	});
}
function delpower(powerId){
	var url=contextPath+"/customermanage/act/CustomerpowerAct/delpowerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			powerId:powerId
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('删除成功');
				window.location.reload();	
			}
	}
	});
}
</script>
</head>
<body>
<form id="customerpowerform" name="customerpowerform" class="form-horizontal">
  <div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 1%;margin-top:10px;width: 98%;">
<a style="cursor: auto;" class="list-group-item active">客户管理权限设置</a>
   <table class="table table-striped table-condensed">
<tr>
<td>设置人员:</td>
<td>
<input type="hidden"  id="accountId" name="accountId" />
<div class="col-xs-6 form-group">
<textarea rows="4" class="form-control"  name="userName" id="userName"  readonly="readonly" ></textarea></div>
<div style="margin-top: 55px;">
<a href="javascript:void(0);"  onclick="userinit(['accountId','userName'],'false','userNameval');">添加</a>
</div>
</td>
</tr>
</table>
<div align="center"><button type="submit" class="btn btn-primary">保 存</button></div>
</br>
   </div>
   </form>
 <div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 1%;margin-top:10px;width: 98%;">
<a style="cursor: auto;" class="list-group-item active">客户管理权限列表</a>
<table class="table table-striped table-condensed" id="powerStaff">
<tr>
<td align="center" width="10%">序号</td>
<td align="center" width="60%">人员名称</td>
<td align="center" width="30%">操作</td>
</tr>
</table>
   </div>
   </div>
    <div id="modaldialog"></div>
    <script type="text/javascript">
    $(document).ready(function() {
    	$("#customerpowerform").bootstrapValidator({
    		 message: 'Pas valide',
    		 container: 'tooltip',
    		 feedbackIcons: {
    		     valid: 'glyphicon glyphicon-ok',
    		     invalid: 'glyphicon glyphicon-remove',
    		     validating: 'glyphicon glyphicon-refresh'
    		 },
    		 fields: {
    			 userName: {
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
    	     addpower();
    		}); 
    });
    </script>
</body>
</html>