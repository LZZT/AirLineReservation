<%--
  Created by IntelliJ IDEA.
  User: yenchanghsieh
  Date: 3/9/17
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<br><br>
<h1>Search result:</h1>

<table width="80%" align="center" border="1">

    <tr>
        <th>Flight Number</th>
        <th>AirLine</th>
        <th>Aircraft</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Departure Airport</th>
        <th>ArrivalAirport</th>
        <th>Price</th>
        <th></th>
    </tr>


    <s:iterator value="#session.validFlights" id="flight">

        <tr>

            <td>
                <s:property value='%{#flight.flightNumber}'/>
            </td>
            <td>
                <s:property value='%{#flight.airline.code}'/>
            </td>
            <td>
                <s:property value='%{#flight.aircraftModel.model}'/>
            </td>
            <td>
                <s:property value='%{#flight.departureTime}'/>
            </td>
            <td>
                <s:property value='%{#flight.arrivalTime}'/>
            </td>
            <td>
                <s:property value='%{#flight.departureAirport.name}'/>
            </td>
            <td>
                <s:property value='%{#flight.arrivalAirport.name}'/>
            </td>
            <td>
                <s:property value='%{#flight.Price}'/>
            </td>
            <td>
                <s:a href="deleteFlight.action?flightNumber=%{#flight.flightNumber}">Delete</s:a><br>
                <s:a href='updateFlight.jsp'>Update</s:a>
            </td>

        </tr>

    </s:iterator>

</table>

</body>
</html>
