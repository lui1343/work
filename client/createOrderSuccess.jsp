<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>电子书城</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" />
	</script>
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center">
				    <table width="60%" border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<td style="padding-top:30px">
								<font style="font-weight:bold; color:#FF0000">订单生成成功！</font><br /> <br />
								<a href="${pageContext.request.contextPath }/index.jsp" class="btn">回首页</a>
							</td>
						</tr>
					</table>
				  <h1>&nbsp;</h1>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
