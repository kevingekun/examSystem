<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>试题信息列表</title>
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/inc/test.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/autoresize.min.js"></script>
<%
String []arry=new String[]{"A","B","C","D","E","F"};
String []arry1=new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);

%>
<style>
<!--
.hh {
width:200px;
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap}
-->
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
function retrieveBook(stid) {
   var content=document.aForm.content.value;
   if(content.length==0){
   alert("请填写意见修改原因");
   }
   else {
	if(bXmlHttpSupport) {
       var sUrl = '<%=request.getContextPath() %>/addquestxgcoul.action?quesionId='+stid+"&content="+content;
       var oRequest = new XMLHttpRequest();
       oRequest.onreadystatechange = function() {
       		if(oRequest.readyState == 4) {
       			var question = eval('(' + oRequest.responseText + ')');
       			var spa=document.getElementById("viewxiugaicishu");
       			spa.innerHTML="建议修改次数："+question.count;
       		}
       }
       oRequest.open('POST', sUrl);
       oRequest.send(null);
    }
    }
}


	function jumpback(){
		window.location.href='<%=request.getContextPath() %>/paper/paper_list.jsp';
	}
	function validateForm(form){
		var type=<s:property value="equestions_type"/>;
		var daan=form.daan;
		if(type==1 || type==2 || type==3){
			var boo=false;
			for(var i=0;i<daan.length;i++){
				if(daan[i].checked){
					boo= true;
					break;
				}
			}
			if(!boo){
				if(window.confirm("您没有填写答案！确定提交!")){
					return true;
				}else{
					return false;
				}
			}
		}else if(type==4){
			var boo=false;
			for(var i=0;i<daan.length;i++){
				if(daan[i].checked){
					boo= true;
					break;
				}
			}
			if(!boo){
				if(window.confirm("您没有填写答案！确定提交!")){
					return true;
				}else{
					return false;
				}
			}else{
				if(form.daansm.value.length==0){
					if(window.confirm("您没有填写答案说明！确定提交!")){
						return true;
					}else{
						return false;
					}
				}
			}
		}else if(type==5 || type==6 || type==7 || type==8){
			if(daan.value.length==0){
					if(window.confirm("您没有填写答案！确定提交!")){
						return true;
					}else{
						return false;
					}
			}
		}
	}
	
	function vidwtruedaan(){
		document.getElementById("bzda").style.display="block";
		document.getElementById("lianxi_xyt").disabled=false;
	}
	function overthelianxi(){
		if(window.confirm("确定结束练习？")){
			window.location.href="lianxiresult.action";
		}
	}
	function goback(){
		window.location.href="selectedQuestion.action";
	}
	
	
	function backone(){
	    document.all.backflag.value = '1';
       document.aForm.submit();
	}
	
</script>
</head>
<body onload=''>



<s:form action="findQuestions.action" name="aForm" method="post">
<s:hidden name="epqtId" />
<s:hidden name="equestions_type" />
<s:hidden name="sequence" />
<s:hidden name="ryid" />
<s:hidden name="turn" value="1"/>
<s:hidden name="backflag" />

<s:if test="null != equestions ">
<table width="90%" align="center" cellspacing="5" style="background:#F7F7F7;  border:solid 1px #3094C0; color:#999; font-size:12px;">
    	<tr>
        	<td valign="top">
            
            
            <div style=" margin:5px;"><b style="color:#217A95;">第<s:property value="aftersequence"/>题  &nbsp;&nbsp;&nbsp;&nbsp;总共<s:property value="Typeall"/>(已完成<s:property value="Typealldone"/>) 题</div>
            <div style="background:#fff; border:solid 1px #CAE6EE; padding:10px;height:100%; overflow:hidden; color:#000; font-size:14px;">			
				<!-- 显示题干 -->
						<s:property value="equestions.stTg"/>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:set name="priority" value="equestions.equestions.equestiontype.priority"/>
				
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="equestions.equestions.stXx" id="querson">
					<s:iterator status="wenti">						
								<s:if test="%{#priority==1}">
									<br><input type="radio" name="daan" value='<s:property value="#request.arry[#wenti.index]"/>'
									<s:generator separator="||" val="equestions.stDa" id="Da">
									<s:iterator status="dada">	<s:property value="#dada "/>						
									<s:if test="%{#request.arry[#wenti.index]==dada.value}">checked</s:if>
									</s:iterator>
									</s:generator> >
									&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property />
							 		<s:generator separator="||" val="equestions.stDa" id="Da">
									<s:iterator status="dada">	<s:property value="#dada"/>						
									<s:if test="%{#request.arry[#wenti.index]==dada.value}">checked</s:if>
									</s:iterator>
									</s:generator>
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
									答案：<s:textarea id="wendatidaan" name="daan" cols="100" rows="6"></s:textarea>
							</td></tr>
							</table>			
						</td>
					</tr>
				</s:elseif>
				
				
				<s:elseif test="%{#priority==3}">
				
				<s:radio list="#{'T':'正确','F':'错误'}" name="daan" />
				</s:elseif>
				
				<s:elseif test="%{#priority==4}">
				
				<s:radio list="#{'T':'正确','F':'错误'}" name="daan"/><br>
				&nbsp;说明：<br>
							    
							        &nbsp;<s:textarea id="stdanansm" name="daansm" cols="100" rows="6"></s:textarea>
							    
				</s:elseif>
				
				<s:elseif test="#priority==7 || #priority==8 || #priority==6" >					
						&nbsp;回答：<br>
							
							&nbsp;<s:textarea id="wendatidaan" name="daan" cols="100" rows="6" cssClass="text_i"></s:textarea>
									
				</s:elseif>
				
				<div style="margin-top:10px; padding-top:10px; border-top: dotted 1px #D6D6D6 ">
				
				
				   <div class="buttons">
                            <button onClick="backone();" 
                            <%String s=request.getAttribute("sequence").toString(); 
                             if(s.equals("0")){%> 
                            disabled="true" <%} %>>上一题</button>
                           <% String t=request.getAttribute("Typeall").toString(); 
                           if(new Long(t).intValue()<=new Long(s).intValue()+1 ) { %> <s:submit id="lianxi_xyt" disabled="true" value="下一题"  /><%} else {%>
                           <s:submit id="lianxi_xyt"  value="下一题"  /><%} %>
                        </div>				  
                </div>
            </td>
        </tr>
 </table>       
</s:if>    
</s:form>
</body>
</html>
			




