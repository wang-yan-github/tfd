function doinit()
{
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getTodayRegistById.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			for(var i=0;data.length>i;i++){
				var type = "";
				if(data[i].type=='1'){
					type = "上班";
				}else{
					type = "下班";
				}
				html+="<tr>";
				html+="<td>第"+(i+1)+"次登记</td>";
				html+="<td>"+type+"登记</td>";
				html+="<td>"+data[i].time+"</td>";
				if(data[i].status!='4'){
					var status = "";
					if(data[i].status=='2'){
						status = "<span style=\"color:red\" >迟到</span>";
					}else if(data[i].status=='3'){
						status = "<span style=\"color:red\" >早退</span>";
					}
					html+="<td>"+data[i].registTime.substr(11,8)+status+"</td>";
				}else{
					html+="<td>未登记</td>";
				}
				if(data[i].editable=='true'){
					if(data[i].status=='2'||data[i].status=='3'){
						html+="<td><a href=\"javascript:void(0);\" onclick=\"javascript:updateRegistTime('"+data[i].attendId+"','"+data[i].type+"','"+data[i].attendTimeId+"');\" >重新登记</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"javascript:setRemark('"+data[i].attendId+"')\" data-toggle=\"modal\" data-target=\"#myModal_remark\" >说明情况</a></td>";
					}else if(data[i].status=='4'){
						html+="<td><a href=\"javascript:void(0);\" onclick=\"javascript:addAttend('"+data[i].type+"','"+data[i].attendTimeId+"');\" >登记</a></td>";
					}else{
						html+="<td>已考勤</td>";
					}
				}else if(data[i].status=='1'){
					html+="<td>已考勤</td>";
				}else{
					html+="<td>不在登记时间段</td>";
				}
			}
			$("#content").html(html);
		}
	});
}

function addAttend(type,attendTimeId){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/addAttend.act";
	$.ajax({
		url:requestUrl,
		data:{registType:type,attendTimeId:attendTimeId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("登记成功");
				doinit();
			}
		}
	});
}

function setRemark(attendId){
	$("#attendId").val(attendId);
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendById.act";
	$.ajax({
		url:requestUrl,
		data:{attendId:attendId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#remark").val(data.remark);
		}
	});
}

function updateRemark(){
	var attendId = $("#attendId").val();
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/updateAttend.act";
	$.ajax({
		url:requestUrl,
		data:{attendId:attendId,remark:$("#remark").val()},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				$("#myModal_remark").modal("hide");
				alert("保存成功");
			}
		}
	});
}

function updateRegistTime(attendId,type,attendTimeId){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/updateRegistTime.act";
	$.ajax({
		url:requestUrl,
		data:{attendId:attendId,registType:type,attendTimeId:attendTimeId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				alert("重新登记成功");
				doinit();
			}
		}
	});
}

function setAttendRegist(){
	var requestUrl=contextPath+"/tfd/system/attend/act/AttendAct/getAttendRegistById.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$("#beforeWork").html(data.beforeWork);
			$("#afterWork").html(data.afterWork);
			$("#beforeBack").html(data.beforeBack);
			$("#afterBack").html(data.afterBack);
		}
	});
}