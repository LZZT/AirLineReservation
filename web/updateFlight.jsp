<%--
  Created by IntelliJ IDEA.
  User: yenchanghsieh
  Date: 3/9/17
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    Departure Airport: <br>
    <select name="departAirport" size="5" style="width:200px;">
        <option value="BOS">Boston Logan(BOS)</option>
        <option value="EWR">Newark Liberty(EWR)</option>
        <option value="JFK">John F. Kennedy(JFK)</option>
        <option value="SFO">San Francisco(SFO)</option>
    </select><br>


    Arrival Airport: <br>
    <select name="departAirport" size="5" style="width:200px;">
        <option value="BOS">Boston Logan(BOS)</option>
        <option value="EWR">Newark Liberty(EWR)</option>
        <option value="JFK">John F. Kennedy(JFK)</option>
        <option value="SFO">San Francisco(SFO)</option>
    </select><br>

    Airline: <br>
    <select name="airline" size="5" style="width:100px;">
        <option value="America Airline">America Airline</option>
        <option value="Southwest">Southwest</option>
        <option value="United Airline">United Airline</option>
    </select><br>

    Aircraft Model:<br>
    <select name="aircraftModel" size="5" style="width:100px;">
        <option value="Airbus 330">Airbus 330</option>
        <option value="Boeing 777">Boeing 777</option>
        <option value="Saab">Saab</option>
    </select><br>

    Price: <input type="text" name="price"><br>

    <input type="submit" value="submit">
</form>
</body>
</html>
