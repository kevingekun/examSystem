<%@page language="java" contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>错页面</title>
<script language="javascript">

function autoHeight()
{
	var obj = document.getElementById("content1");
	var h = document.body.clientHeight;
	obj.style.height=(h-12)+"px";
	
}

</script>
<style type="text/css">
<!--

html{ height:100%;}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #ebf3f6;
	height:100%;
	
}
-->
</style>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body >
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="98%" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      </table>
      <div id="content1" class="borader">
        <table  width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" align="center" valign="middle" ><form action="" method="post" name="query_form" id="query_form">
                <table width="505" height="166" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:70px;">
                  <tr>
                    <td align="center" valign="top"  background="images/ok_3.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="37%">&nbsp;</td>
                        <td width="63%" class="system_font1">错误！<c:out value="${wrongMessage}"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td align="left">&nbsp;</td>
                      </tr>
                    </table>
                      <div class="tips1">
                        <input type="reset" name="Submit4" value="确定" class="submit_2" onclick="goback()"/>
                    </div></td>
                  </tr>
                </table>
              </form></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
<script language="javascript">

	var obj = document.getElementById("content1");
	var h = document.body.clientHeight;
	obj.style.height=(h-12)+"px";
	
	
	function goback(url){
		window.location.href='<%=request.getContextPath()%>/<c:out value="${backUrl}"/>';
	}
</script>
</body>
</html>