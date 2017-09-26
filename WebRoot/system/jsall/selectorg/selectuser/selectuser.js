//部门树
var TREE_DEPT_LIST="deptList";
//部门人员数据显示区
var CONTAINER_DEPT_USER_LIST="deptUserList";
//部门人员显示元素id等取名前缀
var PREFIX_SHOW_DEPT_USER="showDeptUser_";
//部门人员显示元素class名称
var CLASS_SHOW_DEPT_USER="showUser";
//已选中的人员数据显示区
var CONTAINER_USER_CHECKED_LIST="userCheckedList";
//已选中的人员，显示元素id等取名前缀
var PREFIX_CHECKED_USER="checkedUser_";
//已选中的人员显示元素class名称
var CLASS_CHECKED_USER="checkedUser";
//操作,确定
var OPT_OK="opt_ok";
//操作，显示中的部门人员全选
var OPT_SHOW_USER_CHOSE_ALL="opt_show_user_chose_all";
//操作，显示中的部门人员反选
var OPT_SHOW_USER_DEL_ALL="opt_show_user_del_all";
//操作，选中的人员反选
var OPT_CHECKED_USER_DEL_ALL="opt_checked_user_del_all";
//操作，人员搜索
var OPT_USER_SEARCH="opt_user_search";
//对象，选中人员
var selectedUser=null;
//对象，父窗体
var parentWindow=null;
//对象，showModalDialog对话参数
var dialogParam=null;
//对象，父框架中存放人员的id的元素
var userId=null;
//对象，父框架中存放人员的name的元素
var userName=null;
//对象，人员选择是否单选
var userSelect=null;

var userArray=null;
var mainp=null;

var status='true';
//字段，人员搜索内容
var FIELD_USER_NAME="searchName";
/**
 * 2014-5-23 fzd 显示的部门人员鼠标悬浮操作
 * @param nodeId
 */
function showDeptUserMouseOveruser(nodeId){
	var user=getSelectedUser(nodeId);
	if(user==null){
		$("#"+PREFIX_SHOW_DEPT_USER+nodeId).css({
			"background":"#D2FEA5",
			"text-indent":"25px"
		});
	}
}
/**
 * 2014-5-23 fzd 显示的部门人员鼠标移出操作
 * @param nodeId
 */
function showDeptUserMouseOutuser(nodeId){
	var user=getSelectedUser(nodeId);
	if(user==null){
		$("#"+PREFIX_SHOW_DEPT_USER+nodeId).css({
			"background":"white",
			"text-indent":"15px"
		});
	}
}
/**
 * 2014-5-23 fzd 选中的人员鼠标悬浮操作
 */
function checkedUserMouseOveruser(nodeId){
	var checkedUserEle=$("#"+PREFIX_CHECKED_USER+nodeId);
	checkedUserEle.css({
		"background":"#D2FEA5",
		"text-indent":"25px"
	});
}
/**
 * 2014-5-23 fzd 选中的人员鼠标悬浮操作
 */
function checkedUserMouseOutuser(nodeId){
	var checkedUserEle=$("#"+PREFIX_CHECKED_USER+nodeId);
	checkedUserEle.css({
		"background":"white",
		"text-indent":"15px"
	});
}
/**
 * 2014-5-23 fzd 设置选中人员的样式
 * @param nodeId
 */
function setSelectedUserStyleuser(nodeId){
	var selectedUserEle=$("#"+PREFIX_SHOW_DEPT_USER+nodeId);
	selectedUserEle.css({
		"background":"#C2D9FF",
		"text-indent":"15px"
	});
	selectedUserEle.prop("checked",true);
}
/**
 * 2014-5-23 fzd 移除选中的人员
 * @param nodeId
 */
function removeSelectedUser(nodeId){
	for(var i=0;i<selectedUser.length;i++){
		if(selectedUser[i].id==nodeId){
			selectedUser.splice(i,1);
			break;
		}
	}
	if(nodeId=="userAll"){
		status='true';
	}
	$("#"+PREFIX_CHECKED_USER+nodeId).remove();
	if($("#"+PREFIX_SHOW_DEPT_USER+nodeId).length>0){
		$("#"+PREFIX_SHOW_DEPT_USER+nodeId).prop("checked",false);
		showDeptUserMouseOutuser(nodeId);
	}
}
/**
 * 2014-5-23 fzd 移除所有选中的人员
 * @param nodeId
 */
