<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath() %>/cepinginc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/cepingcss/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></HEAD>
<%response.setHeader("Pragma","cache");response.setHeader("Cache-Control","cache");response.setDateHeader("Expires",10);%>
<%
//设置值用来屏蔽菜单
request.getSession().setAttribute("Flag","1");

EPapers p = (EPapers)request.getAttribute("epapers");

//System.out.println("==========="+p.getSjDjsx());
String ceping=(String)session.getAttribute("ceping");
if(ceping==null)ceping="0";

if(!ceping.equals("1")){

 %>

<%
String []arry=new String[]{"A","B","C","D","E","F"};
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
	
	var endtime = <%=( (Date)session.getAttribute("st") ).getTime()%> + <%=p.getSjDjsx()%>*60*1000;
	
    
	function getCurrentTime(){
		var currenttime = new Date();	
		 //var timems=<%=p.getSjDjsx()%>*60*1000;
		 var timems = endtime-currenttime.getTime();
		 //alert(timems);
		if(timems<=0){
			autoSubmit();
			window.clearTimeout();
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
		setTimeout("getCurrentTime()",1000);
	}

	
	function changeTopage(){
     parent.parent.topFrame.location.reload();
		getCurrentTime();

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
function delete_login(){
window.location = "delete_login.jsp" ;
}
</script>

<BODY style="font-size:14px;MARGIN: 5px;" onLoad="changeTopage()" onbeforeunload="javascript:if(document.all.aa.value == 0){return '';}">
<input type="hidden" name="aa" value="0" />
<IFRAME NAME="uploadfrm" id="uploadfrm" src="" STYLE="HEIGHT:0; LEFT: 0px; MARGIN-TOP: 0px; WIDTH:0; SCROLL: no;"   frameborder=0></IFRAME>


<s:form name="paperSubmitform" action="exampaperActioncommit" method="post" onsubmit="return submitForm();">
<s:token></s:token> 
<s:hidden name="paperId"/>
<input type="hidden" name="jssj" value="<%=( (Date)session.getAttribute("st") ).getTime() %>"/>
<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
      <tr>
        <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
            <tr>
              <td  align="left" valign="middle" class="header1"></td>
              <td  class="header2">在线测评 </td>
              <td  class="header3"></td>
            </tr>
        </table></td>
        <td width="53%"  align="left"></td>
      </tr>
      <tr>
        <td colspan="2" valign="top" ><div id="content1" class="borader">
		    <div class="con_bg">
		     <h1>说明:1、排名不分先后。2、对以下人员"德、能、勤、绩、综合"五个方面的评价分别在相应等次进行选择，如有项目未选择作废票处理。</h1>
		<ul class="first_t">
		<li>
<%int zl=0; %>	
<s:iterator value="equestiontypes" id="type">
	<!--定义一个试题类型 -->
	<s:set name="priority" value="#type.priority"/>
	<!--将集合过滤，过滤成符合这个类型的 -->
	<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
	<!--判断过滤后的集合不为空 -->
	<s:if test="#epaperquestions.size!=0">
				<%zl++; %>
				<%=arry1[zl-1] %>、&nbsp;<s:property value="#type.name"/>&nbsp;（共<s:property value="#epaperquestions.size"/>题,共<SPAN id='<s:property value="#type.id"/>fenshuSpan'><script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>');</script></SPAN>分）
	<ul class="second_t">
		<!-- 遍历题目 -->
		<s:iterator value="#epaperquestions" status="index" id="epq">
		<li>
				<!-- 显示题干 -->				
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="#epq.equestions.stTg"/>
	            <ul class="third_t">					
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="#epq.equestions.stXx" id="querson">
					<s:iterator status="wenti">
								<s:if test="%{#priority==1}">
								<li><input style="float:left" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="radio"  value='<s:property value="#request.arry[#wenti.index]"/>'>
								<s:property value="#request.arry[#wenti.index]" />.</li>
							 	<s:property/>
								</s:if>
								
							 	<s:elseif test="%{#priority==2}">
							 	<li><input style="float:left" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>' type="checkbox"  value='<s:property value="#request.arry[#wenti.index]"/>'>
								<s:property value="#request.arry[#wenti.index]" />.</li>
							 		<s:property />
							 	</s:elseif> 
					</s:iterator>
					</s:generator>
				</s:if>
							<!-- 如果是判断题 -->
							<s:elseif test="#priority==3" >
							
							<li><s:radio list="#{'T':'正确','F':'错误'}" name="%{'answer'+#epq.equestions.stId}"/></li>
							</s:elseif>
							<!-- 如果是判断题说明题 -->
							<s:elseif test="#priority==4" >
								<li><s:radio list="#{'T':'正确','F':'错误'}" name="%{'answer'+#epq.equestions.stId}"/></li> 
								<li>&nbsp;说明：</li>
							    <li>
							        &nbsp;<textarea name="%{'answer'+#epq.equestions.stId}sm" id="textarea"  rows="5" class="text_i"></textarea>
							    </li>
							</s:elseif>
							<!-- 如果是录音题 -->
							<s:elseif test="#priority==5" >
									<li><object align="middle"
										classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id='MediaPlayer<s:property value="#epq.equestions.stId"/>'
										width="400" height="69">
										<param name="ShowStatusBar" value="-1">
										<param name="AutoStart" value="0">
										<param name="Filename" value='<s:property value="#epq.equestions.stFjlj"/>'>
										<embed type="application/x-oleobject"
											codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
											flename="mp" src="" width=200 height=50></embed>
									</object></li>
									<li>
									<s:textarea name="%{'answer'+#epq.equestions.stId}" id="textarea"  rows="5" cssClass="text_i"></s:textarea>
									</li>
							</s:elseif>
							<!-- 如果是问答题 -->
							<s:elseif test="#priority==7 || #priority==8 || #priority==6" >
							<li>&nbsp;回答：</li>
							<li>
							&nbsp;<s:textarea name="%{'answer'+#epq.equestions.stId}"  id="textarea"  rows="5"  cssClass="text_i"></s:textarea>
							</li>		
							</s:elseif>
				</ul>
				</li>	
		</s:iterator>
	</ul>
	</s:if>
</s:iterator>
</li></ul>

	          <li>
					<s:submit cssClass="BigButton" id="tjaj" value="提交答卷"></s:submit>
			</li>

     </div></td>
   </tr>
      
    </table>
	<!--表格结束-->
</s:form>
<script type="text/javascript">

function submitForm(paperSubmitform){
//试题id
var stid='';
//试题答案
var daan='';
//提示
var al='';
//是否答题
var boo=false;
var wwcsl=0;
var diji='';
var dijiti='';
<s:iterator value="equestiontypes"  id="type">
	<s:set name="priority" value="#type.priority"/>
	<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
	<s:if test="#epaperquestions.size!=0">

	    <s:iterator value="epaperquestions" id="epaperquestion" status="index">
	    	stid='<s:property value="#epaperquestion.equestions.stId"/>';
	    		    diji='<s:property value="#index.index+1"/>';
	    	daan=document.getElementsByName('answer'+stid);
	    	<s:if test="%{#priority==1 || #priority==2 || #priority==3 || #priority==4}">
	    		for(var i=0;i<daan.length;i++){
	    			if(daan[i].checked){
	    				boo=true;
	    			}
	    		}
	    		if(!boo){
	    			wwcsl++;
	    			dijiti=diji;
	    		}
	    		boo=false;
	    	</s:if>
	    	<s:if test="%{ #priority==6 || #priority==7 || #priority==8}">
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
	alert('第'+dijiti+'题没有完成！');
	if(window.confirm("确定提交？如提交本票将做废票处理")){
		document.all.aa.value = '1';
		return true;
	}else{
		return false;
	}
}
if(window.confirm("确定提交？")){
	document.all.aa.value = '1';
	var jjj=documnet.getElementById("tjaj");
	jjj.disable=true;
	return true;
}else{
	return false;
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
	    	<s:if test="%{#priority==1 || #priority==2 || #priority==3 || #priority==4}">
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
	    	<s:if test="%{ #priority==6 || #priority==7 || #priority==8}">
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
	alert('时间结束，您有'+wwcsl+'道题目没有完成！系统自动提交。');
	document.all.aa.value = '1';
	document.paperSubmitform.submit();
	
}else{
	alert("系统自动提交！");
    document.all.aa.value = '1';
	document.paperSubmitform.submit();
}

}
</script>
<%}else { %>

<script language="javascript">
    alert("您已经提交过了，请关闭浏览器，谢谢！")
	window.close();
</script>

<%} %>
</BODY>
</HTML>
