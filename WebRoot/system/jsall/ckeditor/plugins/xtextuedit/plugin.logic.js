
var flowform_plugin_xtextuedit=new Object();
$(function() {
	var titlejson = top.fieldList.title;
	var fieldjson = top.fieldList.fieldName;
	var oldTitle=null;
	var oldFieldName=null;
	
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
	flowform_plugin_xtextuedit.validate=function() {
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


	//转换dom节点，required
	flowform_plugin_xtextuedit.toDomStr=function(){
		var title = $("#title").val();
		var style = $("#style").val();
		var defaultValue = $("#defaultValue").val();
		var fieldName=$("#fieldName").val();
		
		var html = "<img src='/tfd/system/styles/images/workflow/uedit.png' " +
				   "	type='text' " +
				   "	xtype='xtextuedit'  "+
				   "	datatype='text'"+	
				   " 	name='"+fieldName+ "' " +
				   "	id='"+fieldName+"' "+
				   " 	fieldName='"+fieldName+"'"+
		           "	title='"+title+"'"+
		           " 	value='"+defaultValue+"' " +
		           "	defaultvalue='"+defaultValue+"' "+
		           " 	style='"+style+"' "+
		           "/>";
		return html;
	}
	
	// 加载数据
	var selection = top.editor.getSelection();
	var ranges = selection.getRanges();
	var element = selection.getSelectedElement();
	if (element != null && element.getAttribute('xtype') == 'xtextuedit') {
		if (element.getAttribute('title')) {
			$("#title").val(element.getAttribute('title'));
			oldTitle=element.getAttribute('title');
		}
		if (element.getAttribute('defaultvalue')) {
			$("#defaultValue").val(element.getAttribute('defaultvalue'));
		}
		if (element.getAttribute('fieldname')) {
			$("#fieldName").val(element.getAttribute('fieldname'));
			oldFieldName=element.getAttribute('fieldname');
		}
		if (element.getAttribute('style')) {
			$("#style").val(element.getAttribute('style'));
		}
	}

	initFormValidation();
});


