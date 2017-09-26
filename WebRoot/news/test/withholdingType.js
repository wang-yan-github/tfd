var jq = jQuery.noConflict();
jq(function(){
	var strinfo = localStorage["strinfo"];
	if(strinfo != undefined && strinfo !=""){
		jq("#searchWith").val(strinfo);
	}
	localStorage["strinfo"]="";
	jq("#searchWith").focus(function(){
		jq("#searchWith").val("");
	})
	jq("#queryWithContract").focus(function(){
		jq("#queryWithContract").val("");
	})
	
	//全选按钮
	jq("#SelectAllWith").click(function(){
		if (jq("#SelectAllWith").attr("checked")) {
	    	jq("input[mark='checkwith']").attr("checked",'true');
	    	jq(".check-box").addClass("checkedBox");
	    } else {  
	        jq("input[mark='checkwith']").removeAttr("checked");
	        jq(".check-box").removeClass("checkedBox");
	    }  
	})
	
})

//查询合同信息
function resultSearchwith(){
	var searchContract = jq("#queryWithContract").val();
	if(searchContract=='请输入查询关键字'){
		searchContract="";
	}
	var url="/OA/finance/querywithholdingType.jsp";
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
		'searchContract':searchContract
	},
		async : false,
	    success : function(data) {
			 var info=data.contractInfo.contractInfo;
			 console.info(info);
			 var str ="";
			 jq("#queryContractInfo").find("tr[mark='showContract']").remove();
			 for(var i = 0;i < info.length; i ++){
				 	str += "<tr mark='showContract' ><td style='color:#7AABC2;cursor:pointer;' onclick='addContractTitle(this)' value='"+info[i].id+"' title='"+info[i].title+"'><p style='width:100px' class='infonations'>"+info[i].title+"</p></td>";
	        		str += "<td title='"+info[i].contracttype+"'><p style='width:50px' class='infonations'>"+info[i].contracttype+"</p></td>";
	        		str += "<td title='"+info[i].contractnumber+"'><p style='width:50px' class='infonations'>"+info[i].contractnumber+"</p></td>";
	        		str += "<td title='"+info[i].department+"'><p style='width:50px' class='infonations'>"+info[i].department+"</p></td>";
	        		str += "<td title='"+info[i].userincharge+"'><p style='width:50px' class='infonations'>"+info[i].userincharge+"</p></td>";
	        		str += "<td title='"+info[i].contractamount+"'><p style='width:50px' class='infonations'>"+info[i].contractamount+"</p></td>";
	        		str += "<td title='"+info[i].partyb+"'><p style='width:50px' class='infonations'>"+info[i].partyb+"</p> </td></tr>";
	        		
	    		}
			 jq("#queryContractInfo").append(str);
			}
	});
}
//导入excel
function importWithFile(){
	 var txtfile =jq("#textfieldWith").val();
	 if(txtfile == "")
		{
			alert("请选择文件");
			return false;
		}
	 	var fileReg = /.*\.xls$/;
	 	//校验文件后缀
		if(!txtfile.match(fileReg))
		{
			alert("文件格式不正确");
			return false;
		}
	 var url='/OA/importWithFile.do';
	 jq("#importwithid").attr('disabled','true');
	 jq.ajaxFileUpload({
            url:url, //你处理上传文件的服务端
            secureuri:false,
            dataType: 'json',
            fileElementId:'fileFieldWith',
            type: 'post',
            data: { 'textfield' : txtfile},
             success:function(data){
            	jq("#msg").text("");
            	var message = data.result.message;
            	if(message=='title'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,标题不能为空！");
            		
            	}else if(message=='applicant'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,发起人不能为空！");
            		
            	}else if(message=='CUCXW_CO'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,公司代码不能为空！");
            		
            		
            	}else if(message=='CUCXW_CC'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,部门代码不能为空！");
            		
            	}else if(message=='CUCXW_AC_Debit'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,借方会计科目代码不能为空！");
            		
            	}else if(message=='CUCXW_AC_Credit'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,贷方会计科目代码不能为空！");
            		
            	}else if(message=='memo'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,类型说明不能为空！");
            		
            	}else if(message=='withholdAmount'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,累计预提金额不合法！");
            		
            	}else if(message=='paymentAmount'){
            		jq("#importwithid").removeAttr("disabled");
            		alert("导入失败,累计付款金额不合法！");
            		
            	}else{
            		alert("导入成功");
            		jq('.fancybox-close').click();//关闭弹窗
            		window.location.href="/OA/finance/contractInfo.jsp?pagewith=1&id=withholdOn"
            	}
              },
              error:function(err){
            	  
              }
         });
}


