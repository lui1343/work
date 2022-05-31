<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="domain.User"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/style.css">
      <div class="navbar">
        <div class="logo">
          <a href="${pageContext.request.contextPath}/client/index.jsp"><img src="${pageContext.request.contextPath}/client/images/logo.png" width="150px"></a>
        </div>
        <nav>
          <ul id="MenuItems">
            <li><a href="${pageContext.request.contextPath}/client/index.jsp">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/products">全部产品</a></li>
            <%
          User user = (User) request.getSession().getAttribute("user");
          if(null == user){
          %>
           <li><a href="${pageContext.request.contextPath}/client/account.jsp">账户</a>
          <%
          }else{
          %>
           <li><a href="${pageContext.request.contextPath}/client/myAccount.jsp">账户</a>
          <%
          }
          %>
          </ul>
        </nav>
        <%
        if(null == user){
        %>
         <a href="${pageContext.request.contextPath}/client/account.jsp"><img src="${pageContext.request.contextPath}/client/images/cart.png" width="30px" height="30px"></a>
        <%
        }else{
        %>
         <a href="${pageContext.request.contextPath}/client/cart.jsp"><img src="${pageContext.request.contextPath}/client/images/cart.png" width="30px" height="30px"></a>
        <%
        }
        %>
      </div>
