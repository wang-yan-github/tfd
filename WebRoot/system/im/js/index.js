 var menuData;   
$(function(){
    $.ajax({
        url:contextPath+"/tfd/system/menu/act/SysMenuAct/getSysMeunJson.act",
        dataType:"json",
        type:'POST',
        async:false,
        error:function(e){
            alert(e.message);
        },
        success:function(data){
            menuData=data;
            initMenu(menuData);
            initMenuOpt();
        }
    }); 
    $(".menu_li").mouseover(function(){
		$(this).addClass("moveli");
	});
	$(".menu_li").mouseout(function(){
		$(this).removeClass("moveli");
	}); 
});


function initMenu(menuData){
    var menu = findChilds("0");
    var menuHtml = "";
    $.each(menu,function(index,menu){
        menuHtml += "<div class='menu_list'>";
        menuHtml += "<div class='menu_li' id='menu"+menu.id+"' ><div class='menu_icon'><img width='40' height='40' src='"+menu.icon+"'/></div>";
        menuHtml +="<div class=\"menu_str\" ><div class='menu_name'>"+menu.name+"</div><div class=\"menu_sign\" >今日的风儿好喧嚣啊</div></div></div>";
        menuHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;'></div>";
        menuHtml +="</div>";
    });
    $("#menu_content").html(menuHtml);
}
function initShortMenu(data){
    var menuHtml = "";
    $.each(data,function(index,menu){
        menuHtml += "<div class='menu_list'>";
        menuHtml += "<div class='menu_li' id='menu"+menu.id+"' onclick=\"goUrl('"+menu.name+"','"+menu.urls+"')\" ><div class='menu_icon'><img width='24' height='24' src='"+menu.icon+"'/></div>";
        menuHtml +="<div class='menu_name'>"+menu.name+"</div></div>";
        menuHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;'></div>";
        menuHtml +="</div>";
    });
    $("#shortMenu_content").html(menuHtml);
}



function findChilds(id){
    var childs=[];
    for(var i=0;i<menuData.length;i++){
        if(menuData[i].pId==id){
            childs.push(menuData[i]);
        }
    }
    return childs;
}
function initMenuOpt(){
    $('.menu_li').click(function(){
        var id=$(this).attr("id").replace("menu","");
        if(document.getElementById("menu_child"+id).style.display == "block"){
            $("#menu_child"+id).slideUp(500);
        }else{
            var menu = findChilds("0");
            $.each(menu,function(index,menu){
                if(menu.id == id){
                }else{
                    $("#menu_child"+menu.id).slideUp(500);
                    $("#menu"+menu.id).removeClass("clickli");
                }
            });
            var menu = findChilds(id);
            if(menu != ""){
                var ChildHtml = "";
                $.each(menu,function(index,menu){
                    ChildHtml += "<div class='menu_list2'>";
                    ChildHtml += "<div class='menu_li2' id='menu"+menu.id+"' ><div class='menu_icon2'></div>";
                    ChildHtml +="<input type='hidden' class='menu_urls' value='"+menu.urls+"' ><div class='menu_name2'>"+menu.name+"</div></div>";
                    ChildHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;' ></div>";
                    ChildHtml +="</div>";
                });
                $("#menu_child"+id).html(ChildHtml);
                $("#menu_child"+id).slideDown(500);
                $("#menu"+id).addClass("clickli");
                ChildClick();
            }
        }
    });
    
}

function ChildClick(){
    $('.menu_li2').click(function(){
        var id=$(this).attr("id").replace("menu","");
        if(document.getElementById("menu_child"+id).style.display == "block"){
            $("#menu_child"+id).slideUp(500);
            $("#menu"+id).css("background-color","#F1F7FD");
        }else{
            var menu = findChilds(id);
            if(menu != ""){
                var ChildHtml = "";
                $.each(menu,function(index,menu){
                    ChildHtml += "<div class='menu_list2'>";
                    ChildHtml += "<div class='menu_li3' id='menu"+menu.id+"' onclick=javascript:goUrl('"+menu.name+"','"+menu.urls+"') ><div class='menu_icon3'></div>";
                    ChildHtml +="<div class='menu_name3'>"+menu.name+"</div></div>";
                    ChildHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;' ></div>";
                    ChildHtml +="</div>";
                });
                $("#menu"+id).css("background-color","#B8D6FB");
                $("#menu_child"+id).html(ChildHtml);
                $("#menu_child"+id).slideDown(500);
            }else{
                goUrl($("#menu"+id+" .menu_name2").html(),$("#menu"+id+" .menu_urls").val());
            }
        }
    });
}

function sendmsg()
{
   JS_EX.sendmsg('liushaoquan','刘绍全'); 
}

function goUrl(name,surls){
 //alert(JS_DELPHI.openurl(surls));   
 JS_EX.openurl(name,surls);
 //JS_EX.sendmsg('liushaoquan','刘绍全');
//window.open(urls);
}