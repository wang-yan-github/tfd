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
getMessagePriv("news");
filesUpLoad("news");
getSelect("news","newstype","");
$("#createTime").val(new Date().format("yyyy-MM-dd hh:mm:ss"));
});
function saveNews()
{
	if($("#title").val()==""){		
		parent.layer.msg('标题不可为空',function(){});
	}else{
	var top=0;
	if($('#top').prop("checked")){
		 top=1;
	}
	var url=contextPath+"/news/act/NewsAct/addNewsAct.act";
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
			smsReminds:getsmsRemind()
		},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				parent.layer.msg('保存成功');
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
	}
}
function checkstatus(){
	var url=contextPath+"/news/act/NewsPowerAct/getaccountIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				publishNews();
			}else{
				var url=contextPath+"/news/act/NewsPowerAct/getapprovalAct.act";
				$.ajax({
					url:url,
					type:"POST",
					dataType:"json",
					async:false,
					error:function(e){
					},
					success:function(data){
						if(data.length==0){
							publishNews();
						}else{
						approvalinit(data,1);
						}
					}
				});
			}
	}
	});
}
 function publishNews(){
	 var top=0;
		if($('#top').prop("checked")){
			 top=1;
		}
	var url=contextPath+"/news/act/NewsAct/publishNewsAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
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
			approvalStatus:1,
			commentStatus:$("#commentStatus").val(),
			smsReminds:getsmsRemind()
		},
		async:false,
		error:function(e){
		},
		success : function(data) {
					if (data== 1) {
						parent.layer.msg('保存成功');
						var url=contextPath+"/news/manage/index.jsp";
						new SysFrame().tabs('select', "新闻发布"); 
						var tab = new SysFrame().tabs('getSelected',""); 					
						new SysFrame().tabs('updatetabs', {
							tab: tab,
							title:'新闻维护',
							url: url 
						});

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
 	$('#newsform').bootstrapValidator('revalidateField', 'deptName');
 	$('#newsform').bootstrapValidator('revalidateField', 'userName');
 	$('#newsform').bootstrapValidator('revalidateField', 'userPrivName');
 }
