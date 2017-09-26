/**
 * 系统前端布局框架
 */
function SysFrame(){
	/**
	 * 系统页签
	 * @param option:String(可选值：exists|select|add|update|close)
	 * option:exists,系统页签是否存在判断
	 * 		  setting:String(要判断的页签名称)
	 * option:select,让系统页签处于展示状态
	 * 		  setting:String(需展示的页签名称)
	 * option:add,向系统中添加页签
	 * 		  setting:Object
	 * 				  {
	 * 					title:String,页签名称
	 * 					url:String,页签的打开地址
	 * 					content:页签所衔接的内容
	 * 				  }
	 * option:update,以更新的方式添加系統页签，始终保持需打开的页签的内容或地址是最新的
	 * 		  setting:Object
	 * 				  {
	 * 					title:String,页签名称
	 * 					url:String,页签的打开地址
	 * 					content:页签所衔接的内容
	 * 				  }
	 * option:close,关闭系统页签
	 * 		  setting:String(要关闭的页签名称)
	 */
	this.tabs=function(option,setting){
	     if($TabsBar.length==0){
	         window.open(setting.url);
	         return false;
	     } 
		if(!option){
			return false;
		}
		switch (option) {
		case "getSelected":
			return tabsgetSelected(setting);
			break;	
		case "exists":
			return tabsExist(setting);
			break;
		case "select":
			tabsSelect(setting); 
			break;
		case "add":
			tabsAdd(setting);
			break;
		case "update":
			tabsUpdate(setting);
			break;
		case "close":
			tabsClose(setting);
			break;
		case "updatetabs":
			updatetabs(setting);
			break;	
		}
	};

	var $TabsBar=top.$("#tabs-bar");
	function tabsgetSelected(title){
		return $TabsBar.tabs('getSelected');
	}
	function updatetabs(setting){
		if(tabsExist(setting.title)){
			tabsClose(setting.title);
		}
		content="<div style='width:100%; height:100%;' tabname='"+setting.title+"'>" +
					"	<iframe  width='100%' height='100%' frameborder='0' scrolling='auto' src='"+setting.url+"' tabname='"+setting.title+"'></iframe>" +
					"</div>";
		$TabsBar.tabs('update', {
		tab: setting.tab,
		options: {
			title: setting.title,
			content: content,
			closable: true
		}
	});
	}
	function tabsExist(title){
		return $TabsBar.tabs('exists', title);
	}
	function tabsSelect(title){
		$TabsBar.tabs('select', title);
	}
	function tabsAdd(setting){
		var title=setting.title;
		var content=null;
		if (setting.url) {
			content="<div style='width:100%; height:100%;' tabname='"+title+"'>" +
					"	<iframe  width='100%' height='100%' frameborder='0' scrolling='auto' src='"+setting.url+"' tabname='"+title+"'></iframe>" +
					"</div>";
		}else{
			content="<div style='width:100%; height:100%;' tabname='"+title+"'>" +
						setting.content+
					"</div>";
		}
		$TabsBar.tabs('add',{
			title: title,
			content: content,
			closable: true
		});
	}
	function tabsUpdate(setting){
		var title=setting.title;
		if (tabsExist(title)) {
			tabsSelect(title);
			if (setting.url) {
				top.$("iframe[tabname='"+title+"']")[0].contentWindow.location=setting.url;
			}else if(setting.content){
				top.$("div[tabname='"+title+"']").html(setting.content);
			}else{
				top.$("iframe[tabname='"+title+"']")[0].contentWindow.location.reload();
			}
		}else{
			tabsAdd(setting);
		}
	}
	function tabsClose(title){
		return $TabsBar.tabs('close', title);
	}
}