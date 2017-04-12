<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="model.Traveler" %>
<%@ page import="java.util.List" %>
<%@ page import="service.TravelerService" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Blue Arc Theme - Free Website Template</title>
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
            <li><a href="index.jsp" class="current">Home</a></li>
            <li><a href="reloadDB.action">Manager</a></li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="contact.html">Contact</a></li>
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
                    <input type="button" value="My trip" onclick="location.href='mytrip.jsp';">
                </form>
                <% }%>

            </li>
        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">

        <div><h1>Traveler information</h1></div>

        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script src="jquery-1.7.1.min.js"></script>
        <%--<script>--%>
        <%--$(document).ready(function () {--%>
        <%--$("#btn").click(function () {--%>
        <%--if ($(":radio:checked").length == 0) {--%>
        <%--alert("你的性别未选择");--%>
        <%--}--%>
        <%--});--%>
        <%--});--%>
        <%--</script>--%>
        <script type="text/javascript">
            $(function () {
                $('.dateTxt').datepicker({maxDate: 0});
            });

            function validateForm() {
                <%--var sex = document.getElementsByName("travelerList[<%= i-1%>].gender");--%>
                <%--var flag = false;--%>
                <%--for (var x=0;x<sex.length;x++)--%>
                <%--{--%>
                <%--if (sex.item(x).checked == true)--%>
                <%--{--%>
                <%--flag = true;--%>
                <%--break;--%>
                <%--}--%>
                <%--}--%>
                <%--if (!flag)--%>
                <%--{--%>
                <%--alert("请选择性别");--%>
                <%--return false;--%>
                <%--}--%>

                <%--var lastname = document.forms["TravelersForm"]["travelerList[<%= i-1%>].lastname"].value;--%>
                <%--if (lastname == null || lastname.length < 3) {--%>
                <%--alert("Length of the lastname can not be less than 3! ");--%>
                <%--return false;--%>
                <%--}--%>

                <%--var firstname = document.forms["TravelersForm"]["travelerList[<%= i-1%>].firstname"].value;--%>
                <%--if (firstname == null || firstname.length < 3) {--%>
                <%--alert("Length of the firstname can not be less than 3!");--%>
                <%--return false;--%>
                <%--}--%>

                <%--var dob = document.forms["TravelersForm"]["travelerList[<%= i-1%>].dob"].value;--%>
                <%--if (dob == null) {--%>
                <%--alert("Dob can not be null!");--%>
                <%--return false;--%>
                <%--}--%>

                <%--var phone=document.forms["TravelersForm"]["travelerList[<%= i-1%>].phone"].value;--%>
                <%--var reg=/^([0-9]|[\-])+$/g ;--%>
                <%--if(phone.length<7 || phone.length>18){--%>
                <%--alert("Invalid phone number length!");--%>

                <%--return false;--%>
                <%--}else{--%>
                <%--if(!reg.exec(phone)){--%>
                <%--alert("Invalid phone number!");--%>
                <%--return false;--%>
                <%--}--%>
                <%--}--%>

                <%--var email=document.forms["TravelersForm"]["travelerList[<%= i-1%>].email"].value;--%>
                <%--var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|cc|edu|biz|int|tv)$"--%>
                <%--var re = new RegExp(regu);--%>
                <%--if (email.search(re) != -1) {--%>
                <%--return true;--%>
                <%--}--%>
                <%--else {--%>
                <%--alert ("请输入有效合法的E-mail地址！")--%>
                <%--document.joinus.email.focus()--%>
                <%--return false;--%>
                <%--}--%>
            }
        </script>


    </div> <!-- end of header -->

    <div id="tooplate_middle2">

        <h3>Choose traveler from Order history</h3>

        <table width="80%" align="center" border="1">
            <tr>
                <th>Last Name</th>
                <th>First Name</th>
                <th>Gender</th>
                <th>Phone</th>
            </tr>

                <% TravelerService ts = new TravelerService();
            String username = (String) session.getAttribute("username");
            List<Traveler> travelerList= ts.getTravelerByUsername(username);

            session.setAttribute("TravelersHistoryList",travelerList);

            for (int i = 0; i<travelerList.size(); i++) {

               %>

            <form action="traveler.action" method="post">
                <tr>
                    <td>
                        <%= travelerList.get(i).getLastname() %>
                    </td>
                    <td>
                        <%= travelerList.get(i).getFirstname() %>
                    </td>
                    <td>
                        <%= travelerList.get(i).getGender() %>
                    </td>
                    <td>
                        <%= travelerList.get(i).getPhone() %>
                    </td>

                    <td>
                        <input type="checkbox" name="travelerHistory" value= <%= i%>"/>
                    </td>

                </tr>
                <%--</form>--%>


           <%}%>
        </table>

            <%--<form name="TravelersForm" action="traveler.action" onsubmit="return validateForm()" method="post">--%>

                <%! int i;%>
                <% for (i = 1; i <= Integer.valueOf((String) session.getAttribute("ticketsNumber")); i++) {%>

                <h3>Passenger <%= i%>:</h3>
                <input type="radio" name="travelerList[<%= i-1%>].gender" value="M"/>male
                <input type="radio" name="travelerList[<%= i-1%>].gender" value="F"/>female
                <br>
                Last Name: <input type="text" name="travelerList[<%= i-1%>].lastname"><br>
                First Name: <input type="text" name="travelerList[<%= i-1%>].firstname"><br>
                Date of birth: <input type="text" name="travelerList[<%= i-1%>].dob" class="dateTxt" id= <%= i-1%>><br>
                Phone: <input type="text" name="travelerList[<%= i-1%>].phone"><br>
                Email: <input type="email" name="travelerList[<%= i-1%>].email"><br>
                <br>
                <%}%>


                <input type="submit" value="submit">
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
