<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/system/returnapi/api.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF">
	<title>Insert title here</title> <script
		src="/tfd/system/jsall/jmobile/jquery.mobile-1.4.1.js"></script>
	<link rel="stylesheet"
		href="/tfd/system/jsall/jmobile/jquery.mobile-1.4.1.css"></link>
</head>
<style>
.form-group .ui-input-text {
	margin: 0px 0px;
}
</style>
<script>
function addUnit(){
	var url = "<%=contextPath%>/tfd/system/register/act/RegisterAct/reisterAct.act";
	var para=$("#unitform").serialize();
	para+="&accountId="+$("#accountId").val()+"&password="+$("#password").val();
	alert(para);
	$.ajax({
		url:url,
		dataType:"text",
		async:false,
		data:para,
		success:function(data){
			if(data!=0){
				alert("注册成功");
			}
		}
	});
}
function checkorg(){
	var status;
	var url = "<%=contextPath%>/tfd/system/unit/org/act/UnitAct/checkorgNameAct.act";
		$.ajax({
			url : url,
			dataType : "text",
			async : false,
			data : {
				orgName : $("#orgName").val()
			},
			success : function(data) {
				if (data != 0) {
					status = false;
				} else {
					status = true;
				}
			}
		});
		return status;
	}
</script>
<body>
	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1>公司注册</h1>
		</div>

		<div data-role="content">
			<div align="center" style="margin-top: 20px;">
				<form name="unitform" id="unitform" class="form-horizontal">
					<div class="list-group">
						<a class="list-group-item active">组织机构信息</a>
						<div class="list-group-item" style="height: 55px;">
							<div style="float: left; width: 80px; padding-top: 8px;">单位名称:</div>
							<div class="col-xs-8 form-group">
								<input id="orgName" name="orgName" type="text"
									class="form-control">
							</div>
						</div>
						<div class="list-group-item" style="height: 55px;">
							<div style="float: left; width: 80px; padding-top: 8px;">单位电话:</div>
							<div class="col-xs-8 form-group">
								<input id="orgTel" name="orgTel" type="text"
									class="form-control">
							</div>
						</div>
						<div class="list-group-item" style="height: 55px;">
							<div style="float: left; width: 80px; padding-top: 8px;">单位传真:</div>
							<div class="col-xs-8 form-group">
								<input id="orgFax" name="orgFax" type="text"
									class="form-control">
							</div>
						</div>
						<div class="list-group-item" style="height: 55px;">
							<div style="float: left; width: 80px; padding-top: 8px;">单位地址:</div>
							<div class="col-xs-8 form-group">
								<input id="orgAdd" name="orgAdd" type="text"
									class="form-control">
							</div>
						</div>
						<div class="list-group-item" style="height: 55px;">
							<div style="float: left; width: 80px; padding-top: 8px;">单位邮编:</div>
							<div class="col-xs-8 form-group" style="margin: 0px 0px;">
								<input id="orgPost" name="orgPost" type="text"
									class="form-control">
							</div>
						</div>
						<div class="list-group-item" style="height: 55px;">
							<div style="float: left; width: 80px; padding-top: 8px;">电子邮箱:</div>
							<div class="col-xs-8 form-group">
								<div class="input-group">
									<span class="input-group-addon">@</span> <input type="text"
										class="form-control" placeholder="xxxx@163.com" id="orgEmail"
										name="orgEmail">
								</div>
							</div>
						</div>
						<br>
							<button class="btn btn-primary" type="submit" disabled="disabled">
								<a align="center" href="#pagetwo">确 定 </a>
							</button>
					</div>
				</form>
			</div>
		</div>

	</div>

	<div data-role="page" id="pagetwo">
		<div data-role="header">
			<a href="#" data-transition="fade" data-direction="reverse"
				data-role="button" data-rel="back"> 返回 </a>
			<h1>设置管理员账号</h1>
		</div>

		<div data-role="content">
			<div class="list-group-item" style="height: 55px;">
				<div style="float: left; width: 120px; padding-top: 8px;">管理员账号:</div>
				<div class="col-xs-8 form-group">
					<input id="accountId" name="accountId" type="text"
						class="form-control">
				</div>
			</div>
			<div class="list-group-item" style="height: 55px;">
				<div style="float: left; width: 120px; padding-top: 8px;">管理员密码:</div>
				<div class="col-xs-8 form-group">
					<input id="password" name="password" type="password"
						class="form-control">
				</div>
			</div>
			<a align="center" href="javascript:addUnit();"><button
					class="btn btn-primary">确 定</button> </a>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#unitform').bootstrapValidator({
				container : 'tooltip',
				message : '这不是一个有效的值',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					orgName : {
						validators : {
							notEmpty : {
								message : '单位名称不能为空！'
							},
							callback : {
								message : '该单位已存在，请改名',
								callback : function(value, validator) {
									if($("#orgName").val()!=""){
		                        		return checkorg();	
		                        	}else{
		                        		return true;
		                        	}
								}
							}
						}
					},
					orgEmail : {
						validators : {
							emailAddress : {
								message : '电子邮地址有错误！'
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
				}); 
		})
	</script>
</body>
</html>