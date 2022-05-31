<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>bilibili商店</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
  </head>
  <body>

    <div class="header">
      <div class="container">

        <%-- 网站顶部 --%>
        <%@include file="head.jsp"%>
        <%-- 网站顶部 --%>


        <div class="row">
          <div class="col-2">
            <h1>欢迎光临!</h1>
            <p>你所热爱的，就是你的生活<br></p>
            <a href="${pageContext.request.contextPath}/products" class="btn">开始选购 &#8594;</a>
          </div>
          <div class="col-2">
            <img src="${pageContext.request.contextPath}/client/images/img1.png">
          </div>
        </div>
      </div>
    </div>
    <!-- featured categories -->
    <!-- <div class="categories">
      <div class="small-container">
        <div class="row">
          <div class="col-2">
            <img src="images/category-1.png">
          </div>
          <div class="col-2">
            <img src="images/category-2.png">
          </div>
        </div>
      </div>
    </div> -->

    <!-- 特别产品 -->
    <%@include file="foot.jsp"%>
    <!-- 特别产品 -->

  </body>
</html>
