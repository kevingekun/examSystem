<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>考生考试</TITLE>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.11.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</HEAD>
<style type="text/css">
.img_xx{
	height: 40px;
	width: 300px;
}
.imgPV{
	height: 200px;
	width: 300px;
}
</style>
<%
	//设置值用来屏蔽菜单
	request.getSession().setAttribute("Flag", "1");
	Long surplus = Long.valueOf((String) request
			.getAttribute("surplus"));//剩余时间
	EPapers p = (EPapers) request.getAttribute("epapers");
	//System.out.println("==========="+p.getSjDjsx());
%>

<%
	String[] arry = new String[]{"A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J"};
	String[] arry1 = new String[]{"一", "二", "三", "四", "五", "六", "七",
			"八", "九", "十", "十一", "十二"};
	request.setAttribute("arry", arry);
	request.setAttribute("arry1", arry1);

	Date st = new Date();
	session.setAttribute("st", st);
	/* if (session.getAttribute("st") == null) {
		session.setAttribute("st", st);
	} */
%>
		<script defer="defer">
		
  
      <%--  var sUrl = '<%=request.getContextPath()%>/pingip.action?pingaddress='+paperid;
       var oRequest = new XMLHttpRequest();
       oRequest.open('POST', sUrl);
       oRequest.send(null);
       oRequest.onreadystatechange = function() {
			var dataObj=oRequest.responseText;//
			var obj1 = eval(dataObj);
			if(obj1.length>0){
				for (var i=0;i<obj1.length;i++)
				{
				//判断是否为判断或者单选题
					if(obj1[i].questiontype==2||obj1[i].questiontype==8||obj1[i].questiontype==3){
				 		var obj = document.getElementsByName("answer"+obj1[i].questionid);
				 		var id = document.getElementById("answer"+obj1[i].questionid+"span").title;
				 		checkArray.push(id);
				  		for(var j = 0; j < obj.length; j++) {
						  	var ans = new Array();
						  	ans = obj1[i].answer.split("||");
						  	for(var k = 0;k<ans.length;k++){
							  if(obj[j].value == ans[k]){
								  obj[j].checked = true;
							  }
						  	}
						      /* if(obj[j].value == obj1[i].answer)
						      {  
						        obj[j].checked = true;
						      }   */
						 }
					}else{
						document.getElementsByName("answer"+obj1[i].questionid)[0].value=obj1[i].answer;
					}
				}
       		}
       } --%>
       
</script>
<script>

