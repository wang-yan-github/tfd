
/**
 * q1:popover消除动画，外层容器加上动画 √
 * q2:target后追加div是否会影响target页面的布局？ ok√
 * q3:选至最后一级自动隐藏 √
 * q4:层级选中混乱 √
 * 
 * @author fzd
 * 
 */
(function($){
	var REQUEST_URL_DISTRICT_SEARCH=contextPath+"/com/component/district/act/DistrictAct/search.act";
	var SKIN_DEFAULT="popover";
	var registDistrict=new Object();
	
	
	/**
	 * @param setting
	 * {
	 * 	skin:popover|default
	 *  //loadedCallback:function 
	 * }
	 */
	$.fn.district=function(setting){
		var currDate=new Date().getTime();
		setting=setting?setting:new Object();
		setting.currDate=currDate+"";
		
		registDistrict[setting.currDate+""]=null;
		
		var check=settingCheck(setting);
		if (!check) {
			debug("请检查配置！");
			return this;
		}
		
		settingReset(setting);
		
		var $target=this;
		
		switch(setting.skin){
			case SKIN_DEFAULT:
				districtPopover(setting,$target);
			break;
			default:
				districtPopover(setting,$target);
		}
		
		return this;
	}
	
	
	
	
	function settingCheck(setting){
		return  true;
	}
	function settingReset(setting){
		setting.skin=setting.skin?setting.skin:SKIN_DEFAULT;
		return setting;
	}
	function debug(text){
		if (window.console&&window.console.log) {
			window.console.log(text);
		}
	}
	
	
	
	
	
	function districtInit(setting,$target,$district,initFunc){
		search("0",function(district){
			var districtHtml=toDistrictHtml("0",district);
			$district.html(districtHtml);
			if (initFunc) {
				initFunc();
			}
			
			registDistrict[setting.currDate]=new Object();
		});
		
		$district.on("click",".district-name.district-name-uncheck",function(){
			var id=$(this).attr("value");
			var parentid=$(this).attr("parentid");
			var $this=$(this);
			
			if($district.find(".district-list[value='"+id+"']").length>0){
				districtChecked($target,$district,parentid,$this);
				
				$district.find(".district-list[value='"+id+"']").show();
			}else{
				search(id, function(district){
					districtChecked($target,$district,parentid,$this);
					
					var districtHtml=toDistrictHtml(id,district);
					
					$district.find(".district-children[value='"+parentid+"']").append(districtHtml);
					if (districtHtml=="") {
						districtHide(setting);
					}
				});
			}
			
			
		});
	}
	function districtChecked($target,$district,parentid,$this){
		$district.find(".district-children[value='"+parentid+"'] .district-list")
		.hide()
		.find(".district-name")
		.removeClass("district-name-checked")
		.addClass("district-name-uncheck");
		
		
		
		$district.find(".district-list-text[value='"+parentid+"'] .district-name")
		.removeClass("district-name-checked")
		.addClass("district-name-uncheck");
		$this.removeClass("district-name-uncheck").addClass("district-name-checked");
		
		var checkedDistrict=new Array();
		$district.find(".district-name-checked").each(function(){
			checkedDistrict.push($(this).text());
		});
	
		var regex=/^(input)|(textarea)$/;
		if(regex.test($target[0].nodeName.toLowerCase())){
			$target.val(checkedDistrict.join(""));
		}else{
			$target.html(checkedDistrict.join(""));
		}
	}
	
	
	
	
	
	function search(parentid,func){
		$.ajax({
			url:REQUEST_URL_DISTRICT_SEARCH,
			data:{searchField:"id,name,suffix",parentid:parentid},
			type:"POST",
			async:true,
			dataType:"json",
			error:function(){
				
			},
			success:function(district){
				func(district);
			}
		});
	}
	
	function toDistrictHtml(parentid,district){
		if (district==null||district.length==0) {
			return "";
		}
		var suffixObj=new Object();
		var suffixArray=new Array();
		var temp="";
		temp+="<table>";
		var td=5;
		var tr=district.length%td>0?parseInt(district.length/td)+1:parseInt(district.length/td);
		var index=0;
		for (var i = 0; i < tr; i++) {
			temp+="<tr>";
			for (var j = 0; j < td; j++) {
				var text="";
				
				if (index<district.length) {
					var id=district[index].id;
					var name=district[index].name;
					var suffix=district[index].suffix;
					var children=district[index].children;
					
					if (suffix!=null&&suffix!="") {
						if (!(suffix in suffixObj)) {
							suffixObj[suffix]=null;
							suffixArray.push(suffix);
						}
					}
					text="	<div class='district-name district-name-uncheck' value='"+id+"' parentid='"+parentid+"'>"+name+suffix+"</div>";
				}
				
				
				temp+="<td>";
				temp+=text;
				temp+="</td>";
				
				index++;
			}
			temp+="</tr>";
		}
		temp+="</table>";
		
		var districtHtml="<div class='district-list' value='"+parentid+"'>";
		districtHtml+="		<div class='district-level'>"+suffixArray.join("/")+"</div>";
		districtHtml+="		<div class='district-list-text' value='"+parentid+"'>";
		districtHtml+=			temp;
		districtHtml+="		</div>";
		districtHtml+="		<div class='district-children' value='"+parentid+"'></div>";
		districtHtml+="</div>";
		return districtHtml;
	}
	
	
	
	
	
	function districtPopover(setting,$target){
		var districtLoaded=false;
		
		$("body").append("<div class='district-popover' id='district-popover-"+setting.currDate+"'></div>");
		$target
		.popover({
			html:true,
			content:"<div class='district' id='district-container-"+setting.currDate+"'></div>",
			placement:"bottom",
			trigger:"manual",
			container:"#district-popover-"+setting.currDate,
			animation:false
		})
		.on("show.bs.popover",function(){
			$("#district-popover-"+setting.currDate).animate({opacity:"0"},0);
		})
		.on("shown.bs.popover",function(){
			if (!districtLoaded) {
				districtInit(
					setting,
					$target,
					$("#district-container-"+setting.currDate),
					function(){
						districtShow(setting);
					}
				);
				districtLoaded=true;
			}
		});
		
		$target.on("click.district",function(){
			if (!districtLoaded) {
				$target.popover("show");
			}else{
				districtShow(setting);
			}
		});
		
		$(document).on("click.district.hide"+setting.currDate,function(){
			districtHide(setting);
		});
		$(document).on("click.district.hide"+setting.currDate,"#district-popover-"+setting.currDate,function(e){
			 e.stopPropagation();
		});
		$target.on("click.district.hide"+setting.currDate,function(e){
			 e.stopPropagation();
		});
	}
	function districtHide(setting){
		$("#district-popover-"+setting.currDate).animate({opacity:"0"},200,function(){
			if (registDistrict[setting.currDate]!=null) {
				$(this).hide();
			}
		});
	}
	function districtShow(setting){
		for(var r in registDistrict){
			if (r!=setting.currDate) {
				if (registDistrict[r]!=null) {
					$("#district-popover-"+r).hide();
				}
			}
		}
		$("#district-popover-"+setting.currDate).show().animate({opacity:"1"},200);
	}
})(jQuery);