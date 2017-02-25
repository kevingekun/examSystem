<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="com.wondersgroup.falcon.Util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <link href="../css/style.css" rel="stylesheet" type="text/css" />

  </head>
  <%
 String NodeId = ParamUtil.getStringParameter(request,"id","");
 String NodeName = ParamUtil.getStringParameter(request,"name","");
 //System.out.println(NodeId+"----------------");
 //System.out.println(NodeName+"----------------");
 
   %>
  
  <body>
  
  <%
  if(NodeName.equals("P")){
   %>
  <!-- 政策法规 -->
    <table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="98%" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      </table>
      <div id="content1" class="borader">
        <table  width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" align="center" valign="middle" ><form action="toView_st.action" method="post" name="form" id="form">
            <input type="hidden" name="policyNodeId" value="<%=NodeId %>"/>
                <table width="505" height="166" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:70px;">
                  <tr>
                    <td align="center" valign="top"  background="../images/ok1.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="37%">&nbsp;</td>
                        <td width="63%" class="system_font1"><font color="#FF0000">操作成功！</font></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td align="left">&nbsp;</td>
                      </tr>
                    </table>
                      <div class="tips1">
                        <input type="submit" name="Submit_2" value="确定" class="submit_2" />
                    </div></td>
                  </tr>
                </table>
              </form></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
<%
}else if(NodeName.equals("S")){
 %>
<!-- 办事指南 -->
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="98%" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      </table>
      <div id="content1" class="borader">
        <table  width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" align="center" valign="middle" ><form action="toView_Sst.action" method="post" name="form" id="form">
            <input type="hidden" name="serviceNodeId" value="<%=NodeId %>"/>
                <table width="505" height="166" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:70px;">
                  <tr>
                    <td align="center" valign="top"  background="../images/ok1.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="37%">&nbsp;</td>
                        <td width="63%" class="system_font1"><font color="#FF0000">操作成功！</font></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td align="left">&nbsp;</td>
                      </tr>
                    </table>
                      <div class="tips1">
                        <input type="submit" name="Submit_2" value="确定" class="submit_2" />
                    </div></td>
                  </tr>
                </table>
              </form></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>

<%
}else if(NodeName.equals("f")){
 %>

<!-- 问答资料 -->
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="98%" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      </table>
      <div id="content1" class="borader">
        <table  width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" align="center" valign="middle" ><form action="toView_Fst.action" method="post" name="form" id="form">
            <input type="hidden" name="faqNodeId" value="<%=NodeId %>"/>
                <table width="505" height="166" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:70px;">
                  <tr>
                    <td align="center" valign="top"  background="../images/ok1.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="37%">&nbsp;</td>
                        <td width="63%" class="system_font1"><font color="#FF0000">操作成功！</font></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td align="left">&nbsp;</td>
                      </tr>
                    </table>
                      <div class="tips1">
                        <input type="submit" name="Submit_2" value="确定" class="submit_2" />
                    </div></td>
                  </tr>
                </table>
              </form></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>

<%
}else if(NodeName.equals("c")){
 %>
<!-- 学习资料 -->
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="98%" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      </table>
      <div id="content1" class="borader">
        <table  width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" align="center" valign="middle" ><form action="toView_Cst.action" method="post" name="form" id="form">
            <input type="hidden" name="caseNodeId" value="<%=NodeId %>"/>
                <table width="505" height="166" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:70px;">
                  <tr>
                    <td align="center" valign="top"  background="../images/ok1.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="37%">&nbsp;</td>
                        <td width="63%" class="system_font1"><font color="#FF0000">操作成功！</font></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td align="left">&nbsp;</td>
                      </tr>
                    </table>
                      <div class="tips1">
                        <input type="submit" name="Submit_2" value="确定" class="submit_2" />
                    </div></td>
                  </tr>
                </table>
              </form></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>

<%} %>





  </body>
</html>
