function doinit()
{
    var url = contextPath+"/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/getAllRunPrcsByRunIdAct.act";
    $.ajax({
        url:url,
        data:{runId:runId},
        dataType:"json",
        async:false,
        success:function(returnData){
            var html="";
            var endTime;
            var createTime;
            for(var i =0;returnData.length>i;i++)
                {
                    if(returnData[i].endTime != "当前"){
                        var eTime =returnData[i].endTime  ;
                        endTime=(new Date(eTime.replace(/-/g, "\/"))).getTime();
                    }else
                        {
                        var eTime =new Date();
                        endTime = eTime.getTime();
                        }
                        var cTime = returnData[i].createTime;
                        createTime=(new Date(cTime.replace(/-/g, "\/"))).getTime();
                        var passTime = Number((endTime-createTime)/1000).formatTime();
                    html+="<tr>";
                    html+="<td align=\"center\">"+returnData[i].runPrcsId+"</td>";
                    html+="<td align=\"center\"><b>"+returnData[i].prcsName+"</b></td>";
                    html+="<td align=\"center\">"+returnData[i].userName+"</td>";
                    html+="<td align=\"center\"><div title='接收时间："+returnData[i].createTime+"办理完毕时间："+returnData[i].endTime+"'>共办理"+passTime+"</div></td>";
                    html+="<td align=\"center\">";
                    if(returnData[i].status=="0")
                        {
                            html+="<button class=\"btn btn-primary btn-sm\" onclick=\"goflowback('"+returnData[i].runPrcsId+"','"+returnData[i].prcsId+"');\">回退</button>  <button class=\"btn btn-primary btn-sm\" onclick=\"donext('"+returnData[i].runPrcsId+"','"+returnData[i].prcsId+"');\">转交</button>";
                        }
                    html+="</td>";
                    html+="</tr>";
                }
            $("#tbody").html(html);
        }
    });
    }
 function goflowback(runPrcsId,prcsId)
 {
     alert(1);
 }   
    
function donext(runPrcsId,prcsId)
{
    var html="";
    var url=contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/getFlowProcessSelsectByRunIdAct.act";
    $.ajax({
        url:url,
        data:{runId:runId},
        dataType:"json",
        async:false,
        success:function(returnData){
            for(var i=0;returnData.length>i;i++)
            {
                html+="<option value=\""+returnData[i].id+"\">"+returnData[i].text+"</opti0n>\n";
            }
        }
    });
    $("#goprcsId").html(html);
                $("#myModalLabel").html("流程转交");
                $("#div-modal-dialog").width(300);
                $('#modal').modal({backdrop: 'static', keyboard: false});
                $('#modal').on('shown.bs.modal',function(){
                $("#gonext").unbind('click').click(function (){
                    var goprcsId=$("#goprcsId").val();
                    var gonextuser=$("#gonextuser").val();
                    Forced(runPrcsId,prcsId,goprcsId,gonextuser);
                    }
                    );
                });
}

function Forced(runPrcsId,prcsId,goprcsId,gonextuser)
{
     var url = contextPath+"/tfd/system/workflow/monitor/act/MonitorAct/doForcedAct.act";
    $.ajax({
        url:url,
        data:{runPrcsId:runPrcsId,prcsId:prcsId,goprcsId:goprcsId,gonextuser:gonextuser,runId:runId},
        dataType:"text",
        async:false,
        success:function(returnData){
            alert(returnData);
        }
    });
}
