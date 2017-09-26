var SysUnitUserInfo=function(){
	this.getCurrentUserInfo=function(){
		var userInfoList=null;
		$.ajax({
			url:contextPath+"/tfd/system/unit/userinfo/act/UserInfoAct/getCurrentUserInfoListByAccountId.act",
			async:false,
			dataType:"json",
			success:function(data){
				userInfoList=data;
			}
		});
		return userInfoList;
	}
}