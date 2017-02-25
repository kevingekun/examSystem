<%@ page contentType = "text/html;charset=gbk" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wondersgroup.falcon.model.call.Zhijiankaohe" %>
<%@ page import="com.wondersgroup.falcon.dao.call.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>xx</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../js/base.js"></script>
<script  src="../js/floatwin.js"></script>
<script type="text/javascript" src="../js/calendar.js"></script>
<script type="text/JavaScript">

<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->

function ok(){
  // alert(document.add.fenlei.value);
	if(document.add.name.value==""){
		alert("请输入评估内容");
		return;
		}
	if(document.add.value1.value==""){
		alert("请输入百分比");
		return;
		}
	document.add.submit();
}

function open1(id){
	//alert(id);
	var html = "<iframe id=\"treekinds\" frameborder=0 width=\"100%\" height=\"347\" src=\"tkwh/yichu.jsp?id="+id+"\" ></iframe>"
			addtype(html);
	//window.showModelessDialog("tkwh/yichu.jsp?id="+id+"",window,"status=0;help:no;dialogTop:250;dialogLeft:215;dialogWidth:13;dialogHeight:24");
}

function addtype(html){
	var a = new xWin("1",169,357,200,30,"操作分类维护",html);
}

function DoEmpty(params)
{

if (confirm("确定要删除吗？"))
window.location = "main_delete.jsp?id=" + params ;
}
</script>
<script type="text/javascript" src="../js/base.js"></script></head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="58" style="height:28px;"><img src="images/lb.gif" width="58" height="28" /></td>
    <td bgcolor="#CEE3F7" style="background-image:url(images/jk_bg.gif);"><strong>质检考核表维护</strong></td>
    <td width="30" nowrap bgcolor="#CEE3F7" style="background-image:url(images/jk_bg.gif);">&nbsp;<a id="tclose" href="javascript:switchShow('tclose','tbox');">关闭</a></td>
    <td width="13"><img src="images/jk_16.gif" width="13" height="28" /></td>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5"></td>
  </tr>
</table>
<form action="main_addpingfen.jsp" name="add">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"id="tbox" >
  <tr>
    <td width="6"><img src="images/foot02.gif" width="6" height="5" /></td>
    <td background="images/foot03.gif"></td>
    <td width="6"><img src="images/foot04.gif" width="6" height="5" /></td>
  </tr>
  <tr>
    <td background="images/foot05.gif"></td>
    <td  bgcolor="#EAF8FD"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0">
      <tr>
        <td width="11%" bgcolor="#EAF8FD"><strong> 所属分类: </strong></td>
        <td width="19%" bgcolor="#EAF8FD"><strong>
          <select name="id">
          <option value="" >-请选择</option>
		<%
		List all = new ArrayList();
		Zhijiankaohe pn = new Zhijiankaohe();
		Zhijiankaohe pn1 = new Zhijiankaohe();
		Zhijiankaohe pn2 = new Zhijiankaohe();
		
		ZhijiankaoheDAO dao = new ZhijiankaoheDAO();
		all=dao.findAll();
		for(int m=0;m<all.size();m++){
			pn=(Zhijiankaohe)all.get(m);
		%>
		<option value="<%=pn.getId() %>">
		<%
			
		 %><%=pn.getName() %>
		 </option>
		 <%
		 	}
		  %>
          </select>
        </strong></td>
        <td width="11%" bgcolor="#EAF8FD">&nbsp;</td>
        <td bgcolor="#EAF8FD">&nbsp;</td>
        <td bgcolor="#EAF8FD">&nbsp;</td>
        <td bgcolor="#EAF8FD">&nbsp;</td>
        <td width="8%" bgcolor="#EAF8FD">&nbsp;</td>
      </tr>
      <tr>
        <td bgcolor="#EAF8FD"><strong>评估内容:</strong></td>
        <td bgcolor="#EAF8FD"><input id="name" size="30" name="name"></td>
        <td bgcolor="#EAF8FD">&nbsp;</td>
        <td bgcolor="#EAF8FD"><strong>比重（%）:</strong></td>
        <td bgcolor="#EAF8FD"><input id="value1" size="30" name="value1"><font color=red>（只填数字！）</font></td>
        <td bgcolor="#EAF8FD">&nbsp;</td>
      </tr>
        <tr>
        <td bgcolor="#EAF8FD"><strong>评分标准:</strong></td>
        <td bgcolor="#EAF8FD"><TEXTAREA id=comments name=comments rows=3 cols=30 style="overflow:auto" ></TEXTAREA></td>
        
      </tr>
      <tr>
        <td colspan="3" bgcolor="#EAF8FD"><strong><a href="javascript:ok()" class="bg"><img src="images/bt_xj.gif" width="48" height="21" border="0" /></a></strong></td>
        <td bgcolor="#EAF8FD"><strong><a href="#" class="bg"></a></strong></td>
        
      </tr>
    </table></td>
    <td background="images/foot09.gif"></td>
  </tr>
  <tr>
    <td><img src="images/foot06.gif" width="6" height="5" /></td>
    <td background="images/foot07.gif"></td>
    <td><img src="images/foot08.gif" width="6" height="5" /></td>
  </tr>
