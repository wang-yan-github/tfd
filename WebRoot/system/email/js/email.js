var seqId;
var accountId;
var allToName = "";
var allCopyName = "";
function getEmail(searchContent){
	$('#myTable').datagrid({
		width:'100%',
		url:contextPath+"/tfd/system/email/act/EmailAct/getEmailList.act?boxId="+boxIds+"&searchContent="+searchContent,
		checkOnSelect: false, selectOnCheck: false,
		columns:[
		         [
		         	{field:'SEND_TIME',title:'',width:0,hidden:true},
					 {
						field: 'EMAIL_ID',
						title: '<input style="margin-left:7px;" id="checkAll" type=checkbox /> '+boxId+' <input class=\'btn btn-default\' onclick=deleteEmail("") type=button value=彻底删除 />',
						width: '100%',
						formatter: function(value, rowData, rowIndex){
							var fhtml="<div class='child'>";
							fhtml+="<div class='check' >";
							fhtml+="	<input style=margin-left:7px; type=checkbox class=checkbox name=checkboxs value="+rowData.EMAIL_ID+"  />";
							fhtml+="</div>";
							fhtml+="<div onclick=javascript:readEmail('"+rowData.EMAIL_ID+"','"+rowData.BOX_ID+"') class='content' >";
							fhtml+="	<div class='child-user' >";
							fhtml+=			rowData.USER_NAME;
							var sendTime = rowData.SEND_TIME;
							if(sendTime!=undefined){
								sendTime = sendTime.substr(0,16);
							}
							fhtml+="		<span style=float:right;>"+sendTime+"</span>";
							fhtml+="	</div>";
							fhtml+="	<div class='child-sub' >";
							if(rowData.READ_FLAG == '1'&&rowData.BOX_ID == '1'){
								fhtml+="		<a href=javascript:void(0) ><span style=\"float:left;\" >"+rowData.SUBJECT+"</span><div class='noread' ></div></a>";
							}else{
								fhtml+="		<a href=javascript:void(0) >"+rowData.SUBJECT+"</a>";
							}
							fhtml+="	</div>";
							fhtml+="</div>";
							fhtml+="</div>";
							return fhtml;
						}
					 }
				]
         ],
		sortName: 'READ_FLAG',
		sortOrder: 'ASC',
		loadMsg: ajaxLoading(),
		collapsible: true,
		method: 'POST',
		pagination:true,
		striped: true,
        singleSelect:false,  
        remoteSort:true,
        onLoadSuccess:function(data){  
         $('#checkAll').prop("checked",false);
         if(data.total == 0){
	  		$("#checkAll").prop("disabled","true");
	  		// $('#myTable').datagrid('showColumn','SEND_TIME');
	  		// $('#myTable').datagrid('appendRow',{SEND_TIME:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'SEND_TIME', colspan: 2 });
	  		// $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
	  		$(".datagrid").hide();
	  		$(".MessageBox").show();
         }else{
         	$("#checkAll").removeProp("disabled");
         	$(".datagrid").show();
	  		$(".MessageBox").hide();
         } 
          ajaxLoadEnd();
		}  
	});
	 var p = $('#myTable').datagrid('getPager');  
     $(p).pagination({  
	     pageSize: 10,//每页显示的记录条数，默认为10  
	     pageList: [5, 10, 15 ,20],//可以设置每页记录条数的列表  
	     beforePageText: '第',//页数文本框前显示的汉字  
	     afterPageText: '页    共 {pages} 页',  
	     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
     }); 
     $('#checkAll').click(function(){
    	 if($('#checkAll').prop("checked")){
        	 $('#checkAll').prop("checked",true);
    		 $('.checkbox').prop("checked",true);
    	 }else{
        	 $('#checkAll').prop("checked",false);
        	 $('.checkbox').prop("checked",false);
    	 }
    	 
     });
    
}
/**
 * 3-31
 * 更改邮件未读
 */
