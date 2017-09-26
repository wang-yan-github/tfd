<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=contextPath%>/system/code/js/index.logic.js"></script>
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
.list{position:absolute;top:4%;left:5%;width:30%;height:85%;}
.add_content,.update_content,.child_add_content,.child_content,.child_update_content{position:absolute;width:50%;height:85%;top:9%;right:8%;display:none;}
</style>
</head>
<body>
	<div class="title" ><img alt="" src="/tfd/system/styles/images/code/system.png" width="24" height="24" /><span style="position:relative;top:4px;left:10px;">分类码管理</span></div>
	<div class="list" >
		<button type="button" style="margin-bottom:5px;float:right;" id="add" class="btn btn-success">添加主类</button>
		<div id="myTable" >
		</div>
	</div>
	<div class="add_content"  >
	<form id="form1" name="form1" class="form-horizontal" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         添加代码主分类
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
<tr>
<td width="15%">分类名称:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="add-name" name="add_name" ></div></td>
</tr>
<tr>
<td width="15%">分类编码:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="add-value" name="add_value" /></div></td>
</tr>
</table>
</div>
</div>
 <div align="right">
  <input type="submit"  id="btn_add" class="btn btn-primary" value="确定" /> 
<button type="button"  class="btn btn-default btn_back">返回</button>
   </div>
   </form>
	</div>
	<!--编辑-->
	<div class="update_content" >
	<form id="form2" name="form2" class="form-horizontal" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         编辑代码主分类
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
<tr>
<td width="15%">分类名称:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="update-name" name="update_name"  /></div><input type="hidden" id="update-id" /></td>
</tr>
<tr>
<td width="15%">分类编码:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="update-value" name="update_value" /></div></td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <input type="submit"  id="btn_update" class="btn btn-primary" value="确定" />
<button type="button" class="btn btn-default btn_back">返回</button>
   </div>
   </form>
	</div>
	<!-- 子集列表添加 -->
<div class="child_add_content" >
<form id="form3" name="form3" class="form-horizontal" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         添加代码分类
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped"  >
   <tr>
<td width="15%">上级分类:</td>
<td><div class="col-xs-8 form-group"  id="select-parent-add" ></div><input type="hidden" id="update-id" /></td>
</tr>
<tr>
<td width="15%">分类名称:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="child-add-name" name="child_add_name"  /></div><input type="hidden" id="update-id" /></td>
</tr>
<tr>
<td width="15%">分类编码:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="child-add-value" name="child_add_value" /></td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <input type="submit" id="btn_add_child"  class="btn btn-primary" value="确定" />
<button type="button" class="btn btn-default btn_back_child">返回</button>
   </div>
   </form>
</div>
<!-- 子集列表 -->
<div class="child_content" >
   <div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         代码分类<span style=""><button type="button" style="position:absolute;right:1%;top:0.5%;" id="add_child" class="btn btn-success">添加子类</button></span>
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped" id="trtitle"   >
</table>
</div>
</div>
</div>
<!-- 子集修改 -->
<div class="child_update_content" >
<form id="form4" name="form4" class="form-horizontal" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         修改代码分类
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
   <input type='hidden' id='child-add-id' />
   <table class="table table-striped"  >
   <tr>
<td width="15%">上级分类:</td>
<td><div class="col-xs-8 form-group"   id="select-parent-update" ></div><input type="hidden" id="child-update-id" /></td>
</tr>
<tr>
<td width="15%">分类名称:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="child-update-name" name="child_update_name"  /></div></td>
</tr>
<tr>
<td width="15%">分类编码:</td>
<td><div class="col-xs-8 form-group" ><input class="form-control " type="text" id="child-update-value" name="child_update_value" /></div></td>
</tr>
</table>
</div>
</div>
 <div align="right">
<input type="submit" id="btn_update_child"  class="btn btn-primary" value="确定" />
<button type="button" class="btn btn-default btn_back_child">返回</button>
   </div>
   </form>
</div>
</body>
<script type="text/javascript">
$('#form1').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		add_name: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类名称不能为空'
                }
            }
        },
        add_value: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类值不能为空'
                }
            }
        }
	}
}).on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     btn_add();
});
$('#form2').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		update_name: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类名称不能为空'
                }
            }
        },
        update_value: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类值不能为空'
                }
            }
        }
	}
}).on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     btn_update();
});
$('#form3').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		child_add_name: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类名称不能为空'
                }
            }
        },
        child_add_value: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类值不能为空'
                }
            }
        }
	}
}).on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     btn_add_child();
});
$('#form4').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		child_update_name: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类名称不能为空'
                }
            }
        },
        child_update_value: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '代码分类值不能为空'
                }
            }
        }
	}
}).on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     btn_update_child();
});
</script>
</html>