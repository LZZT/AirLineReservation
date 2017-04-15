<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 2/28/17
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>AirLine Reservation System</title>
</head>
<body>

    Primary key: <s:property value = "returnedPrimaryKey"/><br>
    username: <s:property value = "returnedUsername"/><br>
    password: <s:property value = "returnedPassword"/>

    <form action="deleteTestUser.action">
        <s:hidden name="primaryKey" value="%{#request.returnedPrimaryKey}"></s:hidden>
        <input type="submit" value="delete testUser"/>
    </form>

</body>
</html>