function changeEmailFlag(){
	var id = "";
	var s = 0;
	var checkbox = document.getElementsByName("checkboxs");
	for(var i=0; i<checkbox.length; i++){ 
		if(checkbox[i].checked){
			id += checkbox[i].value+",";
			s++;		
		}
	}
	if(s > 0){
		requestUrl=contextPath+'/tfd/system/email/act/EmailAct/changeEmailFlag.act?';
		$.ajax({
				url:requestUrl,
				data:{id:encodeURIComponent(id)},
				dataType:"json",
				error:function(e){
					alert(e.message);
				},
				beforeSend:ajaxLoad,
				success:function(data){
					ajaxLoadClose();
					//parent["left"].location=contextPath+ "/system/email/left.jsp";
					top.layer.msg('已成功标为已读');
					getEmail("");
					parent["left"].getCount();
				}
		});
	}else{
		top.layer.msg('请选择邮件',function(){});
	}
}
/**
 * 3-31
 * 删除邮件(到废件箱)
 */
function delEmail(id){
	var s = 0;
	var type = "1";
	if(id == ""|| id == "undefinde"){
		var id = "";
		var checkbox = document.getElementsByName("checkboxs");
		for(var i=0; i<checkbox.length; i++){ 
			if(checkbox[i].checked){
				id += checkbox[i].value+",";
				s++;		
			}
		}
		if(s<=0){
			top.layer.msg('请选择邮件',function(){});
			return false;
		}
	}else{
		type = "2";
	}
	if(confirm("确认删除？")){
		requestUrl=contextPath+'/tfd/system/email/act/EmailAct/delEmail.act?';
		$.ajax({
				url:requestUrl,
				data:{id:encodeURIComponent(id)},
				dataType:"json",
				error:function(e){
					alert(e.message);
				},
				beforeSend:function(request){
					if(type!="2"){
						ajaxLoad();
					}else{
						ajaxLoading();
					}
				},
				success:function(data){
					parent["left"].getCount();
					if(type=="2"){
						ajaxLoadEnd();
						layer.msg('删除成功');
						if(boxId.length>1){
							window.location.href="otherBox.jsp?boxId="+boxId;
						}else{
							window.location.href="index.jsp";
						}
					}else{
						ajaxLoadClose();
						top.layer.msg('删除成功');
						getEmail("");
					}
				}
		});
	}
}
/**
 * 3-31
 * 彻底删除
 */
