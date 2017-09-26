Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth()+3)/3),
        "S" : this.getMilliseconds()
    };
    
    if(/(y+)/.test(format))
    {
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
    return format;
};
$(function(){
	getMessagePriv("notice");
filesUpLoad("notice");
getSelect("notice","noticeType","");
var nowDate = new Date();
$("#createTime").val(new Date().format("yyyy-MM-dd hh:mm:ss"));
});
function saveNotice()
{
	if($("#noticeTitle").val()==""){
		parent.layer.msg('标题不可为空',function(){});
	}else{
	var top=0;
	if($('#top').prop("checked")){
		 top=1;
	}
	var attachPower=0;
	if($("#attachPower").prop("checked")){
		attachPower=1;
	}
	var url=contextPath+"/notice/act/NoticeAct/addnoticeAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{
			noticeTitle:$("#noticeTitle").val(),
			noticeType:$("#noticeType").val(),
			accountId:$("#accountId").val(),
			deptPriv:$("#deptPriv").val(),
			userPriv:$("#userPriv").val(),
			createTime:$("#createTime").val(),
			attachId:$("#attachId").val(),
			noticeContent:CKEDITOR.instances.editor.getData(),
			top:top,
			endTime:$("#endTime").val(),
			attachPower:attachPower,
			smsReminds:getsmsRemind()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				parent.layer.msg('保存成功');
				var url=contextPath+"/notice/manage/index.jsp";
						new SysFrame().tabs('select', "通知公告发布"); 
						var tab = new SysFrame().tabs('getSelected',""); 					
						new SysFrame().tabs('updatetabs', {
							tab: tab,
							title:'通知公告维护',
							oldtitle:'通知公告发布',
							url: url 
						});
			}
	}
	});
	}
}
function publishNotice()
{
	var powerurl=contextPath+"/notice/act/ApprovalNoticePowerAct/looktypeapprovalAct.act";
	$.ajax({
		url:powerurl,
		type:"POST",
		dataType:"json",
		data:{
			noticeType:$("#noticeType").val()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(jQuery.isEmptyObject(data)){
				var top=0;
				if($('#top').prop("checked")){
					 top=1;
				}
				var attachPower=0;
				if($("#attachPower").prop("checked")){
					attachPower=1;
				}
				var url=contextPath+"/notice/act/NoticeAct/publishnoticeAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"json",
					data:{
						noticeTitle:$("#noticeTitle").val(),
						noticeType:$("#noticeType").val(),
						accountId:$("#accountId").val(),
						deptPriv:$("#deptPriv").val(),
						userPriv:$("#userPriv").val(),
						createTime:$("#createTime").val(),
						attachId:$("#attachId").val(),
						noticeContent:CKEDITOR.instances.editor.getData(),
						top:top,
						approvalStatus:1,
						endTime:$("#endTime").val(),
						attachPower:attachPower,
						smsReminds:getsmsRemind()
					},
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data==1)
							{
						parent.layer.msg('发布成功');
				var url=contextPath+"/notice/manage/index.jsp";
						new SysFrame().tabs('select', "通知公告发布"); 
						var tab = new SysFrame().tabs('getSelected',""); 					
						new SysFrame().tabs('updatetabs', {
							tab: tab,
							title:'通知公告维护',
							oldtitle:'通知公告发布',
							url: url 
						});
						}
				}
				});
			}
			else{
				approvalinit(data,1);
			}
	}
	});
}

	 function showother()
 {
	 if($("#showother").css('display')=="none")
		 {
		 $("#showother").show();
		 }else
			 {
			 $("#showother").hide();
			 }
 }
 function deptNameval(){
 	$('#noticeform').bootstrapValidator('revalidateField', 'deptName');
 	$('#noticeform').bootstrapValidator('revalidateField', 'userName');
 	$('#noticeform').bootstrapValidator('revalidateField', 'userPrivName');
 }