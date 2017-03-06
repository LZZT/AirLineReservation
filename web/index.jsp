<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 2/28/17
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

  <head>
    <title>Index</title>
  </head>

  <body>

      <br><br>

        <h2>Welcome to AirLine Reservation System!</h2>

      <%
          if (null == session.getAttribute("username")) {
      %>
        <input type="button" value="Login" onclick="location.href='login.jsp';">
        <input type="button" value="Register" onclick="location.href='register.jsp';">

      <% } else {
      %>
          <form action="logout.action" method="post">
              <h2>Hi! ${sessionScope.username}</h2>
              <input type="submit" value="Logout"/>
          </form>
      <% }%>

      <br><br><br><br>
      <h1>This is the place where user search flights</h1>

      <s:actionerror cssStyle="color:red"/>
        <form action="searchFlight.action" method="post">
            Departure City or Airport: <input type="text" name="departureCityOrAirport"/><br>
            Arrival City or Airport: <input type="text" name="arrivalCityOrAirport"/><br>
            <input type="submit" value="Search"/>

        </form>

  </body>
</html>
