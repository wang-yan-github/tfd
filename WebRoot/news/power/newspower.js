function addpower(status){
	if(status==1){
		$("#powerStatus").val("1");
		$("#powerStatusval").val("新闻无需审批人员");
		$('#newspowerform').bootstrapValidator('revalidateField', 'powerStatusval');
	}else{

		$("#powerStatus").val("2");
		$("#powerStatusval").val("新闻审批人员");
		$('#newspowerform').bootstrapValidator('revalidateField', 'powerStatusval');
	}
}
function insertnewstaff(){
	var url=contextPath+"/news/act/NewsPowerAct/addpowerAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			accountId:$("#accountId").val(),
			powerStatus:$("#powerStatus").val()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('添加成功');
				window.location.reload();	
			}
	}
	});
}
function looknewspower(){
	var requestUrl =contextPath+"/news/act/NewsPowerAct/lookpowerAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			async:false,
			success:function(data){
				var j=1;
				var k=1;
				for(var i=0;i<data.length;i++){
					if(data[i].powerStatus==1){
					var tr="<tr><td align=\"center\" width=\"10%\">"+j+"</td>"+
					"<td align=\"center\" width=\"60%\">"+
					"<div>"+data[i].userName+"</div></td>"+
					"<td align=\"center\" width=\"30%\"><a href=\"javascript:void(0);\" onclick=\"delpower('"+data[i].powerId+"');\" >删除</a></td></tr>";
					$("#dispensestaff").append(tr);
					j++;
					}else{
						var tr="<tr><td align=\"center\" width=\"10%\">"+k+"</td>"+
						"<td align=\"center\" width=\"60%\">"+
						"<div>"+data[i].userName+"</div></td>"+
						"<td align=\"center\" width=\"30%\"><a href=\"javascript:void(0);\" onclick=\"delpower('"+data[i].powerId+"');\" >删除</a></td></tr>";
						$("#approvalstaff").append(tr);
						k++;
					}
				}
			}
		});
}
function delpower(powerId){
	if(confirm("确定删除记录吗？\n")){
	var requestUrl =contextPath+"/news/act/NewsPowerAct/delpowerAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			async:false,
			data:{
				powerId:powerId
			},
			success:function(data){
				if(data!=0){
					parent.layer.msg('删除成功');
					window.location.reload();
				}
			}
		});
	}
}
function userNameval(){
	$('#newspowerform').bootstrapValidator('revalidateField', 'userName');
}
$(function (){
	looknewspower();
});