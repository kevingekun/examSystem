<%@ page contentType = "text/html;charset=gb2312" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>

<jsp:useBean id = "Wonders" class="com.wondersgroup.falcon.beans.archives.PolicyTree"/>

<link href="../inc/all.css" rel="stylesheet" type="text/css">

	<link rel="STYLESHEET" type="text/css" href="css/dhtmlXTree.css">
	<script  src="js/dhtmlXCommon.js"></script>
	<script  src="js/dhtmlXTree.js"></script>

<script language="JavaScript" type="text/JavaScript">
<!--



function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//-->
</script>
	  <script language="JavaScript">
var openedid;
var openedid_ft;
var flag=0,sflag=0;



//-------- 菜单点击事件 -------
function clickHandler()
{
        var targetid,srcelement,targetelement;
        var strbuf;
        srcelement=window.event.srcElement;

        //-------- 如果点击了展开或收缩按钮---------
        if(srcelement.className=="outline")
        {
                if(srcelement.title!="" && srcelement.src.indexOf("plus")>-1)
                   menu_shrink();

                targetid=srcelement.id+"d";
                targetelement=document.all(targetid);

                if (targetelement.style.display=="none")
                {
                        targetelement.style.display='';
                        strbuf=srcelement.src;
                        if(strbuf.indexOf("plus.gif")>-1)
                                srcelement.src="../images/menu/tree_minus.gif";
                        else
                                srcelement.src="../images/menu/tree_minusl.gif";
                }
                else
                {
                        targetelement.style.display="none";
                        strbuf=srcelement.src;
                        if(strbuf.indexOf("minus.gif")>-1)
                                srcelement.src="../images/menu/tree_plus.gif";
                        else
                                srcelement.src="../images/menu/tree_plusl.gif";
                }
        }
}

document.onclick = clickHandler;

//-------- 打开网址 -------
function openURL(URL)
{
    parent.openURL(URL);
}

//-------- 菜单全部展开/收缩 -------
var menu_flag=0;
function menu_expand()
{
  menu_flag=1-menu_flag;

  for (i=0; i<document.all.length; i++)
  {
        srcelement=document.all(i);
        if(srcelement.className=="outline")
        {
                targetid=srcelement.id+"d";
                targetelement=document.all(targetid);
                if (menu_flag==1)
                {
                        targetelement.style.display='';
                        strbuf=srcelement.src;
                        if(strbuf.indexOf("plus.gif")>-1)
                                srcelement.src="../images/menu/tree_minus.gif";
                        else
                                srcelement.src="../images/menu/tree_minusl.gif";
                }
                else
                {
                        targetelement.style.display="none";
                        strbuf=srcelement.src;
                        if(strbuf.indexOf("minus.gif")>-1)
                                srcelement.src="../images/menu/tree_plus.gif";
                        else
                                srcelement.src="../images/menu/tree_plusl.gif";
                }
        }
  }
}

//-------- 收缩打开的主菜单项 -------
function menu_shrink()
{
  for (i=0; i<document.all.length; i++)
  {
        srcelement=document.all(i);
        if(srcelement.title!="")
        {
              strbuf=srcelement.src;

              if(strbuf.indexOf("minus")>-1)
              {
                 targetid=srcelement.id+"d";
                 targetelement=document.all(targetid);
                 targetelement.style.display='none';

                 if(strbuf.indexOf("minus.gif")>-1)
                    srcelement.src="../images/menu/tree_plus.gif";
                 else
                    srcelement.src="../images/menu/tree_plusl.gif";
               }
         }
  }
}

 
</script>  
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bg">
  <tr>
    <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td height="5" colspan="5"></td>
        </tr>
        <tr> 
          <td width="163"><img src="../images/m_01.gif" width="208" height="46"></td>
          <td width="5" background="../images/m_05.gif"><img src="../images/m_02.gif" width="5" height="46"></td>
          <td width="68" background="../images/m_05.gif"><img src="../images/m_04.gif" width="68" height="46"></td>
          <td background="../images/m_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td><script>
var marqueeContent=new Array();   //滚动新闻
marqueeContent[0]='<font color="#0000CC">14:25 </font><a href=#>小泉称若自民党在议会选举中失败</a><br>';
marqueeContent[1]='<font color="#0000CC">14:25 </font><a href=#>布什发表广播讲话</a><br>';
marqueeContent[2]='<font color="#0000CC">14:25 </font><a href=#>伊斯兰武装炸毁印控克什米尔铁路导致列车出轨</a><br>';
marqueeContent[3]='<font color="#0000CC">14:25 </font><a href=#>布雷默：即使抓住了萨达姆也难以结束抵抗行动</a><br>';
var marqueeInterval=new Array();  //定义一些常用而且要经常用到的变量
var marqueeId=0;
var marqueeDelay=2000;
var marqueeHeight=20;
//接下来的是定义一些要使用到的函数

		function onNodeSelect(id) {
			//alert(id +"Item was selected");
		}
		
		function onCheckselect(id) {
			if (tree.getUserData(id,"isDir") == "YES"){
				tree.setCheck(id,false);
			}
		}
function initMarquee() {
	var str=marqueeContent[0];
	document.write('<div id=marqueeBox style="overflow:hidden;height:'+marqueeHeight+'px" onmouseover="clearInterval(marqueeInterval[0])" onmouseout="marqueeInterval[0]=setInterval(\'startMarquee()\',marqueeDelay)"><div>'+str+'</div></div>');
	marqueeId++;
	marqueeInterval[0]=setInterval("startMarquee()",marqueeDelay);
	}
