<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></HEAD>

<%

//EPaperquestions_temp epqt=(EPaperquestions_temp)request.getAttribute("equestions");
// request.setAttribute("aBean",epqt);

//设置值用来屏蔽菜单
request.getSession().setAttribute("Flag","1");

EPapers p = (EPapers)request.getAttribute("epapers");

//System.out.println("==========="+p.getSjDjsx());

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
	
    var timeid;
	function getCurrentTime(){
		var currenttime = new Date();	
		 //var timems=<%=p.getSjDjsx()%>*60*1000;
		 var timems = endtime-currenttime.getTime();
		 //alert(timems);
		if(timems<=0){
			autoSubmit();
			window.clearTimeout(timeid);
		}
		else {
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
		timeid=setTimeout("getCurrentTime()",1000);
		}
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





function findquestions(){    
    alert(1);	
	var epqtId=document.aForm.epqtId.value;
    var equestions_type=document.aForm.equestions_type.value;
    var sequence=document.aForm.sequence.value;
    var ryid=document.aForm.ryid.value;
    var turn=document.aForm.turn.value;
    var backflag=document.aForm.backflag.value;
    var paperId=document.aForm.paperId.value;
    var exambegin=document.aForm.exambegin.value;
    
    
    if(bXmlHttpSupport) {
       var sUrl = '<%=request.getContextPath() %>/findquestionsByajax.action?epqtId='+epqtId+'&equestions_type='+equestions_type+'&sequence='+sequence+'&ryid='+ryid+'&turn='+turn+'&backflag='+backflag+'&paperId='+paperId+'&exambegin='+exambegin;	
       alert(2);
       var oRequest = new XMLHttpRequest();
       alert(3);
       oRequest.onreadystatechange = function() {
       		if(oRequest.readyState == 4) {   			
       			var xml=oRequest.responseXML;
       			epqtId=xml.getElementsByTagName("epqtId")[0].firstChild.nodeValue;
       			equestions_type=xml.getElementsByTagName("equestions_type")[0].firstChild.nodeValue;
       			sequence=xml.getElementsByTagName("sequence")[0].firstChild.nodeValue;
       			
       			var equestions=xml.getElementsByTagName("equestions")[0].firstChild.nodeValue;
       			var paperquestions=xml.getElementsByTagName("paperquestions")[0].firstChild.nodeValue;
       			var equestiontypes=xml.getElementsByTagName("equestiontypes")[0].firstChild.nodeValue;
       			var epapers=xml.getElementsByTagName("epapers")[0].firstChild.nodeValue;
       			var all=xml.getElementsByTagName("all")[0].firstChild.nodeValue;
       			var alldone=xml.getElementsByTagName("alldone")[0].firstChild.nodeValue;
       			var aftersequence=xml.getElementsByTagName("aftersequence")[0].firstChild.nodeValue;
       			
       			var Typeall=xml.getElementsByTagName("Typeall")[0].firstChild.nodeValue;
       			var Typealldone=xml.getElementsByTagName("Typealldone")[0].firstChild.nodeValue;
       			
       		//	equestions_type=paperfenshu.equestions_type;
       		//	sequence=paperfenshu.sequence;
       		//	ryid=paperfenshu.ryid;
       		//	turn=paperfenshu.turn;
       		//	backflag=paperfenshu.backflag;
       		//	paperId=paperfenshu.paperId;
       		//	exambegin=paperfenshu.exambegin;       			
       		}
       }
       oRequest.open('POST',sUrl,true);
       oRequest.send(null);
    }
	 
	//requestxml(" <%=request.getContextPath() %>/findquestionsByajax.action?epqtId="+epqtId+"&equestions_type="+equestions_type+"&sequence="+sequence+"&ryid="+ryid+"&turn="+turn+"&backflag="+backflag+"&paperId="+paperId+"&exambegin="+exambegin );
  
	}




    function nextone(){
        findquestions();
       
    }
    
	function backone(){
	  document.aForm.backflag.value = '1';  
	  document.aForm.exambegin.value = '2'; 
      document.aForm.submit();
	}
	
	function nextone(){
	  
	    document.aForm.exambegin.value = '2'; 
       document.aForm.submit();
	}
	
	function listNotfinish(){
	    
	   document.aForm.Undone.value = '1';
        document.aForm.submit();
	}
	
	function Unfinishbackone(){
	    document.bForm.backflag.value = '1';
	     document.bForm.exambegin.value = '2';
       document.bForm.submit();
	}
	
	function Unfinishnextone(){
	    document.bForm.turn.value = '1'; 
	    document.bForm.exambegin.value = '2';
        document.bForm.submit();
	}






function delete_login(){
window.location = "delete_login.jsp" ;
}
</script>

<BODY style="font-size:12px;MARGIN: 5px;"  onLoad="changeTopage()">
<input type="hidden" name="aa" value="0" />
<IFRAME NAME="uploadfrm" id="uploadfrm" src="" STYLE="HEIGHT:0; LEFT: 0px; MARGIN-TOP: 0px; WIDTH:0; SCROLL: no;"   frameborder=0></IFRAME>



<input type="hidden" name="jssj" value="<%=( (Date)session.getAttribute("st") ).getTime() %>"/>
<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
      <tr>
        <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
            <tr>
              <td  align="left" valign="middle" class="header1"></td>
              <td  class="header2">在线考试 </td>
              <td  class="header3"></td>
            </tr>
        </table></td>
        <td width="53%"  align="left"></td>
      </tr>
      <tr>
        <td colspan="2" valign="top" ><div id="content1" class="borader">
		    <div class="con_bg">
		    <div class="title"><h1><s:property value="epapers.sjMc"/></h1>
			    <ul><li>试卷分数：<s:property value="epapers.sjZf"/>分</li>
			    <li>考试时间：<s:property value="epapers.sjDjsx"/> 分钟</li>
			    <li>参考人员：<s:property value="user.realname"/></li>
			    <li>剩余时间：<font  id="remaintime">
				<font id="timeh" color="#0000ff"></font>
				<font id="timem" color="#0000ff"></font>
				<font id="times" color="#0000ff"></font>
			    </font></li>
				</ul>
		    </div>
		<ul class="third_t">
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
				<%=arry1[zl-1] %>、&nbsp;				
				
				 <s:url id="modefy" action="examAction.action">
		            	<s:param name="equestions_type" value="#type.priority"></s:param>
		            	<s:param name="paperId" value="epapers.sjId"></s:param>
		            	<s:param name="ryid" value="ryid"></s:param>
		            	<s:param name="first" value="1"></s:param>
		            	<s:param name="exambegin" value="1"></s:param>
		            	<s:param name="Undone" value="0"></s:param>
		            	</s:url>
		            	<s:a href="%{#modefy}">
		            	<s:property value="#type.name"/>
		            	</s:a> 
				&nbsp;（共<s:property value="#epaperquestions.size"/>题,共<SPAN id='<s:property value="#type.id"/>fenshuSpan'><script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>');</script></SPAN>分）
	
	</s:if>
</s:iterator>（共<s:property value="all"/>题,已完成<s:property value="alldone"/>题）
           <s:url id="Unfinish" action="examUnfinish.action">
		            	<s:param name="paperId" value="epapers.sjId"></s:param>
		            	<s:param name="ryid" value="ryid"> </s:param>
		            	<s:param name="first" value="1"> </s:param>
		            	<s:param name="exambegin" value="1"> </s:param>
		            	<s:param name="Undone" value="1"> </s:param>
		            	</s:url>
		            	<s:a href="%{#Unfinish}">
		            	未完成的题目
		            	</s:a> 
		            	
     





<%String Undone=request.getAttribute("Undone").toString(); 
//System.out.println(Undone+"------------------");
  if(Undone!=null&&Undone.equals("1")){%>
<s:form action="examUnfinish.action" name="bForm" method="post">  
<s:hidden name="epqtId" />
<s:hidden name="equestions_type" />
<s:hidden name="sequence" />
<s:hidden name="ryid" />
<s:hidden name="turn"/>
<s:hidden name="backflag" />
<s:hidden name="paperId" />
<s:hidden name="exambegin" value="1"/>
<s:hidden name="unfinish" />
<s:hidden name="Undone" />

<s:if test="null != equestions ">
<table width="90%" align="center" cellspacing="5" style="background:#F7F7F7;  border:solid 1px #3094C0; color:#999; font-size:12px;">
    	<tr>
        	<td valign="top">
            <div style=" margin:5px;"><b style="color:#217A95;"><%String sum=request.getAttribute("all").toString();
             String finish=request.getAttribute("alldone").toString();  int leftnum=0;leftnum=new Long(sum).intValue()- new Long(finish).intValue();%>还剩下<%=leftnum%>题未完成 
            <div style="background:#fff; border:solid 1px #CAE6EE; padding:10px;height:100%; overflow:hidden; color:#000; font-size:14px;">			
				<!-- 显示题干 -->
						<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(equestions.equestions.stTg)" escape="false"/>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:set name="priority" value="equestions.equestions.equestiontype.priority"/>
				
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="equestions.equestions.stXx" id="querson">
					<s:iterator status="wenti">						
								<s:if test="%{#priority==1}">
									<br><input type="radio" name="daan" value='<s:property value="#request.arry[#wenti.index]"/>'
									<s:generator separator="||" val="equestions.stDa" id="Da">
									<s:iterator status="dada">						
									<s:if test="%{#request.arry[#wenti.index]==equestions.stDa}">checked</s:if>
									</s:iterator>
									</s:generator> >
									&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property />
							 		
								</s:if>
							 	<s:elseif test="%{#priority==2}">
							 		<br><input type="checkbox" name="daan" value='<s:property value="#request.arry[#wenti.index]"/>'
							 		<s:generator separator="||" val="equestions.stDa" id="Da">
									<s:if test="%{#request.arry[#wenti.index]==Da}">checked</s:if></s:generator>/>
							 		
							 		&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property /> 
							 	</s:elseif> 
					</s:iterator>
					</s:generator>
				</s:if>
				
				<s:elseif test="%{#priority==5}">
					<li>
						<object align="middle"
								classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer"
								width="400" height="69">
								<param name="ShowStatusBar" value="-1">
								<param name="AutoStart" value="0">
								<param name="Filename" value='<s:property value="#equestions.stFjlj"/>'>
								<embed type="application/x-oleobject"
									codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
									flename="mp" src="" width=200 height=50></embed>
						</object>
					</li>
					<tr>
						<td colspan='2'>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
							<tr><td colspan='2'>
									答案：<s:textarea id="wendatidaan" name="daan" cols="100" rows="6" value="%{equestions.stDa}"></s:textarea>
							</td></tr>
							</table>			
						</td>
					</tr>
				</s:elseif>
				
				
				<s:elseif test="%{#priority==3}">
				
				<s:radio list="#{'T':'正确','F':'错误'}" name="daan" value="%{equestions.stDa==null?'':equestions.stDa}" />
				
				</s:elseif>
				
				<s:elseif test="%{#priority==4}">
				
				<s:radio list="#{'T':'正确','F':'错误'}" name="daan"/><br>
				&nbsp;说明：<br>
							    
							         &nbsp;<s:textarea id="wendatidaan" name="daansm" cols="100" rows="6" cssClass="text_i" value="%{equestions.stDasm}"></s:textarea>
							    
				</s:elseif>
				
				<s:elseif test="#priority==7 || #priority==8 || #priority==6" >					
						&nbsp;回答：<br>
							
							&nbsp;
							<s:textarea id="wendatidaan" name="daan" cols="100" rows="6" cssClass="text_i" value="%{equestions.stDa}"></s:textarea>
							
									
				</s:elseif>
				
				<div style="margin-top:10px; padding-top:10px; border-top: dotted 1px #D6D6D6 ">
				   <div class="buttons">
				   <s:submit id="Unfinish_xyt"  value="确定" />
				   &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                            <button onClick="Unfinishbackone();" 
                            <%String s=request.getAttribute("sequence").toString(); 
                             if(s.equals("0")){%> 
                            disabled="true" <%} %>>上一题</button>
                            
                            <button onClick="Unfinishnextone();" 
                            <%  
                           if(leftnum<=new Long (s).intValue()+1 ) { %> 
                            disabled="true" <%} %>>下一题</button>

                        </div>				  
                </div>
            </td>
        </tr>
 </table>       
</s:if> 
</s:form> 
<%} else {%>

<s:form action="examAction.action" name="aForm" method="post">
<s:hidden name="epqtId" />
<s:hidden name="equestions_type" />
<s:hidden name="sequence" />
<s:hidden name="ryid" />
<s:hidden name="turn" value="1"/>
<s:hidden name="backflag" />
<s:hidden name="paperId" />
<s:hidden name="exambegin" value="1"/>
<s:hidden name="unfinish" />
<s:hidden name="Undone" value="0"/>

<s:if test="null != equestions ">
<table width="90%" align="center" cellspacing="5" style="background:#F7F7F7;  border:solid 1px #3094C0; color:#999; font-size:12px;">
    	<tr>
        	<td valign="top">
            
            
            <div style=" margin:5px;"><b style="color:#217A95;">第<s:property value="aftersequence"/>题  &nbsp;&nbsp;&nbsp;&nbsp;总共<s:property value="Typeall"/>(已完成<s:property value="Typealldone"/>) 题</div>
            <div style="background:#fff; border:solid 1px #CAE6EE; padding:10px;height:100%; overflow:hidden; color:#000; font-size:14px;">			
				<!-- 显示题干 -->
				<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(equestions.equestions.stTg)" escape="false"/>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:set name="priority" value="equestions.equestions.equestiontype.priority"/>
				
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="equestions.equestions.stXx" id="querson">
					<s:iterator status="wenti">						
								<s:if test="%{#priority==1}">
									<br><input type="radio" name="daan" value='<s:property value="#request.arry[#wenti.index]"/>'
															
									<s:if test="%{#request.arry[#wenti.index]==equestions.stDa}">checked</s:if> >
									&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property />
							 		
								</s:if>
							 	<s:elseif test="%{#priority==2}">
							 		<br><input type="checkbox" name="daan" value='<s:property value="#request.arry[#wenti.index]"/>'
							 		<s:generator separator="||" val="equestions.stDa" id="Da">
							 		<s:iterator  id="dada">
									<s:if test='%{#request.arry[#wenti.index]==#dada}'>checked</s:if>
							 		</s:iterator>
									</s:generator> />
							 		&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property /> 
							 	</s:elseif> 
					</s:iterator>
					</s:generator>
				</s:if>
				
				<s:elseif test="%{#priority==5}">
					<li>
						<object align="middle"
								classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer"
								width="400" height="69">
								<param name="ShowStatusBar" value="-1">
								<param name="AutoStart" value="0">
								<param name="Filename" value='<s:property value="#equestions.stFjlj"/>'>
								<embed type="application/x-oleobject"
									codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
									flename="mp" src="" width=200 height=50></embed>
						</object>
					</li>
					<tr>
						<td colspan='2'>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
							<tr><td colspan='2'>
									答案：<s:textarea id="wendatidaan" name="daan" cols="100" rows="6" value="%{equestions.stDa}"></s:textarea>
							</td></tr>
							</table>			
						</td>
					</tr>
				</s:elseif>
				
				
				<s:elseif test="%{#priority==3}">
				
				<s:radio list="#{'T':'正确','F':'错误'}" name="daan" />
				       <s:if test='equestions.stDa=="T"'>			  		
								<script type="text/javascript">document.getElementsByName('daan')[0].checked=true;</script>
							</s:if>
						<s:elseif test='equestions.stDa=="F"'>
								<script type="text/javascript">document.getElementsByName('daan')[1].checked=true;</script>
						</s:elseif>
				
				</s:elseif>
				
				<s:elseif test="%{#priority==4}">
				
				<s:radio list="#{'T':'正确','F':'错误'}" name="daan"/><br>
				 <s:if test='equestions.stDa=="T"'>			  		
								<script type="text/javascript">document.getElementsByName('daan')[0].checked=true;</script>
							</s:if>
						<s:elseif test='equestions.stDa=="F"'>
								<script type="text/javascript">document.getElementsByName('daan')[1].checked=true;</script>
						</s:elseif>
				
				&nbsp;说明：<br>
							    
							        &nbsp;<s:textarea id="wendatidaan" name="daansm" cols="100" rows="6" cssClass="text_i" value="%{equestions.stDasm}"></s:textarea>
							    
				</s:elseif>
				
				<s:elseif test="#priority==7 || #priority==8 || #priority==6" >					
						&nbsp;回答：<br>
							
							&nbsp;
							<s:textarea id="wendatidaan" name="daan" cols="100" rows="6" cssClass="text_i" value="%{equestions.stDa}"></s:textarea>
							
									
				</s:elseif>
				
				<div style="margin-top:10px; padding-top:10px; border-top: dotted 1px #D6D6D6 ">
				   <div class="buttons">
				   <s:submit id="lianxi_xyt"  value="确定" />
				   &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                            <button onClick="backone();" 
                            <%String s=request.getAttribute("sequence").toString(); 
                             if(s.equals("0")){%> 
                            disabled="true" <%} %>>上一题</button>
                            
                            <button onClick="nextone();" 
                            <% String t=request.getAttribute("Typeall").toString(); 
                           if(new Long(t).intValue()<=new Long(s).intValue()+1 ) { %> 
                            disabled="true" <%} %>>下一题</button>

                        </div>				  
                </div>
            </td>
        </tr>
 </table>       
</s:if> 

<s:else>
	<span>
		该题型已完成，请选择下一题型。
	</span>	
</s:else>   
</s:form>

<%} %>

<s:form name="paperSubmitform" action="exampaperActionover" method="post" onsubmit="return submitForm();">
<s:hidden name="paperId"/>
<input type="hidden" name="jssj" value="<%=( (Date)session.getAttribute("st") ).getTime() %>"/>
           <ul>
	          <li>
			<s:submit cssClass="BigButton" value="提交答卷"></s:submit>
			</li>
	      </ul>
</s:form>         
	      
				
     </div></td>
   </tr>  
    </table>
	<!--表格结束-->








<script type="text/javascript">
function submitForm(paperSubmitform){
var sum='<s:property value="all"/>'
var finish='<s:property value="alldone"/>'
var unfinish=sum-finish;
if(unfinish!=0){
	alert('您有'+unfinish+'道题目没有完成！');
	if(window.confirm("确定提交？")){
		document.all.aa.value = '1';
		return true;
	}else{
		return false;
	}
}
if(window.confirm("确定提交？")){
	document.all.aa.value = '1';
	return true;
}else{
	return false;
}
}

function autoSubmit(){

var sum='<s:property value="all"/>'
var finish='<s:property value="alldone"/>'
var unfinish=sum-finish;
	alert('时间结束，您有'+unfinish+'道题目没有完成！系统自动提交。');
	document.all.aa.value = '1';
	document.paperSubmitform.submit();

}
</script>
</BODY>
</HTML>
