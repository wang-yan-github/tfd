<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<html>
<head>
  <title>拖拽</title>
  <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
  <script>
	 $(function(){
		var divX=$("#div").offset().left;
		var divY=$("#div").offset().top;
		var mouseX=0;
		var mouseY=0;
		var isDrag=false;
		$("#div").on("mousedown",function(e){
			$(this).css("cursor","move");
		 	isDrag=true;
			mouseX=e.pageX;
			mouseY=e.pageY;
		});
		$("#div").on("mouseup",function(e){
			$(this).css("cursor","default");
		 	isDrag=false;
		});
		$("#div").on("mouseout",function(e){
			$(this).css("cursor","default");
		 	isDrag=false;
		});
		$("#div").on("mousemove",function(e){
			if(isDrag){
				var distanceX=mouseX-divX;
				var distanceY=mouseY-divY;
				divX=e.pageX-distanceX;
				divY=e.pageY-distanceY;
			 $(this).css({
			 "top":divY+"px",
			 "left":divX+"px"
			 });

				mouseX=e.pageX;
				mouseY=e.pageY;
			}
		});
	 });
  </script>
</head>
<body>
  <div id="div" style="width:100px;height:100px;background-color:red;position:absolute;"></div>
</body>
</html>
