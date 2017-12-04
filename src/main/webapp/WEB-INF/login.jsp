<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/12/4
  Time: 上午10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/loginsubmit" method="post">
    user:
    <input type="text" name="usercode">
    <br>
    pwd:
    <input type="password" name="password">
    <br>
    <input type="submit" value="[login]">
</form>
</body>
</html>
