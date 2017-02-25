<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wondersgroup.falcon.paper.model.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css">
<HTML>
	<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>考试管理 试卷预览</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
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
<script type="text/javascript">
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
function retrieveBook1(spnid,typeid) {
    var paperid='<s:property value="paperid"/>'
	if(bXmlHttpSupport) {
       var sUrl = '<%=request.getContextPath() %>/findtypefenshu.action?typeId='+typeid+'&paperId='+paperid;
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

function retrieveBook(spnid,typeid) {
  var to_score=0.0;
  var single=document.getElementsByTagName("span");
  //alert(spnid+"  "+typeid+"  "+single.length);
  for(var i=0;i<single.length;i++){
   if(single[i].id.indexOf(typeid)>-1){
     to_score=to_score+parseFloat(single[i].innerHTML);
   }
  }
document.getElementById(spnid).innerHTML=parseFloat(to_score).toFixed(1);
}	


function goback(){
	window.location.href="<%=request.getContextPath() %>/papersServlet?actionType=query&sjZt=-1";
}
</script>
<script>
	var time=parseInt(60)
	var now1 = new Date();
	var m1 = now1.getMinutes()+(time-1);
	var s1 = now1.getSeconds()+(60);
	
	function getRemainTime(){
		var now = new Date();
		var m = now.getMinutes();
		var s = now.getSeconds();
		document.getElementById("remaintime").innerHTML=(m1-m)+":"+(s1-s);
		setTimeout("getRemainTime()",1000);
	}
	function count(){
	}
	
	function savePaper(){
		document.autoform.submit();
	}
	
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}
	function deleteSt(paperQuenId,paperid){
	    var tt=confirm("确定要删除吗？");  //确认是否删除
	    if(tt){
			window.location.href="delePaperQuestionAction.action?paperid="+paperid+"&paperQuenId="+paperQuenId;
		}
	}
	function addSt(paperId,questionTypeId){
		window.location.href="selectQuestionsAction?paperId="+paperId+"&typeId="+questionTypeId;
	}
	function validatetoadd(){
		var fenshuhas='<s:property value="fenshu"/>';
		var fenshu='<s:property value="pager.sjZf"/>';
		if(fenshuhas>=fenshu){
			return false;
		}
	}
	
	//word带答案导出	
	function jumpViewPaper(sjid){
		window.location.href="Paper_word.action?paperid="+sjid;
	}
	//标准word导出	
	function PaperStandard(sjid){
		window.location.href="FileDownload.action?paperid="+sjid;
	}
	function PaperAnswer(sjid){
		window.location.href="AnswerDownload.action?paperid="+sjid;
	}
	//word导出	
	function ViewPaper(sjid){
		window.location.href="Paper_out.action?paperid="+sjid;
	}

	function changeTocheck(sjid){
		window.location.href="changeState.action?paperid="+sjid+"&paperState=4";
	}

</script>
<BODY>
<form name="autoform" action="" method="post">
			<input type=hidden name="actionType" value="savepaper">
			<input type=hidden name="paperType" value="<c:out value="${pager.paperType}"/>">
			<input type=hidden name="toUserId" value="<c:out value="${pager.toUserId}"/>">
			<input type=hidden name="sjMc" value="<c:out value="${pager.sjMc}"/>">
			<input type=hidden name="model" value="<c:out value="${pager.model}"/>">
			<input type=hidden name="sjZf" value="<c:out value="${pager.sjZf}"/>">
			<input type=hidden name="sjDjsx" value="<c:out value="${pager.sjDjsx}"/>">
			<input type=hidden name="sjBhgfs" value="<c:out value="${pager.sjBhgfs}"/>">
			<input type=hidden name="Kksj" value='<fmt:formatDate value="${pager.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm"/>'>
			<input type=hidden name="Yxqjzsj" value='<fmt:formatDate value="${pager.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm"/>'>
			<input type=hidden name="Zjsj" value='<fmt:formatDate value="${pager.sjZjsj}" type="date" pattern="yyyy-MM-dd HH:mm"/>'>
			<input type=hidden name="sjZjrid" value="<c:out value="${pager.sjZjrid}"/>">
			<input type=hidden name="sjZt" value="0">
			<input type=hidden name="sjSyzt" value="0">
<div class="title title_yl"><h1><c:out value="${pager.sjMc}"/></h1></div>		
	<table width="98%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td>试卷名称 : </td>
            <td colspan="2"><c:out value="${pager.sjMc}"/></td>
            <td>试卷总分 : </td>
            <td colspan="2"><c:out value="${pager.sjZf}"/><%-- &nbsp;&nbsp;&nbsp;(已选试题的总分：<c:out value="${fenshu}"/>) --%></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td width="10%">答卷时限: </td>
            <td width="35%" colspan="2"><c:out value="${pager.sjDjsx}"/> 分钟</td>
            <td width="10%" >合格分数线: </td>
            <td width="35%" colspan="2" ><c:out value="${pager.sjBhgfs}"/></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>开考时间: </td>
            <td colspan="2"><fmt:formatDate value="${pager.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm"/></td><td >有效截止日期:</td>
            <td colspan="2" ><fmt:formatDate value="${pager.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
          </tr>
		<%-- <tr bgcolor="#FFFFFF">
            <td>考试模式: </td>
            <td colspan="5"><c:if test="${pager.model==1}">开卷</c:if><c:if test="${pager.model!=1}">闭卷</c:if> </td>
          </tr> --%>
	<tr bgcolor="#FFFFFF">
            <td width="10%">考试类型: </td>
            <td width="35%" colspan="2">
            	<c:if test="${pager.paperType=='1'}"><c:out value="鉴定类考试"></c:out></c:if>
            	<c:if test="${pager.paperType=='2'}"><c:out value="其他类考试"></c:out></c:if>
            	<%-- <c:otherwise><c:out value="其他类考试"></c:out></c:otherwise> --%>
            	<%-- <c:out value="${pager.paperType}"/> --%>
            </td>
            <td width="10%" >立即出分： </td>
            <td width="35%" colspan="2" > 
            	<c:if test="${pager.sjLjcf=='1'}"><c:out value="是"></c:out></c:if>
            	<c:if test="${pager.sjLjcf=='0'}"><c:out value="否"></c:out></c:if>
            </td>
          </tr>
	</table>  
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
			  <button class="btn_export"onclick="goback();">返回</button> 
		      <%-- <button class="btn_export"onclick="ViewPaper(<c:out value="${pager.sjId}"/>);">空试卷导出</button>
		      <button class="btn_export" onclick="jumpViewPaper(<c:out value="${pager.sjId}"/>);">word带答案导出</button>--%>
		      <%-- <a href="javascript:void(0)" class="btn_export" onclick="jumpViewPaper(<c:out value="${pager.sjId}"/>);">带答案word导出</a> --%>
		      <a href="javascript:void(0)" class="btn_export" onclick="PaperStandard(<c:out value="${pager.sjId}"/>);">标准word试题导出</a>
		      <a href="javascript:void(0)" class="btn_export" onclick="PaperAnswer(<c:out value="${pager.sjId}"/>);">单独答案导出</a>
			  <%-- <s:if test="pager.SjZt==0||pager.SjZt==6">
			  	<button class="btn_export"onclick="changeTocheck(<c:out value="${pager.sjId}"/>);">出卷完毕</button>
			  </s:if>  --%>	     
		    </td>
		  </tr>
		</table>
<%
String []arry=new String[]{"A","B","C","D","E","F","G","H","I","J"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二","十三"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);	
%>
<!--  
<s:iterator value="epaperquestions.{?#this.equestions.equestiontype.id==1}" id="epq">
	<s:property value="#epq.equestions.stTg" />
	<s:property value="#epq.equestions.equestiontype.id" />
	
</s:iterator>
-->
<%int zl=0; %>	
<div class="con_bg">

<s:iterator value="equestiontypes" id="type">
   <ul class="first_t">
    <li>
	<!--定义一个试题类型 -->
	<s:set name="priority" value="#type.priority"/>
	<!--将集合过滤，过滤成符合这个类型的 -->
	<s:set name="epaperquestions" value="epaperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
		<s:if test="#epaperquestions.size>0">
			<%zl++; %>
			<%=arry1[zl-1] %>、&nbsp;
			<s:property value="#type.name"/>&nbsp;
			（共<s:property value="#epaperquestions.size"/>题,
				共<SPAN id='<s:property value="#type.id"/>fenshuSpan'></SPAN>分）
		</s:if>										
				<s:url action="selectQuestionsAction.action" id="selectQuestion">
					<s:param name="typeId" value="priority"/>
					<s:param name="paperid" value="paperid"/>
				</s:url>
				<!-- 只有考试前的题目才能出题 -->
			<s:if test="pager.SjZt==0||pager.SjZt==6||pager.SjZt==4">
				<s:if test="fenshu<pager.sjZf">
					<s:a href="%{#selectQuestion}">增加该类型题目</s:a>
				</s:if>
			</s:if>	
		<!-- 遍历题目 -->
		<s:iterator value="#epaperquestions" status="index" id="epq">			
				<!-- 显示题干 -->
		<ul class="second_t"><li>
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#epq.equestions.stTg)" escape="false"/>&nbsp;&lt;<span id='<s:property value="#type.id"/>single'><s:property value="#epq.sjStfs" /></span>分&gt;
						&nbsp;&nbsp;&nbsp;
						<%-- <s:if test="#epq.equestions.stWh!=null"><span class="blues">&lt;<s:property value="#epq.equestions.stWh"/>&gt;</span></s:if>
						<s:else>&nbsp;</s:else> --%>
						<s:if test="#epq.equestions.stCc!=null"><span class="blues">&lt;<s:property value="#epq.equestions.stCc"/>&gt;</span></s:if>
						<s:else></s:else>
						<s:if test="#epq.equestions.stImg!=null">
							<ul style="height: 1px">
								<li style="height: 1px">
									<div style="position: relative;width: 100%">
								  		<div style="width: 300px;height:200px;float:right;right:10;top:1;position:absolute; z-index:100; ">
								  			<img class="imgPv" id="imgPv" src="<%=request.getContextPath()%>/servlet/ImgServlet?stid=<s:property value="#epq.equestions.stId"/>" />
										</div>
									</div>
								</li>
							</ul>
						</s:if>
					<%-- <% 
					//可以不这样写 用Struts2 标签得值
					EPapers p = (EPapers)request.getAttribute("epapers");
					if(p.getSjZt()==new Long(0).longValue()||p.getSjZt()==new Long(6).longValue()){%>
						<input type="button" class="btn_del" onclick='deleteSt(<s:property value="#epq.id"/>,<s:property value="paperid"/>);' value="删除"></input>
				    <%} %> --%>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<ul class="third_t third_sy">
				<s:if test="%{#priority==2  ||  #priority==8}">
					<s:if test="#epq.equestions.stImgA==null">
						<s:generator separator="||" val="#epq.equestions.stXx" id="querson">
						<s:iterator status="wenti">
	                       <li><s:property value="#request.arry[#wenti.index]" /> .
								 	<s:property /></li>	
						</s:iterator>
						</s:generator>
					</s:if>
					<s:else>
						<s:generator val="'A||B||C||D'" separator="||" id="querson">
							<s:iterator status="wenti">
								<li>
		                       		<s:property value="#request.arry[#wenti.index]" /> .
									<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=<s:property value="#request.arry[#wenti.index]" />"/>
							   	</li>
							</s:iterator>
						</s:generator>
						<s:if test="#epq.equestions.stImgE!=null">
							<li>
	                       		E .
								<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=E"/>
						   	</li>
						</s:if>
						<s:if test="#epq.equestions.stImgF!=null">
							<li>
	                       		F .
								<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=F"/>
						   	</li>
						</s:if>
						<s:if test="#epq.equestions.stImgG!=null">
							<li>
	                       		G .
								<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#epq.equestions.stId" />&xx=G"/>
						   	</li>
						</s:if>
					</s:else>
					<!-- 答案 -->
					<!-- <li style="height: 60px"></li> -->
					<li class="answer" style="width: 500px"><dl><dt style="float: left;">
								标准答案：
									<s:generator separator="||" val="#epq.equestions.stDa">
										<s:iterator>
											<s:property/>、
										</s:iterator>
									</s:generator>
						</dt></dl></li>
					
				</s:if>
				</ul>
				<!-- 如果是判断题 -->
				<!-- 答案 -->
				<ul class="third_t third_sy">
				<s:elseif test="#priority==3" >
					 <li class="answer" style="width: 300px;"><dl><dt style="float: left;">
								标准答案：
								
									<s:if test='#epq.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else>	
					</dt></dl></li>
				</s:elseif>
				</ul>
				
				<ul class="third_t third_sy">
				<s:elseif test="#priority==14" >
					<div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF;line-height:23px; font-size:12px; padding:0 5px; color:#555;">
								标准答案：
									<s:if test='#epq.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else>
					<br>
								答题说明：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#epq.equestions.stDasm)" escape="false"/></div>
				</s:elseif>
				</ul>
				
				<ul class="third_t third_sy">
				<s:elseif test="#priority==15">
							<li>
									<object align="middle"
										classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id='MediaPlayer<s:property value="#epq.equestions.stId"/>'
										width="400" height="69">
										<param name="ShowStatusBar" value="-1">
										<param name="AutoStart" value="0">
										<param name="Filename" value='<s:property value="##epq.equestions.stFjlj"/>'>
										<embed type="application/x-oleobject"
											codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
											flename="mp" src="" width=200 height=50></embed>
									</object>
							</li>
						   <div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF;line-height:23px; font-size:12px; padding:0 5px; color:#555;">
								标准答案：
									<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#epq.equestions.stDa)" escape="false"/>
						                  </div>

				</s:elseif>
				</ul>
				<ul class="third_t third_sy">
				<%-- <s:else>
					 <div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF;line-height:23px; font-size:12px; padding:0 5px; color:#555;">标准答案：
									<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#epq.equestions.stDa)" escape="false"/>
								 </div>
				</s:else> --%>
				<s:else>
					 <li class="answer" style="width: 500px">
					 	<dl>
						 	<dt style="float: left;">
							 	标准答案：
								<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#epq.equestions.stDa)" escape="false"/>
						 	</dt>
					 	</dl>
					 </li>
				</s:else>
				</ul>
	</li></ul>
	<s:if test="#epq.equestions.stImg!=null">
		<s:if test="%{#priority==2  ||  #priority==8}">
			<s:if test="#epq.equestions.stImgE==null">
				<ul style="height: 50px">
					<li style="height: 50px"></li>
				</ul>
			</s:if>
		</s:if>
		<s:elseif test="#priority==3">
			<ul style="height: 150px">
					<li style="height: 150px"></li>
				</ul>
		</s:elseif>
		<s:else>
			<ul style="height: 150px">
				<li style="height: 150px"></li>
			</ul>
		</s:else>
	</s:if>
		</s:iterator>	
		<script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>single');</script>
</li></ul>
</s:iterator>
</div>		
		</form>
	</BODY>
	
</HTML>
		