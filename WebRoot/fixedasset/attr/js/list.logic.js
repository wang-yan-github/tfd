$(function(){
	$("#asset-list").datagrid({
		url:"/tfd/fixedasset/act/FixedassetAct/assetList.act",
        columns:[
             [
				{field:"asset_no",title:"资产编号",width:"20%"},
				{field:"asset_name",title:"资产名称",width:"60%"},
				{field:"unit_price",title:"资产价格",width:"20%"}
             ]
        ],
        pagination:true,
        pageNumber:2,
        displayMsg:"第{from}-{to}，总条数：{total}"
	});
	var dg=$("#asset-list").datagrid("getPager");
	dg.pagination({
		pageNumber:1,
		beforePageText:"第",
		afterPageText:"页,共{pages}页",
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});