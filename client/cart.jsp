<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>购物车</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
      <script>
          //当商品数量发生变化时触发该方法
      	function changeProductNum(count, totalCount, id) {
      		count = parseInt(count);
      		totalCount = parseInt(totalCount);
      		//如果数量为0，判断是否要删除商品
      		if (count == 0) {
      			var flag = window.confirm("确认删除商品吗?");

      			if (!flag) {
      				count = 1;
      			}
      		}
      		location.href = "${pageContext.request.contextPath}/changeCart?id="
      				+ id + "&count=" + count;
      	}
      	//删除购物车中的商品
      	function cart_del() {
      	    var msg = "您确定要删除该商品吗？";
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

      <!-- cart items details -->

      <div class="small-container cart-page">
          <table>
            <tr>
              <th>产品</th>
              <th>数量</th>
              <th>总计</th>
            </tr>
            <!-- 循环输出商品信息 -->
            <c:set var="total" value="0" />
            <c:forEach items="${cart}" var="entry" varStatus="vs">
            <tr>
              <td>
                <div class="cart-info">
                  <img src="images/buy-1.png">
                  <div>
                    <p>${entry.key.name }</p>
                    <small>价格:${entry.key.price }</small>
                    <br>
                    <a href="${pageContext.request.contextPath}/changeCart?id=${entry.key.id}&count=0"
                    style="color:#FF0000; font-weight:bold" onclick="javascript:return cart_del()">>移除</a>
                  </div>
                </div>
              </td>
              <td>
              <input type="button" value='-' style="width:20px"
                     onclick="changeProductNum('${entry.value-1}','${entry.key.pnum}','${entry.key.id}')">
               <!-- 商品数量显示 -->
              <input name="text" type="text" value="${entry.value}" style="width:40px;text-align:center" />
              <!-- 增加商品数量 -->
              <input type="button" value='+' style="width:20px"
                     onclick="changeProductNum('${entry.value+1}','${entry.key.pnum}','${entry.key.id}')">
              </td>
              <td>¥${entry.key.price*entry.value}</td>
            </tr>
            <c:set value="${total+entry.key.price*entry.value}" var="total" />
            </c:forEach>
          </table>

          <div class="total-price">

            <table>
              <tr>
                <td>总计</td>
                <td>¥${total}</td>
              </tr>
            </table>
          </div>

          <div style="text-align:right; margin-top:10px">
              <!--继续购物 -->
            <a href="${pageContext.request.contextPath}/products" class="btn">继续购物</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
                                     <!--结账 -->
            <a href="${pageContext.request.contextPath}/createOrder" class="btn">结账</a>
          </div>

      </div>




  </body>
</html>
