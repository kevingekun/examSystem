<%@ page contentType = "text/html;charset=gbk" %>
<%
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires",0);
String fileid = request.getParameter("fileid");
//System.out.println("fileid====>"+fileid);
String filename = request.getParameter("filename");
%>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<jsp:useBean id = "list" class="com.wondersgroup.falcon.beans.archives.FaqTree"/>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.model.archives.*"%>
<%@ page import="com.wondersgroup.falcon.model.auth.*"%>
<%@ page import="com.wondersgroup.falcon.model.dic.*"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.beans.archives.*"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<in>
<HEAD>
<META HTTP-EQUIV=”Cache-Control” CONTENT=”no-cache”>
<META HTTP-EQUIV=”Expires” CONTENT=”0”>
</HEAD>
	<style type="text/css">

	html{ height:100%;}
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #ebf3f6;
		height:100%;	
	}

</style>
<%
String id = request.getParameter("selectitem");
Hashtable policy = list.getPolicy(new Long(id));

if(session.getAttribute("callid")!=null){
		if(!session.getAttribute("callid").toString().equals("")){
			//out.print(session.getAttribute("callid"));
			list.writeLog(new Long(fileid.substring(0,fileid.length()-5)), new Long(session.getAttribute("callid").toString()), userid);
		}
}
PropertiesUtil pu =new PropertiesUtil();
String libstore = pu.getProperties("1111");
//System.out.println("libstore====>"+libstore+fileid);
String fileUrlPath = "../"+pu.getProperties("path.fabu.url")+"faq/"+user.getUserType()+"/";
String fax = "";
String name = "";
AbstractTree t = FactoryBean.creator("faq"); 
FaqNode fathernode = null;
if(fileid!=null&&fileid.length()>0&&!fileid.equals("null")){
 fathernode = (FaqNode)t.getNodeById(Node.getNodeType("faq"),new Long(fileid.substring(0,fileid.length()-5)));
 }
if (fathernode!=null&&fathernode.getName() != null) name = fathernode.getName();
%>

<SCRIPT language=javascript>
	
<!--//
//函数：写入cookie
function WriteCookie (cookieName, cookieValue, expiry) 

{
var expDate = new Date();

if(expiry)    //如果设置了cookie失效时间;
{
expDate.setTime (expDate.getTime() + expiry);
    document.cookie = cookieName + "=" + cookieValue + "; expires=" + expDate.toGMTString();
    }
    else   //没有设置cookie失效时间;
    {
        document.cookie = cookieName + "=" + cookieValue;
}
}

//函数：取得form表单域的值作为cookie的相关值（cookie name,cookie value,expires）
function setCookie (CookieName, CookieValue)
{
var name = CookieName;
var value = CookieValue;
var num = "0";
var select = "0";

if (name=="" || value=="" || num==""){
alert ("请输入Cookie的名字,值和失效期再测试!");
return false;
}

if(num == 0)
{
WriteCookie(name, value, 0);
}
else if(select == 0)
//如果选择的是天;时间换算成秒;
{
WriteCookie(name, value, 1000 * 60 * 60 * 24 * num);
}
else if(select == 1)
//如果选择的是月;
{
WriteCookie(name, value, 1000 * 60 * 60 * 24 * num * 31);
}
else if(select == 2)
//如果选择的是年;
{
WriteCookie(name, value, 1000 * 60 * 60 * 24 * num * 365);
}
//alert ("Cookie已经保存，欢迎访问www.1stscript.com")	
}

//函数：读cookie值;
function ReadCookie (CookieName) {
  var CookieString = document.cookie;
  var CookieSet = CookieString.split (';');
  var SetSize = CookieSet.length;
  var CookiePieces
  var ReturnValue = "";
  var x = 0;

  for (x = 0; ((x < SetSize) && (ReturnValue == "")); x++) {

    CookiePieces = CookieSet[x].split ('=');

    if (CookiePieces[0].substring (0,1) == ' ') {
      CookiePieces[0] = CookiePieces[0].substring (1, CookiePieces[0].length);
    }

    if (CookiePieces[0] == CookieName) {
      ReturnValue = CookiePieces[1];
      
    }

  }
	return ReturnValue;
  //alert ("Cookie Value is:"+ReturnValue);

}
//-->
</SCRIPT>
<script>
		function setc(id,name) {
			setscfm.fileid.value = id;
			setscfm.filetitle.value = name;
			if (setscfm.fileid.value == "" || setscfm.filetitle.value == "") return;
			setscfm.submit();
		}
