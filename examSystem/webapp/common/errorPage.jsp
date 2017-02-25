<%@ page language="java" contentType="text/html;charset=GB2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ page isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>错误提示页面</title>
		<link href="css/all.css" rel="stylesheet" type="text/css">
	</head>

	<body>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="3" colspan="3"></td>
  </tr>
  <tr> 
    <td width="8"><img src="images/min_01.gif" width="8" height="32"></td>
    <td background="images/min_02.gif"><div align="center"> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="15">&nbsp;</td>
            <td><div align="center"><strong><c:out value="${info}"/></strong></div></td>
          </tr>
        </table>
      </div></td>
    <td width="8"><img src="images/min_03.gif" width="8" height="32"></td>
  </tr>
<tr>
    <td height="5" colspan="3"></td>
</tr>
			  
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="5"><img src="images/k_01.gif" width="5" height="4"></td>
    <td background="images/k_02.gif"></td>
    <td width="6"><img src="images/k_03.gif" width="6" height="4"></td>
  </tr>
  <tr>
    <td background="images/k_04.gif"></td>
      <td height="50" bgcolor="#FFFFFF">
        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#EAF4FD">
          <tr bgcolor="#FFFFFF">
            <td align="center"><font color="#FF0000"><c:out value="${errorinfo}"/>
            </font></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="30" colspan="2" align="center">
			<input type="button" name="back" value="返回" class="SmallButton" onClick="history.back();"></td>
          </tr>
        </table>
      </td>
    <td background="images/k_05.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="images/k_06.gif" width="5" height="4"></td>
    <td background="images/k_07.gif"></td>
    <td><img src="images/k_08.gif" width="6" height="4"></td>
  </tr>
</table>
	
	</body>
</html>
