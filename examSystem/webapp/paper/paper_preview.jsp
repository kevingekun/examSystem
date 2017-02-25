<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<TITLE>试卷预览</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>

	</HEAD>
<style type="text/css">
.img_xx{
	height: 40px;
}
.imgPv{
	width: 300px;
	height: 200px
}
</style>
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
</script>
<body class="nrbj">
	<form name="autoform" action="<%=request.getContextPath()%>/papersServlet" method="post">
		<input type=hidden name="actionType" value="savepaper">
		<input type=hidden name="sjid" value="<c:out value="${sjid}"/>">
		<input type=hidden name="ctfs" value="<c:out value="${ctfs}"/>">
		<input type=hidden name="sjMc" value="<c:out value="${pager.sjMc}"/>">
		<input type=hidden name="model" value="<c:out value="${pager.model}"/>">
		<input type=hidden name="paperType" value="<c:out value="${pager.paperType}"/>">
		<input type=hidden name="toUserId" value="<c:out value="${pager.toUserId}"/>">
		<input type=hidden name="sjZf" value="<c:out value="${pager.sjZf}"/>">
		<input type=hidden name="sjDjsx" value="<c:out value="${pager.sjDjsx}"/>">
		<input type=hidden name="sjBhgfs" value="<c:out value="${pager.sjBhgfs}"/>">
		<input type=hidden name="Kksj" value='<fmt:formatDate value="${pager.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'>
		<input type=hidden name="Yxqjzsj" value='<fmt:formatDate value="${pager.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'>
		<input type=hidden name="Zjsj" value='<fmt:formatDate value="${pager.sjZjsj}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'>
		<input type=hidden name="sjZjrid" value="<c:out value="${pager.sjZjrid}"/>">
		<input type=hidden name="sjCtfs" value="<c:out value="${pager.sjCtfs}"/>">
		<input type=hidden name="sjKslx" value="<c:out value="${pager.sjKslx}"/>">
		<input type=hidden name="sjNych" value="<c:out value="${pager.sjNych}"/>">
		<input type=hidden name="sjPc" value="<c:out value="${pager.sjPc}"/>">
		<input type=hidden name="sjJjsj" value="<c:out value="${pager.sjJjsj}"/>">
		<input type=hidden name="sjLjcf" value="<c:out value="${pager.sjLjcf}"/>">
		<input type=hidden name="sjZych" value="<c:out value="${pager.sjZych}"/>">
		<input type=hidden name="sjDj" value="<c:out value="${pager.sjDj}"/>">
		<input type=hidden name="sjCtfs" value="<c:out value="${pager.sjCtfs}"/>">
		<input type=hidden name="sjGzid" value="<c:out value="${pager.sjGzid}"/>">
		<input type=hidden name="sjFs" value="<c:out value="${pager.sjFs}"/>">
		<input type=hidden name="outway" value="<c:out value="${outway}"/>">
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
            <td colspan="2"><fmt:formatDate value="${pager.sjKksj}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td><td >有效截止日期:</td>
            <td colspan="2" ><fmt:formatDate value="${pager.sjYxqjzsj}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          </tr>
		 <%-- <tr bgcolor="#FFFFFF">
            <td>考试模式: </td>
            <td colspan="5"><c:if test="${pager.model==1}">开卷</c:if><c:if test="${pager.model!=1}">闭卷</c:if> </td>
          </tr> --%>
		 <tr bgcolor="#FFFFFF">
            <td width="10%">立即出分: </td>
            <td width="35%" colspan="2">
            	<c:if test="${pager.sjLjcf==1}"><c:out value="是"/></c:if>
            	<c:if test="${pager.sjLjcf==0}"><c:out value="否"/></c:if>
            </td>
            <td width="10%" ><c:if test="${pager.paperType==4}">播测题考试人:</c:if>  </td>
            <td width="35%" colspan="2" ><c:if test="${pager.paperType==4}"><c:out value="${pager.toUserId}"/></c:if></td>
          </tr>
	</table>  
	
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>		    
		  <input name="button" type="button" class="btn_export" onClick="javascript:savePaper();" value="保存试卷" />				     
		    </td>
		  </tr>
		</table>
		    
