<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*"  %>
<%@page import="com.wondersgroup.falcon.dao.archives.*"%>
<%@page import="com.wondersgroup.falcon.model.archives.*"%>
<%@page import="com.wondersgroup.falcon.beans.archives.*"%>
<%@page import="com.wondersgroup.falcon.acegi.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wondersgroup.falcon.Util.*"%>

<%
	String cname = RequestUtils.getString(request, "cname", "policy");
	String status = RequestUtils.getString(request, "status", "");
	
	
	//通过用户ID 缺的菜单操作权限 
	//AcegiUtil acegiUtil=new AcegiUtil();
	//Long userid = ((UserDetailsImpl)acegiUtil.getUserDetails()).getUser().getId();
	 
	//取得政策法规的导航树形菜单数据库	
	AbstractTree tree  = FactoryBean.creator(cname);
	List nodeList1 =tree.getNavigationTree(Node.getNodeType(cname),Long.parseLong(0+"")); 
	System.out.println("YYYYYYYYYYYYY"+nodeList1);
   
    
    //根节点
    Node nodeBase = null;
    if(nodeList1.size()>=1){
    	nodeBase = (Node)nodeList1.get(0);
    	System.out.println("dddf"+nodeBase.getParent()+"uuuuuu");
    } 
	String localSiteBaseURL = "http://" + request.getServerName();
	String http = localSiteBaseURL ;	
%>

<%!
private void createItem(Node node, javax.servlet.jsp.JspWriter out, String http,String cname) throws  IOException{
	String menustr = createMenu(node,http);
	out.println(menustr);
	String name = node.getName();
	if(!node.isVisible()){
		//name = "<font color=red>"+name +"</font>";
	}
	out.println("item" + node.getId() + " = gFld(\"<span class=word10 title="+name+">" +name + "</span>\",\"\",\"show(" + node.getId() + ",this);\")");
	//out.println("item" + node.getId() + " = gFld(\"<span class=gray10>" + node.getId() + "</span>\",\"\",\"noright();\")");
	out.println("item" + node.getId() + ".xID = \"c" + node.getId() +"\"");
	
	AbstractTree tree  = FactoryBean.creator(cname);
	List al = new ArrayList();
	try{
		al =tree.getNavigationTreeChildNodes(Node.getNodeType(cname),node.getId());
	}catch (Exception ex) {
		System.out.println("子节点查询错误");
	}
	for(int i=0; i<al.size(); i++){
		Node nd = (Node)al.get(i);
		if(nd.isVisible())
		createItem(nd, out,  http,cname);
	}
	String addStr = new String("");
	for(int j=0; j<al.size(); j++){
		Node nd1 = (Node)al.get(j);
		if(nd1.isVisible())
		addStr = addStr + ",item" + nd1.getId() ;
	}
	if(addStr.length() >2)
		if(node.isVisible())
		out.println("item" + node.getId() + ".addChildren([" + addStr.substring(1)+ "])");
			
}