function removeAllSelectedUser(){
	var removedUser=selectedUser.slice(0,selectedUser.length);
	selectedUser=[];
	for (var i = 0; i < removedUser.length; i++) {
		var nodeId=removedUser[i].id;
		if(nodeId=="userAll"){
			status='true';
		}
		$("#"+PREFIX_CHECKED_USER+nodeId).remove();
		if($("#"+PREFIX_SHOW_DEPT_USER+nodeId).length>0){
			$("#"+PREFIX_SHOW_DEPT_USER+nodeId).prop("checked",false);
			showDeptUserMouseOutuser(nodeId);
		}
	}
}
/**
 * 2014-5-23 fzd 添加选中人员
 * @param nodeId
 */
function addSelectedUser(nodeId){
	var selectedUserEle=$("#"+PREFIX_SHOW_DEPT_USER+nodeId);
	if(status=='true'){
	if(selectedUserEle.prop("checked")){
		removeSelectedUser(nodeId);
	}else{
		if(userSelect=='true'){
			if(selectedUser){
				for(var i=0;i<selectedUser.length;i++){
					removeSelectedUser(selectedUser[i].id);
				}
			}
		}
		setSelectedUserStyleuser(nodeId);
		var user=getSelectedUser(nodeId);
		if(user==null){
			var userName=selectedUserEle.text();
			if(selectedUser==null){
				selectedUser=[];
			}
			selectedUser.push({id:nodeId,name:userName});
			addSelectedUserEleuser(nodeId,userName);
			if(userSelect=='true'){
				showSelectedDatauser();
				$("#myModal").modal('hide'); 
				if(mainp!=undefined){
					eval(mainp+"()");
				}
			}
		}
	}
	}
}
/**
 * 2014-5-23 fzd 添加选中的人员元素
 * @param userId
 * @param userName
 */
function addSelectedUserEleuser(userId,userName){
	var checkedDiv=createCheckedDivuser(userId,userName);
	$("#"+CONTAINER_USER_CHECKED_LIST).append(checkedDiv);
	initCheckedUserOptFuncuser();
}
/**
 * 2014-5-23 fzd 根据人员id查找选中人员
 * @param nodeId
 */
function getSelectedUser(userId){
	var user=null;
	if(selectedUser!=null){
		for(var i=0;i<selectedUser.length;i++){
			if(selectedUser[i].id==userId){
				user=selectedUser[i];
				break;
			}
		}
	}
	return user;
}

/**
 * 2014-4-23 fzd 初始化已选中的部门鼠标操作
 */
function initCheckedUserOptFuncuser(){
	$("."+CLASS_CHECKED_USER).each(function(){
		var nodeId=$(this).prop("id").replace(PREFIX_CHECKED_USER,"");
		$(this).mouseover(function(){
			checkedUserMouseOveruser(nodeId);
		});
		$(this).mouseout(function(){
			checkedUserMouseOutuser(nodeId);
		});
		$(this).click(function(){
			removeSelectedUser(nodeId);
		});
	});
}
/**
 * 2014-5-23 fzd 初始化显示的子节点鼠标操作
 */
function initShowDeptUserOptFuncuser(){
	$("."+CLASS_SHOW_DEPT_USER).each(function(){
		var nodeId=$(this).prop("id").replace(PREFIX_SHOW_DEPT_USER,"");
		$(this).mouseover(function(){
			showDeptUserMouseOveruser(nodeId);
		});
		$(this).mouseout(function(){
			showDeptUserMouseOutuser(nodeId);
		});
		$(this).click(function(){
			addSelectedUser(nodeId);
		});
	});
}
/**
 * 2014-5-23 fzd 初始化显示的部门人员样式
 */
function initShowDeptUserStyleuser(){
	$("."+CLASS_SHOW_DEPT_USER).each(function(){
		var nodeId=$(this).prop("id").replace(PREFIX_SHOW_DEPT_USER,"");
		var user=getSelectedUser(nodeId);
		if(user!=null){
			setSelectedUserStyleuser(nodeId);
		}else{
			$(this).prop("checked",false);
		}
	});
}
/**
 * 2014-5-23 fzd 创建一个选中的div
 * @param id
 * @param name
 * @returns
 */
function createCheckedDivuser(id,name){
	var checkedDiv="<div title='点击移除' id='"+PREFIX_CHECKED_USER+id+"' class='"+CLASS_CHECKED_USER+"'>"+name+"</div>";
	return checkedDiv;
}
/**
 * 2014-5-23 fzd 显示选中部门的部门人员数据
 */
