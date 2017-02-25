package com.wondersgroup.kaoshi.util;

public class PageMaker {
	public PageMaker(){}   
    public String makeList(PageTool pageinfo,String url){   
       int  linkNumber=10;   
       int  halfLinkNumber=5;   
       String pageStyle="<style> .ptbl {border:1px solid #CEDBEF;font-size:12px;padding:0;font-family:Arial;width:auto} .ptbl " +
       		"a {text-decoration:none;color:#555555} .ptbl " +
       		"td {padding-top:0px;padding-bottom:0px;padding-left:8px;padding-right:8px}   " +
       		" .strong {background:#CEDBEF;font-weight:800;color:#FF7D00}    " +
       		".strong a{color:#FF7D00} " +
       		".page_input {background:#ffffff;border:1px solid #CEDBEF;border-top:none;border-bottom:none;color:#FF7D00;width:30px;margin:0px }" +
       		" </style>" +
       		"<script type='text/javascript'>" +
       		"function tiaozhuanfenye(pagecurr)" +
       		"{window.document.getElementById('ppp').value=pagecurr;" +
       		"window.document.forms[0].submit();}</script>";
       String pageTable="<table border='0'cellpadding='0' cellspacing='0' bgcolor='#CEDBEF' class='ptbl'><tr align='center' bgcolor='#FFFBFF'>";   
        StringBuffer pagelist=new StringBuffer("");   
        //得到有几页，用总共的除以每页显示的数量再加一，就可以得到  
        int curpage=pageinfo.getCur();   
        int pagenumber=pageinfo.getTotal()/pageinfo.getSize()+1;   
        // 
        int prepage=curpage-1;   
        prepage=prepage<=0?1:prepage;   
        int nextpage=curpage+1;   
        nextpage=nextpage>=pagenumber?pagenumber:nextpage;   
        
        
        pagelist.append(pageStyle);   
        pagelist.append(pageTable);  
        
        
       if (pagenumber<=linkNumber){   
          for(int i=1;i<=pagenumber;i++){   
            if(i==curpage)   
               //pagelist.append("<td class='strong'>"+i+"</td>");   
            	pagelist.append("<td class='strong'><a href='#' onclick='tiaozhuanfenye("+i+");'>"+i+"</a></td>");
            else  
               //pagelist.append("<td><a href='"+url+i+"'>"+i+"</a></td>");  
            	pagelist.append("<td ><a href='#' onclick='tiaozhuanfenye("+i+");'>"+i+"</a></td>");
          }   
       }else{   
          int begin=curpage-halfLinkNumber;   
          begin=(begin<=0)?1:begin;   
          begin=((halfLinkNumber+curpage)>pagenumber) ? (pagenumber-linkNumber+1):begin;   
          for(int i=begin;i<=linkNumber+begin-1;i++){   
            if(i==curpage){   
               pagelist.append("<td class='strong'>"+i+"</td>");   
               pagelist.append("\n");   
            }   
            else{   
               //pagelist.append("<td><a href='<s:url action='"+url+"'><s:param name='ppp' value='"+i+"'/></s:url>'>"+i+"</a></td>");  
            	pagelist.append("<td><a href='#' onclick='tiaozhuanfenye("+i+");'>"+i+"</a></td>");
               pagelist.append("\n");   
            }   
          }   
       }   
        //pagelist.append("<td><a href='"+url+nextpage+"'>&gt;</a></td>");   
        //pagelist.append("<td><a href='"+url+pagenumber+"'>&gt;|</a></td>");   
        pagelist.append("<td></td><td>共:<font color='red'>"+pagenumber+"</font>页</td>" +
        		"<td>共:<font color='red'>"+pageinfo.getTotal()+"</font>条数据</td></tr></table>");   
        return pagelist.toString();   
    }   

}