function startMarquee() {
	var str=marqueeContent[marqueeId];
		marqueeId++;
	if(marqueeId>=marqueeContent.length) marqueeId=0;
	if(marqueeBox.childNodes.length==1) {
		var nextLine=document.createElement('DIV');
		nextLine.innerHTML=str;
		marqueeBox.appendChild(nextLine);
		}
	else {
		marqueeBox.childNodes[0].innerHTML=str;
		marqueeBox.appendChild(marqueeBox.childNodes[0]);
		marqueeBox.scrollTop=0;
		}
	clearInterval(marqueeInterval[1]);
	marqueeInterval[1]=setInterval("scrollMarquee()",20);
	}
function scrollMarquee() {
	marqueeBox.scrollTop++;
	if(marqueeBox.scrollTop%marqueeHeight==(marqueeHeight-1)){
		clearInterval(marqueeInterval[1]);
		}
	}
initMarquee();
</script></td>
              </tr>
              <tr> 
                <td height="1"></td>
              </tr>
            </table></td>
          <td width="23" background="../images/m_05.gif"><img src="../images/m_06.gif" width="23" height="46"></td>
        </tr>
      </table>
      <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
        <tr> 
          <td width="208" valign="top" background="../images/lf_03.gif"><img src="../images/lf_01_2.gif" width="150" height="25"> 
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="10"></td>
              </tr>
            </table>
            <table bgcolor="#F2F8FE" width="90%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            	<tr >
            		
	            	<td>
                  <!-- CALLCENTER树标题 -->
                  <table width="100%" height="25" border="0" cellpadding="0" cellspacing="1" bgcolor="#9FBED8">
                    <tr> 
                      <td align="center" bgcolor="#CEE7FC"><a href="javascript:menu_expand()">展开/收缩</a> 
                      </td>
                    </tr>
                    <tr>
                      <td height="1" align="center"  bgcolor="#95EDFE">
					  					</td>
                    </tr>
             			</table>
	            		<div id="treebox" style="width:195;height:430;background-color:#F2F8FE;border :1px solid Silver;; overflow:auto;"/>
	            	</td>
            	</tr>
								<script>
									tree = new dhtmlXTreeObject("treebox","100%","100%",0);
							
									tree.setImagePath("images/");
									tree.enableTreeLines(true);
									//tree.enableThreeStateCheckboxes(true);
									tree.setOnCheckHandler(onCheckselect);
									tree.setOnClickHandler(onNodeSelect);
									tree.setImageArrays("plus","plus2.gif","plus3.gif","plus4.gif","plus.gif","plus5.gif");
									tree.setImageArrays("minus","minus2.gif","minus3.gif","minus4.gif","minus.gif","minus5.gif");
							//		tree.setStdImages("book.gif","books_open.gif","books_close.gif");					
							
									<%
										Wonders.freshTree_proscenium(out);
									%>
									//tree.refreshItem(3);
									</script>
            </table>
          </td>
          <td width="6" bgcolor="#FFFFFF">&nbsp;</td>
          <td valign="top" bgcolor="#FFFFFF"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="3" colspan="3"></td>
              </tr>
              <tr> 
                <td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
                <td background="../images/min_02.gif"><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <td width="15"><strong><img src="../images/ico_03.gif" width="13" height="16"></strong></td>
                        <td><strong> 法规查询</strong> <div align="left"></div></td>
                      </tr>
                    </table>
                  </div></td>
                <td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
              </tr>
              <tr> 
                <td height="5" colspan="3"></td>
              </tr>
            </table>
            <table width="100%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
              <tr> 
                <td bgcolor="#FFFFFF"><table width="98%" border="0" align="center" cellpadding="1" cellspacing="2">
                    <tr> 
                      <td width="49" rowspan="2"><div align="center"><img src="../images/ico/sc.gif" width="54" ></div></td>
                      <td width="66" nowrap>关键字：</td>
                      <td><input name="textfield" type="text" size="20"></td>
                      <td nowrap>文号：</td>
                      <td nowrap><input name="textfield2" type="text" size="20"></td>
                      <td width="268">&nbsp;</td>
                    </tr>
                    <tr> 
                      <td>业务种类：</td>
                      <td width="128" ><input name="textfield2" type="text" size="20"></td>
                      <td width="61" >时 间：</td>
                      <td width="167" > <table border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td><input name="textfield3" type="text" size="10"></td>
                            <td> <img src="../images/calendar.gif" width="18" height="18"></td>
                            <td>&nbsp;</td>
                            <td>&nbsp; </td>
                          </tr>
                        </table></td>
                      <td > <table width="49" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td class="SmallButton"><div align="center">查 询</div></td>
                          </tr>
                        </table></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5" colspan="4"></td>
              </tr>
              <tr> 
                <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
                <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
                <td width="98%" background="../images/k1_03.gif">法规列表</td>
                <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
              </tr>
              <tr> 
                <td background="../images/k1_05.gif"></td>
                <td height="300" colspan="2" valign="top"> 
                  <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
                    <tr bgcolor="#FFFFFF"> 
                      <td width="182" height="23"><div align="center">文件编号</div></td>
                      <td width="581"><div align="center">标 题 </div></td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td height="2" colspan="2" bgcolor="#F1F8FE"></td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
                  
                </td>
                <td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
              </tr>
              <tr> 
                <td><img src="../images/k1_07.gif" width="2" height="5"></td>
                <td colspan="2" background="../images/k1_08.gif"> </td>
                <td><img src="../images/k1_09.gif"></td>
              </tr>
            </table>
            
          </td>
          <td width="22" background="../images/lf_02.gif" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
        <tr> 
          <td background="../images/lf_03.gif"><img src="../images/menu_03.gif" width="163" height="12"></td>
          <td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
          <td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
          <td background="../images/lf_02.gif" bgcolor="#FFFFFF"><img src="../images/menu_05.gif" width="22" height="12"></td>
        </tr>
      </table></td>
  </tr>
</table>


</body>
</html>
