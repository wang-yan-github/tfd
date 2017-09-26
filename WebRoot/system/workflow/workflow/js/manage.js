function designFlow(flowTypeId)
{
    window.open(contextPath+"/system/workflow/flowprocess/designflow/index.jsp?flowTypeId="+flowTypeId);
}
$(function (){
    var requestUrl =contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/getWorkFlowByFlowIdAct.act";
            $.ajax({
                url:requestUrl,
                dataType:"json",
                data:{flowTypeId:flowTypeId},
                async:false,
                success:function(data){
                form=data;
                    getSelect("workflowtype","moduleId",form.moduleId);
                    for(var name in form)
                    {
                        $("*[name='"+name+"']").val(form[name]);
                    }
                    var editorElement = CKEDITOR.document.getById( 'editor' );
                    editorElement.setHtml(form.editor);
                    getFormIdSelect();
                    getWrokFlowTypeSelect();
                }
            });
            var url = contextPath+"/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getTitleAndFieldNameByFlowIdAct.act";
            var items;
            $.ajax({
                    url:url,
                    dataType:"json",
                    data:{flowId:flowTypeId},
                    async:false,
                    error:function(e){
                        alert(e.message);
                    },
                    success:function(data){
                        items=data;
                    }
                });
            var html = "<optgroup label=\"表单字段\">";
            for(var i=0;i<items.length;i++){
                var data = items[i];
                html+="<option value='"+data.fieldName+"'>"+data.title+"</option>";
            }
            html +="</optgroup>";
            html +="<optgroup label=\"流程实例信息\">";
            html +="<option value=\"[发起人姓名]\">[发起人姓名]</option>";
            html +="<option value=\"[发起人帐号]\">[发起人帐号]</option>";
            html +="<option value=\"[发起人部门]\">[发起人部门]</option>";
            html +="<option value=\"[发起人角色]\">[发起人角色]</option>";
            html +="<option value=\"[主办人会签意见]\">[主办人会签意见]</option>";
            html +="<option value=\"[经办人会签意见]\">[经办人会签意见]</option>";
            html +="<option value=\"[公共附件名称]\">[公共附件名称]</option>";
            html +="<option value=\"[公共附件个数]\">[公共附件个数]</option>";
            html +="<option value=\"[当前步骤序号]\">[当前步骤序号]</option>";
            html +="<option value=\"[当前步骤自增ID号]\">[当前步骤自增ID号]</option>";
            html +="<option value=\"[当前步骤名称]\">[当前步骤名称]</option>";
            html +="<option value=\"[当前主办人姓名]\">[当前主办人姓名]</option>";
            html +="<option value=\"[当前主办人帐号]\">[当前主办人帐号]</option>";
            html +="<option value=\"[当前主办人角色]\">[当前主办人角色]</option>";
            html +="<option value=\"[当前主办人辅助角色]\">[当前主办人辅助角色]</option>";
            html +="<option value=\"[当前主办人部门]\">[当前主办人部门]</option>";
            html +="<option value=\"[当前主办人上级部门]\">[当前主办人上级部门]</option>";
            html +="</optgroup>";
            html +="<optgroup label=\"流程变量\">";
            html +="</optgroup>";
            var printField=form.printField;
            var html1="";
            for(var i=0;printField.length>i;i++)
            {
                    html1+="  <input type=\"checkbox\" name=\"p_printfield\"  value=\""+printField[i].field+"\"";
                    if(printField[i].printfield=="1")
                    {
                        html1+=" checked=\"checked\" ";
                    }
                    html1+="/>"+printField[i].title;
            }
            $("#printField").html(html1);
            $("#fields").append(html);
});
     
function getFormIdSelect()
{
    var requestUrl=contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getWorkFlowFormTypeTree.act";
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
}

function getWrokFlowTypeSelect()
{
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
    }
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        v = "";
        vid="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            if(!nodes[i].isParent)
                {
                v += nodes[i].name + ",";
                vid+=nodes[i].id + ",";
                }
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var deptObj = $("#formName");
        deptObj.attr("value", v);
        if (vid.length > 0 )
        	{
        	vid = vid.substring(0, vid.length-1);
        	 if(vid!="")
         	{
         	$("#menuContent").fadeOut("fast");
         	}
        	}
        var deptIdObj = $("#formId");
        deptIdObj.attr("value",vid);
        if($("#formId").val()=="")
        {
            $("#formId").val(form.formId);
            $("#formName").val(form.formName);
        }
    }

    function flowTypeShowMenu() {
        var cityObj = $("#workFlowTypeName");
        var cityOffset = $("#workFlowTypeName").offset();
        $("#menuFlowType").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown1);
    }
    function showMenu() {
        var cityObj = $("#formName");
        var cityOffset = $("#formName").offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu1() {
        $("#menuFlowType").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown1);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown1(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuFlowType" || $(event.target).parents("#menuFlowType").length>0)) {
            hideMenu1();
        }
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        $.fn.zTree.init($("#flowTypeDemo"), flowTypeSetting, flowTypeNodes);
    });

function updateFlowCahce()
{
    var requestUrl =contextPath+"/tfd/system/workflow/workflow/act/WorkFlowAct/updateFlowCahceAct.act";
    $.ajax({
            type:'POST',
            url:requestUrl,
            dataType:"text",
            data:{flowTypeId:flowTypeId},
            async:false,
            success:function(data){
                if(data=="OK")
                    {
                    layer.msg('缓存更新成功！');
                    }else if(data=="LOCK")
                        {
                            layer.msg('由于流程单表可能被定制，目前更新被锁定!');
                        }else
                            {
                            layer.msg("缓存更新失败！");
                            }
            }
        });
    }
    function setmanagepriv()
    {
        location.href=contextPath+"/system/workflow/workflow/managepriv.jsp?flowTypeId="+flowTypeId;
    }
    
    function readForm()
    {
        var formId = $("#formId").val();
        if(formId=="")
            {
            layer.msg("表单名称不能为空！");
            }else
                {
                    window.open(contextPath+"/system/workflow/workflow/readform.jsp?formId="+formId);
                }
    }
    
function deliverwork(flowTypeId){
    $("#myModalLabel").html("工作批量转交");
    $("#div-modal-dialog").width(360);
    $("#modaliframe").width(355);
    $("#modaliframe").height(220);
    $('#modal').modal({backdrop: 'static', keyboard: false});
    $('#modal').on('shown.bs.modal',function(){
    $("#savedata").unbind('click').click(function (){
        var changeA=$("#changeA").val();
        var toChangeB=$("#toChangeB").val();
        if(changeA!=""&&toChangeB!="")
        {
            var requestUrl=contextPath+"/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/deliverWorkAct.act";
            $.ajax({
                url:requestUrl,
                dataType:"text",
                async:false,
                type:'POST',
                data:{flowTypeId:flowTypeId,changeA:changeA,toChangeB:toChangeB},
                error:function(e){
                    alert(e.message);
                },
                success:function(data){
                    if(data>=0)
                    {
                        layer.msg("工作较交完成!");
                    }
                }
        }); 
        }else
        {
            layer.msg("原办理人与目标办理人均不能为空!");
        } 
        
    $('#modal').modal("hide");
        }
        );
    });
}