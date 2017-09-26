//部门树
var TREE_DEPT_LIST="deptList";
//父节点下的子节点数据显示区
var CONTAINER_DEPT_CHILD_LIST="deptChildList";
//子节点数据显示，子节点显示元素id等取名前缀
var PREFIX_SHOW_CHILD="showChild_";
//子节点数据显示，子节点显示元素class名称
var CLASS_SHOW_CHILD="showChild";
//已选中的部门数据显示区
var CONTAINER_DEPT_CHECKED_LIST="deptCheckedList";
//已选中的部门，显示元素id等取名前缀
var PREFIX_CHECKED_DEPT="checkedDept_";
//已选中的部门显示元素class名称
var CLASS_CHECKED_DEPT="checkedDept";
//操作,确定
var OPT_OK="opt_ok";
//操作，取消
var OPT_CANCEL="opt_cancel";
//操作，显示中的子部门全选
var OPT_SHOW_CHILD_CHOSE_ALL="opt_show_child_chose_all";
//操作，显示中的子部门反选
var OPT_SHOW_CHILD_DEL_ALL="opt_show_child_del_all";
//操作，选中的部门反选
var OPT_CHECKED_DEPT_DEL_ALL="opt_checked_dept_del_all";
//操作，部门搜索
var OPT_DEPT_SEARCH="opt_dept_search";
//对象，选中的部门
var selectedDept=null;
//对象，父窗体
var parentWindow=null;
//对象，showModalDialog对话参数
var dialogParam=null;
//对象，父框架中存放部门的id的元素
var deptId=null;
//对象，父框架中存放部门的name的元素
var deptName=null;
//对象，部门选择是否单选
var deptSelect=null;
var maindept=null;

//判断是否可以选择其他人员
var status='true';
//字段，部门搜索内容
var FIELD_DEPT_NAME="searchdept";
/**
 * 2014-5-9 fzd 显示的子部门鼠标悬浮操作
 * @param nodeId
 */

function showChildMouseOver(nodeId){
	var dept=getSelectedDept(nodeId);
	if(dept==null){
		$("#"+PREFIX_SHOW_CHILD+nodeId).css({
			"background":"#D2FEA5",
			"text-indent":"25px"
		});
	}
}
/**
 * 2014-5-9 fzd 显示的子部门鼠标移出操作
 * @param nodeId
 */
function showChildMouseOut(nodeId){
	var dept=getSelectedDept(nodeId);
	if(dept==null){
		$("#"+PREFIX_SHOW_CHILD+nodeId).css({
			"background":"white",
			"text-indent":"15px"
		});
	}
}
/**
 * 2014-5-9 fzd 选中的部门鼠标悬浮操作
 */
function checkedDeptMouseOver(nodeId){
	var checkedDeptEle=$("#"+PREFIX_CHECKED_DEPT+nodeId);
	checkedDeptEle.css({
		"background":"#D2FEA5",
		"text-indent":"25px"
	});
}
/**
 * 2014-5-9 fzd 选中的部门鼠标悬浮操作
 */
function checkedDeptMouseOut(nodeId){
	var checkedDeptEle=$("#"+PREFIX_CHECKED_DEPT+nodeId);
	checkedDeptEle.css({
		"background":"white",
		"text-indent":"15px"
	});
}
/**
 * 2014-5-9 fzd 设置选中部门的样式
 * @param nodeId
 */
function setSelectedDeptStyle(nodeId){
	var selectedDeptEle=$("#"+PREFIX_SHOW_CHILD+nodeId);
	selectedDeptEle.css({
		"background":"#C2D9FF",
		"text-indent":"15px"
	});
	selectedDeptEle.prop("checked",true);
}
/**
 * 2014-5-9 fzd 移除选中的部门
 * @param nodeId
 */
function removeSelectedDept(nodeId){
	for(var i=0;i<selectedDept.length;i++){
		if(selectedDept[i].id==nodeId){
			selectedDept.splice(i,1);
			break;
		}
	}
	if(nodeId=="deptAll"){
		status='true';
	}
	$("#"+PREFIX_CHECKED_DEPT+nodeId).remove();
	if($("#"+PREFIX_SHOW_CHILD+nodeId).length>0){
		$("#"+PREFIX_SHOW_CHILD+nodeId).prop("checked",false);
		showChildMouseOut(nodeId);
	}
}
/**
 * 2014-5-9 fzd 移除选中的部门
 * @param nodeId
 */
