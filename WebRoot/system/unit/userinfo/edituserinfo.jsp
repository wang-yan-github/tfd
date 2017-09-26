<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String accountId=request.getParameter("accountId");%>
<%String notLogin=request.getParameter("notLogin");%>
<%String type=request.getParameter("type");%>
<html>
<head>
<title>用户信息修改</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectleave/selectleave.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
var accountId='<%=accountId%>';
var type="<%=type%>";
var notLogin="<%=notLogin%>";
if(type=="1")
	{
	alert("保存成功！");
	}
var zNodes;
var deptId;
$(function(){
	$("#notLogin").val(notLogin);
	var requestUrl=contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/getUserInfoByAccountIdAct.act";
	var paramData={accountId:accountId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
	 		var form=data[0];
					for(var name in form){
						var inputEle=$("*[name='"+name+"']");
					var inputEleType=inputEle.attr("type");
					inputEleType=inputEleType==null?"":inputEleType;
					if(inputEleType=="checkbox"){
								if(form[name]==null||form[name]==""){
										inputEle.get(0).checked=false;
								}else{
										inputEle.get(0).checked=true;
								}
					}
					inputEle.val(form[name]);
					}
		}
	});
});
</script>
</head>
<body style="margin-top: 10px;">
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
<ul id="treeDemo" class="ztree" ></ul>
</div>
<div  align="center">
<form name="from1" action="<%= contextPath%>/tfd/system/unit/userinfo/act/UserInfoAct/updateUserInfoAct.act"  style="width: 80%;">
<div>
<div class="widget-header bg-blueberry">
<span class="widget-caption">人员信息修改</span>
</div>
<input id="notLogin" name="notLogin" type="hidden">
<div class="list-group-item"  style="padding: 0px;">
<table class="table table-striped table-condensed" >
<tr>
<td width="100px">账号：</td>
<td><div class="col-xs-10"><input name="accountId" id="accountId" readonly class="form-control"/></div></td>
<td width="100px">姓名：</td>
<td><div class="col-xs-10"><input name="userName" id="userName" class="form-control"/></div></td>
</tr>
<tr>
<td>性别：</td>
<td>
<div class="col-xs-10"><select name="sex" id="sex" class="form-control">
<option value="男">男</option>
<option value="女">女</option>
</select></div>
</td>
<td>部门：</td>
<td>
<div class="col-xs-10"><input name="deptId" id="deptId" type="hidden" class="form-control"/>
<input id="deptName" name="deptName" type="text" readonly value="" onclick="showMenu()" class="form-control"/>
</div><a href="javascript:void(0);" onclick="deptinit(['deptId','deptName'],'true');" style="line-height: 30px;">选择</a>
</td>
</tr>
<tr>
<td>上级领导：</td>
<td><div class="col-xs-10"><input id="leadId" name="leadId" type="hidden" class="form-control"/>
<input id="leadUserName" name="leadUserName" readonly class="form-control"></div>
<a href="javascript:void(0);" onclick="userinit(['leadId','leadUserName'],'true');"style="cursor: pointer;" style="line-height: 30px;">选择</a>

</td>
<td>权限组：</td>
<td><div class="col-xs-10"><input id="postPriv" name="postPriv" type="hidden" class="form-control"/>
<input id="postPrivName" name="postPrivName" readonly class="form-control"/></div>
<a href="javascript:void(0);" onclick="privinit(['postPriv','postPrivName'],'true');" style="line-height: 30px;">选择</a>
</td>
</tr>
<tr>
<td>行政级别：</td>
<td><div class="col-xs-10"><input type="hidden" id="leadLeave" name="leadLeave" class="form-control"/>
<input type="text" id="leadLeaveName" name="leadLeaveName" readonly class="form-control"/>
</div>
<a href="javascript:void(0);" onclick="leaveinit(['leadLeave','leadLeaveName'],'true');" style="line-height: 30px;">选择</a>
</td>
<td>兼职：</td>
<td><div class="col-xs-10"><input id="otherPriv" name="otherPriv" type="hidden" class="form-control" />
<input id="otherPrivName" name="otherPrivName" readonly class="form-control"/></div>
<a href="javascript:void(0);" onclick="leaveinit(['otherPriv','otherPrivName']);" style="line-height: 30px;" >选择</a>
</td>
</tr>
<tr>
<td>家庭住址：</td>
<td><div class="col-xs-10"><input id="homeAdd" name="homeAdd" class="form-control"/></div></td>
<td>家庭电话：</td>
<td><div class="col-xs-10"><input id="homeTel" name="homeTel" class="form-control" /></div></td>
</tr>
<tr>
<td>手机：</td>
<td><div class="col-xs-10"><input id="mobileNo" name="mobileNo" class="form-control"/></div></td>
<td>QQ：</td>
<td><div class="col-xs-10"><input id="qQ" name="qQ" class="form-control"/></div></td>
</tr>
<tr>
<td >电子邮箱：</td>
<td>
<div class="col-xs-10">
		<div class="input-group col-xs-12">
			<span class="input-group-addon"  style="width: 30px;">@</span>
			<input type="text" class="form-control" placeholder="xxx@163.com" id="eMaile" name="eMaile"/>
		</div>
</div>
</td>
<td>工号：</td>
<td><div class="col-xs-10"><input id="workId" name="workId" class="form-control"/></div></td>
</tr>
<tr>
<td>管理范围：</td>
<td>
<div class="col-xs-10"><select name="manageDept" id="manageDept" class="form-control">
<option value="1">本部门</option>
<option value="2">全体</option>
</select></div>
<td>管理部门：</td>
<td>
<div class="col-xs-10"><input id="otherDept" name="otherDept" style="display:none;" class="form-control">
<textarea id="otherDeptName" style="height: 60px;" name="otherDeptName" readonly="readonly" class="form-control"></textarea></div>
<a href="javascript:deptinit(['otherDept','otherDeptName'],'false');" style="line-height: 60px;">添加</a>
</td>
</tr>
</table>
</div>
</div>
<br>
<div align="center">
<button type="submit" class="btn btn-primary">确定</button>
<button type="button" onclick="javascript:history.go(-1);" class="btn btn-default">返回</button></div>
</form>
</div>
<div id="modaldialog"></div>
</body>
</html>