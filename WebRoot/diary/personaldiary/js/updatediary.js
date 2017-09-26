var userName="";
$(function(){
	filesUpLoad("diary");
	var url=contextPath+"/diary/act/DiaryAct/getIdDiaryAct.act?diaryId="+diaryId;
	$.ajax({
		   url:url,
	   	   dataType:"json",
	   	   async:false,
	   	   success:function(data){
	   		   userName=data.userName;
	   		$('#diaryName').val(data.diaryName);
	   		var editorElement = CKEDITOR.document.getById('editor');
	   		editorElement.setHtml(data.diaryContent);
	   		$("#sharePrivName").val(data.sharePrivName);
	   		$("#sharePriv").val(data.sharePriv);
	   		$("#diaryId").val(data.diaryId);
	   		$('#diarytitleDatetime').val(data.diaryTitletime);
	   		$("#attachId").val(data.attachId);
	   		attachId=data.attachId;
			creatAttachDiv(attachId,"attach");
	   		}
	   });
});
//检查
function check(){
	   var diaryName=$('#diaryName');
	   var diaryContent=encodeURIComponent(CKEDITOR.instances.editor.getData());
	   if(diaryName.val()=="")
		   {
		   top.layer.msg('请填写日志标题',function(){});
		   return false;
		   }else{if(diaryContent=="")
		   {
			   top.layer.msg('请填写日志内容',function(){});
			   return false;
			   }else{
				   var diarytitleDatetime=$("#diarytitleDatetime").val();
				   	var mydate = new Date().format("yyyy-MM-dd");
				   	if(Date.parse(diarytitleDatetime)<=Date.parse(mydate)){		
				   		if(Date.parse(diarytitleDatetime)>=(Date.parse(mydate)-diaryFirDay()*24*60*60*1000)||diaryFirDay()==0){
				   		return true;
				   		}else{
				   			top.layer.msg('选择日期在锁定范围之内',function(){});
				   			return false;
				   		}
				   	}else{
				   		top.layer.msg('选择日期大于当前日期',function(){});
				   		return false;
				   	}
				   }
		   }
}
function diaryFirDay(){
   	var url=contextPath+"/diary/act/DiaryFitAct/getlockDayAct.act";
   	var lockday=0;
   	 $.ajax({
			   url:url,
			   type:'POST',
		   		async:false,
		   		dataType:"text",
		   		success:function(data){
		   			lockday=data;
		   		}
		   });
		   return lockday;
   }
function updatediary(){
	   var url=contextPath + "/diary/act/DiaryAct/updateDiaryAct.act";
	   var para=$('#diaryform').serialize();
	    var diaryContent=encodeURIComponent(CKEDITOR.instances.editor.getData());
		para+="&diaryContent="+diaryContent;
	   para+="&diaryAnnex="+$("#attachId").val();
	   if(check()){
	   $.ajax({
		   url:url,
		   type:'post',
	   	   data:para,
	   		async:false,
	   		success:function(data){
	   			if(data!=0){
	   				top.layer.msg('修改成功');
	   				history.back();
	   			}
	   		}
	   });
	   }
}
function evaluatediaryName(){
	   var diarytitleDatetime=$('#diarytitleDatetime').val();
	   	var week="天一二三四五六";
	   	var weekday= week.charAt(new Date(Date.parse(diarytitleDatetime.replace(/\-/g,"/"))).getDay());
	   	$('#diaryName').val(diarytitleDatetime+"  星期"+weekday+" "+userName+" 日志");
}