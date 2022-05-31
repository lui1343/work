<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>所有商品 - bilibili商店</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
      <script type="text/javascript">
      /**
       * my_click和my_blur均是用于前台页面搜索框的函数
       */
      //鼠标点击搜索框时执行
      function my_click(obj, myid){
      	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
      	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
      	  document.getElementById(myid).value = '';
      	  obj.style.color='#000';
      	}
      }
      //鼠标不聚焦在搜索框时执行
      function my_blur(obj, myid){
      	//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
      	if (document.getElementById(myid).value == ''){
      	 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
      	 obj.style.color='#999';
       }
      }

      /**
       * 点击搜索按钮执行的函数
       */
      function search(){
      	document.getElementById("searchform").submit();
      }
      </script>
  </head>
  <body>

      <div class="container">
        <%-- 网站顶部 --%>
        <%@include file="head.jsp"%>
        <%-- 网站顶部 --%>
      </div>

    <div class="small-container">

      <div class="row row-2">
        <h2>所有产品</h2>
        <form action="${pageContext.request.contextPath }/searchServlet">
          <div class="row row-2">
          <input type="text" name="textfield" class="inputtable" id="textfield" value="搜索"
          onmouseover="this.focus();"
          onclick="my_click(this, 'textfield');"
          onBlur="my_blur(this, 'textfield');"/>
          <a href="#">
            <img src="${pageContext.request.contextPath}/client/images/searchbutton.png" border="0" style="margin-bottom:-4px" onclick="search()"/>
          </a>
          </div>
        </form>
        <%-- <select>
          <option>Default</option>
          <option>Default</option>
          <option>Default</option>
          <option>Default</option>
        </select> --%>
      </div>

      <c:forEach items="${ps}" var="p"  varStatus="status">
     <c:if test="${status.count eq 1 || (status.count-1) % 4 eq 0}">
      <div class="row">
     </c:if>
       <div class="col-4">
         <a href="${pageContext.request.contextPath}/productdetail?id=${p.id}"><img src="${pageContext.request.contextPath}/client/images/product-1.png"></a>
         <h4>${p.name }</h4>
         <p>¥${p.price }</p>
       </div>
      <c:if test="${status.count % 4 eq 0 || status.count eq 4}">
      </div>
      </c:if>
      </c:forEach>

      <%-- <div class="page-byn">
        <span>1</span>
        <span>2</span>
        <span>3</span>
        <span>4</span>
        <span>&#8594;</span>
      </div> --%>
    </div>
  </body>
</html>
