var zNodes;
var nodesArrs=[];
$(function (){
    var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/getMenuPrivByIdAct.act";
    $.ajax({
            url:requestUrl,
            dataType:"json",
            data:{userPrivId:userPrivId},
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
        check: {
            autoCheckTrigger: true,
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true}
                },
        view: {
            showLine: false
                }
           };
$(document).ready(function(){
    for(var i=0;zNodes.length>i;i++)
    {
        if(zNodes[i].pId=="0")
        {
            var nodesArr=[];
            nodesArr.push(zNodes[i]);
            for(var s=0;zNodes.length>s;s++)
            {
                if(zNodes[s].pId==zNodes[i].id)
                {
                    nodesArr.push(zNodes[s]);
                     for(var x=0;zNodes.length>x;x++)
                        {
                            if(zNodes[x].pId==zNodes[s].id)
                            {
                                nodesArr.push(zNodes[x]);
                            }
                        }
                }
            }
            nodesArrs.push(nodesArr);
        }
    }
    for(var j=0;nodesArrs.length>j;j++)
    {
        var html="<div class=\"menulist\"><ul class='ztree' id=\"tree"+j+"\" name=\"tree"+j+"\"></ul></div>";
        $("#menudiv").append(html);
        $.fn.zTree.init($("#tree"+j), setting, nodesArrs[j]);
        $.fn.zTree.getZTreeObj("tree"+j).expandAll(true);
    }
});

function doSvae(){
     var userPrivStr="";
     for(var i=0;nodesArrs.length>i;i++)
    	 {
    	 var treeObj = $.fn.zTree.getZTreeObj("tree"+i);
    	 var sNodes = treeObj.getCheckedNodes(true);
    	 if (sNodes.length > 0) {
    		 for(var j=0;j<sNodes.length;j++){
    			 userPrivStr += sNodes[j].id+",";
    		 }
    	 }
     }
     if(userPrivStr.length>0)
     {
    	 userPrivStr=userPrivStr.substr(0,userPrivStr.length-1);
     }
     var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/updateMenuPrivAct.act";
        $.ajax({
                url:requestUrl,
                dataType:"text",
                data:{userPrivId:userPrivId,userPrivStr:userPrivStr},
                async:false,
                error:function(e){
                    alert(e.message);
                },
                success:function(data){
                    if(data=="1")
                        {
                    	layer.msg("修改成功!");
                        }
                    }
            });
     }