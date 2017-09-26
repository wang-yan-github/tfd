$(function(){
	$.ajax({
		url:contextPath+"/sysmanage/regist/act/RegistAct/registeredList.act",
		type:"POST",
		dataType:"json",
		success:function(registeredList){
			for (var i = 0; i < registeredList.length; i++) {
				var detailHtml=
				"<tr>" +
				"	<td>"+registeredList[i].productSn+"</td>"+
				"	<td>"+registeredList[i].productName+"</td>"+
				"	<td>"+registeredList[i].productVersion+"</td>"+
				"	<td>"+registeredList[i].registUnit+"</td>"+
				"	<td>"+registeredList[i].registTime+"</td>"+
				"	<td>"+registeredList[i].registDeadline+"</td>"+
				"	<td>"+registeredList[i].registUserCount+"</td>"+
				"	<td>"+registeredList[i].registImUserCount+"</td>"+
				"	<td>"+registeredList[i].registDiskSn+"</td>"+
				"	<td><a class='download-regist-file' id='"+registeredList[i].id+"'>下载注册文件</a></td>"+
				"</tr>";
				$("#registred-list").append(detailHtml);
			}
		}
	});
	$(document).on("click",".download-regist-file",function(){
		window.open(contextPath+"/sysmanage/regist/act/RegAct/toRegFile.act?id="+$(this).attr("id"));
	});
});