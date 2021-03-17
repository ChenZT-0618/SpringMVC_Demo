<%--
  Created by IntelliJ IDEA.
  User: CZT
  Date: 2021/3/9
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(function () {
        $(".delete").click(function () {
            var href = $(this).attr("href");
            $("form").attr("action",href).submit();
            return false;
        })
        // alert("hello");
    })
</script>

<body>
    <form action="" method="POST">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>

    <c:if test="${empty requestScope.employees}">
        没有任何员工信息
    </c:if>
    <c:if test="${!empty requestScope.employees}">
        <table border="1" cellpadding="10">
            <tr>
                <th>ID</th>
                <th>lastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${employees}" var="emp">
                <tr>
                    <td>${emp.id }</td>
                    <td>${emp.lastName }</td>
                    <td>${emp.email }</td>
                    <td>${emp.gender == 0 ? 'Female' : 'Male' }</td>
                    <td>${emp.department.departmentName }</td>
                    <td><a href="emp/${emp.id}">Edit</a></td>
                    <td><a class="delete" href="emp/${emp.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="emp">Add New Employee</a>
</body>
</html>
