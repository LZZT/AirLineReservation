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


<html>
<head>
    <title>Title</title>
</head>

<body>



        <br><br>
        <h1>Search Returning Flights:</h1>

        <s:actionerror cssStyle="color:red"/>

        <form action="filterReturningAirports" method="post">

            Departure Airports List: <br>
            <%
                List<Airport> returningDepartureAirportsList = (List<Airport>)session.getAttribute("returningDepartureAirportsList");

                for(int returningDepartureAirportsIndex = 0; returningDepartureAirportsIndex < returningDepartureAirportsList.size(); returningDepartureAirportsIndex++){
            %>
            <input type="checkbox" name="returningDepartureAirportNamesList" value="<%=returningDepartureAirportsList.get(returningDepartureAirportsIndex).getName()%>"/><%=returningDepartureAirportsList.get(returningDepartureAirportsIndex).getName()%><br>
            <%
                }
            %>

            Arrival Airports List: <br>
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



        Stops: <br>

        <form action="filterReturningStopType" method="post">

            <input type="checkbox" name="returningStopType" value="noneStop"/>None Stop<br>
            <input type="checkbox" name="returningStopType" value="oneStop"/>One Stop<br>
            <input type="submit" value="Filter Stop"/>

        </form>


        Time: <br>

        <form action="filterReturningTime" method="post">

            <input type="checkbox" name="returningTimeRange" value="morning"/>Morning<br>
            <input type="checkbox" name="returningTimeRange" value="afternoon"/>Afternoon<br>
            <input type="checkbox" name="returningTimeRange" value="evening"/>Evening<br>
            <input type="submit" value="Filter Time"/>

        </form>



        Sort result by:
        <form action="sortReturning" method="post">
            <select name="sortReturningBy" onchange="this.form.submit()">
                <option></option>
                <option value="price">Price</option>
                <option value="departureTime">Departure Time</option>
                <option value="arrivalTime">Arrival Time</option>
                <option value="transitionTime">Transition Time</option>
            </select>
        </form>







        <h3>Choose Returning Flight: </h3>


        <table width="80%" align="center">
            <tr>
                <th>Flight Number</th>
                <th>AirLine</th>
                <th>Aircraft</th>
                <th>Departure Time</th>
                <th>Arrival Time</th>
                <th>Departure Airport</th>
                <th>ArrivalAirport</th>
                <th>Price</th>
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

            <%

                }%>

                        <td>
                            <input type="hidden" name="index" value=<%=i%>/>
                            <input type="submit" value="Select"/>
                        </td>
                    </tr>
            </form>
            <%
            }
            %>

        <br>
        </table>


</body>
</html>
