<%@ page import="model.Traveler" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tonggezhu
  Date: 3/8/17
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Traveler</title>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#datepicker" ).datepicker({
                maxDate: 0
            });
        });
    </script>
</head>


<body>
<s:actionerror cssStyle="color:red"/>
<h1>Traveler information</h1>
<form action="traveler.action" method="post">

    <%! int i;%>
    <% for (i = 1; i <= Integer.valueOf((String) session.getAttribute("ticketsNumber")) ; i++) {%>

    Passenger <%= i%>:<br>
    <input type="radio" name="travelerList[<%= i-1%>].gender" value="M"/>male
    <input type="radio" name="travelerList[<%= i-1%>].gender" value="F"/>female
    <br>
    Last Name: <input type="text" name="travelerList[<%= i-1%>].lastname"><br>
    First Name: <input type="text" name="travelerList[<%= i-1%>].firstname"><br>
    Date of birth: <input type="date" name="travelerList[<%= i-1%>].dob" id="datepicker"><br>
    Phone: <input type="text" name="travelerList[<%= i-1%>].phone"><br>
    Email: <input type="email" name="travelerList[<%= i-1%>].email"><br>
    <br>
    
    <%}%>


    <input type="submit" value="submit">
</form>


</body>
</html>
