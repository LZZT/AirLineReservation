<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Refresh" content="6;url=index.jsp">
<html>
<head>
    <title></title>
    <script type="text/javascript">

        var sec = 3;
        var timeId = setInterval("count();", 1000);

        function count() {
            if (document.getElementById("num").innerHTML > 0){
                document.getElementById("num").innerHTML = sec--;
            }
        }

    </script>
</head>
<body>

<h1>You have reserved successfully!</h1>

<h2>redirect in <font id="num">3</font> seconds</h2>

</body>
</html>
