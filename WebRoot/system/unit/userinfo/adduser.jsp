<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String accountId=request.getParameter("accountId");%>
<html>
<head>
<title>用户信息修改</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectleave/selectleave.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/org/userinfo.css"></link>
<script type="text/javascript">
function dovalidate()
{
	$('#form1').bootstrapValidator('revalidateField', 'postPrivName');
	}
var accountId="<%=accountId%>";
var zNodes;
var deptId;
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			zNodes=data;
		}
	});
	var Url=contextPath+"/tfd/system/unit/account/act/AccountAct/getAccountJsonByIdAct.act";
	$.ajax({
		url:Url,
		dataType:"json",
		async:false,
		data:{accountId:accountId},
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#postPriv").val(data.userPriv);
			$("#postPrivName").val(data.userPrivName);
		}
	});
});
//部门下拉处理
var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
	}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	vid="";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		vid+=nodes[i].id + ",";
	}
	if (v.length > 0 )
	{
		v = v.substring(0, v.length-1);
		 $("#deptName").val(v);
		
	}
	if (vid.length > 0 )
	{
		vid = vid.substring(0, vid.length-1);
		 $("#deptId").val(vid);
	}
	$('#form1').bootstrapValidator('revalidateField', 'deptName');
}

function showMenu() {
	var cityObj = $("#deptName");
	var cityOffset = $("#deptName").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});  
</script>
</head>
<body style="margin-top: 10px;">
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1000;">
<ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
</div>
<div align="center">
<form name="from1"  id="form1" class="form-horizontal"  action="<%= contextPath%>/tfd/system/unit/userinfo/act/UserInfoAct/addUserInfoAct.act" style="width: 80%;">
<div>
<div class="widget-header bg-blueberry">
<span class="widget-caption">人员信息修改</span>
</div>
<div class="list-group-item"  style="padding: 0px;">
<table class="table table-striped table-condensed" >
<tr>
<td width="100px">账号：</td>
<td><div class="col-xs-10"><input name="accountId" id="accountId" readonly value="<%=accountId%>" class="form-control" /></div></td>
<td width="100px">姓名：</td>
<td><div class="col-xs-10 form-group"><input name="userName" id="userName" class="form-control"/></div></td>
</tr>
<tr>
<td>性别：</td>
<td>
<div class="col-xs-10"><select name="sex" id="sex" class="form-control" >
<option value="男">男</option>
<option value="女">女</option>
</select></div>
</td>
<td>部门：</td>
<td>
<div class="col-xs-10 form-group"><input name="deptId" id="deptId" type="hidden" class="form-control" />
<input id="deptName" name="deptName" type="text" readonly value="" cursor: pointer;" onclick="showMenu();" class="form-control" /></div>
</td>
</tr>
<tr>
<td>上级领导：</td>
<td><div class="col-xs-10"><input id="leadId" name="leadId" type="hidden" class="form-control" />
<input id="leadUserName" name="leadUserName" readonly class="form-control" /></div>
<a onclick="userinit(['leadId','leadUserName'],'true');" style="cursor: pointer;line-height: 30px;">选择</a>

</td>
<td>权限组：</td>
<td><div class="col-xs-10 form-group"><input id="postPriv" name="postPriv" type="hidden" class="form-control" />
<input id="postPrivName" name="postPrivName" readonly class="form-control" />
</div><a onclick="privinit(['postPriv','postPrivName'],'true','dovalidate');" style="line-height: 30px;">选择</a>
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
<input id="otherPrivName" name="otherPrivName" readonly class="form-control" /></div>
<a onclick="leaveinit(['otherPriv','otherPrivName'],'false');" style="line-height: 30px;">选择</a>
</td>
</tr>
<tr>
<td>家庭住址：</td>
<td><div class="col-xs-10"><input id="homeAdd" name="homeAdd" class="form-control" /></div></td>
<td>家庭电话：</td>
<td><div class="col-xs-10"><input id="homeTel" name="homeTel" class="form-control" /></div></td>
</tr>
<tr>
<td>手机：</td>
<td><div class="col-xs-10"><input id="mobileNo" name="mobileNo" class="form-control" /></div></td>
<td>QQ：</td>
<td>
<div class="col-xs-10"><input id="qQ" name="qQ" class="form-control" /></div></td>
</tr>
<tr>
<td>电子邮箱：</td>
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
<div class="col-xs-10"><select name="manageDept" id="manageDept" class="form-control" />
<option value="2">本部门</option>
<option value="1">全体</option>
</select></div>
<td>管理部门：</td>
<td>
<div class="col-xs-10"><input id="otherDpet" name="otherDpet" style="display:none;" class="form-control" />
<textarea id="otherDpetName" name="otherDpetName" class="form-control" /></textarea></div>
<a onclick="deptinit(['otherDpet','otherDpetName']);" style="line-height: 60px;">添加</a>
</td>
</tr>
</table>
</div>
<br>
<div align="center"><button type="submit" class="btn btn-primary"/>确定</button></div>
</div>
</form>
</div>
<div id="modaldialog"></div>
   <script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
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
                        message: '姓名不能为空！'
                    }
                }
            },
			deptName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '部门不能为空！'
                    }
                }
            },
            postPrivName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '角色不能为空！'
                    }
                }
            }
		}
	}).on('success.form.bv',function(e){
		//e.preventDefault();
			
	     // Get the form instance
	  	 //var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     //var bv = $form.data('bootstrapValidator');
	     
	     
		}); 
});
</script>
</body>
</html>