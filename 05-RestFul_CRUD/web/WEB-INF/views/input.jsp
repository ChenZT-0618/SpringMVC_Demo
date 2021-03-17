<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: CZT
  Date: 2021/3/9
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/emp" method="POST" modelAttribute="employee">
    <%--path对应html的name属性--%>
    <c:if test="${employee.id == null}">
        LastName:<form:input path="lastName"/>
        <br>
    </c:if>
    <c:if test="${employee.id != null}">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT">
    </c:if>
    Email:<form:input path="email"/>
    <br>
    <%
        Map<String,String> genders = new HashMap<>();
        genders.put("1","male");
        genders.put("0","female");
        request.setAttribute("genders",genders);
    %>
    Gender:<form:radiobuttons path="gender" items="${genders}"/>
    <br>
    Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/>
    <br>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
