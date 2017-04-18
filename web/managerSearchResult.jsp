<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="model.Flight" %>
<%@ page import="model.Airport" %>


<html>
<head>

    <title>AirLine Reservation System</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="tooplate_style.css" rel="stylesheet" type="text/css"/>

    <style>
        nav {
            float: left;
            max-width: 200px;
            margin: 0;
            padding: 1em;
        }

        main {
            margin-left: 300px;
            border-left: 1px solid gray;
            padding: 1em;
            overflow: hidden;
        }

    </style>


</head>

<body>
<br><br>

<%
    if (null == session.getAttribute("username") || !session.getAttribute("username").equals("root")) {
        response.sendRedirect("login.jsp");
    }
%>

<div id="tooplate_wrapper">
    <div id="tooplate_menu">
        <ul>
            <li><a href="index.jsp" class="current" style="font-size: large">Home</a></li>
            <li><a href="reloadDB.action" style="font-size: large">Manager</a></li>
            <li><a href="about.html" style="font-size: large">About Us</a></li>
            <li><a href="contact.html" style="font-size: large">Contact</a></li>
            <li>

                <%
                    if (null == session.getAttribute("username")) {
                %>
                <input type="button" value="Login" onclick="location.href='login.jsp';">
                <input type="button" value="Register" onclick="location.href='register.jsp';">

                <% } else {
                %>
                <form action="logout.action" method="post">
                    <h4>Hi! ${sessionScope.username}</h4>
                    <input type="submit" value="Logout"/>
                    <input type="button" value="My Profile" onclick="location.href='mytrip.jsp';">
                </form>
                <% }%>


            </li>
        </ul>
    </div> <!-- end of tooplate_menu -->


    <div id="tooplate_header">

        <h1>Search result:</h1>

        <table style="width: 100%;" align="center" border="1">

            <tr>
                <th>Flight Number</th>
                <th>AirLine</th>
                <th>Aircraft</th>
                <th>Departure Time</th>
                <th>Arrival Time</th>
                <th>Departure Airport</th>
                <th>Arrival Airport</th>
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
                        <s:property value='%{#flight.departureAirport.code}'/>
                    </td>
                    <td>
                        <s:property value='%{#flight.arrivalAirport.code}'/>
                    </td>
                    <td>
                        <s:property value='%{#flight.Price}'/>
                    </td>
                    <td>
                        <s:a href="deleteFlight.action?flightNumber=%{#flight.flightNumber}">Delete</s:a><br>
                        <s:a href="updateFlight.jsp?flightNumber=%{#flight.flightNumber}&departureTime=%{#flight.departureTime}&arrivalTime=%{#flight.arrivalTime}&daysOperated=%{#flight.daysOperated}&departureAirport=%{#flight.departureAirport.code}&arrivalAirport=%{#flight.arrivalAirport.code}&airline=%{#flight.airline.name}&aircraftModel=%{#flight.aircraftModel.model}&price=%{#flight.price}">Update</s:a>
                    </td>

                </tr>

            </s:iterator>

        </table>


    </div>
</div>


<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>





