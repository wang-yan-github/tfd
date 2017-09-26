var zNodes ;
$(function(){
    var requestUrl=contextPath+"/tfd/system/workflow/newwork/act/NewWorkFlowAct/getNewTreeAct.act";
    $.ajax({
        url:requestUrl,
        dataType:"json",
        data:{},
        async:false,
        error:function(e){
            alert(e.message);
        },
        success:function(data){
            zNodes=data;
            }
    });
});

var setting = {
            data: {
                simpleData: {
                    enable: true
                }
                },
            callback:{
                onClick:editinfo
            }
        };
$(document).ready(function(){
            $.fn.zTree.init($("#workflowtree"), setting, zNodes);
        });
function editinfo(event, treeId, treeNode)
{
    if(!treeNode.isParent)
        {
            parent["edit"].location=contextPath+"/system/workflow/newwork/new.jsp?flowId="+treeNode.id;
        }
}
function showAll()
{
    parent["edit"].location=contextPath+"/system/workflow/newwork/allwork.jsp";
    }
function historyWork()
{
    parent["edit"].location=contextPath+"/system/workflow/newwork/insert.jsp";
    }