function removeAllSelectedDept(){
	var removedDept=selectedDept.slice(0,selectedDept.length);
	selectedDept=[];
	for (var i = 0; i < removedDept.length; i++) {
		var nodeId=removedDept[i].id;
		if(nodeId=="deptAll"){
			status='true';
		}
		$("#"+PREFIX_CHECKED_DEPT+nodeId).remove();
		if($("#"+PREFIX_SHOW_CHILD+nodeId).length>0){
			$("#"+PREFIX_SHOW_CHILD+nodeId).prop("checked",false);
			showChildMouseOut(nodeId);
		}
	}
}
/**
 * 2014-5-9 fzd 添加选中部门
 * @param nodeId
 */
function addSelectedDept(nodeId){
	var selectedDeptEle=$("#"+PREFIX_SHOW_CHILD+nodeId);
	if(status=='true'){
	if(selectedDeptEle.prop("checked")){
		removeSelectedDept(nodeId);
	}else{		
		if(deptSelect=='true'){
			if(selectedDept){
				for(var i=0;i<selectedDept.length;i++){
					removeSelectedDept(selectedDept[i].id);
				}
			}
		}
		setSelectedDeptStyle(nodeId);
		var dept=getSelectedDept(nodeId);
		if(dept==null){
			var deptName=selectedDeptEle.text();
			if(selectedDept==null){
				selectedDept=[];
			}
			selectedDept.push({id:nodeId,name:deptName});
			addSelectedDeptEle(nodeId,deptName);
			if(deptSelect=='true'){
				showSelectedData();
				$("#myModal").modal('hide'); 
				if(maindept!=undefined){
					eval(maindept+"()");
				}
			}
		}
	}
	}
}
/**
 * 2014-5-9 fzd 添加选中的部门元素
 * @param deptId
 * @param deptName
 */
function addSelectedDeptEle(deptId,deptName){
	var checkedDiv=createCheckedDiv(deptId,deptName);
	$("#"+CONTAINER_DEPT_CHECKED_LIST).append(checkedDiv);
	initCheckedDeptOptFunc();
}
/**
 * 2014-5-9 fzd 根据部门id查找选中部门
 * @param nodeId
 */
function getSelectedDept(deptId){
	var dept=null;
	if(selectedDept!=null){
		for(var i=0;i<selectedDept.length;i++){
			if(selectedDept[i].id==deptId){
				dept=selectedDept[i];
				break;
			}
		}
	}
	return dept;
}

/**
 * 2014-4-23 fzd 初始化已选中的部门鼠标操作
 */
function initCheckedDeptOptFunc(){
	$("."+CLASS_CHECKED_DEPT).each(function(){
		var nodeId=$(this).prop("id").replace(PREFIX_CHECKED_DEPT,"");
		$(this).mouseover(function(){
			checkedDeptMouseOver(nodeId);
		});
		$(this).mouseout(function(){
			checkedDeptMouseOut(nodeId);
		});
		$(this).click(function(){
			removeSelectedDept(nodeId);
		});
	});
}
/**
 * 2014-4-23 fzd 初始化显示的子节点鼠标操作
 */
function initShowChildOptFunc(){
	$("."+CLASS_SHOW_CHILD).each(function(){
		var nodeId=$(this).prop("id").replace(PREFIX_SHOW_CHILD,"");
		$(this).mouseover(function(){
			showChildMouseOver(nodeId);
		});
		$(this).mouseout(function(){
			showChildMouseOut(nodeId);
		});
		$(this).click(function(){
			addSelectedDept(nodeId);
		});
	});
}
/**
 * 2014-5-9 fzd 初始化显示的子部门样式
 */
function initShowChildStyle(){
	$("."+CLASS_SHOW_CHILD).each(function(){
		var nodeId=$(this).prop("id").replace(PREFIX_SHOW_CHILD,"");
		var dept=getSelectedDept(nodeId);
		if(dept!=null){
			setSelectedDeptStyle(nodeId);
		}else{
			$(this).prop("checked",false);
		}
	});
}
/**
 * 2014-4-30 fzd 创建一个选中的div
 * @param id
 * @param name
 * @returns
 */
function createCheckedDiv(id,name){
	var checkedDiv="<div title='点击移除' id='"+PREFIX_CHECKED_DEPT+id+"' class='"+CLASS_CHECKED_DEPT+"'>"+name+"</div>";
	return checkedDiv;
}
/**
 * 2014-4-23 fzd 显示选中部门的子部门数据
 */