private String createMenu(Node node,String http)  {
			
	StringBuffer str = new StringBuffer();
	//Collection  rights = channelService.getUserRightList(loginUserId, channel.getChannelid());
	String folderico = "chimg"  + ".gif";
	String menu = "menuchannel" + node.getId(); 
			
	str.append("var menuchannel" + node.getId() + "=new Array()\n");

	str.append(menu + "[0] = '<table border=\"0\" cellspacing=\"1\" cellpadding=\"1\" width=\"100%\" align=\"center\">'\n");
	str.append(menu + "[1] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\" onclick=doAction(\"policy_list.jsp?nodeid=" + node.getId() + "\") class=\"word10\"><td style=\"height:16px; cursor:hand\" >&nbsp;&nbsp;文档列表</td></tr>'\n");

	
	
	//if(isAdmin || channelService.hasPermission(rights,"CMSCHANNEL_LIST"))
				
	//else
	//	str.append(menu + "[2] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\"  class=\"gray12\"><td style=\"height:16px; cursor:hand\" >&nbsp;&nbsp;文档列表</td></tr>'\n");
		
	
	//if(channelService.hasPermission(loginUserId, channel.getChannelid(),"CMSCHANNEL_PUB", isAdmin)&& ismanager == 0 && currstatus == 1)
		
	//else
	//		str.append(menu + "[6] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\" class=\"gray12\"><td style=\"height:16px; cursor:hand\">&nbsp;&nbsp;栏目发布</td></tr>'\n");
	
	//if((isAdmin || channelService.hasPermission(rights,"CMSCHANNEL_HTTP")) && ismanager == 0 && currstatus == 1)
		
	//else
	//		str.append(menu + "[20] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\"  class=\"gray12\"><td style=\"height:16px; cursor:hand\" >&nbsp;&nbsp;浏览(本地)</td></tr>'\n");
	//if((isAdmin || channelService.hasPermission(rights,"CMSCHANNEL_HTTP")) && ismanager == 0 && currstatus == 1)
		
	//else
	//		str.append(menu + "[21] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\"  class=\"gray12\"><td style=\"height:16px; cursor:hand\" >&nbsp;&nbsp;浏览(网站)</td></tr>'\n");
		//str.append(menu + "[30] = '<tr><td style=\"height:1px;\"></td></tr><tr><td style=\"height:1px;\" bgcolor=\"#999999\"></td></tr><tr><td style=\"height:1px;\"></td></tr>'\n");
	//if(isAdmin || channelService.hasPermission(rights,"CMSCHANNEL_VIEW"))
		//str.append(menu + "[32] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\" onclick=doAction(\"../site/channel_edit.jsp?channelid=" + "" + "&view=1\")  class=\"word10\"><td style=\"height:16px; cursor:hand\" >&nbsp;&nbsp;栏目设置</td></tr>'\n");
	//else
	//		str.append(menu + "[32] = '<tr onmouseover=javascript:this.style.background=\"#B6C9D8\" onmouseout=javascript:this.style.background=\"#E0E9F1\" class=\"gray12\"><td style=\"height:16px; cursor:hand\" >&nbsp;&nbsp;栏目设置</td></tr>'\n");
	str.append(menu + "[50] = '</table>'\n");
	return str.toString();
	
}
	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>channel_tree</title>
<style type="text/css">
<!--
body {
	background-color: #F2F7FC;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,td,th {
	font-size: 12px;
}
-->
</style>
<style type="text/css">
<!--

a:link {
	color: #000000;
	text-decoration: underline;
}
a:visited {
	text-decoration: underline;
	color: #000000;
}
a:hover {
	text-decoration: underline;
	color: #000000;
}
a:active {
	text-decoration: underline;
	color: #000000;
}
.wordblack14b {	font-size: 12px;
	font-weight:bold;	
}
.word10b {	
	font-size: 12px;
	font-weight:bold;	
	margin-left: 1px;
}
.word10 {
	font-size: 12px;
	margin-left: 1px;
	
}
.gray10 {
	font-size: 12px;
	margin-left: 1px;
	color:#999999;
	
}
.gray12 {
	font-size: 12px;
	color:#999999;
	
}
-->
</style>
<style type="text/css">

#dropmenudiv{
position:absolute;
border:1px solid #999999;
font:normal 12px Verdana;
line-height:18px;
z-index:100;
}

