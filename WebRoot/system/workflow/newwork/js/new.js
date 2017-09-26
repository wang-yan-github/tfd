function doint()
{
    var requestUrl=contextPath+"/tfd/system/workflow/newwork/act/NewWorkFlowAct/createWorkFlowTitleAct.act";
    var paramData={flowTypeId:flowId};
    $.ajax({
        url:requestUrl,
        dataType:"json",
        data:paramData,
        async:false,
        success:function(data){
            $("#title").val(data.flowCode);
            $("#flowName").html("当前流程："+data.flowName);
         }
    });
    
    var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/getFlowFunctionAct.act";
    var paramData={flowTypeId:flowId};
    $.ajax({
        url:requestUrl,
        dataType:"json",
        data:paramData,
        async:false,
        success:function(data){
            createPrcsTable(data);
         }
    });
    
    }
function creatWork()
{
    var requestUrl=contextPath+"/tfd/system/workflow/newwork/act/NewWorkFlowAct/createWorkAct.act";
    var paramData={flowId:flowId,title:$("#title").val()};
    $.ajax({
        url:requestUrl,
        dataType:"json",
        data:paramData,
        async:false,
        success:function(data){
            new SysFrame().tabs("add",{
            	title:"<div  style=\"display:none;\" id="+data.runId+"></div >"+data.title,
            	url:contextPath+data.url
            });
         }
    });
    }
function createPrcsTable(data)
{
    $("#flowFunction").html(data.flowFunction);
    var html="<table style=\"border: 1px solid #ccc;\" width=\"100%;\" class=\"table table-striped\"><tr><td width=\"20%;\" nowrap align=\"center\"><b>步骤号</b></td><td width=\"40%;\" nowrap align=\"center\"><b>步骤名称</b></td><td width=\"40%;\" nowrap align=\"center\"><b>可转步骤号</b></td><tr>";
          html+="<tr><td align=\"center\">"+data.beginPrcs.prcsId+"</td><td align=\"center\">"+data.beginPrcs.prcsName+"</td><td align=\"center\">"+data.beginPrcs.nextPrcs+"</td></tr>"; 
          for(var i=0;data.proPrcs.length>i;i++)
          {
              html+="<tr><td align=\"center\">"+data.proPrcs[i].prcsId+"</td><td align=\"center\">"+data.proPrcs[i].prcsName+"</td><td align=\"center\">"+data.proPrcs[i].nextPrcs+"</td></tr>"; 
          }
          html+="<tr><td align=\"center\">"+data.endPrcs.prcsId+"</td><td align=\"center\">"+data.endPrcs.prcsName+"</td><td align=\"center\">"+data.endPrcs.nextPrcs+"</td></tr>"; 
          html+="</table>";
    $("#prcstable").html(html);
}
    
function goFlowView(flowId)
{
    window.open(contextPath+"/system/workflow/flowgraph/designgraph/index.jsp?flowId="+flowId);
    }
function goFormView(flowId)
{
    var leavePath ="";
    var requestUrl=contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/getPreviewPathAct.act";
    var paramData={flowTypeId:flowId};
    $.ajax({
        url:requestUrl,
        dataType:"text",
        data:paramData,
        async:false,
        success:function(data){
            leavePath=data;
         }
    });
    window.open(contextPath+"/system/workflow/dowork/"+leavePath);
    }