<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wondersgroup.falcon.question.model.EQuestions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>������ϸ��Ϣ</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<%-- <script src="<%=request.getContextPath() %>/js/customer.js"></script> --%>
		<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>

</HEAD>	
<%
String []arry=new String[]{"A","B","C","D","E","F","G","H","I","J"};
String []arry1=new String[]{"һ","��","��","��","��","��","��","��","��","ʮ","ʮһ","ʮ��"};
request.setAttribute("arry",arry);
request.setAttribute("arry1",arry1);	
%>

<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">������ϸ��Ϣ</td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_layout">
        <tr>
          <th align="right" width="70">�������ͣ�</th>
          <td align="left"><s:property value="equestions.equestiontype.name"/></td>
          <th align="right" bgcolor="#FFFFFF">���׶ȣ�</th>
          <td align="left"><s:property value="equestions.eimportance.name"/></td>
          <th align="right" bgcolor="#FFFFFF">�Ƿ����⣺</th>
          <td align="left"><s:if test="equestions.stKszy==1">
						  		��
						  	</s:if>
						  	<s:else>
						  		��
						  	</s:else> </td>
          <%-- <th align="right" bgcolor="#FFFFFF">ҵ�����ͣ�</th>
          <td align="left"><s:property value="equestions.ebusinesstype.name"/></td> --%>
        </tr>
        <tr>
          <th align="right">ר�ң�</th>
          <td align="left"><s:property value="equestions.stSyryId"/></td>
          <th align="right" bgcolor="#FFFFFF">���������</th>
          <td colspan="5" align="left"><s:property value="equestions.stCc"/></td>
        </tr>
        <tr>
          <th align="right">������Ŀ��</th>
          <td colspan="7"><span class="red"><s:property value="equestions.stTg"/></span></td>
        </tr>    
    
    
     <!-- ����ǵ�ѡ����ѡ����Ҫ������ָ���ʾ -->
      <s:set name="priority" value="equestions.equestiontype.priority"></s:set>
	  
              
	<s:if test="%{#priority==8  ||  #priority==2}">        
      <tr>
          <th align="right" valign="top">ѡ�</th>
          <td colspan="7">
          <s:generator separator="||" val="equestions.stXx" id="querson">
							<s:iterator status="wenti">
									 	&nbsp;&nbsp;&nbsp;<s:property value="#request.arry[#wenti.index]" /> .
									 	<s:property />	<br> 
							</s:iterator>
		  </s:generator></td>
       </tr>
       <tr>
		           <th align="right" valign="top" bgcolor="#FFFFFF"><span class="blue_f">��׼�𰸣�</span></th>
		           <td colspan="7"><span class="blue_f"><s:generator separator="||" val="equestions.stDa">
													<s:iterator>
														<s:property/>��
													</s:iterator>
												</s:generator></span></td>
		</tr>																			
	</s:if>
		
		<!-- ������ж��� -->
					<!-- �� -->
	  
	<s:elseif test="#priority==3" >
		<tr>	

			       <th align="right" ><span class="blue_f">��׼�𰸣�</span></th>
			       <td colspan="7"><span class="blue_f">
				
									  		<s:if test='equestions.stDa=="T"'>
									  		
														��ȷ
													</s:if>
													<s:else>
														����
											      </s:else>
						</span>					
					  </td>
		 </tr> 
	</s:elseif>    
		       <!-- ������ж���˵���� -->
					<!-- �� -->
		
		<s:elseif test="#priority==4" >
		<tr>			
			
			
			       <th align="right" valign="top" bgcolor="#FFFFFF"><span class="blue_f">��׼�𰸣�</span></th>
			       <td valign="top"><span class="blue_f">
								  		<s:if test='equestions.stDa=="T"'>						  	
														��ȷ
													</s:if>
													<s:else>
														����
											</s:else>
					</span>	
					</td> 				    
		   
		</tr>
		 <tr>
          <th align="right" valign="top">����˵����</th>
          <td colspan="7">
          <s:property value="equestions.stDasm"/>
          </td>
        </tr> 
		</s:elseif>  
		
		    
		<s:else >
		  <tr>
          <th align="right" valign="top"><span class="blue_f">��׼�𰸣�</span></th>
          <td colspan="7"><span class="blue_f"><s:property value="equestions.stDa"/></span>
          </tr>
		</s:else>
		
		 <table width="100%" height="24" border="0" cellpadding="0" cellspacing="0">
	                <tr>
	                    <td height="24" align="center" valign="bottom">
	                      <input type="button" onClick="window.close()" value="�� ��"  class="submit_2"/>
	                    </td>
	        </tr>
	      </table>
</table>
</td>
				</tr></table>
</BODY>
</HTML>

