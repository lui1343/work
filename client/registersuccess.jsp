<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>注册成功</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css %>">
      <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>
  </head>
  <body>

      <div class="container">
        <%-- 网站顶部 --%>
        <%@include file="head.jsp"%>
        <%-- 网站顶部 --%>
      </div>

			<div class="col-2">
				<h1>注册成功!</h1><br>
				<a href="${pageContext.request.contextPath }/client/account.jsp">
					<span>点击此处去登陆</span>
					</a>
			</div>


</body>
</html>
