<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>考试试卷信息列表</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
</head>
<script>
	/* function opens() {
		var paperid = document.getElementById("paperId").value;
		var return_val = window
				.showModalDialog("kaoshi/paper/ExamNotice.jsp?paperid="
						+ paperid, "",
						"dialogWidth=1200px;dialogHeight=600px;center:yes;scroll:yes;status:no");
		if (return_val.code == "true") {
			test();
		}
	} */
	function opens() {
		window.showModalDialog("kaoshi/paper/ExamNotice.jsp", 
				"","dialogWidth=1200px;dialogHeight=600px;center:yes;scroll:yes;status:no;controlbox:false");
	}
	function test() {
		var paperid = document.getElementById("paperId").value;
		$.ajax({
			type:'post',
			url:'checkExam.action?paperId='+paperid,
			success:function(result){
				if(result=='submitAlready'){
					window.location.href = "/qdSkillMonitoring/checkResult.action?paperId="
						+ paperid;
				}else if(result=='success'){
					window.location.href = "/qdSkillMonitoring/examAllAction.action?paperId="
							+ paperid;
				}else if(result=='false1'){
					alert("一个考生只能在同一台电脑参加考试!请联系监考老师！");
				}else if(result=='flase2'){
					alert("一台电脑只允许一个考生参加考试！请联系监考老师！");
				}else if(result=='0'){
					alert("考试还没有开始，请耐心等待！");
				}
			},
			error:function(){
				alert("系统出错，请联系管理员！");
			}
		});
	}
</script>
<body class="nrbj" style="background-color: #F8FBFD;">
	<div style="width: 100%;text-align: center;margin-top: -20px;">
		<input type="hidden" id="paperId" value="<c:out value="${epapers.sjId }"/>">
		<div style="height: 20px;"></div>
		<div class="examOnline">
			<div class="examinfo">
				<font size="6px">试卷名称：<c:out value="${epapers.sjMc }"/></font>
				<div style="float: right; margin-right: 10px;margin-top: -10px;">
					<a id="btn_age" class="btn_exam" href="javascript:void(0)" onclick="opens()">考试公告</a>
				</div>
			</div>
			<div class="examinfo">
				<font size="6px">工种：<c:out value="${gzmc }"/></font>
			</div>
			<div class="examinfo">
				<font size="6px">等级：<c:out value="${gzdj }"/></font>
			</div>
			<div class="examinfo">
				<font size="6px">有效截止日期：<c:out value="${epapers.sjYxqjzsj}"/></font>
			</div>
			<div class="examinfo">
				<a id="btn_age" class="btn_exam" href="javascript:void(0)" onclick="test()">参加考试</a>
			</div>
		</div>
		<%-- <div class="examOnline">
			<div>
				<font>工种：<c:out value="${gzmc }"/></font>
			</div>
		</div>
		<div class="examOnline">
			<div>
				<font>等级：<c:out value="${gzdj }"/></font>
			</div>
		</div>
		<div class="examOnline">
			<div>
				<font>有效截止日期：<c:out value="${epapers.sjYxqjzsj }"/></font>
			</div>
		</div>
		<div class="examOnline">
			<div>
				<input type="button" value="参加考试" onclick="opens()" />
			</div>
		</div> --%>
	</div>

</body>

</html>




