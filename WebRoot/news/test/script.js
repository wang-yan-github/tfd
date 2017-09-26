  var jq=jQuery.noConflict();
jq(document).ready(function () {
	/****选项卡****/
	jq('.tab_title li').click(function(){
	jq(this).addClass("on").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("on")；siblings()意思就是当前对象的同级元素，removeClass就是删除；
	jq(".floor_content > div").hide().eq(jq('.tab_title li').index(this)).show();
	});

	//首页选项卡
	jq('#index_tab li').mouseover(function(){
		jq(this).addClass("on").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("on")；siblings()意思就是当前对象的同级元素，removeClass就是删除；
		jq(".floor_content > div").hide().eq(jq('#index_tab li').index(this)).show();
		Resize();
	});
	
	
	jq('.lai_tab li').click(function(){
		jq(this).addClass("lai_on").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("on")；siblings()意思就是当前对象的同级元素，removeClass就是删除；
		jq(".ygtd_list0 > div").hide().eq(jq('.lai_tab li').index(this)).show();
		
	});

	//首页新员工介绍选项卡
	jq('#index_tab_title li').click(function(){
		jq(this).addClass("liu_index_paging_on").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("on")；siblings()意思就是当前对象的同级元素，removeClass就是删除；
		jq("#index_content > div").hide().eq(jq('#index_tab_title li').index(this)).show();
	});


	/****二级选项卡****/

	jq('.liu_tab li').click(function(){
		jq(this).addClass("liu_on").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("on")；siblings()意思就是当前对象的同级元素，removeClass就是删除；
		jq(".liu_floor_content > div").hide().eq(jq('.liu_tab li').index(this)).show();
	});
	
	
	/*品牌选项卡小标签*/
	jq('.liu_standard_tab_title li').bind('click', function () {

		jq('.liu_standard_tab_title li span').each(function () {
			if(jq(this).find('span').css('display')!='none'){
				jq(this).css({'display':'none'});
			}
		})
		if(jq(this).find('span').css('display')!='block'){
			jq(this).find('span').css({'display':'block'});

		}
	})

});


//模拟下拉框
function selectTog(obj,cascade){
	jq(obj).siblings("ul").toggle();
	var input = jq(obj).parent().find("input");
	jq(obj).siblings("ul").children("li").bind("click",function(){
		var jqthis = jq(this);
		input.attr("value",jqthis.text());
		jqthis.parent().find(".selected").removeClass("selected");
		jqthis.addClass("selected");
		jqthis.parent().hide();
	})
	if(input.val() == "") jq(obj).siblings("ul").children("li.selected").removeClass("selected");
}

