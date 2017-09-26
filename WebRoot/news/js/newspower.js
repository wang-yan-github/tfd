var approvalhtml="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n"+
   "<div class=\"modal-dialog\">\n"+
      "<div class=\"modal-content\">\n"+
         "<div class=\"modal-header\">\n"+
            "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;\n"+
            "</button><h4 class=\"modal-title\" id=\"myModalLabel\">\n"+
             "选择审批人员</h4>\n"+
         "</div><div class=\"modal-body\" align=\"center\" style=\"height: 260px;\">\n"+
						"<div><div style=\"width: 200px; height: 40px;float:left; margin-left:45px; text-align: center; line-height: 40px; background-color: #F7F8BD; border: solid 1px #CCCCCC; border-bottom: none; border-top-left-radius: 5px; border-top-right-radius: 5px;\">\n"+
						"审批人员列表</div><div style=\"width: 200px; height: 40px; margin-left: 220px; text-align: center; line-height: 40px; background-color: #F7F8BD; border: solid 1px #CCCCCC; border-bottom: none; border-top-left-radius: 5px; border-top-right-radius: 5px;\">\n"+
						"已选中的人员列表</div></div><div><div style=\"width: 200px; margin-left:45px;text-align:center; cursor:pointer;height:200px;line-height:30px;border-bottom: solid 1px #CCCCCC; float:left;overflow-y:auto;border: solid 1px #CCCCCC;\" id=\"selectPriv\"></div>\n"+
						"<div style=\"width: 200px; margin-left:44px; text-align:center; cursor:pointer;height:200px;line-height:30px;border-bottom: solid 1px #CCCCCC; float:left;overflow-y:auto;border: solid 1px #CCCCCC;\" id=\"optPriv\"></div>\n"+
						"</div></div>\n" +
						"<div class=\"modal-footer\">\n"+
            "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>\n"+
            "<button type=\"button\" class=\"btn btn-primary\" id=\"opt_okapproval\" data-dismiss=\"modal\" >确定</button>\n"+
         "</div>\n" +
         "</div>\n"+
"</div>";
function approvalcreateDialog(){
	$("#modaldialog").html(approvalhtml);
}
function addapproval(){
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
}