//导出预提类别
function exportWith(){
	 var url='/OA/exportWithholdType.do';
	 jq.ajaxFileUpload({
           url:url, 
           secureuri:false,
           dataType: 'json',
           type: 'post',
           data: {},
            success:function(data){
        	   alert("导出成功，请到桌面查看！");
           	 window.location.href="/OA/finance/contractInfo.jsp?pagewith=1&id=withholdOn"
             },
             error:function(err){
           	  
             }
        });
}

function copyValue(param){
	 var www =jq("#fileFieldWith").val();
	    jq("#textfieldWith").val(www);
}

//点击新增按钮
function addWithholdType(){
	jq("#addTitle").val("");
	jq("#attendPersonId").val("");
	jq("#attendContractId").val("");
	jq("#addCUCXW_CO").val("");//公司代码
	jq("#addCUCXW_CC").val("");//部门代码
	jq("#addCUCXW_AC_Debit").val("");//借方会计科目代码
	jq("#addCUCXW_AC_Credit").val("");//贷方会计科目代码
	jq("#addMemo").val("");//类型说明
	jq("#addWithholdAmount").val("");//累计预提金额
	jq("#addPaymentAmount").val("");//累计付款金额
	jq("#male").attr("checked","checked");//是否有效
	jq("#male2").attr("checked","");//是否有效
	jq('#selectedUserId').html("");
	jq('#contractNameId').html("");
	jq("#attendPersonLoginNameId").val("");
	selUserList="";
	selLoginnameList = "";     
	selConList = ""; 
	selContractList="";
	
}
//新增点击确定按钮
function addfunOk(){
	var title=jq.trim(jq("#addTitle").val());
	var attendPersonId=jq.trim(jq("#attendPersonId").val());
	var attendContractId=jq.trim(jq("#attendContractId").val());
	var attendPersonLoginNameId=jq.trim(jq("#attendPersonLoginNameId").val());//发起人登录名
	var attendContractNameId=jq.trim(jq("#attendContractNameId").val());//合同id
	var addCUCXW_CO=jq.trim(jq("#addCUCXW_CO").val());//公司代码
	var addCUCXW_CC=jq.trim(jq("#addCUCXW_CC").val());//部门代码
	var addCUCXW_AC_Debit=jq.trim(jq("#addCUCXW_AC_Debit").val());//借方会计科目代码
	var addCUCXW_AC_Credit=jq.trim(jq("#addCUCXW_AC_Credit").val());//贷方会计科目代码
	var addMemo=jq.trim(jq("#addMemo").val());//类型说明
	var addWithholdAmount=jq.trim(jq("#addWithholdAmount").val());//累计预提金额
	var addPaymentAmount=jq.trim(jq("#addPaymentAmount").val());//累计付款金额
	var isvalid='';
	if(jq("#male").attr("checked")=='checked'){
		isvalid="是";
	}else{
		isvalid="否";
	}
	if(title.length == 0){
		alert("标题不能为空");
		return;
	}
	var arr =attendPersonId.split(';');
	if(attendPersonId.length == 0||arr.length>1){
		alert("发起人只能选择一人且不能为空");
		return;
	}
	var arrcon =attendContractId.split(';');
	if(arrcon.length>1){
		alert("匹配合同只能选择一个");
		return;
	}
	
	if(addCUCXW_CO.length == 0){
		alert("公司代码不能为空");
		return;
	}
	
	if(addCUCXW_CC.length == 0){
		alert("部门不能为空");
		return;
	}
	if(addMemo.length == 0){
		alert("类型说明不能为空");
		return;
	}
	if(addCUCXW_AC_Debit.length == 0){
		alert("借方会计科目代码不能为空");
		return;
	}
	if(addCUCXW_AC_Credit.length == 0){
		alert("贷方会计科目代码不能为空");
		return;
	}
	var reg= /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(addWithholdAmount.length != 0){
		if(!reg.test(addWithholdAmount)){
			alert("请输入有效的累计预提金额");
			return;
		}
	}
	if(addPaymentAmount.length != 0){
		if(!reg.test(addPaymentAmount)){
			alert("请输入有效的累计付款金额");
			return;
		}
	}
	
	var url="/OA/finance/withholdingType.jsp";
	jq("#addwithid").attr('disabled','true');
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
<<<<<<< .mine
		'title' : title,
 		'budgetyear' : budgetyear,
 		'salarybudget' : salarybudget,
 		'hrcostbudget' : hrcostbudget,
 		'salaryamount' : salaryamount,
 		'hrcostamount' : hrcostamount,
 		'salaryamountaccrued' : salaryamountaccrued,
 		'hrcostamountaccrued' : hrcostamountaccrued
=======
		'addTitle' : title,
 		'attendPersonId' : attendPersonLoginNameId,
 		'attendContractId' : attendContractNameId,
 		'addCUCXW_CO' : addCUCXW_CO,
 		'addCUCXW_CC' : addCUCXW_CC,
 		'addCUCXW_AC_Debit' : addCUCXW_AC_Debit,
 		'addCUCXW_AC_Credit' : addCUCXW_AC_Credit,
 		'addMemo' : addMemo,
 		'addWithholdAmount' : addWithholdAmount,
 		'addPaymentAmount' : addPaymentAmount,
 		'isvalid' : isvalid
>>>>>>> .r2838
		},
		async : false,
	    success : function(data) {
			 alert("操作成功！");
			 jq('.fancybox-close').click();//关闭弹窗
			 window.location.href="/OA/finance/contractInfo.jsp?pagewith=1&id=withholdOn";
			}
		
	});
}
//点击编辑按钮
function editWithholdType(id,flag){
	jq("#editWithholdId").val(id);
	//取值
	var title=jq("#title"+id).text();
	var applicant=jq("#applicant"+id).text();
	var contract=jq("#contract"+id).text();
	var cucxw_co=jq("#cucxw_co"+id).text();
	var cucxw_cc=jq("#cucxw_cc"+id).text();
	var cucxw_ac_debit=jq("#cucxw_ac_debit"+id).text();
	var cucxw_ac_credit=jq("#cucxw_ac_credit"+id).text();
	var memo=jq("#memo"+id).text();
	var withholdamount=jq("#withholdamount"+id).text();
	var paymentamount=jq("#paymentamount"+id).text();
	var isvalid=jq("#isvalidss"+id).text();
	//赋值
	jq("#editTitle").val(title);
	jq("#editattendPersonId").val(applicant);
	jq("#editattendContractId").val(contract);
	jq("#editCUCXW_CO").val(cucxw_co);
	jq("#editCUCXW_CC").val(cucxw_cc);
	jq("#editCUCXW_AC_Debit").val(cucxw_ac_debit);
	jq("#editCUCXW_AC_Credit").val(cucxw_ac_credit);
	jq("#editMemo").val(memo);
	jq("#editWithholdAmount").val(withholdamount);
	jq("#editPaymentAmount").val(paymentamount);
	if(isvalid=='是'){
		jq("#maleedit").attr("checked","checked");
	}else{
		jq("#male2edit").attr("checked","checked");
	}
	if(flag=='2'){
		//赋值
		jq("#editmsg").text("查看");
		jq("#editwithid").attr("disabled","true");
		jq("#editTitle").attr("readonly","readonly");
		jq("#editattendPersonId").attr("readonly","readonly");
		jq("#editattendContractId").attr("readonly","readonly");
		jq("#editCUCXW_CO").attr("readonly","readonly");
		jq("#editCUCXW_CC").attr("readonly","readonly");
		jq("#editCUCXW_AC_Debit").attr("readonly","readonly");
		jq("#editCUCXW_AC_Credit").attr("readonly","readonly");
		jq("#editMemo").attr("readonly","readonly");
		jq("#editWithholdAmount").attr("readonly","readonly");
		jq("#editPaymentAmount").attr("readonly","readonly");
		jq("#maleedit").attr("disabled","true");
		jq("#male2edit").attr("disabled","true");
	}else{
		//赋值
		jq("#editmsg").text("编辑");
		jq("#editwithid").removeAttr("disabled");
		jq("#editTitle").removeAttr("readonly");
		jq("#editattendPersonId").removeAttr("readonly");
		jq("#editattendContractId").removeAttr("readonly");
		jq("#editCUCXW_CO").removeAttr("readonly");
		jq("#editCUCXW_CC").removeAttr("readonly");
		jq("#editCUCXW_AC_Debit").removeAttr("readonly");
		jq("#editCUCXW_AC_Credit").removeAttr("readonly");
		jq("#editMemo").removeAttr("readonly");
		jq("#editWithholdAmount").removeAttr("readonly");
		jq("#editPaymentAmount").removeAttr("readonly");
		jq("#maleedit").removeAttr("disabled");
		jq("#male2edit").removeAttr("disabled");
	}
	
}
//点击编辑确定按钮
function editfunOk(){
	var id=jq("#editWithholdId").val();
	//获取修改的值
	var editTitle=jq("#editTitle").val();
	var editattendPersonId=jq("#editattendPersonId").val();
	var editattendContractId=jq("#editattendContractId").val();
	var editattendPersonLoginNameId=jq("#editattendPersonLoginNameId").val();//发起人long_name
	var editattendContractNameId=jq("#editattendContractNameId").val();//合同id
	var editCUCXW_CO=jq("#editCUCXW_CO").val();
	var editCUCXW_CC=jq("#editCUCXW_CC").val();
	var editCUCXW_AC_Debit=jq("#editCUCXW_AC_Debit").val();
	var editCUCXW_AC_Credit=jq("#editCUCXW_AC_Credit").val();
	var editMemo=jq("#editMemo").val();
	var editWithholdAmount=jq("#editWithholdAmount").val();
	var editPaymentAmount=jq("#editPaymentAmount").val();
	var editisvalid='';
	if(jq("#maleedit").attr("checked")=='checked'){
		editisvalid="是";
	}else{
		editisvalid="否";
	}
	
	if(editTitle.length == 0){
		alert("标题不能为空");
		return;
	}
	var earr =editattendPersonId.split(';');
	if(editattendPersonId.length == 0||earr.length>1){
		alert("发起人只能选择一人且不能为空");
		return;
	}
	var earrcon =editattendContractId.split(';');
	if(earrcon.length>1){
		alert("匹配合同只能选择一个");
		return;
	}
	
	if(editCUCXW_CO.length == 0){
		alert("公司代码不能为空");
		return;
	}
	
	if(editCUCXW_CC.length == 0){
		alert("部门不能为空");
		return;
	}
	
	if(editMemo.length == 0){
		alert("类型说明不能为空");
		return;
	}
	if(editCUCXW_AC_Debit.length == 0){
		alert("借方会计科目代码不能为空");
		return;
	}
	if(editCUCXW_AC_Credit.length == 0){
		alert("贷方会计科目代码不能为空");
		return;
	}
	var reg= /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(editWithholdAmount.length != 0){
		if(!reg.test(editWithholdAmount)){
			alert("请输入有效的累计预提金额");
			return;
		}
	}
	if(editPaymentAmount.length != 0){
		if(!reg.test(editPaymentAmount)){
			alert("请输入有效的累计付款金额");
			return;
		}
	}
	
	var url="/OA/finance/editWithholdingType.jsp";
	jq("#editwithid").attr('disabled','true');
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
		'editTitle' : editTitle,
 		'editattendPersonId' : editattendPersonLoginNameId,
 		'editattendContractId' : editattendContractNameId,
 		'editCUCXW_CO' : editCUCXW_CO,
 		'editCUCXW_CC' : editCUCXW_CC,
 		'editCUCXW_AC_Debit' : editCUCXW_AC_Debit,
 		'editCUCXW_AC_Credit' : editCUCXW_AC_Credit,
 		'editMemo' : editMemo,
 		'editWithholdAmount' : editWithholdAmount,
 		'editPaymentAmount' : editPaymentAmount,
 		'editisvalid' : editisvalid,
 		'idedit': id
		},
		async : false,
	    success : function(data) {
			 alert("操作成功！");
			 jq('.fancybox-close').click();//关闭弹窗
			 window.location.href="/OA/finance/contractInfo.jsp?pagewith=1&id=withholdOn";

			}
		
	});
	
}

