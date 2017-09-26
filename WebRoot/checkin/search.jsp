<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<%
	String priv=request.getParameter("priv");
	priv=priv==null?"":priv;
%>
<html>
<head>
    <title>考勤综合查询</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
	<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css"/>
	<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
	<link rel="stylesheet" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css"/>
	
    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/locale/zh-cn.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/checkin/js/search.logic.js"></script>

   <style>
   		#search-box{width:500px;margin:0px auto;margin-top:30px;margin-bottom:15px;}
   		#search-box .box-table .header{
   			background-color:#2DC3E8;color: white;
   			font-weight:bold;text-align: center;font-size:16px;letter-spacing: 1px;
   		}
   		#search-box .box-table td:FIRST-CHILD{text-align:center;}
   		.modal{top:0px !important;}
   		#search-result-alert{text-align: center;}
   </style>
   <script>
   		checkin.priv="<%=priv%>";
   </script>
</head>
<body>
	<div class="container">
	
		<div id="search-box">
			<form id="search-form">
			    <table class="table table-bordered box-table">
			    	<tr>
			    		<td colspan="2" class="header">考勤综合查询</td>
			    	</tr>
			    	<tr class="deptName-box">
			    		<td width="100px">部门</td>
			    		<td>
							<input class="form-control" name="deptName" id="deptName" placeholder="部门名称" />
			    		</td>
			    	</tr>
			    	<tr class="userName-box">
			    		<td>员工</td>
			    		<td>
							<input class="form-control" name="userName" id="userName" placeholder="员工姓名" />
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>类型</td>
			    		<td>
			    			<select name="checkinType" class="form-control">
			    				<option value="">全部</option>
			    				<option value="I">签到</option>
			    				<option value="O">签退</option>
			    			</select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>时间</td>
			    		<td>
			    			<div class="row">
			    				<div class="col-xs-6">
				    				<input class="form-control" name="checkinTimeStart" id="checkinTimeStart" placeholder="起始时间"/>
				    			</div>
				    			<div class="col-xs-6">
				    				<input class="form-control" name="checkinTimeEnd" id="checkinTimeEnd" placeholder="结束时间"/>
				    			</div>
			    			</div>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td colspan="2" style="text-align: center;">
			    			<button type="button" class="btn btn-default" id="ok">确定</button>
			    		</td>
			    	</tr>
			    </table>
			</form>
		</div>
		
		
		
		
		<div id="search-result">
			<table class="table table-bordered">
				<tr>
					<td style="text-align: center;font-weight:bold;letter-spacing: 1px;">查询结果</td>
				</tr>
				<tr>
					<td>
						<div id="search-result-alert">无！</div>
						<div id="checkin-list"></div>
					</td>
				</tr>
			</table>
		</div>		
		<div id="modaldialog"></div>
	</div>
	
</body>
</html>
