<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>准考证</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resource/lodop6.217/LodopFuncs.js"></script>
	<!-- 设置一个常量WebRoot,保存当前应用的contextpath -->
	<script type="text/javascript">
		var WebRoot = '${pageContext.request.contextPath}';
	</script>

	<!-- 注册一个Lodop控件 -->
	<!-- <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA"
		width=0 height=0>
		<param name="CompanyName" value="万达信息股份有限公司">
		<param name="License" value="649717666688688748719056235623">
	</object> -->
	
	<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
	</object>

	<script type="text/javascript">
	//var LODOP = document.getElementById("LODOP");//这行语句是为了符合DTD规范
	
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	
	/* CheckLodop();

	function create() {
		LODOP.PRINT_INIT("");
		
		//假设我们使用A4纸张打印 A4=21cm * 29.7cm
		//页边距我们设置为 1cm (top、left、rifht、bottom)
		//width =21-1*2 =21-2=19
		//height =29.7-1*2=29.7-2=27.7cm
		LODOP.SET_PRINT_PAGESIZE (3, 0, 0,"A4");
		LODOP.SET_SHOW_MODE('LANDSCAPE_DEFROTATED',1);
		LODOP.ADD_PRINT_HTM('1.3cm', '0.7cm', '19cm', '27.7cm', document.getElementById("from_div").innerHTML);  
		//LODOP.ADD_PRINT_TABLE('1.3cm', '0.7cm', '19cm', '27.7cm', document.getElementById("table_p").innerHTML);  
	}

	function print() {
		create();
		LODOP.PRINT();
	}
*/
	function preview() {
		LODOP.PRINT_INITA(1,0,770,1100,"准考证打印预览");
		
		<c:forEach var="aBean" items="${printVOs}" varStatus="status">
			LODOP.PRINT_INITA(15,9,770,1100,"准考证打印");
			LODOP.ADD_PRINT_RECT(9,9,730,1025,0,1);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
			LODOP.ADD_PRINT_TEXT(35,79,611,34,'<c:out value="${aBean[0] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
			LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.SET_PRINT_STYLEA(0,"Bold",1);
			LODOP.ADD_PRINT_TEXT(93,330,110,29,"准考证");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
			LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.SET_PRINT_STYLEA(0,"Bold",1);
			LODOP.ADD_PRINT_TEXT(148,56,163,25,"姓名："+'<c:out value="${aBean[1] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(147,267,227,25,"准考证号："+'<c:out value="${aBean[3] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(190,56,255,25,"身份证号："+'<c:out value="${aBean[2] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(235,56,439,25,"单位名称："+'<c:out value="${aBean[4] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(278,56,255,25,"专业："+'<c:out value="${aBean[11] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(278,367,131,25,"等级："+'<c:out value="${aBean[12] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(322,56,255,25,"考场："+'<c:out value="${aBean[5] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(320,368,129,25,"座号："+'<c:out value="${aBean[6] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			
			LODOP.ADD_PRINT_TEXT(367,56,680,25,"考点名称："+'<c:out value="${aBean[7] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(414,56,680,25,"考点地址："+'<c:out value="${aBean[8] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_TEXT(460,56,680,25,"考试时间："+'<c:out value="${aBean[9] }"/>'+" 至 "+'<c:out value="${aBean[10] }"/>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.ADD_PRINT_RECT(551,18,713,467,3,1);
			LODOP.ADD_PRINT_TEXT(507,19,120,26,"注意事项：");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
			LODOP.SET_PRINT_STYLEA(0,"Bold",1);
			LODOP.ADD_PRINT_TEXT(564,30,695,40,"1、考试开始前15 分钟，考试人员凭准考证和身份证（缺一不可）进入考场对号入座，同时将身份证和准考证放在座位左上角，监考人员逐一进行查验。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_TEXT(621,31,695,20,"2、考试开始20 分钟后，考试人员不得入场。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_TEXT(653,31,695,40,"3、考试人员进入考场时，不准携带书籍、资料等物品，已携带入场的须按照要求存放在指定位置。携带的手机等电子设备要全部关闭。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_TEXT(707,32,695,20,"4、考试人员按照座号入座，不得随意调换座位。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_TEXT(740,32,695,60,"5、登录系统指令发出后，考试人员方可使用身份证号和准考证号登录考试系统。登录后应仔细核对姓名、准考证号、场次及本人照片等信息，并仔细阅读《考试须知》。考试人员如发现信息错误，应举手示意，听从监考人员的安排处理。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_TEXT(811,32,695,60,"6、考试人员登录考试界面后，不得随意退出或关闭浏览器。考试开始指令发出后，考试人员方可开始答卷，系统开始计时。开考20 分钟内未能在考试机上登录并确认的考试人员，视为缺考，考试系统将不再接受该准考证号登录。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_TEXT(879,32,695,40,"7、考试机如出现故障，考试人员须举手示意，由技术人员进行处理，严禁故意关机或自行重新启动计算机以及其他恶意操作行为。");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
			LODOP.SET_PRINT_STYLEA(0,"Italic",1);
			LODOP.ADD_PRINT_RECT(160,560,121,158,1,1);
			LODOP.ADD_PRINT_TEXT(229,571,100,20,"照片粘贴处");
			LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.NewPage();
		</c:forEach>
		LODOP.PREVIEW();
	}
</script>
	<body lang=ZH-CN style='text-justify-trim:punctuation'>
			<div>
				<!-- <a href="javascript:print();">打印</a> -->
				<a href="javascript:preview();">预览</a>
				<a href="javascript:window.close();">关闭</a>
			<%-- <s:debug></s:debug> --%>
			</div>
			<br>
			<center>
			<div id="from_div" align="left">
			<c:forEach var="aBean" items="${printVOs}" varStatus="status">
				<div class=WordSection1 style='layout-grid:15.6pt;'>

<div style='border:solid windowtext 1.0pt;padding:1.0pt 4.0pt 1.0pt 4.0pt;width: 700px;'>
<%-- <p class=MsoNormal align=center style='text-align:center;border:none;
padding:0cm'><span lang=EN-US style='font-size:16.0pt'>&nbsp;</span></p> --%>

<p class=MsoNormal align=center style='text-align:center;border:none;
padding:0cm'><span style='font-size:16.0pt;font-family:宋体'><c:out value="${aBean[0] }"></c:out> </span></p>

<p class=MsoNormal align=center style='text-align:center;border:none;
padding:0cm'><span style='font-size:16.0pt;font-family:宋体'>准</span><span
lang=EN-US style='font-size:16.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
style='font-size:16.0pt;font-family:宋体'>考</span><span lang=EN-US
style='font-size:16.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
style='font-size:16.0pt;font-family:宋体'>证</span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;
border:none;padding:0cm'><span style='font-size:12.0pt;
font-family:宋体'>姓</span><span lang=EN-US style='font-size:
12.0pt;'>&nbsp;&nbsp;&nbsp; </span><span style='font-size:12.0pt;
font-family:宋体'>名：<c:out value="${aBean[1] }"></c:out></span>
<span lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
<span style='font-size:12.0pt;font-family:宋体'>准考证号：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[3] }"></c:out></span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>身份证号：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[2] }"></c:out></span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>单位名称：<c:out value="${aBean[4] }"></c:out></span>
</p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>专</span>
<span lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp; </span>
<span style='font-size:12.0pt;font-family:宋体'>业：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[11] }"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
<span style='font-size:12.0pt;font-family:宋体'>等级：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[12] }"></c:out></span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>考</span>
<span lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp; </span>
<span style='font-size:12.0pt;font-family:宋体'>场：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[5] }"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
<span style='font-size:12.0pt;font-family:宋体'>座号：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[6] }"></c:out></span></p>



<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>考点名称：<c:out value="${aBean[7] }"></c:out></span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>考点地址：<c:out value="${aBean[8] }"></c:out></span>
</p>

<p class=MsoNormal align=left style='text-align:left;text-indent:18.0pt;border:none;padding:0cm'>
<span style='font-size:12.0pt;font-family:宋体'>考试时间：</span>
<span lang=EN-US style='font-size:12.0pt;'><c:out value="${aBean[9] }"></c:out> </span>
<span style='font-size:12.0pt;font-family:宋体'>至</span>
<span lang=EN-US style='font-size:12.0pt;'> <c:out value="${aBean[10] }"></c:out></span>
</p>
<p class=MsoNormal style='line-height:60%;border:none;padding:0cm'><u><span
lang=EN-US style='font-size:12.0pt;line-height:60%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;</span></u></p>


<p class=MsoNormal style='border:none;padding:0cm'>
	<b>
		<span style='font-family:宋体'>考试注意事项：</span>
	</b>
</p>
<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>1、考试开始前</span><span lang=EN-US>15</span>
	<span style='font-family:宋体'>分钟，考试人员凭准考证和身份证（缺一不可）进入考场对号入座，同时将身</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;份证和准考证放在座位左上角，监考人员逐一进行查验。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>2、考试开始</span><span lang=EN-US>20</span>
	<span style='font-family:宋体'>分钟后，考试人员不得入场。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>3、考试人员进入考场时，不准携带书籍、资料等物品，已携带入场的须按照要求存放在指定位</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;置。携带的手机等电子设备要全部关闭。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>4、考试人员按照座号入座，不得随意调换座位。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>5、登录系统指令发出后，考试人员方可使用身份证号和准考证号登录考试系统。登录后应仔细核</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;对姓名、准考证号、场次及本人照片等信息，并仔细阅读《考试须知》。考试人员如发现信息</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;错误，应举手示意，听从监考人员的安排处理。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>6、考试人员登录考试界面后，不得随意退出或关闭浏览器。考试开始指令发出后，考试人员方可</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;开始答卷，系统开始计时。开考</span><span lang=EN-US>20</span>
	<span style='font-family:宋体'>分钟内未能在考试机上登录并确认的考试人员，视为缺考，</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;考试系统将不再接受该准考证号登录。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>7、考试机如出现故障，考试人员须举手示意，由技术人员进行处理，严禁故意关机或自行重新启</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;动计算机以及其他恶意操作行为。</span>
</p>

<%-- <p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>8、考试结束指令发出后，考试人员应立即停止答卷，向系统提交试卷，并按要求退场。提前结束</span>
	<br>
	<span style='font-family:宋体'>&nbsp;&nbsp;考试的，应立即退场，不得在考场附近逗留、喧哗。</span>
</p>

<p class=MsoListParagraph style='border:none;padding:0cm'>
	<span style='font-family:宋体'>9、考试人员要严格遵守考场纪律，自觉维护考场秩序，不得交头接耳、大声喧哗。一经发现有违</span>
	<br>
	<span style='font-family:宋体;'>&nbsp;&nbsp;反考纪行为的，一律取消考试成绩和补考资格，通报所在单位党组织，并按照相关规定作出处</span>
	<br>
	<span style='font-family:宋体;'>&nbsp;&nbsp;理。</span>
</p> --%>
<%-- <p>
<span style='page-break-after:always;'>&nbsp;&nbsp;</span>
</p> --%>

</div>
</div>
</c:forEach>
</div>

				<%-- <div id="from_div" align="center">
				<c:forEach var="dto" items="${printVOs}" varStatus="s">
				<table width="1050" height="432" border="0" align="center" style="page-break-after:always;border-left: 3px solid #ddd;border-bottom: 3px solid #ddd;border-right: 3px solid #ddd;border-top: 3px solid #ddd;" cellspacing="6" >   
				  <tr>
				    <td colspan="3" align="left">
				    <img width="501" height="123" src='<c:url value="/pages/jinengdasai/e7703/1log_new1.png"/>'></td> 
				    <td width="540" rowspan="12" style="line-height:30px;border-left: 3px solid #ddd;" > 
				        <p class="MsoNormal" align="center" style="margin-top:0cm;margin-right:0pt;margin-bottom:0cm;margin-left:0pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;mso-para-margin-right:-.39gd;mso-para-margin-bottom:0cm;mso-para-margin-left:-.85gd;mso-para-margin-bottom:.0001pt;text-align:center;tab-stops:84.0pt;"><b><span style="font-size:22.0pt;font-family: 宋体">参  赛  须  知<span lang="EN-US"><o:p></o:p></span></span></b></p>
						<!-- <p class="MsoNormal" align="center" style="margin-top:0cm;margin-right:0pt;margin-bottom:0cm;margin-left:0pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;mso-para-margin-right:-.39gd;mso-para-margin-bottom:0cm;mso-para-margin-left:-.85gd;mso-para-margin-bottom:.0001pt;text-align:center;tab-stops:84.0pt;"><b><span lang="EN-US" style="font-size:14.0pt;font-family:宋体;">&nbsp;</span></b></p> -->
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;">　　 1.</span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">参赛选手按照大赛规定的时间和地点参加比赛。<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;">　　 2.</span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">考试请带好黑色钢笔或签字笔及 2B铅笔、橡皮。<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;">　　 3.</span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">参赛选手开考前 30分钟到达指定考场，按监考老<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;师引导入场；考试开始 30分钟后不得入场考试；开考 30<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;分钟后方可交卷退场。<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;">　　 4.</span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">参赛选手必须携带参赛证和身份证（学生证）原件<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;入场（缺一不可），以备监考人员检查。<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;">　　 5.</span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">参赛选手要自觉维护考场秩序，听从现场裁判及考<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;务人员的安排；禁止携带具有存储或接收信息功能的设备<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;（如手机等），对违反考试纪律和舞弊者，视情节轻重，<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;分别给予批评教育、本场成绩作废或取消比赛资格处理。<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;">　　 6.</span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">其他注意事项参照大赛通知和公告执行。<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;成绩查询网址:http://wsbs.qdhrss.gov.cn/wssbrcyj/<span lang="EN-US"><o:p></o:p></span></span></p>
						<p class="MsoNormal" style="line-height:150%; "><span lang="EN-US" style="font-size:14.0pt;line-height:150%;font-family:宋体;"></span><span style="font-size:14.0pt;line-height:150%;font-family:宋体;">&nbsp;&nbsp;work/dsRegister/queryScore.action<span lang="EN-US"><o:p></o:p></span></span></p>
				        </td>
				  </tr>
				  <tr>
				    <td colspan="3" height="100"><div align="center">   
				   <p class="MsoNormal" align="center" style="text-align:center;">
				    <b><span style="font-size:50.0pt;font-family:宋体;">参 赛 证<span lang="EN-US"></span></span></b></p>
				    </div>
				    </td>
				  </tr>
				  <tr>
				    <td width="150px" rowspan="4" bordercolor="#F0F0F0">
				    <div style="border:1px solid #ccc; width:150px; height:180px; margin-left: 10px;">
				      <div style="width:150px; height:180px; border:1px solid #666;">
				      	<img src="${pageContext.request.contextPath}/work/e7703/showImage.action?chz018=${dto.chz018}" style="width:150px; height:180px;" />
					 </div>
				    </div>
				    </td>
				    <td align="left" width="320" height="36"><span style="font-size:15.0pt;font-family:宋体;">&nbsp;姓　　名:<u>${dto.aac003}</u></span></td>
				  </tr>
				  <tr align="left">
				    <td align="left" height="36"><p><span style="font-size:15.0pt;font-family:宋体;">&nbsp;身份证号:<u>${dto.aac147}</u></span></p></td>
				  </tr>
				  <tr align="left" >
				    <td  align="left" height="40"><span style="font-size:15.0pt;font-family:宋体;">&nbsp;参赛工种:<u>${dto.aca112}</u></span></td>
				  </tr>
				  <tr align="left">
				    <td align="left" height="41"><span style="font-size:15.0pt;font-family:宋体;">&nbsp;参赛证号:<u>${dto.dsbm13}</u></span></td>
				  </tr>
				  <tr height="50">
				    <td height="33" colspan="2"> &nbsp;&nbsp;&nbsp;</td>
				  </tr>
				  <tr>
				    <td height="33" colspan="2">
				    	<span style="font-size:15.0pt;font-family:宋体;">&nbsp;理论竞赛时间：<u>${dto.llkssj}至${dto.lljssj}</u></span>
				    </td>
				  </tr>
				  <tr>
				    <td colspan="2">
				    	<p>
				    		<span style="font-size:15.0pt;font-family:宋体;">&nbsp;地　　　　点：<u>${dto.llksdd}</u></span>
				    	</p>
				    </td>  
				  </tr>
				  <tr>
				    <td colspan="2"><span style="font-size:15.0pt;font-family:宋体;">&nbsp;实作竞赛时间：<u>${dto.szkssj}至${dto.szjssj}</u></span></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p><span style="font-size:15.0pt;font-family:宋体;">&nbsp;地　　　　点：<u>${dto.szksdd}</u></span></p></td>
				  </tr>
				  
				  <tr height="100">
				    <td colspan="2">
				    
				    <div align="center">
				      <p class="MsoNormal"><b>
				      <span style="font-size:16.0pt;font-family:宋体;mso-ascii-font-family:Calibri;mso-ascii-theme-font:minor-latin;mso-fareast-font-family:宋体;mso-fareast-theme-font:minor-fareast;mso-hansi-font-family:Calibri;mso-hansi-theme-font:minor-latin;">
				                青岛市第十四届职业技能大赛组委会办公室
				       </span></b><b><span lang="EN-US" style="font-size:18.0pt;"><o:p></o:p></span></b></p>
				    </div>
				    </td>
				  </tr>
				</table>
				</c:forEach>
			</div> --%>
		</center>
	</body>
</html>