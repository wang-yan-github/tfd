(function() {
	Raphael.fn.arrowLine = function(s, j) {
		var t = "";
		for ( var n = 0; n < s.length; n++) {
			var g = s[n];
			if (n == 0) {
				t += "M" + g[0] + "," + g[1]
			} else {
				t += "L" + g[0] + "," + g[1]
			}
		}
		if (s.length >= 2) {
			var f = s[s.length - 1][0];
			var r = s[s.length - 1][1];
			var e = s[s.length - 2][0];
			var p = s[s.length - 2][1];
			var m = f - e;
			var h = r - p;
			var o = (h) / (m);
			var c = (-1) / o;
			var q = 7;
			var l = this.path("M" + f + "," + r + "L" + (f - q / Math.sqrt(3))
					+ "," + (r - q) + "L" + (f + q / Math.sqrt(3)) + ","
					+ (r - q) + "L" + f + "," + r);
			l.attr("fill", j);
			if (m > 0 && h > 0) {
				l.rotate(-(90 - Math.abs(Math.atan(o) * 180 / Math.PI)), f, r)
			} else {
				if (m < 0 && h > 0) {
					l.rotate((90 - Math.abs(Math.atan(o) * 180 / Math.PI)), f,
							r)
				} else {
					if (m < 0 && h < 0) {
						l.rotate((90 + Math.abs(Math.atan(o) * 180 / Math.PI)),
								f, r)
					} else {
						if (m > 0 && h < 0) {
							l.rotate(-(90 + Math.abs(Math.atan(o) * 180
									/ Math.PI)), f, r)
						} else {
							if (m > 0 && h == 0) {
								l.rotate(-90, f, r)
							} else {
								if (m < 0 && h == 0) {
									l.rotate(90, f, r)
								} else {
									if (m == 0 && h > 0) {
									} else {
										if (m == 0 && h < 0) {
											l.rotate(180, f, r)
										}
									}
								}
							}
						}
					}
				}
			}
		}
		var k = this.path(t);
		k.attr("stroke", j);
		return k
	};
	var b;
	var a = false;
	window.FlowDesigner = function(h) {
		var e = $(h.canvas).parent();
		var f = new Array();
		var d;
		$(e).mousemove(function(k) {
			var j = $(this);
			if (a) {
				if (b) {
					var i = parseInt(b.attr("offsetX"));
					var n = parseInt(b.attr("offsetY"));
					var m = k.pageX - $(e).position().left - i;
					var l = k.pageY - $(e).position().top - n;
					b.css({
						left : m,
						top : l
					});
					if (d) {
						d(f, k)
					}
				}
			}
		});
		this.drawStart = function(i, m, l) {
			var j = "<div node start class='flow-start' id='" + l + "'></div>";
			var k = $(j).appendTo($(e)).css({
				left : i,
				top : m
			});
			g(k);
			c(k);
			f.push(k);
			return k
		};
		this.drawEnd = function(i, m, l) {
			var j = "<div node end class='flow-end' id='" + l + "'></div>";
			var k = $(j).appendTo($(e)).css({
				left : i,
				top : m
			});
			g(k);
			c(k);
			f.push(k);
			return k
		};
		this.drawSimpleNode = function(i, o, n, m, l) {
			var j = "<table node simple class='flow-simple-node-frame' id='"
					+ n + "'>";
			j += "<tr class='flow-simple-node-title'><td>" + m + "</td></tr>";
			j += "<tr class='flow-simple-node-content'><td>" + l + "</td></tr>";
			j += "</table>";
			var k = $(j).appendTo($(e)).css({
				left : i,
				top : o
			});
			g(k);
			c(k);
			f.push(k);
			return k
		};
		this.drawAggregationNode = function(i, o, n, m, l) {
			var j = "<table node aggregation class='flow-aggregation-node-frame' id='"
					+ n + "'>";
			j += "<tr class='flow-aggregation-node-title'><td>" + m
					+ "</td></tr>";
			j += "<tr class='flow-aggregation-node-content'><td>" + l
					+ "</td></tr>";
			j += "</table>";
			var k = $(j).appendTo($(e)).css({
				left : i,
				top : o
			});
			g(k);
			c(k);
			f.push(k);
			return k
		};
		this.drawParallelNode = function(i, o, n, m, l) {
			var j = "<table node parallel class='flow-parallel-node-frame' id='"
					+ n + "'>";
			j += "<tr class='flow-parallel-node-title'><td>" + m + "</td></tr>";
			j += "<tr class='flow-parallel-node-content'><td>" + l
					+ "</td></tr>";
			j += "</table>";
			var k = $(j).appendTo($(e)).css({
				left : i,
				top : o
			});
			g(k);
			c(k);
			f.push(k);
			return k
		};
		this.removeNode = function(o) {
			var q = o.attr("id");
			for ( var n = 0; n < f.length; n++) {
				var m = f[n];
				var l = m[0].nextnodes;
				for ( var k = 0; k < l.length; k++) {
					if (l[k].attr("id") == q) {
						m[0].nextnodes.splice(k, 1);
						break
					}
				}
				if (m.attr("id") == q) {
					f.splice(n, 1)
				}
			}
			for ( var n = 0; n < f.length; n++) {
				var m = f[n];
				var p = m[0].prevnodes;
				for ( var k = 0; k < p.length; k++) {
					if (p[k].attr("id") == q) {
						m[0].prevnodes.splice(k, 1);
						break
					}
				}
			}
			o.remove()
		};
		var c = function(i) {
			i.mousedown(
					function(j) {
						i.attr("offsetX", j.pageX - $(e).position().left
								- i.position().left);
						i.attr("offsetY", j.pageY - $(e).position().top
								- i.position().top);
						b = i;
						a = true
					}).mouseup(function() {
				b = undefined;
				a = false
			})
		};
		var g = function(i) {
			i[0].nextnodes = new Array();
			i[0].prevnodes = new Array();
			i.addNextNode = function(q) {
				var j = false;
				var k = false;
				var p = $(this);
				var m = p[0].nextnodes;
				var o = q[0].prevnodes;
				for ( var l = 0; l < m.length; l++) {
					if (m[l].attr("id") == q.attr("id")) {
						j = true;
						break
					}
				}
				for ( var l = 0; l < o.length; l++) {
					if (o[l].attr("id") == p.attr("id")) {
						k = true;
						break
					}
				}
				if (!j) {
					m.push(q)
				}
				if (!k) {
					o.push(p)
				}
			};
			i.getNextNodes = function() {
				var k = $(this);
				var j = k[0].nextnodes;
				return j
			};
			i.getPrevNodes = function() {
				var k = $(this);
				var j = k[0].prevnodes;
				return j
			}
		};
		this.setMovedListener = function(i) {
			d = i
		};
		this.lineTo = function(G, o) {
			var t = G.position().left;
			var F = G.position().top;
			var u = G.height();
			var E = G.width();
			var M = t + E / 2;
			var L = F + u / 2;
			var l = o.position().left;
			var s = o.position().top;
			var O = o.height();
			var H = o.width();
			var Q = l + H / 2;
			var P = s + O / 2;
			var K = l;
			var J = l + H;
			var p = s;
			var n = s + O;
			var D = P - L;
			var z = Q - M;
			var y = L * z - M * D;
			var v = [ K, (K * D + y) / z ];
			var k = [ J, (J * D + y) / z ];
			var r = [ (p * z - y) / D, p ];
			var q = [ (n * z - y) / D, n ];
			var x = new Array();
			if (v[1] >= s && v[1] <= s + O) {
				x.push(v)
			}
			if (k[1] >= s && k[1] <= s + O) {
				x.push(k)
			}
			if (r[0] >= l && r[0] <= l + H) {
				x.push(r)
			}
			if (q[0] >= l && q[0] <= l + H) {
				x.push(q)
			}
			var j;
			var w = 100000;
			for ( var I = 0; I < x.length; I++) {
				var m = x[I];
				var N = Math.sqrt(Math.pow((m[0] - M), 2)
						+ Math.pow((m[1] - L), 2));
				if (I == 0) {
					j = m;
					w = N
				} else {
					if (w > N) {
						w = N;
						j = m
					}
				}
			}
			h.arrowLine([ [ M, L ], j ], "#434343")
		};
		this.doLineTo = function() {
			for ( var l = 0; l < f.length; l++) {
				var m = f[l];
				var n = m.getNextNodes();
				for ( var k = 0; k < n.length; k++) {
					this.lineTo(m, n[k])
				}
			}
		};
		this.repaint = function() {
			h.clear()
		}
	};
	$.fn.addNextNode = function(j) {
		var c = false;
		var d = false;
		var h = $(this);
		var f = h[0].nextnodes;
		var g = j[0].prevnodes;
		for ( var e = 0; e < f.length; e++) {
			if (f[e].attr("id") == j.attr("id")) {
				c = true;
				break
			}
		}
		for ( var e = 0; e < g.length; e++) {
			if (g[e].attr("id") == h.attr("id")) {
				d = true;
				break
			}
		}
		if (!c) {
			f.push(j)
		}
		if (!d) {
			g.push(h)
		}
	};
	$.fn.getNextNodes = function(e) {
		var d = $(this);
		var c = d[0].nextnodes;
		return c
	};
	$.fn.getPrevNodes = function() {
		var d = $(this);
		var c = d[0].prevnodes;
		return c
	}
})();