<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/homepage/index.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/homepage/js/index.logic.js"></script>
<title>首页设置</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
.update_content{width:400px;height:300px;margin-top:50px;margin-left:50px;display:none;}
</style>
<script>
</script>
</head>
<body onload="doinit();" >
	<div class="page_top" >
		<div class="page_logo" >
			<img src="<%=imgPath %>/homepage/page_setting.png" width="48" height="48" />
		</div>
		<div class="logo_name" >首页设置</div>
	</div>
	<div class="page_main" >
		<div class="main_left" >
			<table class="table table-striped" style="width:400px;margin-left:100px;margin-top:50px;" >
			<tr>
			<td width="100px" >模块序号</td>
			<td width="100px" >模块名称</td>
			<td width="100px" >操作</td>
			<td width="100px" >开关</td>
			</tr>
			<tbody id="content" name="content"></tbody>
			</table>
		</div>
		<div class="main_right">
			<div class="update_content">
				<form id="form1" name="form1" class="form-horizontal">
					<div class="list-group" style="margin-bottom: 0px;">
						<a class="list-group-item active">
							<h5 class="list-group-item-heading">编辑模块</h5>
						</a>
						<div class="panel-body" style="border: none; box-shadow: none;">
							<table class="table table-striped">
								<td width="100px">模块名称:</td>
								<td><div class="col-xs-8 form-group">
										<input class="form-control " type="text" id="update-name" name="child_add_name" />
									</div>
									<input type="hidden" id="update-id" /></td>
								</tr>
								<tr>
									<td width="100px">模块开关:</td>
									<td><div class="col-xs-8 form-group">
											<select id="update-isOpen" class="form-control ">
												<option value="1">开</option>
												<option value="0">关</option></select>
										</div></td>
								</tr>
								<tr>
									<td colspan="2" align="right" >
										<input type="submit"  onclick="doUpdate();" class="btn btn-primary" value="确定" /> 
										<button type="button" onclick="doBack();" class="btn btn-default btn_back">返回</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
			</div>
		</div></body>
</html>