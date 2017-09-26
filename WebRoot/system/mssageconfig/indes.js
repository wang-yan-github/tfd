function save()
{
    var workNextValue={
        "sms1":$("#worknextsms1").val(),
        "sms2":$("#worknextsms2").val(),
        "sms3":$("#worknextsms3").val(),
        "sms4":$("#worknextsms4").val(),
        "sms5":$("#worknextsms5").val(),
        };
   var passTimeValue={
        "sms1":$("#passtimesms1").val(),
        "sms2":$("#passtimesms2").val(),
        "sms3":$("#passtimesms3").val(),
        "sms4":$("#passtimesms4").val(),
        "sms5":$("#passtimesms5").val(),
        };
   var workEndValue={
        "sms1":$("#workendsms1").val(),
        "sms2":$("#workendsms2").val(),
        "sms3":$("#workendsms3").val(),
        "sms4":$("#workendsms4").val(),
        "sms5":$("#workendsms5").val(),
        };
   var workFollowValue={
        "sms1":$("#workfollowsms1").val(),
        "sms2":$("#workfollowsms2").val(),
        "sms3":$("#workfollowsms3").val(),
        "sms4":$("#workfollowsms4").val(),
        "sms5":$("#workfollowsms5").val(),
        };
   var noticeValue={
        "sms1":$("#noticesms1").val(),
        "sms2":$("#noticesms2").val(),
        "sms3":$("#noticesms3").val(),
        "sms4":$("#noticesms4").val(),
        "sms5":$("#noticesms5").val(),
        };  
  var newsValue={
      "sms1":$("#newssms1").val(),
      "sms2":$("#newssms2").val(),
      "sms3":$("#newssms3").val(),
      "sms4":$("#newssms4").val(),
      "sms5":$("#newssms5").val(),
  }; 
 var calendarValue={
      "sms1":$("#calendarsms1").val(),
      "sms2":$("#calendarsms2").val(),
      "sms3":$("#calendarsms3").val(),
      "sms4":$("#calendarsms4").val(),
      "sms5":$("#calendarsms5").val(),
  }; 
 var diaryValue={
      "sms1":$("#diarysms1").val(),
      "sms2":$("#diarysms2").val(),
      "sms3":$("#diarysms3").val(),
      "sms4":$("#diarysms4").val(),
      "sms5":$("#diarysms5").val(),
  }; 
  var meetValue={
      "sms1":$("#meetsms1").val(),
      "sms2":$("#meetsms2").val(),
      "sms3":$("#meetsms3").val(),
      "sms4":$("#meetsms4").val(),
      "sms5":$("#meetsms5").val(),
  }; 
   var emailValue={
      "sms1":$("#emailsms1").val(),
      "sms2":$("#emailsms2").val(),
      "sms3":$("#emailsms3").val(),
      "sms4":$("#emailsms4").val(),
      "sms5":$("#emailsms5").val(),
  }; 
  var value={
                    "workNextValue":workNextValue,
                    "passTimeValue":passTimeValue,
                    "workEndValue":workEndValue,
                    "workFollowValue":workFollowValue,
                    "noticeValue":noticeValue,
                    "newsValue":newsValue,
                    "calendarValue":calendarValue,
                    "diaryValue":diaryValue,
                    "meetValue":meetValue,
                    "emailValue":emailValue
                    };
  value="["+JSON.stringify(value)+"]";
  var requestUrl=contextPath+'/tfd/system/messageconfig/act/MessageConfigAct/updateMessageConfigAct.act';
        $.ajax({
                url:requestUrl,
                data:{value:value},
                type:"POST",
                dataType:"text",
                async:false,
                error:function(e){
                    alert(e.message);
                },
                success:function(data){
                    if(data=="1")
                    {
                        alert("保存成功！");
                    }
                }
        });
  
}
