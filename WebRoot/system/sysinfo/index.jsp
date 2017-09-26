<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<html>
<head>
<title>系统参数设置</title>
<script type="text/javascript">
	$(function(){
		check();
		doinit();
		$("#myTable tr td input[name='missPwd']").click(function(){
			if($("#myTable tr td input[name='missPwd']:checked").val()=='1'){
				$('#inputSet').css("display","block");
			}else{
				$('#inputSet').css("display","none");
			}
		});
		$("#myTable tr td input[name='outPwd']").click(function(){
			if($("#myTable tr td input[name='outPwd']:checked").val()=='1'){
				$('#outPwd_div').css("display","block");
			}else{
				$('#outPwd_div').css("display","none");
			}
		});
	});
	function doinit(){
		var requestUrl = contextPath + "/tfd/system/sysinfo/copyright/act/SysInfoAct/getSysInfoList.act";
		$.ajax({
 			url:requestUrl,
 			dataType:"json",
 			async:false,
 			error:function(e){
 				alert(e.message);
 			},
 			success:function(data){
 				$("input[name='updateName']").get(data.updateName-1).checked = true;
 				$("input[name='initPwd']").get(data.initPwd-1).checked = true;
 				$("input[name='outPwd']").get(data.outPwd-1).checked = true;
 				if(data.outPwd==1){
 					$('#outPwd_div').css("display","block");
 				}
 				$("input[name='isAbc']").get(data.isAbc-1).checked = true;
 				$("input[name='missPwd']").get(data.missPwd-1).checked = true;
 				if(data.missPwd==1){
 					$('#inputSet').css("display","block");
 				}
 				$("input[name='findPwd']").get(data.findPwd-1).checked = true;
 				$("input[name='remberUser']").get(data.remberUser-1).checked = true;
 				$("input[name='moreLogin']").get(data.moreLogin-1).checked = true;
 				$("input[name='comWithPhone']").get(data.comWithPhone-1).checked = true;
 				$("input[name='useKey']").get(data.useKey-1).checked = true;
 				$("input[name='domainLogin']").get(data.domainLogin-1).checked = true;
 				$("input[name='userIp']").get(data.userIp-1).checked = true;
 				$("input[name='updateName']").get(data.updateName-1).checked = true;
 				$("input[name='remberStatus']").get(data.remberStatus-1).checked = true;
 				var pwdWidth = data.pwdWidth;
 				var widthArr = pwdWidth.split("-");
 				$("input[name='pwdWidth1']").val(widthArr[0]);
 				$("input[name='pwdWidth2']").val(widthArr[1]);
 				$("input[name='pwdCycle']").val(data.pwdCycle);
 				$("input[name='missNum']").val(data.missNum);
 				$("input[name='missTime']").val(data.missTime);
 				$("#sysConfigId").val(data.sysConfigId);
 			}
 		});
	}
	function updateInfo(){
		var para =$("#form1").serialize();
		var requestUrl = contextPath + "/tfd/system/sysinfo/copyright/act/SysInfoAct/updateSysConfig.act";
		$.ajax({
 			url:requestUrl,
 			dataType:"json",
 			data:para,
 			async:false,
 			error:function(e){
 				alert(e.message);
 			},
 			success:function(data){
 				if(data!=0){
 					top.layer.msg("保存成功");
 				}
 			}
		});
	}
	function check(){
		$('#form1').bootstrapValidator({
			message: '这不是一个有效的值',
			container: 'tooltip',
		    feedbackIcons: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
			fields: {
				pwdCycle: {
		            validators: {
		            	container: 'popover',
		            	integer: {
		                    message: '只能为整数'
		                }
		            }
		       },
		       pwdWidth: {
		            validators: {
		            	container: 'popover',
		                integer: {
		                    message: '只能为整数'
		                }
		            }
		        },
		        missNum: {
		            validators: {
		            	container: 'popover',
		                integer: {
		                    message: '只能为整数'
		                }
		            }
		        },
		        missTime: {
		            validators: {
		            	container: 'popover',
		                integer: {
		                    message: '只能为整数'
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
		     updateInfo();
			});
	}
</script>
<style type="text/css">
	body,html{
		width:100%;
		height:100%;
		margin:0px;
		padding:0px;
		font-size:13px;
	}
	.td1{width:15%;color:#000;font-weight:200;height:30px;line-height:30px;}
	.td2{width:30%;height:30px;line-height:30px;}
</style>
</head>
<body>
<form   method="post" name="form1" id="form1" class="form-horizontal" >
<input type="hidden" id="sysConfigId" name="sysConfigId" />
<div style="width:90%;margin-left:5%;margin-top:30px;" >
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
        系统参数设置
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="Table table-striped" id="myTable"  style="width: 100%">
   <tr style="background-color:#CCC;height:30px;line-height:30px;" >
<td class="td1" align="center" >选项</td>
<td class="td2" align="center" >参数</td>
<td align="center" >备注</td>
</tr>
<tr>
<td class="td1" >是否允许用户修改用户名</td>
<td class="td2" >
	<label class="checkbox-inline">
      <input type="radio" name="updateName" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="updateName" 
         value="2"> 否
   </label>
</td>
<td>如果否，则不允许用户在控制面板中修改用户名，且非OA管理员在系统管理-用户管理中也不允许修改用户名</td>
</tr>
<tr>
<td class="td1">初始密码登录修改密码</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="initPwd" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="initPwd" 
         value="2"> 否
   </label>
</td>
<td>用户用初始密码登录需修改密码</td>
</tr>
<tr>
<td class="td1">密码定时过期</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="outPwd" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="outPwd" 
         value="2"> 否
   </label>
   <div id="outPwd_div" style="display:none;" ><div class="col-xs-4 form-group" >
   <input type="text" class="form-control input-sm" style="float:left;" name="pwdCycle" /></div><span>天后过期</span>
   </div>
</td>
<td>如果超过了密码的有效期，则在用户登录时将强制用户修改密码。</td>
</tr>
<tr>
<td class="td1">密码强度</td>
<td class="td2" >
	<div><span style="float:left;" >密码长度：</span><div class="col-xs-4 form-group" ><input type="text" class="form-control input-sm" style="float:left;" name="pwdWidth1" /></div><span style="float:left;" >&nbsp;—&nbsp;</span><div class="col-xs-4 form-group" ><input type="text" style="float:left;" class="form-control input-sm" name="pwdWidth2" /></div></div>
</td>
<td>设置密码强度，以保证密码的安全性。</td>
</tr>
<tr>
<td class="td1">是否包含字母和数字</td>
<td class="td2" >
	<label class="checkbox-inline">
     <input type="radio" name="isAbc" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="isAbc" 
         value="2"> 否
   </label>
</td>
<td>设置密码强度，以保证密码的安全性。</td>
</tr>
<tr>
<td class="td1">登录错误次数限制</td>
<td class="td2" >
	<div>
		<label class="checkbox-inline">
     <input type="radio" name="missPwd" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="missPwd" 
         value="2"> 否
   </label>
	</div>
	<div id="inputSet" style='display:none;' >
		<span style="float:left;" >登陆错误重试</span><div class="col-xs-4 form-group" ><input class="form-control input-sm" style="float:left;" type="text" name="missNum" /></div><span style="float:left" >次后</span><div class="col-xs-4 form-group" ><input class="form-control input-sm" style="float:left;" type="text" name="missTime" /></div><span style="float:left;" >分钟内禁止再次登陆</span>
	</div>
</td>
<td>如果选择“是”，则登录错误重试数次后会被限制数分钟内不能登录。</td>
</tr>
<tr>
<td class="td1">找回OA登录密码</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="findPwd" 
         value="1" checked> 开启
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="findPwd" 
         value="2"> 关闭
   </label>
</td>
<td>是否允许用户在登录密码错误时，自己根据在“电子邮件 >> Internet邮箱”中设置的“电子邮件外发默认邮箱”找回OA登录密码。（admin和使用USB Key的用户将不能通过该功能找回OA登录密码。）</td>
</tr>
<tr>
<td class="td1">允许登录时记忆用户名</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="remberUser" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="remberUser" 
         value="2"> 否
   </label>
</td>
<td>登录界面记忆上次成功登录的用户名可以方便用户登录，但可能会带来安全隐患。</td>
</tr>
<tr>
<td class="td1">是否允许多人用同一帐号同时登录</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="moreLogin" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="moreLogin" 
         value="2"> 否
   </label>
</td>
<td>如果允许同一账号在同一时间允许多人登陆选择是，不允许选择否。</td>
</tr>
<tr>
<td class="td1">是否允许移动版与电脑版同时登录</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="comWithPhone" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="comWithPhone" 
         value="2"> 否
   </label>
</td>
<td>如果允许移动版和电脑版同时登录选择是,不允许选择否。</td>
</tr>
<tr>
<td class="td1">是否启用动态密码卡</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="useKey" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="useKey" 
         value="2"> 否
   </label>
</td>
<td>购买了动态密码卡的用户请选中此项启用后，在登录界面需要输入动态密码才可以登录</td>
</tr>
<tr>
<td class="td1">是否使用域登录</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="domainLogin" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="domainLogin" 
         value="2"> 否
   </label>
</td>
<td>如果使用域登录，则通过域检查用户的用户名和密码是否有效。</td>
</tr>
<tr>
<td class="td1">显示用户登录IP</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="userIp" 
         value="1" checked> 不显示
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="userIp" 
         value="2"> 仅管理员可见
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="userIp" 
         value="3"> 所有用户可见
   </label>
</td>
<td>全部人员列表和用户资料是否显示用户最近一次登录的IP。如显示IP，用户多时可能会导致用户列表加载缓慢。</td>
</tr>
<tr>
<td class="td1">记忆在线状态</td>
<td class="td2" >
<label class="checkbox-inline">
     <input type="radio" name="remberStatus" 
         value="1" checked> 是
   </label>
   <label class="checkbox-inline">
      <input type="radio" name="remberStatus" 
         value="2"> 否
   </label>
</td>
<td>用户重新登录后是否记忆上次设置的在线状态(如忙碌、离开等)。</td>
</tr>
</table>
   </div>
   </div>
   <div align="center" >
   	<input type="submit" id="btn_ok" class="btn btn-primary" value="确定" >
   </div>
   </div>
   </form>
</body>
</html>