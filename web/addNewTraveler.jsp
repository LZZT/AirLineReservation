<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="model.Traveler" %>
<%@ page import="java.util.List" %>
<%@ page import="service.TravelerService" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %>

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
                    <input type="button" value="My Profile" onclick="location.href='mytrip.jsp';">
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
        <form action="addNewTraveler.action" method="post">

            PhoneNumber: <input type="text" name="phone"><br>

            First Name: <input type="text" name="firstname"><br>

            Last Name: <input type="text" name="lastname"><br>

            <input type="radio" name="gender" value="M"/>Male
            <input type="radio" name="gender" value="F"/>Female
            <br>

            Date of birth: <input type="date" name="dob"><br>

            Email: <input type="email" name="email"><br>


            <input id="submit" type="submit" value="Submit" style="font-size: 100px"/>
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