function showDeptChildData(childData){
	if(childData){
		$("#"+CONTAINER_DEPT_CHILD_LIST).html("");
		for(var i=0;i<childData.length;i++){
			var childNode=childData[i];
			var childDiv="<div class='"+CLASS_SHOW_CHILD+"' id='"+PREFIX_SHOW_CHILD+childNode.id+"'><img src='"+childNode.icon+"'/>"+childNode.name+"</div>";
			$("#"+CONTAINER_DEPT_CHILD_LIST).append(childDiv);
		}
		initShowChildOptFunc();
		initShowChildStyle();
	}
}
/**
 * 2014-4-23 fzd 节点单击触发动作
 */
function deptNodeClick(event,treeId,treeNode){
		var childData=null;
		// if(treeNode.children==null||treeNode.children.length==0){
			// childData=getDeptChildData(treeNode);
			// loadDeptChildData(treeNode,childData,true);
		// }
		var showChildData=[];
		showChildData.push({id:treeNode.id,name:treeNode.name,isParent:treeNode.isParent,icon:treeNode.icon});
		var childNodes=getDeptChildData(treeNode);
		for (var i=0; i < childNodes.length; i++) {
			showChildData.push({id:childNodes[i].id,name:childNodes[i].name,isParent:childNodes[i].isParent,icon:childNodes[i].icon});
		};
		// var childNodes=treeNode.children;
		// for (var i = 0; i < childNodes.length; i++) {
			// showChildData.push({id:childNodes[i].id,name:childNodes[i].name,isParent:childNodes[i].isParent});
		// }
		//var showChildData=getDeptChildDataOfShow(treeNode);
		showDeptChildData(showChildData);
}
/**
 * 2014-5-27 fzd 获取显示的子部门数据，包括点击的父级部门和该部门下的子部门数据
 * @param treeNode
 * @returns {Array}
 */
// function getDeptChildDataOfShow(treeNode){
	// var showChildData=[];
	// showChildData.push({id:treeNode.id,name:treeNode.name,isParent:treeNode.isParent});
	// var childNodes=treeNode.children;
	// for (var i = 0; i < childNodes.length; i++) {
		// showChildData.push({id:childNodes[i].id,name:childNodes[i].name,isParent:childNodes[i].isParent});
	// }
	// return showChildData;
// }
/**
 * 2014-4-23 fzd 加载部门子节点数据
 * parentNode 父节点，没有请天null
 * newNodes 子节点数据
 * isSlient 父节点是否不展开
 */
function loadDeptChildData(parentNode,newNodes,isSlient){
	var zTreeObj=$.fn.zTree.getZTreeObj(TREE_DEPT_LIST);
	zTreeObj.addNodes(parentNode,newNodes,isSlient);
}
/**
 * 2014-4-30 fzd 初始化操作，确定
 */
function initPageOptOkFunc(){
	$("#"+OPT_OK).click(function(){
		showSelectedData();
		if(maindept!=undefined){
			eval(maindept+"()");
		}
		status='true';
	});
	$("#opt_all").click(function (){
		choiceAll();
		if(maindept!=undefined){
			eval(maindept+"()");
		}
		status='true';
	});
	$("#opt_remove").click(function (){
		selectedDept=null;
		status='true';
	});
}
//选择全体部门
function choiceAll(){
	$('#'+deptId).val("deptAll");
	$('#'+deptName).val("全部部门");
}
/**
 * 2014-5-9 fzd 显示区中的所有子部门全选
 */
function initOptShowChildChoseAllFunc(){
	$("#"+OPT_SHOW_CHILD_CHOSE_ALL).click(function(){
		$(".showChild").each(function(){
			var nodeId=$(this).prop("id").replace(PREFIX_SHOW_CHILD,"");
			addSelectedDept(nodeId);
		});
	});
}
/**
 * 2014-5-9 fzd 显示区中的所有子部门反选
 */
function initOptShowChildDelAllFunc(){
	$("#"+OPT_SHOW_CHILD_DEL_ALL).click(function(){
		$(".showChild").each(function(){
			var nodeId=$(this).prop("id").replace(PREFIX_SHOW_CHILD,"");
			removeSelectedDept(nodeId);
		});
	});
}
/**
 * 2014-5-9 fzd 已选择的部门反选
 */
