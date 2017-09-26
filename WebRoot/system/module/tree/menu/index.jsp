<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link href="<%=contextPath%>/system/jsall/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="<%=contextPath%>/system/jsall/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="<%=contextPath%>/system/jsall/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
  <script type="text/javascript">
  function openurl(url)
  {
	location.href(url);  
  }
        $(function ()
        {   
            $("#tree1").ligerTree({ 
            url:'<%=contextPath%>/tfd/system/menu/act/SysMenuAct/getSysMeunJson.act',
            idFieldName :'id',
            parentIDFieldName :'pid',
            checkbox:false,
            isExpand:false,
            onClick:onClick
            }
            );
            function onClick(node){
            	var url = node.data.url;
            	if(url!=null && url!="" && url!="null"){
            		window.open("http://"+url);
            	}
            }
        });
    </script>
</head>
<body style="padding:10px">   
    <div style="width:200px; height:300px; margin:10px; float:left; border:1px solid #ccc; overflow:auto;  ">
    <ul id="tree1"></ul>
    </div> 
 
        <div style="display:none">
    <!--  数据统计代码 --> 
    </div>
</body>
</html>
