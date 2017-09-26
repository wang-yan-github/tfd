var jq = jQuery.noConflict();
jq(function(){
	var ressearch = localStorage["ressearch"];
	if(ressearch != undefined && ressearch !=""){
		jq("#searchID").val(ressearch);
	}
	localStorage["ressearch"]="";
	jq("#searchID").focus(function(){
		jq("#searchID").val("");
	})
	
	
//	jq('input[name="con_chebox"]').wrap("<div class='check_box'><i></i></div>");
//    jq.fn.toggleCheckbox = function () {
//        this.attr('checked', !this.attr('checked'));
//    }
//    jq('.check_box').on('click', function () {
//        jq(this).find(':checkbox').toggleCheckbox();
//        jq(this).toggleClass('checkedBox');
//    })
//    //全选按钮
//	jq("#SelectAll").click(function(){
//		if (jq("#SelectAll").attr("checked")) {
//	    	jq("input[mark='checkCon']").attr("checked",'true');
//	    	jq(".check_box").addClass("checkedBox");
//	    } else {  
//	        jq("input[mark='checkCon']").removeAttr("checked");
//	        jq(".check_box").removeClass("checkedBox");
//	    }  
//	})
    
    jq("#SelectAll").click(function(){
		if (jq("#SelectAll").attr("checked")) {  
	    	jq("[name='con_chebox']").attr("checked",'true'); 
	    } else {  
	        jq("[name='con_chebox']").removeAttr("checked");;  
	    }  
	})
})
//导出Excel文件
function exportExcel(){
	 var url='/OA/emportConInfoExcel.do';
	 jq.ajax({
            url:url, //你处理下载文件的服务端
//            secureuri:false,
            dataType: 'json',
            type: 'post',
            async:false,
             success:function(data){

            	 //jq('.fancybox-close').click();//关闭弹窗
            	 

		 		alert("导出成功，请到桌面查看！");
		 		//window.location.href="/OA/finance/contractInfo.jsp";

              },
              error:function(err){
            	  
              }
         });
}
function copyVal(param){
	 var www =jq("#fileFieldContract").val();
	    jq("#contractInfoPath").val(www);
}
//导入清空路径
function removepath(){
	jq("#contractInfoPath").val("");
}
//导入Excel
function importContractFile(){
	 var contractInfoPath =jq("#contractInfoPath").val();
	 var url='/OA/importContractFile.do';
	 jq.ajaxFileUpload({
           url:url, //你处理上传文件的服务端
           secureuri:false,
           dataType: 'json',
           fileElementId:'fileFieldContract',
           type: 'post',
           data: { 
		 		'conpath' : contractInfoPath
        	   
            },
            success:function(data){
            	var message = data.result.message;
            	if(message=='title'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败,合同名称不能为空！");
            		return;
            		
            	}else if(message=='contractType'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败,合同类型不能为空！");
            		return;
            		
            	}else if(message=='contractNumber'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败,合同编号不能为空！");
            		return;
            		
            	}else if(message=='department'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败,经办部门不能为空！");
            		return;
            	}else if(message=='userInCharge'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败,经办人不能为空！");
            		return;
            	}else if(message=='partyB'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败,对方单位不能为空！");
            		return;
            	}else if(message=='contractAmount'){
            		jq("#importContId").removeAttr("disabled");
            		alert("导入失败, 合同金额不能为空！");
            		return;
            	}else{
            		alert("导入成功");
            		jq('.fancybox-close').click();//关闭弹窗
            		window.location.href="/OA/finance/contractInfo.jsp"
            	}
             },
             error:function(err){
           	  
             }
        });
}
//点击新增按钮
function addbtn(){
	jq("#add_title").val("");
	jq("#add_type").val("");
	jq("#add_number").val("");
	jq("#add_department").val("");//经办部门
	jq("#add_userInCharge").val("");//经办人
	jq("#add_amount").val("");//合同金额
	jq("#add_partyB").val("");//对方单位
}
//新增点击确定按钮
function addContractInfo(){
	var title=jq.trim(jq("#con_title").val());//标题
	var contractType=jq.trim(jq("#con_type").val());//合同类型
	var contractNumber=jq.trim(jq("#con_number").val());//合同编号
	var department=jq.trim(jq("#con_department").val());//经办部门
	var userInCharge=jq.trim(jq("#con_userInCharge").val());//经办人
	var contractAmount=jq.trim(jq("#con_amount").val());//合同金额
	var partyB=jq.trim(jq("#con_partyB").val());//对方单位
	var isvalid='';
	
	if(title.length == 0){
		alert("标题不能为空");
		return;
	}
//	var arr =attendPersonId.split(';');
//	if(attendPersonId.length == 0||arr.length>1){
//		alert("发起人只能选择一人且不能为空");
//		return;
//	}
	
	
	if(contractType.length == 0){
		alert("合同类型不能为空");
		return;
	}
	
	if(contractNumber.length == 0){
		alert("合同编号不能为空");
		return;
	}
	
	if(department.length == 0){
		alert("经办部门不能为空");
		return;
	}
	if(userInCharge.length == 0){
		alert("经办人不能为空");
		return;
	}
	var reg= /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(contractAmount.length == 0 || !reg.test(contractAmount)){
		alert("合同金额不符合规定！");
		return;
	}
	if(partyB.length == 0){
		alert("对方单位不能为空");
		return;
	}
	jq("#addconbtn").attr('disabled','true');
	var url="/OA/finance/addContractInfo.jsp";
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
			'title' : title,
	 		'contractType' : contractType,
	 		'contractNumber' : contractNumber,
	 		'department' : department,
	 		'userInCharge' : userInCharge,
	 		'contractAmount' : contractAmount,
	 		'partyB' : partyB,
		},
		async : false,
	    success : function(data) {
			 jq('.fancybox-close').click();//关闭弹窗
			 alert("操作成功！");
			 window.location.href="/OA/finance/contractInfo.jsp?page=1"
			}
		
	});

} 
//全选、取消全选的事件 
function selectAll(){  
    if (jq("#SelectAll").attr("checked")) {  
    	jq("[name='check-box']").attr("checked",'true'); 
    } else {  
        jq("[name='check-box']").removeAttr("checked");;  
    }  
} 

