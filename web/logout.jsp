<%--
  Created by IntelliJ IDEA.
  User: QQZhao
  Date: 3/5/17
  Time: 1:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Refresh" content="6;url=index.jsp">
<html>
<head>
    <title>Logout</title>
    
    
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

    <h1>You have successfully Logout!</h1>

    <h2>redirect in <font id="num">5</font> seconds</h2>

</body>
</html>
