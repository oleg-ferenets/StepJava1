<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<table border="1">
    <thead>
    <tr>
        <td>description</td>
        <td>time</td>
        <td>calories</td>
        <td>exceed</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${mealList}" var="item">
        <jsp:useBean id="item" type="ru.javawebinar.topjava.model.MealWithExceed"/>




        <tr>
            <td>${item.description}</td>
            <td>${item.dateTime}</td>
            <td>${item.calories}</td>
            <td>${item.exceed}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<h2>Meals</h2>
</body>
</html>