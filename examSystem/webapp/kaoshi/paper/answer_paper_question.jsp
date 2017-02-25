<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css">
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
String []arry=new String[]{"A","B","C","D","E","F","G","H","I","J"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);
	
Date st = new Date();
if(session.getAttribute("st")==null){
	session.setAttribute("st",st);
}
%>

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
//得分只能输数字
function JHshNumberText(){
	if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57)) 
			|| (window.event.keyCode == 13) || (window.event.keyCode == 46) 
				|| (window.event.keyCode == 45))){
		window.event.keyCode = 0 ;
	}
} 
function retrieveBook1(spnid,typeid) {
    var paperid='<s:property value="eqnswerpaper.epapers.sjId"/>'
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
</script>



<BODY>  
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
    <td>	
    		试卷总分: <s:property value="eqnswerpaper.epapers.sjZf"/>分&nbsp;&nbsp;&nbsp;
			试卷得分:<s:property value="eqnswerpaper.djZf"/>分&nbsp;&nbsp;&nbsp;
			考试时间: <s:date name="eqnswerpaper.djKssj"/> --<s:date name="eqnswerpaper.djJssj"/>
			参考人员: <s:property value="eqnswerpaper.djRymc"/>
    </td>
    <td>          <button class="btn_export" onclick="backtomypaper();">返回</button> <button class="btn_export" onclick="word();">word导出</button>
&nbsp;</td>
    </tr>
</table>
<%int zl=0; %>	
<div class="con_bg">
<s:iterator value="equestiontypes" id="type">
<ul class="first_t">
    <li>
	<s:property value="type.priority"/>
	<!--定义一个试题类型 -->
	<s:set name="priority" value="#type.priority"/>
	<!--将集合过滤，过滤成符合这个类型的 -->
	<s:set name="eanswerquestionse" value="eanswerquestionses.{?#this.epaperquestions.equestions.equestiontype.id==#priority}"/>
	<!--判断过滤后的集合不为空 -->
	<s:if test="#eanswerquestionse.size!=0">
				<%zl++; %>
				【<%=arry1[zl-1] %>】.&nbsp;<s:property value="#type.name"/>&nbsp;（共<s:property value="#eanswerquestionse.size"/>题,共<SPAN
													id='<s:property value="#type.id"/>fenshuSpan'></SPAN>分）
		<!-- 遍历题目 -->
		<s:iterator value="#eanswerquestionse" status="index" id="eapq">
				<!-- 显示题干 -->
			<ul class="second_t"><li>
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stTg)" escape="false"/>&nbsp;&lt;<span id='<s:property value="#type.id"/>single'><s:property value="#eapq.epaperquestions.sjStfs"/></span>分&gt;
						<%-- <s:if test="#eapq.epaperquestions.equestions.stWh!=null"><span class="blues">&lt;<s:property value="#eapq.epaperquestions.equestions.stWh"/>&gt;</span></s:if>
						<s:else>&nbsp;</s:else> --%>
						<s:if test="#eapq.epaperquestions.equestions.stCc!=null"><span class="blues">&lt;<s:property value="#eapq.epaperquestions.equestions.stCc"/>&gt;</span></s:if>
						<s:else></s:else>
						<s:if test="#eapq.epaperquestions.equestions.stImg!=null">
							<ul style="height: 1px">
								<li style="height: 1px">
									<div style="position: relative;width: 100%">
								  		<div style="width: 300px;height:200px;float:right;right:10;top:1;position:absolute; z-index:100; ">
								  			<img class="imgPv" id="imgPv" src="<%=request.getContextPath()%>/servlet/ImgServlet?stid=<s:property value="#eapq.epaperquestions.equestions.stId"/>" />
										</div>
									</div>
								</li>
							</ul>
						</s:if>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<ul class="third_t third_sy">
				<s:if test="%{#priority==2  ||  #priority==8}">
					<s:if test="#eapq.epaperquestions.equestions.stImgA==null">
						<s:generator separator="||" val="#eapq.epaperquestions.equestions.stXx" id="querson">
							<s:iterator status="wenti">
								<li>
		                       		<s:property value="#request.arry[#wenti.index]" /> .
									 	<s:property />
							   	</li>	
							</s:iterator>
						</s:generator>
					</s:if>
					<s:else>
						<s:generator val="'A||B||C||D'" separator="||" id="querson">
							<s:iterator status="wenti">
								<li>
		                       		<s:property value="#request.arry[#wenti.index]" /> .
									<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#eapq.epaperquestions.equestions.stId" />&xx=<s:property value="#request.arry[#wenti.index]" />"/>
							   	</li>
							</s:iterator>
						</s:generator>
						<s:if test="#eapq.epaperquestions.equestions.stImgE!=null">
							<li>
	                       		E .
								<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#eapq.epaperquestions.equestions.stId" />&xx=E"/>
						   	</li>
						</s:if>
						<s:if test="#eapq.epaperquestions.equestions.stImgF!=null">
							<li>
	                       		F .
								<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#eapq.epaperquestions.equestions.stId" />&xx=F"/>
						   	</li>
						</s:if>
						<s:if test="#eapq.epaperquestions.equestions.stImgG!=null">
							<li>
	                       		G .
								<img class="img_xx" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<s:property value="#eapq.epaperquestions.equestions.stId" />&xx=G"/>
						   	</li>
						</s:if>
					</s:else>
					<!-- 答案 -->
					<li class="answer3" style="width: 300px;">
					  <span class="score">得分：<s:property value="#eapq.stDf"/></span>
					    <b>标准答案：<s:generator separator="||" val="#eapq.epaperquestions.equestions.stDa">
										<s:iterator>
											<s:property/>
										</s:iterator>
									</s:generator></b></li>
					 <li class="answer4" style="width: 300px;">
								　　回答：<s:generator separator="||" val="#eapq.stDa">
										<s:iterator>
											<s:property/>
										</s:iterator>
									</s:generator></li>
				</s:if>
				<!-- 如果是判断题 -->
				<!-- 答案 -->
				<s:elseif test="#priority==3" >
				<li class="answer3" style="width: 300px;">
				  <span class="score">得分：<s:property value="#eapq.stDf"/></span>
					 <b>标准答案：
						<s:if test='#eapq.epaperquestions.equestions.stDa=="T"'>
							正确
						</s:if>
						<s:else>
							错误
						</s:else>
					 </b>
				</li>
				<li class="answer4" style="width: 300px;">回答：
					<s:if test='#eapq.stDa=="T"'>
						正确
					</s:if>
					<s:elseif test='#eapq.stDa=="F"'>
						错误
					</s:elseif>
				</li>			
				</s:elseif>
				
				<s:elseif test="#priority==4" >
				<li class="answer3" style="width: 300px;">
				   <span class="score">得分：<s:property value="#eapq.stDf"/></span>
						<b>标准答案：
									<s:if test='#epq.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else>
								<br>答题说明：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDasm)" escape="false"/>
						</b></li>
						
					<li class="answer4">
                        回答：<s:if test='#eapq.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else><br>
                       答题说明：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.stDasm)" escape="false"/>
					</li>		
				</s:elseif>
				
				<s:elseif test="#priority==5">
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
						  <li class="answer3">
				   <span class="score">得分：<s:property value="#eapq.stDf"/></span>
								<b>标准答案：
									<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDa)" escape="false"/>
						                </b> </li>
						     <li class="answer4">
                        回答：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.stDa)" escape="false"/>
					</li>	           

				</s:elseif>
				
				<s:else>
					<li class="answer3">
				   <span class="score">得分：<s:property value="#eapq.stDf"/></span>
								<b>标准答案：
									<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDa)" escape="false"/>
						                </b> </li>
						     <li class="answer4">
                        回答：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.stDa)" escape="false"/>
					</li>	
				</s:else>
				
			</ul></li></ul>
			<s:if test="#eapq.epaperquestions.equestions.stImg!=null">
				<s:if test="%{#priority==2  ||  #priority==8}">
					<s:if test="#eapq.epaperquestions.equestions.stImgE==null">
						<ul style="height: 30px">
							<li style="height: 30px"></li>
						</ul>
					</s:if>
				</s:if>
				<s:elseif test="#priority==3">
					<ul style="height: 120px">
							<li style="height: 120px"></li>
						</ul>
				</s:elseif>
			</s:if>
		</s:iterator>
	</s:if>
	<script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>single');</script>
	</li></ul>
</s:iterator>
			

<script type="text/javascript">
function backtomypaper(){
	window.location.href="answerPaperQuerryAction.action";
}


function word(){
var myPaperId=<s:property value="eqnswerpaper.djId"/>;
	window.location.href="myPaperQuestionWord.action?myPaperId="+myPaperId;
}
</script>
</BODY>
</HTML>
									