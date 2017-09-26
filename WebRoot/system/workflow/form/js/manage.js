    var flowTypeNodes;
    var zNodes;
    $(function(){
        var requestUrl=contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getWorkFlowFormByFormIdAct.act";
        var paramData={formId:formId};
        $.ajax({
            url:requestUrl,
            dataType:"json",
            data:paramData,
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                data=data[0];
                deptId=data.formDeptId;
                for(var name in data){
                    $("#"+name).val(data[name]);
                }
            }
        });
        var requestUrl=contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeAct.act";
        $.ajax({
            url:requestUrl,
            dataType:"json",
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                zNodes=data;
            }
        }); 
        
        var requestUrl=contextPath+"/tfd/system/workflow/workflowtype/act/WorkFlowTypeAct/getWorkFlowTypeTreeAct.act";
        $.ajax({
            url:requestUrl,
            dataType:"json",
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                flowTypeNodes=data;
            }
        }); 
    });
function designForm()
{
    var leavaPath=$("#formTableName").val();
    window.open(contextPath+"/system/workflow/form/design/designform.jsp?formId="+formId+"&leavePath="+leavaPath);
    }
    
var setting = {
        view: {
            dblClickExpand: false,
            selectedMulti:false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
};

var flowTypeSetting = {
        view: {
            dblClickExpand: false,
            selectedMulti:false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: flowTypeOnClick
        }
};
    
function flowTypeOnClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("flowTypeDemo"),
    nodes = zTree.getSelectedNodes(),
    v = "";
    vid="";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
        vid+=nodes[i].id + ",";
    }
    if (v.length > 0 )
    {
        v = v.substring(0, v.length-1);
        $("#workFlowTypeName").val(v);
    }
    if (vid.length > 0 ) 
    {
         vid = vid.substring(0, vid.length-1);
        $("#workFlowTypeId").val(vid);
    }
    $("#menuFlowType").fadeOut("fast");
    $('#form1').bootstrapValidator('revalidateField', 'workFlowTypeName');
}
    
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
    nodes = zTree.getSelectedNodes(),
    v = "";
    vid="";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
        vid+=nodes[i].id + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    {
    $("#formDept").val(v);;
    }
    if (vid.length > 0 ) vid = vid.substring(0, vid.length-1);
    {
        $("#formDeptId").val(vid);
    }
    $("#menuContent").fadeOut("fast");
}

function flowTypeShowMenu() {
    var cityObj = $("#workFlowTypeName");
    var cityOffset = $("#workFlowTypeName").offset();
    $("#menuFlowType").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown1);
}

function showMenu() {
    var cityObj = $("#formDeptName");
    var cityOffset = $("#formDeptName").offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function hideMenu1() {
    $("#menuFlowType").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown1);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}
function onBodyDown1(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuFlowType" || $(event.target).parents("#menuFlowType").length>0)) {
        hideMenu1();
    }
}
$(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    $.fn.zTree.init($("#flowTypeDemo"), flowTypeSetting, flowTypeNodes);
});

function readForm()
{
    var formId = $("#formId").val();
    if(formId=="")
        {
        layer.msg('表单名称不能为空！');
        }else
            {
                window.open(contextPath+"/system/workflow/form/readform.jsp?formId="+formId);
            }
}