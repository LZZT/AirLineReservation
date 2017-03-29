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

    <script src="jquery-1.7.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#btn").click(function(){
                if ($(":radio:checked").length == 0)
                {
                    alert("你的性别未选择");
                }
            });
        });
    </script>


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

</head>


<body>
<s:actionerror cssStyle="color:red"/>
<h1>Traveler information</h1>
<form name="TravelersForm" action="traveler.action" onsubmit="return validateForm()" method="post">

    <%! int i;%>
    <% for (i = 1; i <= Integer.valueOf((String) session.getAttribute("ticketsNumber")); i++) {%>

    Passenger <%= i%>:<br>
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


</body>
</html>