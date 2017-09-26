<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<html>
    <head>
    <title>重新登录提示</title>
    <script type="text/javascript">
	    $(function(){
	    	//找到最外层的框架
	 	
	    	layer.alert('请重新登陆！',{icon: 1,
			    closeBtn: 0},function(yes){
			    	window.top.location.replace(contextPath+"/index.jsp");
		   });
	    });
	</script>
</head>
<body>
</body>
</html>
