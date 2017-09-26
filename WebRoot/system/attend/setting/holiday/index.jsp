<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<title>分类码管理</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
html{
overflow: hidden;
}

.title{width:150px;height:40px;line-height:40px;font-size:18px;text-align:center;}
.list{position:absolute;top:10px;left:1%;width:45%;height:85%;}
.add_content,.update_content{position:absolute;width:50%;height:85%;top:50px;;right:1%;display:none;}
#attendtable{text-align: center;}
</style>
</head>
<body>
	<div class="list" >
	<div >
		<button type="button" id="add" class="btn btn-success">添加节假日</button>
		<button type="button"  class="btn btn-default btn_back" id="">返回</button>
		</div>
		<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-top: 5px;">
		<a></a>
			<table class="table table-striped" id="attendtable">
			<tr>
			<td width="100px">节假日名称</td>
			<td>起始时间</td>
			<td>结束时间</td>
			<td>操作</td>
			</tr>
			<tbody id="content" name="content"></tbody>
			</table>
		</div>
	</div>
	<div class="add_content"  >
	<form id="form1" name="form1" class="form-horizontal" action="<%=contextPath%>/tfd/system/attend/act/AttendAct/addAttendHoliday.act" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         添加节假日
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
   <tr>
<td width="100px">节假日名称：</td>	
<td><div class="col-xs-8 form-group" ><input class="form-control "  type="text" id="holidayName" name="holidayName" /></div></td>
</tr>
<tr>
<td width="100px">起始日期：</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"  type="text" id="startDate" name="startDate" ></div></td>
</tr>
<tr>
<td width="100px">结束日期：</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" id="endDate" name="endDate" /></div></td>
</tr>

</table>
</div>
</div>
 <div align="right">
  <input type="submit" disabled="disabled" id="addbtn" name="addbtn" class="btn btn-primary" value="确定" /> 
   </div>
   </form>
	</div>
	<!--编辑-->
	<div class="update_content" >
	<form id="form2" name="form2" class="form-horizontal" action="<%=contextPath%>/tfd/system/attend/act/AttendAct/editAttendHoliday.act">
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         编辑节假日
         <input type="hidden" id="holidayId2" name="holidayId2"/>
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
<tr>
<td width="15%">起始日期:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" id="startDate2" name="startDate2" ></div></td>
</tr>
<tr>
<td width="15%">结束日期:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" type="text" id="endDate2" name="endDate2" /></div></td>
</tr>
<tr>
<td width="15%">节假日名称:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control "  type="text" id="holidayName2" name="holidayName2" /></div></td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <input type="submit"   id="btn_update" class="btn btn-primary" value="确定" />
   </div>
   </form>
	</div>
</body>
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
		endDate: {
            validators: {
            	container: 'popover',
            	callback: {
                	message: '结束时间不能小于起始时间！',
                	callback: function(value, validator) {
                		if(value<$("#startDate").val()){
                			return false;
                		}else{
                			return true;
                		}
                	}
                }
            }
        },
        holidayName: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类值不能为空'
                }
            }
        }
	}
})/* .on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');
     addHoliday();
}); */
$('#form2').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		endDate2: {
            validators: {
            	container: 'popover',
            	callback: {
                	message: '结束时间不能小于起始时间！',
                	callback: function(value, validator) {
                		if(value<$("#startDate2").val()){
                			return false;
                		}else{
                			return true;
                		}
                	}
                }
            }
        },
        holidayName2: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类值不能为空'
                }
            }
        }
	}
})/* .on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     editHoliday();
}); */
});
$(function(){
	doint();
	$('#add').click(function(){
	    	$('.update_content').attr("style","display:none");
	    	$('.add_content').attr("style","display:block");
	});
	$(".btn_back").click(function(){
		history.go(-1);
		return false;
	});
})
function doint(){
	var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/getAttendHoliday.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			$.each(data,function(index,data){
				html += "<tr><td>"+data.holidayName+"</td>";
				html += "<td>"+data.startDate+"</td>";
				html += "<td>"+data.endDate+"</td>";
				html += "<td><a href=\"javascript:void(0)\" onclick=\"javascript:toEdit('"+data.holidayId+"');\" >编辑</a>    <a href=\"javascript:void(0)\" onclick=\"delHoliday('"+data.holidayId+"');\" >删除</a></td>";
			});
			$("#content").html(html);
		}
	});
}

function addHoliday(){
	var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/addAttendHoliday.act';
	$.ajax({
		url:requestUrl,
		data:{holidayName:$("#holidayName").val(),startDate:$("#startDate").val(),endDate:$("#endDate").val()},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("添加成功");
				doint();
			}
		}
	});
}

function delHoliday(id){
	var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/delAttendHoliday.act';
	$.ajax({
		url:requestUrl,
		data:{holidayId:id},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("删除成功");
				doint();
			}
		}
	});
}

function toEdit(id){
	$('.update_content').attr("style","display:block");
	$('.add_content').attr("style","display:none");
	var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/getAttendHolidayById.act';
	$.ajax({
		url:requestUrl,
		data:{holidayId:id},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#holidayId2").val(data.holidayId);
			$("#holidayName2").val(data.holidayName);
			$("#startDate2").val(data.startDate);
			$("#endDate2").val(data.endDate);
		}
	});
}

function editHoliday(){
	var requestUrl= '<%=contextPath%>/tfd/system/attend/act/AttendAct/editAttendHoliday.act';
	$.ajax({
		url:requestUrl,
		data:{holidayId:$("#holidayId2").val(),holidayName:$("#holidayName2").val(),startDate:$("#startDate2").val(),endDate:$("#endDate2").val()},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("修改成功");
				doint();
			}
		}
	});
}
</script>
</html>