<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>会议纪要查询</title>
	<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/metting/summary/js/querysummary.js"></script>
<style type="text/css">
#bigdiv {
	width: 60%;
}
</style>
</head>
<body>
<body class="bodycolor">
	<div align="center">
		<div id="bigdiv">
			<div class="list-group-item"  style="padding: 0px;cursor: auto;">
<a style="cursor: auto;" class="list-group-item active">会议纪要查询</a>
					<form enctype="multipart/form-data" id="summaryform" name="summaryform">
						<table class="table table-striped table-condensed">
							<tr>
								<td >会议名称：</td>
								<td><div class="col-xs-5">
										<input type="text" id="meetingName" name="meetingName" class="form-control "  >
										</div></td>
							</tr>
							<tr>
								<td>申请人:</td>
								<td>
								<div class="col-xs-5">
								<textarea rows="3" cols="40" name="askStaff" id="askStaff" style="display: none;" ></textarea>
								<textarea rows="3" cols="40" name="userName" readonly="readonly" id="userName" class="form-control"></textarea></div>
								<div style="margin-top: 30px;">
								<a href="javascript:void(0);" onclick="userinit(['accountId','userName'],true);">选择</a>
								</div>
								</td>
								</tr>
							<tr>
								<td >关键词1：</td>
								<td><div class="col-xs-5">
										<input type="text" id="contentone" name="contentone" class="form-control "  >
										</div></td>
							</tr>
							<tr>
								<td >关键词2：</td>
								<td><div class="col-xs-5">
										<input type="text" id="contenttwo" name="contenttwo" class="form-control "  >
										</div></td>
							</tr>
							<tr>
								<td >关键词3：</td>
								<td><div class="col-xs-5">
										<input type="text" id="contentthree" name="contentthree" class="form-control "  >
										</div></td>
							</tr>
							<tr align="center">
								<td colspan="2" ><input
									type="button" value="确定" class="btn btn-primary"
									onclick="querysummary();">&nbsp;&nbsp; <input type="reset"
									value="重置" class="btn btn-default">&nbsp;&nbsp;</td>
							</tr>
						</table>
					</form>
			</div>
		</div>
	</div>
	<div id="table" style="display: none;">
		<div>
			<input type="button" value="返回查询页面" class="btn btn-primary"
				onclick="returnquery();">
			<div>&nbsp;</div>
		</div>
		<div id="myTable" name="myTable"></div>
	</div>
	<div id="modaldialog"></div>
</body>
</body>
</html>