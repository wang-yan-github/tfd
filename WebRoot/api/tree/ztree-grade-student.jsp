<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<html>
<head>
  <title>ztree</title>
  <link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>

  <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.all-3.5.js"></script>
  <script>
    $(function(){
      var treeSetting={
        data:{
          simpleData:{
            enable:true,
            idKey:"id",
            pIdKey:"pid",
            rootPId:"0"
          },
          key:{
            name:"text"
          }
        },
        async:{
          enable:true,
          url:contextPath+"/api/tree/TreeAct/loadTree.act",
          autoParam:["id"]
        }
      };
      $.fn.zTree.init($("#tree"),treeSetting);
    });
  </script>
</head>
<body>
  <ul class="ztree" id="tree"></ul>
</body>
</html>
