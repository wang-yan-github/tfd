$(function() {
	var regexUtil=new RegexUtil();
	
	$.fn.zTree.init(
		$("#dept-tree"),
		{
	        data:{
	          simpleData:{
	            enable:true,
	            idKey:"id",
	            pIdKey:"pid",
	            rootPId:"0"
	          },
	          key:{
	            name:"text"
	          }
	        },
	        async:{
	          enable:true,
	          url:contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeOfChose.act",
	          autoParam:["id"]
	        },
	        view:{
	        	selectedMulti:false
	        },
	        callback:{
	        	onClick:function(event,treeId,treeNode){
	        		var dept={
	        				orgLeaveId:treeNode.id=="-1"?"0":treeNode.id,
	        				orgLeaveName:treeNode.text,
	        				parentDeptLongId:treeNode.deptLongId,
	        				parentDeptLongName:treeNode.deptLongName
	        		};
	        		
	        		for(var fieldName in dept){
	        			$("#dept-form *[name='"+fieldName+"']").val(dept[fieldName]);
	        		}
					
	        		$(document).trigger('click.bs.dropdown.data-api');
	        		
	        	}
	        }
	    }
	);

	$('#dept-form').formValidation({
		framework:"bootstrap",
		err:{
			container:"tooltip"
		},
		icon: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
		fields : {
			deptName : {
				validators : {
					notEmpty : {
						message : '请填写部门名称！'
					}
				}
			},
			deptTel:{
				validators : {
					regexp : {
						message : '请填写正确的电话号码！',
						regexp:regexUtil.REGEX_TELEPHONE
					}
				}
			},
			deptFax:{
				validators : {
					regexp : {
						message : '请填写正确的传真号码！',
						regexp:regexUtil.REGEX_TELEPHONE
					}
				}
			},
			deptEmail : {
				validators : {
					emailAddress : {
						message : '请填写正确的邮箱地址！'
					}
				}
			}
		}
	})
	.on('success.form.fv',function(e) {
		e.preventDefault();
		
		$.ajax({
			url : contextPath+ "/tfd/system/unit/dept/act/DeptAct/addDept.act",
			data : $(e.target).serialize(),
			type : 'POST',
			dataType : "json",
			async : false,
			success : function(result) {
				if (eval(result)) {
					layer.msg("添加成功！");
				} else {
				    layer.msg("添加失败！");
				}
				parent.location.reload();
			}
		});
	});
	
	$(document)
    .on('click.bs.dropdown.data-api', '.dropdown .ztree', function (e) { e.stopPropagation(); })
    .on('click.bs.dropdown.data-api', "*[name='orgLeaveName']", function (e) { e.stopPropagation(); })
	.on("click","#dept-form *[name='orgLeaveName']",function(){
		$("#dropdown-dept-tree .dropdown-toggle").trigger("click");
	})
	.on("click","#dept-form *[name='deptLeadUserName']",function(){
		$('#dept-form').data("formValidation").updateStatus("deptLeadUserName", 'NOT_VALIDATED');
		userinit(['deptLead','deptLeadUserName'],'true');
	});
});