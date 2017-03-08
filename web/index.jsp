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
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="/resources/demos/style.css">
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script>
          $(function() {
              $("#singleTrip, #roundTrip").change(function(){
                  $("#datepicker1, #datepicker2").val("").attr("readonly",true);
                  if($("#singleTrip").is(":checked")){
                      $("#datepicker1").datepicker();
                  } else if($("#roundTrip").is(":checked")){
                      $("#datepicker2").removeAttr("readonly");
                      $("#datepicker2").focus();
                      $("#datepicker1").datepicker();
                      $("#datepicker2").datepicker();
                  }
              });
          });
      </script>

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
            <input type="radio" name="tripType" id="singleTrip" value="singleTrip" required/>Single Trip
            <input type="radio" name="tripType" id="roundTrip" value="roundTrip" required/>Round Trip
            <br>
            Departure City or Airport: <input type="text" name="departureCityOrAirport"/><br>
            Arrival City or Airport: <input type="text" name="arrivalCityOrAirport"/><br>
            Departing Date: <input type="text" id="datepicker1" name="departingDate" readonly/><br>
            Returning Date: <input type="text" id="datepicker2" name="returningDate" readonly/><br>

            <input type="submit" value="Search"/>

        </form>

  </body>
</html>
