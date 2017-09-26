
function changeOrg(accountId)
    {
        var username=accountId;
                  var url = contextPath + "/com/system/login/act/DologinAct/CheckLogin.act";
                  $.ajax({
                        url:url,
                        dataType:"text",
                        data:{username:username},
                        async:false,
                        success:function(data){
                            if(data=="pass"){
                                location.href=contextPath+"/system/frame/index.jsp";
                            }else{
                                location.href=contextPath+"/system/returnapi/loginerr.jsp";
                            }
                         }
                  });
    }
$(function(){
    var url=contextPath+"/com/system/login/act/ChangeOrgAct/ChanageOrgListAct.act";
    $.ajax({
            url:url,
            dataType:"json",
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                for(var i=0;data.length>i;i++)
                {
                    if(data[i].flag==true)
                    {
                        $("#change-org").append("<a onclick=\"changeOrg('"+data[i].byName+"');\" class=\"list-group-item\">"+data[i].orgName+"<span class=\"glyphicon glyphicon-ok\" style=\"float:right;\"></span> </a>");
                    }else
                    {
                    $("#change-org").append("<a onclick=\"changeOrg('"+data[i].byName+"');\" class=\"list-group-item\">"+data[i].orgName+"</a>");
                    }
                }
            }
        });
    
    
    
    function hideChangeOrg(){
        $("#change-org").animate({opacity:"0",right:"-300px"},200);
    }
    hideChangeOrg();
    
    function showChangeOrg(){
        $("#change-org").show().animate({opacity:"1",right:"0px"},300);
    }
    
    
    $(document).on("click","#desktop-change-org",function(){
        showChangeOrg();
    })
    .on("click.changeorg.hide",function(e){
        hideChangeOrg();
    })
    .on("click.changeorg.hide.stop","#desktop-change-org",function(e){
        $(".document-click-hide").hide();
        showChangeOrg();
        e.stopPropagation();
    })
    .on("click.to.hide","#change-org",function(e,confirm){
        if (confirm) {
            hideChangeOrg();
        }
    });
    
    
});