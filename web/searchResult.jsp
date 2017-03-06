<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 3/5/17
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <br><br>
        <h1>Search result:</h1>

        <s:actionerror cssStyle="color:red"/>
        <s:form action="filter" method="post">

            <s:label value="Departure Airports List:"/>

            <s:iterator value="#session.departureAirportsList" id="airport">
                <s:checkbox name="departureAirportNamesList" label="%{#airport.name}" fieldValue="%{#airport.name}"/>
            </s:iterator>

            <s:label value="Arrival Airports List:"/>

            <s:iterator value="#session.arrivalAirportsList" id="airport">
                <s:checkbox name="arrivalAirportNamesList" label="%{#airport.name}" fieldValue="%{#airport.name}"/>
            </s:iterator>

            <s:submit name="submit" value="Filter"/>

        </s:form>


</body>
</html>