var selectedpriv=null;
//初始化
function approvalinit(data,id){
	if(data.length==1){
		defaulepubNews(data[0].accountId,id);
	}else{
	approvalcreateDialog();
	getapproval(data);
	initgetoptOkapproval(id);
	addapproval();
	}
}
//获取审批人员列表
function getapproval(data){
	for(var i = 0; i < data.length;i++){ 
			var privId="<div style='display:none;' id='privId"+data[i].accountId+"' >"+data[i].accountId+"<div>";
			 var privName="<div id='privName"+data[i].accountId+"' class='checkpriv' onMouseOut=\"mooutprivapproval('privName"+data[i].accountId+"');\" onMouseOver=\"mouseoverapproval('privName"+data[i].accountId+"');\" onclick=\"mousedownapproval('"+data[i].accountId+"','"+data[i].userName+"','privName"+data[i].accountId+"');\">"+data[i].userName+"</div>";
			 $('#selectPriv').append(privId);
			 $('#selectPriv').append(privName);
		} 
}
function mouseoverapproval(id){
	if($('#'+id).prop("checked")==true){
		
	}
	else{
	$('#'+id).css("background-color","#D2FEA5");
	}
}
function mooutprivapproval(id){
	if($('#'+id).prop("checked")==true){}
	else{
	$('#'+id).css("background-color","");
	}
}
function mousedownapproval(privId,privName,prid){
	if($('#'+prid).prop("checked")==true){
		
	}else{
			if(selectedpriv!=null){
			for(var i=0;i<selectedpriv.length;i++){
				$('#privName_'+selectedpriv[i].id).remove();
				$('#privName'+selectedpriv[i].id).prop("checked",false);
				$('#privName'+selectedpriv[i].id).css("background-color","");
			}
			}
		selectedpriv=null;
		$('#'+prid).prop("checked",true);
		$('#'+prid).css("background-color","#C2D9FF");
		if(selectedpriv!=null){}else{
			selectedpriv=[];
		}
		selectedpriv.push({id:privId,name:privName});
		var privId=privId;
		var privName="<div id=\"privName_"+privId+"\" onMouseOut=\"mooutprivapproval('privName_"+privId+"' );\" onMouseOver=\"mouseoverapproval('privName_"+privId+"' );\" onclick=\"approvalremove('privName_"+privId+"','"+privId+"','"+prid+"');\"  class='optpriv'>"+privName+"</div>";
		$('#optPriv').append(privName);	
	}
}
function approvalremove(id,privId,oneid){
	var parentid=oneid;
	for(var i=0;i<selectedpriv.length;i++){
		if(selectedpriv[i].id==privId){
			selectedpriv.splice(i,1);
			break;
		}
	}
	$("#"+parentid).prop("checked",false);
	$('#'+parentid).css("background-color","");
	$('#'+id).remove();
}
function defaulepubNews(accountId,id){
	if(id==1){
			var top=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				var url=contextPath+"/news/act/NewsAct/publishNewsAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"json",
					data:{
						title:$("#title").val(),
						newstype:$("#newstype").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						endTime:$("#endTime").val(),
						attachId:$("#attachId").val(),
						contect:editor.getData(),
						top:top,
						commentStatus:$("#commentStatus").val(),
						approvalStaff:accountId,
						approvalStatus:0,
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1)
							{
							parent.layer.msg('发布成功');
							var url=contextPath+"/news/manage/index.jsp";
						new SysFrame().tabs('select', "新闻发布"); 
						var tab = new SysFrame().tabs('getSelected',""); 					
						new SysFrame().tabs('updatetabs', {
							tab: tab,
							title:'新闻维护',
							oldtitle:'新闻发布',
							url: url 
						});
						}
				}
				});
			}else{
				var url=contextPath+"/news/act/NewsAct/publishUpdateNewsAct.act";
				var top=0;
				var endStatus=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				if($("#endStatus").prop("checked")){
					endStatus=1;
				}
				$.ajax({
					url:url,
					type:"POST",
					dataType:"text",
					data:{
						newsId:newsId,
						title:$("#title").val(),
						newstype:$("#newstype").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						endTime:$("#endTime").val(),
						attachId:$("#attachId").val(),
						contect:editor.getData(),
						top:top,
						endStatus:endStatus,
						approvalStaff:accountId,
						commentStatus:$("#commentStatus").val(),
						approvalStatus:0,
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1){
							parent.layer.msg('发布成功');
							history.back();
						}
					}
				});
			}
}
function initgetoptOkapproval(id){
	$("#opt_okapproval").click(function (){
		var ids="";
		var names="";
		if(selectedpriv.length>0){
			for(var i=0;i<selectedpriv.length;i++){
				ids+=selectedpriv[i].id+",";
				names+=selectedpriv[i].name+",";
			}
		ids=ids.length>0?ids.substring(0,ids.length-1):"";
		names=names.length>0?names.substring(0,names.length-1):"";
		$('#approvalstaff').val(ids);
		if(id==1){
			var top=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				var url=contextPath+"/news/act/NewsAct/publishNewsAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"json",
					data:{
						title:$("#title").val(),
						newstype:$("#newstype").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						endTime:$("#endTime").val(),
						attachId:$("#attachId").val(),
						contect:editor.getData(),
						top:top,
						commentStatus:$("#commentStatus").val(),
						approvalStaff:$("#approvalstaff").val(),
						approvalStatus:0,
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1)
							{
							parent.layer.msg('发布成功');
							var url=contextPath+"/news/manage/index.jsp";
						new SysFrame().tabs('select', "新闻发布"); 
						var tab = new SysFrame().tabs('getSelected',""); 					
						new SysFrame().tabs('updatetabs', {
							tab: tab,
							title:'新闻维护',
							oldtitle:'新闻发布',
							url: url 
						});
						}
				}
				});
			}else{
				var url=contextPath+"/news/act/NewsAct/publishUpdateNewsAct.act";
				var top=0;
				var endStatus=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				if($("#endStatus").prop("checked")){
					endStatus=1;
				}
				$.ajax({
					url:url,
					type:"POST",
					dataType:"text",
					data:{
						newsId:newsId,
						title:$("#title").val(),
						newstype:$("#newstype").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						endTime:$("#endTime").val(),
						attachId:$("#attachId").val(),
						contect:editor.getData(),
						top:top,
						endStatus:endStatus,
						approvalStaff:$("#approvalstaff").val(),
						commentStatus:$("#commentStatus").val(),
						approvalStatus:0,
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1){
							parent.layer.msg('发布成功');
							history.back();
						}
					}
				});
			}
		}
		else{
			$('#approvalstaff').val();
			parent.layer.msg('未选择审批人员',function(){});
		}
	});
}

 