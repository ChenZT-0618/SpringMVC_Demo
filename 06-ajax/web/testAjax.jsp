<%--
  Created by IntelliJ IDEA.
  User: CZT
  Date: 2021/3/26
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax异步加载测试</title>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(function () {
            // 点击按钮
            $("#btn").click(function () {
                // alert("hhhhh");
                // 请求数据
                $.post({
                    url: "${pageContext.request.contextPath}/t2",
                    success: function (data) {
                        var html = "";
                        for (let i = 0; i < data.length; i++) {
                            html += "<tr>" +
                                "<td>" + data[i].name + "</td>" +
                                "<td>" + data[i].age + "</td>" +
                                "<td>" + data[i].sex + "</td>" +
                                "</tr>"
                        }
                        $("#content").html(html);
                    }
                })
            });
        })

    </script>
</head>
<body>

<%--获取数据的事件--%>
<input type="button" id="btn" value="加载数据">
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <tbody id="content">
    <%--显示的数据：后台传过来的--%>
    </tbody>
</table>
</body>
</html>