</style>
<STYLE type=text/css>
#loader_container {
LEFT: 1px; WIDTH: 100%; POSITION: absolute; TOP: 1px; TEXT-ALIGN: left
}
#loader {
BORDER-RIGHT: #5a667b 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #5a667b 1px solid; 
DISPLAY: block; PADDING-LEFT: 0px; FONT-SIZE: 11px; Z-INDEX: 2; 
PADDING-BOTTOM: 1px; MARGIN: 0px auto; BORDER-LEFT: #5a667b 1px solid; 
WIDTH: 130px; COLOR: #000000; PADDING-TOP: 1px; BORDER-BOTTOM:#5a667b 1px solid; 
FONT-FAMILY: Tahoma, Helvetica, sans; BACKGROUND-COLOR:#FFFFCA; TEXT-ALIGN: left
}
</STYLE>
<script language="javascript" src="<%=request.getContextPath()%>/kaoshi/question/js/menu.js"></script>
<script language="javascript">

function autoHeight()
{
	var obj = document.getElementById("content1");
	var h = document.body.clientHeight;
	obj.style.height=(h-34)+"px";
	
}

</script>

<script language="JavaScript">
function doAction(u){
	parent.frm.location.href=u;
}
function closefrm(){
	parent.fr.cols="0,*";
}

function selectSite(){
	parent.main.location.href="main.jsp";
	document.form1.submit();
}
function reloadWin(){
	document.location.href="<%=request.getContextPath()%>/kaoshi/question/law_tree1.jsp";
}
function noright(){
	alert("抱歉，您未授权访问该栏目!");
}
function show(m, obj) {
	//dropdownmenu(obj, event, m, "90px");
	
	doAction("<%=request.getContextPath()%>/kaoshi/question/policy_list.jsp?nodeid="+m);
}

function doAlertAction(u) {
	if(confirm("是否真的删除栏目?\n\n警告：删除栏目将使得该站点下的子栏目和文档一并删除！")){
		parent.main.location.href=u;
	}
}
function openWin(s){
	window.open(s);
}
</script>

<%if("1".equals(status)){%>
<script>
	doAction("policy_list.jsp");
</script>
<%} %>

<SCRIPT language=JavaScript>
var t_id = setInterval(animate,20);
function animate()
{
  //
}
function remove_loading() {
this.clearInterval(t_id);
var targelem = document.getElementById('loader_container');
targelem.style.display='none';
targelem.style.visibility='hidden';
}


</SCRIPT>
<script src="<%=request.getContextPath()%>/kaoshi/question/js/ua.js"></script>
<script src="<%=request.getContextPath()%>/kaoshi/question/js/ftiens4.js"></script>
<script>
function expandTree(folderObj)
{
    var childObj;
    var i;

    //Open folder
    if (!folderObj.isOpen)
      clickOnNodeObj(folderObj)

    //Call this function for all folder children
    for (i=0 ; i < folderObj.nChildren; i++)  {
      childObj = folderObj.children[i]
      if (typeof childObj.setState != "undefined") {//is folder
        expandTree(childObj)
      }
    }
}

// Close all folders
function collapseTree()
{
	//hide all folders
	clickOnNodeObj(foldersTree)
	//restore first level
	clickOnNodeObj(foldersTree)
}

// In order to show a folder, open all the folders that are higher in the hierarchy 
// all the way to the root must also be opened.
// (Does not affect selection highlight.)
function openFolderInTree(linkID) 
{
	var folderObj;
	folderObj = findObj(linkID);
	folderObj.forceOpeningOfAncestorFolders();
	if (!folderObj.isOpen)
		clickOnNodeObj(folderObj);
} 

</script>
</head>
<body onload=remove_loading() scroll="yes"> 
<div id="content1" class="borader">

<form name="form1" action="<%=request.getContextPath()%>/kaoshi/question/site_tree.jsp" method="get" >
<DIV id=loader_container align=left>
<DIV id=loader>
<DIV align=left><img src="<%=request.getContextPath() %>/kaoshi/question/images/loading.gif"  align="absmiddle">页面正在加载中 ...</DIV>
</DIV></DIV>
<DIV>

