var zNodes ;
$(function(){
    var requestUrl=contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getWorkFlowFormTypeTree.act";
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
//      view: {
//          showLine: false
//      },
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
            $.fn.zTree.init($("#formtree"), setting, zNodes);
        });
function editinfo(event, treeId, treeNode)
{

    if(!treeNode.isParent)
        {
            parent["edit"].location=contextPath+"/system/workflow/form/manage.jsp?formId="+treeNode.id;
        }else
        {
            parent["edit"].location=contextPath+"/system/workflow/form/formlist.jsp?sortId="+treeNode.id;
        }
}
function add()
{
    parent["edit"].location=contextPath+"/system/workflow/form/add.jsp";
    }
    