//发起人
function addPersonFun(index){
	
	jq("#addOrEditId").attr("value",index);
	jq("#personShowId").html("<li></li><li></li>");
	var url ="/OA/meetingBook/getBusinessDeptInfo.jsp";
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data:{},
		async : false,
		success : function(data) {
    		var info = data.businessDeptInfo;
    		var str = "<li name='0'>管理层</li>";
    		for(var i = 0;i < info.length; i ++){
        		str += "<li name='"+info[i].id+"'>"+info[i].dept_name+"</li>";
    		}
    		jq("#busDeptId").html(str);
		},
		error : function(json_data) {
			alert("系统繁忙，请稍后重试！");
		}
	});
	
	
	var logindeptId = jq("#logindeptId").attr("value");
	var logindeptName = jq("#logindeptName").attr("value");
	if(logindeptName.indexOf("事业部") >= 0 ){
		jq("#busDeptId").parent().children("input").attr("value",logindeptName);
		getDeptInfo(logindeptId);
		getUserInfo(logindeptId);
	}else{
		var myselfUrl = "/OA/meetingBook/getBusinessDeptBymyself.jsp?"+"&deptId="+logindeptId;
		myselfUrl = encodeURI(myselfUrl);
		jq.ajax({
			type : "post",
			url : myselfUrl,
			dataType : "json",
			data:{},
			async : false,
			success : function(data) {
				jq("#busDeptId").parent().children("input").attr("value",data.businessDeptMy[0].dept_name);
				getDeptInfo(data.businessDeptMy[0].id);
			},
			error : function(json_data) {
				alert("系统繁忙，请稍后重试！");
			}
		});
		jq("#addDeptId").parent().children("input").attr("value",logindeptName);
		getUserInfo(logindeptId);
	}
}
//合同信息
function addContractFun(index){
	jq("#attendContractId").val("");
	jq("#addOrEditIdContract").attr("value",index);
	resultSearchwith();
	
}

