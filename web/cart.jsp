<%@ page import="model.Flight" %>
<%@ page import="java.util.List" %>
<%@ page import="service.ValidateTicketService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>AirLine Reservation System</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<br><br>

<div id="tooplate_wrapper">

    <div id="tooplate_menu">
        <ul>
            <li><a href="index.jsp" class="current">Home</a></li>
            <li><a href="reloadDB.action">Manager</a></li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="contact.html">Contact</a></li>
            <li>

                <%
                    if (null == session.getAttribute("username")) {
                        response.sendRedirect("login.jsp");
                %>
                <%--<input type="button" value="Login" onclick="location.href='login.jsp';">--%>
                <%--<input type="button" value="Register" onclick="location.href='register.jsp';">--%>

                <% } else {
                %>
                <form action="logout.action" method="post">
                    <h4>Hi! ${sessionScope.username}</h4>
                    <input type="submit" value="Logout"/>
                    <input type="button" value="My trip" onclick="location.href='mytrip.jsp';">
                </form>
                <% }%>


            </li>
        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">

        <div><h1 style="text-align:left">Travel Plan</h1></div>

        <h2 style="text-align:left">Leaving Flight:</h2>
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
                <th>Remain</th>

            </tr>

            <%
                List<Flight> currentFlights = (List<Flight>) session.getAttribute("leavingFlightObjectSet");

                for(Flight flight : currentFlights){
                    ValidateTicketService validateTicketService = new ValidateTicketService();
                    int totalTicketNumber = validateTicketService.getTotalTicketNumber(flight.getFlightNumber(),String.valueOf(flight.getDepartureTime()));
                    int remain =validateTicketService.getCapacity(flight.getAircraftModel().getModel())-totalTicketNumber;

            %>



            <tr>
                <td>
                    <%= flight.getFlightNumber() %>
                </td>
                <td>
                    <%= flight.getAirline().getCode()%>
                </td>
                <td>
                    <%= flight.getAircraftModel().getModel() %>
                </td>
                <td>
                    <% out.println((String) session.getAttribute("departingDate"));%>
                </td>
                <td>
                    <%= flight.getDepartureTime()%>
                </td>
                <td>
                    <%= flight.getArrivalTime()%>
                </td>

                <td>
                    <%= flight.getDepartureAirport().getName() %>
                </td>
                <td>
                    <%= flight.getArrivalAirport().getName()%>
                </td>
                <td>
                    <%= flight.getPrice()%>
                </td>
                <td>
                    <%= remain%>
                </td>
            </tr>
                    <%

                    }%>
        </table>

        <% if(null != session.getAttribute("validReturningFlights")) {%>
        <br>
        <h2 style="text-align:left">Returning Flight:</h2>
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
                <th>Remain</th>


            </tr>

            <%
                List<Flight> returningFlightObjectSet = (List<Flight>) session.getAttribute("returningFlightObjectSet");

                for(Flight flight : returningFlightObjectSet){
                    ValidateTicketService validateTicketService = new ValidateTicketService();
                    int totalTicketNumber = validateTicketService.getTotalTicketNumber(flight.getFlightNumber(),String.valueOf(flight.getDepartureTime()));
                    int remain =validateTicketService.getCapacity(flight.getAircraftModel().getModel())-totalTicketNumber;
            %>

            <tr>
                <td>
                    <%= flight.getFlightNumber() %>
                </td>
                <td>
                    <%= flight.getAirline().getCode()%>
                </td>
                <td>
                    <%= flight.getAircraftModel().getModel() %>
                </td>
                <td>
                    <% out.println((String) session.getAttribute("returningDate"));%>
                </td>
                <td>
                    <%= flight.getDepartureTime()%>
                </td>
                <td>
                    <%= flight.getArrivalTime()%>
                </td>

                <td>
                    <%= flight.getDepartureAirport().getName() %>
                </td>
                <td>
                    <%= flight.getArrivalAirport().getName()%>
                </td>
                <td>
                    <%= flight.getPrice()%>
                </td>
                <td>
                    <%= remain%>
                </td>
            </tr>
            <%

                }%>
        </table>
        <% } %>

    </div> <!-- end of header -->

    <div id="tooplate_middle">

        <s:actionerror cssStyle="color:red"/>
        <form action="cartNumber.action" method="post">

            <input type="button" value="Re-select" onclick="location.href='searchReturningResult.jsp'"/><br>
            <input type="submit" value="Next"/>

        </form>
    </div>
</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>

