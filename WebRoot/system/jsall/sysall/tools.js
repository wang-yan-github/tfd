    function getUserName(accountIdStr)
    {
        var returnData="";
        var requestUrl=contextPath+"/tfd/system/unit/account/act/AccountAct/getUserNameStrAct.act";
        $.ajax({
            url:requestUrl,
            dataType:"text",
            data:{accountIdStr:accountIdStr},
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
            returnData= data;    
            }
        });
        return returnData;
    }
    //通过RUNID获取发起人姓名
    function getUserNameByRunId(runId)
    {
         var returnData="";
        var requestUrl=contextPath+"/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/flowBeginUserAct.act";
        $.ajax({
            url:requestUrl,
            dataType:"text",
            data:{runId:runId},
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
            returnData= data;    
            }
        });
        return returnData;
    }


//生成SELECT HTML
function createselect(id,requestUrl)
{
        $.ajax({
            url:requestUrl,
            dataType:"json",
            data:{},
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                var ophtml="<option value=\"0\">请选择</option>\n";
                for(var i=0;data.length>i;i++)
                {
                    ophtml+="<option value=\""+data[i].id+"\">"+data[i].text+"</option>\n";
                }
                $("#"+id).html(ophtml);
            }
        });
}