<table width="210" border="0" cellpadding="0" cellspacing="0">
	<tr>
   		<td height="1" colspan="2" bgcolor="#AAC1DE"></td>
  	</tr>
  <tr>
    <td valign="top"><table width="210" height="100%" border="0" cellspacing="0" cellpadding="0" class="dialoginnerbox">
      <tr>
        <td width="100%" align="left" valign="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="10"><table width="100%"  border="0" cellspacing="0">
                <tr>
                  <td width="40%" height="25" bgcolor="#A2D5FF"><img src="<%=request.getContextPath()%>/kaoshi/question/images/open.gif">文档管理</td>
                  <td width="60%" bgcolor="#A2D5FF" align="center">
				  <a href="javascript:collapseTree(1)" title="折叠"><img src="<%=request.getContextPath()%>/kaoshi/question/images/min_view.gif" width="16" height="16" border="0"></a><a href="javascript:expandTree(foldersTree)" title="展开"><img src="<%=request.getContextPath()%>/kaoshi/question/images/max_view.gif" width="16" height="16" border="0"></a><a href="javascript:reloadWin()" title="刷新"><img src="<%=request.getContextPath()%>/kaoshi/question/images/reload.gif" width="13" height="16" border="0" align="absmiddle"></a><a href="javascript:closefrm()" title="关闭"><img src="<%=request.getContextPath()%>/kaoshi/question/images/close_view.gif" width="16" height="16" border="0" align="absmiddle" title="关闭"></a></td>
				</tr>
                <tr>
                  <td height="1" colspan="2" bgcolor="#AAC1DE"></td>
                </tr>
              </table>                
               </td>
			   </tr>
             
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
       	
<table width="210" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="top">

<script>
var menusite<%=nodeBase.getId()%>=new Array()
menusite<%=nodeBase.getId()%>[0] = '<table border="0" cellspacing="1" cellpadding="1" width="100%" align="center">'
menusite<%=nodeBase.getId()%>[1] = '<tr onmouseover=javascript:this.style.background="#B6C9D8" onmouseout=javascript:this.style.background="#E0E9F1" onclick=doAction("<%=request.getContextPath()%>/kaoshi/question/policy_list.jsp?nodeid=<%=nodeBase.getId()%>")  class="word10"><td style="height:16px; cursor:hand" ></td></tr>'
menusite<%=nodeBase.getId()%>[50] = '</table>'
</script>
<script>

USETEXTLINKS = 1  
STARTALLOPEN = 0
HIGHLIGHT = 1
PRESERVESTATE = 1
GLOBALTARGET="R"
ICONPATH = "<%=request.getContextPath()%>/kaoshi/question/images/";

foldersTree = gFld("", "#" ,null)
foldersTree.treeID = "L1" 
foldersTree.xID = "root" 
foldersTree.iconSrc = ICONPATH + "blank.gif"

site = gFld("<div class=word10b title=<%=nodeBase.getName()%>><%=nodeBase.getName()%></div>", "" ,"<%=1>0?"show("+nodeBase.getId()+",this)":null%>;")
site.treeID = "S1" 
site.xID = "site" 
site.iconSrc = ICONPATH + "root.gif"
site.iconSrcClosed = ICONPATH + "root.gif"

<%

	AbstractTree tr = FactoryBean.creator(cname);
	List nodeList =tr.getNavigationTreeChildNodes(Node.getNodeType(cname),nodeBase.getId());

	for(int i = 0; i < nodeList.size(); i++){
		Node nd = (Node)nodeList.get(i);		
		createItem(nd, out, http,cname);
	}
	String addStr = new String("");
	for(int j = 0; j < nodeList.size(); j++){
		Node nd = (Node)nodeList.get(j);	
		addStr = addStr + ",item" + nd.getId() ;
	}
	if(addStr.length() > 2)
		out.println("site.addChildren([" + addStr.substring(1)+ "])");
	
%>
foldersTree.addChildren([site])
</script>
<script>initializeDocument()</script>               
	</td>
  </tr>
</table>	


</DIV>

</form>
</div>
</body>
</html>

