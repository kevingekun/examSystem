 <%@ page contentType = "text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List,com.wondersgroup.popedom.bo.ExamStaff"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">
<title>准考证打印-考生信息导入 </title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<script>
	function submit(){
		document.fileupload.submit();
	}
	function templateDownload() {
		window.location.href = "templateDownload.action?flag=printcard";
	}
</script>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">导入考生信息</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                            <tr>
                              <td align="left" valign="middle" class="header7"></td>
                              <td class="header8">导入条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
    
              <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                 <!-- <td width="18%" height="28" align="center">鉴定批次名称：</td>
                  <td width="25%">
                  	<input type="text" name="mc" value="" id="mc" style="float: left;">
                    <font color="#FF0000">*</font>
                  </td>
				  <td width="10%"></td> -->
				  <td width="40%"></td>
				  <td width="25%">
	            	<form name="fileupload" action="batchAddAction_displayExcel_printcard.action" method="post" enctype="multipart/form-data">
	            		<input type="file" id="file" name="file_excel" style="width: 200px"/>
	            	</form>
            	</td>
            	<td width="10%"></td>
				</td>
                  <td align="left"><input type="button" class="submit_2" onclick="submit()" value="导入" />
                    </td>
                </tr>
              </table>  
                      </td>  
                </tr>
              </table>
            
         <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                       <td class="header8">
                       	<!-- <a href="javascript:void(0)" class="infocount" onclick="showT()" style="color: green">导入正确考生</a> 
                       	<a href="javascript:void(0)" class="infocount" onclick="showF()" style="color: red">导入错误考生</a> -->
						<div style="float:right">
							<input type="button" onclick="templateDownload()" value="准考证打印考生导入模板下载" style="margin-right:20px;width:150px;background-color: #79C7CD" />
						</div>
						</td>
                      </tr>
                  </table>
                 </td>
                </tr>
              </table>     
           <table id="tb1"  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" >
             <tr class="title_font">
             <td width="3%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号 </span></td>
                <td width="8%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">单位名称</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">考场名称</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">座号</span></td>
             </tr>  
                <s:iterator value="usersList" id="user"  status="state">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#state.index+1"/>
                </td>
                <td align='center' class='num_font'><s:property value="#user.zkh"/>
                </td>
                <td align='center' class='num_font'> <s:property value="#user.name"/>           
	     	    </td>
	             <td align='center' class='num_font'> <s:property value="#user.idcard"/>           
	     	    </td>
	     	    </td>
	             <td align='center' class='num_font'> <s:property value="#user.dw_name"/>           
	     	    </td>
	     	    </td>
	             <td align='center' class='num_font'> <s:property value="#user.kc_name"/>           
	     	    </td>
	     	    </td>
	             <td align='center' class='num_font'> <s:property value="#user.seat_no"/>           
	     	    </td>
	     	     
             </tr>
           </s:iterator>
             
			</table>
			<%-- <table id="tb2" style="display: none;"  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" >
             <tr class="title_font">
              <td width="3%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号 </span></td>
                <td width="8%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
             </tr>  
                <s:iterator value="list_error" id="user"  status="state">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                 <td align='center' class='num_font'><s:property value="#state.index+1"/>
                </td>
                <td align='center' class='num_font'><s:property value="#user.password"/>
                </td>
                <td align='center' class='num_font'> <s:property value="#user.realname"/>           
	     	    </td>
	             <td align='center' class='num_font'> <s:property value="#user.username"/>           
	     	    </td>
             </tr>
           </s:iterator>
             
			</table> --%>
					</td>
				</tr>
				
			</table>
			  
  	</div></td>
  	</tr>
  	</table>
</body>
</html>