function deleteEmail(id){
	var s = 0;
	var type = "1";
	if(id == ""|| id == "undefinde"){
		var id = "";
		var checkbox = document.getElementsByName("checkboxs");
		for(var i=0; i<checkbox.length; i++){ 
			if(checkbox[i].checked){
				id += checkbox[i].value+",";
				s++;		
			}
		}
		if(s<=0){
			top.layer.msg('请选择邮件',function(){});
			return false;
		}
	}else{
		type = "2";
	}
	if(confirm("确认彻底删除？")){
		requestUrl=contextPath+'/tfd/system/email/act/EmailAct/deleteEmail.act?';
		$.ajax({
				url:requestUrl,
				data:{id:encodeURIComponent(id)},
				dataType:"json",
				error:function(e){
					alert(e.message);
				},
				beforeSend:function(request){
					if(type!="2"){
						ajaxLoad();
					}else{
						ajaxLoading();
					}
				},
				success:function(data){
					parent["left"].getCount();
					if(type=="2"){
						ajaxLoadEnd();
						layer.msg('彻底删除成功');
						if(boxId.length>1){
							window.location.href="otherBox.jsp?boxId="+boxId;
						}else{
							window.location.href="index.jsp";
						}
					}else{
						ajaxLoadClose();
						top.layer.msg('彻底删除成功');
						getEmail("");
					}
				}
		});
	}
}
function readEmail(emailId){
	if(boxIds == "3"){
		window.location.href= contextPath + "/system/email/draftbox/edit.jsp?emailId="+emailId;
	}else if(boxIds == "1"){
		window.location.href=  "/tfd/system/email/inbox/readEmail.jsp?emailId="+emailId+"&boxId="+boxIds;
	}else if(boxIds == "2"){
		window.location.href= contextPath + "/system/email/sendbox/readEmail.jsp?emailId="+emailId+"&boxId="+boxIds;
	}else if(boxIds == "4"){
		window.location.href= contextPath + "/system/email/outbox/readEmail.jsp?emailId="+emailId+"&boxId="+boxIds;
	}else{
		window.location.href= contextPath + "/system/email/readEmail.jsp?emailId="+emailId+"&boxId="+boxIds;
	}
}
function sendEmail(i){
	var content = encodeURIComponent(editor.getData());
	var accountId = $('#accountId').val();
	if(accountId == ""){
		top.layer.msg('请选择收件人',function(){});
		return false;
	}
	var subject = $('#subject').val();
	if(subject == ""){
		subject = "[无主题]";
	}
	var sendOther = $('#sendOther').val();
	var requestUrl = "";
	if(i==3){
		requestUrl = contextPath +"/tfd/system/email/act/EmailAct/sendEmailByDraft.act?emailId="+$('#emailId').val()+"&bodyId="+$('#bodyId').val();
	}else if(i == 0){
		requestUrl = contextPath +'/tfd/system/email/act/EmailAct/sendEmail.act';
	}
	
	$.ajax({
		url:requestUrl,
		data:{subject:subject,content:content,accountId:accountId,sendOther:sendOther,attachId:$("#attachId").val()
			,toWebmail:$("#WebMail").val(),smsRemind:getsmsRemind(),important:$("#important").val()},
		dataType:"json",
		type:"post",
		error:function(e){
			alert(e.message);
		},
		beforeSend:function(request){
			indexs = top.layer.load(0,{shade: [0.1,'#fff']});
		},
		success:function(data){
			if(data==1){
				top.layer.msg('发送成功');
				ajaxLoadClose();	
				if(i==3){
					window.location.href=contextPath+ "/system/email/new/index.jsp";
				}else{
					history.go(0);
				}
			}
			if(data=='-1'){
				top.layer.msg("发送失败,外部邮件错误");
				ajaxLoadClose();
			}
			parent["left"].getCount();
		}
	});
}
function getEmailById(id,i,boxId){
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getEmailById.act';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id),boxId:boxId},
		dataType:"json",
		type:"POST",
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			//var str = "<div style='margin-top:20px;width:96%;margin-left:2%;background-color:#CCC;min-height:80px;border:#CCC 1px solid;'><div style=font-size:12px; >发件人："+data[0].fromName+"</div>";
			var copyId = data[0].copyToId;
			var toName = data[0].toName;
			var allName = ""; 
			$.each(toName,function(index,toName){
				allName += toName.name+",";
			});
			allName = allName.substr(0,allName.length-1);
			var str = "<div style=\"height:0px;border-bottom:1px #c0c2cf solid;margin:5px auto\"></div><div style=\"padding:5px 15px;border-bottom:1px #cccccc solid;background:#edf6db;font-size:12px;\"><span style=\"line-height:16px;\"><strong>发件人：</strong>"+data[0].fromName+"</span><br/><span style=\"line-height:16px;\"><strong>收件人：</strong>"+allName+"</span><br/>";
			var copyToName = data[0].copyToName;
			var CopyName = ""; 
			$.each(copyToName,function(index,copyToName){
				CopyName += copyToName.name+",";
			});
			CopyName = CopyName.substr(0,CopyName.length-1);
			if(copyId!=""){
				var flag = checkUser(copyId);
				if(flag){
					str +="<span style=\"line-height:16px;\"><strong>抄送人：</strong>"+CopyName+"</span><br/>";
				}else{
					str +="<span style=\"line-height:16px;\"><strong>收件人：</strong>"+allName+"</span><br/><span style=\"line-height:16px;\"><strong>抄送人："+CopyName+"</span><br/>";
				}
			}
			str += "<span style=\"line-height:16px;\"><strong>发送时间:</strong>"+data[0].sendTime+"</span><br/><span style=\"line-height:16px;\"><strong>主题:</strong>"+data[0].subject+"</span></div><div style=\"padding:10px 10px;\">"+data[0].content+"</div>";
			if(i == '2'){
				editor.setData(str);
				attachId=data[0].attachId;
				creatAttachDiv(attachId,"attach");
				$('#subject').val("回复："+data[0].subject);
				$('#accountId').val(data[0].fromId);
				$('#userName').val(data[0].fromName);
			}else if(i == '3'){
				editor.setData(str);
				attachId=data[0].attachId;
				creatAttachDiv(attachId,"attach");
				$('#subject').val("回复："+data[0].subject);
				$('#accountId').val(data[0].toId);
				$('#userName').val(allName);
				$('#bodyId').val(data[0].bodyId);
			}else if(i == '4'){
				editor.setData(str);
				attachId=data[0].attachId;
				creatAttachDiv(attachId,"attach");
				$('#subject').val("转发："+data[0].subject);
			}else{
				editor.setData(data[0].content);
				attachId=data[0].attachId;
				creatAttachDiv(attachId,"attach");
				$('#subject').val(data[0].subject);
				$('#accountId').val(data[0].toId);
				$('#userName').val(allName);
			}
			$('#emailId').val(data[0].emailId);
			$('#bodyId').val(data[0].bodyId);
			$("#important").val(data[0].important);
			if(data[0].copyToId!=""){
				$("#OtherTr").removeClass("hiddenTr");
				$("#showOther").html("删除抄送");
				$('#sendOther').val(data[0].copyToId);
				$('#sendOtherName').val(CopyName);
			}
			if(data[0].toWebMail!=null&&data[0].toWebMail){
				$("#WebTr").removeClass("hiddenTr");
				$("#showWeb").html("删除Web抄送人");
				$('#WebMail').val(data[0].toWebMail);
			}
			setSms(data[0].isSms);
		}
	});
}
function setSms(isSms){
	if(isSms.sitesms==1){
		$("#sitesms").prop("checked",true);
	}
	if(isSms.mobilesms==1){
		$("#mobilesms").prop("checked",true);
	}
	if(isSms.emailsms==1){
		$("#emailsms").prop("checked",true);
	}
	if(isSms.webemilesms==1){
		$("#webemilesms").prop("checked",true);
	}
	if(isSms.wxsms==1){
		$("#wxsms").prop("checked",true);
	}
}
function saveEmail(i){
	var content = encodeURIComponent(editor.getData());
	var accountId = $('#accountId').val();
	if(accountId == ""){
		top.layer.msg('请选择收件人',function(){});
		return false;
	}
	var subject = $('#subject').val();
	if(subject == ""){
		subject = "[无主题]";
	}
	var sendOther = $('#sendOther').val();
	var requestUrl = "";
	if(i == 3){
		requestUrl=contextPath+"/tfd/system/email/act/EmailAct/updateEmailByDraft.act?emailId="+$('#emailId').val()+"&bodyId="+$('#bodyId').val();
	}else{
		requestUrl=contextPath+'/tfd/system/email/act/EmailAct/saveEmail.act';
	}
	$.ajax({
		url:requestUrl,
		data:{subject:subject,content:content,accountId:accountId,sendOther:sendOther,attachId:$("#attachId").val(),toWebmail:$("#WebMail").val(),important:$("#important").val()},
		dataType:"text",
		type:"POST",
		error:function(e){
			alert(e.message);
		},
		beforeSend:function(request){
			indexs = top.layer.load(0,{shade: [0.1,'#fff']});
		},
		success:function(data){
			ajaxLoadClose();
			top.layer.msg('保存成功');
			if(i == '1'){
				id = data;
			}
			parent["left"].getCount();
			window.location.href= contextPath + "/system/email/draftbox/edit.jsp?emailId="+id;
		}
	});
}

