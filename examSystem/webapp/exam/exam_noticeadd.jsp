<%@ page contentType="text/html;charset=gbk"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
<!--

	function checksubmit(){
		if(trim(document.formvip.title.value)==""){
			window.errormessage.innerText = "标题不能为空！";
			return;
		}
		if(document.formvip.exampaperid.value==""){
			alert("没有试卷，请选择试卷！");
			return ;
		}
		if(trim(document.formvip.content.value)==""){
			window.errormessage.innerText = "内容不能为空！";
			return;
		}
		if(document.formvip.effectdate.value==""||document.formvip.noteffectdate.value==""){
			alert("请选择时间！");
			return ;
		}
		document.formvip.submit();
	}
	
	function checksubmit1(){
		document.formvip.comments.value=="1"
		document.formvip.submit();
	}
	
	function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g,"");
	}
	
	function getlenb(tstr)
	{
		j=0
		for (i=0;i<tstr.length;i++)
		{
			if(tstr.charCodeAt(i)<0||tstr.charCodeAt(i)>255)  j++;
			j++;
		}
		return j;
	}


	function getExampaper(){
		var members = new Object();
		members=showModalDialog('exampaperlist.jsp',window,'dialogWidth:600px;dialogHeight:500px;');
		if(members!=null){
			document.getElementById("examname").innerHTML="&nbsp;"+members.namevalue;
			//alert(members.id);
			document.formvip.exampaperid.value=members.id;
		}
	}

	function fPopUpCalendarDlg(textname,startYear,endYear,q)
	{
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
		
		retval = window.showModalDialog("../8.htm", arguments , features );
		
		if( retval != null ){
			textname.value = retval;
		}
	}
//-->
</script>
</head>

<body>
<form name="formvip" method="post" action="exam_noticesend.jsp">
	<input type=hidden name="exampaperid" value="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr> 
	      <td height="5" colspan="4"></td>
	    </tr>
        <tr> 
          <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
          <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
          <td width="98%" background="../images/k1_03.gif"><strong>发送通告</strong></td>
          <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
        </tr>
        <tr>
          <td background="../images/k1_05.gif"></td>
          <td height="5" colspan="2"></td>
          <td background="../images/k1_06.gif">&nbsp;</td>
        </tr>
        <tr> 
			<td background="../images/k1_05.gif"></td>
			<td height="380" colspan="2" valign="top">
				<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
					<tr bgcolor="#FFFFFF"> 
	            		<td colspan="5" align=center><div style="color:#ff0000" id="errormessage" name="errormessage"></div></td>
	          		</tr>
	          		<tr>
						<td >
							<input type=button onclick="getExampaper()" class="BigButton" value="选择试卷">
						</td>
						<td colspan=4 bgcolor=#ffffff>
							<div id="examname"></div>
						</td>
					</tr>
					<tr bgcolor="#FFFFFF"> 
						<td align="right" width=5%>标 &nbsp;&nbsp;&nbsp;题</td>
				    	<td colspan=4><input type=text id="title" name="title" size=100 ></td>
					</tr>   
					<tr bgcolor="#FFFFFF"> 
				  		<td align=right>
				  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生效时间
				  		</td>
				  		<td width=10%>
				  			<input type=text name="effectdate" readonly>
				  			&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(effectdate,1949,2010,1)">
				  		</td>
				  		<td width=2% align=center>
				  			－－
				  		</td>
				  		<td width=4% align=right>
				  			失效时间
				  		</td>
				  		<td width=20%>
				  			<input type=text name="noteffectdate" readonly>
				  			&nbsp;<img border="0" src="<%=request.getContextPath() %>/images/0_cal.gif" style="cursor:hand;" onclick="return fPopUpCalendarDlg(noteffectdate,1949,2010,1)">
				  		</td>
					</tr>									                 
					<tr bgcolor="#FFFFFF"> 
				  		<td align="right">内 &nbsp;&nbsp;&nbsp;容
				    	</td>
				  		<td colspan=4><textarea id="content" name="content" style="width:600;height:300;"></textarea> </td>
					</tr>
					<tr bgcolor="#FFFFFF"> 
				  	<td colspan="5"> <div align="center"> 
				      	<table width="49" border="0" align="center" cellpadding="0" cellspacing="0">
				        	<tr> 
				          	<td ><input type=button class="SmallButton" onClick="javascript:checksubmit();" value="提 交"></td>
				        	</tr>
				      	</table>
				    	</div></td>
				   	
					</tr>
      			</table>
      		</td>
        	<td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
		</tr>
		<tr> 
	<!--  
		<a HREF="../sms_send.htm">发送消息</a>
	-->
                    
			<td><img src="../images/k1_07.gif" width="2" height="5"></td>
	  		<td colspan="2" background="../images/k1_08.gif"> </td>
	  		<td><img src="../images/k1_09.gif"></td>
		</tr>
	</table>
</form> 
             
</body>
</html>
