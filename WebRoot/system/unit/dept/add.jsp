<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门添加</title>


<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" >
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/dept.css"></link>

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/RegexUtil.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/unit/dept/js/add.logic.js"></script>
<style>
	#dropdown-dept-tree .dropdown-toggle{display:none !important;}
	#dropdown-dept-tree .dropdown-menu{width:100%;}
	#dept-tree{
		max-height:300px;
		overflow-x:hidden;overflow-y:auto;
	}
</style>
</head>
<body>
	<br/>
	<div class="container">
		<form class="form-horizontal" id="dept-form">
			<input name="orgLeaveId" type="hidden" value="0"/>
			<input name="deptLead" id="deptLead" type="hidden"/>
			<input name="parentDeptLongId" type="hidden" value="0"/>
			<input name="parentDeptLongName" type="hidden"/>
			
			<div class="list-group-item well with-header  with-footer">
				<div class="header bg-palegreen" id="header-dept-add">添加部门</div>
				<table class="table table-striped table-condensed">
					<tr>
						<td width="150px;">上级部门：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input class="form-control" name="orgLeaveName" readonly="readonly" value="无" />
								<div class="dropdown" id="dropdown-dept-tree">
									<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
									</button>
									<ul class="dropdown-menu">
										<li>
											<ul id="dept-tree" class="ztree"></ul>
										</li>
									</ul>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>部门名称：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input name="deptName" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>部门领导：</td>
						<td>
							<div class="col-xs-6 form-group"> 
								<input name="deptLeadUserName" id="deptLeadUserName" class="form-control" readonly="readonly" placeholder="请选择"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>部门电话：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input name="deptTel" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>部门传真：</td>
						<td>
							<div class="col-xs-6 form-group">
								<input name="deptFax" class="form-control" />
							</div>
						</td>
					</tr>
					<tr>
						<td>部门电子邮件：</td>
						<td>
							<div class="col-xs-6 form-group">
								<div class="input-group">
									<span class="input-group-addon">@</span> 
									<input class="form-control" placeholder="xxx@163.com" name="deptEmail"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>部门职能：</td>
						<td>
							<div class="col-xs-6 form-group">
								<textarea name="deptFunction" class="form-control" rows="3"></textarea>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-xs-12">
				<button type="submit" class="btn btn-info center-block">添加</button>
			</div>
		</form>
	</div>
	<div id="modaldialog"></div>
</body>
</html>