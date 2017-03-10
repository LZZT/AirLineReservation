<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Payment Information</title>
</head>
<body>

<h1>Payment Information</h1>
<br>

<s:actionerror cssStyle="color:red"/>
<br>

<form action="payment.action" method="post">

    Card Number: <input type="text" name="cardNumber"><br>

    Last Name: <input type="text" name="cardLastname"><br>

    First Name: <input type="text" name="cardFirstname"><br>

    <%--ExpDate: <input type="date" name="expDate"><br>--%>
    Expire Date: <input type="text"  size ="4" maxlength = "4" name="expDate"><br>
    <%--CVV: <input type="text" name="cvv"><br>--%>
    CVV: <input type="text" size = "3" maxlength="3" name = "cvv"><br>
    Billing Address: <input type="text" name="billingAddress"><br>

    <input type="submit" value="Continue">

</form>

</body>
</html>
