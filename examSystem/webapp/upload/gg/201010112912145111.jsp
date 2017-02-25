<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<s:head theme="ajax" /> 
<title>练习信息</title>
<link href="<%=request.getContextPath() %>/css/all.css" rel="stylesheet" type="text/css">
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
</script>
</head>
<body >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="3" colspan="3"></td>
  </tr>
  <tr> 
    <td width="8"><img src="<%=request.getContextPath() %>/images/min_01.gif" width="8" height="32"></td>
    <td background="<%=request.getContextPath() %>/images/min_02.gif"><div align="center"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="15">&nbsp;</td>
            <td><div align="center"><strong>我的练习</strong></div></td>
          </tr>
        </table>

    </div></td>
    <td width="8"><img src="<%=request.getContextPath() %>/images/min_03.gif" width="8" height="32"></td>
  </tr>
<tr>
    <td height="5" colspan="3">  </td>
</tr>	  
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="5"><img src="<%=request.getContextPath() %>/images/k_01.gif" width="5" height="4"></td>
    <td background="<%=request.getContextPath() %>/images/k_02.gif"></td>
    <td width="6"><img src="<%=request.getContextPath() %>/images/k_03.gif" width="6" height="4"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath() %>/images/k_04.gif"></td>
      <td height="50" bgcolor="#FFFFFF">
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td>练习时间: </td>
            <td><s:date name="eexercise.lxKssj" format="yyyy-MM-dd HH:mm"/>--<s:date name="eexercise.lxJssj" format="yyyy-MM-dd HH:mm"/></td>
           	<td>答题数量: </td>
            <td><s:property value="eexercise.lxDtsl"/> </td>
          </tr>
          
          <tr bgcolor="#FFFFFF">
            <td>客观题数量: </td>
            <td><s:property value="eexercise.lxKgtsl"/></td>
           	<td>客观题正确率: </td>
            <td><s:property value="eexercise.lxKgtzql"/>% </td>
          </tr>

        </table>
      </td>
    <td background="<%=request.getContextPath() %>/images/k_05.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath() %>/images/k_06.gif" width="5" height="4"></td>
    <td background="<%=request.getContextPath() %>/images/k_07.gif"></td>
    <td><img src="<%=request.getContextPath() %>/images/k_08.gif" width="6" height="4"></td>
  </tr>
</table>
<TABLE border=0 align=center width=100% cellpadding=1 cellspacing=1 bgcolor="#D2E8FF">
	
<s:if test="eexercisequestionses.size!=0">
	<tr bgcolor="#ffffff"><td align=center></td></tr>
	<tr bgcolor="#ffffff"　><td align=center><font style="font-size:16px"><b><s:property value="epapers.sjMc"/></b></font></td></tr>
	

	<TR bgcolor="#ffffff">
		<TD>

		<!-- 遍历题目 -->
		<s:iterator value="eexercisequestionses" status="index" id="epq">
			<s:set name="priority" value="#epq.equestions.equestiontype.priority"/>
			<table width="100%" cellpadding="0" cellspacing="0">
				<!-- 显示题干 -->
				<tr>
					<td width="100%" height="30"  bgcolor="#cccccc" colspan="2">
						<s:property value="#index.index+1"/>
						&nbsp;.&nbsp;<s:property value="#epq.equestions.stTg"/>&nbsp;
					</td>
				</tr>
				<!-- 如果是单选题或多选题则要把试题分割显示 -->
				<s:if test="%{#priority==1  ||  #priority==2}">
					<s:generator separator="||" val="#epq.equestions.stXx" id="querson">
					<s:iterator status="wenti">
						<tr bgcolor='#ffffff'>
							<td  width='100%'  height='30px' nowrap colspan="2">
								<s:if test="%{#priority==1 || #priority==2}">
									&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
							 		<s:property />	<br> 
								</s:if>
							</td>
						</tr>
					</s:iterator>
					</s:generator>
					<!-- 答案 -->
					<tr>
						<td colspan='2'>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
							<tr><td colspan='2'>
								<font color='blue'>&nbsp;★标准答案：
									<s:generator separator="||" val="#epq.equestions.stDa">
										<s:iterator>
											<s:property/>、
										</s:iterator>
									</s:generator>
								</font>
								</td></tr>
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
				<tr>
					<td colspan='2'>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
							<tr>
								<td>
									<font color='blue'>&nbsp;回答：<s:property value="#epq.stDa"/></font>
								</td>
							</tr>
						</table>			
					</td>
				</tr>
			</table>
		</s:iterator>
		</TD>
	</TR>
	<tr>
		<td align=center>
			<button style="align:right"  class="BigButton"  onclick="backtomypaper();">返回</button>
		</td>
	</tr>
</s:if>
<s:else>
	<tr>
		<td align=center>
			没有试题。。。。。。<button style="align:right"  class="BigButton"  onclick="backtomypaper();">返回</button>
		</td>
	</tr>
	
</s:else>
			
		
	
</TABLE>
</body>
</html>

