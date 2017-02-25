<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD> 
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<TITLE>New Document</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
	</HEAD>
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
function retrieveBook(spnid,typeid) {
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
		window.location.href="delePaperQuestionAction.action?paperid="+paperid+"&paperQuenId="+paperQuenId;
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
</script>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">试卷名称：<c:out value="${pager.sjMc}"/></td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">

<form name="autoform" action="" method="post">
			<input type=hidden name="actionType" value="savepaper">
			<input type=hidden name="sjMc" value="<c:out value="${pager.sjMc}"/>">
			<input type=hidden name="sjZf" value="<c:out value="${pager.sjZf}"/>">
			<input type=hidden name="sjDjsx" value="<c:out value="${pager.sjDjsx}"/>">
			<input type=hidden name="sjBhgfs" value="<c:out value="${pager.sjBhgfs}"/>">
			<input type=hidden name="Kksj" value='<fmt:formatDate value="${pager.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm"/>'>
			<input type=hidden name="Yxqjzsj" value='<fmt:formatDate value="${pager.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm"/>'>
			<input type=hidden name="Zjsj" value='<fmt:formatDate value="${pager.sjZjsj}" type="date" pattern="yyyy-MM-dd HH:mm"/>'>
			<input type=hidden name="sjZjrid" value="<c:out value="${pager.sjZjrid}"/>">
			<input type=hidden name="sjZt" value="0">
			<input type=hidden name="sjSyzt" value="0">
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">试卷名称：</td>
			          <td width="20%"><c:out value="${pager.sjMc}"/></td>
			          <td width="12%" align="right">试卷总分：</td>
			          <td width="20%"><c:out value="${pager.sjZf}"/>&nbsp;&nbsp;&nbsp;(已选试题的总分：<c:out value="${fenshu}"/>)</td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">答卷时限：</td>
			          <td width="20%"><c:out value="${pager.sjDjsx}"/> 分钟</td>
			          <td width="12%" align="right">不合格分数线：</td>
			          <td width="20%"><c:out value="${pager.sjBhgfs}"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>
			     <tr class="row_height">
			          <td width="18%">&nbsp;</td>
			          <td width="15%" height="26" align="right">开考时间：</td>
			          <td width="20%"><fmt:formatDate value="${pager.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
			          <td width="12%" align="right">有效截止时间：</td>
			          <td width="20%"><fmt:formatDate value="${pager.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
			          <td width="15%">&nbsp;</td>
			     </tr>          			
         <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="40" align="center" valign="bottom">
                      <input  onclick="window.history.go(-1);" class="submit_2"  value="返 回"/>			
                    </td>
                  </tr>
          </table>
    </table>	
</form>    
<%
String []arry=new String[]{"A","B","C","D","E","F"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);	
%>

<%int zl=0; %>	
<s:iterator value="equestiontypes" id="type">
	<!--定义一个试题类型 -->
	<s:set name="priority" value="#type.priority"/>
	<!--将集合过滤，过滤成符合这个类型的 -->
	<s:set name="epaperquestions" value="epaperquestions.{?#this.equestions.equestiontype.id==#priority}"/>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#3a86c4"> 
			<tr > 
				<td width="11px"></td>
				<td width='100%' style='color:white'>
				<%zl++; %>
				【<%=arry1[zl-1] %>】.&nbsp;<s:property value="#type.name"/>&nbsp;（共<s:property value="#epaperquestions.size"/>题,共<SPAN id='<s:property value="#type.id"/>fenshuSpan'><script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>');</script></SPAN>分）
				<s:url action="selectQuestionsAction.action" id="selectQuestion">
					<s:param name="typeId" value="priority"/>
					<s:param name="paperid" value="paperid"/>
				</s:url>
				<s:if test="fenshu<pager.sjZf">
					<s:a  href="%{#selectQuestion}">增加该类型题目</s:a>
				</s:if>
				
				</td>
				<!--  <td width='30px' nowrap>&lt;&gt;</td>-->
			</tr>
		</table>
		<!-- 遍历题目 -->
		<s:iterator value="#epaperquestions" status="index" id="epq">
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<!-- 显示题干 -->
				<tr>
					<td width="100%" height="30"  bgcolor="#cccccc">
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="#epq.equestions.stTg"/>&nbsp;&lt;<s:property value="#epq.sjStfs"/>分&gt;
					</td>
					<td bgcolor="#cccccc" align="right"><button class="BigButton" onclick='deleteSt(<s:property value="#epq.id"/>,<s:property value="paperid"/>);'>删除</button></td>
				</tr>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="#epq.equestions.stXx" id="querson">
					<s:iterator status="wenti">
						<tr bgcolor='#ffffff'>
							<td  width='100%'  height='30px' colspan="2">
							 	&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 	<s:property />	<br> 
							</td>
						</tr>
					</s:iterator>
					</s:generator>
					<!-- 答案 -->
					<tr>
						<td colspan='2'>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> <tr><td colspan='2'>
								<font color='blue'>&nbsp;★标准答案：
									<s:generator separator="||" val="#epq.equestions.stDa">
										<s:iterator>
											<s:property/>、
										</s:iterator>
									</s:generator>
								</font></td></tr>
							</table>			
						</td>
					</tr>
					
				</s:if>
				<!-- 如果是判断题 -->
				<!-- 答案 -->
				<s:elseif test="#priority==3" >
					<tr>
						<td colspan='2'>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> <tr><td colspan='2'>
								<font color='blue'>&nbsp;★标准答案：
								
									<s:if test="#epq.equestions.stDa=='T'">
										正确
									</s:if>
									<s:else>
										错误
									</s:else>
								
								</font></td></tr>
							</table>			
						</td>
					</tr>
				</s:elseif>
				<s:else>
					<tr>
						<td colspan='2'>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> <tr><td colspan='2'>
								<font color='blue'>&nbsp;★标准答案：
									<s:property value="#epq.equestions.stDa"/>
								</font></td></tr>
							</table>			
						</td>
					</tr>			
				</s:else>
			</table>
		</s:iterator>

	
</s:iterator>
	

	</td>
	</tr>
				
</TABLE>
			
		</form>
	</BODY>
	
</HTML>