</table>
</form>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5"></td>
  </tr>
</table>
<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
  <tr>
    <td width="3"><img src="images/k1_01.gif" width="3" height="30" /></td>
    <td width="21" background="images/k1_03.gif"><img src="images/k1_02.gif" width="21" height="30" /></td>
    <td width="50%" background="images/k1_03.gif"><strong>分类一览（操作分类）</strong></td>
    <td width="50%" background="images/k1_03.gif">&nbsp;</td>
    <td width="4" background="images/k1_03.gif"><img src="images/k1_04.gif" width="4" height="30" /></td>
  </tr>
  <tr>
    <td background="images/k1_05.gif"></td>
    <td colspan="3" valign="top"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" bgcolor="#CCCCCC">
      <tr>
        <td width="65%" bgcolor="#92D6E4"><div align="left"><strong>评估内容</strong></div></td>
        <td width="35%" bgcolor="#92D6E4"><div align="left">操 作</div></td>
        </tr>
      <tr>
        <td height="2" colspan="3" bgcolor="#28B9FF"></td>
      </tr>
        <%
      	
		
		
		List tree = null;
		List subtree = null;
		List subtree1 = null;
		Long id=new Long(0);
		Long id1=new Long(0);
		Long id2=new Long(0);
		tree=dao.getFirst();
      	for(int i=0;i<tree.size();i++){
      		pn=(Zhijiankaohe)tree.get(i);
      		out.print("<tr><td width='65%' bgcolor='#ffffff'><img src='images/TreeBranch1.gif' width='18' height='18'>");
      		out.print(pn.getName()+"("+pn.getValue()+"%)</td>");
      		id=pn.getId();
      		if(dao.getChildren(id)==null||dao.getChildren(id).size()==0){
      			out.print("<td bgcolor='#ffffff'><a href=main_modify.jsp?id="+id+" target=\"_self\">修改</a>  <a href='javascript:DoEmpty("+id+");'>删除</a></td></tr>");
      			out.print("<tr><td height='3' colspan='3' background='images/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
      		}
      		else if(dao.getChildren(id)!=null&&dao.getChildren(id).size()!=0){
      		    out.print("<td bgcolor='#ffffff'><a href=main_modify.jsp?id="+id+" target=\"_self\">修改</a></td></tr>");
      			out.print("<tr><td height='3' colspan='3'  bgcolor='#FFFFFF'></td></tr>");
      			subtree=dao.getChildren(id);
				for(int j=0;j<subtree.size();j++){
					pn1=(Zhijiankaohe)subtree.get(j);
					out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/TreeBranch1.gif' width='18' height='18'>");
					out.print(pn1.getName()+"("+pn1.getValue()+"%)</td>");
      				id1=pn1.getId();
      				if(dao.getChildren(id1)==null||dao.getChildren(id1).size()==0){
      				out.print("<td bgcolor='#ffffff'><a href=main_modify.jsp?id="+id1+" target=\"_self\">修改</a>  <a href='javascript:DoEmpty("+id1+");'>删除</a></td></tr>");
      				out.print("<tr><td height='3' colspan='3' background='images/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
      			}
      				else if(dao.getChildren(id1)!=null&&dao.getChildren(id1).size()!=0){
      					 out.print("<td bgcolor='#ffffff'><a href=main_modify.jsp?id="+id1+" target=\"_self\">修改</a></td></tr>");
      					out.print("<tr><td height='3' colspan='3'  bgcolor='#FFFFFF'></td></tr>");
      					subtree1=dao.getChildren(id1);
						for(int k=0;k<subtree1.size();k++){
							pn2=(Zhijiankaohe)subtree1.get(k);
							out.print("<tr><td width='65%' bgcolor='#ffffff'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='images/TreeBranch1.gif' width='18' height='18'>");
							out.print(pn2.getName()+"("+pn2.getValue()+"%)</td>");
							out.print("<td bgcolor='#ffffff'><a href=main_modify.jsp?id="+pn2.getId()+" target=\"_self\">修改</a>  <a href='javascript:DoEmpty("+pn2.getId()+");'>删除</a></td></tr>");
							out.print("<tr><td height='3' colspan='3' background='images/dot_line.gif' bgcolor='#FFFFFF'></td></tr>");
						}
      				}
				}
      		}
      	}
       %>
 
      
  
      <tr>
        <td width="65%" bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
      <tr>
        <td width="65%" bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
    </table>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="40" height="1" bgcolor="#DFDFDF"></td>
          </tr>
          <tr>
            <td height="1" bgcolor="#D9D9D9"></td>
          </tr>
    </table></td>
    <td background="images/k1_06.gif"></td>
  </tr>
  <tr>
    <td background="images/k1_05.gif"><img src="images/k1_07.gif" width="3" height="5" /></td>
    <td colspan="3" background="images/k1_08.gif"></td>
    <td background="images/k1_08.gif"><img src="images/k1_09.gif" width="4" height="5" /></td>
  </tr>
</table>
<form action="admin/guibinfl.do" name="guibininfo">
<input type=hidden name=flid value="">
<input type=hidden name=guibin value="">
</form>
</body>
</html>
