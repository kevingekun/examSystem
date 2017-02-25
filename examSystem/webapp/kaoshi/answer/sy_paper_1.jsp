<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator" %>
<%@ page import="com.wondersgroup.falcon.paper.model.EAnswerpaper"%>
<%@ page import="com.wondersgroup.falcon.paper.model.EAnswerquestions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<link href="newcss/style.css" rel="stylesheet" type="text/css">
</HEAD>

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
function retrieveBook(spnid,typeid) {
    var paperid='<s:property value="eanswerpaper.epapers.sjId"/>'
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
//得分只能输数字
function JHshNumberText(){
	if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57)) 
			|| (window.event.keyCode == 13) || (window.event.keyCode == 46) 
				|| (window.event.keyCode == 45))){
				alert("只能输入数字");
		window.event.keyCode = 0 ;
	}
	
//
} 
function total(strname,fen){
         
		var x ="";
		var y =fen;
        if(strname!=null&&!strname==""){
    	 	x= document.all[strname].value;
    	 	
    	}
		var x2 = parseFloat(x);
    	var t=0;

    	if(x2>y){
    	t=1;
    	alert("所给分数大于题目分数");
    	}
    	if(t==0){
      	var  zf =  0 ;
		var a = document.paperSubmitform.getElementsByTagName("input");   		
		for (var i=0; i<a.length; i++){
				
   				if(a[i].title=="title" && a[i].type=="text"){
	   		
   					zf = zf+ Number(a[i].value);
   					
   				}
   				
   				
   		}
   		document.paperSubmitform.zfzf.value=zf;
   		
   	}

} 

function goback(){	
	window.location.href="tosypaperAction.action?djSyzt="+0+"&currpage="+1;
}
	
</script>

<BODY style="font-size:12px;MARGIN: 5px;" >
<s:form name="paperSubmitform" action="defenQuestion" method="post" onsubmit="return submitForm();" >
<s:hidden name="djSyzt" />
<s:hidden name="answerpaperid" />
<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
      <tr>
        <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
            <tr>
              <td  align="left" valign="middle" class="header1"></td>
              <td  class="header2">确定提交 </td>
              <td  class="header3"></td>
            </tr>
        </table></td>
        <td width="53%"  align="left"></td>
      </tr>
    <tr>
        <td colspan="2" valign="top" ><div id="content1" class="borader">

    <div class="con_bg">
    <div class="title title_sy"><h1><s:property value="epapers.sjMc"/></h1>
    <ul><li>试卷分数：<s:property value="eanswerpaper.epapers.sjZf"/>分</li><li>考试时间：<s:property value="eanswerpaper.epapers.sjDjsx"/>分钟</li><li>得份：<input name="zfzf" type="text" class="inners fs" size="10" value= ></li></ul>
    </div>
    <br>
    <button  cssClass="BigButton" onclick="javascript:history.go(-1);">返回</button>
    <s:if test="djSyzt==0">
	  <s:submit cssClass="BigButton" value="提交审阅"></s:submit>
	</s:if>
	<s:elseif test="djSyzt==1">
	  <s:submit cssClass="BigButton" value="确定提交"></s:submit>
	</s:elseif>


