
function doinit(){
	getTable();
}

function getTable(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAddressByTimeAndDept.act";
	$.ajax({
		url:requestUrl,
		data:{date:date,accountId:accountId},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if($.isEmptyObject(data)){
				$(".content-body").hide();
				$(".MessageBox").show();
			}else{
				$(".content-body").show();
				$(".MessageBox").hide();
				$.each(data,function(index,data){
					var html = "";
					html += "<tr><td>"+(index+1)+"</td>";
					html += "<td>"+data.registTime+"</td>";
					html += "<td>"+data.address+"</td>";
					//html += "<td><div id='attach"+index+"Div' name='attach"+index+"Div' ></div></td>";
					html += "<td><a href=\"javascript:void(0);\" onclick=\"javascript:showDetail('"+data.attendId+"');\" >查看</a></td>";
					$("#content").append(html);
					//readAttachDiv(data.pictrue,"attach"+index);
				});
			}
		}
	});
}

function showDetail(attendId){
	var url = contextPath + "/system/attend/detail.jsp?id=" + attendId;
    $("#modal-body").html("<iframe id=\"modaliframe\"  name=\"modaliframe\" frameborder=\"0\"></iframe>");
    $("#modaliframe").attr("src",url);
    $("#div-modal-dialog").width(455);
    $("#modaliframe").width(450);
    $("#modaliframe").height(320);
    $('#myModals').modal({backdrop: 'static', keyboard: false});
    $('#myModals').modal('show');
}
