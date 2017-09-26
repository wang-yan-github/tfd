var unit_dept_edit=new Object();
$(function() {
	var deptId=unit_dept_edit.deptId;
	var regexUtil=new RegexUtil();
	
	var deptInfo=sys_unit_dept_getDeptInfo(deptId);
	for(var fieldName in deptInfo){
		$input=$("#dept-form *[name='"+fieldName+"']");
		if ($input.length>0) {
			$input.val(deptInfo[fieldName]);
		}
	}
	
	var parentDeptInfo=sys_unit_dept_getDeptInfo(deptInfo.orgLeaveId);
	$("#dept-form *[name='parentDeptLongId']").val(parentDeptInfo.deptLongId);
	$("#dept-form *[name='parentDeptLongName']").val(parentDeptInfo.deptLongName);
	
	
	
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
	        		}
	        		
	        		
	        		var orgLeaveIdCheck=true;
	        		if (deptId) {
	        			if(dept.parentDeptLongId.indexOf(deptInfo.deptLongId)>-1){
	        				orgLeaveIdCheck=false;
	        				layer.msg("父级部门不能选择当前部门及当前部门的子级！");
	        			}
	        		}
	        		
	        		if (orgLeaveIdCheck) {
	        			for(var fieldName in dept){
	        				$("#dept-form *[name='"+fieldName+"']").val(dept[fieldName]);
	        			}
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
			type : 'POST',
			url : contextPath+ "/tfd/system/unit/dept/act/DeptAct/updateDept.act",
			dataType : "json",
			data : $(e.target).serialize(),
			async : false,
			success : function(result) {
				if (eval(result)) {
					layer.msg("已保存！");
				} else {
					layer.msg("操作失败！");
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
		userinit(['deptLead','deptLeadUserName'],true);
	})
	.on("click","#dept-delete",function(){
		var count=sys_unit_dept_getDeptAllUserInfoCount(deptInfo.deptId);
		if (count!=null&&count==0) {
			if (window.confirm("确定删除当前部门，确定后，当前部门及当前部门的子部门都将被删除！？")) {
				$.ajax({
					url : contextPath+ "/tfd/system/unit/dept/act/DeptAct/deleteDept.act",
					data:{deptId:deptInfo.deptId},
					type:"POST",
					dataType : "json",
					async:false,
					success : function(result) {
						if (eval(result)) {
							layer.msg("已删除！");
						} else {
							layer.msg("操作失败！");
						}
						parent.location.reload();
					}
				});
			}
		}else{
			layer.msg("该部门或该部门的子部门尚有人员，请先删除这些人员！");
		}
	});
	
});