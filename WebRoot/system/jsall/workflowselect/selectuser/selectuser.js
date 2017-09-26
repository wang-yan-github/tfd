$(function(){
	var userMap=new Object();
	var selectedUser=null;
	var selectedSponsorUser=null;
	
	var setting=null;
	var $wf=null;
	var loaded=false;
	
	var dialogHtml="<div class='modal fade workflow-selectuser-dialog'>"+
	   "  <div class='modal-dialog'>"+
	   "	<div class='modal-content'>"+
	   "	  <div class='modal-header'>"+
	            "<button type='button' class='close'  data-dismiss='modal' aria-hidden='true'>&times;</button>"+
	            "<h5 class='modal-title'>人员选择</h5>"+
	         "</div>"+
	         "<div class='modal-body'>"+
	            "<div class='user-search-box'>" +
	            "	<form class='form-inline search-form'>" +
	            "		<div class='form-group'>" +
	            "			<input name='searchStr' class='form-control' placeholder='人员姓名'/>" +
	            "		</div>"+		
	            "		<button type='submit' class='btn btn-info'>搜索</button>" +
	            "	</form>"+
	            "</div>" +
	            "<div class='list-box'>"+
	            "  <div class='list-container list-container-dept-list'>"+
             "	<div class='list-title'>部门列表</div>"+
             "	<div class='ztree list-content dept-list' id='workflow-dept-list'></div>"+
             "  </div>"+
	            "  <div class='list-container list-container-dept-user'>"+
             "	<div class='list-title'>" +
             "		部门人员列表&nbsp;&nbsp;" +
             "		<a href='javascript:void(0);' class='show-user-chose-all'>全选</a>" +
             "		<a href='javascript:void(0);' class='show-user-delete-all'>/反选</a>" +
             "	</div>"+
             "	<div class='list-content dept-user-list'></div>"+
             "  </div>"+
	            "  <div class='list-container list-container-selected-user'>"+
             "	<div class='list-title'>" +
             "		已选择人员&nbsp;&nbsp;" +
             "		<a href='javascript:void(0);' class='selected-user-delete-all'>反选</a>" +
             "	</div>"+
	            "	<div class='list-content selected-user-list'></div>"+
             "  </div>"+
	            "</div>"+
	         "</div>"+
          "<div class='modal-footer' style='padding-top:5px;padding-bottom:5px;'>"+
          "	<button type='button' class='btn btn-default select-cancel' data-dismiss='modal'>取消</button>"+
          "	<button type='button' class='btn btn-primary select-ok' data-dismiss='modal'>确定</button>"+
          "</div>"+      
	      "</div>"+
		"</div>";

	$wf=$(".workflow-selectuser-dialog");
	if($wf.length==0){
		$("body").append(dialogHtml);
		$wf=$(".workflow-selectuser-dialog");
	}
	
	
	$(document)
	.on("click",".workflow-selectuser",function(){
		/**
		 * 选人设置
		 * {
		 * 	container:[人员账户容器id,人员姓名容器id],
		 * 	multiple:true/false,多选吗
		 * 	flowId:流程id
		 * 	prcsId:当前步骤号
		 * }
		 */
		setting=eval("("+$(this).attr("data-select-setting")+")");


		$wf.modal("show").on("shown.bs.modal",function(){
			
			if (loaded) {
				$wf.find(".selected-user-delete-all").trigger("click");
			}else{
				initSearchFormValidation();
				loadDept();
			}
			loaded=true;
			
			selectedUser=new Object();
			selectedSponsorUser=new Object();
			loadSelectedUser();
			for(var id in selectedUser){
				$wf.find(".show-user-name[data-id='"+id+"']").trigger("click");
			}
			for(var id in selectedSponsorUser){
				$wf.find(".show-user-sponsor[data-id='"+id+"']").trigger("click");
			}
		});
	});
	
	$wf
	.on("click",".show-user-name-unselected",function(){
		var id=$(this).attr("data-id");
		
		var selectedCount=Object.getOwnPropertyNames(selectedUser).length+Object.getOwnPropertyNames(selectedSponsorUser).length;
		if (selectedCount==0) {
			$wf.find(".show-user-sponsor-unselected[data-id='"+id+"']").trigger("click");
			return true;
		}
		
		$(this).removeClass("show-user-name-unselected").addClass("show-user-name-selected");
		selectedUser[id]=userMap[id];
		addSelectedUserEle(userMap[id]);
		
		$wf.find(".selected-sponsor-user[data-id='"+id+"']").trigger("click");
		
		$wf.find(".show-user[data-id='"+id+"']").addClass("show-user-selected");
	})
	.on("click",".show-user-name-selected",function(){
		$wf.find(".selected-user[data-id='"+$(this).attr("data-id")+"']").trigger("click");
	})
	.on("click",".show-user-sponsor-unselected",function(){
		$wf.find(".selected-sponsor-user").trigger("click");
		
		$(this).removeClass("show-user-sponsor-unselected").addClass("show-user-sponsor-selected");
		$(this).find("input[type='checkbox']").prop("checked",true);
		var id=$(this).attr("data-id");
		selectedSponsorUser[id]=userMap[id];
		addSelectedSponsorUserEle(userMap[id]);
		
		$wf.find(".selected-user[data-id='"+id+"']").trigger("click");
		
		$wf.find(".show-user[data-id='"+id+"']").addClass("show-user-selected");
	})
	.on("click",".show-user-sponsor-selected",function(){
		$wf.find(".selected-sponsor-user[data-id='"+$(this).attr("data-id")+"']").trigger("click");
	})
	.on("click",".selected-user",function(){
		var id=$(this).attr("data-id");
		$wf.find(".show-user-name-selected[data-id='"+id+"']").removeClass("show-user-name-selected").addClass("show-user-name-unselected");
		delete selectedUser[$(this).attr("data-id")];
		$(this).remove();
		
		if(!(id in selectedUser)&&!(id in selectedSponsorUser)){
			$wf.find(".show-user[data-id='"+id+"']").removeClass("show-user-selected");
		}
	})
	.on("click",".selected-sponsor-user",function(){
		var id=$(this).attr("data-id");
		$wf.find(".show-user-sponsor-selected[data-id='"+id+"']").removeClass("show-user-sponsor-selected").addClass("show-user-sponsor-unselected");
		$wf.find(".show-user-sponsor[data-id='"+id+"']").find("input[type='checkbox']").prop("checked",false);
		delete selectedSponsorUser[id];
		$(this).remove();
		
		if(!(id in selectedUser)&&!(id in selectedSponsorUser)){
			$wf.find(".show-user[data-id='"+id+"']").removeClass("show-user-selected");
		}
		
//		$wf.find(".show-user-name-unselected[data-id='"+id+"']").trigger("click");
	})
	.on("click",".show-user-chose-all",function(){
		$wf.find(".show-user-name-unselected").trigger("click");
	})
	.on("click",".show-user-delete-all",function(){
		$wf.find(".selected-user-delete-all").trigger("click");
	})
	.on("click",".selected-user-delete-all",function(){
		$wf.find(".selected-user").trigger("click");
		$wf.find(".selected-sponsor-user").trigger("click");
	})
	.on("click",".select-ok",function(){
		var ids=new Array();
		var sponsorIds=new Array();
		for(var id in selectedUser){
			ids.push(id);
		}
		for(var id in selectedSponsorUser){
			sponsorIds.push(id);
		}
		$("#"+setting.container[0]).val(sponsorIds.join(","));
		$("#"+setting.container[1]).val(ids.join(","));
		
		
		var zhdiv="";
		var opdiv="";
		for(var id in selectedSponsorUser){
			opdiv+="<div id='op"+id+"'  class='user-tags'  name='op"+id+"' >"
			+selectedSponsorUser[id].name+
			"	<g class='close' onclick=\"del('op','"+id+"','"+setting.prcsId+"');\">x</g>" +
			"</div>";
		}
		for(var id in selectedUser){
			zhdiv+="<div id='zh"+id+"'  class='user-tags' name='zh"+id+"' >"
			+selectedUser[id].name+
			"   <g class='close' onclick=\"del('zh','"+id+"','"+setting.prcsId+"');\">x</g>" +
			"</div>";
		}
		
		$("#opdiv"+setting.prcsId).html(opdiv);
		$("#zhdiv"+setting.prcsId).html(zhdiv);
	});
	
	
	
	
	
	
	function loadDept(){
		$.fn.zTree.init(
				$("#workflow-dept-list"),
				{
					data:{
						simpleData:{
							enable:true,
							idKey:"id",
							pIdKey:"pid",
							rootPId:"0"
						}
					},
					async:{
						enable:true,
						url:contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/getDeptTreeOfWorkflowPriv.act?" +
						"flowId="+setting.flowId+"&prcsId="+setting.prcsId+"&",
						autoParam:["id"]
					},
					callback:{
						onClick:function(event,treeId,treeNode){
							if (treeNode.userCount>0) {
								var deptUser=getDeptUser(treeNode.id);
								showDeptUser(deptUser);
							}
						}
					}
					
				}
		);
	}
	function initSearchFormValidation(){
		
		$wf.find(".search-form").formValidation({
			framework:"bootstrap",
			err:{
				container:"tooltip"
			},
			icon: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields:{
				searchStr:{
					trigger:"keyup",
					validators:{
						notEmpty:{
							message:"请填写！"
						}
					}
				},
			}
		})
		.on("success.form.fv",function(e){
			e.preventDefault();
			
			var $form=$(e.target);
			$.ajax({
				url:contextPath+"/tfd/system/module/selectuser/act/SelectUserAct/searchUserOfWorkflow.act",
				data:$form.serialize()+"&flowId="+setting.flowId+"&prcsId="+setting.prcsId,
				type:"POST",
				dataType:"json",
				success:function(data){
					showDeptUser(data);
				}
			});
			
		});
	}
	
	function getDeptUser(deptId){
		var deptUserData=null;
		$.ajax({
			url:contextPath+"/tfd/system/module/selectuser/act/SelectUserAct/getWorkFlowUserJsonAct.act",
			dataType:"json",
			data:{deptId:deptId,flowId:setting.flowId,prcsId:setting.prcsId},
			async:false,
			success:function(data){
				deptUserData=data;
			}
		});
		return deptUserData;
	}
	
	function showDeptUser(deptUser){
		if(deptUser){
			for (var i = 0; i < deptUser.length; i++) {
				userMap[deptUser[i].id]=deptUser[i];
			}
			
			var deptUserHtml="";
			for(var i=0;i<deptUser.length;i++){
				var user=deptUser[i];
				
				var selectClass=(deptUser[i].id in selectedSponsorUser||deptUser[i].id in selectedUser)?" show-user-selected":"";
				var sponsorSelectClass=deptUser[i].id in selectedSponsorUser?"show-user-sponsor-selected":"show-user-sponsor-unselected";
				var nameSelectClass=deptUser[i].id in selectedUser?"show-user-name-selected":"show-user-name-unselected";
				var sponsorChecked=deptUser[i].id in selectedSponsorUser?"checked='checked'":"";
				
				deptUserHtml+="<div class='show-user"+selectClass+"' data-id='"+user.id+"'>"+
							  "	<div class='show-user-sponsor "+sponsorSelectClass+"' data-id='"+user.id+"'>" +
							  "		<input type='checkbox' "+sponsorChecked+"/>主办" +
							  "	</div>"+
							  "	<div class='show-user-name "+nameSelectClass+"' data-id='"+user.id+"'>" +
							  "		<img src='"+user.icon+"'/> "+user.name+
							  "	</div>" +
							  "</div>";
			}
			$wf.find(".dept-user-list").html(deptUserHtml);
		}
	}
	
	
	function loadSelectedUser(){
		
		var sponsorIdStr=$('#'+setting.container[0]).val();
		var idStr=$('#'+setting.container[1]).val();
		
		if(sponsorIdStr!=""){
			$.ajax({
				url:contextPath+"/tfd/system/unit/account/act/AccountAct/getUserListOfUserIdStr.act",
				data:{"userIdStr":sponsorIdStr},
				type:"POST",
				dataType:"json",
				async:false,
				success:function(data){
					for (var i = 0; i < data.length; i++) {
						var id=data[i].accountId;
						selectedSponsorUser[id]={id:id,name:data[i].userName};
						addSelectedSponsorUserEle(selectedSponsorUser[id]);
					}
				}
			});
		}
		if(idStr!=""){
			$.ajax({
				url:contextPath+"/tfd/system/unit/account/act/AccountAct/getUserListOfUserIdStr.act",
				data:{"userIdStr":idStr},
				type:"POST",
				dataType:"json",
				async:false,
				success:function(data){
					for (var i = 0; i < data.length; i++) {
						var id=data[i].accountId;
						selectedUser[id]={id:id,name:data[i].userName};
						addSelectedUserEle(selectedUser[id]);
					}
				}
			});
		}
	}
	
	function addSelectedUserEle(user){
		var checkedDivHtml="<div title='点击移除' class='selected-user' " +
						   "	data-id='"+user.id+"'>"+user.name+"</div>";
		$wf.find(".selected-user-list").append(checkedDivHtml);
	}
	function addSelectedSponsorUserEle(user){
		var checkedDivHtml="<div title='点击移除' class='selected-sponsor-user' " +
						   "	data-id='"+user.id+"'>"+user.name+"</div>";
		$wf.find(".selected-user-list").prepend(checkedDivHtml);
	}
});