//发起人搜索
function searchPersonFun(){
	var name = jq("#searchPersonId").attr("value");
	if(name == "请输入查询关键字"){
		alert("请输入查询关键字!")
	}else{
		var url ="/OA/meetingBook/searchUserInfo.jsp?"+"&name="+name;
		url = encodeURI(url);
		showUserInfoFun(url);
	}
}

function selectBusDept(obj){
	jq(obj).siblings("ul").toggle();
	var input = jq(obj).parent().find("input");
	jq(obj).siblings("ul").children("li").bind("click",function(){
		var $this = jq(this);
		var name = $this.attr("name");
		var value = $this.text();
		input.attr("name",name);
		input.attr("value",value);
		$this.parent().find(".selected").removeClass("selected");
		$this.addClass("selected");
		$this.parent().hide();
		getDeptInfo(name);
		getUserInfo(name);
	})
	if(input.val() == "") jq(obj).siblings("ul").children("li.selected").removeClass("selected");
}

function getDeptInfo(parentId){
	var url ="/OA/meetingBook/getDeptInfo.jsp?"+"&parentId="+parentId;
	url = encodeURI(url);
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data:{},
		async : false,
		success : function(data) {
    		var info = data.deptInfo;
    		var str = "";
    		for(var i = 0;i < info.length; i ++){
        		str += "<li name='"+info[i].id+"'>"+info[i].dept_name+"</li>";
    		}
    		jq("#addDeptId").html(str);
    		jq("#addDeptId").parent().find("input").attr("name","");
    		jq("#addDeptId").parent().find("input").attr("value","请选择");
		},
		error : function(json_data) {
			alert("系统繁忙，请稍后重试！");
		}
	});
}

