
var selectedleave=null;
var optleaveId=null;
var optleaveName=null;
var markleave=null;
var mainleave=null;
//初始化
function leaveinit(leaveArray,selectleave,mainpara){
	addleave();
	getleave();
	mainleave=mainpara;
	markleave=selectleave;
	initgetleave(leaveArray);
	initgetoptOk();
}
function initgetleave(leaveArray){
	optleaveId=leaveArray[0];
	optleaveName=leaveArray[1];
	var leaveId=$('#'+optleaveId).val();
	var leaveName=$('#'+optleaveName).val();
	if(leaveId !=""&& leaveId !=undefined ){
		var url=contextPath+"/tfd/system/unit/userleave/act/UserLeaveAct/getjsonleaveAct.act";
			$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:{leaveIdStr:leaveId},
		async:false,
		error:function(e){
		},
		success:function(data){
			selectedleave=[];
			for(var i = 0; i < data.length;i++){ 
			selectedleave.push({id:data[i].leaveId,name:data[i].leaveName});
			$('#leaveName'+data[i].leaveId).css("background-color","#C2D9FF");
			$('#leaveName'+data[i].leaveId).prop("checked",true);
			var leaveName="<div id='leaveName_"+data[i].leaveId+"' class=\"pitchleave\" onMouseOut=\"mooutleave('leaveName_"+data[i].leaveId+"' );\" onMouseOver=\"mouseoverleave('leaveName_"+data[i].leaveId+"' );\" onclick=\"leaveremove('leaveName_"+data[i].leaveId+"','"+data[i].leaveId+"','leaveName"+data[i].leaveId+"');\" class=\"optleave\">"+data[i].leaveName+"</div>";
			$('#optleave').append(leaveName);	
			}
		}
	});
	}
	else{
		selectedleave=[];
	}
}
//获取列表
function getleave(){
	var url=contextPath+"/tfd/system/unit/userleave/act/UserLeaveAct/getUserLeaveSelectAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				var leaveId="<div style='display:none;' id='leaveId"+data[i].id+"' >"+data[i].id+"<div>";
				var leaveName="<div id='leaveName"+data[i].id+"' class='checkleave' onMouseOut=\"mooutleave('leaveName"+data[i].id+"');\" onMouseOver=\"mouseoverleave('leaveName"+data[i].id+"');\" onclick=\"mousedownleave('"+data[i].id+"','"+data[i].text+"','leaveName"+data[i].id+"');\">"+data[i].text+"</div>";
				$('#selectleave').append(leaveId);
				$('#selectleave').append(leaveName);
			}
		}
	});
}
function mouseoverleave(id){
	if($('#'+id).prop("checked")==true){}
	else{
	$('#'+id).css("background-color","#D2FEA5");
	}
}
function mooutleave(id){
	if($('#'+id).prop("checked")==true){}
	else{
	$('#'+id).css("background-color","");
	}
}
function mousedownleave(leaveId,leaveName,leid){
	if($('#'+leid).prop("checked")){
		for(var i=0;i<selectedleave.length;i++){
		if(selectedleave[i].id==leaveId){
			selectedleave.splice(i,1);
			break;
		}
		}
		$('#'+leid).prop("checked",false);
		$('#'+leid).css("background-color","");
		$('#leaveName_'+leaveId).remove();
	}else{
		if(markleave=='true'){
			if(selectedleave!=null){
			for(var i=0;i<selectedleave.length;i++){
				$('#leaveName_'+selectedleave[i].id).remove();
				$('#leaveName'+selectedleave[i].id).prop("checked",false);
				$('#leaveName'+selectedleave[i].id).css("background-color","");
			}
			}
		selectedleave=null;
		$('#'+leid).prop("checked",true);
		$('#'+leid).css("background-color","#C2D9FF");
		if(selectedleave!=null){}else{
			selectedleave=[];
		}
		selectedleave.push({id:leaveId,name:leaveName});
		var leaveId=leaveId;
		var leaveName="<div id='leaveName_"+leaveId+"' onMouseOut=\"mooutleave('leaveName_"+leaveId+"');\" onMouseOver=\"mouseoverleave('leaveName_"+leaveId+"');\" onclick=\"leaveremove('leaveName_"+leaveId+"','"+leaveId+"','"+leid+"');\"  class='optleave'>"+leaveName+"</div>";
		$('#optleave').append(leaveName);	
		}else{
			$("#"+leid).prop("checked",true);
			$('#'+leid).css("background-color","#C2D9FF");
			if(selectedleave!=null){}else{
				selectedleave=[];
			}
			selectedleave.push({id:leaveId,name:leaveName});
			var leaveId=leaveId;
			var leaveName="<div id='leaveName_"+leaveId+"' onMouseOut=\"mooutleave('leaveName_"+leaveId+"');\" onMouseOver=\"mouseoverleave('leaveName_"+leaveId+"');\" onclick=\"leaveremove('leaveName_"+leaveId+"','"+leaveId+"','"+leid+"');\"  class='optleave'>"+leaveName+"</div>";
			$('#optleave').append(leaveName);	
		}
		if(markleave=='true'){
				showleaveName();
				$("#myModal").modal('hide'); 
				if(mainleave!=undefined){
				eval(mainleave+"()");
				}	
			}
	}
}
function leaveremove(id,leaveId,oneid){
	var parentid=oneid;
	for(var i=0;i<selectedleave.length;i++){
		if(selectedleave[i].id==leaveId){
			selectedleave.splice(i,1);
			break;
		}
	}
	$("#"+parentid).prop("checked",false);
	$('#'+parentid).css("background-color","");
	$('#'+id).remove();
}
function initgetoptOk(){
	$("#leaveok").click(function (){
		showleaveName();
		if(mainleave!=undefined){
			eval(mainleave+"()");
		}
	});
	$("#leaveremove").click(function (){
		selectedleave=null;
	});
}
function showleaveName(){
	var ids="";
		var names="";
		if(selectedleave.length>0){
			for(var i=0;i<selectedleave.length;i++){
				ids+=selectedleave[i].id+",";
				names+=selectedleave[i].name+",";
			}
		ids=ids.length>0?ids.substring(0,ids.length-1):"";
		names=names.length>0?names.substring(0,names.length-1):"";
		$('#'+optleaveId).val(ids);
		$('#'+optleaveName).val(names);
		}
		else{
			$('#'+optleaveId).val("");
			$('#'+optleaveName).val("");
		}
}
