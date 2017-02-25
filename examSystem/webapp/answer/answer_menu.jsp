<%@ page contentType = "text/html;charset=gb2312" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<link href="../css/all.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/JavaScript">
<!--

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

		function goto(i){
			switch(i){
				case 1:
					frm.location="tosypaperAction.action?djSyzt=0";break;	
				case 2:
					frm.location="tosypaperAction.action?djSyzt=1";break;						
				case 3:
					frm.location="answerPaperQuerryAction.action";break;						
				case 4:
					frm.location="tosypaperAction.action?";break;						
						
			}
		}
		
//-->
</script>


	
</head>

<style>
	.gddiv{
		height:30;
		margin-left:30;
		
	}
	.gddiv2{
		margin-left:30;
		cursor:hand;
	}
</style>

<body  scroll="no">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bg">
	<tr>
    	<td>
    		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        		<tr> 
          			<td height="5" colspan="5"></td>
        		</tr>
        		<tr> 
          			<td width="177"><img src="../images/m_01.gif" width="177" height="46"></td>
					<td width="5" background="../images/m_05.gif"><img src="../images/m_02.gif" width="5" height="46"></td>
					<td width="68" background="../images/m_05.gif"><img src="../images/m_04.gif" width="68" height="46"></td>
					<td background="../images/m_05.gif">
	
            		</td>
          			<td width="23" background="../images/m_05.gif"><img src="../images/m_06.gif" width="23" height="46"></td>
        		</tr>
    		</table>
      		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
        		<tr> 
          			<td width="177" valign="top" background="../images/lf_03.gif" bgcolor="#FFFFFF">
					<img src="../images/lf_01_2.gif" width="134" height="32"> 
            			<table width="95%" border="0" cellpadding="0" cellspacing="0">
              				<tr> 
                				<td >
                					
									


<table bgcolor="#F2F8FE" width="90%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	<tr >	
  		<td>
        	<table width="100%" height="25" border="0" cellpadding="0" cellspacing="1" bgcolor="#9FBED8">
           		<tr>
      				<td height="1" align="left"  bgcolor="#95EDFE"></td>
           		</tr>
				<tr bgcolor=#CEE7FC align=center height=30 onMouseOver="this.style.backgroundColor='#F2F8FE';this.style.fontSize='24px'" 
				onMouseOut="this.style.backgroundColor='#CEE7FC'">
					<td><b><a href="#"  onclick="goto(1)">&nbsp;<font color="#FE8652">答 卷 审 阅</font></a></b></td>
				</tr>
				<tr bgcolor=#CEE7FC align=center height=30 onMouseOver="this.style.backgroundColor='#F2F8FE';this.style.fontSize='24px'" 
				onMouseOut="this.style.backgroundColor='#CEE7FC'">
					<td><b><a href="#"  onclick="goto(2)">&nbsp;<font color="#FE8652">二 次 审 阅</font></a></b></td>
				</tr>
				<tr bgcolor=#CEE7FC align=center height=30 onMouseOver="this.style.backgroundColor='#F2F8FE';this.style.fontSize='24px'" 
				onMouseOut="this.style.backgroundColor='#CEE7FC'">
					<td><b><a href="#"  onclick="goto(3)">&nbsp;<font color="#FE8652">答 卷 查 询</font></a></b></td>
				</tr>
				
				<tr bgcolor=#CEE7FC align=center height=30 onMouseOver="this.style.backgroundColor='#F2F8FE';this.style.fontSize='24px'" 
				onMouseOut="this.style.backgroundColor='#CEE7FC'">
					<td><b><a href="#"  >&nbsp;<font color="#FE8652"></font></a></b></td>
				</tr>		           						    		
         		<tr>
         			<td><div id="treebox" style="width:166;height:396;background-color:#F2F8FE;border :1px solid Silver;; overflow:auto;"></div></td>
            	</tr>
         		<tr>
         		  <td>&nbsp;</td>
       		  </tr>
 			</table>	            		
		</td>	            		
 	</tr>
</table>
									
									
									
									
            					</td>
              				</tr>
            			</table>
          			</td>
          			<td width="6" bgcolor="#FFFFFF">&nbsp;</td>
          			<td valign="top" bgcolor="#FFFFFF"> 
		  				<iframe  id="frm" src='<%=request.getContextPath()%>/answerPaperQuerryAction.action' width="100%" height="584" scrolling="auto" frameborder="0"></iframe>
					</td>
          			<td width="22" background="../images/lf_02.gif" bgcolor="#FFFFFF">&nbsp;</td>
       			</tr>
        		<tr> 
					<td background="../images/lf_03.gif" align="right"><img src="../images/menu_03.gif" width="163" height="12"></td>
					<td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
					<td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
					<td background="../images/lf_02.gif" bgcolor="#FFFFFF"><img src="../images/menu_05.gif" width="22" height="12"></td>
        		</tr>
      		</table>
      	</td>
 	</tr>
</table>
</body>
</html>

