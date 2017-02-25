<%@ include file="/include.jsp" %>
<%@ page contentType = "text/html;charset=gb2312" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>

<html>
	<head>
		<title>Welcome</title>
	</head>
	
<authz:authorize ifAllGranted="ROLE_EDITOR">
	<script>
				window.location = "admin/index.htm";
	</script>
</authz:authorize>

<authz:authorize ifAnyGranted="ROLE_SUPER_OFFICE,ROLE_SUPER_TEAM">
	<script>

				window.location = "frame_super.htm";

	</script>
</authz:authorize>


<authz:authorize ifAnyGranted="ROLE_ANONYMOUS,ROLE_1,ROLE_2,ROLE_3,ROLE_4,ROLE_5,ROLE_6,ROLE_7,ROLE_8,ROLE_MANAGER,ROLE_SYSTEM,ROLE_9">
	<script>
		window.location = "mainframe.jsp";
	</script>
</authz:authorize>
	
	<body>
		<table>
			<tr><td>
			</td></tr>
			<tr><td>
				<!--<authz:authorize ifAllGranted="ROLE_ANONYMOUS,ROLE_1,ROLE_2,ROLE_3,ROLE_4,ROLE_5,ROLE_6,ROLE_7,ROLE_8,ROLE_MANAGER,ROLE_SYSTEM,ROLE_9">ROLE_ANONYMOUS</authz:authorize>-->
				<script>
					window.location = "mainframe.jsp";
				</script>
			</td></tr>
		</table>
		<!--
		<authz:authentication operation="principal"/>
			-->
	</body>
</html>