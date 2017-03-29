<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 3/7/17
  Time: 3:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Title</title>
</head>

<h1>This is the cart</h1>

<h2>Leaving Flight:</h2>
<br>
<table width="80%" align="center" border="1">

    <tr>
        <th>Flight Number</th>
        <th>Airline</th>
        <th>Aircraft Model</th>
        <th>Departure Date</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Departure Airport</th>
        <th>Arrival Airport</th>
        <th>Price</th>
        <th></th>
    </tr>

    <s:iterator value='#session.leavingFlightObjectSet' id="flightList">
        <s:iterator value='%{#flightList}' id="flight">

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
                    <% out.println((String) session.getAttribute("departingDate"));%>
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
                    <s:property value='%{#flight.Price}'/><br>
                </td>

            </tr>
        </s:iterator>
    </s:iterator>

</table>
<br>
<h2>Returning Flight:</h2>
<br>

<table width="80%" align="center" border="1">

    <tr>
        <th>Flight Number</th>
        <th>Airline</th>
        <th>Aircraft Model</th>
        <th>Departure Date</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Departure Airport</th>
        <th>Arrival Airport</th>
        <th>Price</th>
        <th></th>
    </tr>



    <s:iterator value='#session.returningFlightObjectSet' id="flightList">

        <s:iterator value='%{#flightList}' id="flight">

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
                    <% out.println((String) session.getAttribute("returningDate"));%>
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
                    <s:property value='%{#flight.Price}'/><br>
                </td>

            </tr>
        </s:iterator>
    </s:iterator>

</table>

<body>
<form action="cartNumber.action" method="post">
    <br><br>
    <h2>Please select total number of travelers:</h2>
<input type="number" name="ticketsNumber" min="1"/>

<input type="reset" value="reset"/>
<input type="button" value="Re-select" onclick="location.href='searchReturningResult.jsp'"/><br>
<input type="submit" value="Next"/>
<%--<input type="button" onclick="location.href='traveler.jsp';" value="Continue"/>--%>

</form>


<br><br> <br><br> <br><br> <br><br> <br><br> <br><br>


</body>
</html>
