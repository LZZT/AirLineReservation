<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="service.TransactionService" %>
<%@ page import="model.*" %>
<%@ page import="service.TravelerService" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title></title>
</head>
<body>

<br><br>

<s:actionerror cssStyle="color:red"/>

<%--<form action="mytrip.action" method="post">--%>

<%
    TransactionService transactionService = new TransactionService();
    String username= (String) session.getAttribute("username");
    Map<Transactions,List<Ticket>> ticketsListSet = transactionService.getTransactionAndTicket2(username);
//    List<List<Ticket>> ticketsListSet = transactionService.getTransactionAndTicket(username);

    session.setAttribute("ticketsListSet",ticketsListSet);
%>

<s:iterator value='#session.ticketsListSet' id="ticketsListSet">

<table width="80%" align="center" border="1">

        <tr>
            <th>TicketID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Flight Number</th>
            <th>Flight Date</th>
            <th>Departure City</th>
            <th>Arrival City</th>
        </tr>

       <s:iterator value='%{#ticketsListSet.value}' id="ticketList">
            <s:iterator value='%{#ticketList}' id="ticket">

                <tr>

                    <td>
                        <s:property value='%{#ticket.ticketID}'/>
                    </td>
                    <td>
                        <s:property value='%{#ticket.firstName}'/>
                    </td>
                    <td>
                        <s:property value='%{#ticket.lastName}'/>
                    </td>
                    <td>
                        <s:property value='%{#ticket.flightNumber}'/>
                    </td>
                    <td>
                        <s:property value='%{#ticket.flightDate}'/>
                    </td>
                    <td>
                        <s:property value='%{#ticket.departureCity}'/>
                    </td>
                    <td>
                        <s:property value='%{#ticket.arrivalCity}'/>
                    </td>
                </tr>
            </s:iterator>
        </s:iterator>

    </table>

</s:iterator>
<br>
<input type="button" onclick="location.href='index.jsp';" value="Return"/>

<%--</form>--%>

</body>
</html>