var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) { /*ignore*/ }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
} 
	var endtime = <%=((Date) session.getAttribute("st")).getTime()%> + (<%=surplus%>)*60*1000;
	var bxjjtime=<%=p.getSjBhgfs()%>*60*1000 -<%=p.getSjJjsj()%>*60*1000;
	var timeid;
	var jjtime=<%=p.getSjJjsj()%>;//多少分钟可以交卷
    var currenttime;
	var timems=<%=surplus%>*60*1000;
	var sytime;//剩余时间，定时更新 全局变量
	
	var leftTime = true;//剩余五分钟后只提醒一次
    
	function getCurrentTime(){
		var currenttime = new Date();	
		 //var timems=<%=p.getSjDjsx()%>*60*1000;
		 //var timems = endtime-currenttime.getTime();
		 timems = timems-1000;
		 sytime = timems;
		  if(timems>=298000&&timems<=301000&&leftTime==true){
		 	alert("考试时间还剩5分钟！！");
		 	leftTime=false;
		 }
		 //alert(timems);
		if(timems<=0){
			var submittype=document.getElementById("submittype").value;
			if(submittype==0){
				autoSubmit();
				window.clearTimeout();
				document.getElementById("submittype").value="1";
			}
		} 
		var timeh = (Math.floor(timems/(1000*60*60))%24)<=0?0:(Math.floor(timems/(1000*60*60))%24);
		if(timeh/10<1){
			timeh="0"+timeh
		}
		var timem = (Math.floor(timems/(1000*60))%60)<=0?0:(Math.floor(timems/(1000*60))%60);
		if(timem/10<1){
			timem="0"+timem
		}
		var times = (Math.floor(timems/1000)%60)<=0?0:(Math.floor(timems/1000)%60);
		if(times/10<1){
			times="0"+times
		}
		document.getElementById("timeh").innerHTML=timeh+":";
		document.getElementById("timem").innerHTML=timem+":";		
		document.getElementById("times").innerHTML=times;		
		//alert(timeh+":"+timem+":"+times);
		parentjs(timeh,timem,times);
		setTimeout("getCurrentTime()",1000);
	}

	
	function changeTopage(){
		//currenttime='<%=new java.util.Date().getTime()%>';
		var currenttime = new Date();	
		//timems = endtime-currenttime.getTime();
		timems = <%=surplus%>*60*1000;
		getCurrentTime();
		updateSurplus();
		remove_loading();
		parent.parent.topFrame.location.reload();
		
		//判断是否网络中断 
		  var paperid='<s:property value="paperId"/>';
		  var checkArray = new Array();
		  $.ajax({
			  type: 'post',
			  async: false,
			  url: 'pingip.action?pingaddress='+paperid,
			  success:function(dataObj){
				  var obj1 = new Array();
				  obj1 = eval(dataObj);
				  //alert(obj1);
					if(obj1.length>0){
						for (var i=0;i<obj1.length;i++)
						{
						//判断是否为判断或者单选题
							if(obj1[i].questiontype==2||obj1[i].questiontype==8||obj1[i].questiontype==3){
						 		var obj = document.getElementsByName("answer"+obj1[i].questionid);
						 		//var id = document.getElementById("answer"+obj1[i].questionid+"span").title;
						 		if(obj1[i].answer!=""){
						 			var id = $("#answer"+obj1[i].questionid+"span").attr('title');
							 		checkArray.push(id);
						 		}
						  		for(var j = 0; j < obj.length; j++) {
								  	var ans = new Array();
								  	ans = obj1[i].answer.split("||");
								  	for(var k = 0;k<ans.length;k++){
									  if(obj[j].value == ans[k]){
										  obj[j].checked = true;
									  }
								  	}
								}
							}else{
								document.getElementsByName("answer"+obj1[i].questionid)[0].value=obj1[i].answer;
							}
						}
		     		}
			  },
			  error:function(){
				  alert("error");
			  }
		  });
		
		var total = document.getElementById("total").value;
		parent.menu.drawtable(total,checkArray);
	}
	
	function savebeforeunload(){
		if(window.confirm("您确定要离开考试吗？")){
			autoSubmit();
		}
	}
	
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}	
function retrieveBook(spnid,typeid) {
    var paperid='<s:property value="paperId"/>'
	if(bXmlHttpSupport) {
       var sUrl = '<%=request.getContextPath()%>/findtypefenshu.action?typeId='+typeid+'&paperId='+paperid;
       var oRequest = new XMLHttpRequest();
       oRequest.onreadystatechange = function() {
       		if(oRequest.readyState == 4) {
       			var paperfenshu = eval('(' + oRequest.responseText + ')');
       			var spa=document.getElementById(spnid)
       			spa.innerHTML=paperfenshu;
       		}
       }
       oRequest.open('POST', sUrl);
       oRequest.send(null);
    }
}	
function delete_login(){
window.location = "delete_login.jsp" ;
}
function loadto(index){
	 window.location.hash = "#"+index;
}
//定时更新时间
var submittimes="1";
function updateSurplus() {
    var paperid='<s:property value="paperId"/>'
    var currenttime = new Date();	
	 //var timems = endtime-currenttime.getTime();
	 //var timems=<%=surplus%>*60*1000;
	 sytime = timems;
       var sUrl = '<%=request.getContextPath()%>/updatesurplus.action?paperId='+paperid+'&times='+timems;
       var oRequest = new XMLHttpRequest();
       oRequest.onreadystatechange = function() {
       		if(oRequest.readyState == 4) {
       			var paperfenshu = eval('(' + oRequest.responseText + ')');
       			if(paperfenshu==1){
       				var submittype=document.getElementById("submittype").value;
       				if(submittype=="0"){
	       				alert("由于发现本次考试作弊，将退出本次考试");
	       				logout();
       			 		document.getElementById("submittype").value="1";
       				}
       			}
       		}
       }
       oRequest.open('POST', sUrl);
       oRequest.send(null);
    setTimeout("updateSurplus()",10000);
}
function logout(){
	 autoSubmit();
	window.clearTimeout();
			 
}
function parentjs(h,m,s){
	//parent.parent.topFrame.startkaoshi(h,m,s);
	//换成jquery.1.11.3.min.js可以使用如下方法
	$('#timeh',window.parent.parent.frames["topFrame"].document).text(h+":");
	$('#timem',window.parent.parent.frames["topFrame"].document).text(m+":");
	$('#times',window.parent.parent.frames["topFrame"].document).text(s);
}

