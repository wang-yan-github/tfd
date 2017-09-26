var flowform_plugin_xselect=new Object();
$(function() {
	function initFormValidation(){
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
		    fields:{
		    	title:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if (oldTitle!=value&&(value in titlejson)) {
										return {valid:false,message:"标题已存在"};
									}
								}
		    					return true;
		    				}
		    			}
		    		}
		    	},
		    	fieldName:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if (oldFieldName!=value&&(value in fieldjson)) {
										return {valid:false,message:"名称已存在"};
									}else if(!new RegExp("^[a-z]*$").test(value)){
										return {valid:false,message:"字段名必须全部为小写字母"};
									}
								}
		    					return true;
		    				}
		    			}
		    		}
		    	}
		    }
		})
		.on("success.form.fv",function(e){
			e.preventDefault();//阻止自动提交
		});
	} 

	//校验，required
	flowform_plugin_xselect.validate=function() {
		$("form").data("formValidation").destroy();
		initFormValidation();
		$("form").data("formValidation").validate();
		
		if ($("form").data("formValidation").isValid()) {
			var title=$("#title").val();
			var fieldName=$("#fieldName").val();
			
			if (oldTitle!=null) {
				delete titlejson[oldTitle];
				delete fieldjson[oldFieldName];
			}
			
			titlejson[title]=title;
			fieldjson[fieldName] = fieldName;
			
			return true;
		}
		return false;
	}

	// 转换dom节点，required
	flowform_plugin_xselect.toDomStr=function() {
		var model=[];
		
		var optionHtml="";
		var defaultValue = "";
		var title = $("#title").val();
		var fieldName = $("#fieldName").val();
		$("#xselect-content tbody tr").each(function() {
			
			var value=$(this).find("*[name='select-value']").val();
			value=$.trim(value);
			var checked = $(this).find("*[name='select-default']").prop("checked");
			if (checked) {
				defaultValue = value;
			}
			
			if (value!= "") {
				optionHtml += "<option value='" + value + "' "+(checked ? "selected='selected'":"")+">" + value + "</option>";
				
				model.push({value:value,desc:value});
			}
		});

		
		var html = "<select" +
				   "	xtype='xselect'" +
				   "	title='" + title +"'"+
				   "	defaultvalue='" + defaultValue + "'" +
				   "	datatype='text'"+
				   "	name='" + fieldName + "'" +
				   "	id='" + fieldName + "' "+
				   " 	fieldname='" + fieldName + "'"+
				   "	model='"+$.json.encode(model)+"'"+
				   ">"+
				   optionHtml+
				   "</select>";
		return html;
	}
	
	
	
	var titlejson = top.fieldList.title;
	var fieldjson = top.fieldList.fieldName;
	var oldTitle=null;
	var oldFieldName=null;
	// 初始化控件数据
	var selection = top.editor.getSelection();
	var ranges = selection.getRanges();
	var element = selection.getSelectedElement();
	var xselect_content = $("#xselect_content");

	if (element != null && element.getAttribute('xtype') == 'xselect') {
		if (element.getAttribute("title")) {
			$("#title").val(element.getAttribute("title"));
			oldTitle=element.getAttribute('title');
		}
		if (element.getAttribute('fieldname')) {
			$("#fieldName").val(element.getAttribute('fieldname'));
			oldFieldName=element.getAttribute('fieldname');
		}
		
		$(element.getHtml()).each(function(i) {
			var itemHtml = "<tr index='"+i+"'>"+
					"<td>" +
					"	<input class='form-control' type='text' name='select-value' value='"+ $(this).val()+ "'/>" +
					"</td>" +
					"<td>" +
					"	<input type='radio' name='select-default' "+ ($(this).prop("selected") ? "checked='checked'":"")+ "/>" +
					"</td>" +
					"<td>" +
					"	<a href='javascript:void(0)' class='select-item-delete' index='"+i+"'>删除</a>" +
					"</td>"+
					"</tr>";
			$("#xselect-content tbody").append(itemHtml);
		});
	}

	$("#xselect_content").on("click",".select-item-delete",function(){
		$("#xselect_content tbody tr[index='"+$(this).attr("index")+"']").remove();
	});
	
	$("#select-item-add").on("click",function(){
		var itemHtml =  "<tr>"+
						"	<td><input type='text' name='select-value' class='form-control'/></td>"+
						"	<td><input type='radio' name='select-default'/></td>"+
						"	<td><a href='javascript:void(0)'>删除</a></td>"+
						"</tr>";
		$("#xselect-content tbody").append(itemHtml);
	});

	initFormValidation();
});



