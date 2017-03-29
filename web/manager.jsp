<%--
  Created by IntelliJ IDEA.
  User: yenchanghsieh
  Date: 3/8/17
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>


    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="https://code.jquery.com/ui/3.2.1/jquery-ui.js"></script>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

    <script>
        $(document).ready(function() {
            $("input[name$='manager']").click(function() {
                var test = $(this).val();

                $("div.desc").hide();
                if(test === "insert")
                    $("#insert").show();
                else if(test === "search")
                    $("#search").show();
            });
        });
    </script>

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
                    alert("You must select one day operated.");
                    return false;
                }

            });
        });

    </script>
</head>
<body>
    <!-- -->

    <s:actionerror cssStyle="color:red"/>

    <div id="managerOp">
        <input type="radio" name="manager" value="insert" checked/>Insert Flight
        <input type="radio" name="manager" value="search"  />Search Flight


        <div id="insert" class="desc">
            <form action="insertFlight.action" method="post">

                Flight Number: <input type="text" name="flightNumber" required>*</input><br>

                Departure Time: <input type="text" name="departTime" id="departTime" required><br>

                Arrival Time: <input type="text" name="arriTime" id="arriTime" required><br>

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
                <select name="arriAirport" size="5" style="width:200px;" required>
                    <option value="BOS">Boston Logan(BOS)</option>
                    <option value="EWR">Newark Liberty(EWR)</option>
                    <option value="JFK">John F. Kennedy(JFK)</option>
                    <option value="SFO">San Francisco(SFO)</option>
                </select><br>

                Airline: <br>
                <select name="airline" size="5" style="width:100px;" required>
                    <option value="America Airline">America Airline</option>
                    <option value="Southwest">Southwest</option>
                    <option value="United Airline">United Airline</option>
                </select><br>

                Aircraft Model:<br>
                <select name="aircraftModel" size="5" style="width:100px;" required>
                    <option value="Airbus 330">Airbus 330</option>
                    <option value="Boeing 777">Boeing 777</option>
                    <option value="Saab">Saab</option>
                </select><br>

                Price: <input type="text" name="price" required><br>

                <input type="submit" value="submit" id="submit">
            </form>
        </div>

        <div id ="search" class="desc" hidden>
            <form action="searchFlightByFlightNumber.action" method="post">
                Flight Number: <input type="text" name="flightNumber"><br>

                <input type="submit" value="submit">
            </form>
        </div>

    </div>
</body>
</html>
