<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<% String orgId = request.getParameter("orgId");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>机构设置</title>
<script type="text/javascript">
var orgId="<%=orgId%>";
function doinit()
{
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/getOrgConfigByIdAct.act";
	$.ajax({
		url:url,
		data:{orgId:orgId},
		dataType:"json",
		async:false,
		success:function(data){
			for(var name in data){
				$("#"+name).val(data[name]);
			}
	}
	});
}
function save()
{
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/updateOrgConfigAct.act";
	$.ajax({
		url:url,
		data:{orgId:orgId,orgName:$("#orgName").val(),orgAdmin:$("#orgAdmin").val()},
		dataType:"json",
		async:false,
		success:function(data){
			if(data==1)
				{
				layer.msg("修改成功！");
				window.location.href=contextPath+"/system/unit/orgconfig/index.jsp";
				}
	}
	});
	}

function checkOrg(){
	var status;
	var url = "<%=contextPath %>/tfd/system/unit/org/act/UnitAct/checkorgConfigAct.act";
	$.ajax({
		url:url,
		data:{orgName:$("#orgName").val()},
		dataType:"text",
		async:false,
		success:function(data){
			if(data!=0){
				status=false;
			}else{
				status=true;
			}
	}
	});
	return status;
}
</script>
<body onload="doinit();">
<form name="form1" id="form1" class="form-horizontal">
<div align="center">
<div class="panel panel-info" style="width: 80%" >
   <div class="panel-heading">
      <h5 class="panel-title">
      组织机构修改
      </h5>
   </div>
   <div class="panel-body">
<table class="table table-striped">
<tr>
<td width="15%;">机构名称：</td>
<td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="orgName" name="orgName" class="form-control" /></div>
</td>
</tr>
<tr>
<td width="15%;">机构管理员：</td>
<td>
<td>
<div class="col-xs-4 form-group">
<input type="text" id="orgAdmin" name="orgAdmin" class="form-control"/></div>
</td>
</tr>
</table>
</div>
</div>
<div align="center">
<button type="submit"  class="btn btn-info" disabled="disabled">确定</button>
&nbsp;&nbsp;
<button type="button" class="btn btn-default" onclick="history.back();">返回</button>
</div>
</div>
</form>
 <script type="text/javascript">
$(document).ready(function() {
	$('#form1').bootstrapValidator({
		message: 'This value is not valid',
		container: 'tooltip',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			orgName: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '机构名称不能为空 ！'
                    }, callback: {
                        message: '机构名称已存在，请改名',
                        callback: function(value, validator) {
                        	return checkOrg();
                        }
                    }
                }
            },
            orgAdmin: {
                validators: {
                	container: 'popover',
                    notEmpty: {
                        message: '机构管理员不能为空 ！'
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
	    save();
		}); 
});
</script>
</body>
</html>