function initOptCheckedDeptDelAllFunc(){
	$("#"+OPT_CHECKED_DEPT_DEL_ALL).click(function(){
		if(selectedDept){
			removeAllSelectedDept();
		}
	});
}
/**
 * 2014-5-11 fzd 初始化操作，部门搜索
 */
function initOptDeptSearchFunc(){
	$("#"+OPT_DEPT_SEARCH).click(function(){
		var searchData=getDeptSearchData();
		if(searchData!=""){
			showDeptChildData(searchData);
		}else{
			parent.layer.msg('不存在符合的部门',function(){});
		}
	});
}
/**
 * 2014-4-30 fzd 在父级框架中显示选中的数据
 */
function showSelectedData(){
	var ids="";
	var names="";
	if(selectedDept&&selectedDept.length>0){
		for(var i=0;i<selectedDept.length;i++){
			ids+=selectedDept[i].id+",";
			names+=selectedDept[i].name+",";
		}
	}
	ids=ids.length>0?ids.substring(0,ids.length-1):"";
	names=names.length>0?names.substring(0,names.length-1):"";
		$('#'+deptId).val(ids);
		$('#'+deptName).val(names);
}
/**
 * 2014-4-23 fzd 加载部门树
 */
function loadDeptList(){
	var setting={
			view:{
				selectMuilt:false//节点是否允许多选
			},
			callback:{
				onClick:deptNodeClick
			},async:{
			enable:true,
			url:contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/getDeptChildJsonAct.act",
			autoParam:["id"]
		}
	};
	var nodeData =getDeptTopParentData();
	$.fn.zTree.init($("#"+TREE_DEPT_LIST),setting,nodeData);
}
/**
 * 2014-4-30 fzd 加载已选中
 */
function loadSelectedData(deptArray){
		deptId=deptArray[0];
		deptName=deptArray[1];
		var deptIdStr=$('#'+deptId).val();
		var deptNameStr=$('#'+deptName).val();
		if(deptIdStr!=""&& deptIdStr!=undefined ){
		var url=contextPath+"/tfd/system/unit/dept/act/DeptAct/getjsondeptNameStrAct.act";
			$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:{deptNameStr:deptIdStr},
		async:false,
		error:function(e){
		},
		success:function(data){
			selectedDept=[];
			for(var i = 0; i < data.length;i++){ 
				if(data[i].deptId=="deptAll"){
					status='false';
				}
				selectedDept.push({id:data[i].deptId,name:data[i].deptName});
				addSelectedDeptEle(data[i].deptId,data[i].deptName);
			}
		}
	});
		}
		else{
			selectedDept=[];
		}
}
/**
 * 2014-5-9 fzd 获取传递参数
 */
function setDialogParam(singleSelect){
	deptSelect=singleSelect;
}
/**
 * 2014-4-30 fzd 初始化页面操作
 */
function initPageOptFunc(){
	initPageOptOkFunc();
	initOptShowChildChoseAllFunc();
	initOptShowChildDelAllFunc();
	initOptCheckedDeptDelAllFunc();
	initOptDeptSearchFunc();
}
function deptinit(deptArray,singleSelect,mainpara){
	adddept(singleSelect);
	setDialogParam(singleSelect);
	loadSelectedData(deptArray);
	initCheckedDeptOptFunc();
	loadDeptList();
	initPageOptFunc();
	maindept=mainpara;
}
/**
 * 2014-4-23 fzd 获取部门顶级初始数据
 */
function getDeptTopParentData(){
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
 * 2014-4-23 fzd 获取部门子节点数据
 * node 节点
 */
function getDeptChildData(node){
	var parentId=node.id;
	var json;
	var requestUrl=contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/getDeptChildJsonAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{id:parentId},
		async:false,
		success:function(data){
			json=data;
		}
	});
	return json;
}
/**
 * 2014-5-11 fzd 获取部门搜索的数据
 */
function getDeptSearchData(){
	var searchContent=$.trim($("#"+FIELD_DEPT_NAME).val());
	if(searchContent==""){
		parent.layer.msg('请填写搜索内容！',function(){});
		return null;
	}else{
		var requestUrl=contextPath+"/tfd/system/module/selectdept/act/SelectDeptAct/serachdeptAct.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{deptName:searchContent},
			async:false,
			success:function(data){
				json=data;
			}
		});
		return json;
	}
}