function showDeptUserDatauser(deptUserData){
	if(deptUserData){
		$("#"+CONTAINER_DEPT_USER_LIST).html("");
		for(var i=0;i<deptUserData.length;i++){
			var deptUser=deptUserData[i];
			var deptUserDiv="<div class='"+CLASS_SHOW_DEPT_USER+"' id='"+PREFIX_SHOW_DEPT_USER+deptUser.id+"'><img src='"+deptUser.icon+"'/> "+deptUser.name+"</div>";
			$("#"+CONTAINER_DEPT_USER_LIST).append(deptUserDiv);
		}
		initShowDeptUserOptFuncuser();
		initShowDeptUserStyleuser();
	}
}
/**
 * 2014-5-23 fzd 节点单击触发动作
 */
function deptNodeClickuser(event,treeId,treeNode,clickFlag){
	/*if(treeNode.isParent){
		if(treeNode.children==null||treeNode.children.length==0){
			var childData=getDeptChildDatauser(treeNode);
			loadDeptChildDatauser(treeNode,childData,true);
		}
	}*/
	var deptUserData=getDeptUserDatauser(treeNode);
	showDeptUserDatauser(deptUserData);
}
/**
 * 2014-5-23 节点展开
 * @param event
 * @param treeId
 * @param treeNode
 */
function deptNodeExpanduser(event, treeId, treeNode) {
	if(treeNode.isParent){
		if(treeNode.children==null||treeNode.children.length==0){
			var childData=getDeptChildDatauser(treeNode);
			loadDeptChildDatauser(treeNode,childData,true);
		}
	}
}
/**
 * 2014-5-23 fzd 加载部门子节点数据
 * parentNode 父节点，没有请天null
 * newNodes 子节点数据
 * isSlient 父节点是否不展开
 */
function loadDeptChildDatauser(parentNode,newNodes,isSlient){
	var zTreeObj=$.fn.zTree.getZTreeObj(TREE_DEPT_LIST);
	zTreeObj.addNodes(parentNode,newNodes,isSlient);
}
/**
 * 2014-5-23 fzd 初始化操作，确定
 */
function initPageOptOkFuncuser(){
	$("#"+OPT_OK).click(function(){
		showSelectedDatauser();
		if(mainp!=undefined){
			eval(mainp+"()");
		}
		status='true';
	});
	$("#opt_all").click(function (){
		choiceAllUser();
		if(mainp!=undefined){
			eval(mainp+"()");
		}
		status='true';
	});
	$("#opt_remove").click(function (){
		selectedUser=null;
		status='true';
	});
}
//选择全部人员
function choiceAllUser(){
	$('#'+userId).val("userAll");
	$('#'+userName).val("全部人员");
}
/**
 * 2014-5-23 fzd 显示区中的所有部门人员全选
 */
function initOptShowDeptUserChoseAllFuncuser(){
	$("#"+OPT_SHOW_USER_CHOSE_ALL).click(function(){
		$(".showUser").each(function(){
			var nodeId=$(this).prop("id").replace(PREFIX_SHOW_DEPT_USER,"");
			addSelectedUser(nodeId);
		});
	});
}
/**
 * 2014-5-23 fzd 显示区中的所有部门人员反选
 */
function initOptShowDeptUserDelAllFuncuser(){
	$("#"+OPT_SHOW_USER_DEL_ALL).click(function(){
		$(".showUser").each(function(){
			var nodeId=$(this).prop("id").replace(PREFIX_SHOW_DEPT_USER,"");
			removeSelectedUser(nodeId);
		});
	});
}
/**	
 * 2014-5-23 fzd 已选择的人员反选
 */
function initOptCheckedUserDelAllFuncuser(){
	$("#"+OPT_CHECKED_USER_DEL_ALL).click(function(){
		if(selectedUser){
			removeAllSelectedUser();
		}
	});
}
/**
 * 2014-5-23 fzd 初始化操作，人员搜索
 */
function initOptUserSearchFuncuser(){
	$("#"+OPT_USER_SEARCH).click(function(){
		var searchData=getUserSearchDatauser();
		if(searchData){
			showDeptUserDatauser(searchData);
		}
	});
}
/**
 * 2014-5-23 fzd 在父级框架中显示选中的数据
 */
function showSelectedDatauser(){
	var ids="";
	var names="";
	if(selectedUser&&selectedUser.length>0){
		for(var i=0;i<selectedUser.length;i++){
			ids+=selectedUser[i].id+",";
			names+=selectedUser[i].name+",";
		}
	}
	ids=ids.length>0?ids.substring(0,ids.length-1):"";
	names=names.length>0?names.substring(0,names.length-1):"";
	$('#'+userId).val(ids);
	$('#'+userName).val(names);
}
/**
 * 2014-4-23 fzd 加载部门树
 */
