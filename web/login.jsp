<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 3/4/17
  Time: 12:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<s:actionerror cssStyle="color:red"/>

<form action="login.action" method="post">

    username: <input type="text" name="username"><br>

    password: <input type="password" name="password"><a href="testInsert.jsp">forget password?</a><br>

    <input type="button" onclick="location.href='register.jsp';" value="Register"/>

    <input type="submit" value="Login">

</form>



</body>
</html>
