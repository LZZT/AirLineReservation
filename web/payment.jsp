<%@ page import="service.TransactionService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="model.Transactions" %>
<%@ page import="model.Payment" %>
<%@ page import="service.PaymentService" %>
<%@ page import="java.util.ArrayList" %>
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
                    <input type="button" value="My Profile" onclick="location.href='mytrip.jsp';">
                </form>
                <% }%>

            </li>
        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="mid_title">

        <div><h3 style="text-align:left">Payment Information</h3></div>

    </div> <!-- end of header -->

    <div id="tooplate_middle2">
        <h3>Choose payment from Order history</h3>

        <%
            PaymentService paymentService = new PaymentService();
            String username = (String) session.getAttribute("username");
            List<Payment> paymentsList = paymentService.getCreditCardByUsername(username);
            session.setAttribute("paymentsHistoryList",paymentsList);


            if(paymentsList.size()>0){ %>
                <table width="80%" align="center" border="1">

            <tr>
                <th>cardNumber</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Billing Address</th>
                <th>&nbsp</th>
            </tr>

             <%

            for (int i = 0; i<paymentsList.size(); i++) {




//

        %>




            <form action="payment.action" method="post">
                <tr>
                    <td>

                        <%= paymentsList.get(i).getCardNumber() %>
                    </td>
                    <td>
                        <%= paymentsList.get(i).getCardFirstname()%>
                    </td>
                    <td>
                        <%= paymentsList.get(i).getCardLastname()%>
                    </td>
                    <td>
                        <%= paymentsList.get(i).getBillingAddress()%>
                    </td>

                    <td>
                        <input type="radio" name="index" value=<%=i%>/>

                    </td>
                </tr>

                    <% }}%>
            </table>

        <s:actionerror cssStyle="color:red"/>

        <form action="payment.action" method="post">

            Card Number: <input type="text" name="cardNumber" ><br>

            Last Name: <input type="text" name="cardLastname"><br>

            First Name: <input type="text" name="cardFirstname"><br>

            Expire Date: <input type="text" size="4" maxlength="4" name="expDate"><br>

            CVV: <input type="text" size="3" maxlength="3" name="cvv"><br>

            Billing Address: <input type="text" name="billingAddress"><br>

            <input type="submit" value="Continue">

        </form>
    </div>
</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>



