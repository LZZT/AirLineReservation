<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 3/7/17
  Time: 3:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <h1>This is the cart</h1> <br><br>




    <s:iterator value='#session.leavingFlightObjectSet' id="flightList">

        <s:iterator value='%{#flightList}' id="flight">

            <s:property value='%{#flight.flightNumber}'/>

            <s:property value='%{#flight.airline.code}'/>

            <s:property value='%{#flight.aircraftModel.model}'/>

            <s:property value='%{#flight.departureTime}'/>

            <s:property value='%{#flight.arrivalTime}'/>

            <s:property value='%{#flight.departureAirport.name}'/>

            <s:property value='%{#flight.arrivalAirport.name}'/>

            <s:property value='%{#flight.Price}'/><br>

        </s:iterator>

    </s:iterator>

    <br><br>

    -------------------------------------------------------------------------------------------------------------
    <br><br>


    <s:iterator value='#session.returningFlightObjectSet' id="flightList">

        <s:iterator value='%{#flightList}' id="flight">

            <s:property value='%{#flight.flightNumber}'/>

            <s:property value='%{#flight.airline.code}'/>

            <s:property value='%{#flight.aircraftModel.model}'/>

            <s:property value='%{#flight.departureTime}'/>

            <s:property value='%{#flight.arrivalTime}'/>

            <s:property value='%{#flight.departureAirport.name}'/>

            <s:property value='%{#flight.arrivalAirport.name}'/>

            <s:property value='%{#flight.Price}'/><br>

        </s:iterator>

    </s:iterator>





    <br><br> <br><br> <br><br> <br><br> <br><br> <br><br>






    <h2>In session, you will get two Sets. These two sets are not stored in database</h2>
    <h3>1. leavingFlightObjectSet containing Flights Objects</h3>
    <h3>2. returningFlightObjectSet containing Flights Objects</h3>
    <br>

    <h3>Tasks:</h3>
    <h4>1.  show both seperately </h4>
    <h4>2.  remove function, but update number not allowed  </h4>
    (if a customer want to buy tickets for 2 persons, buy for 1 person first.
    It need more information of which combination belongs to which traveler in session.)
    <h4>3.  click "Enter Traveler Information" button will go to traveler information page</h4>

</body>
</html>
