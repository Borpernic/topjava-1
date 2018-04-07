<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmtbn" uri="http://ru.topjava/functions" %>

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
<section>
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
</section>

<section>
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
            <jsp:useBean id="userMeal" scope="page" beanName="userMeal"
                         type="ru.javawebinar.topjava.model.MealWithExceed"/>

            <c:if test="${userMeal.exceed}">
                <tr class="exceeded">

            </c:if>
            <c:if test="${!userMeal.exceed}">
                <tr class="normal">

            </c:if>

            <td style.normal>${fmtbn:formatLocalDateTime(userMeal.dateTime)}</td>
            <td>${userMeal.description} </td>
            <td>${userMeal.calories} </td>
            <td>${userMeal.exceed} </td>
            <td>
                <form action="userMeals" method="post">
                    <button type="submit" name="deletename" value="delete&age=${userMeal.id}" class="btn-link">Delete</button>
                </form>




            </td>
            </tr>


            <%-- <fmt: formatDate    value="${parseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
        </c:forEach>


    </table>
</section>

</body>
</html>