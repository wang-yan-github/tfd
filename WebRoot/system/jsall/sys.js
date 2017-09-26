var suppotPictureFileType = [ "bmp", "jpg", "gif", "png" ];
var suppotExcleFileType = [ "xls", "xlsx" ];
var suppotWordFileType = [ "doc", "docx" ];
var suppotCsvFileType = [ "csv" ];
function checkSuppotPictureFile(b) {
	var d = $("#" + b)[0];
	var e = d.value;
	if (!d || e == "") {
		alert("请选择文件!");
		d.focus();
		return false
	}
	if (e.lastIndexOf(".") != -1) {
		var a = (e.substring(e.lastIndexOf(".") + 1, e.length)).toLowerCase();
		for (var c = 0; c < suppotPictureFileType.length; c++) {
			if (suppotPictureFileType[c] == a) {
				return true
			} else {
				continue
			}
		}
		alert("文件只支持" + suppotPictureFileType.toString());
		return false
	} else {
		alert("文件只支持" + suppotPictureFileType.toString())
	}
}
function checkSuppotExcleFile(b) {
	var d = $("#" + b)[0];
	var e = d.value;
	if (!d || e == "") {
		alert("请选择文件!");
		d.focus();
		return false
	}
	if (e.lastIndexOf(".") != -1) {
		var a = (e.substring(e.lastIndexOf(".") + 1, e.length)).toLowerCase();
		for (var c = 0; c < suppotExcleFileType.length; c++) {
			if (suppotExcleFileType[c] == a) {
				return true
			} else {
				continue
			}
		}
		alert("文件只支持" + suppotExcleFileType.toString());
		return false
	} else {
		alert("文件只支持" + suppotExcleFileType.toString())
	}
}
function checkSuppotWordFile(b) {
	var d = $("#" + b)[0];
	var e = d.value;
	if (!d || e == "") {
		alert("请选择文件!");
		d.focus();
		return false
	}
	if (e.lastIndexOf(".") != -1) {
		var a = (e.substring(e.lastIndexOf(".") + 1, e.length)).toLowerCase();
		for (var c = 0; c < suppotWordFileType.length; c++) {
			if (suppotWordFileType[c] == a) {
				return true
			} else {
				continue
			}
		}
		alert("文件只支持" + suppotWordFileType.toString());
		return false
	} else {
		alert("文件只支持" + suppotWordFileType.toString())
	}
}
function checkSuppotCsvFile(b) {
	var d = $("#" + b)[0];
	var e = d.value;
	if (!d || e == "") {
		alert("请选择文件!");
		d.focus();
		return false
	}
	if (e.lastIndexOf(".") != -1) {
		var a = (e.substring(e.lastIndexOf(".") + 1, e.length)).toLowerCase();
		for (var c = 0; c < suppotCsvFileType.length; c++) {
			if (suppotCsvFileType[c] == a) {
				return true
			} else {
				continue
			}
		}
		alert("文件只支持" + suppotCsvFileType.toString());
		return false
	} else {
		alert("文件只支持" + suppotCsvFileType.toString())
	}
}
function dialog(f, e, b) {
	var c = (screen.width - e) / 2;
	var a = (screen.height - b) / 2;
	var d = null;
	d = "status:no;directories:no;";
	d += "dialogWidth:" + e + "px;";
	d += "dialogHeight:" + b + "px;";
	d += "dialogLeft:" + c + "px;";
	d += "dialogTop:" + a + "px;";
	return window.showModalDialog(f, self, d)
}
function dialogChangesize(f, e, b) {
	var c = (screen.width - e) / 2;
	var a = (screen.height - b) / 2;
	var d = null;
	d = "status:no;directories:yes;scroll:yes;Resizable=yes;";
	d += "dialogWidth:" + e + "px;";
	d += "dialogHeight:" + b + "px;";
	d += "dialogLeft:" + c + "px;";
	d += "dialogTop:" + a + "px;";
	return window.showModalDialog(f, self, d)
}
function openWindow(b, g, a, i) {
	var d = "menubar=0,toolbar=0,status=0";
	d += ",scrollbars=1,resizable=1";
	var e = window.screen.availWidth;
	var h = window.screen.availHeight;
	if ($.browser.webkit) {
		h = parseInt(h) - 60
	}
	if (!a) {
		a = 800
	}
	if (!i) {
		i = 600
	}
	var c = (e - a) / 2;
	var f = (h - 100 - i) / 2;
	d += ",width=" + a;
	d += ",height=" + i;
	d += ",top=" + f;
	d += ",left=" + c;
	d += ",location=no";
	return window.open(b, g, d)
}
function jBoxWindow(b, e, d) {
	if (!d) {
		d = {}
	}
	var c = d.width || $(top.document).width() - 100;
	var a = d.height || $(top.document).height() - 100;
	top.$.jBox.open("iframe:" + b, e, c, a, {
		top : "5%",
		submit : function(i, k, l) {
			var j = $(k).find("iframe:first");
			var g = j[0].contentWindow;
			if (d.submit) {
				return d.submit(i, k, l, g)
			}
			return true
		}
	})
}
function bsWindow(c, k, a) {
	if (!a) {
		a = {}
	}
	var d = a.width || $(document).width() - 100;
	var l = a.height || $(document).height() - 250;
	d += "";
	l += "";
	if (d.indexOf("%") != -1) {
		d = d.replace("%", "");
		d = $(document).width() * (parseInt(d) * 0.01)
	}
	if (l.indexOf("%") != -1) {
		l = l.replace("%", "");
		l = parseInt(document.documentElement.clientHeight)
				* (parseInt(l) * 0.01)
	}
	if (!window.BSWINDOW) {
		window.BSWINDOW = $('<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=""></div>');
		window.BSWINDOW_BS1 = $('<div class="modal-dialog"></div>');
		window.BSWINDOW_BS2 = $('<div class="modal-content"></div>');
		window.BSWINDOW_BS3 = $('<div class="modal-header"></div>');
		window.BSWINDOW_BS4 = $('<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>');
		window.BSWINDOW_BS5 = $('<h4 class="modal-title" id="myModalLabel"></h4>');
		window.BSWINDOW_BS6 = $('<div class="modal-body" style="padding-top:0px;padding-bottom:0px;padding-top:3px;"></div>');
		window.BSWINDOW_BS7 = $('<iframe frameborder=0 style="width:100%;height:100%"></iframe>');
		window.BSWINDOW_BS8 = $('<div class="modal-footer" style="padding-top:3px;"></div>');
		window.BSWINDOW.append(window.BSWINDOW_BS1);
		window.BSWINDOW_BS1.append(window.BSWINDOW_BS2);
		window.BSWINDOW_BS2.append(window.BSWINDOW_BS3);
		window.BSWINDOW_BS3.append(window.BSWINDOW_BS4).append(
				window.BSWINDOW_BS5);
		window.BSWINDOW_BS2.append(window.BSWINDOW_BS6);
		window.BSWINDOW_BS6.append(window.BSWINDOW_BS7);
		window.BSWINDOW_BS2.append(window.BSWINDOW_BS8);
		$("body").append(window.BSWINDOW)
	}
	window.BSWINDOW_BS5.html(k);
	window.BSWINDOW_BS1.css({
		width : d
	});
	window.BSWINDOW_BS6.css({
		height : l
	});
	window.BSWINDOW_BS7.attr("src", c);
	window.BSWINDOW_BS8.html("");
	if (a.buttons) {
		for (var g = 0; g < a.buttons.length; g++) {
			var h = a.buttons[g];
			var f = h.classStyle || "btn btn-default";
			var b = h.name;
			var e = $('<button class="' + f + '">' + b + "</button>");
			e.click(function() {
				if (a.submit) {
					if (a.submit($(this).html(), window.BSWINDOW_BS7)) {
						window.BSWINDOW.modal("hide")
					}
				}
			});
			e.appendTo(window.BSWINDOW_BS8)
		}
	} else {
		var m = $(
				'<button class="btn btn-default"  data-dismiss="modal">关闭</button>')
				.appendTo(window.BSWINDOW_BS8);
		var j = $('<button class="btn btn-primary">确定</button>').click(
				function() {
					if (a.submit) {
						if (a.submit("ok", window.BSWINDOW_BS7)) {
							window.BSWINDOW.modal("hide")
						}
					} else {
						window.BSWINDOW.modal("hide")
					}
				}).appendTo(window.BSWINDOW_BS8)
	}
	window.BSWINDOW.modal("show")
}
function openFullWindow(d, g) {
	var c = "menubar=0,toolbar=0,status=0";
	c += ",scrollbars=1,resizable=1";
	var f = 0;
	var e = 0;
	var b = window.screen.availWidth;
	var a = window.screen.availHeight;
	if ($.browser.webkit) {
		a = parseInt(a) - 60
	}
	c += ",width=" + b;
	c += ",height=" + a;
	c += ",top=" + e;
	c += ",left=" + f;
	c += ",location=no";
	return window.open(d, g, c)
}
function setOptions(c, a, d) {
	for (var b = 0; b < a.length; b++) {
		var e = document.createElement("option");
		e.value = a[b].value;
		if (a[b].value == d) {
			e.selected = true
		}
		e.appendChild(document.createTextNode(a[b].text));
		c.appendChild(e)
	}
}
function isInteger(b) {
	if (b == null) {
		return false
	}
	var a = /^[-+]?[1-9][0-9]*$/;
	return a.test(b)
}
function isIntegeOrZero(b) {
	if (b == null) {
		return false
	}
	var a = /^\d+$/;
	return a.test(b)
}
function isPositivInteger(b) {
	if (b == null) {
		return false
	}
	var a = /^[1-9][0-9]*$/;
	return a.test(b)
}
function isValidDate(b, c, a) {
	return isValidDateStr(b + "-" + c + "-" + a)
}
function isValidDateStr(c) {
	if (!c) {
		return
	}
	var a = c.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (a == null) {
		return false
	}
	if (parseInt(a[1]) > 9999 || parseInt(a[1]) < 1753) {
		return false
	}
	var b = new Date(a[1], a[3] - 1, a[4]);
	return (b.getFullYear() == a[1] && (b.getMonth() + 1) == a[3] && b
			.getDate() == a[4])
}
function isValidTimeStr(b) {
	if (!b) {
		return
	}
	var a = b.match(/^(\d{1,2}):(\d{1,2}):(\d{1,2})$/);
	if (a == null) {
		return false
	}
	if (parseInt(a[1]) > 59 || parseInt(a[1]) < 0) {
		return false
	}
	if (parseInt(a[2]) > 59 || parseInt(a[2]) < 0) {
		return false
	}
	if (parseInt(a[3]) > 59 || parseInt(a[3]) < 0) {
		return false
	}
	return true
}
function isValidateTimeStr(c) {
	if (!c) {
		return
	}
	var a = c
			.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})\s(\d{1,2}):(\d{1,2}):(\d{1,2})$/);
	if (a == null) {
		return false
	}
	if (parseInt(a[1]) > 9999 || parseInt(a[1]) < 1753) {
		return false
	}
	var b = new Date(a[1], a[3] - 1, a[4]);
	if (!(b.getFullYear() == a[1] && (b.getMonth() + 1) == a[3] && b.getDate() == a[4])) {
		return false
	}
	if (parseInt(a[5]) > 59 || parseInt(a[5]) < 0) {
		return false
	}
	if (parseInt(a[6]) > 59 || parseInt(a[6]) < 0) {
		return false
	}
	if (parseInt(a[7]) > 59 || parseInt(a[7]) < 0) {
		return false
	}
	return true
}
function isValidateFilePath(b) {
	var a = /["\/\\:*?"<>|]/;
	return b.match(a)
}
function strToDate(dateStr){
    var data = dateStr;  
    var reCat = /(\d{1,4})/gm;   
    var t = data.match(reCat);
    t[1] = t[1] - 1;
    eval('var d = new Date('+t.join(',')+');');
    return d;
}
Date.prototype.pattern = function(a) {
	var d = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		"H+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		S : this.getMilliseconds()
	};
	var c = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(a)) {
		a = a.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length))
	}
	if (/(E+)/.test(a)) {
		a = a
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ c[this.getDay() + ""])
	}
	for ( var b in d) {
		if (new RegExp("(" + b + ")").test(a)) {
			a = a.replace(RegExp.$1, (RegExp.$1.length == 1) ? (d[b])
					: (("00" + d[b]).substr(("" + d[b]).length)))
		}
	}
	return a
};
function getFormatDateStr(c, b) {
	var d = "";
	if (c) {
		var a = new Date(parseInt(c, 10));
		d = new Date(a).pattern(b)
	} else {
		d = new Date().pattern(b)
	}
	return d
}
function getTimeMiliBirdDesc(f) {
	var e = Math.floor(f / (60 * 60 * 24));
	var f = f - (e * 24 * 60 * 60);
	var a = Math.floor(f / (60 * 60));
	var f = f - (a * 60 * 60);
	var b = Math.floor(f / 60);
	var f = f - (b * 60);
	var c = Math.floor(f);
	var d = "";
	if (e != 0) {
		d += e + "天"
	}
	if (a != 0) {
		d += a + "小时"
	}
	if (b != 0) {
		d += b + "分钟"
	}
	if (c != 0) {
		d += c + "秒"
	}
	return d
}
function getTimeMilisecondDesc(e) {
	if (!e || e == "") {
		return ""
	}
	var d = Math.floor(e / (60 * 60 * 24));
	var e = e - (d * 24 * 60 * 60);
	var a = Math.floor(e / (60 * 60));
	var e = e - (a * 60 * 60);
	var b = Math.floor(e / 60);
	var e = e - (b * 60);
	var c = "";
	if (d != 0) {
		c += d + "天"
	}
	if (a != 0) {
		c += a + "小时"
	}
	if (b != 0) {
		c += b + "分钟"
	}
	return c
}
function doFramesetScroll(h, d, c, b) {
	try {
		var a = document.getElementById(d);
		var g = document.getElementById(h);
		g.contentWindow.document.body.style.height = "auto";
		g.contentWindow.document.attachEvent
				&& g.contentWindow.document
						.attachEvent(
								"onmousewheel",
								function(j) {
									var i = (a.contentWindow.document.documentElement || a.contentWindow.document.body).scrollTop;
									if (j.wheelDelta >= 120) {
										a.contentWindow.scrollTo(0, i - 50)
									} else {
										a.contentWindow.scrollTo(0, i + 50)
									}
								});
		a.onload = a.onreadystatechange = function() {
			if (!this.readyState || this.readyState == "complete") {
				g.contentWindow.scrollTo(0, 0);
				a.contentWindow.document.body.style.minHeight = parseInt(c - 160)
						+ "px";
				if (a.contentWindow.attachEvent) {
					a.contentWindow
							.attachEvent(
									"onscroll",
									function(i) {
										g.contentWindow
												.scrollTo(
														0,
														a.contentWindow.document.documentElement.scrollTop);
										return false
									})
				} else {
					if (a.contentWindow.addEventListener) {
						a.contentWindow
								.addEventListener(
										"scroll",
										function(i) {
											g.contentWindow
													.scrollTo(
															0,
															a.contentWindow.document.documentElement.scrollTop);
											return false
										}, false)
					}
				}
			}
		};
		a.src = b
	} catch (f) {
	} finally {
	}
}
function getCookie(b) {
	var a = document.cookie.match(new RegExp("(^| )" + b + "=([^;]*)(;|$)"));
	if (a != null) {
		return unescape(a[2])
	} else {
		return null
	}
	return null
}
function setCookie(a, c, b) {
	var d = new Date();
	d.setTime(d.getTime() + b * 24 * 60 * 60 * 1000);
	document.cookie = a + "=" + escape(c) + ";path=/;expires="
			+ d.toGMTString()
}
function messageMsg(e, d, c, b) {
	if (!b) {
		b = 280
	}
	var a = '<table class="MessageBox" align="center"  style="width:' + b
			+ 'px">';
	if (!c) {
		c = "info"
	}
	a += ' <tr>  <td class="msg ' + c + '">';
	a += '<div class="" style="">' + e + "</div> </td> </tr> </table>";
	$("#" + d).html(a)
}
function setFormClearValue(a) {
	$(a).find("input[name][type=text]").each(function(b, c) {
		$(c).val("")
	});
	$(a).find("input[name][type=hidden]").each(function(b, c) {
		$(c).val("")
	});
	$(a).find("textarea[name]").each(function(b, c) {
		$(c).val("")
	});
	$(a).find("select[name]").each(function(b, c) {
		$(c).val("")
	});
	$(a).find("input[name][type=checkbox]").each(function(b, c) {
		if ($(c).attr("checked")) {
		} else {
		}
	});
	$(a).find("input[name][type=radio][checked]").each(function(b, c) {
	});
	$(a).find("input[name][type=password]").each(function(b, c) {
		$(c).val()
	})
}
$.extend({
	includePath : "",
	include : function(d) {
		var a = typeof d == "string" ? [ d ] : d;
		for (var e = 0; e < a.length; e++) {
			var b = a[e].replace(/^\s|\s$/g, "");
			var g = b.split(".");
			var c = g[g.length - 1].toLowerCase();
			var j = c == "css";
			var k = j ? "link" : "script";
			var f = j ? " type='text/css' rel='stylesheet' "
					: " language='javascript' type='text/javascript' ";
			var h = (j ? "href" : "src") + "='" + $.includePath + b + "'";
			if ($(k + "[" + h + "]").length == 0) {
				document.write("<" + k + f + h + "></" + k + ">")
			}
		}
	}
});
var FormatModel = function(d, b) {
	for ( var a in b) {
		var c = new RegExp("{" + a + "}", "g");
		d = d.replace(c, b[a])
	}
	return d;
};
$.fn.group = function() {
	var a = $(this);
	a.children().each(function(b, c) {
		$(c).click(function() {
			a.children().each(function(d, e) {
				$(e).removeClass("active")
			});
			$(this).addClass("active")
		})
	})
};

