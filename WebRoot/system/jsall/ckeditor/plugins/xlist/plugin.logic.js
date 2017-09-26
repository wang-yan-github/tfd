
var flowform_plugin_xlist=new Object();
$(function (){
	var titlejson=top.fieldList.title;
	var fieldjson=top.fieldList.fieldName;
	var oldTitle=null;
	var oldChildtable=null;
	var model=new Array();
	var modelMap=new Object();
	var rowIndex=0;
	var selection = top.editor.getSelection();
	var element = selection.getSelectedElement();
	
	

	function getXlistItemHtml(rowIndex) {
		var html = "<tr data-row-index='"+rowIndex+"'>"
			+ "<td><div class='form-group'><input class='form-control' name='title-"+rowIndex+"'/></div></td>"
			+ "<td><div class='form-group'><input class='form-control' name='fieldname-"+rowIndex+"'/></div></td>"
			+ "<td><div class='form-group'><input class='form-control' name='width-"+rowIndex+"'/></div></td>"
			+ "<td><div class='form-group'><input class='form-control' name='stylewidth-"+rowIndex+"'/></div></td>"
			+ "<td><div class='form-group'><input class='form-control' name='sum-"+rowIndex+"' type='checkbox'/></div></td>"
			+ "<td><div class='form-group'><input class='form-control' name='formula-"+rowIndex+"'/></div></td>"
			+ "<td>" 
				+"<div class='form-group'>"+
					"<select class='form-control' name='type-"+rowIndex+"'>"+
					"<option value='1'>单行输入框</option>"+
					"<option value='2'>多行文本框</option>"+
					"<option value='3'>下拉菜单</option>"+
					"<option value='4'>单选框</option>"+
					"<option value='5'>多选框</option>"+
					"<option value='6'>日期选择</option>"+
					"</select>"
				+"</div>"
			+ "</td>"
			+ "<td><div class='form-group'><input class='form-control' name='model-"+rowIndex+"'/></div></td>"
			+ "<td><div class='form-group'><button type='button' class='btn btn-danger btn-xs xlist-item-delete' data-row-index='"+rowIndex+"'>删除</button></div></td>";
		+"</tr>";

		return html;
	}

	function initFormValidation(){
		function getRowIndex($field){
			var nameFull=$field.attr("name");
			return parseInt(nameFull.substring(nameFull.lastIndexOf("-")+1));
		}
		function getOldValue($field){
			var nameFull=$field.attr("name");
			var name=nameFull.substring(0,nameFull.lastIndexOf("-"));
			var rowIndex=getRowIndex($field);
			var oldValue=null;
			if (modelMap[rowIndex]!=null&&rowIndex in modelMap) {
				oldValue=modelMap[rowIndex][name];
			}
			return oldValue;
		}
		function itemTitleExist(title){
			for(var rowIndex in modelMap){
				if (modelMap[rowIndex]!=null&&modelMap[rowIndex].title==title) {
					return true;
				}
			}
			return false;
		}
		function itemFieldnameExist(fieldname){
			for(var rowIndex in modelMap){
				if (modelMap[rowIndex]!=null&&modelMap[rowIndex].fieldname==fieldname) {
					return true;
				}
			}
			return false;
		}
		
		var int0Validators={
				validators:{
	    			callback:{
	    				callback:function(value,validator,$field){
	    					value=$.trim(value);
	    					$field.val(value);
	    					
	    					if (value=="") {
								return {valid:false,message:"请填写"};
							}else{
								if(!new RegExp(new RegexUtil().REGEX_INT_0_).test(value)){
									return {valid:false,message:"必须是大于0的整数"};
								}
							}
	    					
	    					return true;
	    				}
	    			}
	    		}
		}
		
		
		var fvFields={
		    	title:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if (value!=oldTitle&&(value in titlejson)) {
										return {valid:false,message:"标题已存在"};
									}
								}
		    					return true;
		    				}
		    			}
		    		}
		    	},
		    	childtable:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if (value!=oldChildtable&&(value in fieldjson)) {
										return {valid:false,message:"名称已存在"};
									}else if(!new RegExp("^[a-z]*$").test(value)){
										return {valid:false,message:"必须全部为小写字母"};
									}
								}
		    					
		    					return true;
		    				}
		    			}
		    		}
		    	},
		    	width:int0Validators
		    };
		for (var i in modelMap) {
			fvFields["title-"+i]={
	    		validators:{
	    			callback:{
	    				callback:function(value,validator,$field){
	    					value=$.trim(value);
	    					$field.val(value);
	    					var oldValue=getOldValue($field);
	    					
	    					if (value=="") {
								return {valid:false,message:"请填写"};
							}else if(value!=oldValue&&itemTitleExist(value)){
								return {valid:false,message:"标题已存在"};
							}
	    					
	    					var rowIndex=getRowIndex($field);
	    					if (rowIndex in modelMap) {
	    						if (modelMap[rowIndex]==null) {
									modelMap[rowIndex]=new Object();
								}
	    						modelMap[rowIndex].title=value;
							}else{
								var modelOne=new Object();
								modelOne.title=value;
								modelMap[rowIndex]=modelOne;
							}
	    					return true;
	    				}
	    			}
	    		}
			};
			fvFields["fieldname-"+i]={
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					var oldValue=getOldValue($field);
		    					
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if(value!=oldValue&&itemFieldnameExist(value)){
										return {valid:false,message:"名称已存在"};
									}else if(!new RegExp("^[a-z]*$").test(value)){
										return {valid:false,message:"必须全部为小写字母"};
									}
								} 
		    					
		    					var rowIndex=getRowIndex($field);
		    					if (rowIndex in modelMap) {
		    						if (modelMap[rowIndex]==null) {
										modelMap[rowIndex]=new Object();
									}
		    						modelMap[rowIndex].fieldname=value;
								}else{
									var modelOne=new Object();
									modelOne.fieldname=value;
									modelMap[rowIndex]=modelOne;
								}
		    					return true;
		    				}
		    			}
		    		}
				};
			fvFields["width-"+i]=int0Validators;
			fvFields["stylewidth-"+i]=int0Validators;
		}
		
		$("form").formValidation({
			//结合bootstrap
			framework:"bootstrap",
			//验证错误提示方式
			err:{
				container:"tooltip"
			},
			//验证结果图标
			icon: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
		    fields:fvFields
		})
		.on("success.form.fv",function(e){
			e.preventDefault();//阻止自动提交
		});
	} 
	flowform_plugin_xlist.validate=function() {
		$("form").data("formValidation").destroy();
		initFormValidation();
		$("form").data("formValidation").validate();
		
		if ($("form").data("formValidation").isValid()) {
			var title=$("#title").val();
			var childtable=$("#childtable").val();
			
			if (oldTitle!=null) {
				delete titlejson[oldTitle];
				delete fieldjson[oldChildtable];
			}
			
			titlejson[title]=title;
			fieldjson[childtable] = childtable;
			
			return true;
		}
		return false;
	}
	flowform_plugin_xlist.toDomStr=function() {
		var title = $("#title").val();
		var width = $("#width").val();
		var header = $("#header").val();
		var data = $("#data").val();
		var childtable = $("#childtable").val();
		
		var model=new Array();
		for(var i in modelMap){
			var $tr=$("#xlist-body tr[data-row-index='"+i+"']");
			var modelOne=new Object();
			modelOne.title=$tr.find("*[name='title-"+i+"']").val();
			modelOne.fieldname=$tr.find("*[name='fieldname-"+i+"']").val();
			modelOne.width=$tr.find("*[name='width-"+i+"']").val();
			modelOne.stylewidth=$tr.find("*[name='stylewidth-"+i+"']").val();
			modelOne.sum=$tr.find("*[name='sum-"+i+"']").val();
			modelOne.formula=$tr.find("*[name='formula-"+i+"']").val();
			modelOne.type=$tr.find("*[name='type-"+i+"']").val();
			modelOne.model=$tr.find("*[name='model-"+i+"']").val();
			
			model.push(modelOne);
		}
		
		var html = "<img " +
		"	src="+contextPath + "/system/styles/images/workflow/xlist.png " +
		"	xtype='xlist' " +
		"	title='" + title +"'"+ 
		" 	name='" + childtable + "'" +
		"	id='" + childtable +"'"+
		" 	rows='" + width + "' " +
		" 	childtable='" + childtable + "' " +
		"	header='" + header+"'"+
		" 	data='" + data + "'"+
		"	model='" + $.json.encode(model) + "'"+
		"/>";
		return html;
	}
	
	
	$("#xlist-item-add").on("click",function(){
		$("#xlist-body").append(getXlistItemHtml(++rowIndex));
		modelMap[rowIndex]=null;
		
		$("form").data("formValidation").destroy();
		initFormValidation();
	});
	$(document).on("click",".xlist-item-delete",function(){
		$("form").data("formValidation").destroy();
		
		var rowIndex=parseInt($(this).attr("data-row-index"));
		$("#xlist-body tr[data-row-index='"+rowIndex+"']").remove();
		delete modelMap[rowIndex];
		
		initFormValidation();
	});
	
	if (element != null && element.getAttribute('xtype') == 'xlist') {
		var title = $("#title");
		var width = $("#width");
		var data = $("#data");
		var header = $("#header");
		var childtable = $("#childtable");
		var modelStr = element.getAttribute("model");//获取元素model属性
		
		if (element.getAttribute("title")) {
			title[0].value = element.getAttribute("title");
			oldTitle=title[0].value;
		}
		if (element.getAttribute("rows")) {
			width[0].value = element.getAttribute("rows");
		}
		if (element.getAttribute("data")) {
			data[0].value = element.getAttribute("data");
		}
		if (element.getAttribute("header")) {
			header[0].value = element.getAttribute("header");
		}
		if (element.getAttribute("childtable")) {
			childtable[0].value = element.getAttribute("childtable");
			oldChildtable=childtable[0].value;
		}
		
		if (modelStr!=null && modelStr != "") {
			model = eval("(" + modelStr + ")");
		}
	}

	var xlistBodyHtml="";
	for (var i = 0; i < model.length; i++) {
		xlistBodyHtml += getXlistItemHtml(rowIndex++);
	}
	var html = "<table>" +
				"<thead>" +
				"<tr>" +
				"	<td class='field-title'>字段标题</td>" +
				"	<td class='field-fieldname'>字段名称</td>" +
				"	<td class='field-width'>字段宽度</td>" +
				"	<td class='field-stylewidth'>此列宽度</td>" +
				"	<td class='field-sum'>列合计</td>" +
				"	<td class='field-formula'>计算公式</td>" +
				"	<td class='field-type'>字段类型</td>" +
				"	<td class='field-model'>值数据</td>" +
				"	<td class='opt-column'>操作</td>"+
				"</tr>"+
				"</thead>" +
				"<tbody id='xlist-body'>"+xlistBodyHtml+"</tbody>"+
				"</table>";
	
	$("#xlist-content").html(html);
	for (var i = 0; i < model.length; i++) {
		$("#xlist-body *[name='title-"+i+"']").val(model[i].title);
		$("#xlist-body *[name='fieldname-"+i+"']").val(model[i].fieldname);
		$("#xlist-body *[name='width-"+i+"']").val(model[i].width);
		$("#xlist-body *[name='stylewidth-"+i+"']").val(model[i].stylewidth);
		$("#xlist-body *[name='sum-"+i+"']").val(model[i].sum);
		$("#xlist-body *[name='formula-"+i+"']").val(model[i].formula);
		$("#xlist-body *[name='type-"+i+"']").val(model[i].type);
		$("#xlist-body *[name='model-"+i+"']").val(model[i].model);
		
		modelMap[i]=model[i];
	}
	
	initFormValidation();
});
