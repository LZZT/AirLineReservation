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

<h2>My Tickets</h2>

<s:actionerror cssStyle="color:red"/>


<%
    TransactionService transactionService = new TransactionService();
    String username= (String) session.getAttribute("username");

    if(username == null){
        response.sendRedirect("login.jsp");
    }

    Map<Transactions,List<Ticket>> ticketsListSet = transactionService.getTransactionAndTicket2(username);
    session.setAttribute("ticketsListSet",ticketsListSet);
%>

<s:iterator value='#session.ticketsListSet' id="ticketsListSet">
    <s:iterator value='%{#ticketsListSet.key}' id="transactions">

        &nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Transaction ID:<s:property value='%{#transactions.transactionID}'/>
        &emsp; Transaction Date:<s:property value='%{#transactions.transactionDate}'/>
        &emsp; Total Price:<s:property value='%{#transactions.price}'/>

    </s:iterator>
<table width="80%" align="center" border="1">

        <tr>
            <th>TicketID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Flight Number</th>
            <th>Flight Date</th>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Price</th>

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
                    <td>
                        <s:property value='%{#ticket.price}'/>
                    </td>
                </tr>
            </s:iterator>
        </s:iterator>

    </table>
    <br><br>

</s:iterator>
<br>
<input type="button" onclick="location.href='index.jsp';" value="Return"/>

</body>
</html>
