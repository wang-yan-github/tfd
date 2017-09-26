$(function (){
	var datalength=0;
	$.ajax({
		url:contextPath+"/tfd/system/apppower/act/AppiconAct/lookiconAct.act",
		type:"POST",
		dataType:"json",
		async:false,
		success:function(data){
			datalength=data.length;
			for(var i=0;i<data.length;i++){
				var json=JSON.stringify(data[i]);
				$("#appicon").append(
						"&nbsp;&nbsp;&nbsp;" +
						"<input type=\"checkbox\" name='appicon"+i+"' id='appicon"+i+"'  value='"+json+"'>" +
						"<label for='appicon"+i+"'>"+data[i].name+"</label>&nbsp;&nbsp;"
				);
			}
		}
	});
	
	
	$("#ok").on("click",function(){
		addpower();
	});
	
	function addpower(){
		var appArray=[];
		for(var i=0; i<datalength;i++){
			if($("#appicon"+i).prop("checked")){
				appArray.push($("#appicon"+i).val());
			}
		}
		var appIcon="["+appArray.join(",")+"]";
		
		var result="0";
		
		var accountIdArr=$("#accountId").val();
		if(accountIdArr!=""){
			result=userpower(accountIdArr,appIcon);
		}
		
		if (result=="0") {
			var deptIdArr=$("#deptId").val();
			if(deptIdArr!=""){
				result=deptpower(deptIdArr,appIcon)
			}
		}
		
		if (result=="0") {
			var userPrivArr=$("#userPriv").val();
			if(userPrivArr!=""){
				result=privpower(userPrivArr,appIcon);
			}
		}
		
		if(result=="0"){
			parent.layer.msg('设置成功');
		}else{
			parent.layer.msg('设置失败',function(){});
		} 
		location.reload();
	}
	function userpower(accountId,appIcon){
		var result="-1";
		$.ajax({
			url:contextPath+"/tfd/system/unit/account/act/AccountAct/getIdpowerAct.act",
			type:"POST",
			data:{accountId:accountId,appIcon:appIcon},
			dataType:"text",
			async:false,
			success:function(data){
				result=data;
			}
		});
		return result;
	}
	function deptpower(deptId,appIcon){
		var result="-1";
		$.ajax({
			url:contextPath+"/tfd/system/unit/account/act/AccountAct/getdeptpowerAct.act",
			type:"POST",
			data:{deptId:deptId,appIcon:appIcon},
			dataType:"text",
			async:false,
			success:function(data){
				result=data;
			}
		});
		return result;
	}
	function privpower(userPriv,appIcon){
		var result="-1";
		$.ajax({
			url:contextPath+"/tfd/system/unit/account/act/AccountAct/getprivIdpowerAct.act",
			type:"POST",
			data:{userPriv:userPriv,appIcon:appIcon},
			dataType:"text",
			async:false,
			success:function(data){
				result=data;
			}
		});
		return result;
	}
});