<%@ page contentType = "text/html;charset=gb2312" %>
<%@ include file="/include.jsp" %>
<%@ page import="com.wondersgroup.falcon.exceptions.InfrastructureException"%>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil"%>
<%@ page import="com.wondersgroup.exam.Summary"%>
<%@ page import="com.wondersgroup.exam.ServiceType"%>
<%@ page import="com.wondersgroup.exam.SummaryDAO"%>
<%@ page import="com.wondersgroup.exam.ServiceTypeDAO"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.wondersgroup.falcon.model.auth.User"%>
<%@ page import="com.wondersgroup.falcon.acegi.*"%>

<%
	if(session.getAttribute("callid")==null){
		response.sendRedirect("no_call.jsp");
	}
%>
<%
	String isforce = request.getParameter("isforce")==null?"":request.getParameter("isforce");
	String summary = request.getParameter("summary")==null?"":request.getParameter("summary");
	String servicetype[] = request.getParameterValues("servicetype");
	Date curdate = new Date();
	SummaryDAO smDAO = new SummaryDAO();
	ServiceTypeDAO stDAO = new ServiceTypeDAO();
	Summary sm = new Summary();
	ServiceType[] st = new ServiceType[100];
	
	User user = new User();
	user = ((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser();

	Long callid = new Long(0);
	if(session.getAttribute("callid")!=null){
		if(!(session.getAttribute("callid").toString().equals(""))){
			try{
				callid = new Long(session.getAttribute("callid").toString());
			}
			catch(Exception e){
				callid = null;
			}
		}
	}
	
	Long userid = new Long(0);
	if(user!=null){
		userid = user.getId();
	}
	else{
		userid = null;
	}
	

	
	if(isforce.equals("1")){         //ǿ�Ƹ���ԭ����������Ϣ
		//����Ѿ�������˴������Ҫ��Ϣ������ɾ��ԭ������Ϣ
		if(smDAO.getSummaryList(callid,userid).size()>0 
			|| stDAO.getServiceTypeList(callid,userid).size()>0){
			smDAO.deleteSummaryList(callid,userid);
			stDAO.deleteServiceTypeList(callid,userid);
		}	
		//�����µ�������Ϣ(���������Ҫ�ͷ������)
		//���������Ҫ			
		sm.setSummary(summary);
		//����������ˮ��
		if(session.getAttribute("callid")!=null){
			if(!(session.getAttribute("callid").toString().equals(""))){
				Long l = new Long(0);
				try{
					l = new Long(session.getAttribute("callid").toString());
				}
				catch(Exception e){
					l = null;
				}
				sm.setCallid(l);
			}
		}
		//����༭��Աid
		if(user!=null){
			sm.setUserid(user.getId());
		}

		sm.setEventtime(curdate);
		smDAO.addSummary(sm);
		
		//����������
		if(servicetype!=null){
			int i = 0;
			while(i < servicetype.length){
				st[i] = new ServiceType();
	
				st[i].setServicetype(servicetype[i]);
				//����������ˮ��
				if(session.getAttribute("callid")!=null){
					if(!(session.getAttribute("callid").toString().equals(""))){
						Long l = new Long(0);
						try{
							l = new Long(session.getAttribute("callid").toString());
						}
						catch(Exception e){
							l = null;
						}
						st[i].setCallid(l);
					}
				}
				//����༭��Աid
				if(user!=null){
					st[i].setUserid(user.getId());
				}
		
				st[i].setEventtime(curdate);
	
				stDAO.addServiceType(st[i]);
				i++;
			}
		}
	    HibernateUtil.commitTransaction();
    	HibernateUtil.closeSession();
		response.sendRedirect("insert_result.jsp");
	
	}	
	else{							//��ǿ�Ƹ���ԭ����������Ϣ
		//���ϵͳ��û�д˴�������Ϣ������˴�������Ϣ��Ȼ����ת������ɹ����ҳ��
		if(smDAO.getSummaryList(callid,userid).size()==0 
			&& stDAO.getServiceTypeList(callid,userid).size()==0){
		
			//�����µ�������Ϣ(���������Ҫ�ͷ������)
			//���������Ҫ			
			sm.setSummary(summary);
			//����������ˮ��
			if(session.getAttribute("callid")!=null){
				if(!(session.getAttribute("callid").toString().equals(""))){
					Long l = new Long(0);
					try{
						l = new Long(session.getAttribute("callid").toString());
					}
					catch(Exception e){
						l = null;
					}
					sm.setCallid(l);
				}
			}
			//����༭��Աid
			if(user!=null){
				sm.setUserid(user.getId());
			}
			sm.setEventtime(curdate);
			smDAO.addSummary(sm);
			
			//����������
			if(servicetype!=null){
				int i = 0;
				while(i < servicetype.length){
					st[i] = new ServiceType();
					st[i].setServicetype(servicetype[i]);
				//����������ˮ��
				if(session.getAttribute("callid")!=null){
					if(!(session.getAttribute("callid").toString().equals(""))){
						Long l = new Long(0);
						try{
							l = new Long(session.getAttribute("callid").toString());
						}
						catch(Exception e){
							l = null;
						}
						st[i].setCallid(l);
					}
				}
				//����༭��Աid
				if(user!=null){
					st[i].setUserid(user.getId());
				}
					st[i].setEventtime(curdate);
					stDAO.addServiceType(st[i]);
					i++;
				}
			}
		    HibernateUtil.commitTransaction();
    		HibernateUtil.closeSession();
			response.sendRedirect("insert_result.jsp");
		}
	}
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>�ޱ����ĵ�</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//-->
</script>

<script language="JavaScript" type="text/JavaScript">
<!--

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}
//-->
</script>
</head>

<body  scroll="no">
<form id="frmtelinfo"  name="frmtelinfo" method="post" action="call_exec_insert.jsp">	
<div id="Layer1" scroll="yes"  style="position:absolute; left:10px; top:93px; width:190px; height:300px;visibility: hidden; "> 
<%
	//���洫���������ݣ�����µ�form
	
	out.println("<input type=\"hidden\" id=\"isforce\" name=\"isforce\" value=\"1\">");
	out.println("<input type=\"hidden\" id=\"summary\" name=\"summary\" value=\"" + summary + "\">");
	if(servicetype!=null){
		int i = 0;
		while(i < servicetype.length){
			out.println("<input type=\"checkbox\" id=\"servicetype\" name=\"servicetype\" value=\"" + servicetype[i] + "\" checked>");			
			i++;
		}
	}
%>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bg" scrolling="no">
  <tr>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td height="5" colspan="3"></td>
        </tr>
        <tr> 
          <td width="7"><img src="../images/k2_01.gif" width="7" height="7"></td>
          <td background="../images/k2_02.gif"></td>
          <td width="7"><img src="../images/k2_03.gif" width="7" height="7"></td>
        </tr>
        <tr> 
          <td background="../images/k2_04.gif">&nbsp;</td>
          <td valign="top" bgcolor="#CDE4F8"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5" colspan="4"></td>
              </tr>
              <tr> 
                <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
                <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
                <td width="572" background="../images/k1_03.gif"><strong>�����Ҫ</strong></td>
                <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
              </tr>
              <tr> 
                <td background="../images/k1_05.gif"> </td>
                <td colspan="2" bgcolor="#FFFFFF"> <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
                    <tr bgcolor="#F7FBFF"> 
                        <td colspan="2"><font color="ff0000">���Ѿ�������˴�������Ϣ����ѡ��˴�������δ���</font></td>
                    </tr>
                    <tr> 
                      <td width="13%">&nbsp;</td>
                      <td width="87%">
                      </td>
                    </tr>
                    <tr> 
                      <td width="13%">&nbsp;</td>
                      <td width="87%"><div style="cursor:hand;" onclick="javascript:document.frmtelinfo.submit();">����ԭ������</div>
                      </td>
                    </tr>
                    <tr bgcolor="#F7FBFF"> 
                        <td colspan="2"> </td>
                    </tr>
                    <tr> 
                      <td>&nbsp;</td>
                        <td><div style="cursor:hand;" onclick="javascript:window.location='../right.jsp';">�����˴�����</div> </td>
                    </tr>
                    <tr> 
                      <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                  </table></td>
                <td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
              </tr>
              <tr> 
                <td><img src="../images/k1_07.gif" width="3" height="5"></td>
                <td colspan="2" background="../images/k1_08.gif"> </td>
                <td><img src="../images/k1_09.gif"></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="5"><img src="../images/k_01.gif" width="5" height="4"></td>
                <td background="../images/k_02.gif"></td>
                <td width="6"><img src="../images/k_03.gif" width="6" height="4"></td>
              </tr>
              <tr> 
                <td background="../images/k_04.gif"></td>
                <td height="20" bgcolor="#F2F8FD"> <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td width="33" bgcolor="#CDE4F8"><img src="../images/work_cl.gif" width="28" height="24"></td>
                      <td width="153" bgcolor="#CDE4F8"><strong>���ù���</strong></td>
                    </tr>
                    <tr> 
                      <td height="5" colspan="2"></td>
                    </tr>
                    <tr> 
                      <td height="5" colspan="2"> <table width="98%" border="0" cellspacing="1" cellpadding="3">
                          <tr> 
                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="#">���˻���ͳ�� 
                              </a> </td>
                          </tr>
                          <tr> 
                            <td height="3" background="images/dot_line.gif"></td>
                          </tr>
                          <authz:authorize ifAllGranted="ROLE_SPECIALAGENT">
	                          <tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="leaveword/index.htm" target="LeftFrm">�������� 
	                              </a></td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
                          </authz:authorize>
                          <tr> 
                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="sms_send.htm" target="LeftFrm">��Ϣ����</a></td>
                          </tr>
                          <tr> 
                            <td height="3" background="images/dot_line.gif"></td>
                          </tr>
                          <tr> 
                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="commonfunction/deskfax_index.jsp" target="LeftFrm">��ʱ����</a></td>
                          </tr>
                          <tr> 
                            <td height="3" background="images/dot_line.gif"></td>
                          </tr>
                          <tr> 
                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="#">������</a></td>
                          </tr>
                          <tr> 
                            <td height="3" background="images/dot_line.gif"></td>
                          </tr>
                          <authz:authorize ifAnyGranted="ROLE_CAPTAIN,ROLE_MANAGER,ROLE_LEADER">
	                          <tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="commonfunction/blacklist_index.htm" target="LeftFrm">������</a></td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
	                          <tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="commonfunction/long_transfer_index.htm" target="LeftFrm">Զ��ת���б� 
	                              </a> </td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
	                          <tr> 
	                            <td><img src="images/arrow_r.gif" width="7" ><a href="commonfunction/sysparameter_index.htm" target="LeftFrm">&nbsp;ϵͳ��������</a> 
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
	                          <tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="commonfunction/moandco_index.jsp" target="LeftFrm">ʱʵ���</a></td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
	                          <tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="commonfunction/vip_index.htm" target="LeftFrm">VIP����
	                              </a> </td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
                          </authz:authorize>
						    					<tr> 
                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;<a href="commonfunction/password_modify.jsp" target="LeftFrm">�����޸� 
                              </a> </td>
                          </tr>
                          <tr> 
                            <td height="3" background="images/dot_line.gif"></td>
                          </tr>
                          <authz:authorize ifNotGranted="ROLE_SPECIALAGENT">
							    					<tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
                          </authz:authorize>
                          <authz:authorize ifNotGranted="ROLE_CAPTAIN,ROLE_MANAGER,ROLE_LEADER">
							    					<tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
							    					<tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
							    					<tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
							    					<tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
							    					<tr> 
	                            <td><img src="images/arrow_r.gif" width="7" >&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="3" background="images/dot_line.gif"></td>
	                          </tr>
                          </authz:authorize>
					  
                        </table></td>
                    </tr>
                    <tr> 
                      <td height="5" colspan="2"></td>
                    </tr>
                  </table></td>
                <td background="../images/k_05.gif">&nbsp;</td>
              </tr>
              <tr> 
                <td><img src="../images/k_06.gif" width="5" height="4"></td>
                <td background="../images/k_07.gif"></td>
                <td><img src="../images/k_08.gif" width="6" height="4"></td>
              </tr>
            </table>
            <br> </td>
          <td background="../images/k2_05.gif">&nbsp;</td>
        </tr>
        <tr> 
          <td><img src="../images/k2_06.gif" width="7" height="7"></td>
          <td background="../images/k2_07.gif"> </td>
          <td><img src="../images/k2_08.gif" width="7" height="7"></td>
        </tr>
      </table></td>
  </tr>
</table>

</form>

<%
    HibernateUtil.commitTransaction();
    HibernateUtil.closeSession();
%>
</body>
</html>
