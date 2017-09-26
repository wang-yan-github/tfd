
var selectedpriv=null;
var optprivId=null;
var optprivName=null;
var markpriv=null;
var mainpriv=null;
var status='true';
//初始化
function privinit(privArray,selectpriv,mpriv){
	addpriv(selectpriv);
	getpriv();
	markpriv=selectpriv;
	mainpriv=mpriv;
	initgetpriv(privArray);
	initgetoptOk();
}
function initgetpriv(privArray){
	optprivId=privArray[0];
	optprivName=privArray[1];
	var privId=$('#'+optprivId).val();
	var privName=$('#'+optprivName).val();
	if(privId !=""&& privId !=undefined){
		var url=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/getjsonuserPrivAct.act";
			$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:{userprivStr:privId},
		async:false,
		error:function(e){
		},
		success:function(data){
			selectedpriv=[];
			for(var i = 0; i < data.length;i++){ 
				if(data[i].userprivId=="privAll")
				{
					status='false';
				}
			selectedpriv.push({id:data[i].userprivId,name:data[i].userprivName});
			$('#privName'+data[i].userprivId).css("background-color","#C2D9FF");
			$('#privName'+data[i].userprivId).prop("checked",true);
			var privName="<div id='privName_"+data[i].userprivId+"' class=\"pitchpriv\" onMouseOut=\"mooutpriv('privName_"+data[i].userprivId+"' );\" onMouseOver=\"mouseover('privName_"+data[i].userprivId+"' );\" onclick=\"privremove('privName_"+data[i].userprivId+"','"+data[i].userprivId+"','privName"+data[i].userprivId+"');\" class='optpriv'>"+data[i].userprivName+"</div>";
			$('#optPriv').append(privName);	
			}
		}
	});
	}
	else{
		selectedpriv=[];
	}
}
//获取角色列表
function getpriv(){
	var url=contextPath+"/tfd/system/module/selectpriv/act/selectPrivAct/getPrivAct.act";
	$.ajax({
		url:url,
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				var privId="<div style='display:none;' id='privId"+data[i].privId+"' >"+data[i].privId+"<div>";
				var privName="<div id=\"privName"+data[i].privId+"\" class=\"checkpriv\" onMouseOut=\"mooutpriv('privName"+data[i].privId+"');\" onMouseOver=\"mouseover('privName"+data[i].privId+"');\" onclick=\"mousedown('"+data[i].privId+"','"+data[i].privName+"','privName"+data[i].privId+"');\">"+data[i].privName+"</div>";
				$('#selectPriv').append(privId);
				$('#selectPriv').append(privName);
			}
		}
	});
}
function mouseover(id){
	if($('#'+id).prop("checked")==true){}
	else{
	$('#'+id).css("background-color","#D2FEA5");
	}
}
function mooutpriv(id){
	if($('#'+id).prop("checked")==true){}
	else{
	$('#'+id).css("background-color","");
	}
}
function mousedown(privId,privName,prid){
	if(status=='true'){
	if($('#'+prid).prop("checked")){
		for(var i=0;i<selectedpriv.length;i++){
		if(selectedpriv[i].id==privId){
			selectedpriv.splice(i,1);
			break;
		}
	}
		$('#privName_'+privId).remove();
		$('#privName'+privId).prop("checked",false);
		$('#privName'+privId).css("background-color","");
	}else{
		if(markpriv=='true'){
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
		var privName="<div id='privName_"+privId+"' onMouseOut=\"mooutpriv('privName_"+privId+"');\" onMouseOver=\"mouseover('privName_"+privId+"');\" onclick=\"privremove('privName_"+privId+"','"+privId+"','"+prid+"');\"  class='optpriv'>"+privName+"</div>";
		$('#optPriv').append(privName);	
		}else{
			$('#'+prid).prop("checked",true);
			$('#'+prid).css("background-color","#C2D9FF");
			if(selectedpriv!=null){}else{
				selectedpriv=[];
			}
			selectedpriv.push({id:privId,name:privName});
			var privId=privId;
			var privName="<div id='privName_"+privId+"' onMouseOut=\"mooutpriv('privName_"+privId+"');\" onMouseOver=\"mouseover('privName_"+privId+"');\" onclick=\"privremove('privName_"+privId+"','"+privId+"','"+prid+"');\"  class='optpriv'>"+privName+"</div>";
			$('#optPriv').append(privName);	
		}
		if(markpriv=='true'){
				showprivName();
				$("#myModal").modal('hide'); 
				if(mainpriv!=undefined){
				eval(mainpriv+"()");
				}
			}
	}
	}
}
function privremove(id,privId,oneid){
	var parentid=oneid;
	for(var i=0;i<selectedpriv.length;i++){
		if(selectedpriv[i].id==privId){
			selectedpriv.splice(i,1);
			break;
		}
	}
	if(privId=="privAll"){
		status='true';
	}
	$("#"+parentid).prop("checked",false);
	$('#'+parentid).css("background-color","");
	$('#'+id).remove();
}
function initgetoptOk(){
	$("#opt_ok").click(function (){
		showprivName();
		if(mainpriv!=undefined){
				eval(mainpriv+"()");
				}
				status='true';
	});
	$("#opt_all").click(function (){
		choicepriv();
		if(mainpriv!=undefined){
				eval(mainpriv+"()");
				}
				status='true';
	});
	$("#opt_remove").click(function (){
		selectedpriv=null;
		status='true';
	});
}
function choicepriv(){
	$('#'+optprivId).val("privAll");
		$('#'+optprivName).val("全部角色");
}
function showprivName(){
	var ids="";
		var names="";
		if(selectedpriv.length>0){
			for(var i=0;i<selectedpriv.length;i++){
				ids+=selectedpriv[i].id+",";
				names+=selectedpriv[i].name+",";
			}
		ids=ids.length>0?ids.substring(0,ids.length-1):"";
		names=names.length>0?names.substring(0,names.length-1):"";
		$('#'+optprivId).val(ids);
		$('#'+optprivName).val(names);
		}
		else{
			$('#'+optprivId).val("");
			$('#'+optprivName).val("");
		}
}
