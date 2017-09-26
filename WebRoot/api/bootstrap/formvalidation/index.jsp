<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FormValidation验证框架 +bootstrap样式=表单验证</title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>

<script>
	
	$(function(){
		$("form").formValidation({
			//结合bootstrap
			framework:"bootstrap",
			//验证错误提示方式
			err:{
				container:"tooltip"
			},
			//验证结果图标
			icon: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
		    fields:{
		    	name:{
		    		trigger:"keyup",
		    		validators:{
		    			notEmpty:{
		    				message:"请填写！"
		    			}
		    		}
		    	},
		    }
		})
		.on("success.form.fv",function(e){//表单验证成功
			e.preventDefault();//阻止自动提交
			
			//自定义提交方式
			var $form=$(e.target);
			
		});
	});
</script>
</head>

<body>
	<h2>FormValidation验证框架 +bootstrap样式=表单验证</h2>
	<h4>API：<a href="http://formvalidation.io/">http://formvalidation.io/</a></h4>
	<h4>示例：</h4>
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-xs-2 control-label">名称</label>
			<div class="col-xs-5">
				<input class="form-control" name="name"/>
			</div>
		</div>
		<div class="form-group">
			<br/>
			<div class="col-xs-5 col-xs-offset-2">
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
		</div>
	</form>
</body>
</html>