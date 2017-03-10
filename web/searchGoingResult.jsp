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


<html>
<head>
    <title>Title</title>
</head>

<body>

        <br><br>
        <h1>Search Going Flights:</h1>

        <s:actionerror cssStyle="color:red"/>
        <s:form action="filter" method="post">

            <s:label value="Departure Airports List:"/>

            <s:iterator value="#session.goingDepartureAirportsList" id="airport">
                <s:checkbox name="goingDepartureAirportNamesList" label="%{#airport.name}" fieldValue="%{#airport.name}"/>
            </s:iterator>

            <s:label value="Arrival Airports List:"/>

            <s:iterator value="#session.goingArrivalAirportsList" id="airport">
                <s:checkbox name="goingArrivalAirportNamesList" label="%{#airport.name}" fieldValue="%{#airport.name}"/>
            </s:iterator>

            <s:submit name="submit" value="Filter"/>

        </s:form>


        <h3>Choose Departure Flight: </h3>


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
            List<ArrayList> validFlights = (List<ArrayList>)session.getAttribute("validGoingFlights");

            for(int i = 0; i < validFlights.size(); i++){

            %>

            <form action="searchReturningFlight" method="post"/>
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
                            <%= flight.getDepartureTime()%>
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
                            <s:a href="deleteFlight.action?flightNumber=">Delete</s:a><br>
                            <s:a href='updateFlight.jsp'>Update</s:a>
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
















        <%--<s:iterator value="#session.validFlights" id="flightList">--%>

                <%--<s:iterator value='%{#flightList}' id="flight">--%>

                            <%--<s:property value='%{#flight.flightNumber}'/>--%>

                            <%--<s:property value='%{#flight.airline.code}'/>--%>

                            <%--<s:property value='%{#flight.aircraftModel.model}'/>--%>

                            <%--<s:property value='%{#flight.departureTime}'/>--%>

                            <%--<s:property value='%{#flight.arrivalTime}'/>--%>

                            <%--<s:property value='%{#flight.departureAirport.name}'/>--%>

                            <%--<s:property value='%{#flight.arrivalAirport.name}'/>--%>

                            <%--<s:property value='%{#flight.Price}'/><br>--%>

                <%--</s:iterator>--%>

            <%------------------------------------------------------------------------------------------------------------------------------------%>
            <%--<br>--%>
        <%--</s:iterator>--%>


        <%--<table width="80%" align="center" border="1">--%>

        <%--<tr>--%>
        <%--<th>Flight Number</th>--%>
        <%--<th>AirLine</th>--%>
        <%--<th>Aircraft</th>--%>
        <%--<th>Departure Time</th>--%>
        <%--<th>Arrival Time</th>--%>
        <%--<th>Departure Airport</th>--%>
        <%--<th>ArrivalAirport</th>--%>
        <%--<th>Price</th>--%>
        <%--<th></th>--%>
        <%--</tr>--%>


        <%--<s:iterator value="#session.validFlights" id="flight">--%>

        <%--<tr>--%>

        <%--<td>--%>
        <%--<s:property value='%{#flight.flightNumber}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:property value='%{#flight.airline.code}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:property value='%{#flight.aircraftModel.model}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:property value='%{#flight.departureTime}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:property value='%{#flight.arrivalTime}'/>--%>
        <%--</td>--%>

        <%--<td>--%>
        <%--<s:property value='%{#flight.departureAirport.name}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:property value='%{#flight.arrivalAirport.name}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:property value='%{#flight.Price}'/>--%>
        <%--</td>--%>
        <%--<td>--%>
        <%--<s:a href="addToCart.action?id=%{#flight.flightNumber}">Add to Cart</s:a>--%>
        <%--</td>--%>

        <%--</tr>--%>

        <%--</s:iterator>--%>

        <%--</table>--%>








</body>
</html>