function changeEmailFlagById(){
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/changeEmailFlag.act';
	$.ajax({
			url:requestUrl,
			data:{id:encodeURIComponent(id)},
			dataType:"json",
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				parent["left"].getCount();
			}
	});
}

function sendEmails(){
	var id = "";
	var s = 0;
	var checkbox = document.getElementsByName("checkboxs");
	for(var i=0; i<checkbox.length; i++){ 
		if(checkbox[i].checked){
			id += checkbox[i].value+",";	
			s++;	
		}
	}
	if(s<=0){
		top.layer.msg('请选择邮件',function(){});
		return false;
	}
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/sendEmails.act';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id)},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			ajaxLoadEnd();
			top.layer.msg('发送成功');
			parent["left"].getCount();
			getEmail("");
		}
	});
}

function sendEmailAgain(id){
	var s = 0;
	if(id == ""|| id == "undefinde"){
		var id = "";
		var checkbox = document.getElementsByName("checkboxs");
		for(var i=0; i<checkbox.length; i++){ 
			if(checkbox[i].checked){
				id += checkbox[i].value+",";	
				s++;	
			}
		}
		if(s<=0){
			top.layer.msg('请选择邮件',function(){});
			return false;
		}
	}
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/sendEmailAgain.act';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id)},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			ajaxLoadEnd();
			top.layer.msg('发送成功');
			parent["left"].getCount();
			getEmail("");
		}
	});
}

