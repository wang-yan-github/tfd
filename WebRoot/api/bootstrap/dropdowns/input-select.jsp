<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/system/returnapi/api-simple.jsp" %>
<html>
<head>
    <title>tfdsys api</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">

	$(document)
	//阻止点击下拉框中的内容(#select)时下拉框被隐藏
    .on('click.bs.dropdown.data-api', '.dropdown #select', function (e) { e.stopPropagation(); })
	//阻止点击input框(#input)时下拉框被隐藏
    .on('click.bs.dropdown.data-api', "#input", function (e) { e.stopPropagation(); })
    //点击input框（#input）时展开下拉框
	.on("click","#input",function(){
		$("#dropdown .dropdown-toggle").trigger("click");
	});
    </script>
    <style>
		#dropdown .dropdown-toggle{display:none !important;}
		#dropdown .dropdown-menu{width:100%;}
    </style>
</head>
<body>

	<form class="form-horizontal">
		<div class="col-xs-6 form-group">
			<input class="form-control" id="input" placeholder="点击选择"/>
			<div class="dropdown" id="dropdown">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				</button>
				<ul class="dropdown-menu">
					<li>
						<div id="select">
							<div>a</div>
							<div>b</div>
							<div>c</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
