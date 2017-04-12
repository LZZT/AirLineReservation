<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 2/28/17
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Index</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function() {
            $("#singleTrip, #roundTrip").change(function(){
                $("#datepicker1, #datepicker2").val("").attr("readonly",true);
                if($("#singleTrip").is(":checked")){
                    $("#datepicker2").attr("readonly",true);
                    $("#datepicker1").datepicker({
                        minDate: 0
                    });
                    $("#datepicker2").datepicker( "option", "disabled", true );
                } else if($("#roundTrip").is(":checked")){
                    $("#datepicker2").attr("readonly",false);
                    $("#datepicker1").datepicker({
                        minDate: 0,
                        onSelect: function(date){

                            var selectedDate = new Date(date);
                            var msecsInADay = 86400000;
                            var endDate = new Date(selectedDate.getTime() + msecsInADay);

                            $("#datepicker2").datepicker( "option", "minDate", endDate );

                        }
                    });
                    $("#datepicker2").datepicker();
                }
            });
        });
    </script>

</head>

<body>

<br><br>

<div id="tooplate_wrapper">

    <div id="tooplate_menu">
        <ul>
            <li><a href="index.jsp" class="current">Home</a></li>
            <li><a href="reloadDB.action">Manager</a></li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="contact.html">Contact</a></li>
            <li>

                <%
                    if (null == session.getAttribute("username")) {
                %>
                <input type="button" value="Login"   style="font-size: 15px" onclick="location.href='login.jsp';">
                <input type="button" value="Register"  style="font-size: 15px" onclick="location.href='register.jsp';">

                <% } else {
                %>
                <form action="logout.action" method="post">
                    <h4>Hi! ${sessionScope.username}</h4>
                    <input type="submit"  value="Logout"/>
                    <input type="button" style="font-size: 15px" value="My trip" onclick="location.href='mytrip.jsp' ;">
                </form>
                <% }%>


            </li>

        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">

        <div><h1>Welcome to AirLine Reservation System!</h1></div>

    </div> <!-- end of header -->

    <div id="tooplate_middle">

        <s:actionerror cssStyle="color:red"/>
        <form action="searchGoingFlight.action" method="post">
            <input type="radio" name="tripType" id="singleTrip" value="singleTrip" checked="checked"/>Single Trip
            <input type="radio" name="tripType" id="roundTrip" value="roundTrip"/>Round Trip


            <br>
            Departure City or Airport: <input type="text" name="departureCityOrAirport"/><br>
            Arrival City or Airport: <input type="text" name="arrivalCityOrAirport"/><br>
            Departing Date: <input type="text" id="datepicker1" name="departingDate" readonly/><br>
            Returning Date: <input type="text" id="datepicker2" name="returningDate" readonly/><br>

            <input type="submit" value="Search"/>

        </form>
    </div>
</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>



<%--<input type="button" value="Manager Mode" onclick="location.href='manager.jsp';">--%>
<%--<br><br><br><br>--%>



</body>
</html>
