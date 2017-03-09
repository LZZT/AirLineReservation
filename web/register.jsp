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
    <title>Register</title>
</head>
<body>

    <s:actionerror cssStyle="color:red"/>

    <form action="register.action" method="post">

        username: <input type="text" name="username"><br>

        password: <input type="password" name="password"><br>

        re-password: <input type="password" name="repassword"><br>

        email: <input type="text" name="email"><br>

        <input type="submit" value="submit">

    </form>

</body>
</html>