//删除合同信息
function deleteContractInfo(){
	var arr="";
	jq("input[mark='checkCon']").each(function(){
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
    	var url="/OA/finance/deleteContractInfo.jsp";
    	jq.ajax( {
    		type : "post",
    		url : url,
    		dataType : "json",
    		data : {
    		'ids':arr
    		},
    		async : false,
    	    success : function(data) {
    			window.location.href="/OA/finance/contractInfo.jsp";
    			}
    	});
        return true;
     }else{
        return false;
     }
}

//点击编辑按钮
function editContractInfo(id,flag){
	jq("#editContractInfoId").val(id);
	//取值
	var title=jq("#title"+id).text();
	var contracttype=jq("#contracttype"+id).text();
	var contractnumber=jq("#contractnumber"+id).text();
	var department=jq("#department"+id).text();
	var userincharge=jq("#userincharge"+id).text();
	var contractamount=jq("#contractamount"+id).text();
	var partyb=jq("#partyb"+id).text();
	//赋值
	jq("#edit_id").val(id);
	jq("#edit_title").val(title);
	jq("#edit_type").val(contracttype);
	jq("#edit_number").val(contractnumber);
	jq("#edit_department").val(department);
	jq("#edit_userInCharge").val(userincharge);
	jq("#edit_amount").val(contractamount);
	jq("#edit_partyB").val(partyb);
	if(flag == 1){
		//赋值
		jq("#toptitle").text("查看");
		jq("#contractbtn").attr("disabled","true");
		
		jq("#edit_title").attr("readonly","readonly");
		jq("#edit_type").attr("readonly","readonly");
		jq("#edit_number").attr("readonly","readonly");
		jq("#edit_department").attr("readonly","readonly");
		jq("#edit_userInCharge").attr("readonly","readonly");
		jq("#edit_amount").attr("readonly","readonly");
		jq("#edit_partyB").attr("readonly","readonly");
	}else{
		jq("#toptitle").text("编辑");
		//jq("#contractbtn").removeAttr("disabled");
		jq('#contractbtn').attr("disabled",false); 
		jq("#edit_title").removeAttr("readonly");
		jq("#edit_type").removeAttr("readonly");
		jq("#edit_number").removeAttr("readonly");
		jq("#edit_department").removeAttr("readonly");
		jq("#edit_userInCharge").removeAttr("readonly");
		jq("#edit_amount").removeAttr("readonly");
		jq("#edit_partyB").removeAttr("readonly");
	}
	
}
//点击编辑确定按钮
function editConInfo(){
	var id=jq("#editContractInfoId").val();
	//获取修改的值
	var title=jq.trim(jq("#edit_title").val());//标题
	var contractType=jq.trim(jq("#edit_type").val());//合同类型
	var contractNumber=jq.trim(jq("#edit_number").val());//合同编号
	var department=jq.trim(jq("#edit_department").val());//经办部门
	var userInCharge=jq.trim(jq("#edit_userInCharge").val());//经办人
	var contractAmount=jq.trim(jq("#edit_amount").val());//合同金额
	var partyB=jq.trim(jq("#edit_partyB").val());//对方单位
	var url="/OA/finance/editContractInfo.jsp";
	jq("#contractbtn").attr('disabled','true');
	if(title.length == 0){
		alert("标题不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	if(contractType.length == 0){
		alert("合同类型不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	
	if(contractNumber.length == 0){
		alert("合同编号不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	
	if(department.length == 0){
		alert("经办部门不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	if(userInCharge.length == 0){
		alert("经办人不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	
	if(contractAmount.length == 0){
		alert("合同金额不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	var reg= /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(!reg.test(contractAmount))
	{
		alert("合同金额不合法，请重新输入！");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	if(partyB.length == 0){
		alert("对方单位不能为空");
		jq('#contractbtn').attr("disabled",false); 
		return;
	}
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
			'id' : id,
			'title' : title,
	 		'contractType' : contractType,
	 		'contractNumber' : contractNumber,
	 		'department' : department,
	 		'userInCharge' : userInCharge,
	 		'contractAmount' : contractAmount,
	 		'partyB' : partyB,
		},
		async : false,
	    success : function(data) {
			 alert("操作成功！");
			 jq('.fancybox-close').click();//关闭弹窗
			 window.location.href="/OA/finance/contractInfo.jsp";
			}
		
	});
	

}
//function resultSearch(){
//	var search = jq("#searchID").val();
//	jq.ajax( {
//		type : "post",
//		url : url,
//		dataType : "json",
//		data : {
//			'search' : search
//		},
//		async : false,
//	    success : function(data) {
//			 //jq('.fancybox-close').click();//关闭弹窗
//			 window.location.href="/OA/finance/contractInfo.jsp";
//			}
//		
//	});
//}
function resultSearch(){
	var ressearch = jq("#searchID").val();
	window.localStorage["ressearch"] = ressearch;
	window.location.href="/OA/finance/contractInfo.jsp?ressearch="+ressearch;
}
