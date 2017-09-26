<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人设置</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
</head>
<style>
html,body{
margin:0px;
padding:0px;
}
#main{width:90%;margin-left:5%;margin-top:20px;}
</style>
<body>
<form id="form1" name="form1" class="form-horizontal" >
	<div id="main">
		<div class="userinfo" >
			<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         个人资料
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
<tr>
<td width="15%">姓名:</td>
<td width="70%" ><div class="col-xs-12 form-group" ><input class="form-control " type="text" id="username" name="username"  ></div><input type="hidden" id="userId" /></td>
<td rowspan="3" style="border-left:#CCC 1px solid;"align="center">
<input type="hidden" id="headImg" name="headImg">
<div>
	<img id="imgheadsrc" name="imgheadsrc" onerror="this.src='<%=imgPath %>/personal/error.jpg'"  width="100%" height="150px"/>
<div  id="imgupdiv"  name="imgupdiv" style="margin-bottom: 0px;position:relative; bottom: 0px;">
	<a id="uphead" style="cursor: pointer;padding-top:5px;" data-toggle="modal" data-target="#myModal">更改上传</a>
</div>
</div>
</td>
</tr>
<tr>
<td width="15%">别名:</td>
<td><div class="col-xs-12 form-group" ><input class="form-control " type="text" name="byname" id="byname"></td>
</tr>
<tr>
<td width="15%">性别:</td>
<td ><div class="col-xs-12 form-group"  >
	<select id="sex" name="sex" class="form-control " >
		<option value="男" >男</option>
		<option value="女" >女</option>
	</select>
</div></td>
</tr  >
</table>
<table class="table table-striped" >
<tr>
<td width="15%">工作电话:</td>
<td><div class="col-xs-12 form-group" ><input class="form-control " type="text" name="mobile" id="mobile"></td>
<td width="15%">家庭电话:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" name="hometel" id="hometel"></td>
</tr>
<tr>
<td width="15%">QQ号码:</td>
<td><div class="col-xs-12 form-group" ><input class="form-control " type="text" name="qq" id="qq"></td>
<td width="15%">电子邮件:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" name="emaile" id="emaile"></td>
</tr>
</table>
<table class="table table-striped" >
<tr>
<td width="15%">家庭地址:</td>
<td><div class="col-xs-12 form-group" ><input class="form-control " type="text" name="homeadd" id="homeadd"></td>
</tr>
<tr>
<td colspan="2" align="right" >
	<input type="submit" style="margin-top:15px;margin-right:10px;" id="btn_ok" value="保存设置" class="btn btn-primary">
	<button type="button"  style="margin-top:15px;"  class="btn btn-default btn_back">返回</button>
</td>
</tr>
</table>
</div>
</div>
</div>
</div>
</form>

 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
	 <div class="modal-dialog"> <div class="modal-content"> 
		 <div class="modal-header"> 
			 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
			 <h4 class="modal-title" id="myModalLabel">上传头像</h4> 
		 </div>
			 <div class="modal-body">
					 <table>
					 	<tr>
					 	<td width="100px;">选择图：</td>
					 	<td><input type="file" id="headfile" name="headfile"/></td>
					 	</tr>
					 </table>
					 </br>
					  <div class="modal-footer"> 
				 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
				 <button type="button" class="btn btn-primary" onclick="uphead('userinfo','headfile');">上传</button> 
			 </div>
			 </div> 
		 </div> 
	 </div> 
 </div>



</body>
<script type="text/javascript">
function uphead(module,fileId)
{
	
	upmoduleimg(module,fileId,function(rv){
		var json=rv;
		if(json.msg=="1")
			{
			alert(json.conent);
			}else if(json.msg=="2")
			{
				$("#headImg").val(json.imgname);
				$("#imgheadsrc").prop("src",json.webpath);
				$('#myModal').modal('hide')
			}
	});
}

$('#form1').bootstrapValidator({
	container: 'tooltip',
	message: '这不是一个有效的值',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		username: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空！'
                }
            }
        },
        emaile: {
           validators: {
               emailAddress: {
                   message: '电子邮地址有错误！'
               }
           }
        }
	}
});
	$(function(){
		var requestUrl=contextPath+'/tfd/system/setuser/act/SetUserAct/getUserInfoAndAccountById.act?';
    	$.ajax({
    			url:requestUrl,
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				checkConfig();
    				$('#userId').val(data[0].userId);
    				$('#username').val(data[0].userName);
    				$('#sex').val(data[0].sex);
    				$('#homeadd').val(data[0].homeAdd);
    				$('#hometel').val(data[0].homeTel);
    				$('#mobile').val(data[0].mobileNo);
    				$('#qq').val(data[0].qQ);
    				$('#emaile').val(data[0].eMaile);
    				$('#byname').val(data[0].byName);
    				var headImg=data[0].headImg;
    				$("#headImg").val(headImg);
    				if(headImg!=""&&headImg!="null"&&headImg!=null){
    					$("#imgheadsrc").prop("src",contextPath + "/attachment/userinfo/"+headImg);
    				}
    			}
    	});
    	$('#btn_ok').click(function(){
    		updateInfo();
    	})
	})
	function updateInfo(){
		var requestUrl=contextPath+'/tfd/system/setuser/act/SetUserAct/updateUserInfoAndAccount.act?';
    	$.ajax({
    			url:requestUrl,
    			data:{
    				userId:$('#userId').val(),
    				userName:$('#username').val(),
    				sex:$('#sex').val(),
    				homeAdd:$('#homeadd').val(),
    				homeTel:$('#hometel').val(),
    				mobile:$('#mobile').val(),
    				qq:$('#qq').val(),
    				email:$('#emaile').val(),
    				byName:$('#byname').val(),
    				headImg:$("#headImg").val()
    			},
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				if(data!=0){
    					top.layer.msg("修改成功")
    				}
    			}
    	});
	}
	function checkConfig(){
		var requestUrl = contextPath + "/tfd/system/sysinfo/copyright/act/SysInfoAct/getSysInfoList.act";
    	$.ajax({
    			url:requestUrl,
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				if(data.updateName!='1'){
						$("#username").attr("readonly",true);
					}
    			}
    	});
	}
</script>
</html>