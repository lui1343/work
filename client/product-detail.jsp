<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>商品细节</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
  </head>
  <body>

      <div class="container">
        <%-- 网站顶部 --%>
        <%@include file="head.jsp"%>
        <%-- 网站顶部 --%>
      </div>

      <!-- single product details-->
      <div class="small-container single-product">
        <div class="row">
          <div class="col-2">
            <img src="images/gallery-1.png" width="100%" id ="Productimg">



          </div>
          <div class="col-2">
            <p>Home / T-Shirt</p>
            <h1>${p.name}</h1>
            <h4>¥${p.price}</h4>
            <%-- <select>
              <option>Select size</option>
              <option>XXL</option>
              <option>XL</option>
              <option>L</option>
              <option>M</option>
              <option>S</option>
            </select> --%>

            <%
          if(null == user){
          %>
           <a href="${pageContext.request.contextPath}/client/account.jsp" class="btn">请先登陆</a>
          <%
          }else{
          %>
           <a href="${pageContext.request.contextPath}/addCart?id=${p.id}" class="btn">加入购物车</a>
          <%
          }
          %>
            <h3>产品信息</h3>
            <br>
            <p>${p.description}</p>
          </div>
        </div>
      </div>

      <!-- titile -->
      <div class="small-container">
        <div class="row row-2">
          <h2>相关产品</h2>
          <p>查看更多</p>
        </div>
      </div>


    <!-- 特别产品 -->
    <%@include file="foot.jsp"%>
    <!-- 特别产品 -->



    </div>
  </body>
</html>
