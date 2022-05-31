<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>我的</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/style.css" type="text/css" />
	<script type="text/javascript">
	//退出确认框
	function confirm_logout() {
	    var msg = "您确定要退出登录吗？";
	    if (confirm(msg)==true){
	    return true;
	    }else{
	    return false;
	    }
	}
	</script>
</head>
<body>
	<div class="container">
		<%-- 网站顶部 --%>
		<%@include file="head.jsp"%>
		<%-- 网站顶部 --%>
	</div>
	<div class="row">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0" style="margin-top:30px">
						<tr>
							<td class="listtitle">${user.username}  我的帐户</td>
						</tr>
						<tr>
							<td class="listtd">
								<a href="${pageContext.request.contextPath }/logoff" class="btn">注销用户</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<a href="${pageContext.request.contextPath}/logout" class="btn" onclick="javascript:return confirm_logout()">用户退出</a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td style="padding:20px"><p>
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
