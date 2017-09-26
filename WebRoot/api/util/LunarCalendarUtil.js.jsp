<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys api</title>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/LunarCalendarUtil.js"></script>
   	<script type="text/javascript">
   		window.onload=function(){
   			document.getElementById("convert").onclick=function(){
   				var solar=document.getElementById("solar").value;
   				

   				var date = new Date(solar.replace(/-/g, '/'));
   				var year = date.getFullYear();
   				var month = date.getMonth() + 1;
   				var day = date.getDate();
   				
   				var lunar = new LunarCalendarUtil().calendar.solar2lunar(year, month, day);
   				document.getElementById("lunar").value=lunar.lYear+"-"+lunar.lMonth+"-"+lunar.lDay;
   			}
   		}
   	</script>
   	
   	
</head>
<body>
	阳历<input id="solar" value="2015-10-15"/>
	<input type="button" value="转换" id="convert"/>
	农历<input id="lunar"/>
	
</body>
</html>
