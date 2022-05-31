<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
      <div class="small-container">
        <h2 class="title">特别推荐</h2>
        <div class="row">

          <c:forEach items="${fps}" var="p"  varStatus="status">
          <c:if test="${status.count <= 4}">
           <div class="col-4">
             <a href="${pageContext.request.contextPath}/productdetail?id=${p.id}"><img src="${pageContext.request.contextPath}/client/images/product-1.png"></a>
             <h4>${p.name }</h4>
             <p>¥${p.price }</p>
           </div>
          </c:if>
          </c:forEach>

          <%-- <div class="col-4">
            <img src="${pageContext.request.contextPath}/client/images/product-1.png">
            <h4>APEX 哔哩哔哩十一周年 2233私服出游Ver. 手办 豪华版</h4>
            <p>¥1549.00</p>
          </div>
          <div class="col-4">
            <img src="${pageContext.request.contextPath}/client/images/product-2.png">
            <h4>世嘉 刀剑神域 Alicization 诗乃 礼服Ver. 景品手办 + 哔哩哔哩 校园系列 小电视 动物橡皮 礼包</h4>
            <p>¥71.00</p>
          </div>
          <div class="col-4">
            <img src="${pageContext.request.contextPath}/client/images/product-3.png">
            <h4>BANPRESTO ESPRESTO系列 哔哩哔哩 22 33 小电视 景品手办 附独家特典</h4>
            <p>¥299.00</p>
          </div>
          <div class="col-4">
            <img src="${pageContext.request.contextPath}/client/images/product-4.png">
            <h4>BANPRESTO（眼镜厂）租借女友 七海麻美 景品手办 独家先行 + 哔哩哔哩 校园系列 小电视 动物橡皮 礼包</h4>
            <p>¥71.00</p>
          </div> --%>
        </div>
      </div>
