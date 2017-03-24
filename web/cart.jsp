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


<body>
<form action="cartNumber.action" method="post">
    <br><br> <br><br>
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
