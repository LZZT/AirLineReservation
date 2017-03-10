<%--
  Created by IntelliJ IDEA.
  User: yenchanghsieh
  Date: 3/9/17
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="updateFlight.action" method="post">

    Flight Number: <input type="text" name="flightNumber"><br>

    Departure Time: <input type="time" name="departTime"><br>

    Arrival Time: <input type="time" name="arriTime"><br>

    Days Operated:  <input type="checkbox" name="daysOperated" value="Mon">Mon
    <input type="checkbox" name="daysOperated" value="Tue">Tue
    <input type="checkbox" name="daysOperated" value="Wed">Wed
    <input type="checkbox" name="daysOperated" value="Thu">Thu
    <input type="checkbox" name="daysOperated" value="Fri">Fri
    <input type="checkbox" name="daysOperated" value="Sat">Sat
    <input type="checkbox" name="daysOperated" value="Sun">Sun<br>

    Departure Airport: <input type="text" name="departAirport"><br>

    Arrival Airport: <input type="text" name="arriAirport"><br>

    Airline: <input type="text" name="airline"><br>

    Aircraft Model: <input type="text" name="aircraftModel"><br>

    Price: <input type="text" name="price"><br>

    <input type="submit" value="submit">
</form>
</body>
</html>