function selectAddDept(obj){
	jq(obj).siblings("ul").toggle();
	var input = jq(obj).parent().find("input");
	jq(obj).siblings("ul").children("li").bind("click",function(){
		var $this = jq(this);
		var name = $this.attr("name");
		input.attr("name",name);
		input.attr("value",$this.text());
		$this.parent().find(".selected").removeClass("selected");
		$this.addClass("selected");
		$this.parent().hide();
		getUserInfo(name);
	})
	if(input.val() == "") jq(obj).siblings("ul").children("li.selected").removeClass("selected");
}

function getUserInfo(deptId){
	if(deptId == 0){
		str = "<li><a href='javascript:void(0)' onclick='addUserName(this)' value='wenxin'>魏欣</a></li>"+
		"<li><a href='javascript:void(0)' onclick='addUserName(this)' value='liushan'>刘珊</a></li>"+
		"<li><a href='javascript:void(0)' onclick='addUserName(this)' value='luyang20'>陆洋</a></li>"+
		"<li><a href='javascript:void(0)' onclick='addUserName(this)' value='xuxing8'>徐兴</a></li>";
		jq("#personShowId").html(str);
	}else{
		var url ="/OA/meetingBook/getUserInfo.jsp?"+"&deptId="+deptId;
		url = encodeURI(url);
		showUserInfoFun(url);
	}
}