/**
 * 大小人民币和数字互转工具
 */
function RMBUtil(){
	/**
	 * 中文中简写的汉字金额
	 */
	var RMB_NUMBERS = [ "一", "二", "三","四", "五", "六", "七", "八", "九", "两", "廿", "卅", "○" ];
	/**
	 * 中文中繁写的汉字金额
	 */
	var BIG_RMB_NUMBERS = [ "壹", "贰","叁", "肆", "伍", "陆", "柒", "捌", "玖", "俩", "廿", "卅", "零" ];
	/**
	 * 与汉字相应的转化的数字
	 */
	var TO_ARABIC_NUMBERS = [ 1, 2, 3, 4,5, 6, 7, 8, 9, 2, 2, 3, 0 ];

	/**
	 * 人民币单位关键词（即大写数字倍数） 简写 注意：一定要由大到小
	 */
	var RMB_UNIT = [ "亿", "万", "千", "百","十", "元", "角", "分", "厘" ];
	/**
	 * 人民币单位关键词 繁体写
	 */
	var BIG_RMB_UNIT = [ "億", "萬", "仟","佰", "拾", "圆", "角", "分", "厘" ];

	/**
	 * 与人民币单位关键词对应的基数
	 */
	var TO_CARDINAL_NUMBERS = [100000000, 10000,1000, 100, 10,1,0.1,0.01,0.001];
	
	
	/**
	 * 大写转化为小写的过程操作
	 * @param money 大写金额
	 */
	this.toDigital=function(money) {
		if (money == null||$.trim(money)=="") {
			return null;
		}
		money=money.replace(new RegExp("整","g"),"");
		
		var result=getDigitalNum(money);
		return new Number(result.toFixed(3));;
	}

	/**
	 * 辅助类，处理中文数字转换成阿拉伯数字，利用递归算法
	 * @param money 人民币大写
	 */
	function getDigitalNum(money) {
		var result = 0;
		if(new RegExp("["+RMB_UNIT.join("")+BIG_RMB_UNIT.join("")+"]").test(money)){
			for (var i = 0; i < RMB_UNIT.length; i++) {
				// 查找字符中的简、繁单位
				var index = money.lastIndexOf(RMB_UNIT[i]) > -1 ? money.lastIndexOf(RMB_UNIT[i]) : money.lastIndexOf(BIG_RMB_UNIT[i]);
				if (index>-1) {
					var pre_money = money.substring(0, index);
					// 截取当前单位后面的字符串 ，进行下一次迭代比较
					money = money.substring(index + 1); 
					result +=getDigitalNum(pre_money)*TO_CARDINAL_NUMBERS[i];
				}
			}
		}
		if (money=="") {
			return result;
		}
		
		// 如果不带单位 直接阿拉伯数字匹配替换
		for (var j = 0; j < RMB_NUMBERS.length; j++) {
			money = money.replace(new RegExp(RMB_NUMBERS[j],"g"),TO_ARABIC_NUMBERS[j])
			.replace(new RegExp(BIG_RMB_NUMBERS[j],"g"),TO_ARABIC_NUMBERS[j]);
		}
		result=new Number(money);
		return result;
	}
}