function restoreEmail(id){
	var s = 0;
	if(id == ""|| id == "undefinde"){
		var id = "";
		var checkbox = document.getElementsByName("checkboxs");
		for(var i=0; i<checkbox.length; i++){ 
			if(checkbox[i].checked){
				id += checkbox[i].value+",";
				s++;		
			}
		}
		if(s<=0){
			top.layer.msg('请选择邮件',function(){});
			return false;
		}
	}
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/restoreEmail.act';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id),boxId:'1'},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			ajaxLoadEnd();
			top.layer.msg('恢复成功');
			parent["left"].getCount();
			getEmail("");
		}
	});
}
function goNext(boxId){
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getNextEmail.act?boxId='+boxId;
	$.ajax({
		url:requestUrl,
		data:{id:seqId,boxId:boxId},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			if($.isEmptyObject(data)){
				top.layer.msg('没有下一封邮件了',function(){});
			}else{
				id = data[0].emailId;
				changeEmailFlagById();
				$(".info").css("height","70px");
				if(data[0].attachId!=null&&data[0].attachId!=''){
					$('#attachment').attr("style","display:block;");
					$('#attachDiv').attr("style","display:block;");
					attachId=data[0].attachId;
					readAttachDiv(attachId,"attach");
				}else{
					$('#attachment').attr("style","display:none;");
					$('#attachDiv').attr("style","display:none;");
				}
				var toName = data[0].toName;
				allToName = toName;
				var allName = ""; 
				$.each(toName,function(index,toName){
					if(toName.readFlag=="true"){
						 allName += "<div class='read' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+toName.userId+"')\" >"+toName.name+"</a>,</span>";
					}else{
						allName += "<div class='noread' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+toName.userId+"')\" >"+toName.name+"</a>,</span>";
					}
					if(index==7){
						allName += "<span style=\"margin-left:0px;float:left;\" >...<a href=\"javascript:void(0);\" onclick=\"javascript:showAllToName();\"  >详情</a></span>";
						return false;
					}
				});
				allName = allName.substr(0,allName.length-1);
				var str = "";
				var important = "";
				if(data[0].important=='2'){
					important = "<span style=\"color:#FF6600;\">重要邮件</sapn>";
				}else if(data[0].important=='3'){
					important = "<span style=\"color:red;\">非常重要</sapn>";
				}
				str = "<div class=top ><span>"+data[0].subject+important+"</span></div><div class=moddle ><span>发件人:<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data[0].fromId+"')\" >"+data[0].fromName+"</a></span></div><div class=bottom ><div style=width:100%;float:left ><span ><span style=\"float:left;\">收件人:</span><div style=\"float:left;width:50%;\">"+allName+"</div></span></div>";
				var copyId = data[0].copyToId;
				if(copyId!=""){
					var flag = checkUser(copyId,toName);
					if(!flag){
						str = "<div class=top ><span>"+data[0].subject+"</span></div><div class=moddle ><span>发件人:<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data[0].fromId+"')\" >"+data[0].fromName+"</a></span>";
					}else{
						var height = $(".info").css("height");
						height = height.substr(0,height.length-2);
						height = parseInt(height)+20;
						$(".info").css("height",height+"px");
					}
					var copyToName = data[0].copyToName;
					allCopyName = copyToName;
					var CopyName = ""; 
					$.each(copyToName,function(index,copyToName){
						if(copyToName.readFlag=="true"){
							 CopyName += "<div class='read' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+copyToName.userId+"')\" >"+copyToName.name+"</a>,</span>";
						}else{
							CopyName += "<div class='noread' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+copyToName.userId+"')\" >"+copyToName.name+"</a>,</span>";
						}
						if(index==7){
							allName += "<span style=\"margin-left:0px;float:left;\" >...<a href=\"javascript:void(0);\" onclick=\"javascript:showAllCopyName();\"  >详情</a></span>";
							return false;
						}
					});
					str += "<div class=bottom ><div style=\"float:left;width:100%;\" ><span ><span style=\"float:left;\">抄送人:</span><div style=\"float:left;width:50%;\">"+CopyName+"</div>";
				}
				str += "<div  style=float:right;margin-right:10px;>"+data[0].sendTime+"</div></div>";
				$('.info').html(str);
				$('#emailHtml').html(data[0].content);
				seqId = data[0].id;
			}
			ajaxLoadEnd();
		}
	});
}
function goLast(boxId){
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getLastEmail.act?boxId='+boxId;
	$.ajax({
		url:requestUrl,
		data:{id:seqId,boxId:boxId},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			if($.isEmptyObject(data)){
				top.layer.msg('没有上一封邮件了',function(){});
			}else{
				id = data[0].emailId;
				changeEmailFlagById();
				$(".info").css("height","70px");
				if(data[0].attachId!=null&&data[0].attachId!=''){
					$('#attachment').attr("style","display:block;");
					$('#attachDiv').attr("style","display:block;");
					attachId=data[0].attachId;
					readAttachDiv(attachId,"attach");
				}else{
					$('#attachment').attr("style","display:none;");
					$('#attachDiv').attr("style","display:none;");
				}
				var toName = data[0].toName;
				allToName = toName;
				var allName = ""; 
				$.each(toName,function(index,toName){
					if(toName.readFlag=="true"){
						 allName += "<div class='read' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+toName.userId+"')\" >"+toName.name+"</a>,</span>";
					}else{
						allName += "<div class='noread' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+toName.userId+"')\" >"+toName.name+"</a>,</span>";
					}
					if(index==7){
						allName += "<span style=\"margin-left:0px;float:left;\" >...<a href=\"javascript:void(0);\" onclick=\"javascript:showAllToName();\"  >详情</a></span>";
						return false;
					}
				});
				allName = allName.substr(0,allName.length-1);
				var str = "";
				var important = "";
				if(data[0].important=='2'){
					important = "<span style=\"color:#FF6600;\">重要邮件</sapn>";
				}else if(data[0].important=='3'){
					important = "<span style=\"color:red;\">非常重要</sapn>";
				}
				str = "<div class=top ><span>"+data[0].subject+important+"</span></div><div class=moddle ><span>发件人:<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data[0].fromId+"')\" >"+data[0].fromName+"</a></span></div><div class=bottom ><div style=width:100%;float:left ><span ><span style=\"float:left;\">收件人:</span><div style=\"float:left;width:50%;\">"+allName+"</div></span></div>";
				var copyId = data[0].copyToId;
				if(copyId!=""){
					var flag = checkUser(copyId,toName);
					if(!flag){
						str = "<div class=top ><span>"+data[0].subject+"</span></div><div class=moddle ><span>发件人:<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data[0].fromId+"')\" >"+data[0].fromName+"</a></span>";
					}else{
						var height = $(".info").css("height");
						height = height.substr(0,height.length-2);
						height = parseInt(height)+20;
						$(".info").css("height",height+"px");
					}
					var copyToName = data[0].copyToName;
					allCopyName = copyToName;
					var CopyName = ""; 
					$.each(copyToName,function(index,copyToName){
						if(copyToName.readFlag=="true"){
							 CopyName += "<div class='read' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+copyToName.userId+"')\" >"+copyToName.name+"</a>,</span>";
						}else{
							CopyName += "<div class='noread' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+copyToName.userId+"')\" >"+copyToName.name+"</a>,</span>";
						}
						if(index==7){
							allName += "<span style=\"margin-left:0px;float:left;\" >...<a href=\"javascript:void(0);\" onclick=\"javascript:showAllCopyName();\"  >详情</a></span>";
							return false;
						}
					});
					str += "<div class=bottom ><div style=\"float:left;width:100%;\" ><span ><span style=\"float:left;\">抄送人:</span><div style=\"float:left;width:50%;\">"+CopyName+"</div>";
				}
				str += "<div  style=float:right;margin-right:10px;>"+data[0].sendTime+"</div></div>";
				$('.info').html(str);
				$('#emailHtml').html(data[0].content);
				seqId = data[0].id;
			}
			ajaxLoadEnd();
		}
	});
}

