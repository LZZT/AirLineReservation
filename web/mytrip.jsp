<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="model.Traveler" %>
<%@ page import="java.util.List" %>
<%@ page import="service.TravelerService" %>

<%@ page import="java.util.List" %>
<%@ page import="service.TransactionService" %>
<%@ page import="model.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>AirLine Reservation System</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>

    <link href="tooplate_style.css" rel="stylesheet" type="text/css"/>


    <style type="text/css">
        tooplate_middle2 {
            clear: both;
            width: 800px;
            height: 600px;
            padding: 100px 94px;
            overflow: hidden;
        }

    </style>



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
    </div>
    <!-- end of tooplate_menu -->

    <div id="tooplate_header">

        <div><h1>Traveler information</h1></div>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script src="jquery-1.7.1.min.js"></script>

        <script type="text/javascript">


        </script>


    </div>
    <!-- end of header -->

    <div id="tooplate_middle2">
        <h1> My Tickets</h1>


        <s:actionerror cssStyle="color:red"/>


            <%
    TransactionService transactionService = new TransactionService();
    String username= (String) session.getAttribute("username");
    Map<Transactions,List<Ticket>> ticketsListSet = transactionService.getTransactionAndTicket(username);
    Iterator<List<Ticket>> it = ticketsListSet.values().iterator();
    while(it.hasNext()){
        List<Ticket> ticketList=it.next();
        if (ticketList.size()==0){
            it.remove();
        }
    }
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
                <th></th>

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
                        <td>
                            <s:a href="cancelTicket.action?ticketID=%{#ticket.ticketID}">Cancel</s:a><br>
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

    </div>
</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>


