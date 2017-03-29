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
</head>
<body>
<form action="updateFlight.action" method="post">

    Flight Number: <input type="text" name="flightNumber"><br>

    Departure Time: <input type="text" name="departTime" id="departTime"><br>

    Arrival Time: <input type="text" name="arriTime" id="arriTime"><br>

    Days Operated:  <input type="checkbox" name="daysOperated" value="Mon">Mon
    <input type="checkbox" name="daysOperated" value="Tue">Tue
    <input type="checkbox" name="daysOperated" value="Wed">Wed
    <input type="checkbox" name="daysOperated" value="Thu">Thu
    <input type="checkbox" name="daysOperated" value="Fri">Fri
    <input type="checkbox" name="daysOperated" value="Sat">Sat
    <input type="checkbox" name="daysOperated" value="Sun">Sun<br>

    <s:select label="Departure Airports"
              headerKey="-1" headerValue="Select Departure Airport"
              list="#session.managerAirports"
              name="airport" /> <br>

    <s:select label="Arrival Airports"
              headerKey="-1" headerValue="Select Arrival Airport"
              list="#session.managerAirports"
              name="airport" /> <br>

    <s:select label="Airline"
              headerKey="-1" headerValue="Select Airline"
              list="#session.managerAirlines"
              name="airline" /> <br>

    <s:select label="Aircraft Model"
              headerKey="-1" headerValue="Select Aircraft Model"
              list="#session.managerAircraft"
              name="aircraft" /> <br>

    Price: <input type="text" name="price"><br>

    <input type="submit" value="submit">
</form>
</body>
</html>
