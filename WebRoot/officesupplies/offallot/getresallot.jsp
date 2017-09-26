<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% String userId =request.getSession().getAttribute("USER_ID").toString(); %>
<%String resId=request.getParameter("resId"); %>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offallot/js/getresallot.js"></script>
<title>办公用品调拨</title>
<script type="text/javascript">
var userId="<%=userId%>";
var resId="<%=resId%>";
</script>
</head>
<body>
<div class="panel panel-info" style="width:94%;margin-left: 3%;margin-top: 1%;">
   <div class="panel-heading">
      <h3 class="panel-title">
       调拨
      </h3>
      </div>
   <form action="" method="post" id="offresform" class="form-horizontal" name="offresform">
   <div>
   <div class="panel-body" style=" width: 45%; margin-top: 3%;float: left;"  >
   <table class="table table-striped table-condensed">
   <tr>
<td width="25%">办公用品库:</td>
<td>
<div class="col-xs-10 form-group">
<select id="beforelibraryId" name="beforelibraryId" class="form-control " onchange="getlibclassify(1);">
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>办公用品类别:</td>
<td>
<div class="col-xs-10 form-group">
<select id="beforeclassifyId" name="beforeclassifyId" class="form-control " onchange="getresName();" >
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>办公用品:</td>
<td>
<div class="col-xs-10 form-group">
<select id="resId" name="resId" class="form-control " onchange="getresId();" >
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>申请数量:</td>
<td>
<div class="col-xs-10 form-group">
<input type="text" id="beforeStock" name="beforeStock" class="form-control"/>
</div>
</td>
</tr>
    </table>
    </div>
   <div style="float: left; margin-top: 5%;margin-left: 1%">
   <img alt="" src="arrow.png ">
   </div>
   </div>
   <div class="panel-body" style=" width: 45%; margin-top: 5%; float: right;"  >
   <table class="table table-striped" >
   <tr>
<td width="25%">办公用品库:</td>
<td>
<div class="col-xs-10 form-group">
<select id="libraryId" name="libraryId" class="form-control " onchange="getlibclassify(2);">
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
<tr>
<td>办公用品类别:</td>
<td>
<div class="col-xs-10 form-group">
<select id="classifyId" name="classifyId" class="form-control " >
<option selected="selected" value="">请选择</option>
</select>
</div>
</td>
</tr>
    </table>
   </div>
      </div>
      <div align="center" style="float:left; width:100%;">
  <button type="submit" name="send" value="" class="btn btn-primary ">调拨</button>
 </div>
   </form>
    <script type="text/javascript">
   $(document).ready(function() {
 $("#offresform").bootstrapValidator({
 message: 'Pas valide',
 container: 'tooltip',
 feedbackIcons: {
     valid: 'glyphicon glyphicon-ok',
     invalid: 'glyphicon glyphicon-remove',
     validating: 'glyphicon glyphicon-refresh'
 },
 fields: {
	 beforelibraryId: {
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },beforeclassifyId: {
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },resId:{
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },beforeStock:{
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             },integer:{
            	 message:'请填写整数'
             }
         }
     },libraryId:{
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
             }
         }
     },classifyId:{
         validators: {
        	 container: 'popover',
             notEmpty: {
                 message: '不能为空'
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
     addallot();
	}); 
 });
 </script>
</body>
</html>