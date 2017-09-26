$(function(){
	//getAddUpdateCalFroms();
	if(type == '2'){
		$('#bottons').css("display","block");
		ii = 1;
	}else if(type == '3'){
		$('#bottons').css("display","block");
		ii = 2;
	}
	getCalById();
	
	parent.$("#btn_edit").unbind('click').click(function(){
		    var url = contextPath + "/calendar/leader/edit.jsp?id=" + calId+"&type=2";
		    parent.$("#modaliframe").attr("src",url);
		    parent.$("#div-modal-dialog").width(700);
		    parent.$("#modaliframe").width(695);
		    parent.$("#modaliframe").height(500);
		    parent.$('#myModals').modal({backdrop: 'static', keyboard: false});
		    parent.$('#myModals').modal('show');
		    parent.$('#btn_edit').hide();
		    parent.$('#btn_delete').hide();
		    parent.$("#btn-save").show();
		});
		parent.$("#btn_delete").click(function(){
			deleteCal(calId);
		});
		parent.$("#btn_finished").click(function(){
			var calId = $("#cal_Id").val();
			var content = $("#diary_content").val();
			var diaryId = $("#d_Id").val();
			var url =  contextPath +  "/calendar/act/CalendarAct/addDiary.act";
			var para = {sid:calId,content:content,diaryId:diaryId};
			$.ajax({
				url:url,
				dataType:"json",
				data:para,
				async:false,
				success:function(data){
					parent.$(".btn_close").trigger("click");
					parent.updateOverStatus(calId,1);
				}
			});
		});
	});
	function getCalById(){
		var url =  contextPath +  "/calendar/act/CalendarAct/selectByIdAct.act";
		var para = {sid:calId};
		var returnData;
		$.ajax({
			url:url,
			dataType:"json",
			data:para,
			async:false,
			success:function(data){
				$("#cal_Id").val(data.id);
				$("#startDate").html(data.startTimeStr);
				$("#endDate").html(data.endTimeStr);
				if(data.userName == ""){
					$("#userName").html("无");
				}else{
					var userName = "";
					for(var i = 0;i<data.userName.length;i++){
						userName += "<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data.userName[i].userId+"')\">"+data.userName[i].name+"</a>,";
					}
					$("#userName").html(userName);
				}
				$("#accountName").html("<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data.fromId+"')\">"+data.accountName+"</a>");
				$("#content").html(data.title);
				var json;
				parent.$("#btn_finished").show();
				if(data.diaryId!=null){
					json = getDiary(data.diaryId);
					if(data.status == '0'){
						$("#status").html("未开始");
						$("#diary_content").val(json.diaryContent);
					}else if(data.status == '1'){
						$("#status").html("已完成");
						parent.$("#btn_finished").hide();
						$("#finishedTr").hide();
						$("#diaryTr").show();
						$("#diaryTd").html("<a style=\"cursor:pointer;\" onclick=\"showdiary('"+json.diaryId+"','')\" >"+json.diaryName+"</a>");
					}else if(data.status == '3'){
						$("#status").html("进行中");
						$("#diary_content").val(json.diaryContent);
					}else if(data.status == '4'){
						$("#status").html("已超时");
						$("#diary_content").val(json.diaryContent);
					}
				}else{
					if(data.status == '0'){
						$("#status").html("未开始");
					}else if(data.status == '1'){
						$("#status").html("已完成");
						parent.$("#btn_finished").hide();
						$("#finishedTr").hide();
						$("#diaryTr").show();
						$("#diaryTd").html("无");
					}else if(data.status == '3'){
						$("#status").html("进行中");
					}else if(data.status == '4'){
						$("#status").html("已超时");
					}
				}
				if(data.isAll=="false"){
					parent.$("#btn_finished").hide();
					$("#finishedTr").hide();
					$("#diaryTr").show();
					if(data.diaryId!=null){
						json = getDiary(data.diaryId);
						$("#diaryTd").html("<a style=\"cursor:pointer;\" onclick=\"showdiary('"+json.diaryId+"','')\" >"+json.diaryName+"</a>");
					}else{
						$("#diaryTd").html("无");
					}
				}
				if(ii==1){
					$("#finishedTr").hide();
					$("#diaryTr").show();
					if(data.diaryId!=null){
						json = getDiary(data.diaryId);
						$("#diaryTd").html("<a style=\"cursor:pointer;\" onclick=\"showdiary('"+json.diaryId+"','')\" >"+json.diaryName+"</a>");
					}else{
						$("#diaryTd").html("无");
					}
				}else if(ii==2){
					$("#finishedTr").hide();
					$("#diaryTr").hide();
					parent.$("#btn_finished").hide();
				}
			}
		});
	}
	function getDiary(diaryId){
		var returnData;
		var url =  contextPath +  "/diary/act/DiaryAct/getIdDiaryAct.act";
		var para = {diaryId:diaryId};
		$.ajax({
			url:url,
			dataType:"json",
			data:para,
			async:false,
			success:function(data){
				$("#d_Id").val(data.diaryId);
				returnData = data;
			}
		});
		return returnData;
	}
	function showdiary(diaryId,userName){
		var url=contextPath+"/diary/personaldiary/diarydetails.jsp?diaryId="+diaryId+"&userName="+encodeURIComponent(userName);
		new SysFrame().tabs('update',{
			title: "日志详情",
			url:url
		});
	}