<%int zl=0; %>	
<s:set name="equestiontypesGuolv" value="equestiontypes.{?#this.canSy!=1}"/>
<s:iterator value="#equestiontypesGuolv" id="type">
<ul class="first_t">
<li>
	<!--定义一个试题类型 -->
	<s:set name="priority" value="#type.priority"/>
	<!--将集合过滤，过滤成符合这个类型的 -->
	<s:set name="eanswerquestionse" value="eanswerquestionses.{?#this.epaperquestions.equestions.equestiontype.id==#priority}"/>
	<!--判断过滤后的集合不为空 -->
	<s:if test="#eanswerquestionse.size!=0">
				<%zl++; %>
				<%=arry1[zl-1] %>、&nbsp;<s:property value="#type.name"/>&nbsp;（共<s:property value="#eanswerquestionse.size"/>题,共<SPAN id='<s:property value="#type.id"/>fenshuSpan'><script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>');</script></SPAN>分）
		<!-- 遍历题目 -->
		<s:iterator value="#eanswerquestionse" status="index" id="eapq">
			<ul class="second_t"><li>
				<!-- 显示题干 -->
					<s:property value="#index.index+1"/>
						&nbsp;&nbsp;<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stTg)" escape="false"/>&nbsp;&lt;<s:property value="#eapq.epaperquestions.sjStfs"/>分&gt;
						<s:if test="#eapq.epaperquestions.equestions.stWh!=null"><span class="blues">&lt;<s:property value="#eapq.epaperquestions.equestions.stWh"/>&gt;</span></s:if>
						<s:else>&nbsp;</s:else>
						<s:if test="#eapq.epaperquestions.equestions.stCc!=null"><span class="blues">&lt;<s:property value="#eapq.epaperquestions.equestions.stCc"/>&gt;</span></s:if>
						<s:else></s:else>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<ul class="third_t third_sy">
				<s:if test="%{#priority==1  ||  #priority==2||#priorty==10}">
					<s:generator separator="||" val="#eapq.epaperquestions.equestions.stXx" id="querson">
					<s:iterator status="wenti">
								<s:if test="%{#priority==1 || #priority==2||#priorty==10}">
									<li>&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> 
							 		<s:property /></li>
								</s:if>
					</s:iterator>
					</s:generator>
					<!-- 答案 -->
					<li class="answer"><dl>
					<s:if test="djSyzt==0"><s:if test="#eapq.stsyzt==1"><dd>已审阅</dd></s:if></s:if>
					<s:if test="djSyzt==1"> <dt>得分：<s:textfield title="title" name="%{'defen'+#eapq.id}" value="%{#eapq.stDf}" onkeypress="JHshNumberText();"  onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></dt>	</s:if>
					<s:else><dt><s:if test="#eapq.epaperquestions.equestions.stDa==#eapq.stDa">
												<font color='blue'>&nbsp;得分：<s:textfield  title="title" name="%{'defen'+#eapq.id}" value="%{#eapq.epaperquestions.sjStfs}" onkeypress="JHshNumberText();"  onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></font>
											</s:if>
											<s:else>
												<font color='blue'>&nbsp;得分：<s:textfield  title="title" name="%{'defen'+#eapq.id}" value="0" onkeypress="JHshNumberText();" onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></font>
											</s:else></dt>
					</s:else>							
					<dt>标准答案：<s:generator separator="||" val="#eapq.epaperquestions.equestions.stDa">
										<s:iterator>
											<s:property/>、
										</s:iterator>
									</s:generator></dt><dt>回答：<s:if test="%{#priority==1  ||  #priority==2}">
										<s:generator separator="||" val="#eapq.stDa" id="querson">
											<s:iterator status="wenti">
													 	<s:property />、
											</s:iterator>
										</s:generator>
									</s:if></dt></dl></li>				
				</s:if>
				</ul>	
				
				<!-- 如果是判断题 -->
				<!-- 答案 -->
				<ul class="third_t third_sy">
				<s:elseif test="#priority==3" >
				<li class="answer"><dl>
				<s:if test="djSyzt==0"><s:if test="#eapq.stsyzt==1"><dd>已审阅</dd></s:if></s:if>
				<s:if test="djSyzt==1"> <dt>得分：<s:textfield title="title" name="%{'defen'+#eapq.id}" value="%{#eapq.stDf}" onkeypress="JHshNumberText();"  onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></dt></s:if>
				<s:else><dt><s:if test="#eapq.epaperquestions.equestions.stDa==#eapq.stDa">
												<font color='blue'>&nbsp;得分：<s:textfield  title="title" name="%{'defen'+#eapq.id}" value="%{#eapq.epaperquestions.sjStfs}" onkeypress="JHshNumberText();"  onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></font>
											</s:if>
											<s:else>
												<font color='blue'>&nbsp;得分：<s:textfield  title="title" name="%{'defen'+#eapq.id}" value="0" onkeypress="JHshNumberText();" onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></font>
											</s:else></dt>
				</s:else>							
				<dt>标准答案：<s:if test='#eapq.epaperquestions.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else></dt><dt>回答：<s:if test='#eapq.stDa=="T"'>
											正确
										</s:if>
										<s:elseif test='#eapq.stDa=="F"'>
											错误
										</s:elseif>
										<s:else>
										</s:else></dt></dl></li>													
				</s:elseif>
				</ul>
				
				
				<!-- 如果是判断说明题 -->
				<!-- 答案 -->
				<ul class="third_t third_sy">
				<s:elseif test="#priority==4" >
				<li class="blue_bg">答题：<s:if test='#eapq.stDa=="T"'>
											正确
										</s:if>
										<s:else>
											错误
										</s:else></li>
					<li class="blue_bg">答题说明：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.stDasm)" escape="false"/> </li>
					<li class="red">标准答案：<s:if test='#eapq.epaperquestions.equestions.stDa=="T"'>
										正确
										</s:if>
										<s:else>
										错误
									    </s:else></li>
					<li class="red">答题说明：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDasm)" escape="false"/></li>
					    <li class="answer"><dl>
					    <s:if test="djSyzt==0"><s:if test="#eapq.stsyzt==1"><dd>已审阅</dd></s:if></s:if>
					    <s:if test="djSyzt==1"><dt>得分：<s:textfield title="title" name="%{'defen'+#eapq.id}" value="%{#eapq.stDf}" onkeypress="JHshNumberText();"  onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></dt></s:if>
						<s:else><dt><font color='blue'>&nbsp;得分：<s:textfield  title="title" name="%{'defen'+#eapq.id}"  value="%{#eapq.stDf}" onkeypress="JHshNumberText();"  onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></font></dt>											
				       </s:else>
					   </dl></li>
				</s:elseif>
				</ul>
				
				<!-- 如果是其他类型 -->
				<ul class="third_t third_sy">
				<s:else>
				<li class="answer"><dl>
				<li class="blue_bg">答题：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.stDa)" escape="false"/></li>
				<li class="red">标准答案：<s:property value="@com.wondersgroup.falcon.Util.StringUtils@convertToBrNew(#eapq.epaperquestions.equestions.stDa)" escape="false"/></li>
				<li class="answer"><dl>
				<s:if test="djSyzt==0"><s:if test="#eapq.stsyzt==1"><dd>已审阅</dd></s:if></s:if>
				<s:if test="djSyzt==1"><dt>得分：<s:textfield title="title" name="%{'defen'+#eapq.id}" value="%{#eapq.stDf}" onkeypress="JHshNumberText();"  value="%{#eapq.stDf}" onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></dt></s:if>
				<s:else><dt><font color='blue'>&nbsp;得分：<s:textfield  title="title" name="%{'defen'+#eapq.id}"  value="%{#eapq.stDf}" onkeypress="JHshNumberText();"   onblur="total(this.name,'%{#eapq.epaperquestions.sjStfs}');"/></font></dt>										
				</s:else>
				</dl></li>		
				</s:else>
				</ul>
	</li></ul>	
		</s:iterator>
		
 	</s:if></li></ul>
	<s:if test="#eanswerquestionse.size==0">		
	</s:if>
</s:iterator>

			</div>
			</div>
		</td>
	</tr>
		
</TABLE>
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
<s:set name="equestiontypesGuolv" value="equestiontypes.{?#this.canSy!=1}"/>
<s:iterator value="#equestiontypesGuolv" id="type">
	<s:set name="priority" value="#type.priority"/>
	<s:set name="eanswerquestionse" value="eanswerquestionses.{?#this.epaperquestions.equestions.equestiontype.id==#priority}"/>
	<s:if test="#eanswerquestionse.size!=0">
	    <s:iterator value="eanswerquestionse" id="eapq">
	    	stid='<s:property value="#eapq.id"/>';
	    	daan=document.getElementsByName('defen'+stid);
	    	if(daan[0].value.length!=0){
	    		boo=true;
	    	}
	    	if(!boo){
	    		wwcsl++;
	    	}
	    	boo=false;
	    </s:iterator>
    </s:if>
</s:iterator>
if(wwcsl!=0){
	if(window.confirm('您有'+wwcsl+'道题目没有审阅！')){
		return true;
	}else{
		return false;
	}
}
if(window.confirm("确定提交？")){
	return true;
}else{
	return false;
}
}
//total();
</script>
</BODY>
</HTML>