function loadDeptListuser(){
	var setting={
			view:{
				selectMuilt:false//节点是否允许多选
			},
			callback:{
				onClick:deptNodeClickuser,
			},async:{
			enable:true,
			url:contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/getDeptChildJsonAct.act",
			autoParam:["id"]
		}
	};
	var nodeData =getDeptTopParentDatauser();
	$.fn.zTree.init($("#"+TREE_DEPT_LIST),setting,nodeData);
}
/**
 * 2014-4-30 fzd 加载已选中
 */
function loadSelectedDatauser(userArray){
			userId=userArray[0];
			userName=userArray[1];
		var userIdStr=$('#'+userId).val();
		var userNameStr=$('#'+userName).val();
		if(userIdStr!=""&& userIdStr!=undefined ){
			var url=contextPath+"/tfd/system/unit/account/act/AccountAct/getjsonUserNameStrAct.act";
			$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:{userNameStr:userIdStr},
		async:false,
		error:function(e){
		},
		success:function(data){
			selectedUser=[];
			for(var i = 0; i < data.length;i++){ 
				if(data[i].accountId=="userAll"){
					status='false';
				}
				selectedUser.push({id:data[i].accountId,name:data[i].userName});
				addSelectedUserEleuser(data[i].accountId,data[i].userName);
			}
		}
	});
		}else{
		selectedUser=[];	
		}
}
/**
 * 2014-5-9 fzd 获取传递参数
 */
function setDialogParamuser(singleSelect){
	userSelect=singleSelect;
}
/**
 * 2014-4-30 fzd 初始化页面操作
 */
function initPageOptFuncuser(){
	initPageOptOkFuncuser();
	initOptShowDeptUserChoseAllFuncuser();
	initOptShowDeptUserDelAllFuncuser();
	initOptCheckedUserDelAllFuncuser();
	initOptUserSearchFuncuser();
}

function userinit(userArray,singleSelect,mainpara){
	adduser(singleSelect);
	setDialogParamuser(singleSelect);
	loadSelectedDatauser(userArray);
	initCheckedUserOptFuncuser();
	loadDeptListuser();
	initPageOptFuncuser();
	mainp=mainpara;	
}
function selectUser(userArray,singleSelect,mainpara){
	userinit(userArray,singleSelect,mainpara);
	$("#opt_all").hide();
};
/**
 * 2014-5-23 fzd 获取部门顶级初始数据
 */
function getDeptTopParentDatauser(){
	var deptJson;
	var requestUrl=contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/getDeptTopJsonAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		success:function(data){
			deptJson=data;
		}
	});
	return deptJson;
}
/**
 * 2014-5-23 fzd 获取部门子节点数据
 * nodeId 节点id属性值
 */
// function getDeptChildDatauser(node){
	// var parentId=node.id;
	// var json;
	// var requestUrl=contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/getDeptChildJsonAct.act";
	// $.ajax({
		// url:requestUrl,
		// dataType:"json",
		// data:{deptId:parentId},
		// async:false,
		// success:function(data){
			// json=data;
		// }
	// });
	// return json;
// }
/**
 * 2014-5-23 fzd 获取部门人员数据
 * @param deptNode
 */
function getDeptUserDatauser(deptNode){
	var deptUserData;
	var deptId=deptNode.id;
	var requestUrl=contextPath+"/tfd/system/module/selectuser/act/SelectUserAct/getDeptUserJsonAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{deptId:deptId},
		async:false,
		success:function(data){
			deptUserData=data;
		}
	});
	return deptUserData;
}
/**
 * 2014-5-23 fzd 获取部门搜索的数据
 */
function getUserSearchDatauser(){
	
	var searchContent=$.trim($("#"+FIELD_USER_NAME).val());
	if(searchContent==""){
		parent.layer.msg('请填写搜索内容！',function(){});
		return null;
	}else{
		var requestUrl=contextPath+"/tfd/system/module/selectuser/act/SelectUserAct/searchNameUserAct.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{searchName:searchContent},
			async:false,
			success:function(data){
				if(data!=""){
				deptUserData=data;
				showDeptUserDatauser(deptUserData);
				}
				else{					
					parent.layer.msg('不存在此员工！',function(){});
				}
			}
		});
		
	}
}