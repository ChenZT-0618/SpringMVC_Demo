<%--
  Created by IntelliJ IDEA.
  User: CZT
  Date: 2021/3/26
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script>
        function a1() {
            $.ajax({
                url: "${pageContext.request.contextPath}/t3",
                data: {"username": $("#name").val()},
                success: function (data) {
                    if (data.toString() == 'OK') {
                        $("#result").css("color", "green");
                    } else {
                        $("#result").css("color", "red");
                    }
                    $("#result").html(data);
                }
            })
        }
    </script>
</head>
<body>
<p>
    用户名:<input type="text" id="name" onblur="a1()"/>
</p>
<p>
    <span id="result"></span>
</p>
</body>
</html>
