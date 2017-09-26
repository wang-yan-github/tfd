/**
 * 检查内部短信
 */
function checkShortMsrg() {
	var requestUrl=contextPath+"/tfd/system/sms/act/SmsAct/getNoReadSmsAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{},
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$("#smsContent").html(data[0].SMS_CONTECT);
				}
		});
}
