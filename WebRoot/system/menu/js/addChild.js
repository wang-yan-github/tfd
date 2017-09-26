var zNodes=null;
var sysMenu = null;
var sysMenuLeave=null;
var sysMenuLeaveName=null;
$(function() {
	$("#sysMenuLeave").val(sysMenuLeave);
	$("#sysMenuLeaveName").val(sysMenuLeaveName);
	
	var setting = {
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : function(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("menuTree"), nodes = zTree
						.getSelectedNodes(), v = "";
				vid = "";
				nodes.sort(function compare(a, b) {
					return a.id - b.id;
				});
				for (var i = 0, l = nodes.length; i < l; i++) {
					v += nodes[i].name + ",";
					vid += nodes[i].id + ",";
				}
				if (v.length > 0)
					v = v.substring(0, v.length - 1);
				var nameem = $("#sysMenuLeaveName");
				nameem.val(v);
				if (vid.length > 0)
					vid = vid.substring(0, vid.length - 1);
				var idem = $("#sysMenuLeave");
				idem.val(vid);
			}
		}
	};
	

	$.ajax({
		url : contextPath+ "/tfd/system/menu/act/SysMenuAct/getAllMenuInfoAct.act",
		dataType : "json",
		async : false,
		error : function(e) {
		},
		success : function(data) {
			zNodes = data;
		}
	});
	
	$('#menu-form').bootstrapValidator({
		message : '这不是一个有效的值',
		container : 'tooltip',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			sysMenuName : {
				validators : {
					container : 'popover',
					notEmpty : {
						message : '菜单名称不能为空！'
					}
				}
			},
			sysMenuCode : {
				container : 'popover',
				validators : {
					notEmpty : {
						message : '菜单编码不能为空！'
					}
				}
			},
			sysMenuPic : {
				container : 'popover',
				validators : {
					notEmpty : {
						message : '菜单图片不能为空！'
					}
				}
			}
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		$.ajax({
			url : contextPath+ "/tfd/system/menu/act/SysMenuAct/addMenuAct.act",
			data : $(e.target).serialize(),
			type : 'POST',
			dataType : "text",
			async : false,
			error : function(e) {
			},
			success : function(data) {
				if (data == "OK") {
					alert("添加成功！");
				} else {
					alert("添加失败！");
				}
			}
		});
		parent.location.reload();
	});

	$.fn.zTree.init($("#menuTree"), setting, zNodes);
	
	
	$("#add-child").on("click",function(){
		parent["edit"].location = contextPath
			+ "/system/menu/addChild.jsp?sysMenuId=" + sysMenuId
			+ "&sysMenuName=" + encodeURIComponent(sysMenu.sysMenuName);
	});
	$("#delete").on("click",function(){
		$.ajax({
			url : contextPath+ "/tfd/system/menu/act/SysMenuAct/delMenuByMenuIdAct.act",
			data : {
				sysMenuId : sysMenuId
			},
			dataType : "text",
			async : false,
			error : function(e) {
			},
			success : function(data) {
				if (data == "OK") {
					alert("删除菜单成功！");
				} else if (data == "ERR") {
					alert("该菜单下存在子菜单，不允许删除！");
				} else {
					alert("删除菜单失败！");
				}
			}
		});
	});
	$("#back").on("click",function(){
		history.back();
	});
	
	$("#sysMenuLeaveName").on("click",function(e){
		e.stopPropagation();
		$("#menuContent").css({
			"width":$(this).outerWidth()+"px",
			"top":$(this).offset().top+$(this).outerHeight()+2+"px",
			"left":$(this).offset().left+"px"
		}).slideDown(200);
	});

	$(document).on("click",function(){
		$("#menuContent").hide();
	});

	$("#menuContent").on("click",function(e){
		e.stopPropagation();
	});
});


