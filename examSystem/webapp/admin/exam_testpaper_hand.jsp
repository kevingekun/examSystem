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
		
	  function fPopUpCalendarDlg(textname,startYear,endYear,q){
				var pattern = /^(19|20)([0-9]){2}$/;
				flag=pattern.test(startYear);
				if(!flag)startYear=1900;
				flag=pattern.test(endYear);
				if(!flag)endYear=2050;
			
				today=new Date();
				
				var currentDate = today.getYear() + "-" + today.getMonth() + "-" + today.getDay();
			
				var arguments = new Array(startYear,endYear,0,0,0);
				
				var pattern = /^(19|20)([0-9]){2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
				flag=pattern.test(currentDate);
				if(flag)
				{
					iYear=currentDate.substring(0,4);
					iMonth=currentDate.substring(5,7);
					iDay=currentDate.substring(8,10);
					arguments = new Array(startYear,endYear,iYear,iMonth,iDay);
				}
			
			
				showx = event.screenX - event.offsetX + 18; 
				showy = event.screenY - event.offsetY - 210; 
				
				var features =
					'dialogWidth:'  + 180 + 'px;' +
					'dialogHeight:' + 230 + 'px;' +
					'dialogLeft:'   + showx     + 'px;' +
					'dialogTop:'    + showy     + 'px;' +
					'directories:no; location:no; status:no; menubar:no; toolbar=no;scrollbars=no;Resizeable=no; help:0';
				
				retval = window.showModalDialog("8.htm", arguments , features );
				
				if( retval != null ){
					textname.value = retval;
				}
		}
		
		function checkon(id,chec){
			if(chec==true){
				document.getElementById(id).style.display="block";		
			}else{
				document.getElementById(id).style.display="none";			
			}
		}
		
		function checksubmit(){
			var subtypechecks = document.getElementsByName("subtypecheck");
			var count=0;
			for(var i=0;i<subtypechecks.length;i++){
				if(subtypechecks[i].checked==true)count++;
			}
			if(count==0){
				alert("��û��ѡ���κ�����!");
				return ;
			}
			
			document.exam_autocreform.submit();	
		}
		
		function paperPreview(){
			document.exam_handcreform.submit();
		}	
		
		
		function querySubject(){
			var realsubname = document.getElementById("query_realsubname").value;
			var businesstype = document.getElementById("query_businesstype").value;
			var subjecttype = document.getElementById("query_subjecttype").value;
			var importance = document.getElementById("query_importance").value;
			requestXml("<%=request.getContextPath()%>/querysubservlet?actionType=querysubject&realsubname="+realsubname+"&businesstype="+businesstype+"&&subjecttype="+subjecttype+"&&importance="+importance+"");		
		}
		
		var xmlhttp = false;
		
		function requestXml(url){
			if(window.ActiveXObject){
				try{
					xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			
			xmlhttp.onreadystatechange=jiexi;
			xmlhttp.open("GET",url,true);
			xmlhttp.send(null);
		}
		
		function jiexi(){
			if(xmlhttp.readystate==4){
				if(xmlhttp.status==200){
					if(xmlhttp.responseText!=null){
						//alert(xmlhttp.responseText);
						document.getElementById("queryresult").innerHTML=xmlhttp.responseText;
					}
				}
			}
		}
		
		function checkall(chec){
			var singlechecks = document.getElementsByName("singlecheck");
			for(var i=0;i<singlechecks.length;i++){
				singlechecks[i].checked=chec;
			}
		}
		var counts = 1;
		function getSubjects(){
			var singlechecks = document.getElementsByName("singlecheck");
			for(var i=0;i<singlechecks.length;i++){
				if(singlechecks[i].checked==true){
					//alert(singlechecks[i].value);
					var subname =document.getElementById(singlechecks[i].value).innerText;
					var subtype = document.getElementById("type"+singlechecks[i].value).innerText;

					if(subtype=="��ѡ��"){
						 var divstr = "<div id='div"+(singlechecks[i].value)+"' value='"+subname+"' "+
						 ">�� "+subname+"<input type=hidden name='realsubid' value='"+(singlechecks[i].value)+"'>&nbsp;&nbsp;"+
				   		 "<font color=#0000ff style=\"cursor:hand\" onmouseover=\"this.style.color='#ff0000'\" onmouseout=\"this.style.color='#0000ff'\" "+
				   		 "onclick=\"javascript:removediv('div"+(singlechecks[i].value)+"','ocseletedsubjects')\" >�Ƴ�"+
				   		 "</div>";
						ocseletedsubjects.innerHTML+=divstr;				
					}else if(subtype=="��ѡ��"){
						 var divstr = "<div id='div"+(singlechecks[i].value)+"' value='"+subname+"' "+
						 ">�� "+subname+"<input type=hidden name='realsubid' value='"+(singlechecks[i].value)+"'>&nbsp;&nbsp;"+
				   		 "<font color=#0000ff style=\"cursor:hand\" onmouseover=\"this.style.color='#ff0000'\" onmouseout=\"this.style.color='#0000ff'\" "+
				   		 "onclick=\"javascript:removediv('div"+(singlechecks[i].value)+"','mcseletedsubjects')\" >�Ƴ�"+
				   		 "</div>";			
						mcseletedsubjects.innerHTML+=divstr;		
					}else if(subtype=="�ж���"){
						 var divstr = "<div id='div"+(singlechecks[i].value)+"' value='"+subname+"' "+
						 ">�� "+subname+"<input type=hidden name='realsubid' value='"+(singlechecks[i].value)+"'>&nbsp;&nbsp;"+
				   		 "<font color=#0000ff style=\"cursor:hand\" onmouseover=\"this.style.color='#ff0000'\" onmouseout=\"this.style.color='#0000ff'\" "+
				   		 "onclick=\"javascript:removediv('div"+(singlechecks[i].value)+"','jdseletedsubjects')\" >�Ƴ�"+
				   		 "</div>";				
						jdseletedsubjects.innerHTML+=divstr;		
					}else if(subtype=="�ʴ���"){
						 var divstr = "<div id='div"+(singlechecks[i].value)+"' value='"+subname+"' "+
						 ">�� "+subname+"<input type=hidden name='realsubid' value='"+(singlechecks[i].value)+"'>&nbsp;&nbsp;"+
				   		 "<font color=#0000ff style=\"cursor:hand\" onmouseover=\"this.style.color='#ff0000'\" onmouseout=\"this.style.color='#0000ff'\" "+
				   		 "onclick=\"javascript:removediv('div"+(singlechecks[i].value)+"','dlseletedsubjects')\" >�Ƴ�"+
				   		 "</div>";				
						dlseletedsubjects.innerHTML+=divstr;		
					}

					singlechecks[i].checked=false;
					counts++;
				}
			}
			alert("�����ϣ���ע��鿴��");
		}
		
		function removediv(id,divname){
			//alert(id+" and "+divname);
			var parentdiv = document.getElementById(divname);
			var childdiv = document.getElementById(id);
			parentdiv.removeChild(childdiv);
		}
	</script>
	<BODY >
		<br>
		<form name="exam_handcreform" action="<%=request.getContextPath() %>/examServlet" method="post">
		<input type=hidden name="actionType" value="handcreate">
		<table width=99% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD" align=center>
			<tr bgcolor="#ffffff"  height=20>
				<td align=center>�˹�����Ծ��굥</td>
			</tr>
			
			<tr ><td>&nbsp;�Ծ��������</td></tr>
			<tr bgcolor=#ffffff>
				<td>
					<table width=100% border=0 >
						<tr align=center>
							<td align=right>
								�Ծ���:
							</td>
							<td align=left>
								<input type=text name="examname" value="�����Ծ�һ">
							</td>
							<td align=right>
								�Ծ��ܷ�:
							</td>
							<td align=left>
								<input type=text name="exammark" size="2" value="100">
							</td>
							<td align=right>
								����ʱ��:
							</td >
							<td align=left>
								<input type=text name="examtime" value="60">(����)
							</td>
						</tr>
						<tr >
							<td  align=right>
								��Ч����:</td>
							<td colspan=5 align=left>
								<input type=text name="effectstarttime" value="2008-1-1">&nbsp;<img border="0" src="images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(effectstarttime,1949,2010,1)">
								-
								<input type=text name="effectendtime" value="2008-12-31">&nbsp;<img border="0" src="images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(effectendtime,1949,2010,1)">
							</td>
						</tr>						
					</table>
				</td>
			</tr>

			<tr height=20 bgcolor="#E0F0FD">		
				<td>&nbsp;�˹�ѡ��</td>
			</tr>
			<tr id="onechoice" bgcolor="#ffffff" style="display:block" height=50>		
				<td >
					<table width=99% border=0 cellspadding=1 cellspacing =1 align=center bgcolor="#E0F0FD">
						<tr bgcolor=#ffffff><td>
						<table  width=100% border=0 cellspadding=0 cellspacing =0>
						<tr bgcolor="#ffffff" height=50 >
							<td align=left width=9%>&nbsp;��������</td>
							<td align=left width=20%><input type=text id="query_realsubname" name=realsubname ></td>
							<td align=right width=10%>ҵ�����ͣ�</td>
							<td align=left width=10%>
								<select id="query_businesstype">
									<option value=0>��ѡ��</option>
									<option value=1>�Ǳ���</option>
									<option value=2>����</option>
								</select>
							</td>
							<td align=right width=10%>�������ͣ�</td>
							<td align=left width=10%>
								<select id="query_subjecttype">
									<option value=0>��ѡ��</option>
									<option value=1>��ѡ��</option>
									<option value=2>��ѡ��</option>
									<option value=3>�ж���</option>
									<option value=4>�ʴ���</option>
								</select>
							</td>
							<td align=right width=10%>��Ҫ�ԣ�</td>
							<td align=left width=10%>
								<select id="query_importance">
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
							<tr bgcolor="#ffffff">
								<td align=center colspan=8><input type=button class="smallbutton" onclick="querySubject()" value="����"></td>
							</tr>
						</table>
						<table><tr><td></td></tr></table>
						<table width=99% border=0 cellpadding=0 cellspacing=0 align=center>
						<tr bgcolor="#ffffff">
							<td >
								<div id="queryresult"></div>
							</td>
						</tr>	
					</table>
					<table><tr><td></td></tr></table>
					<table width=99% border=0 cellspadding=1 cellspacing =1 align=center bgcolor="#E0F0FD">
						<tr>
							<td>��ѡ���� &nbsp;&nbsp;ÿ�������<input type=text name="onechoicemark" style="width:30;height:20;" value="1">(��)</td>
						</tr>
						<tr bgcolor=#ffffff>
							<td>
														
							</td>
						</tr>						
						<tr bgcolor="#ffffff">
							<td>
								<div id="ocseletedsubjects"></div>
							</td>
						</tr>
						<tr>
							<td>��ѡ���� &nbsp;&nbsp;ÿ�������<input type=text name="manychoicemark" style="width:30;height:20;" value="2">(��)</td>
						</tr>
						<tr>
							<td></td>
						</tr>						
						<tr bgcolor="#ffffff">
							<td><div id="mcseletedsubjects""></div>
							</td>
						</tr>
						<tr>
							<td>�ж����� &nbsp;&nbsp;ÿ�������<input type=text name="judgemark" style="width:30;height:20;" value="2">(��)</td>
						</tr>
						<tr>
							<td></td>
						</tr>						
						<tr bgcolor="#ffffff">
							<td><div id="jdseletedsubjects">
							</td>
						</tr>
						<tr>
							<td>�ʴ����� &nbsp;&nbsp;ÿ�������<input type=text name="dialogmark" style="width:30;height:20;" value="10">(��)</td>
						</tr>
						<tr>
							<td></td>
						</tr>						
						<tr bgcolor="#ffffff">
							<td><div id="dlseletedsubjects"></div>
							</td>
						</tr>																								
					</table>
				</td>
			</tr>	
			<tr bgcolor=#ffffff>
				<td align=center ><input type=button class="BigButton" onclick="paperPreview()" value="�Ծ�Ԥ��"></td>
			</tr>												
		</table>
		</form>
	</BODY>
</HTML>
