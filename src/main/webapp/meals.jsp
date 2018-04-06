<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals list</h2>

<style>
    .normal {
        color: green
    }

    .exceeded {
        color: red
    }

</style>

<form action="userMeals">
    <table border="0">
        <tr>
            <td>Время начало:</td>
            <td>Время конец:</td>
        </tr>
        <tr>
            <td>
                <input type="time" name="sarttime" value="00:00">
            </td>
            <td>
                <input type="time" name="endtime" value="23:59">
            </td>
            <td>
                <input type="submit" value="Фильтр">
            </td>
        </tr>

    </table>

</form>
<table border="1">
    <thead>
    <tr>
        <th>Data</th>
        <th>Discriptions</th>
        <th>Calories</th>
        <th>Exceed</th>
    </tr>
    </thead>


    <c:forEach items="${mealList}" var="userMeal">
        <jsp:useBean id="userMeal" scope="page" beanName="userMeal" type="ru.javawebinar.topjava.model.MealWithExceed"/>

        <c:if test="${userMeal.exceed}">
            <tr class="exceeded">

        </c:if>
        <c:if test="${!userMeal.exceed}">
            <tr class="normal">

        </c:if>

        <td style.normal>${userMeal.dateTime}</td>
        <td>${userMeal.description} </td>
        <td>${userMeal.calories} </td>
        <td>${userMeal.exceed} </td>
        </tr>
        <fmt:parseDate value="${userMeal.dateTime}" pattern="y-M-dd' T' H:m" var="parseDate"/>
        <fmt:formatDate value="${parseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
    </c:forEach>


</table>


</body>
</html>