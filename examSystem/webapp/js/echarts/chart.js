var count_age = new Array();
var count_academic = new Array();
var count_category = new Array();
var count_usenum = new Array();
$.ajax({
	type:'get',
	dataType:'json',
	url:'agecount.action',
	success:function(jsonData){
		count_age = jsonData;
	},
	error:function(){
		alert("系统出错...");
	}
});
function ageClick(){
	$.ajax({
		type:'get',
		dataType:'json',
		url:'agecount.action',
		success:function(jsonData){
			count_age = jsonData;
		},
		error:function(){
			alert("系统出错...");
		}
	});
	setTimeout(egeCount, 200);
}
function usenumClick(){
	$.ajax({
		type:'get',
		dataType:'json',
		url:'usernumcount.action',
		success:function(jsonData){
			count_usenum = jsonData;
		},
		error:function(){
			alert("系统出错...");
		}
	});
	setTimeout(usenumCount, 500);
}

function egeCount(){
	var dldChart = echarts.init(document.getElementById('ageCount'));
	option = {
		    title : {
		        text: '专家年龄统计'
		        //subtext: '年龄统计'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['人数']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['小于30','30-40','40-50','50-60','60-70','70-80','大于80']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'人数',
		            type:'bar',
		            data:count_age
		           /* markPoint : {
		                data : [
							{name: '最小年龄', value: 0, xAxis: 0, yAxis: 0},      // 当xAxis为类目轴时，数值1会被理解为类目轴的index
							{name: '最大年龄', value: 55, xAxis: '50-60', yAxis: 0},
		                ]
		            }*/
		           /* markLine : {
		                data : [
		                    {type : 'average', name: '平均年龄'}
		                ]
		            }*/
		        }
		    ]
		};
	//option.yAxis[0].data=count_dld[0+(n-4)*2];
	option.series[0].data = count_age.slice(0,7);
	//option.series[0].markPoint.data[0].value = count_age[7];
	//option.series[0].markPoint.data[1].value = count_age[8];
	//option.legend.data[0] = count_dld[n];
	//option.series[0].name = count_dld[n];
	
	dldChart.setOption(option);
	$("#maxage").text(count_age[8]);
	$("#minage").text(count_age[7]);
	$("#avgage").text(count_age[9]);
	$("#info").css("display","block");
	
	/*if(n==4){
		$("#yearChangeId").click(function(){
			docDownload(5);
		});
		$("#yearChangeId")[0].innerHTML=count_dld[5]+"排行";
	}else{
		$("#yearChangeId").click(function(){
			docDownload(4);
		});
		$("#yearChangeId")[0].innerHTML=count_dld[4]+"排行";
	}
	$("#yearChange").removeAttr("style");*/
}
function usenumCount(){
	var numChart = echarts.init(document.getElementById('ageCount'));
	option = {
		    title : {
		        text: '专家安排频次统计'
		        //subtext: '年龄统计'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['人数']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['安排一次','安排两次','安排三次','安排三次以上']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'人数',
		            type:'bar',
		            data:[0,0,0,0]
		        }
		    ]
		};
	option.series[0].data = count_usenum;
	numChart.setOption(option);
	$("#info").css("display","none");
}
function expCategory(){
	
	$("#yearChange").attr("style","display:none;");
	var levChart = echarts.init(document.getElementById('docChart'));
	option = {
		    title : {
		        text: '专家类别统计',
		        subtext: 'professional statistical',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:['一类文档','二类文档','三类文档']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1548
		                    }
		                }
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'By DMIS',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:0, name:'一类文档'},
		                {value:0, name:'二类文档'},
		                {value:0, name:'三类文档'}
		            ]
		        }
		    ]
		};
	option.series[0].data[0].value=count_lev[0];
	option.series[0].data[1].value=count_lev[1];
	option.series[0].data[2].value=count_lev[2];
	levChart.setOption(option);
}

function expCategory(){
	
	$("#yearChange").attr("style","display:none;");
	var catChart = echarts.init(document.getElementById('docChart'));
	var option = {
			tooltip : {
				show : true
			},
			legend : {
				data : [ '专家类别统计' ]
			},
			xAxis : [ {
				type : 'category',
				data : [ "doc", "docx", "pdf", "ppt", "pptx", "xls", "xlsx" ]
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				"name" : "文档类型统计",
				"type" : "bar",
				"data" : count_cat
			} ]
		};
	option.series[0].data = count_cat;
	catChart.setOption(option);
}
setTimeout(egeCount, 500);//页面首次加载时显示‘专家年龄统计’，延时处理