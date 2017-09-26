var flowform_plugin_xinput=new Object();
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
		    	},
		    	maxLength:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if(!new RegExp(new RegexUtil().REGEX_INT_0_).test(value)){
										return {valid:false,message:"字段长度必须是大于0的整数！"};
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
	flowform_plugin_xinput.validate=function() {
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
	flowform_plugin_xinput.toDomStr=function() {
		var title = $("#title").val();
		var defaultValue = $("#defaultValue").val();
		var fieldName = $("#fieldName").val();
		var maxLength = $("#maxLength").val();
		var datatype = $("#datatype").val();
		var html = "<input " +
				   "	type='text' " +
				   "	xtype='xinput' "+
				   "	name='" + fieldName + "' " +
				   "	id='" + fieldName + "'"+
				   "	title='" + title + "'"+
				   "	fieldName='" + fieldName + "'"+
				   "	maxlength='" + maxLength + "'"+
				   "	datatype='" + datatype + "' " +
				   "	value='" + defaultValue + "' " +
				   "	defaultvalue='"+ defaultValue + "' "+
				   ">";
		return html;
	}
	
	
	
	var titlejson = top.fieldList.title;
	var fieldjson = top.fieldList.fieldName;
	var oldTitle=null;
	var oldFieldName=null;
	// 加载数据
	var selection = top.editor.getSelection();
	var ranges = selection.getRanges();
	var element = selection.getSelectedElement();
	if (element != null && element.getAttribute('xtype') == 'xinput') {
		if (element.getAttribute('title')) {
			$("#title").val(element.getAttribute('title'));
			oldTitle=element.getAttribute('title');
		}
		if (element.getAttribute('defaultvalue')) {
			$("#defaultValue").val(element.getAttribute('defaultvalue'));
		}
		if (element.getAttribute('name')) {
			$("#name").val(element.getAttribute('name'));
		}
		if (element.getAttribute('fieldname')) {
			$("#fieldName").val(element.getAttribute('fieldname'));
			oldFieldName=element.getAttribute('fieldname');
		}
		if (element.getAttribute('maxlength')) {
			$("#maxLength").val(element.getAttribute('maxlength'));
		}
		if (element.getAttribute('datatype')) {
			$("#datatype").val($(element).attr("datatype"));
		}
	}
	
	initFormValidation();
});


