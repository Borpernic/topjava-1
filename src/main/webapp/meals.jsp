<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<form action="Meals">
    <table border="0">
        <tr>
            <td>Время начало:</td>
            <td>Время конец:</td>
        </tr>
        <tr>
            <td>
                <input type="time" name="sarttime">
            </td>
            <td>
                <input type="time" name="endtime">
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


    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" beanName="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>

        <c:if test="${meal.exceed}">
            <tr class="exceeded">

        </c:if>
        <c:if test="${!meal.exceed}">
            <tr class="normal">

        </c:if>

        <td style.normal>${meal.dateTime} </td>
        <td>${meal.description} </td>
        <td>${meal.calories} </td>
        <td>${meal.exceed} </td>
        </tr>
    </c:forEach>


</table>


</body>
</html>