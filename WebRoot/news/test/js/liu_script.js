jq(document).ready(function(){
	
	
//    //table tr点击变颜色
//    jq('tr').bind('click', function () {
//			
//        jq('tr').each(function() {
//			
//            jq(this).css({'background-color':'#fff'});
//        });
//        jq(this).css({'background-color':'#d8f0fc'});
//        jq('.draft-title').css({'background-color':'#fff'});
//    });
    //checkbox勾选
    jq('input[name="check-box"]').wrap("<div class='check-box'><i></i></div>");
    jq.fn.toggleCheckbox = function () {
        this.attr('checked', !this.attr('checked'));
    }
    jq('.check-box').on('click', function () {
        jq(this).find(':checkbox').toggleCheckbox();
        jq(this).toggleClass('checkedBox');
    });



    //删除按钮根据checkbox变颜色
    jq('.liu_checkbox_del .liu_draft').on('click', function () {
        //获取父级
        var fuji=$($(this).parents(".liu_checkbox_del")[0]);
        if(fuji.find(".checkedBox").length>=1){
            fuji.find(".liu_select_del").addClass("liu_select_del_orange");
        }else{
            fuji.find(".liu_select_del").removeClass("liu_select_del_orange");
        }
        jq('.liu_checkbox_del .liu_select_del_orange').click(function(){
            jq(this).removeClass("liu_select_del_orange");
        });

    });



    //打钩删除
	jq('.delete-selected').bind('click', function () {
        jq('.gou').each(function () {
            if(jq(this).is(':checked')){
                jq(this).parent().parent().parent().parent().remove();
            }
        })
    })

    //时间段选择预订
    jq('.liu_reservation').click(function(){
        jq(this).find('img').attr("src","../css/images/liu_icon59.png");
        jq(this).attr({"disabled":"disabled"});
        var a = jq(this).val();
        jq('.liu_resource_delete').append('<div class="liu_resource_delete_box lf"><span>'+a+'</span><a href="javascirpt:void(0)" class="liu_delete"><img src="../css/images/liu_icon61.png" alt=""/></a></div>');
        var i = jq('#liu_beds').val();
        i++;
        jq('#liu_beds').val(i);
    });
    jq(".liu_delete").live("click",function() {
        var i = jq('#liu_beds').val();
        i--;
        jq('#liu_beds').val(i);
        var text = jq(this).prev().text();

        jq('.liu_reservation').each(function () {
            if(jq(this).val()==text){
                jq(this).find('img').attr("src","../css/images/liu_icon57.png");
                jq(this).removeAttr("disabled");
            }
        });
        jq(this).parent().remove();

    });
	
	

    //首页-待办事宜-收缩
    jq('.diamonds').click(function(){

        if(jq(this).parents('.kuang01').find('.list01').css('display')=='none'){
            jq(this).parents('.kuang01').find('.list01').fadeIn();
            jq(this).parents('.kuang01').find('b').fadeIn();
            jq(this).css({'transform':'rotate(0deg)'});
            Resize();
        }else{
            jq(this).parents('.kuang01').find('.list01').fadeOut();
            jq(this).parents('.kuang01').find('b').fadeOut();
            jq(this).css({'transform':'rotate(180deg)'});
            Resize();
        }

    });


    //首页-hr管理-移动上去换图标

   // $('.liu_attendance_home ul li').mouseover(function(){
   //     $(this).addClass("liu_attendance_home_select").siblings().removeClass();
   // });


    //资源管理_会议室管理_会议室预定_新增_本单位与会人员v3-----删除员工
    jq('.liu_personnel_choice_name ul li a').bind('click',function(){
        jq(this).parent().remove();
    });

    jq('.liu_personnel_choice_button input').click(function(){
        jq('.liu_personnel_choice_name ul li').remove();
    })

    //资源管理_会议室管理_会议室查询——悬浮框
    jq('.liu_resource_query table tr td span').mouseover(function(){
        jq(this).next('.liu_resource_query_box2').show();
    });
    jq('.liu_resource_query table tr td span').mouseout(function(){
        jq(this).next('.liu_resource_query_box2').hide();
    });

    //关闭悬浮框
    jq(".liu_cancel").bind('click', function () {
        parent.jq('.fancybox-close').click();
    });

    //话题列表
    jq('.club_index_box2 a').mouseover(function(){
        jq('.club_index_box2 a').each(function () {
            jq(this).removeClass("club_index_select");
        })
        jq(this).addClass("club_index_select");

    });
    jq('.club_index_box2 a').mouseout(function(){
        jq(this).removeClass("club_index_select");
    })

    //HR_个人信息_个人资料_员工个人资料

    var list=jq("#liu_sp_table .liu_sp_table_public");
    for(var i=1;i<=list.length;i++)
    {
        if(i%4==0&&i!=0){
            list.eq(i-1).css({'margin-right':'0'});
        }
    }

    var list=jq("#liu_sp_table2 .liu_sp_table_public");
    for(var i=1;i<=list.length;i++)
    {
        if(i%4==0&&i!=0){
            list.eq(i-1).css({'margin-right':'0'});
        }
    }

    //HR_人事管理_人事合同管理_table tr 删除
    jq(".liu_checkbox_del .liu_select_del").click(function(){
        jq(".liu_checkbox_del input[type='checkbox']").each(function () {
            if(jq(this).is(":checked")){
                jq(this).parent().parent().parent().parent().parent().remove();
            }
        });
    });
	
	//单选框
	jq(function() {
  jq('label').click(function(){
    var radioId = jq(this).attr('name');
    jq('label').removeAttr('class') && jq(this).attr('class', 'checked');
    jq('input[type="radio"]').removeAttr('checked') && jq('#' + radioId).attr('checked', 'checked');
  });
  
});

//时间切换


    //$("#draft_table input").click(function(){
    //    alertDiv("a");
    //if($('#draft_table input').is(':checked')){
    //    alertDiv("a");
    //    $('.liu_select_del').addClass("liu_select_del_orange");
    //}else{
    //    $('.liu_select_del').removeClass("liu_select_del_orange");
    //}
    //
    //});


    //$("#draft_table input[type='checkbox']").each(function () {
    //    if($(this).is(":checked")){
    //        alertDiv("a");
    //        $('.liu_select_del').addClass("liu_select_del_orange");
    //    } else{
    //        $('.liu_select_del').removeClass("liu_select_del_orange");
    //    }
    //});
});
function show_time(n){
	 for(i=1;i<3;i++){
		 var laib=document.getElementById("lai+i");
		 var lai1=document.getElementById("lai_showtime1");
		 var lai2=document.getElementById("lai_showtime2");
		 var lai3=document.getElementById("lai_showtime3");
		 var lai4=document.getElementById("lai_showtime4");
		 if(n==1){
			 lai1.style.display="block";
			 lai3.style.display="block";
			 lai2.style.display="none";
			 lai2.value="";
			 lai4.style.display="none";
			 lai2.value="";
		 }
		 else{
			 lai1.style.display="none";
			 lai1.value="";
			 lai3.style.display="none";
			 lai3.value="";
			 lai2.style.display="block";
			 lai4.style.display="block";
			 }
	 }
}


function mouseMoveOver(obj){
	   
   jq(obj).addClass("liu_attendance_home_select").siblings().removeClass();
   var imgs =jq(this).find("img");
   
   jq(imgs[0]).removeClass("liu_attendance_click2");//click2为显示
   jq(imgs[0]).addClass("liu_attendance_click");
   jq(imgs[1]).removeClass("liu_attendance_click");//click2为显示
   jq(imgs[1]).addClass("liu_attendance_click2");
	
};

function mouseMoveOut(obj){
	   
	   jq(obj).removeClass();
	   var imgs =jq(this).find("img");
	   
	   jq(imgs[0]).removeClass("liu_attendance_click");//click2为显示
	   jq(imgs[0]).addClass("liu_attendance_click2");
	   jq(imgs[0]).removeClass("liu_attendance_click2");//click2为显示
	   jq(imgs[0]).addClass("liu_attendance_click");
	};
