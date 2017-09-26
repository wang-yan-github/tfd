var checkin={
	userName:"null",
	priv:""	
}
$(function(){
	$.ajax({
		url:contextPath+"/checkin/act/CheckinAct/checkinSetting.act",
		data:{url:"/tfd/checkin/search.jsp?priv="+checkin.priv},
		type:"POST",
		dataType:"json",
		async:false,
		error:function(){
			checkin.priv="";
		},
		success:function(returnData){
			checkin.userName=returnData.userName;
			if(!returnData.priv){
				checkin.priv="";
			}
		}
	});
	
	if(checkin.priv==""){
		$(".userName-box").remove();
		$(".deptName-box").remove();
	}
	
	
	$("#checkinTimeStart").datetimepicker({
		locale:"zh-cn",
		format:"YYYY-MM-DD HH:mm:ss"
    });
	$("#checkinTimeEnd").datetimepicker({
		locale:"zh-cn",
		format:"YYYY-MM-DD HH:mm:ss"
    });
	
   	
   	
   	$("#ok").on("click",function(){
   		$("#search-result-alert").hide();
   		$("#checkin-list").datagrid({
   			url:contextPath+"/checkin/act/CheckinAct/list.act",
   			queryParams:(function(){
   				var param={
   	   				checkinType:$("#search-form *[name='checkinType']").val(),
   	   				checkinTimeStart:$("#search-form *[name='checkinTimeStart']").val(),
   	   				checkinTimeEnd:$("#search-form *[name='checkinTimeEnd']").val()
   	   			};
   				if(checkin.priv=="all"){
   					param.userName=$("#search-form *[name='userName']").val();
   					param.deptName=$("#search-form *[name='deptName']").val();
   				}else{
   					param.userName=checkin.userName;
   				}
   				return param;
   			})(),
   			columns:[
   			         [
   			          {field:"deptname",title:"部门",width:"25%"},
   			          {field:"username",title:"员工",width:"25%"},
   			          {field:"checktype",title:"类型",width:"20%"},
   			          {field:"checktime",title:"考勤时间",width:"30%"}
   			          ]
	         ],
	         sortName:"checktime",
	         sortOrder:"desc",
	         pagination:true,
	         pageNumber:1,
	         displayMsg:"第{from}-{to}，总条数：{total}"
   		});
   		$("#checkin-list").datagrid("getPager").pagination({
   			pageNumber:1,
   			beforePageText:"第",
   			afterPageText:"页,共{pages}页",
   			displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
   		});
   	});
   	
	
});