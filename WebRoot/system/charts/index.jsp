<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/charts/index.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/echarts/dist/echarts.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/charts/js/index.logic.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/quickflip/quickflip.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<title>图表分析</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
</style>
<script>
</script>
</head>
<body onload="doinit()" >
	<div class="charts_top" >
		<div class="charts_logo" >
			<img src="<%=imgPath %>/charts/charts.png" width="48" height="48" />
		</div>
		<div class="logo_name" >图表分析</div>
		<div class="charts_modules"  align="center" >
		</div>
		<div class="charts_switch" >
			<img id="charts_more" width="30" height="25" src="<%=imgPath %>/charts/switch.png" ></img>
		</div>
	</div>
	<div class="charts_main" >
		
	</div>
	<table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
	   <tbody><tr class="head-no-title">
	      <td class="left"></td>
	      <td class="center">
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="msg">
	      <td class="left"></td>
	      <td class="center info">
	         <div class="msg-content">暂无图表</div>
	      </td>
	      <td class="right"></td>
	   </tr>
	   <tr class="foot">
	      <td class="left"></td>
	      <td class="center"><b></b></td>
	      <td class="right"></td>
	   </tr>
	   </tbody>
	</table>
</body>
</html>