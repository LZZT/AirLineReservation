<%@ page import="model.Payment" %>
<%@ page import="model.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>

<br><br>

<s:actionerror cssStyle="color:red"/>
<h1>Flight Infomation</h1>
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



<h1>Traveler Infomation</h1>
<table width="80%" align="center" border="1">

    <tr>
        <th>Last Name</th>
        <th>First Name</th>
        <th>Gender</th>
        <th>Date of birth</th>
        <th>Email</th>
        <th>Phone</th>
        <th></th>
    </tr>


    <s:iterator value="#session.travelerList" id="traveler">

        <tr>

            <td>
                <s:property value='%{#traveler.lastname}'/>
            </td>
            <td>
                <s:property value='%{#traveler.firstname}'/>
            </td>
            <td>
                <s:property value='%{#traveler.gender}'/>
            </td>
            <td>
                <s:property value='%{#traveler.dob}'/>
            </td>
            <td>
                <s:property value='%{#traveler.email}'/>
            </td>
            <td>
                <s:property value='%{#traveler.phone}'/>
            </td>

        </tr>

    </s:iterator>

</table>

<br><br>

<h1>Payment Infomation</h1>
<table width="80%" align="center" border="1">

    <tr>
        <th>Card Number</th>
        <th>Last Name</th>
        <th>First Name</th>
        <th>Exp Date</th>
        <th>CVV</th>
        <th>Billing Address</th>
        <th></th>
    </tr>

    <%
        Payment payment=(Payment) session.getAttribute("payment");
    %>
    <s:iterator >

        <tr>

            <td>
            <%      out.println(payment.getCardNumber());%>
            </td>
            <td>
                <%      out.println(payment.getCardLastname());%>
            </td>
            <td>
                <%      out.println(payment.getCardFirstname());%>
            </td>
            <td>
                <%      out.println(payment.getExpDate());%>
            </td>
            <td>
                <%      out.println(payment.getCvv());%>
            </td>
            <td>
                <%      out.println(payment.getBillingAddress());%>
            </td>

        </tr>

    </s:iterator>

</table>

<form action="transAction.action" method="post">
<br><br>
<input type="submit" value="Submit">

</form>

</body>
</html>
