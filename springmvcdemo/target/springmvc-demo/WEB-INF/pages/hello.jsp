<%--
  Created by IntelliJ IDEA.
  User: liujun
  Date: 2019/9/7
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>恭喜你来到成功页面了</h1>
<%--<img src="<%=request.getContextPath()+"/images/taile.jpg"%>">--%>
<img src="images/taile.jpg">

<form action="addEmp" method="post" >
    lastName:<input type="text" name="lastName"><p>${lastNameError}</p></br>
    email:<input name="email"><p>${emailError}</p></br>
    gender:<input name="gender"><p>${genderError}</p></br>
    birth:<input name="birth"><p>${birthError}</p></br>
    <input type="submit" value="提交">
</form>
</body>
</html>
