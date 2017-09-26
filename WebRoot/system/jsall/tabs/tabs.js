(function(d) {
	d.addTab = function(n, l, o) {
		var q = d("#" + n);
		var p = d("#" + l);
		if (o instanceof Array) {
			for (var k = 0; k < o.length; k++) {
				b(q, p, o[k])
			}
		} else {
			if (o.active == undefined) {
				o.active = true
			}
			var m = i(q, o);
			if (m) {
				h(q);
				a(m);
				return
			}
			b(q, p, o)
		}
		j(q, p);
		e(q);
		g(q)
	};
	function b(t, s, l) {
		var w = l.title;
		var n = l.url;
		var r = l.active;
		var m = l.cache;
		var y = l.closable;
		var u = t[0].outerTabContainer;
		if (!u) {
			var q = d("<div style='float:left;' class='tee_tab_pointer_left'><i class=\"glyphicon glyphicon-chevron-left\"></i></div>");
			var x = d("<div style='float:right;' class='tee_tab_pointer_right'><i class=\"glyphicon glyphicon-chevron-right\"></i></div>");
			t[0].tab_left = q[0];
			t[0].tab_right = x[0];
			var k = d("<div style='overflow:hidden'></div>");
			t.append(q).append(x).append(k);
			t[0].tab_center = k[0];
			u = d("<div style='width:5000px;'></div>");
			t[0].outerTabContainer = u[0];
			k.append(u);
			q.click(function() {
				var A = t[0].innerContentWidth;
				var B = k.width();
				var z = k.scrollLeft() - B;
				z = z <= 0 ? 0 : z;
				k.animate({
					scrollLeft : z
				}, 500)
			});
			x.click(function() {
				var A = t[0].innerContentWidth;
				var B = k.width();
				var z = k.scrollLeft() + B;
				z = z >= B ? B + 20 : z;
				k.animate({
					scrollLeft : z
				}, 500)
			})
		}
		var o = d("<div class='tee_tab'>" + w + "</div>");
		var v = d("<i class='tee_close glyphicon glyphicon-remove'></i>")
				.hide();
		o.append(v);
		o[0].url = n;
		o[0].title = w;
		o[0].active = r;
		o[0].cache = m;
		o[0].closable = y;
		var p = d("<iframe style='height:100%;width:100%;dispaly:none;' frameborder=0></iframe>");
		o[0].tabContent = p[0];
		s.append(p);
		d(u).append(o);
		o.click(function() {
			h(t);
			a(o);
			if (y) {
				v.show()
			}
		});
		v.click(function() {
			h(t);
			var A = o.prev();
			if (A.length != 0) {
				a(A)
			} else {
				var z = o.next();
				if (z.length != 0) {
					a(z)
				}
			}
			o.remove();
			p.remove()
		});
		if (r) {
			d(d(o)[0].tabContent).attr("src", d(o)[0].url);
			h(t);
			a(o)
		}
	}
	function a(k) {
		d(d(k)[0].tabContent).show();
		if (!d(k)[0].active || !d(k)[0].cache) {
			d(d(k)[0].tabContent).attr("src", d(k)[0].url)
		}
		d(k).addClass("tee_tab_select");
		d(k)[0].active = true;
		if (d(k)[0].closable) {
			d(k).find(".tee_close:first").show()
		}
	}
	function h(k) {
		d(k[0].outerTabContainer).children().each(function(l, m) {
			d(m.tabContent).hide();
			d(m).removeClass("tee_tab_select")
		});
		d(k[0].outerTabContainer).find(".tee_close").each(function(l, m) {
			d(m).hide()
		})
	}
	function j(m, l) {
		var k;
		d(m[0].outerTabContainer).children().each(function(n, o) {
			if (o.active) {
				k = d(o)
			}
			d(o.tabContent).hide()
		});
		if (k) {
			d(k[0].tabContent).show()
		}
	}
	function e(k) {
		if (!k[0].listener) {
			setInterval(function() {
				c(k)
			}, 1000);
			k[0].listener = true
		}
		c(k)
	}
	function c(m) {
		var l = d(m[0].outerTabContainer);
		var n = 0;
		var o = d(m[0].tab_left);
		var k = d(m[0].tab_right);
		n = m[0].innerContentWidth;
		if (n > m.outerWidth()) {
			o.show();
			k.show()
		} else {
			o.hide();
			k.hide();
			d(m[0].tab_center).scrollLeft(0)
		}
	}
	function g(l) {
		var k = d(l[0].outerTabContainer);
		var m = 0;
		k.children().each(
				function(n, o) {
					m += f(d(o).outerWidth()) + f(d(o).css("marginLeft"))
							+ f(d(o).css("marginRight"))
				});
		l[0].innerContentWidth = m
	}
	function f(m) {
		try {
			var l = parseInt(m);
			return (l + 0) == l ? l : -1
		} catch (k) {
			return -1
		}
	}
	function i(m, l) {
		var k;
		d(m[0].outerTabContainer).children().each(function(n, o) {
			if (o.title == l.title) {
				k = d(o)
			}
		});
		return k
	}
})(jQuery);