<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.wondersgroup.kaoshi.util.PageTool;"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
	int  linkNumber=10;   
    int  halfLinkNumber=5;   
%>
<style>
 .ptbl {
 	border:1px solid #CEDBEF;f
 	ont-size:12px;
 	padding:0;
 	font-family:Arial;width:auto
 	} 
 .ptbl a {text-decoration:none;color:#555555}
 .ptbl td {padding-top:0px;padding-bottom:0px;padding-left:8px;padding-right:8px}
       strong {background:#CEDBEF;font-weight:800;color:#FF7D00} 
       strong a{color:#FF7D00} " +
       page_input {background:#ffffff;border:1px solid #CEDBEF;border-top:none;border-bottom:none;color:#FF7D00;width:30px;margin:0px }
</style>
<script type='text/javascript'>
	function tiaozhuanfenye(pagecurr){
		window.document.getElementById('ppp').value=pagecurr;
       	window.document.forms[0].submit();
    }
</script>
<s:hidden name='ppp' value='%{ppp}'/>
<table border='0'cellpadding='0' cellspacing='0' bgcolor='#CEDBEF' class='ptbl'><tr align='center' bgcolor='#FFFBFF'>
<%    
        //得到有几页，用总共的除以每页显示的数量再加一，就可以得到  
        PageTool pageTool= (PageTool)request.getAttribute("pageTool");
        int curpage=pageTool.getCur();   
        int pagenumber=pageTool.getTotal()/pageTool.getSize()+1;   
        // 
        int prepage=curpage-1;   
        prepage=prepage<=0?1:prepage;   
        int nextpage=curpage+1;   
        nextpage=nextpage>=pagenumber?pagenumber:nextpage;   
            
       if (pagenumber<=linkNumber){   
          for(int i=1;i<=pagenumber;i++){   
            if(i==curpage){  
               //pagelist.append("<td class='strong'>"+i+"</td>");   
            %>
            	<td class='strong'><a href='#' onclick="tiaozhuanfenye('<%=i %>');"><%=i %></a></td>
            <%
            }else{  
               //pagelist.append("<td><a href='"+url+i+"'>"+i+"</a></td>");  
            	%><td ><a href='#' onclick="tiaozhuanfenye('<%=i %>');"><%=i %></a></td><%
            }
          }   
       }else{   
          int begin=curpage-halfLinkNumber;   
          begin=(begin<=0)?1:begin;   
          begin=((halfLinkNumber+curpage)>pagenumber) ? (pagenumber-linkNumber+1):begin;   
          for(int i=begin;i<=linkNumber+begin-1;i++){   
            if(i==curpage){   
               %><td class='strong'><%=i %></td><%
            }   
            else{   
               //pagelist.append("<td><a href='<s:url action='"+url+"'><s:param name='ppp' value='"+i+"'/></s:url>'>"+i+"</a></td>");  
            	%><td><a href='#' onclick='tiaozhuanfenye("+i+");'><%=i %></a></td><%  
            }   
          }   
       }   
        //pagelist.append("<td><a href='"+url+nextpage+"'>&gt;</a></td>");   
        //pagelist.append("<td><a href='"+url+pagenumber+"'>&gt;|</a></td>");   
        %>
        <td></td><td>共:<font color='red'><%=pagenumber %></font>页</td>" +
        		"<td>共:<font color='red'><%pageTool.getTotal(); %>+"</font>条数据</td></tr></table>  
         
