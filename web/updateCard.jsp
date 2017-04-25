<%@ page import="service.PaymentService" %>
<%@ page import="model.Payment" %><%--
  Created by IntelliJ IDEA.
  User: yenchanghsieh
  Date: 3/9/17
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>AirLine Reservation System</title>

    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>


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
                %>
                <input type="button" value="Login" onclick="location.href='login.jsp';">
                <input type="button" value="Register" onclick="location.href='register.jsp';">

                <% } else {
                %>
                <form action="logout.action" method="post">
                    <h4>Hi! ${sessionScope.username}</h4>
                    <input type="submit" value="Logout"/>
                    <input type="button" value="My Profile" onclick="location.href='mytrip.jsp' ;">
                </form>
                <% }%>
            </li>

        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">
        <div>
            <h1>Update Card Action</h1>
        </div>
    </div>

    <div id="tooplate_middle">
        <%
            String cardnumber= (String) session.getAttribute("updateNumber");
            PaymentService paymentService = new PaymentService();
            Payment payment = paymentService.getPayment(cardnumber);
            session.setAttribute("myoldpayment",payment);

        %>
        <h2>Old Creditcard Information</h2>
        <table width="80%" align="center" border="1">

            <tr>
                <th>Card #</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Expire Date</th>
                <th>Billing Address</th>

            </tr>

            <s:iterator value='#session.myoldpayment' id="card">

                <tr>
                    <td>
                        <s:property value='%{#card.cardNumber}'/>
                    </td>
                    <td>
                        <s:property value='%{#card.cardFirstname}'/>
                    </td>
                    <td>
                        <s:property value='%{#card.cardLastname}'/>
                    </td>
                    <td>
                        <s:property value='%{#card.expDate}'/>
                    </td>
                    <td>
                        <s:property value='%{#card.billingAddress}'/>
                    </td>
                </tr>
            </s:iterator>

        </table>

        <s:actionerror cssStyle="color:red"/>

        <form action="updateNewCard.action" method="post">

            Card Number: <s:property value='%{#card.cardNumber}'/><br>

            Last Name: <input type="text" name="cardLastname"><br>

            First Name: <input type="text" name="cardFirstname"><br>

            Expire Date: <input type="text" size="4" maxlength="4" name="expDate"><br>

            CVV: <input type="text" size="3" maxlength="3" name="cvv"><br>

            Billing Address: <input type="text" name="billingAddress"><br>

            <input type="submit" value="Submit">

        </form>


</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>
</body>
</html>