function setEmail(id,boxId){
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getEmailById.act?';
	$.ajax({
		url:requestUrl,
		data:{id:encodeURIComponent(id),boxId:boxId},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			$(".info").css("height","70px");
			if(data[0].attachId!=null&&data[0].attachId!=''){
				$('#attachment').attr("style","display:block;");
				$('#attachDiv').attr("style","display:block;");
				attachId=data[0].attachId;
				readAttachDiv(attachId,"attach");
			}else{
				$('#attachment').attr("style","display:none;");
				$('#attachDiv').attr("style","display:none;");
			}
			var toName = data[0].toName;
			allToName = toName;
			var allName = ""; 
			$.each(toName,function(index,toName){
				if(toName.readFlag=="true"){
					 allName += "<div class='read' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+toName.userId+"')\" >"+toName.name+"</a>,</span>";
				}else{
					allName += "<div class='noread' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+toName.userId+"')\" >"+toName.name+"</a>,</span>";
				}
				if(index==7){
					allName += "<span style=\"margin-left:0px;float:left;\" >...<a href=\"javascript:void(0);\" onclick=\"javascript:showAllToName();\"  >详情</a></span>";
					return false;
				}
			});
			//allName = allName.substr(0,allName.length-1);
			var str = "";
			var important = "";
			if(data[0].important=='2'){
				important = "<span style=\"color:#FF6600;\">重要邮件</sapn>";
			}else if(data[0].important=='3'){
				important = "<span style=\"color:red;\">非常重要</sapn>";
			}
			str = "<div class=top ><span>"+data[0].subject+important+"</span></div><div class=moddle ><span>发件人:<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data[0].fromId+"')\" >"+data[0].fromName+"</a></span></div><div class=bottom ><div style=width:100%;float:left ><span ><span style=\"float:left;\">收件人:</span><div style=\"float:left;width:50%;\">"+allName+"</div>";
			var copyId = data[0].copyToId;
			if(copyId!=""){
				var flag = checkUser(copyId,toName);
				if(!flag){
					str = "<div class=top ><span>"+data[0].subject+"</span></div><div class=moddle ><span>发件人:<a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+data[0].fromId+"')\" >"+data[0].fromName+"</a></span>";
				}else{
					var height = $(".info").css("height");
					height = height.substr(0,height.length-2);
					height = parseInt(height)+20;
					$(".info").css("height",height+"px");
				}
				var copyToName = data[0].copyToName;
				allCopyName = copyToName;
				var CopyName = ""; 
				$.each(copyToName,function(index,copyToName){
					if(copyToName.readFlag=="true"){
						 CopyName += "<div class='read' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+copyToName.userId+"')\" >"+copyToName.name+"</a>,</span>";
					}else{
						CopyName += "<div class='noread' ></div><span style=\"margin-left:0px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+copyToName.userId+"')\" >"+copyToName.name+"</a>,</span>";
					}
					if(index==7){
						allName += "<span style=\"margin-left:0px;float:left;\" >...<a href=\"javascript:void(0);\" onclick=\"javascript:showAllCopyName();\"  >详情</a></span>";
						return false;
					}
				});
				str += "<div class=bottom ><div style=\"float:left;width:100%;\" ><span ><span style=\"float:left;\">抄送人:</span><div style=\"float:left;width:50%;\">"+CopyName+"</div>";
			}
			str += "<div  style=float:right;margin-right:10px;>"+data[0].sendTime+"</div></span></div>";
			$('.info').html(str);
			$('#emailHtml').html(data[0].content);
			seqId = data[0].id;
			ajaxLoadEnd();
		}
	});
}
function checkUser(copyId,toName){
	var flag = false;
	if(copyId.indexOf(",")>-1){
		var copyIds = copyId.split(",");
		for(var i = 0; i< copyIds.length;i++){
			if(copyIds[i] == userId){
				$.each(toName,function(index,toName){
					if(toName.userId == copyId){
						flag = true;
					}
				});
			}else{
				flag = true;
			}
		}
	}else{
		if(copyId == userId){
			$.each(toName,function(index,toName){
				if(toName.userId == copyId){
					flag = true;
				}
			});
		}else{
			flag = true;
		}
	}
	return flag;
}

