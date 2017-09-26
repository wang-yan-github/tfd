$(function(){
		filesUpLoad("diary");
		init();
		});
function init(){
	    	var titletime=new Date().format("yyyy-MM-dd");
	    	var week="天一二三四五六";
	    	var weekday= week.charAt(new Date(Date.parse(titletime.replace(/\-/g,"/"))).getDay());
	    	$('#diaryName').val(titletime+"  星期"+weekday+" "+userName+"的日志");
	    	$('#diarytitleDatetime').val(titletime);
	}
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
   function getdiaryFit(){
	var returndata=null;
	var url=contextPath+"/diary/act/DiaryFitAct/lookFitAct.act";
	$.ajax({
		   url:url,
		   type:'POST',
	   	   dataType:"json",
	   		async:false,
	   		success:function(data){
	   			if(!jQuery.isEmptyObject(data)){
	   			returndata=data;
	   		}
	   		}
	   });
	   return returndata;
}
   function evaluatediaryName(){
	   var diarytitleDatetime=$('#diarytitleDatetime').val();
	   	var week="天一二三四五六";
	   	var weekday= week.charAt(new Date(Date.parse(diarytitleDatetime.replace(/\-/g,"/"))).getDay());
	   	$('#diaryName').val(diarytitleDatetime+"  星期"+weekday+ " "+userName+"的日志");
   }
   function addDiary(){
   			var shareRange=0;
   			if($("#diaryMold").val()==0){
   			if(getdiaryFit()!=null){
   			shareRange=getdiaryFit().shareStatus;
   			}
   			}
		   var url=contextPath + "/diary/act/DiaryAct/addDiaryAct.act";
		   var para=$('#diaryform').serialize();
		   var diaryContent=encodeURIComponent(CKEDITOR.instances.editor.getData());
		   para+="&diaryContent="+diaryContent;
		   para+="&diaryAnnex="+$("#attachId").val()+"&shareRange="+shareRange;
		   if(check()){
		   $.ajax({
			   url:url,
			   type:'POST',
		   	   data:para,
		   	   dataType:"text",
		   		async:false,
		   		success:function(data){
		   			if(data!=0){
		   			top.layer.msg('添加成功');
		   			var url=contextPath+"/diary/personaldiary/personaldiary.jsp";
		   			window.location=url;
		   			}
		   			else{
		   				top.layer.msg('添加失败',function(){});
		   			}
		   		}
		   });
		   }
   }