</script>
<SCRIPT language=JavaScript>
var t_id = setInterval(animate,20);
function animate()
{
  //
}
function remove_loading() {
this.clearInterval(t_id);
var targelem = document.getElementById('loader_container');
targelem.style.display='none';
targelem.style.visibility='hidden';
}
</SCRIPT>
<script language="javascript" type="text/javascript">
function showdiv() {  
            document.getElementById("bg").style.display ="block";
            document.getElementById("show").style.display ="block";
        }
function hidediv() {
            document.getElementById("bg").style.display ='none';
            document.getElementById("show").style.display ='none';
        }
</script>
<style type="text/css">
#bg {
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 150%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.7;
	opacity: .70;
	filter: alpha(opacity =   70);
}

#show {
	position: absolute;
	top: 25%;
	left: 22%;
	width: 53%;
	height: 49%;
	padding: 8px;
	border: 8px solid #E8E9F7;
	font-size:20px;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
<BODY style="font-size: 12px; MARGIN: 5px;" onLoad="changeTopage()" onpaste="javascript:if('<s:property value="epapers.model"/>'=='1'){return true;}else{return false;}" onbeforeunload="javascript:if(document.all.aa.value == 0){return '';}">
	<DIV id=loader_container align=left>
		<DIV id=loader>
			<DIV align=left>
				<img src="<%=request.getContextPath()%>/faq/images/loading.gif" align="absmiddle">试卷加载中 ...
			</DIV>
		</DIV>
	</DIV>
	<DIV>
		<div id="bg" style="display: none;"></div>
		<div id="show" style="display: none;">系统正在提交当前答题，请耐心等待..</div>
		<div><input id="total" value='<s:property value="all"/>' type="hidden"/></div>
		<input type="hidden" name="aa" value="0" />
		<IFRAME NAME="uploadfrm" id="uploadfrm" src="" STYLE="HEIGHT: 0; LEFT: 0px; MARGIN-TOP: 0px; WIDTH: 0; SCROLL: no;" frameborder=0></IFRAME>

		<s:form name="paperSubmitform" action="exampaperActioncommit" method="post" onsubmit="return submitForm();">
			<s:token></s:token>
			<s:hidden name="paperId" />
			<input type="hidden" name="jssj" value="<%=((Date) session.getAttribute("st")).getTime()%>" />
			<input type="hidden" name="submittype" id="submittype" value="0" />
		</s:form>
			<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top: 10px; margin-left: 8px;">
				<tr>
					<td width="45%" align="left">
						<table border="0" align="left" cellpadding="0" cellspacing="0">
							<tr>
								<td align="left" valign="middle" class="header1"></td>
								<td class="header2">
									<a id="1001">在线考试</a>
								</td>
								<td class="header3"></td>
							</tr>
						</table>
					</td>
					<td width="53%" align="left"></td>
				</tr>
				<tr>
					<td colspan="2" valign="top">
						<div id="content1" class="borader">
							<div class="con_bg">
								<div class="title" style="width: 100%;text-align: center;">
									<div>
										<h1>
											<s:property value="epapers.sjMc" />
										</h1>
									</div>
									<div style="width: 100%;">
										<ul style="width: 100%;margin-left: 12%">
											<li>考试模式： <s:if test="epapers.model==1">开卷</s:if> <s:elseif test="epapers.model!='1'">闭卷</s:elseif></li>
											<li>试卷分数： <s:property value="epapers.sjZf" /> 分</li>
											<li>考试时间： <s:property value="epapers.sjDjsx" /> 分钟</li>
											<li>参考人员： <s:property value="user.realname" /></li>
											<li>剩余时间： 
													  <font id="remaintime"> 
														<font id="timeh" color="#0000ff"></font> 
														<font id="timem" color="#0000ff"></font> 
														<font id="times" color="#0000ff"></font>
													  </font>
											</li>
										</ul>
									</div>
								</div>
								<ul class="first_t">
									<li>
										<%
											int zl = 0;
											int num = 1;
										%>
										<s:iterator value="equestiontypes" id="type">
											<!--定义一个试题类型 -->
											<s:set name="priority" value="#type.priority" />
											<!--将集合过滤，过滤成符合这个类型的 -->
											<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}" />
											<!--判断过滤后的集合不为空 -->
											<s:if test="#epaperquestions.size!=0">
												<%
													zl++;
												%>
												<%=arry1[zl - 1]%>、&nbsp;<s:property value="#type.name" />&nbsp;（共<s:property value="#epaperquestions.size" />题,共
													<SPAN id='<s:property value="#type.id"/>fenshuSpan'><script type="text/javascript">
													retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>');
													</script> </SPAN>分）
												<ul class="second_t">
													<li>
														<!-- 遍历题目 --> 
														<s:iterator value="#epaperquestions" status="index" id="epq">
															<!-- 显示题干 -->
															<%-- <s:property value="#index.index+1" /> --%>
															<a id="<%=num %>" style="font-size:20px;"><%=num %></a>
															&nbsp;.&nbsp;<span id='<s:property value="%{'answer'+#epq.equestions.stId}"/>span'  title='<%=num%>' style="color: black"><s:property value="#epq.equestions.stTg" /> </span>&nbsp;&lt;<s:property value="#epq.sjStfs" />分&gt;
						<!--<s:if test="#epq.equestions.stWh!=null"><span class="blues">&lt;<s:property value="#epq.equestions.stWh"/>&gt;</span></s:if>
						<s:else>&nbsp;</s:else>
						<s:if test="#epq.equestions.stCc!=null"><span class="blues">&lt;<s:property value="#epq.equestions.stCc"/>&gt;</span></s:if>
						<s:else></s:else>-->
															<s:if test="#epq.equestions.stImg!=null">
																<ul style="height: 1px">
																	<li style="height: 1px">
																		<div style="position: relative;width: 100%">
																	  		<div style="width: 300px;height:200px;float:right; z-index:100; ">
																	  			<img class="imgPV" id="imgPv" src="<%=request.getContextPath()%>/servlet/ImgServlet?stid=<s:property value="#epq.equestions.stId"/>" />
																			</div>
																		</div>
																	</li>
																</ul>
															</s:if>
															<ul class="third_t">
																<!-- 如果是单选题或多选题则要把试题分割显示 -->
																<s:if test="%{#priority==2  ||  #priority==8}">
																<s:if test="#epq.equestions.stImgA==null">
																	<s:generator separator="||" val="#epq.equestions.stXx" id="querson">
																	<%
																		int k = 1;
																	%>
																		<s:iterator status="wenti">
																				<li>
																					<input id="<%=num%>-<%=k %>" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="<s:if test="%{#priority==2}">radio</s:if><s:elseif test="%{#priority==8}">checkbox</s:elseif>"
																						onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','<s:property value="#request.arry[#wenti.index]"/>','<%=num %>');" 
																						value='<s:property value="#request.arry[#wenti.index]"/>'/>&nbsp;
																					<label for="<%=num%>-<%=k %>">
																						<s:property value="#request.arry[#wenti.index]" /> .<s:property />
																					</label>
																				</li>
																	<%
																		k++;
																	%>
																		</s:iterator>
																	</s:generator>
																	</s:if>
																	<s:else>
																		<s:generator val="'A||B||C||D'" separator="||" id="querson">
																		<%
																			int t = 1;
																		%>
																			<s:iterator status="wenti">
																				<li>
														                       		<input id="<%=num%>-<%=t %>" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="<s:if test="%{#priority==2}">radio</s:if><s:elseif test="%{#priority==8}">checkbox</s:elseif>"
																						onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','<s:property value="#request.arry[#wenti.index]"/>','<%=num %>');" 
																						value='<s:property value="#request.arry[#wenti.index]"/>'/>&nbsp;
																					<label for="<%=num%>-<%=t %>">
																						<s:property value="#request.arry[#wenti.index]" /> .
																						<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=<s:property value="#request.arry[#wenti.index]" />"/>
																					</label>
																			   	</li>
																			   	<%
																					t++;
																				%>
																			</s:iterator>
																		
																		</s:generator>
																		<s:if test="#epq.equestions.stImgE!=null">
																			<li>
													                       		<input id="<%=num%>-5" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="<s:if test="%{#priority==2}">radio</s:if><s:elseif test="%{#priority==8}">checkbox</s:elseif>"
																					onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','<s:property value="#request.arry[#wenti.index]"/>','<%=num %>');" 
																					value="E"/>&nbsp;
																				<label for="<%=num%>-5">
																					E .<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=E"/>
																				</label>
																		   	</li>
																		</s:if>
																		<s:if test="#epq.equestions.stImgF!=null">
																			<li>
													                       		<input id="<%=num%>-6" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="<s:if test="%{#priority==2}">radio</s:if><s:elseif test="%{#priority==8}">checkbox</s:elseif>"
																					onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','<s:property value="#request.arry[#wenti.index]"/>','<%=num %>');" 
																					value="F"/>&nbsp;
																				<label for="<%=num%>-6">
																					F .<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=F"/>
																				</label>
																		   	</li>
																		</s:if>
																		<s:if test="#epq.equestions.stImgG!=null">
																			<li>
													                       		<input id="<%=num%>-7" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="<s:if test="%{#priority==2}">radio</s:if><s:elseif test="%{#priority==8}">checkbox</s:elseif>"
																					onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','<s:property value="#request.arry[#wenti.index]"/>','<%=num %>');" 
																					value="G"/>&nbsp;
																				<label for="<%=num%>-7">
																					G .<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=G"/>
																				</label>
																		   	</li>
																		</s:if>
																	</s:else>
																</s:if>
																<!-- 如果是判断题 -->
																<s:elseif test="#priority==3">
																	<li>
																		<input id="<%=num %>-1" type="radio" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','T','<%=num %>');" value="T"/> <label for="<%=num%>-1">正确</label>
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	 	<input id="<%=num %>-2" type="radio" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','F','<%=num %>');" value="F"/> <label for="<%=num%>-2">错误</label>
																	</li>
																</s:elseif>
																<!-- 如果是判断题说明题 -->
																<s:elseif test="#priority==4">
																	<li>
																		<input id="<%=num %>-1" type="radio" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' onclick="changeColor(this,'<s:property value="%{this,'answer'+#epq.equestions.stId}"/>span','1','T','<%=num %>');" value="T"/> <label for="<%=num%>-1">正确</label>
																		<input id="<%=num %>-2" type="radio" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' onclick="changeColor(this,'<s:property value="%{this,'answer'+#epq.equestions.stId}"/>span','1','F','<%=num %>');" value="F"/> <label for="<%=num%>-2">错误</label>
																	</li>
																	<li>&nbsp;说明：</li>
																	<li>&nbsp; <textarea name='<s:property value="%{'answer'+#epq.equestions.stId}"/>sm' rows="5" style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','','<%=num %>');"></textarea>
																	</li>
																</s:elseif>
																<!-- 如果是录音题 -->
																<s:elseif test="#priority==5">
																	<li><object align="middle" classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id='MediaPlayer<s:property value="#epq.equestions.stId"/>' width="400" height="69">
																			<param name="ShowStatusBar" value="-1">
																			<param name="AutoStart" value="0">
																			<param name="Filename" value='<s:property value="#epq.equestions.stFjlj"/>'>
																			<embed type="application/x-oleobject" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" flename="mp" src="" width=200 height=50></embed>
																		</object></li>
																	<li><textarea name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' rows="5" style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','','<%=num %>');"></textarea></li>
																</s:elseif>
																<!-- 如果是问答题 -->
																<s:elseif test="#priority==4 || #priority==5 || #priority==6||#priority==9||#priority==11||#priority=13">
																	<li>&nbsp;回答：</li>
																	<li>&nbsp; <textarea name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' rows="5" style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','','<%=num %>');"></textarea>
																	</li>
																</s:elseif>
																<!-- 点库题 -->
																<s:elseif test="#priority==12">
																	<li>&nbsp;回答：</li>
																	<li>&nbsp; <textarea onpropertychange="if(value.length>220) value=value.substr(0,220)" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' rows="5" style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>','','<%=num %>');"></textarea> <span style="text-algin:right">(限制220字符)</span>
																	</li>
																</s:elseif>
															</ul>
															<%num++; %>
															<s:if test="#epq.equestions.stImg!=null">
																<s:if test="%{#priority==2  ||  #priority==8}">
																	<s:if test="#epq.equestions.stImgE==null">
																		<ul style="height: 100px">
																			<li style="height: 100px"></li>
																		</ul>
																	</s:if>
																</s:if>
																<s:elseif test="#priority==3">
																	<ul style="height: 175px">
																			<li style="height: 175px"></li>
																		</ul>
																</s:elseif>
															</s:if>
														</s:iterator>
													</li>
												</ul>
											</s:if>
										</s:iterator>

										<ul>
											<li style="text-align: center;">
												<!-- <a id="btn_age" class="btn_exam" href="javascript:void(0)" onclick="submitForm()">提交答卷</a> -->
												 <input id="btn_submit" type="button" class="submit_2" value="提交答卷" onclick="submitForm()" style="margin-left: auto;margin-right: auto;">
											</li>
										</ul>
										<ul style="height:20px;"></ul>
									</li>
								</ul>
							</div>
						</div>
					</td>
				</tr>

			</table>
			<!--表格结束-->
		


		<script type="text/javascript">

function submitForm(){
 		var currenttime = new Date();	
		 //var timems=<%=p.getSjDjsx()%>*60*1000;
		 var timems = endtime-currenttime.getTime();
		 
		 var surplusTime = sytime/60/1000;//考试剩余时间
		 var djtime = <%=p.getSjDjsx()%>;//本次考试持续时间
		 var t = djtime-surplusTime;
		 if(t<jjtime){
			 alert("开始考试"+jjtime+"分钟后才可以交卷，请耐心答题！");
		 }
		 <%-- if(bxjjtime<timems){
			//alert(bxjjtime+"-----"+timems);
			alert('<%=(Date)session.getAttribute("st")%>');
		 	alert("开始考试"+jjtime+"分钟后才可以交卷，请耐心答题！");
			 
		 } --%>
		 else {
//试题id
var stid='';
//试题答案
var daan='';
//提示
var al='';
//是否答题
var boo=false;
var wwcsl=0;
<s:iterator value="equestiontypes" id="type">
	<s:set name="priority" value="#type.priority"/>
	<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
	<s:if test="#epaperquestions.size!=0">
	    <s:iterator value="epaperquestions" id="epaperquestion">
	    	stid='<s:property value="#epaperquestion.equestions.stId"/>';
	    	daan=document.getElementsByName('answer'+stid);
	    	<s:if test="%{#priority==2 || #priority==3 || #priority==8}">
	    		for(var i=0;i<daan.length;i++){
	    			if(daan[i].checked){
	    				boo=true;
	    			}
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    	<s:if test="%{ #priority==1 || #priority==4 || #priority==5|| #priority==6|| #priority==7}">
	    		if(daan[0].value.length!=0){
	    			boo=true;
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    </s:iterator>
    </s:if>
</s:iterator>
if(wwcsl!=0){
	alert('您有'+wwcsl+'道题目没有完成！');
	if(window.confirm("确定提交？")){
		document.all.aa.value = '1';
		showdiv();
		loadto(1001);
		$("#btn_submit").attr('onclick','');
		document.paperSubmitform.submit();
	}else{
		 
	}
}else  if(window.confirm("确定提交？")){
	document.all.aa.value = '1';
	showdiv();
	loadto(1001);
	$("#btn_submit").attr('onclick','');
	document.paperSubmitform.submit();
}else{
	 
}
}
}

function autoSubmit(){
	//试题id
	
var stid='';
//试题答案
var daan='';
//提示
var al='';
//是否答题
var boo=false;
var wwcsl=0;
<s:iterator value="equestiontypes" id="type">
	<s:set name="priority" value="#type.priority"/>
	<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
	<s:if test="#epaperquestions.size!=0">
	    <s:iterator value="epaperquestions" id="epaperquestion">
	    	stid='<s:property value="#epaperquestion.equestions.stId"/>';
	    	daan=document.getElementsByName('answer'+stid);
	    	<s:if test="%{#priority==2 || #priority==3 || #priority==8}">
	    		for(var i=0;i<daan.length;i++){
	    			if(daan[i].checked){
	    				boo=true;
	    			}
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    	<s:if test="%{ #priority==1 || #priority==4 || #priority==5|| #priority==6|| #priority==7}">
	    		if(daan[0].value.length!=0){
	    			boo=true;
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    </s:iterator>
    </s:if>
</s:iterator>
if(wwcsl!=0){
	//alert('时间结束，您有'+wwcsl+'道题目没有完成！系统自动提交。');
	document.all.aa.value = '1';
	showdiv();
	loadto(1001);
	$("#btn_submit").attr('onclick','');
	document.paperSubmitform.submit();
}else{
	//alert("系统自动提交！");
    document.all.aa.value = '1';
    showdiv();
    loadto(1001);
    $("#btn_submit").attr('onclick','');
	document.paperSubmitform.submit();
}

}
/**做过题以后改变颜色*/
function changeColor(mid,id,priority,answer,index){
	window.parent.frames["menu"].dochange(index);//左边菜单栏显示已做的题
	//alert(mid.name+"--"+id+"--"+priority+"--"+answer+"--"+index);
		answertemp(mid,id,priority,answer);
	if(priority=='2'||priority=='3'){
		document.getElementById(id).style.color='#03C'; //1是单选题或者是判断题
	}else if(priority=='8'||priority=='10'){
		document.getElementById(id).style.color='#03C';
		var tagName=document.getElementsByName(mid.name);
		var booleanflag=false;
		for(var i=0;i<tagName.length;i++){
			if(tagName[i].checked){
				booleanflag=true;
			}
		}
		if(booleanflag){document.getElementById(id).style.color='#03C';
		 }else{
			document.getElementById(id).style.color='black';
			window.parent.frames["menu"].undochange(index);
		}
	}else{
		smLength=mid.value.length;
		if(smLength>0){
			document.getElementById(id).style.color='#03C';
		}else{
			document.getElementById(id).style.color='black';
		}
	}
}
  //数据保存临时表
  function answertemp(mid,id,priority,answer){
	  var paperid='<s:property value="paperId"/>'
	  if(priority==1||priority==4||priority==5||priority==6){
	  	answer=encodeURI(encodeURI(document.getElementsByName(mid.name)[0].value));
	  }
	  if(priority==8){
		  answer = "";
		  var box = document.getElementsByName(mid.name);
		  for(var i=0;i<box.length;i++){
			  if(box[i].checked==true){
				  answer += box[i].value+"||";
			  }
		  }
		  answer = answer.substr(0,answer.length-2);
	  }
	  var qid=mid.name;
	  var questionid=qid.substring(6, qid.length);
      var sUrl = '<%=request.getContextPath()%>/addanswertemp.action?answer='+answer+'&shijuanid='+paperid+'&questionid='+questionid+'&questiontype='+priority;
      var oRequest = new XMLHttpRequest();
      oRequest.onreadystatechange = function() {
      		if(oRequest.readyState == 4){
      			if(oRequest.status==0){
	      			alert("网络异常，请马上联系监考老师！");
      			}
      		}
      }
      oRequest.open('POST', sUrl);
      oRequest.send(null);
  }
  </script>
</DIV>
</BODY>
</HTML>
