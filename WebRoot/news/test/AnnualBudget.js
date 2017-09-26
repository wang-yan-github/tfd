var jq = jQuery.noConflict();
jq(function(){
	//点击清空搜索框
	jq("#search").focus(function(){
		jq("#search").val("");
	});
	
	//全选按钮
	jq("#SelectAnnual").click(function(){
		if (jq("#SelectAnnual").attr("checked")) {
	    	jq("input[mark='checkwith']").attr("checked",'true');
	    	jq(".check-box").addClass("checkedBox");
	    } else {  
	        jq("input[mark='checkwith']").removeAttr("checked");
	        jq(".check-box").removeClass("checkedBox");
	    }  
	})
});

//点击新增按钮
function addAnnualBudgetType(){
	jq("#title").val("");
	jq("#budgetyear").val("");
	jq("#salarybudget").val("");
	jq("#hrcostbudget").val("");
	jq("#salaryamount").val("");
	jq("#hrcostamount").val("");
	jq("#salaryamountaccrued").val("");
	jq("#hrcostamountaccrued").val("");
}
//年度预算新增确定按钮
function queding(){
	var title=jq.trim(jq("#title").val());//标题
	var budgetyear=jq.trim(jq("#budgetyear").val());//预算年度
	var salarybudget=jq.trim(jq("#salarybudget").val());//工资预算
	var hrcostbudget=jq.trim(jq("#hrcostbudget").val());//人力成本预算
	var salaryamount=jq.trim(jq("#salaryamount").val());//年度累计工资额预提
	var hrcostamount=jq.trim(jq("#hrcostamount").val());//年度累计人力成本预提
	var salaryamountaccrued=jq.trim(jq("#salaryamountaccrued").val());//年度累计工资额计提
	var hrcostamountaccrued=jq.trim(jq("#hrcostamountaccrued").val());//年度累计人力成本计提
	
	if(title.length == 0){
		alert("标题不能为空");
		return;
	}
	var reg= /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(salarybudget.length != 0){
		if(!reg.test(salarybudget)){
			alert("请输入有效的工资预算");
			return;
		}
	}
	if(hrcostbudget.length != 0){
		if(!reg.test(hrcostbudget)){
			alert("请输入有效的人力成本预算");
			return;
		}
	}
	if(salaryamount.length != 0){
		if(!reg.test(salaryamount)){
			alert("请输入有效的年度累计工资额预提");
			return;
		}
	}
	if(hrcostamount.length != 0){
		if(!reg.test(hrcostamount)){
			alert("请输入有效的年度累计人力成本预提");
			return;
		}
	}
	if(salaryamountaccrued.length != 0){
		if(!reg.test(salaryamountaccrued)){
			alert("请输入有效的年度累计工资额计提");
			return;
		}
	}
	if(hrcostamountaccrued.length != 0){
		if(!reg.test(hrcostamountaccrued)){
			alert("请输入有效的年度累计人力成本计提");
			return;
		}
	}

	var url="/OA/finance/AnnualBudget/addAnnualBudget.jsp";
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
		'title' : title,
 		'budgetyear' : budgetyear,
 		'salarybudget' : salarybudget,
 		'hrcostbudget' : hrcostbudget,
 		'salaryamount' : salaryamount,
 		'hrcostamount' : hrcostamount,
 		'salaryamountaccrued' : salaryamountaccrued,
 		'hrcostamountaccrued' : hrcostamountaccrued
		},
		async : false,
	    success : function(data) {
			 jq('.fancybox-close').click();//关闭弹窗
			 window.location.href="/OA/finance/contractInfo.jsp?pagew=1&id=annualOn";
			}
		
	});
}

//年度预算编辑按钮
function editAnnualBudgetId(id){
	jq("#editAnnualBudgetId").val(id);
	//取值
	var title=jq("#title"+id).text();
	var budgetyear=jq("#budgetyear"+id).text();//预算年度
	var salarybudget=jq("#salarybudget"+id).text();//工资预算
	var hrcostbudget=jq("#hrcostbudget"+id).text();//人力成本预算
	var salaryamount=jq("#salaryamount"+id).text();//年度累计工资额预提
	var hrcostamount=jq("#hrcostamount"+id).text();//年度累计人力成本预提
	var salaryamountaccrued=jq("#salaryamountaccrued"+id).text();//年度累计工资额计提
	var hrcostamountaccrued=jq("#hrcostamountaccrued"+id).text();//年度累计人力成本计提
	//赋值
	jq("#edittitle").val(title);
	jq("#editbudgetyear").val(budgetyear);
	jq("#editsalarybudget").val(salarybudget);
	jq("#edithrcostbudget").val(hrcostbudget);
	jq("#editsalaryamount").val(salaryamount);
	jq("#edithrcostamount").val(hrcostamount);
	jq("#editsalaryamountaccrued").val(salaryamountaccrued);
	jq("#edithrcostamountaccrued").val(hrcostamountaccrued);
	
}

