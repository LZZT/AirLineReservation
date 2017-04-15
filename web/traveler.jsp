<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="model.Traveler" %>
<%@ page import="java.util.List" %>
<%@ page import="service.TravelerService" %>

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
    <script type="text/javascript">
        function addMorePassengerRow(tableID) {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount); //to insert blank row

            var cell1 = row.insertCell(0);   //to insert first column
            var snoCol = document.createElement("input");
            snoCol.type = "text";
            snoCol.name = "travelerSet[" + (rowCount - 1) + "].lastname";
//            snoCol.value = rowCount;
            cell1.appendChild(snoCol);

            var cell2 = row.insertCell(1); //to insert second column
            var nameCol = document.createElement("input");
            nameCol.type = "text";
            nameCol.name = "travelerSet[" + (rowCount - 1) + "].firstname";
            cell2.appendChild(nameCol);

            var cell3 = row.insertCell(2); // to insert 3rd column
            var genderCol = document.createElement("input");
            genderCol.type = "text";
            genderCol.name = "travelerSet[" + (rowCount - 1) + "].gender";
            cell3.appendChild(genderCol);

            var cell4 = row.insertCell(3);  //to insert 4th column
            var ageCol = document.createElement("input");
            ageCol.type = "text";
            ageCol.name = "travelerSet[" + (rowCount - 1) + "].dob";
            cell4.appendChild(ageCol);

            var cell5 = row.insertCell(4);  //to insert 5th column
            var phoneCol = document.createElement("input");
            phoneCol.type = "text";
            phoneCol.name = "travelerSet[" + (rowCount - 1) + "].phone";
            cell5.appendChild(phoneCol);

            var cell6 = row.insertCell(5);  //to insert 6th column
            var emailCol = document.createElement("input");
            emailCol.type = "email";
            emailCol.name = "travelerSet[" + (rowCount - 1) + "].email";
            cell6.appendChild(emailCol);

            var cell7 = row.insertCell(6);   // to insert 7th column
            var rowRemoveCol = document.createElement("a");
            var text = document.createTextNode("Delete");
            rowRemoveCol.appendChild(text);
            rowRemoveCol.setAttribute("href", "javascript:goSubmit(" + (rowCount - 1) + ")");
            rowRemoveCol.name = "reqlink[]";
            cell7.appendChild(rowRemoveCol);

            return false;

        }

        function goSubmit(rowindex) {
//            var Form = document.getElementsByID("TravelerForm");
//            var rowCountofForm = Form.row.length;
//            var table = document.getElementById(tableID);
//            var rowCount = table.rows.length;

            document.TravelerForm.pdTable.rowIndex.value=rowindex

            document.TravelerForm.action="delete";
            document.TravelerForm.submit();

        }

    </script>

    <%--<script type="text/javascript">--%>
        <%--$(document).ready(function () {--%>
            <%--$('#submit').click(function() {--%>
                <%--checked = $("input[type=radio]:checked").length;--%>

                <%--if(!checked) {--%>
                    <%--alert("You must select one type of trip.");--%>
                    <%--return false;--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>


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
                    <input type="button" value="My trip" onclick="location.href='mytrip.jsp';">
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
        <s:form name="TravelerForm" action="traveler" method="post">
            <s:hidden name="rowindex"/>


            <table width="100%" align="center" border="1">
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Phone</th>
                    <th>Email</th>
                </tr>

                <% TravelerService ts = new TravelerService();
                    String username = (String) session.getAttribute("username");
                    List<Traveler> travelerList = ts.getTravelerByUsername(username);

                    session.setAttribute("TravelersHistoryList", travelerList);

                    for (int i = 0; i < travelerList.size(); i++) {

                %>


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
                        <%= travelerList.get(i).getDob() %>
                    </td>
                    <td>
                        <%= travelerList.get(i).getPhone() %>
                    </td>
                    <td>
                        <%= travelerList.get(i).getEmail() %>
                    </td>

                    <td>
                        <input type="checkbox" name="travelerHistory" value="<%= i%>"/>
                    </td>

                </tr>
                <%}%>
            </table>

            <table>

                <tr>

                    <td colspan=1 align="right">
                        <input type="button" value="Add More Passenger" onclick="addMorePassengerRow('pdTable')"/>
                    </td>

                </tr>
            </table>


            <table id="pdTable" width="100%" align="center" border="1">


                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>


                <s:iterator value="list" status="cnt">
                    <tr>
                        <TD><s:textfield name="travelerSet[%{#cnt.count-1}].lastname"/></TD>
                        <TD><s:textfield name="travelerSet[%{#cnt.count-1}].firstname"/></TD>
                        <TD><s:textfield name="travelerSet[%{#cnt.count-1}].gender"/></TD>
                        <TD><s:textfield name="travelerSet[%{#cnt.count-1}].dob"/></TD>
                        <TD><s:textfield name="travelerSet[%{#cnt.count-1}].phone"/></TD>
                        <TD><s:textfield name="travelerSet[%{#cnt.count-1}].email"/></TD>
                        <TD><a href="javascript:goSubmit(<s:property value='#cnt.count-1'/>)">delete</a></TD>
                    </tr>
                </s:iterator>


            </table>
            <table width="80%" align="left">
                <tr>
                    <td align="left">
                        <input type="submit" value="submit">
                    </td>

                </tr>
            </table>


        </s:form>

    </div>
</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>
