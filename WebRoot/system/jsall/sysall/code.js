
/**
 * 得到分类代码下拉选框
 * Time:2015-4-7
 * Author:Yzz
 * @param {String} value:div的Id,下拉框在这个div中
 * @param {String} id:select的Id
 * @param {String} pageValue：当前页面的值
 */
function getSelect(codeId,pageid,pageValue){
	var requestUrl= contextPath+'/tfd/system/code/act/CodeAct/getCodeAndChildByValue.act';
	$.ajax({
		url:requestUrl,
		data:{value:encodeURIComponent(codeId)},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if($.isEmptyObject(data)){
			}else{
				var str = "<select class='form-control' id='"+pageid+"' name='"+pageid+"'  >";
				var row = data[0].rows;
				str+="<option value='' selected = 'selected'>请选择</option>";
				$.each(row,function(index,row){
				    if(row.value==pageValue)
				    {
				        str += "<option value='"+row.value+"'>"+row.name+"</option>";
				    }else
				    {
				        str += "<option value='"+row.value+"' >"+row.name+"</option>";
				    }
						
				});
				str += "</select>";
				$('#'+pageid).replaceWith(str);
			}
		}
	});
}
