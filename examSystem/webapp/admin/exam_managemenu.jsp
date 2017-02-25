<%@page contentType="text/html;charset=gbk" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="../inc/all.css" rel="stylesheet" type="text/css">
</HEAD>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function changeSubModel(id){
		switch(id){
			case 1:
				parent.parent.parent.frames.rightframe.location="exam_subjectadd.jsp";	
				break;
			case 2:
				parent.parent.parent.frames.rightframe.location="exam_create.jsp";	
				break;	
			case 3:
				parent.parent.parent.frames.rightframe.location="exam_anspaperquery.jsp";	
				break;								
		}
	}
//-->
</SCRIPT>
<BODY bgcolor="#DEEFFF" scroll="no">
<TABLE style="border-left: 1px solid rgb(204, 204, 204); border-right: 1px solid rgb(204, 204, 204);" border="0" cellpadding="0" cellspacing="0" width="100%">
<TR height="30">
	<TD class="t_b" onmouseover="this.className='t_b_on';" onclick="javascript:changeSubModel(1);" onmouseout="this.className='t_b'"><img src="../images/arrow_blue_right.gif" border="0" >&nbsp;试题录入</TD>
</TR>
<TR align=center valign=center>
	<TD >
		<TABLE cellpadding="0" cellspacing="0" width="100%" id="LM_1235_1237_on">
			<TR>
				<TD >
					<div class="t_b_l">
						<A HREF="exam_subjectadd.jsp" class=b target="rightframe">试题录入</A>
					</div>
				</TD>
			</TR>
		</TABLE>
	</TD>
</TR>
<TR height="30" >
	<TD class="t_b" onmouseover="this.className='t_b_on';" onclick="javascript:changeSubModel(2);" onmouseout="this.className='t_b'"><img src="images/arrow_blue_right.gif" border="0" >&nbsp;试卷管理</TD>
</TR>
<TR align=center valign=center >
	<TD >
		<TABLE cellpadding="0" cellspacing="0" width="100%" id="LM_1235_1242_on">
			<TR>
				<TD >
					<div style="margin-left:30">
						<a  href="exam_create.jsp" class=b target="rightframe"　>自动组卷</a><br>
						<a  href="exam_handcreate.jsp" class=b target="rightframe"　>人工组卷</a><br>
						<a  href="exam_list.jsp" class=b target="rightframe"　>试卷列表</a>
					</div>	
				</TD>
			</TR>
		</TABLE>
	</TD>
</TR>
<TR height="30" >
	<TD class="t_b" onmouseover="this.className='t_b_on';" onclick="javascript:changeSubModel(3);" onmouseout="this.className='t_b'"><img src="images/arrow_blue_right.gif" border="0" >&nbsp;答卷管理</TD>
</TR>
<TR align=center valign=center >
	<TD >
		<TABLE cellpadding="0" cellspacing="0" width="100%" id="LM_1235_1247_on">
			<TR>
				<TD >
					<div class="t_b_l">
						<a  href="exam_anspaperquery.jsp" class=b target="rightframe"　>答卷查询</a><br>
						<a  href="exam_anspapercheck.jsp" class=b target="rightframe"　>审阅答卷</a><br>						
					</div>
				</TD>
			</TR>
		</TABLE>
	</TD>
</TR>
<TR height="500">
	<TD >
		<TABLE >
		<TR>
			<TD></TD>
		</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>
</BODY>
</HTML>