function showTr(tr,name){
	if($("#"+tr+"Tr").css("display")!="none"){
		$("#"+tr+"Tr").addClass("hiddenTr");
		$("#show"+tr).html("添加"+name);
	}else{
		$("#"+tr+"Tr").removeClass("hiddenTr");
		$("#show"+tr).html("删除"+name);
	}
}
function checkUserName(){
	$('#form1').bootstrapValidator('revalidateField', 'userName');

}
function showAllToName(){
	var allName = "<table class=\"table table-striped\">";
	for(var i = 0 ; i < allToName.length; i++ ){
		if(allToName[i].readFlag=="true"){
			 allName += "<tr><td><div class='read' ></td><td></div><span style=\"margin-left:10px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+allToName[i].userId+"')\" >"+allToName[i].name+"</a></span></td></tr>";
		}else{
			 allName += "<tr><td><div class='noread' ></td><td></div><span style=\"margin-left:10px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+allToName[i].userId+"')\" >"+allToName[i].name+"</a></span></td></tr>";
		}
	};
	allName += "</table>";
	$("#toName-modal-body").html(allName);
	$("#toName-div-modal-dialog").width(300);
	$("#toName-div-modal-dialog").height(400);
	$("#toName-modal-body").css("height","400px");
	$("#toName-modal-body").css("overflow","auto");
	$('#toNameModal').modal({backdrop: 'static', keyboard: false});
	$('#toNameModal').modal('show');
}

