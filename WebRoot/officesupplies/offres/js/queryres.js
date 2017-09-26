function queryres(){
		$("#bigdiv").hide();
		$('#table').show();	
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/officesupplies/act/OffresAct/tremqueryresAct.act",
	        method: 'POST',
	        sortName:'ID',
	      	sortOrder:'DESC',
	      	pagePosition:'top',
	      	queryParams : {
				resId:$("#resId").val(),
				libraryId:$("#libraryId").val(),
				classifyId:$("#classifyId").val(),
				resName:$("#resName").val()
			},
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        rownumbers:true,
	        singleSelect:true,  
	        remoteSort:true, 
	        columns:[[
	           	{title: '办公用品名称', field: 'RES_NAME', width: '20%', align: 'center',sortable:true},
	           	{title: '办公用品类别', field:'CLASSIFY_NAME', width: '14%' ,align :'center' ,sortable:true },
	            {title: '计量单位', field: 'RES_UNIT', width: '6%', align: 'center',sortable:true},
	            {title: '供应商', field: 'RES_SUPPLIER', width: '15%', align: 'center',sortable:true},
	            {title: '警戒库存', field: 'MINSTOCK', width: '6%', align: 'center',sortable:true},
	            {title: '当前库存', field: 'BEFORESTOCK', width: '6%', align: 'center',sortable:true},
	            {title: '审批人', field: 'USER_NAME', width: '12%', align: 'center',sortable:true},
	            {title: '操作', field: 'OPT', width: '20%', align: 'center'}
	        ]]
	    });
	     
	    var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为5  
	        pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });  

	}
	function returnquery() {
		$('#table').hide();
		$('#bigdiv').show();
	}
	function updateres(resId){
		var url=contextPath+"/officesupplies/offres/updateres.jsp?resId="+resId;
		window.location=url;
	}
	function delres(resId){
		if(confirm("确认删除？")){
			var url=contextPath+"/officesupplies/act/OffresAct/delIdresAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{resId:resId},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					if(data==1)
					{
					parent.layer.msg('删除成功');
					getrestable();
				}else{
				}
			}
			});
			}
	}
		function getlibrary(){
			var url=contextPath+"/officesupplies/act/OfflibraryAct/getlibraryAct.act";
			$.ajax({
				url:url,
				type:"POST",
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					for(var i=0;i<data.length;i++){
						$("#libraryId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
					}
			}
			});
		}
		function getlibclassify(){
			var libraryId=$("#libraryId").val();
			var url=contextPath+"/officesupplies/act/OfflibraryAct/gettopIdNameAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{topId:libraryId},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					$("#classifyId").empty();
					$("#classifyId").append("<option selected=\"selected\" value=''>请选择</option>");
					$("#resId").empty();
					$("#resId").append("<option selected=\"selected\" value=''>请选择</option>");
					if(data!=""){
					for(var i=0;i<data.length;i++){
						$("#classifyId").append("<option value=\""+data[i].libraryId+"\"> "+data[i].libraryName+"</option>");
					}
					}
			}
			});
		}
		function getresName(){
			var classifyId=$("#classifyId").val();
			var url=contextPath+"/officesupplies/act/OffresAct/getresNameAct.act";
			$.ajax({
				url:url,
				type:"POST",
				data:{classifyId:classifyId},
				dataType:"json",
				async:false,
				error:function(e){
				},
				success:function(data){
					$("#resId").empty();
					$("#resId").append("<option selected=\"selected\" value=''>请选择</option>");
					if(data!=""){
					for(var i=0;i<data.length;i++){
						$("#resId").append("<option value=\""+data[i].resId+"\"> "+data[i].resName+"</option>");
					}
					}
			}
			});
		}
	$(function(){
		getlibrary();
	});