<%
String []arry=new String[]{"A","B","C","D","E","F","G","H","I","J"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);	
%>
<%int zl=0; %>	
<div class="con_bg">
	<c:forEach items="${types}" var="type" varStatus="ty">
	<!-- 第一层 -->
	<ul class="first_t">
	 <li> 
		<c:set var="key" value="${type.descriptor}" scope="request"/>
		<c:set var="keyid" value="${type.id}" scope="request"/>
		<c:set var="questionlist" value="${questionmap[key]}" scope="request"/> 	
		<input type=hidden name="<c:out value="${type.descriptor}"/>" value="<c:out value="${pointmap[key]}"/>">
			
		<c:if test= "${!empty questionlist}">   
				<%zl++; %>
				【<%=arry1[zl-1] %>】.&nbsp;<c:out value="${type.name}"/>&nbsp;（共<c:out value="${questionnumbermap[key]}"/>题,<%-- 每题<c:out value="${pointmap[key]}"/>分, --%>共<c:out value="${totallmap[key]}"/>分）
		</c:if>	 
		<%int row=0; %>
		
		<%-- <c:forEach items="${questionlist}" var="list" varStatus="qs"> --%> 
		<!-- 第二层 -->
			<c:forEach items="${questionlist}" var="q" varStatus="s">
				<ul class="second_t"><li>	
							
					<% row++; %>
					 &nbsp;&nbsp;<%=row%>.&nbsp;<c:out value="${q.stTg}"/>&nbsp;&nbsp;&nbsp;&nbsp;
					 <c:if test="${q.stFz!=null}"><span class="blues">&lt;<c:out value="${q.stFz}"/>分&gt;</span></c:if>
					 <%-- <c:if test="${q.stWh!=null}"><span class="blues">&lt;<c:out value="${q.stWh}"/>&gt;</span></c:if> --%>
					 <c:if test="${q.stCc!=null}"><span class="blues">&lt;<c:out value="${q.stCc}"/>&gt;</span></c:if>
					 <input type="hidden" name="questionid" value="<c:out value="${q.stId}"/>">
							
					<c:if test="${q.stImg!=null}">
						<ul style="height: 1px">
							<li style="height: 1px">
								<div style="position: relative;width: 100%">
							  		<div style="width: 300px;height:200px;float:right;right:10;top:5;position:absolute; z-index:100; ">
							  			<img class="imgPv" id="imgPv" src="<%=request.getContextPath()%>/servlet/ImgServlet?stid=<c:out value="${q.stId}"/>" />
									</div>
								</div>
							</li>
						</ul>
					</c:if>
					<ul class="third_t third_sy">
						<c:if test="${q.stImgA==null}">
							<c:forTokens items="${q.stXx}" delims="||" var="itxx" varStatus="sx" >
								 <li>
								 	<c:if test="${key == 'single'}">
								 			<input type="radio" name="single<%=row%>" value="<c:out value="${arry[sx.count-1]}" />" 
								 			<c:forTokens items="${q.stDa}" delims="||" var="da"><c:if test="${arry[sx.count-1]==da}">checked</c:if></c:forTokens>>
								 	</c:if>
									<c:if test="${key == 'many'}">
								 			<input type="checkbox" name="many<%=row%>" value="<c:out value="${arry[sx.count-1]}" />" 
								 			<c:forTokens items="${q.stDa}" delims="||" var="da"><c:if test="${arry[sx.count-1]==da}">checked</c:if></c:forTokens>>
									</c:if>
									<c:out value="${arry[sx.count-1]}"/>.<span ><c:out value="${itxx}"/></span>
								</li>
							</c:forTokens>
						</c:if>
						<c:if test="${q.stImgA!=null}">
							<c:forTokens items="A||B||C||D" delims="||" var="itxx" varStatus="sx" >
						 		<li>
									<c:if test="${key == 'single'}">
							 			<input type="radio" name="single<%=row%>" value="<c:out value="${arry[sx.count-1]}" />" 
							 			<c:forTokens items="${q.stDa}" delims="||" var="da"><c:if test="${arry[sx.count-1]==da}">checked</c:if></c:forTokens>>
								 	</c:if>
									<c:if test="${key == 'many'}">
							 			<input type="checkbox" name="many<%=row%>" value="<c:out value="${arry[sx.count-1]}" />" 
							 			<c:forTokens items="${q.stDa}" delims="||" var="da"><c:if test="${arry[sx.count-1]==da}">checked</c:if></c:forTokens>>
									</c:if>
									<c:out value="${arry[sx.count-1]}"/>.
									<span>
										<img class="img_xx" id="imgPv<c:out value="${arry[sx.count-1]}"/>" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${q.stId}"/>&xx=<c:out value="${arry[sx.count-1]}"/>"/>
									</span>
								</li>
							</c:forTokens>
							<c:if test="${q.stImgE!=null}">
					 			<li>
						 			<c:if test="${key == 'single'}">
							 			<input type="radio" name="single<%=row%>" value="E" <c:if test="${'E'==q.stDa}">checked</c:if>>
							 		</c:if>
							 		<c:if test="${key == 'many'}">
							 			<input type="checkbox" name="many<%=row%>" value="E" <c:if test="${'E'==q.stDa}">checked</c:if>>
						 			</c:if>
						 			<c:out value="E"/>.
							 		<span>
							 			<img class="img_xx" id="imgPvE" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${q.stId}"/>&xx=E"/>
							 		</span>
						 		</li>
					 		</c:if>
					 		<c:if test="${q.stImgF!=null}">
					 			<li>
						 			<c:if test="${key == 'single'}">
							 			<input type="radio" name="single<%=row%>" value="F" <c:if test="${'F'==q.stDa}">checked</c:if>>
							 		</c:if>
							 		<c:if test="${key == 'many'}">
							 			<input type="checkbox" name="many<%=row%>" value="F" <c:if test="${'F'==q.stDa}">checked</c:if>>
						 			</c:if>
						 			<c:out value="F"/>.
							 		<span>
							 			<img class="img_xx" id="imgPvF" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${q.stId}"/>&xx=F"/>
							 		</span>
						 		</li>
					 		</c:if>
					 		<c:if test="${q.stImgG!=null}">
					 			<li>
						 			<c:if test="${key == 'single'}">
							 			<input type="radio" name="single<%=row%>" value="G" <c:if test="${'G'==q.stDa}">checked</c:if>>
							 		</c:if>
							 		<c:if test="${key == 'many'}">
							 			<input type="checkbox" name="many<%=row%>" value="G" <c:if test="${'G'==q.stDa}">checked</c:if>>
						 			</c:if>
						 			<c:out value="G"/>.
							 		<span>
							 			<img class="img_xx" id="imgPvG" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${q.stId}"/>&xx=G"/>
							 		</span>
						 		</li>
					 		</c:if>
						</c:if>
						<li style="height: 20px"></li>
						<div style="background:url(newimages/answer_bg.jpg) repeat-x #F3F6FB;border:1px solid #D5DBEF;line-height:23px; font-size:12px; padding:0 5px; color:#555; width: 300px">
							标准答案：<c:out value="${q.stDa}"/>				
							<c:if test="${key == 'verdict'}">
								<br>答题说明：<c:out value="${q.stDasm}"/>
							</c:if>
						</div>
					</ul>
				</li></ul>		
				<%-- <c:if test="${q.stImg!=null}">
					<c:if test="${key == 'single'||key == 'many' }">
						<c:if test="${q.stImgE==null}">
							<ul style="height: 15px">
								<li style="height: 15px"></li>
							</ul>
						</c:if>
					</c:if>
					<c:if test="${key == 'verdict' }">
						<ul style="height: 100px">
							<li style="height: 100px"></li>
						</ul>
					</c:if>
				</c:if>	 --%>	
				<c:if test="${q.stImg!=null}">
				<c:choose>
						<c:when test="${key == 'single'||key == 'many' }">
							<c:if test="${q.stImgE==null}">
								<ul style="height: 15px">
									<li style="height: 15px"></li>
								</ul>
							</c:if>
						</c:when>
						<c:when test="${key == 'verdict' }">
							<ul style="height: 100px">
								<li style="height: 100px"></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul style="height: 120px">
								<li style="height: 120px"></li>
							</ul>
						</c:otherwise>
					</c:choose>
					</c:if>
			</c:forEach>		
		 <%--  </c:forEach> --%> 
		 </li>
		</ul>							
	</c:forEach>		
</div>									
</form>
</BODY>
</HTML>