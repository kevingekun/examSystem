<%@page contentType="text/html;charset=gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<%@ page import="com.wondersgroup.falcon.question.beans.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%
EBusinesstypeService  eBusinesstypeService =new EBusinesstypeService(); 
EImportanceService eImportanceService =new EImportanceService();
EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();
List businesstypelist=eBusinesstypeService.findEBusinesstypeAll();
List importiontypelist=eImportanceService.findEImportanceAll();
List questiontypelist=eQuestiontypeService.findEQuestiontypeAll();
request.setAttribute("businesstypelist",businesstypelist);
request.setAttribute("importiontypelist",importiontypelist);
request.setAttribute("questiontypelist",questiontypelist);
%>
<HTML>
	<HEAD> 
		<TITLE>New Document</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<%-- <script src="<%=request.getContextPath() %>/js/customer.js"></script> --%>
		<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
	</HEAD>	
<script language="JavaScript">

var rowIndex=0;
function addLine(obj,num){
	var objSourceRow=obj.parentNode.parentNode;
	var objTable=obj.parentNode.parentNode.parentNode.parentNode;
	
	if(obj.value=='����'){		
		if(rowIndex<2){
			rowIndex++;
			var tm='E';
			if(rowIndex==1){
				var tm='E';
			}else if (rowIndex==2){
				var tm='F';
			}					
			var objRow=objTable.insertRow(rowIndex);
			var objCell;
			objCell=objRow.insertCell(0);
			if(num==1){
			objCell.innerHTML="<input type=radio name='singleoptionkey' value='"+tm+"'> "+tm+":";;
			}
			if(num==2){
			objCell.innerHTML="<input type=checkbox name='manyoptionkey' value='"+tm+"'> "+tm+":";;
			}
			objCell=objRow.insertCell(1);
			objCell.innerHTML=objSourceRow.cells[1].innerHTML;
	
			objCell=objRow.insertCell(2);
			objCell.innerHTML=objSourceRow.cells[2].innerHTML.replace(/����/,'ɾ��');
		}else{
			alert("����ϵͳ֧��������");
		}
	}
	else{
		objTable.lastChild.removeChild(objSourceRow);
		rowIndex--;
	} 
}
</script>
	<script language="JavaScript">
		function showSubData(){
			var k = window.showModalDialog("exam_newsubwindow.jsp",window,"");
			if(k!=null){
				add_realquesname.value = k.value;
				add_realquesid.value = k.id;
			}
		}		
		function showKeyData(type,fig){
			var k = window.showModalDialog("exam_newkeywindow.jsp",window,"");
			switch(type){
				case 1:
					document.getElementById("add_ockeyname"+fig).value=k.value;
					document.getElementById("add_ockeyid"+fig).value=k.id;
					break;
				case 2:
					document.getElementById("add_mckeyname"+fig).value=k.value;
					document.getElementById("add_mckeyid"+fig).value=k.id;
					break;					
			}
		}
		function query(){			
			document.all.myaction.value="";	
			document.addform.submit();
		}	
				
		function checkSubmit(){			
			if(document.all.ST_YWLXID.value=="0"){
				alert("��ѡ��ҵ������");
				return ;
			}
			if(document.all.ST_LXID.value=="0"){
				alert("��ѡ���������ͣ�");
				return ;
			}
			if(document.all.ST_ZYXID.value=="0"){
				alert("��ѡ�����׶�");
				return ;
			}		
			if(document.all.ST_TG.innerText==""){
				alert("��ɲ���Ϊ�գ�");
				return ;
			}	
			
			////var text=document.all.ST_TG.value;
			////document.all.ST_TG.value=text.replace(/\r\n|\n/g,'<br/>');			
			document.addform.submit();
		}		
		function changeDiv(sv){
			
			switch(sv){
				case "0":
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";				
					break;			
				case "1"://��ѡ
					div1.style.display="block";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";						
					break;
				case "2"://��ѡ
					div1.style.display="none";
					div2.style.display="block";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";					
					break;
				case "3"://�ж���
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="block";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";					
					break;
				case "4"://�ж�˵����
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="block";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";					
					break;
				case "5"://¼����
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="block";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";							
					break;
				case "6"://�����
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="block";
					div7.style.display="none";					
					div8.style.display="none";							
					break;					
				case "7"://�ʴ���
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="block";					
					div8.style.display="none";							
					break;					
				case "8"://����������
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="block";							
					break;					
			}
		}
		
	function aaaaa(){
		window.open('<%=request.getContextPath()%>/wenhao_view.action','�ĺ�ѡ��','height=600, width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
	
	function fuzhi(wenhao,type){
		var values=wenhao.split("||");
		document.getElementById("ST_WH").value=values[0];
		document.getElementById("ST_CC").value=values[1];
		document.getElementById("WH_ID").value=values[2];
		document.getElementById("WH_TYPE").value=type;
		
	}

	function stdel(){
	    if(confirm("ȷ��Ҫɾ����")){
	    	document.addform.action="QuestionServlet";
			document.addform.myaction.value="modify_st_del";
			document.addform.submit();
	    }else{
	    	return false;
	    }
		
	}		
	</script>

<% String messge= (String)request.getAttribute("messge");
if(messge!=null){%>
	<script>
	alert('<%=messge%>');
	</script>
<%}
 
String []arry=new String[]{"A","B","C","D","E","F"};
request.setAttribute("arry",arry);
%>

<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">�����޸�</td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">		
<form name="addform" action="<%=request.getContextPath() %>/QuestionServlet" method="post">
 <input type=hidden name="actionType" value="query">
 <input type=hidden name="myaction" value="modify_st">
 <input type="hidden" name="stId"  value="<c:out value="${question.stId}"/>"/>
 <input type="hidden" name="subject"  value="<c:out value="${subject}"/>"/>
 <input type="hidden" name="documentnum"  value="<c:out value="${documentnum}"/>"/>
 <input type="hidden" name="examsign"  value="<c:out value="${examsign}"/>"/>
 <input type="hidden" name="recorddatebegin"  value="<c:out value="${recorddatebegin}"/>"/>
 <input type="hidden" name="recorddateend"  value="<c:out value="${recorddateend}"/>"/>
 <input type="hidden" name="modifiydatebegin"  value="<c:out value="${modifiydatebegin}"/>"/>
 <input type="hidden" name="modifiydateend"  value="<c:out value="${modifiydateend}"/>"/>
 <input type="hidden" name="businesstype"  value="<c:out value="${businesstype}"/>"/>
 <input type="hidden" name="importance"  value="<c:out value="${importance}"/>"/>
 <input type="hidden" name="questiontype"  value="<c:out value="${questiontype}"/>"/>
 <input type="hidden" name="orderby"  value="<c:out value="${orderby}"/>"/>
 <input type="hidden" name="currpage"  value="<c:out value="${currpage}"/>"/>
 <input type="hidden" name="pagesize"  value="<c:out value="${pagesize}"/>"/>
 <input type="hidden" name="pagenum"  value="<c:out value="${pagenum}"/>"/>
 
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tr class="row_height">
			        <td height="26" width="60" align="right">ҵ�����ͣ�</td>
			        <td><select id="ST_YWLX_ID" name="ST_YWLXID" style="width:286px">
								<option value=0 >��ѡ��</option>
								<c:forEach var="aBean" items="${businesstypelist}">
								<c:choose>
								<c:when test="${question.ebusinesstype.id==aBean.id}">
								 <option value="<c:out value="${aBean.id}"/>" selected="selected"><c:out value="${aBean.name}"/>
				                </c:when>
				                <c:otherwise>
				                 <option value="<c:out value="${aBean.id}"/>" ><c:out value="${aBean.name}"/>
				                </c:otherwise>
				                </c:choose>
				                </c:forEach>
								</select></td>
			        <td align="right">���׶ȣ�</td>
			        <td><select id="ST_ZYXID" name="ST_ZYXID" style="width:266px">
			                 <option value=0>��ѡ��</option>
			                 <c:forEach var="aBean" items="${importiontypelist}">
			             	<c:choose>
							<c:when test="${question.eimportance.id==aBean.id}">
							 <option value="<c:out value="${aBean.id}"/>" selected="selected"><c:out value="${aBean.name}"/>
			                </c:when>
			                <c:otherwise>
			                 <option value="<c:out value="${aBean.id}"/>" ><c:out value="${aBean.name}"/>
			                </c:otherwise>
			                </c:choose>
			                </c:forEach>
                            </select></td>
                </tr>
                <tr>
			        <td height="26" align="right">�������ͣ�</td>
			         <td><select  name="ST_LXID" onChange="changeDiv(this.options[selectedIndex].value)" style="width:286px">
                               <option value=0>��ѡ��</option>
								<c:forEach var="aBean" items="${questiontypelist}">
			             		<c:choose>
								<c:when test="${question.equestiontype.id==aBean.id}">
								 <option value="<c:out value="${aBean.id}"/>" selected="selected"><c:out value="${aBean.name}"/>
			               		 </c:when>
			                	<c:otherwise>
			                 	<option value="<c:out value="${aBean.id}"/>" ><c:out value="${aBean.name}"/>
			                	</c:otherwise>
			                	</c:choose>
			                	</c:forEach>
                                 </select>     </td>
			        <td align="right">�Ƿ����⣺</td>
			        <td><input type=radio name="ST_KSZY" value="1" <c:if test="${question.stKszy==1}">checked</c:if>>��
						  <input type=radio  name="ST_KSZY" value="0" <c:if test="${question.stKszy==0}">checked</c:if>>��
						  </td>
                </tr>
                <tr>
			        <td height="26" align="right">������</td>
			        <td colspan=3>
			        <input type=text id="ST_CC" name="ST_CC" value="<c:out value="${question.stCc}" />" style="width:622px" readonly>
			        <a href="javascript:aaaaa();">[ѡ��]</a>
			        </td>
			     </tr>
			     <tr>  
			        <td height="26" align="right">�ĺţ�</td>
			        <td colspan=3>
			        <input type=text id="ST_WH" name="ST_WH" value="<c:out value="${question.stWh}"/>" style="width:622px" readonly>
			        <input id="WH_ID" type="hidden" name="WH_ID" value="" />
					<input id="WH_TYPE" type="hidden" name="WH_TYPE" value="" />
			        </td>
                </tr>																
								
				<tr>
			          <td height="26" align="right">��Ŀ��</td>
			          <td colspan="3"><textarea id="ST_TG1" name="ST_TG" style="width:580px;" rows="4"><c:out value="${question.stTg}"/></textarea></td>
			      </tr>	
			    <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
			       <tr>
                    <td height="40" align="center" valign="bottom">
                     <input name="add" type=button class="submit_2" onClick="jvavscript:checkSubmit();" value="�� ��"/>&nbsp;
					 <input name="res" type="reset"  class="submit_2" value="�� ��"/>&nbsp;
					 <input name="del" type=button class="submit_2" onClick="jvavscript:stdel();" value="ɾ ��"/>
                    </td>
                  </tr>	
                </table> 			
		
<div id="div1" <c:if test="${question.equestiontype.id!=1}"> style="display:none"</c:if> >		
	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr><td width=40% colspan="3">&nbsp;��ѡ�������</td></tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">��ѡ�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
				
					<c:forTokens items="${question.stXx}" delims="||" var="it" varStatus="s" >					
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60">
									<input type=radio name="singleoptionkey" value="<c:out value="${arry[s.count-1]}" />" 
									<c:forTokens items="${question.stDa}" delims="||" var="da">
									<c:if test="${arry[s.count-1]==da}">checked</c:if>
									</c:forTokens>>
									<c:out value="${arry[s.count-1]}"/>��
									</td>
									<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3"><c:out value="${it}"/></textarea></td>
									
									
									
									<td><c:if test="${s.count==4}"><input name="add1" type="button" id="add2" value="����" onClick="addLine(this,1)"></c:if>
									<c:if test="${s.count>4}"><input name="add1" type="button" id="add1" value="ɾ��" onClick="addLine(this,1)">
									<script language="JavaScript">rowIndex++</script>
									</c:if>
									
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					</c:forTokens>
			
				<c:if test="${question.stXx==null}">
					
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
								<td width="60"><input type=radio name="singleoptionkey" value="A">A:</td>
								<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3"></textarea></td>
								<td></td></tr>
							</table>
						</td>	
					</tr>
					<tr>
						<td>
						
							<table border="0" cellpadding="0" cellspacing="0"><tr>
								<td width="60"><input type=radio  name="singleoptionkey"  value="B">B:</td>
								<td><textarea id="singleoption" name="singleoption"  style="width:580px;" rows="3"></textarea></td>
								<td></td></tr>
							</table>
						</td>	
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
								<td width="60"><input type=radio name="singleoptionkey"  value="C">C:</td>
								<td><textarea id="singleoption" name="singleoption"  style="width:580px;" rows="3"></textarea></td>
								<td></td></tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
						<table border="0" cellpadding="0" cellspacing="0"><tr>		
							<td width="60"><input type=radio name="singleoptionkey"  value="D">D:</td>
							<td><textarea id="singleoption" name="singleoption"  style="width:580px;" rows="3"></textarea></td>
							<td><input name="add2" type="button" id="add2" value="����" onClick="addLine(this,1)"></td></tr>
						</table>
						</td>
					</tr>
			
				</c:if>		
					
					
				</table>
			  </td>
			  <td width="10%">&nbsp;</td>
			</tr>
		</table>
</div>						
						
<div id="div2" <c:if test="${question.equestiontype.id!=2}"> style="display:none"</c:if> >
	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;��ѡ�������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">��ѡ�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>

					<c:forTokens items="${question.stXx}" delims="||" var="it" varStatus="s" >					
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60">
									<input type=checkbox name="manyoptionkey" value="<c:out value="${arry[s.count-1]}" />" 
									<c:forTokens items="${question.stDa}" delims="||" var="da">
									<c:if test="${arry[s.count-1]==da}">checked</c:if>
									</c:forTokens>>
									<c:out value="${arry[s.count-1]}"/>��
									</td>
									<td><textarea id="manyoption" name="manyoption" style="width:580px;" rows="2"><c:out value="${it}"/></textarea></td>
									<td><c:if test="${s.count==4}"><input name="add2" type="button" id="add2" value="����" onClick="addLine(this,2)"></c:if>
									<c:if test="${s.count>4}"><input name="add2" type="button" id="add2" value="ɾ��" onClick="addLine(this,2)">
									<script language="JavaScript">rowIndex++</script>
									</c:if>
									
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					</c:forTokens>
					
					<c:if test="${question.stXx==null}">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
								<td width="60"><input type=checkbox name="manyoptionkey" value="A">A:</td>
								<td><textarea name="manyoption" id="manyoption1" style="width:580px;" rows="2"></textarea></td>
								<td></td></tr>
							</table>
						</td>	
					</tr>
					<tr>
						<td>
						
							<table border="0" cellpadding="0" cellspacing="0"><tr>
								<td width="60"><input type=checkbox name="manyoptionkey" value="B">B:</td>
								<td><textarea name="manyoption" id="manyoption2" style="width:580px;" rows="2"></textarea></td>
								<td></td></tr>
							</table>
						</td>	
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td width="60"><input type=checkbox name="manyoptionkey" value="C">C:</td>
								<td><textarea name="manyoption" id="manyoption3" style="width:580px;" rows="2"></textarea></td>
								<td></td></tr>
							</table>
						</td>	
					</tr>
					<tr>
						<td>
						<table border="0" cellpadding="0" cellspacing="0"><tr>
							<td width="60"><input type=checkbox name="manyoptionkey" value="D">D:</td>
							<td><textarea name="manyoption" id="manyoption4" style="width:580px;" rows="2"></textarea></td>
							<td><input name="add2" type="button" id="add2" value="����" onClick="addLine(this,2)"></td></tr>
						</table>
						</td>
					</tr>
					</c:if>
			  </table>
			  
			</td>
			<td width="10%">&nbsp;</td>
		  </tr>
		</table>
</div>					
						
<div id="div3" <c:if test="${question.equestiontype.id!=3}"> style="display:none"</c:if>>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;�ж��������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">�ж�ѡ�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
								<td><input type=radio name="verdictkey" value="T" <c:if test="${question.stDa=='T'}">checked</c:if>>
								  ��:</td>
								<td><input type=radio name="verdictkey" value="F" <c:if test="${question.stDa=='F'}">checked</c:if>>
								  ��</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>
		</table>
</div>												
<div id="div4" <c:if test="${question.equestiontype.id!=4}">style="display:none"</c:if>>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;�ж�˵���������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">�ж�ѡ�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td><input type=radio name="verdictsaykey" value="T" <c:if test="${question.stDa=='T'}">checked</c:if>>
								  ��:</td>
								<td><input type=radio name="verdictsaykey" value="F" <c:if test="${question.stDa=='F'}">checked</c:if>>
								  ��</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>
		  <tr bgcolor="#ffffff">
			<td width="10%" align="right">˵����</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table><tr>
							  <td><textarea id="verdictsay" name="verdictsay" style="width:580px;" rows="5"><c:if test="${question.equestiontype.id==4}"><c:out value="${question.stDasm}"/></c:if></textarea></td>
								<td>&nbsp;</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>

		</table>
</div>
<div id="div5" <c:if test="${question.equestiontype.id!=5}"> style="display:none"</c:if>>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;¼���������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">�𰸣�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td><textarea name="recordkey" id="recordkey" style="width:580px;" rows="5"><c:if test="${question.equestiontype.id==5}"><c:out value="${question.stDa}"/></c:if></textarea></td>
								<td>&nbsp;</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>

		<tr bgcolor="#ffffff">
			<td width="10%" align="right">¼���ļ���</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td><input type=text name="recordpath" size="80" value="<c:if test="${question.equestiontype.id==5}"><c:out value="${question.stFjlj}"/></c:if>"></td>
								<td>&nbsp;</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>
		</table>
</div>	
						
<div id="div6" <c:if test="${question.equestiontype.id!=6}"> style="display:none"</c:if>>

                                                        
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;����������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">�𰸣�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td><textarea name="fillkey" id="fillkey" style="width:580px;" rows="5"><c:if test="${question.equestiontype.id==6}"><c:out value="${question.stDa}"/></c:if></textarea></td>
								<td>����һ�����ż����</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>
		</table>						
</div>	
<div id="div7" <c:if test="${question.equestiontype.id!=7}"> style="display:none"</c:if>>

    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;�ʴ��������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">�𰸣�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td><textarea name="askkey" id="askkey" style="width:580px;" rows="5"><c:if test="${question.equestiontype.id==7}"><c:out value="${question.stDa}"/></c:if></textarea></td>
								<td>&nbsp;</td>
								<td></td></tr>
							</table></td>
					</tr>						
			  </table>
			</td>
		  </tr>
	</table>			
</div>	


<div id="div8" <c:if test="${question.equestiontype.id!=8}"> style="display:none"</c:if>>

    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
		<tr>
		  <td width=40% colspan="3">&nbsp;���������������</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td width="10%" align="right">�𰸣�</td>
			<td width="80%" align="center">
				<table width=760 border=0 align="center" cellspacing ="1" cellspadding=1>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0"><tr>
							  <td><textarea name="casekey" id="casekey" style="width:580px;" rows="5"><c:if test="${question.equestiontype.id==8}"><c:out value="${question.stDa}"/></c:if></textarea></td>
								<td>&nbsp;</td>
								<td></td></tr>
							</table>
						</td>
					</tr>						
			  </table>
			</td>
		  </tr>
		</table>

</div>	
		
		</table>
		</form>
		</div>
		</td>
		</tr>
		</table>
		</body>

</HTML>
