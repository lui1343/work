<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>登陆/注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css">
      <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>
  </head>
  <body>

      <div class="container">
        <%-- 网站顶部 --%>
        <%@include file="head.jsp"%>
        <%-- 网站顶部 --%>
      </div>

      <!-- account page -->
      <div class="account-page">
        <div class="container">
          <div class="row">
            <div class="col-2">
              <img src="${pageContext.request.contextPath}/client/images/img1.png" width="100%">
            </div>

            <div class="col-2">
              <div class="form-container">
                <div class="form-btn">
                  <span onclick="login()">登陆</span>
                  <span onclick="register()">注册</span>
                  <hr id="Indicator">
                </div>
                <form id="LoginForm" action="${pageContext.request.contextPath}/login" method="post">
                  <input type="text" name="username" placeholder="Username">
                  <input type="password" name="password" placeholder="Password">
                  <button type="submit" class="btn" onclick="return formcheck()">登入</button>
                  <font
                    color="#ff0000">${requestScope["register_message"]}</font>
                </form>

                <form id="RegForm" action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkForm();">
                  <input type="text" id="username" name="username" onkeyup="checkUsername();" placeholder="Username">
                    <span id="usernameMsg"></span><font color="#999999">字母数字下划线1到10位, 不能是数字开头</font>
                  <input type="text" id="email" name="email" onkeyup="checkEmail();" placeholder="Email">
                    <span id="emailMsg"></span><font color="#999999">请输入有效的邮箱地址</font>
                  <input type="password" id="password" name="password" onkeyup="checkPassword();" placeholder="Password">
                    <span id="passwordMsg"></span><font color="#999999">密码请设置6-16位字符</font>
                  <input type="password" id="repassword" name="repassword" onkeyup="checkConfirm();" placeholder="confirmpassword">
                    <span id="confirmMsg"></span>&nbsp;
                  <button type="submit" name="submit" class="btn">注册</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>


      <!-- js -->
      <script>

        var LoginForm = document.getElementById("LoginForm");
        var RegForm = document.getElementById("RegForm");
        var Indicator = document.getElementById("Indicator");

        function register(){
          RegForm.style.transform = "translateX(0px)";
          LoginForm.style.transform = "translateX(0px)";
          Indicator.style.transform = "translateX(100px)";
        }

        function login(){
          RegForm.style.transform = "translateX(300px)";
          LoginForm.style.transform = "translateX(300px)";
          Indicator.style.transform = "translateX(0px)";
        }
      </script>

  </body>
</html>
