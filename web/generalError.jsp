
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Refresh" content="6;url=index.jsp">



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Blue Arc Theme - Free Website Template</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />



    <script type="text/javascript">

        var sec = 5;
        var timeId = setInterval("count();", 1000);

        function count() {
            if (document.getElementById("num").innerHTML > 0){
                document.getElementById("num").innerHTML = sec--;
            }
        }

    </script>
</head>
<body>

<br><br>

<div id="tooplate_wrapper">

    <div id="tooplate_menu">
        <ul>
            <li><a href="index.jsp" class="current">Home</a></li>
            <li><a href="manager.jsp">Manager</a></li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="contact.html">Contact</a></li>
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
                    <input type="button" value="My trip" onclick="location.href='mytrip.jsp';">
                </form>
                <% }%>


            </li>
        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">

        <div><h1>Error</h1></div>

    </div> <!-- end of header -->

    <div id="tooplate_middle">

        <h2>Something is wrong with your query, please search again!</h2>

        <h2>redirect in <font id="num">5</font> seconds</h2>

    </div>


</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>
