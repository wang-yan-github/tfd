<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
    <html>
    <head>
  <script type="text/javascript">
   function msg(){
	   
	   layer.alert('身份验证错误！',{icon: 1,
		    closeBtn: 0},function(yes){
		   location.href=contextPath+"/";
	   });
	   }
</script>
    <title></title>
    </head>
<body onload="msg()">
</body>
</html>
