/**
 * 系统.组织机构.部门.api
 */
/**
 * 获取部门信息
 * @param deptId
 * @returns
 */
function sys_unit_dept_getDeptInfo(deptId){
	var deptInfo=null;
	$.ajax({
		url : contextPath+ "/tfd/system/unit/dept/act/DeptAct/getDeptInfo.act",
		data:{deptId:deptId},
		type:"POST",
		dataType : "json",
		async:false,
		success : function(data) {
			deptInfo=data;
		}
	});
	return deptInfo;
}

function sys_unit_dept_getDeptAllUserInfoCount(deptId){
	var count=null;
	$.ajax({
		url : contextPath+ "/tfd/system/unit/dept/act/DeptAct/getDeptAllUserInfoCount.act",
		data:{deptId:deptId},
		type:"POST",
		dataType : "json",
		async:false,
		success : function(result) {
			count=result;
		}
	});
	return count;
}