//查看年度信息
function findAnnualBudgetId(id){

	jq("#editAnnualBudgetId").val(id);
	//取值
	var title=jq("#title"+id).text();
	var budgetyear=jq("#budgetyear"+id).text();//预算年度
	var salarybudget=jq("#salarybudget"+id).text();//工资预算
	var hrcostbudget=jq("#hrcostbudget"+id).text();//人力成本预算
	var salaryamount=jq("#salaryamount"+id).text();//年度累计工资额预提
	var hrcostamount=jq("#hrcostamount"+id).text();//年度累计人力成本预提
	var salaryamountaccrued=jq("#salaryamountaccrued"+id).text();//年度累计工资额计提
	var hrcostamountaccrued=jq("#hrcostamountaccrued"+id).text();//年度累计人力成本计提
	//赋值
	jq("#findtitle").val(title);
	jq("#findbudgetyear").val(budgetyear);
	jq("#findsalarybudget").val(salarybudget);
	jq("#findhrcostbudget").val(hrcostbudget);
	jq("#findsalaryamount").val(salaryamount);
	jq("#findhrcostamount").val(hrcostamount);
	jq("#findsalaryamountaccrued").val(salaryamountaccrued);
	jq("#findhrcostamountaccrued").val(hrcostamountaccrued);

	
	
	jq("#findtitle").attr("readonly","readonly");
	jq("#findbudgetyear").attr("readonly","readonly");
	jq("#findsalarybudget").attr("readonly","readonly");
	jq("#findhrcostbudget").attr("readonly","readonly");
	jq("#findsalaryamount").attr("readonly","readonly");
	jq("#findhrcostamount").attr("readonly","readonly");
	jq("#findsalaryamountaccrued").attr("readonly","readonly");
	jq("#findhrcostamountaccrued").attr("readonly","readonly");
	
}

//年度预算编辑确定按钮
function editAnnualBudgetQD(){
	var id=jq("#editAnnualBudgetId").val();
	//获取修改的值
	var edittitle=jq("#edittitle").val();
	var editbudgetyear=jq("#editbudgetyear").val();//预算年度
	var editsalarybudget=jq("#editsalarybudget").val();//工资预算
	var edithrcostbudget=jq("#edithrcostbudget").val();//人力成本预算
	var editsalaryamount=jq("#editsalaryamount").val();//年度累计工资额预提
	var edithrcostamount=jq("#edithrcostamount").val();//年度累计人力成本预提
	var editsalaryamountaccrued=jq("#editsalaryamountaccrued").val();//年度累计工资额计提
	var edithrcostamountaccrued=jq("#edithrcostamountaccrued").val();//年度累计人力成本计提
	
	
	if(edittitle.length == 0){
		alert("标题不能为空");
		return;
	}
	var reg= /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	if(editsalarybudget.length != 0){
		if(!reg.test(editsalarybudget)){
			alert("请输入有效的工资预算");
			return;
		}
	}
	if(edithrcostbudget.length != 0){
		if(!reg.test(edithrcostbudget)){
			alert("请输入有效的人力成本预算");
			return;
		}
	}
	if(editsalaryamount.length != 0){
		if(!reg.test(editsalaryamount)){
			alert("请输入有效的年度累计工资额预提");
			return;
		}
	}
	if(edithrcostamount.length != 0){
		if(!reg.test(edithrcostamount)){
			alert("请输入有效的年度累计人力成本预提");
			return;
		}
	}
	if(editsalaryamountaccrued.length != 0){
		if(!reg.test(editsalaryamountaccrued)){
			alert("请输入有效的年度累计工资额计提");
			return;
		}
	}
	if(edithrcostamountaccrued.length != 0){
		if(!reg.test(edithrcostamountaccrued)){
			alert("请输入有效的年度累计人力成本计提");
			return;
		}
	}

	var url="/OA/finance/AnnualBudget/editAnnualBudget.jsp";
	jq.ajax( {
		type : "post",
		url : url,
		dataType : "json",
		data : {
		'edittitle' : edittitle,
 		'editbudgetyear' : editbudgetyear,
 		'editsalarybudget' : editsalarybudget,
 		'edithrcostbudget' : edithrcostbudget,
 		'editsalaryamount' : editsalaryamount,
 		'edithrcostamount' : edithrcostamount,
 		'editsalaryamountaccrued' : editsalaryamountaccrued,
 		'edithrcostamountaccrued' : edithrcostamountaccrued,
 		'editid' : id
		},
		async : false,
	    success : function(data) {
			 jq('.fancybox-close').click();//关闭弹窗
			 window.location.href="/OA/finance/contractInfo.jsp?pagew=1&id=annualOn";
			}
		
	});
}
//删除
function deleteAnnualBudget(){
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
    	var url="/OA/finance/AnnualBudget/deleteAnnualBudget.jsp";
    	jq.ajax( {
    		type : "post",
    		url : url,
    		dataType : "json",
    		data : {
    		'ids':arr
    		},
    		async : false,
    	    success : function(data) {
    			window.location.href="/OA/finance/contractInfo.jsp?pagew=1&id=annualOn";
    			}
    	});
        return true;
     }else{
        return false;
     }
}
//年度预算模糊查询
function queryAnnualBudgets(){
	var search=jq.trim(jq("#search").val());
	window.localStorage["search"] = search;
	window.location.href="/OA/finance/contractInfo.jsp?pagew=1&id=annualOn&search="+search;
}
//导出年度预算
function exportAnnualBudget(){
	 var url='/OA/exportAnnualBudgetType.do';
	 jq.ajaxFileUpload({
           url:url, 
           secureuri:false,
           dataType: 'json',
           type: 'post',
           data: {},
            success:function(data){
        	   alert("导出成功，请到桌面查看！");
           	 window.location.href="/OA/finance/contractInfo.jsp?pagew=1&id=annualOn"
             },
             error:function(err){
           	  
             }
        });
}