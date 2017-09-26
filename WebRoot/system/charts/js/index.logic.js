function doinit(){
	var priv = getChartsPriv();
	var data = getChartsData(priv);
	$(".charts_li").click(function(){
		$(".charts_li").removeClass("charts_li_clicked");
		$(this).addClass("charts_li_clicked");
	});
	$('.charts_modules').quickFlip({
        vertical : true
    });
    $(".charts_switch").click(function(){
    	var src = $("#charts_more").prop("src");
    	imgName = src.substr(src.lastIndexOf("/"),src.length);
    	if(imgName=="/switch.png"){
    		$(".redPanel .quickFlipCta").trigger("click");
    		$("#charts_more").prop("src",imgPath+"/charts/switch_2.png");
    	}else{
    		$(".redPanel .quickFlipCta").trigger("click");
    		$("#charts_more").prop("src",imgPath+"/charts/switch.png");
    	}
    	
    });
}
function getChartsPriv(){
	var all = "";
	var requestUrl=contextPath+"/tfd/system/charts/act/ChartsAct/getChartsModulesByPriv.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			if(data.length>=9){
				$(".charts_switch").show();
			}
			$.each(data,function(index,data){
				if(index==0){
					html += "<div class=\"redPanel\" ><a class=\"quickFlipCta\" href=\"javascript:void(0);\" ></a><ul id=\"charts_ul\" ><li class=\"charts_li li_first\" onclick=\"javascript:getChartsData('"+data.chartsModule+"')\"  >"+data.chartsName+"</li>";
					all += data.chartsModule+",";
				}else if(index==9){
					html += "</ul></div><div class=\"blackPanel\" ><a class=\"quickFlipCta\" href=\"javascript:void(0);\" ></a><ul id=\"charts_ul\" >";
					html += "<li class=\"charts_li\" onclick=\"javascript:getChartsData('"+data.chartsModule+"')\"  >"+data.chartsName+"</li>";
					all += data.chartsModule+",";
				}else{
					html += "<li class=\"charts_li\" onclick=\"javascript:getChartsData('"+data.chartsModule+"')\"  >"+data.chartsName+"</li>";
					all += data.chartsModule+",";
				}
			});
			$(".charts_modules").html(html+"<ul></div>");
			$(".li_first").before("<li class=\"charts_li charts_li_clicked\" onclick=\"javascript:getChartsData('"+all+"','全部')\" >全部</li>");
		}
	});
	return all;
}

function getChartsData(priv){
	var requestUrl=contextPath+"/tfd/system/charts/act/ChartsAct/getChartsData.act";
	$.ajax({
		url:requestUrl,
		data:{priv:priv,type:'1'},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			createHtml(data);
			setDataInHtml(data);
		}
	});
}

function createHtml(data){
	var html = "";
	$.each(data,function(index,data){
		html += "<div class=\"charts-div\" onclick=\"showDetail('"+data.module+"')\" id="+data.module+"  ></div>";
	});
	if(html==""){
		$(".charts_main").hide();
		$(".MessageBox").slideDown(600);
	}else{
		$(".charts_main").show();
		$(".MessageBox").hide();
	}
	$(".charts_main").html(html);
}

function setDataInHtml(data){
	require.config({paths: {echarts: contextPath+'/system/jsall/echarts/dist'}});
	    // 使用
	$.each(data,function(index,data){
		if(data.type == 'bar' || data.type == 'line'){
			require([
	            'echarts',
	            'echarts/chart/'+data.type 
	        ],
		        function (ec) {
		            // 基于准备好的dom，初始化echarts图表
		            var myChart = ec.init(document.getElementById(data.module)); 
		            var option = {
		            	title : {
	        				text: data.name
	    				},
		                tooltip: {
		                    show: true
		                },
		                legend: {
		                    data:data.legend
		                },
		                xAxis : [
		                    {
		                        type : 'category',
		                        data : data.listX
		                    }
		                ],
		                yAxis : [
		                    {
		                        type : 'value'
		                    }
		                ],
		                series : data.list
		            };
		            myChart.setOption(option); 
		        }
		    );
		}else{
			require([
	            'echarts',
	            'echarts/chart/'+data.type 
	        ],
		        function (ec) {
		            // 基于准备好的dom，初始化echarts图表
		            var myChart = ec.init(document.getElementById(data.module));
					option = {
				    title : {
				        text: data.name,
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient : 'vertical',
				        x : 'left',
				        data:data.listX
				    },
				    calculable : true,
				    series : data.list
				};
				 myChart.setOption(option); 
		        }
		    );
		}
    });
}

function showDetail(module){
	window.open(contextPath+"/system/charts/detail.jsp?module="+module);
}


