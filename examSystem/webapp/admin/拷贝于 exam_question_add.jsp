<%@page contentType="text/html;charset=gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script language="javascript">
	
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
		
		function checkSubmit(type){
			if(add_realquesname.value==""){
				alert("��Ŀ������Ϊ�գ�");
				return ;
			}
			if(add_questiontype.value=="0"){
				alert("��ѡ�����ͣ�");
				return ;
			}
			if(add_businesstype.value=="0"){
				alert("��ѡ��ҵ������");
				return ;
			}
			if(add_importance.value=="0"){
				alert("��ѡ����Ҫ��");
				return ;
			}			
			switch(type){
				case 1:
					document.ocsubaddform.realquesname.value=add_realquesname.value;
					document.ocsubaddform.realquesid.value=add_realquesid.value;				
					document.ocsubaddform.businesstype.value=add_businesstype.value;					
					document.ocsubaddform.importance.value=add_importance.value;		
					document.ocsubaddform.questiontype.value=type;
					document.ocsubaddform.submit();
					break;
				case 2:
					document.mcsubaddform.realquesname.value=add_realquesname.value;
					document.mcsubaddform.realquesid.value=add_realquesid.value;				
					document.mcsubaddform.businesstype.value=add_businesstype.value;					
					document.mcsubaddform.importance.value=add_importance.value;	
					document.mcsubaddform.questiontype.value=type;
					document.mcsubaddform.submit();	
					break;
				case 3:
					document.jdsubaddform.realquesname.value=add_realquesname.value;
					document.jdsubaddform.realquesid.value=add_realquesid.value;				
					document.jdsubaddform.businesstype.value=add_businesstype.value;					
					document.jdsubaddform.importance.value=add_importance.value;	
					document.jdsubaddform.questiontype.value=type;
					document.jdsubaddform.submit();	
					break;					
				case 4:
					document.dlsubaddform.realquesname.value=add_realquesname.value;
					document.dlsubaddform.realquesid.value=add_realquesid.value;				
					document.dlsubaddform.businesstype.value=add_businesstype.value;					
					document.dlsubaddform.importance.value=add_importance.value;	
					document.dlsubaddform.questiontype.value=type;
					document.dlsubaddform.submit();	
					break;	
				case 5:
					document.rcsubaddform.realquesname.value=add_realquesname.value;
					document.rcsubaddform.realquesid.value=add_realquesid.value;				
					document.rcsubaddform.businesstype.value=add_businesstype.value;					
					document.rcsubaddform.importance.value=add_importance.value;	
					document.rcsubaddform.questiontype.value=type;
					document.rcsubaddform.submit();	
					break;						
					
			}
		}
		
		function changeDiv(sv){
			switch(sv){
				case "0":
					ocdiv.style.display="none";
					mcdiv.style.display="none";					
					jddiv.style.display="none";
					dldiv.style.display="none";	
					rcdiv.style.display="none";						
					break;			
				case "1":
					ocdiv.style.display="block";
					mcdiv.style.display="none";					
					jddiv.style.display="none";
					dldiv.style.display="none";	
					rcdiv.style.display="none";					
					break;
				case "2":
					ocdiv.style.display="none";
					mcdiv.style.display="block";					
					jddiv.style.display="none";
					dldiv.style.display="none";	
					rcdiv.style.display="none";					
					break;
				case "3":
					ocdiv.style.display="none";
					mcdiv.style.display="none";					
					jddiv.style.display="block";
					dldiv.style.display="none";	
					rcdiv.style.display="none";					
					break;
				case "4":
					ocdiv.style.display="none";
					mcdiv.style.display="none";					
					jddiv.style.display="none";
					dldiv.style.display="block";
					rcdiv.style.display="none";					
					break;
				case "5":
					ocdiv.style.display="none";
					mcdiv.style.display="none";					
					jddiv.style.display="none";
					dldiv.style.display="none";
					rcdiv.style.display="block";						
					break;					
			}
		}
	</script>
	<BODY >
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
			  <td height="3" colspan="3"></td>
			</tr>
			<tr> 
				<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
				<td background="../images/min_02.gif">
					<div align="center">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
			            	<tr> 
				            	<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
				                <td><strong> &nbsp;����¼��</strong>
			              			<div align="left"></div></td>
			                </tr>
			             </table>
		             </div>
		        </td>
		    	<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
			</tr>
		    <tr> 
				<td height="5" colspan="3"></td>
		    </tr>
		</table>   
		<table><tr height=15><td></td></tr></table>
		
		
			<table width=100% border=0 cellspadding=2 cellspacing =2 >
				<tr bgcolor=#ffffff>
					<td>
						<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
						<tr bgcolor="#ffffff" height=30 >
							<td  width=5% align=right>&nbsp;��Ŀ����</td>
							<td align=left colspan=5>
								<input type=text id="add_realquesname" size=90 name=realquesname value="" >
								<input type=hidden id="add_realquesid" name="add_realquesid" value="">
								<a href="#" class=b onClick="showSubData()">�����������ѡ��</a>
							</td>
						</tr>
						<tr bgcolor="#ffffff">
							<td align=right width=5%>ҵ�����ͣ�</td> 
							<td align=left width=8%>
								<select id="add_businesstype" name="add_businesstype">
									<option value=0>��ѡ��</option>
									<option value=1>�Ǳ���</option>
									<option value=2>����</option>
								</select>
							</td>
							<td align=right width=8%>�������ͣ�</td>
							<td align=left width=8%>
								<select id="add_questiontype" name="add_questiontype" onChange="changeDiv(this.options[selectedIndex].value)">
									<option value=0>��ѡ��</option>
									<option value=1>��ѡ��</option>
									<option value=2>��ѡ��</option>
									<option value=3>�ж���</option>
									<option value=4>�ʴ���</option>
									<option value=5>¼����</option>									
								</select>
							</td>
							<td align=right width=8%>��Ҫ�ԣ�</td>
							<td align=left width=8%>
								<select id="add_importance" name="add_importance">
									<option value=0>��ѡ��</option>
									<option value=1>��ͨ</option>
									<option value=2>�ϸ�</option>
									<option value=3>�ܸ�</option>
								</select>								
							</td>
							</tr>

						</table>
					</td>
				</tr>
				<tr  bgcolor=#ffffff>
					<td>
						<div id="ocdiv" >
							<form name="ocsubaddform" action="<%=request.getContextPath() %>/questionServlet" method="post">
								<input type=hidden name="actionType" value="ocsubadd">
								<input type=hidden name="realquesname" value="">
								<input type=hidden  name="realquesid" value="">
								<input type=hidden name="businesstype" value="">
								<input type=hidden  name="importance" value="">
								<input type=hidden  name="questiontype" value="">	
								<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
								<tr><td>&nbsp;��ѡ�������</td></tr>
								<tr bgcolor="#ffffff" height=50 >
									<td  width=5%>
										<ul style="list-style-type:upper-alpha">
											<li><input type=text id="add_ockeyname1" name=add_ockeyname >
											<input type=hidden id="add_ockeyid1" name=add_ockeyid >
											<a href="#" class=b onClick="showKeyData(1,1)">�����д𰸿���ѡ��</a>
											<li><input type=text id="add_ockeyname2" name=add_ockeyname >
											<input type=hidden id="add_ockeyid2" name=add_ockeyid >
											<a href="#" class=b onClick="showKeyData(1,2)">�����д𰸿���ѡ��</a>
											<li><input type=text id="add_ockeyname3" name=add_ockeyname >
											<input type=hidden id="add_ockeyid3" name=add_ockeyid >
											<a href="#" class=b onClick="showKeyData(1,3)">�����д𰸿���ѡ��</a>
											<li><input type=text id="add_ockeyname4" name=add_ockeyname >
											<input type=hidden id="add_ockeyid4" name=add_ockeyid >
											<a href="#" class=b onClick="showKeyData(1,4)">�����д𰸿���ѡ��</a>																																							
										</ul>
										&nbsp;&nbsp;&nbsp;��ȷ�𰸣�
										<input type=radio name="add_occrctkey" value="A">A
										<input type=radio name="add_occrctkey" value="B">B
										<input type=radio name="add_occrctkey" value="C">C
										<input type=radio name="add_occrctkey" value="D">D
									</td>
								</tr>
								<tr bgcolor="#E0F0FD">
									<td colspan=6 align=center>
										<input type=button onClick="checkSubmit(1)" class="BigButton" value="��������">
									</td>
								</tr>								
								</table>
							</form>
						</div>	
						<div id="mcdiv"  >
							<form name="mcsubaddform" action="<%=request.getContextPath() %>/questionServlet" method="post">
								<input type=hidden name="actionType" value="mcsubadd">
								<input type=hidden name="realquesname" value="">
								<input type=hidden  name="realquesid" value="">
								<input type=hidden name="businesstype" value="">
								<input type=hidden  name="importance" value="">	
								<input type=hidden  name="questiontype" value="">
							<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
							<tr><td>&nbsp;��ѡ�������</td></tr>
							<tr bgcolor="#ffffff" height=50 >
								<td  width=5%>
									
									<ul style="list-style-type:upper-alpha">
										<li><input type=text id="add_mckeyname1" name=add_mckeyname >
										<input type=hidden id="add_mckeyid1" name=add_mckeyid >
										<a href="#" class=b onClick="showKeyData(2,1)">�����д𰸿���ѡ��</a>
										<li><input type=text id="add_mckeyname2" name=add_mckeyname >
										<input type=hidden id="add_mckeyid2" name=add_mckeyid >
										<a href="#" class=b onClick="showKeyData(2,2)">�����д𰸿���ѡ��</a>
										<li><input type=text id="add_mckeyname3" name=add_mckeyname >
										<input type=hidden id="add_mckeyid3" name=add_mckeyid >
										<a href="#" class=b onClick="showKeyData(2,3)">�����д𰸿���ѡ��</a>
										<li><input type=text id="add_mckeyname4" name=add_mckeyname >
										<input type=hidden id="add_mckeyid4" name=add_mckeyid >
										<a href="#" class=b onClick="showKeyData(2,4)">�����д𰸿���ѡ��</a>																																							
									</ul>
									&nbsp;&nbsp;&nbsp;��ȷ�𰸣�
									<input type=checkbox name="add_mcorctkey" value="A">A
									<input type=checkbox name="add_mcorctkey" value="B">B
									<input type=checkbox name="add_mcorctkey" value="C">C
									<input type=checkbox name="add_mcorctkey" value="D">D									
								</td>
							</tr>
							<tr bgcolor="#E0F0FD">
								<td colspan=6 align=center>
									<input type=button onClick="checkSubmit(2)" class="BigButton" value="��������">
								</td>
							</tr>						
							</table>
							</form>
						</div>
						<div id="jddiv"  >
							<form name="jdsubaddform" action="<%=request.getContextPath() %>/questionServlet" method="post">
								<input type=hidden name="actionType" value="jdsubadd">
								<input type=hidden name="realquesname" value="">
								<input type=hidden  name="realquesid" value="">
								<input type=hidden name="businesstype" value="">
								<input type=hidden  name="importance" value="">		
								<input type=hidden  name="questiontype" value="">					
							<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
							<tr><td>&nbsp;�ж��������</td></tr>
							<tr bgcolor="#ffffff" height=50 >
								<td  width=5% >
									&nbsp;�𰸣�<br>
									&nbsp;&nbsp;��ȷ��<input type=radio id="add_jdkeyname" name=add_jdkeyname value="0" ><br>
									&nbsp;&nbsp;����<input type=radio id="add_jdkeyname" name=add_jdkeyname value="1">
								</td>
							</tr>
								<tr bgcolor="#E0F0FD">
									<td colspan=6 align=center>
										<input type=button onClick="checkSubmit(3)" class="BigButton" value="��������">
									</td>
								</tr>							
							</table>
							</form>
						</div>												
						<div id="dldiv"  >
							<form name="dlsubaddform" action="<%=request.getContextPath() %>/questionServlet" method="post">
								<input type=hidden name="actionType" value="dlsubadd">
								<input type=hidden name="realquesname" value="">
								<input type=hidden  name="realquesid" value="">
								<input type=hidden name="businesstype" value="">
								<input type=hidden  name="importance" value="">
								<input type=hidden  name="questiontype" value="">	
							<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
							<tr><td>&nbsp;�ʴ��������</td></tr>
							<tr bgcolor="#ffffff" height=50 >
								<td  width=5% >&nbsp;�𰸣�<br>
									&nbsp;<textarea id="add_dlkeyname" name=add_dlkeyname style="width:600;height:100;overflow:visible"></textarea>
								</td>
							</tr>
							<tr bgcolor="#E0F0FD">
								<td colspan=6 align=center>
									<input type=button onClick="checkSubmit(4)" class="BigButton" value="��������">
								</td>
							</tr>							
							</table>
							</form>
						</div>
						<div id="rcdiv" >
							<form name="rcsubaddform" action="<%=request.getContextPath() %>/questionServlet" method="post">
								<input type=hidden name="actionType" value="rcsubadd">
								<input type=hidden name="realquesname" value="">
								<input type=hidden  name="realquesid" value="">
								<input type=hidden name="businesstype" value="">
								<input type=hidden  name="importance" value="">
								<input type=hidden  name="questiontype" value="">	
							<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
							<tr><td>&nbsp;¼���������</td></tr>
							<tr bgcolor="#ffffff" height=50 >
								<td  width=5% >&nbsp;�𰸣�<br>
									&nbsp;<textarea id="add_rckeyname" name=add_rckeyname style="width:600;height:100;overflow:visible"></textarea>
								</td>
							</tr>
							<tr bgcolor="#E0F0FD">
								<td colspan=6 align=center>
									<input type=button onClick="checkSubmit(5)" class="BigButton" value="��������">
								</td>
							</tr>							
							</table>
							</form>
						</div>						
					</td>
				</tr>
			</table>
	</BODY>
</HTML>
