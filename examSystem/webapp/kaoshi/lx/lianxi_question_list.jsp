<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<s:head theme="ajax" /> 
<title>练习信息</title>
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>

<style>
<!--
.hh {
width:200px;
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap}
-->
</style>  
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
<script type="text/javascript">
	function jumpback(){
		window.location.href='<%=request.getContextPath() %>/paper/paper_list.jsp';
	}
	function validateForm(form){
		var daan=form.daan;
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
			if(window.confirm("确定提交!")){
				return true;
			}else{
				return false;
			}
		}
	}
	
	function backtomypaper(){
		window.location.href="lianxiquestionQuery.action";
	}
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}
</script>
</head>
<BODY>  
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
    <td>	
    		答题数量: <s:property value="eexercise.lxDtsl"/>&nbsp;&nbsp;&nbsp;
		     客观题数量:<s:property value="eexercise.lxKgtsl"/>&nbsp;&nbsp;&nbsp;
			练习时间: <s:date name="eexercise.lxKssj" format="yyyy-MM-dd HH:mm"/>--<s:date name="eexercise.lxJssj" format="yyyy-MM-dd HH:mm"/>
			客观题正确率:: <s:property value="eexercise.lxKgtzql"/>%
    </td>
    <td>          <button class="btn_export" onclick="backtomypaper();">返回</button> 
&nbsp;</td>
    </tr>
</table>
	
<s:if test="eexercisequestionses.size!=0">
<div class="con_bg">
		<!-- 遍历题目 -->
		<s:iterator value="eexercisequestionses" status="index" id="epq">		
		<ul class="first_t">
          <li>
			<s:set name="priority" value="#epq.equestions.equestiontype.priority"/>
				<!-- 显示题干 -->
				<ul class="second_t"><li>
				
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="#epq.equestions.stTg"/>&nbsp;
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
			<ul class="third_t third_sy">
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="#epq.equestions.stXx" id="querson">
					<s:iterator status="wenti">
								<li><s:if test="%{#priority==1 || #priority==2}">
									&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property />
								</s:if></li>
					</s:iterator>
					</s:generator>
					<!-- 答案 -->
					<li class="answer3">
					    <b>标准答案：<s:generator separator="||" val="#epq.equestions.stDa">
										<s:iterator>
											<s:property/>
										</s:iterator>
									</s:generator></b></li>
					 <li class="answer4">
								　　回答：<s:generator separator="||" val="#epq.stDa">
										<s:iterator>
											<s:property/>
										</s:iterator>
									</s:generator></li>
				</s:if>
				<!-- 如果是判断题 -->
				<!-- 答案 -->
				<s:elseif test="#priority==3" >
				<li class="answer3">
					 <b>标准答案：
									<s:if test='#epq.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else></b></li>
						<li class="answer4">回答：<s:if test='#epq.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else></li>			
				</s:elseif>

				<s:elseif test="#priority==4" >
					
				<li class="answer3">
						<b>标准答案：
									<s:if test='#epq.equestions.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else>
								<br>答题说明：<s:property value="#epq.equestions.stDasm"/>
						</b></li>
						
					<li class="answer4">
                        回答：<s:if test='#epq.stDa=="T"'>
										正确
									</s:if>
									<s:else>
										错误
									</s:else><br>
                       答题说明：<s:property value="#epq.stDasm"/>
					</li>		
				</s:elseif>					
				<s:elseif test="#priority==5" >
					       <li>
									<object align="middle"
										classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id='MediaPlayer<s:property value="#epq.equestions.stId"/>'
										width="400" height="69">
										<param name="ShowStatusBar" value="-1">
										<param name="AutoStart" value="0">
										<param name="Filename" value='<s:property value="#epq.equestions.stFjlj"/>'>
										<embed type="application/x-oleobject"
											codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
											flename="mp" src="" width=200 height=50></embed>
									</object>
							</li>
					<li class="answer3">
								<b>标准答案：
									<s:property value="#epq.equestions.stDa"/>
						                </b> </li>
						     <li class="answer4">
                        回答：<s:property value="#epq.stDa"/>
				</li>	           

				</s:elseif>	

                <s:else>
					<li class="answer3">
								<b>标准答案：
									<s:property value="#epq.equestions.stDa"/>
						                </b> </li>
						     <li class="answer4">
                        回答：<s:property value="#epq.stDa"/>
					</li>	
				</s:else>
     </ul></li></ul></li></ul>
		</s:iterator>
     </div>
</s:if>
<s:else>
<table>
	<tr>
		<td align=center>
			没有试题。。。。。。
		</td>
	</tr>
</table>	
</s:else>
			

</body>
</html>

