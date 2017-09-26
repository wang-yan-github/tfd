<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String module = request.getParameter("module");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/charts/index.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/echarts/dist/echarts.js"></script>
<title>图表分析</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
</style>
<script>
var module = "<%=module%>";
	function doinit(){
		var requestUrl=contextPath+"/tfd/system/charts/act/ChartsAct/getChartsData.act";
		$.ajax({
			url:requestUrl,
			data:{priv:module,type:'2'},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				require.config({paths: {echarts: contextPath+'/system/jsall/echarts/dist'}});
				$.each(data,function(index,data){
					$("body").html("<div id=\""+data.module+"\" style=\"height:100%;\" ></div>");
					if(data.type == 'bar' || data.type == 'line'){
						require([
						            'echarts',
						            'echarts/chart/bar',
						            'echarts/chart/line'
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
						                toolbox: {
						                    show : true,
						                    feature : {
						                        magicType : {show: true, type: data.types},
						                        restore : {show: true},
						                        saveAsImage : {show: true}
						                    }
						                },
						                legend: {
						                    data:data.legend
						                },
						                xAxis : [
						                    {
						                        type : 'category',
						                        data : data.listX,
						                        axisLabel:{
						                          interval:0,
						                          rotate:-45,
						                          margin:2,
						                          textStyle:{
						                              color:"#222"
						                          }
						                      },
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
						            'echarts/chart/pie',
						            'echarts/chart/funnel'
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
							    toolbox: {
							        show : true,
							        feature : {
							            magicType : {
							                show: true, 
							                type: ['pie', 'funnel']
							            },
							            restore : {show: true},
							            saveAsImage : {show: true}
							        }
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
		});
	}
</script>
</head>
<body onload="doinit()" >

</body>
</html>