function showUserInfoFun(url){
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data:{},
		async : false,
		success : function(data) {
			var info = data.userInfo;
			var str = "";
			var jingli = "";
			for(var i = 0; i < info.length; i ++){
	    		var name = info[i].name;
	    		var loginName = info[i].login_name;
	    		var position = info[i].position;
	    		if(position !=null && position.indexOf("经理")>=0){
	    			jingli += "<li><a href='javascript:void(0)' class='liu_personnel_name_bold' onclick='addUserName(this)' value='"+loginName+"'>"+name+"</a></li>"
	    		}else if(position !=null){
	        		str += "<li><a href='javascript:void(0)' onclick='addUserName(this)' value='"+loginName+"'>"+name+"</a></li>";
	    		}
			}
			if(str == "" && jingli == ""){
				jq("#personShowId").html("<li></li><li></li>");
			}else{
				if(info.length == 1){
					jq("#personShowId").html(jingli+str+"<li></li>");
				}else{
					jq("#personShowId").html(jingli+str);
				}
			}
		},
		error : function(json_data) {
			alert("系统繁忙，请稍后重试！");
		}
	});
}
//发起人信息添加到页面下方
function addUserName(obj){
	var loginName = jq(obj).attr("value");
	var name = jq(obj).html();
	if(selLoginnameList.indexOf(loginName)<0 ){
		jq("#selectedUserId").append("<li><span>"+name+"</span><a href='javascript:void(0)' onclick='deleteUserName(this)' value='"+loginName+"' name='"+name+"'><img src='/OA/css/images/liu_icon71.png' alt=''/></a></li>");
		if(selLoginnameList != ""){
			selLoginnameList +=";"+loginName;
		}else{
			selLoginnameList = loginName;
		}
	}
}
//合同信息添加到页面下方
function addContractTitle(obj){
	var contractName = jq(obj).attr("value");
	var name = jq(obj).find("p").text();
	if(selContractList.indexOf(contractName)<0 ){
		jq("#contractNameId").append("<li><span>"+name+"</span><a href='javascript:void(0)' onclick='deleteContractName(this)' value='"+contractName+"' name='"+name+"'><img src='/OA/css/images/liu_icon71.png' alt=''/></a></li>");
		if(selContractList != ""){
			selContractList +=";"+contractName;
		}else{
			selContractList = contractName;
		}
	}
}

