


<%@ page contentType = "text/html;charset=gb2312" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>排班菜单</title>

<link href="../inc/all.css" rel="stylesheet" type="text/css">

	<link rel="STYLESHEET" type="text/css" href="css/dhtmlXTree.css">
	<script  src="js/dhtmlXCommon.js"></script>
	<script  src="js/dhtmlXTree.js"></script>
	<script>
		function goto(i){
			switch(i){
				case 1:
					frm.location="exam_answerpaper_query.jsp";break;	
				case 2:
					frm.location="exam_andwerpaper_grade.jsp";break;						
			}
		}
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
<body scroll="no">
<table bgcolor="#F2F8FE" width="90%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	<tr >	
  		<td>
        	<table width="100%" height="25" border="0" cellpadding="0" cellspacing="1" bgcolor="#9FBED8">
           		<tr>
      				<td height="1" align="left"  bgcolor="#95EDFE"></td>
           		</tr>
				<tr bgcolor=#CEE7FC align=center height=30 onmouseover="this.style.backgroundColor='#F2F8FE';this.style.fontSize='24px'" onmouseout="this.style.backgroundColor='#CEE7FC'">
					<td>
						<b><a href="#"  onclick="goto(1)">&nbsp;<font color="#FE8652">答&nbsp;卷&nbsp;查&nbsp;询</font></a></b>
					</td>
				</tr>
				<tr bgcolor=#CEE7FC align=center height=30 onmouseover="this.style.backgroundColor='#F2F8FE';this.style.fontSize='24px'" onmouseout="this.style.backgroundColor='#CEE7FC'">
					<td>
						<b><a href="#"  onclick="goto(2)">&nbsp;<font color="#FE8652">答&nbsp;卷&nbsp;审&nbsp;阅</font></a></b>
					</td>
				</tr>
         		<tr>
         			<td>
            			<div id="treebox" style="width:166;height:396;background-color:#F2F8FE;border :1px solid Silver;; overflow:auto;">
								
            			</div>        				
            		</td>
            	</tr>
 			</table>	            		
		</td>	            		
 	</tr>
</table>
</body>
</html>

