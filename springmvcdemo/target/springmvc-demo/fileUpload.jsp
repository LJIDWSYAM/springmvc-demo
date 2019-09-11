<%--
  Created by IntelliJ IDEA.
  User: liujun
  Date: 2019/9/11
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>文件上传</h1>
    <form action="index/fileUpload" method="post" enctype="multipart/form-data">
        上传文件:<input type="file" name="fileTarget"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
