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
        <s:form action="filter" method="post">

            <s:label value="Departure Airports List:"/>

            <s:iterator value="#session.returningDepartureAirportsList" id="airport">
                <s:checkbox name="returningDepartureAirportNamesList" label="%{#airport.name}" fieldValue="%{#airport.name}"/>
            </s:iterator>

            <s:label value="Arrival Airports List:"/>

            <s:iterator value="#session.returningArrivalAirportsList" id="airport">
                <s:checkbox name="returningArrivalAirportNamesList" label="%{#airport.name}" fieldValue="%{#airport.name}"/>
            </s:iterator>

            <s:submit name="submit" value="Filter"/>

        </s:form>


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

            <form action="cartAction" method="post"/>
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