</script>

           	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">详细内容<a href="#" onclick="history.back(1)"><img src="../images/ico/point2_15px.gif" alt="后退" width="15" height="11" border="0" /></a>&nbsp; &nbsp;<%if(fax!=null&&fax.equals("1")){ %> <a href="#" onclick="return setc('<%=fileid%>','<%=name%>')";><img src="../images/ico/2005042312303075s.gif" alt="添加至附件" width="14" height="16" border="0" /></a>&nbsp;&nbsp;<a href="../commonfunction/deskfax_input.jsp?faxid=<%= fileid.substring(0,fileid.length()-5)%>.tif&name= <%=name%>"  target='_self'><img src="../images/ico/83.gif" alt="传真" width="12" height="12" border="0" /></a>&nbsp;&nbsp;<%} %>
                      &nbsp;&nbsp; <%
	            	out.print("位置：" + list.getPath(new Long(fileid.substring(0,fileid.length()-5))) + "</font>");
	          %></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
        

<!--<iframe name=roll4  src=../libstore/policy/1369.html width="100%" height="95%" Frameborder=0 Border=0 Marginwidth=0 Marginheight=0 Scrolling=auto ></iframe>-->
<iframe name=roll4  src='<%=fileUrlPath %><%=fileid%>' width="100%" height="80%" Frameborder=0 Border=0 Marginwidth=0 Marginheight=0 Scrolling=auto ></iframe>
<form method = "post" action = "faq_list.jsp" name="list">
		<input type=hidden name=selectitem value="">
</form>
<script>
	if (ReadCookie("ID") == ""){
		setCookie("ID","2");
		setCookie("item1","<%=filename%>");
		setCookie("item1link","<%=fileid%>");
	}else if (ReadCookie("ID") == "2"){
		setCookie("ID","3");
		setCookie("item2","<%=filename%>");
		setCookie("item2link","<%=fileid%>");
	}else if (ReadCookie("ID") == "3"){
		setCookie("ID","1");
		setCookie("item3","<%=filename%>");
		setCookie("item3link","<%=fileid%>");
	}else if (ReadCookie("ID") == "1"){
		setCookie("ID","2");
		setCookie("item1","<%=filename%>");
		setCookie("item1link","<%=fileid%>");
	}
</script>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">关联列表</td>
                      </tr>
                    </table></td>
                </tr>
              </table>
              <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb" >
                <tr onmouseover="this.className='td_over'" onmouseout="this.className=''" id="r1">
                 <%
										int i = 0;
										if (policy != null){
											for (Enumeration e = policy.keys(); e.hasMoreElements();) {
												Object obj = e.nextElement();
												if (i == 0)
													out.println("<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'>");
												out.println("<td>");
												String u=(String)obj;
												//out.print("<a href='../libstore/policy/" + (String)obj + "' target=_blank>");
												out.print("<a title="+policy.get(obj)+" href='../law/law_file.jsp?fileid=" + (String)obj + "&filename=" + policy.get(obj) + "&&selectitem="+(String)obj.toString().substring(0,u.length()-5)+"' target=_self>");
												if(policy.get(obj).toString().length()>18)
												out.print(policy.get(obj).toString().substring(0,18));
												else out.print(policy.get(obj));
												out.println("</a>");
												out.println("</td>");
												if (i == 2)
													out.println("</tr>");
												i++;
												if (i >= 3)
													i = 0;
											}
										}
										if (i == 1){
											out.println("<td>&nbsp;</td>");
											i++;
										}
										if (i == 2)
											out.println("<td>&nbsp;</td></tr>");
										
										%>
                </tr>
              </table></td>
          </tr>
        </table>



                  
                </td>
               
            </table>

<form method = "post" action = "../setsession.jsp" name="setscfm">
	<input type=hidden name=fileid value="">
	<input type=hidden name=filetitle value="">
</form>