//删除发起人信息
function deleteUserName(obj){
	var loginName = jq(obj).attr("value");
	var name = jq(obj).attr("name");
	jq(obj).parent().remove();
	var z = selLoginnameList.indexOf(loginName);
	var beforStr = selLoginnameList.substring(0, z-1);
	beforStr += selLoginnameList.substring(z+loginName.length, selLoginnameList.length);
	selLoginnameList= beforStr;
	if(selLoginnameList.indexOf(";")==0){
		selLoginnameList = selLoginnameList.substring(1, selLoginnameList.length);
	}
}
//删除合同信息
function deleteContractName(obj){
	var contractName = jq(obj).attr("value");
	var name = jq(obj).attr("name");
	jq(obj).parent().remove();
	var z = selContractList.indexOf(contractName);
	var beforStr = selContractList.substring(0, z-1);
	beforStr += selContractList.substring(z+contractName.length, selContractList.length);
	selContractList= beforStr;
	if(selContractList.indexOf(";")==0){
		selContractList = selContractList.substring(1, selContractList.length);
	}
}
//发起人页面弹框 点击确定
function attendPersonOkFun(obj){
	var index = jq("#addOrEditId").attr("value");
	var span = jq("#selectedUserId").find("span");
	var len = span.length;
	selUserList = "";
	jq(span).each(function(){
		if(selUserList == ""){
			selUserList = jq(this).html();
		}else{
			selUserList += ";"+jq(this).html();
		}
	 });
	if(index == "y"){
		jq("#attendPersonId").attr("value",selUserList);
		jq("#attendPersonLoginNameId").attr("value",selLoginnameList);
		jq(obj).attr("href","#inline03");
	}else{//edit
		jq("#editattendPersonId").attr("value",selUserList);
		jq("#editattendPersonLoginNameId").attr("value",selLoginnameList);
		jq(obj).attr("href","#inline03edit");
	}
}
//合同信息页面弹框 点击确定
function attendContractOkFun(obj){
	var index = jq("#addOrEditIdContract").attr("value");
	var span = jq("#contractNameId").find("span");
	var len = span.length;
	selConList = "";
	jq(span).each(function(){
		if(selConList == ""){
			selConList = jq(this).html();
		}else{
			selConList += ";"+jq(this).html();
		}
	 });
	if(index == "y"){
		jq("#attendContractId").attr("value",selConList);
		jq("#attendContractNameId").attr("value",selContractList);
		jq(obj).attr("href","#inline03");
	}else{//edit
		jq("#editattendContractId").attr("value",selConList);
		jq("#editattendContractNameId").attr("value",selContractList);
		jq(obj).attr("href","#inline03edit");
	}
}
///////////////////////编辑发起人////////////////////////////
function editAddPersonFun(){
	var person = jq("#editattendPersonId").attr("value");
	var arr = person.split(';');
	selLoginnameList = jq("#editattendPersonLoginNameId").attr("value");
	var login = selLoginnameList.split(';');
	jq('#selectedUserId').html("");
	for(var i in arr){
		html = "<li><span>"+arr[i]+"</span><a href='javascript:void(0)' onclick='deleteUserName(this)' value='"+login[i]+"' name='"+arr[i]+"'><img src='/OA/css/images/liu_icon71.png' alt=''/></a></li>";
		jq("#selectedUserId").append(html);
	}
	selUserList = "";
	addPersonFun("e");
}
///////////////////////编辑匹配合同////////////////////////////
function editContractFun(){
	var person = jq("#editattendContractId").attr("value");
	var arr = person.split(';');
	selContractList = jq("#editattendContractNameId").attr("value");
	var login = selContractList.split(';');
	jq('#contractNameId').html("");
	for(var i in arr){
		html = "<li><span>"+arr[i]+"</span><a href='javascript:void(0)' onclick='deleteContractName(this)' value='"+login[i]+"' name='"+arr[i]+"'><img src='/OA/css/images/liu_icon71.png' alt=''/></a></li>";
		jq("#contractNameId").append(html);
	}
	selConList = "";
	addContractFun("e");
}


