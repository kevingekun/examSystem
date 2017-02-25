<%@page contentType = "text/html;charset=gbk" %>
<%@page import="java.util.List,java.util.Iterator" %>
<jsp:useBean id="callservice" class="com.wondersgroup.falcon.beans.call.CallService"/>
<html>
<head>
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
</head>

<%
	List urllist = callservice.getRecordUrl("0066019cd7259faa");	
	System.out.println("urllist==============>>>>>>>>>>>>>>>"+urllist); 
%>

<script>
	function mediaplayer(wav_name){
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}
</script>

<body>
<TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0><!--DWLayoutTable-->
	<TBODY>
		<TR>
			<TD vAlign=top width=16 rowSpan=2><IMG height=14 src="../images/right_01.gif"></TD>
			<TD vAlign=top colSpan=2 height=10>
		    	<TABLE cellSpacing=0 cellPadding=0 width="100%" background=../images/right_02.gif border=0><!--DWLayoutTable-->
		      		<TBODY>
		      			<TR>
		        			<TD width=928 height=10></TD>
		        		</TR>
		        	</TBODY>
		        </TABLE>
			</TD>
		  	<TD vAlign=top width=24 rowSpan=2><IMG height=14 src="../images/right_03.gif" width=24></TD></TR>
		<TR>
			<TD width=795 height=4></TD>
			<TD width=795></TD>
		</TR>
        <TR>
			<TD vAlign=top background=../images/right_06.gif>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" background=../images/right_06.gif border=0><!--DWLayoutTable-->
					<TBODY>
						<TR>
							<TD >&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
			</TD>
			<TD colspan="2" vAlign=top></TD>
			<TD background=../images/right_05.gif vAlign=top>
				<TABLE cellSpacing=0 cellPadding=0 background=../images/right_05.gif border=0><!--DWLayoutTable-->
				<TBODY>
					<TR>
						<TD align=left width=24>&nbsp;</TD>
					</TR>
				</TBODY>
				</TABLE>
			</TD>
		</TR>
        <TR>
			<TD vAlign=top height=17><IMG height=17 src="../images/right_08.gif" width=16></TD>
			<TD vAlign=top colSpan=2>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" background=../images/right_09.gif border=0><!--DWLayoutTable-->
					<TBODY>
						<TR>
							<TD width=928 height=17>&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
			</TD>
			<TD vAlign=top><IMG height=17 src="../images/right_10.gif" width=24></TD>
		</TR>
	</TBODY>
</TABLE>
                      
<br>
                      
<TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0><!--DWLayoutTable-->
	<TBODY>
		<TR>
			<TD vAlign=top width=16 rowSpan=2><IMG height=14 src="../images/right_01.gif"></TD>
			<TD vAlign=top colSpan=2 height=10>
			    <TABLE cellSpacing=0 cellPadding=0 width="100%" background=../images/right_02.gif border=0><!--DWLayoutTable-->
			  		<TBODY>
			      		<TR>
			        		<TD width=928 height=10></TD>
			        	</TR>
			        </TBODY>
			    </TABLE>
		   	</TD>
		  	<TD vAlign=top width=24 rowSpan=2><IMG height=14 src="../images/right_03.gif" width=24></TD></TR>
		<TR>
		  	<TD width=795 height=4></TD>
		  	<TD width=795></TD></TR>
		<TR>
		  	<TD vAlign=top background=../images/right_06.gif>
		    	<TABLE cellSpacing=0 cellPadding=0 width="100%" 
		    		background=../images/right_06.gif border=0><!--DWLayoutTable-->
					<TBODY>
		            	<TR>
		                	<TD >&nbsp;</TD>
		                </TR>
		            </TBODY>
		         </TABLE>
		    </TD>
			<TD colspan="2" vAlign=top>
				<table border=0>
					<%
						for (Iterator lt = urllist.iterator(); lt.hasNext();) {
							String[] array = (String[])lt.next();
							out.println("<tr>");
							out.println("<td width='10%' align='center'>名称</td>");
							out.print("<td width='10%'>");
							out.print(array[1]);
							out.println("</td>");							
							out.println("<td width='20%' align='center'>时间</td>");
							out.print("<td width='20%'>");
							out.print(array[2]);
							out.println("</td>");
							out.println("<td width='10%' align='center'>工号</td>");
							out.print("<td width='10%'>");
							out.print(array[3]);
							out.println("</td>");
							out.print("<td >");
							out.println("<a href='#' onclick = 'return mediaplayer(\"" + array[0] + "\")'>播放</a>");
							out.println("</td>");
							out.println("<tr>");
						}
			   		%>

			</table>
		<object align="middle"
			classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" id="MediaPlayer"
			width="400" height="69">
			<param name="ShowStatusBar" value="-1">
			<param name="AutoStart" value="0">
			<param name="Filename" value="">
			<embed type="application/x-oleobject"
				codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
				flename="mp" src="\\172.16.31.16\voice\65470" width=200 height=50></embed>
		</object>
	</TD>
	<TD background=../images/right_05.gif vAlign=top>
		<TABLE cellSpacing=0 cellPadding=0 background=../images/right_05.gif
			border=0>
			<!--DWLayoutTable-->
			<TBODY>
				<TR>
					<TD align=left width=24>
						&nbsp;
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</TD>
	</TR>
	<TR>
	<TD vAlign=top height=17>
		<IMG height=17 src="../images/right_08.gif" width=16>
	</TD>
	<TD vAlign=top colSpan=2>
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background=../images/right_09.gif border=0>
			<!--DWLayoutTable-->
			<TBODY>
				<TR>
					<TD width=928 height=17>
						&nbsp;
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</TD>
	<TD vAlign=top>
		<IMG height=17 src="../images/right_10.gif" width=24>
	</TD>
</TR>
</TBODY>
</TABLE>

</body>
</html>
