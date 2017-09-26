
$(function(){
	$("#layout").layout({auto:true});
	
	
	getMessagePriv("workNext");
    var requestUrl=contextPath+"/tfd/system/workflow/workflownext/act/WorkFlowNextAct/getNextPrcsInfoAct.act";
    var paramData={nextPrcs:nextData,flowId:flowId};
    $.ajax({
        url:requestUrl,
        dataType:"json",
        data:paramData,
        async:false,
        error:function(e){
            alert(e.message);
        },
        success:function(data){
            if(data.flag=="false")
                {
                layer.msg("没有找到符合条件的步骤!");
                }else{
                var arrhtml = showNextPrcsInfo(data);
                var nextList=eval("("+nextData+")");
                var rightHtml="";
                var leftHtml="<div class=\"list-group\">\n";
                      leftHtml+="<a class=\"list-group-item active\"><b>请选择步骤</b></a>\n";
            for(var i =0;arrhtml.length>i;i++)
                {
                    leftHtml+="<a style=\"cursor: pointer;\"  onclick=\"showdiv('"+arrhtml[i].prcsId+"');\" class=\"list-group-item\">"+
                    "<input type=\"checkbox\" onchange=\"checkNext('prcsinfo_" + nextList[i].prcsId + "');\" name=\"prcsinfo\"id=\"prcsinfo_" + nextList[i].prcsId + "\" value =\"" + nextList[i].prcsId + "\">"+
                    "第["+arrhtml[i].prcsId+"]步:"+arrhtml[i].prcsName+"</a>\n";
                    rightHtml+=arrhtml[i].htmlStr;
                }
            leftHtml=leftHtml+"</div>";
            $("#west").html(leftHtml);
            $("#content").html(rightHtml);
            for(var i=0;nextList.length>i;i++)
                {
                 var accountId=nextList[i].autoUser.accountId;
                 var userName = nextList[i].autoUser.userName;
                $("#opAccount_"+nextList[i].prcsId).val(accountId);
                $("#opUser_"+nextList[i].prcsId).val(userName);
                    if(accountId!=""&&accountId!=undefined)
                    {
                        var opdiv="<div id=\"op"+accountId+"\"  class=\"user-tags\"  name=\"op"+accountId+"\" >"+userName+" <g class=\"close\" onclick=\"del('op','"+accountId+"');\">x</g></div>";
                        $("#opdiv"+nextList[i].prcsId).html(opdiv);
                    }
                }
        }
            
        }
    });
     var forceParallel = nodedata.forceParallel;
    //强制并发设置
    if (forceParallel == "1") {
        $("input[name='prcsinfo']").prop("checked", true);
        $("input[name='prcsinfo']").prop("disabled", "disabled");
    }
});


function showdiv(id)
{
    $("div[name=divcneter]").hide();
    $("#div"+id).show();
    var forceParallel = nodedata.forceParallel;
    if(forceParallel=="0")
    {
        if($("#prcsinfo_"+id).prop("checked"))
        {
            $("#prcsinfo_"+id).prop("checked",false);
        }else
        {
            $("#prcsinfo_"+id).prop("checked",true);
        }
   }else if(forceParallel=="2")
   {
        $("input[name='prcsinfo']").prop("checked", false);
       $("#prcsinfo_"+id).prop("checked",true);
   }
    
    
    return false;
    }
//进行提交生成下一步待办
function go()
{
var smsRemind=getsmsRemind();
var paramStr="[";
var nextText="";  
$("input[name=prcsinfo]").each(function() {
if ($(this).prop("checked"))
{  
    nextText +=$(this).val()+ ",";  
}
}); 
if(nextText==""){
    layer.msg("请选择下一步骤！");
    return;
        }else
            {
                var nextArr=nextText.substr(0,nextText.length-1).split(",");
                for(var i=0;nextArr.length>i;i++)
                    {
                        if(nextArr[i]=="0")
                            {
                            var zhAccount=$("#userName_"+nextArr[i]).val();
                            paramStr+="{\"prcsId\":\""+nextArr[i]+"\",\"opAccount\":\"\",\"zhAccount\":\""+zhAccount+"\"},";
                            }else
                                {
                                var accountIds=$("#opAccount_"+nextArr[i]).val();
                                var opAccount="";
                                 var zhAccount="";
                                if(accountIds.indexOf(",")>-1)
                                {
                                    opAccount = accountIds.substring(0,accountIds.indexOf(","));
                                    zhAccount=accountIds.substring(accountIds.indexOf(",")+1,accountIds.length);
                                }else
                                {
                                   opAccount =accountIds;
                                   zhAccount="";
                                }
                                if(opAccount==""||opAccount==null)
                                    {
                                    layer.msg("请选择第"+nextArr[i]+"步的主办人员！");
                                    return;
                                    }else
                                        {
                                        paramStr=paramStr+"{\"prcsId\":\""+nextArr[i]+"\",\"opAccount\":\""+opAccount+"\",\"zhAccount\":\""+zhAccount+"\"},";
                                        }
                                }
                }
            }
if(paramStr.substring(paramStr.length-1)==",")
    {
    paramStr=paramStr.substring(0,paramStr.length-1);
    }
    paramStr=paramStr+"]";
    var requestUrl=contextPath+"/tfd/system/workflow/workflownext/act/WorkFlowNextAct/goNextAct.act";
$.ajax({
    url:requestUrl,
    dataType:"text",
    data:{paramStr:paramStr,flowId:flowId,tableName:tableName,prcsId:prcsId,runId:runId,runPrcsId:runPrcsId,smsRemind:smsRemind},
    async:false,
    error:function(e){
        alert(e.message);
    },
    success:function(data){
        if(data=="OK")
            {
            layer.msg("工作流转交成功！");
            new SysFrame().tabs('close',"<div  style=\"display:none;\" id="+runId+"></div >办理工作流");
            location.href=contextPath+"/system/workflow/worklist/index.jsp";
            }else if(data=="flowWait"){
             layer.msg("子流程没有结束，请等待！");
            }
        }
});
}
function del(op,id,prcsId){
    if(op=="op"){
    	$("#op"+id).remove();
    	var ids=$("#opAccount_"+prcsId).val().split(",");
    	for (var i = 0; i < ids.length; i++) {
			if (ids[i]==id) {
				ids.splice(i,1);
			}
		}
    	$("#opAccount_"+prcsId).val(ids.join(","));
    }else{
    	$("#zh"+id).remove(); 
    	var ids=$("#opUser_"+prcsId).val().split(",");
    	for (var i = 0; i < ids.length; i++) {
			if (ids[i]==id) {
				ids.splice(i,1);
			}
		}
    	$("#opUser_"+prcsId).val(ids.join(","));
    }
}
    
function goback(){
	history.back(-1);
}