function deleteWith(){
	var arr="";
	jq("input[mark='checkwith']").each(function(){
		if(jq(this).attr('checked')=='checked'){
			arr+=(jq(this).attr('id')+",");
		}else{
			
		}
	});
	arr=arr.substring(0,arr.length-1);
	if(arr==''){
		alert("请选择要删除的内容！");
		return;
	}
	
	var msg = "确认删除吗？";
    if (confirm(msg)==true){
    	var url="/OA/finance/deletewithholdingType.jsp";
    	jq.ajax( {
    		type : "post",
    		url : url,
    		dataType : "json",
    		data : {
    		'ids':arr
    		},
    		async : false,
    	    success : function(data) {
    			window.location.href="/OA/finance/contractInfo.jsp?pagewith=1&id=withholdOn";
    			}
    	});
        return true;
     }else{
        return false;
     }


}

//查询
function searchWith(){
	var strinfo=jq.trim(jq("#searchWith").val());
	window.localStorage["strinfo"] = strinfo
	window.location.href="/OA/finance/contractInfo.jsp?pagewith=1&id=withholdOn&strinfo="+strinfo;
}
//刷新
//function searchWith(){
//	var url="/OA/finance/querywithholdingType.jsp";
//	jq.ajax( {
//		type : "post",
//		url : url,
//		dataType : "json",
//		data : {},
//		async : false,
//	    success : function(data) {
//			 var info=data.withholdInfo.withholdInfo;
//			 var str ="";
//			 jq("#queryWithInfo").find("tr[mark='showwith']").remove();
//			 for(var i = 0;i < info.length; i ++){
//				 	str += " <tr mark='showwith'><td><input mark='checkwith' id='"+info[i].id+"' class='gou' type='checkbox' name='check-box'/></td>";
//	        		str += "<td>"+info[i].rownum+"</td>";
//	        		str += "<td id='title"+info[i].id+"'><p class='blue_font'>"+info[i].title+"</p></td>";
//	        		str += "<td id='applicant"+info[i].id+"'>"+info[i].applicant+"</td>";
//	        		str += "<td id='contract"+info[i].id+"'>"+info[i].contract+"</td>";
//	        		str += "<td id='cucxw_co"+info[i].id+"'>"+info[i].cucxw_co+"</td>";
//	        		str += "<td id='cucxw_cc"+info[i].id+"'>"+info[i].cucxw_cc+"</td>";
//	        		str += "<td id='cucxw_ac_debit"+info[i].id+"'>"+info[i].cucxw_ac_debit+"</td>";
//	        		str += "<td id='cucxw_ac_credit"+info[i].id+"'>"+info[i].cucxw_ac_credit+"</td>";
//	        		str += "<td id='memo"+info[i].id+"'>"+info[i].memo+"</td>";
//	        		str += "<td id='withholdamount"+info[i].id+"'>"+info[i].withholdamount+"</td>";
//	        		str += "<td id='paymentamount"+info[i].id+"'>"+info[i].paymentamount+"</td>";
//	        		str += "<td id='isvalid"+info[i].id+"'>"+info[i].isvalid+"</td>";
//	        		str += '<td align="center"><a class="fancybox" href="#inline03edit" onclick="editWithholdType(\''+info[i].id+'\')">编辑</a></td></tr>';
//	        		
//	    		}
//			 jq("#queryWithInfo").append(str);
//			}
//	});
//}


