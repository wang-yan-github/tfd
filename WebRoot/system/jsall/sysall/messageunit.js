function getMessagePriv(module)
{
    var html="";
    var requestUrl=contextPath+"/tfd/system/messageconfig/act/MessageConfigAct/getMConfigJsonAct.act";
    $.ajax({
            url:requestUrl,
            dataType:"json",
            data:{module:module},
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                    if(data.sms1=="0")
                    {
                        html+="<input type=\"checkbox\" id=\"wxsms\" name=\"smsRemind\"  disabled=\"disabled\"/>手机微信 &nbsp;";
                    }else if(data.sms1=="1")
                    {
                       html+="<input type=\"checkbox\" id=\"wxsms\" name=\"smsRemind\" value=\"1\" />手机微信  &nbsp;"; 
                    }else if(data.sms1=="2")
                    {
                        html+="<input type=\"checkbox\" id=\"wxsms\" name=\"smsRemind\" value=\"1\" checked=\"checked\" onclick=\"return false\" />手机微信  &nbsp;";
                    }
                    
                     if(data.sms2=="0")
                    {
                        html+="<input type=\"checkbox\" id=\"sitesms\" name=\"smsRemind\"  disabled=\"disabled\"/>内部短信  &nbsp;";
                    }else if(data.sms2=="1")
                    {
                       html+="<input type=\"checkbox\" id=\"sitesms\" name=\"smsRemind\" value=\"1\" />内部短信  &nbsp;"; 
                    }else if(data.sms2=="2")
                    {
                        html+="<input type=\"checkbox\" id=\"sitesms\" name=\"smsRemind\" value=\"1\" checked=\"checked\" onclick=\"return false\" />内部短信  &nbsp;";
                    }
                    
                     if(data.sms3=="0")
                    {
                        html+="<input type=\"checkbox\" id=\"mobilesms\" name=\"smsRemind\"  disabled=\"disabled\"/>手机短信  &nbsp;";
                    }else if(data.sms3=="1")
                    {
                       html+="<input type=\"checkbox\" id=\"mobilesms\" name=\"smsRemind\" value=\"1\" />手机短信  &nbsp;"; 
                    }else if(data.sms3=="2")
                    {
                        html+="<input type=\"checkbox\" id=\"mobilesms\" name=\"smsRemind\" value=\"1\" checked=\"checked\" onclick=\"return false\" />手机短信  &nbsp;";
                    }
                    
                      if(data.sms4=="0")
                    {
                        html+="<input type=\"checkbox\" id=\"emailsms\" name=\"smsRemind\"disabled=\"disabled\"/>内部邮件  &nbsp;";
                    }else if(data.sms4=="1")
                    {
                       html+="<input type=\"checkbox\" id=\"emailsms\" name=\"smsRemind\" value=\"1\" />内部邮件  &nbsp;"; 
                    }else if(data.sms4=="2")
                    {
                        html+="<input type=\"checkbox\" id=\"emailsms\" name=\"smsRemind\" value=\"1\" checked=\"checked\" onclick=\"return false\"  />内部邮件  &nbsp;";
                    }
                    
                      if(data.sms5=="0")
                    {
                        html+="<input type=\"checkbox\" id=\"webemilesms\" name=\"smsRemind\"  disabled=\"disabled\"/>外部邮件  &nbsp;";
                    }else if(data.sms5=="1")
                    {
                       html+="<input type=\"checkbox\" id=\"webemilesms\" name=\"smsRemind\" value=\"1\" />外部邮件  &nbsp;"; 
                    }else if(data.sms5=="2")
                    {
                        html+="<input type=\"checkbox\" id=\"webemilesms\" name=\"smsRemind\" value=\"1\" checked=\"checked\" onclick=\"return false\" />外部邮件  &nbsp;";
                    }
                }
        });
        $("#smsdiv").html(html);
}

function getsmsRemind()
{
    var json={};
$("[name='smsRemind']").each(function(){
    if($(this).prop("checked")==true)
    {
      json[$(this).prop("id")]=$(this).val();
    }else
    {
        json[$(this).prop("id")]="0";
    }
 });
 return JSON.stringify(json);
}
