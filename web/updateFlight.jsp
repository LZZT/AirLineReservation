<%--
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
    <title>Title</title>

    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

    <script>
        $(function() {
            $('#departTime').timepicker({
                timeFormat: 'HH:mm:ss',
                interval: 15,
                startTime: '00:00:00',
                dynamic: false,
                dropdown: true,
                scrollbar: true
            });

            $('#arriTime').timepicker({
                timeFormat: 'HH:mm:ss',
                interval: 15,
                startTime: '00:00:00',
                dynamic: false,
                dropdown: true,
                scrollbar: true
            });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#submit').click(function() {
                checked = $("input[type=checkbox]:checked").length;

                if(!checked) {
                    alert("You must fill all fields in this form.");
                    return false;
                }

                var selectDepart = document.getElementById("departAirport");
                var departAirport = selectDepart.options[selectDepart.selectedIndex].value;
                var selectArrival = document.getElementById("arriAirport");
                var arrivalAirport = selectArrival.options[selectArrival.selectedIndex].value;
                if(departAirport == arrivalAirport) {
                    alert("Departure airport cannot be same with Arrived airport!");
                    return false;
                }
            });
        });
    </script>

    <script>
        function GetURLParameter(sParam)
        {
            var sPageURL = window.location.search.substring(1);
            var sURLVariables = sPageURL.split('&');
            for (var i = 0; i < sURLVariables.length; i++)
            {
                var sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] == sParam)
                {
                    document.getElementById('test').innerHTML=sParameterName[1];
                    return sParameterName[1];
                }
            }
        }​
    </script>
</head>
<body>
<br><br>

<%
    if (null == session.getAttribute("username") || !session.getAttribute("username").equals("root")) {
        response.sendRedirect("login.jsp");
    }
%>

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
                    <input type="button" value="My trip" onclick="location.href='mytrip.jsp' ;">
                </form>
                <% }%>
            </li>

        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">
        <div><h1>Update Flight Action</h1></div>
    </div>

    <h4>You must fill all fields in this form.</h4>

    <form action="updateFlight.action" method="post">

        Flight Number: <input id="flightNumber" type="text" name="flightNumber" readonly><br>
        <script>
            var sPageURL = window.location.search.substring(1);
            var sParameters = sPageURL.split('&');
            var sParameterFL = sParameters[0].split('=');
            document.getElementById("flightNumber").value = decodeURI(sParameterFL[1]);
        </script>

        Departure Time: <input id="departureTime" type="text" name="departTime" id="departTime"><br>
        <script>
            var sParameterDT = sParameters[1].split('=');
            document.getElementById("departureTime").value = decodeURI(sParameterDT[1]);
        </script>

        Arrival Time: <input id="arriTime" type="text" name="arriTime" id="arriTime"><br>
        <script>
            var sParameterAT = sParameters[2].split('=');
            document.getElementById("arriTime").value = decodeURI(sParameterAT[1]);
        </script>

        Days Operated:
        <input id="Mon" type="checkbox" name="daysOperated" value="Mon">Mon
        <input id="Tue" type="checkbox" name="daysOperated" value="Tue">Tue
        <input id="Wed" type="checkbox" name="daysOperated" value="Wed">Wed
        <input id="Thu" type="checkbox" name="daysOperated" value="Thu">Thu
        <input id="Fri" type="checkbox" name="daysOperated" value="Fri">Fri
        <input id="Sat" type="checkbox" name="daysOperated" value="Sat">Sat
        <input id="Sun" type="checkbox" name="daysOperated" value="Sun">Sun<br>
        <script>
            var sParameterDO = sParameters[3].split('=');
            var sDayOperated = decodeURI(sParameterDO[1]);
            for(var i = 0; i < sDayOperated.length; i++) {
                if(sDayOperated.charAt(i) == '1'){
                    document.getElementById("Mon").checked = true;
                } else if(sDayOperated.charAt(i) == '2') {
                    document.getElementById("Tue").checked = true;
                } else if(sDayOperated.charAt(i) == '3') {
                    document.getElementById("Wed").checked = true;
                } else if(sDayOperated.charAt(i) == '4') {
                    document.getElementById("Thu").checked = true;
                } else if(sDayOperated.charAt(i) == '5') {
                    document.getElementById("Fri").checked = true;
                } else if(sDayOperated.charAt(i) == '6') {
                    document.getElementById("Sat").checked = true;
                } else{
                    document.getElementById("Sun").checked = true;
                }
            }
        </script>

        <s:select id="departureAirport"
                  label="Departure Airports"
                  headerKey="-1" headerValue="Select Departure Airport"
                  list="#session.managerAirports"
                  name="departAirport" /> <br>
        <script>
            var sParameterDA = sParameters[4].split('=');
            document.getElementById('departureAirport').value = decodeURI(sParameterDA[1]);
        </script>

        <s:select id="arriAirport"
                  label="Arrival Airports"
                  headerKey="-1" headerValue="Select Arrival Airport"
                  list="#session.managerAirports"
                  name="arriAirport" /> <br>
        <script>
            var sParameterAA = sParameters[5].split('=');
            document.getElementById('arriAirport').value = decodeURI(sParameterAA[1]);
        </script>

        <s:select id="airline"
                  label="Airline"
                  headerKey="-1" headerValue="Select Airline"
                list="#session.managerAirlines"
                name="airline" /> <br>
        <script>
            var sParameterAI = sParameters[6].split('=');
            document.getElementById('airline').value = decodeURI(sParameterAI[1]);
        </script>

        <s:select id="aircraftModel"
                  label="Aircraft Model"
                  headerKey="-1" headerValue="Select Aircraft Model"
                  list="#session.managerAircraft"
                  name="aircraftModel" /> <br>
        <script>
            var sParameterAM = sParameters[7].split('=');
            document.getElementById('aircraftModel').value = decodeURI(sParameterAM[1]);
        </script>

        Price: <input id="price" type="text" name="price"><br>
        <script>
            var sParameterPR = sParameters[8].split('=');
            document.getElementById('price').value = decodeURI(sParameterPR[1]);
        </script>

        <input type="submit" value="submit">
    </form>
</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>
</body>
</html>
