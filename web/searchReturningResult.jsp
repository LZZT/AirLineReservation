<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 3/5/17
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="model.Flight" %>
<%@ page import="model.Airport" %>
<%@ page import="service.ValidateTicketService" %>


<html>
<head>

    <title>AirLine Reservation System</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />

    <style>
        nav {
            float: left;
            max-width: 200px;
            margin: 0;
            padding: 1em;
        }

        main {
            margin-left: 50px;
            border-left: 1px solid gray;
            padding: 1em;
            overflow: hidden;
        }

    </style>

    <%
        String sortReturningBy = "None";
        if(null != session.getAttribute("sortReturningBy")) {
            sortReturningBy = (String)session.getAttribute("sortReturningBy");
        }
    %>

    <script>
        function filterSelection() {
            var element = document.getElementById('sortReturningBy');
            if('None' != ('<%= sortReturningBy %>'))
                element.value = ('<%= sortReturningBy %>');
        }
    </script>

</head>

<body>

    <br><br>

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


        <nav>
            <h3>Filter Conditions:</h3>
            <ul>
                <li>

                    <h5>Sort result by:</h5>
                    <form action="sortReturning" method="post">
                        <select id="sortReturningBy" name="sortReturningBy" onchange="this.form.submit()">
                            <option>None</option>
                            <option value="price">Price</option>
                            <option value="departureTime">Departure Time</option>
                            <option value="arrivalTime">Arrival Time</option>
                            <option value="transitionTime">Transition Time</option>
                        </select>
                    </form>

                </li>


                <li>

                    <s:actionerror cssStyle="color:red"/>

                    <form action="filterReturningAirports" method="post">

                        <h5>Departure Airports:</h5>
                        <%
                            List<Airport> returningDepartureAirportsList = (List<Airport>)session.getAttribute("returningDepartureAirportsList");

                            for(int returningDepartureAirportsIndex = 0; returningDepartureAirportsIndex < returningDepartureAirportsList.size(); returningDepartureAirportsIndex++){
                        %>
                        <input type="checkbox" name="returningDepartureAirportNamesList" value="<%=returningDepartureAirportsList.get(returningDepartureAirportsIndex).getName()%>"/><%=returningDepartureAirportsList.get(returningDepartureAirportsIndex).getName()%><br>
                        <%
                            }
                        %>

                        <h5>Arrival Airports:</h5>
                        <%
                            List<Airport> returningArrivalAirportsList = (List<Airport>)session.getAttribute("returningArrivalAirportsList");

                            for(int returningArrivalAirportsIndex = 0; returningArrivalAirportsIndex < returningArrivalAirportsList.size(); returningArrivalAirportsIndex++){
                        %>

                        <input type="checkbox" name="returningArrivalAirportNamesList" value="<%=returningArrivalAirportsList.get(returningArrivalAirportsIndex).getName()%>"/><%=returningArrivalAirportsList.get(returningArrivalAirportsIndex).getName()%><br>

                        <%
                            }
                        %>

                        <br>

                        <input type="submit" value="Filter Airport"/>

                    </form>

                </li>

                <li>

                    <h5>Stops:</h5>

                    <form action="filterReturningStopType" method="post">

                        <input type="checkbox" name="returningStopType" value="noneStop"/>None Stop<br>
                        <input type="checkbox" name="returningStopType" value="oneStop"/>One Stop<br>
                        <input type="submit" value="Filter Stop"/>

                    </form>

                </li>

                <li>

                    <h5>Time:</h5>

                    <form action="filterReturningTime" method="post">

                        <input type="checkbox" name="returningTimeRange" value="morning"/>Morning<br>
                        <input type="checkbox" name="returningTimeRange" value="afternoon"/>Afternoon<br>
                        <input type="checkbox" name="returningTimeRange" value="evening"/>Evening<br>
                        <input type="submit" value="Filter Time"/>

                    </form>

                </li>

            </ul>
        </nav>

        <main>

            <h3>Choose Returning Flight: </h3>


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
                    <th>Remain</th>
                    <th>&nbsp</th>
                </tr>

                <%
                List<ArrayList> validFlights = (List<ArrayList>)session.getAttribute("validReturningFlights");

                for(int i = 0; i < validFlights.size(); i++){

                %>

                <form action="cartAction.action" method="post"/>
                <%
                    List<Flight> currentFlights = validFlights.get(i);

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

                <%

                    }%>

                            <td>
                                <input type="hidden" name="index" value=<%=i%>/>
                                <input type="submit" value="Select" style="font-size: 30px"/>
                            </td>
                        </tr>
                </form>
                <%
                }
                %>

            <br>
            </table>

        </main>
    </div>



    <div id="tooplate_footer_wrapper">
        <div id="tooplate_footer">
            Copyright © 2017 <a href="#">CS 542 Team 2</a>
        </div>
    </div>


</body>
</html>