function showAllCopyName(){
	var allName = "<table class=\"table table-striped\">";
	for(var i = 0 ; i < allCopyName.length; i++ ){
		if(allCopyName[i].readFlag=="true"){
			 allName += "<tr><td><div class='read' ></td><td></div><span style=\"margin-left:10px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+allCopyName[i].userId+"')\" >"+allCopyName[i].name+"</a></span></td></tr>";
		}else{
			 allName += "<tr><td><div class='noread' ></td><td></div><span style=\"margin-left:10px;float:left;\" ><a href=\"javascript:void(0);\" onclick=\"javascript:showPersonal('"+allCopyName[i].userId+"')\" >"+allCopyName[i].name+"</a></span></td></tr>";
		}
	};
	allName += "</table>";
	$("#toName-modal-body").html(allName);
	$("#toName-div-modal-dialog").width(300);
	$("#toName-div-modal-dialog").height(400);
	$("#toNameModalLabel").html("抄件人详情");
	$("#toName-modal-body").css("height","400px");
	$("#toName-modal-body").css("overflow","auto");
	$('#toNameModal').modal({backdrop: 'static', keyboard: false});
	$('#toNameModal').modal('show');
}

function goPrint(boxId){
	window.open(contextPath+"/system/email/print/index.jsp?emailId="+id+"&boxId="+boxId);
}
