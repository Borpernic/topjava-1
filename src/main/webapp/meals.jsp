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

    <table border="0">
        <form action="userMeals" name="filter">
            <tr>
                <td><label for="starttime"> Время начало:</label></td>

                <td><label for="starttime">Время конец:</label></td>
            </tr>
            <tr>
                <td>
                    <input id="starttime" type="time" name="sarttime" value="00:00">
                </td>
                <td>
                    <input id="endtime" type="time" name="endtime" value="23:59">
                </td>
                <td>
                    <input type="submit" value="Фильтр">
                </td>
            </tr>
        </form>
        <form action="userMeals" name="filter" method="post">
            <tr>
                <td>
                    <input value="addMeal" name="action" hidden/>
                    <input value=0 name="id" hidden/>
                    <button name="addButton" type="submit" value="addButton">Добавить еду</button>
                </td>
            </tr>

        </form>
    </table>

</section>

<section>

    <table border="2" frame="border" rules="all">
        <thead>
        <tr>
            <th>Id</th>
            <th>Date</th>
            <th>Discriptions</th>
            <th>Calories</th>
            <th>Exceed</th>
        </tr>
        </thead>


        <c:forEach items="${mealList}" var="userMeal">
            <jsp:useBean type="ru.javawebinar.topjava.model.MealWithExceed" id="userMeal" beanName="userMeal"
                         scope="page"></jsp:useBean>

            <c:if test="${userMeal.exceed}">
                <tr class="exceeded">

            </c:if>
            <c:if test="${!userMeal.exceed}">
                <tr class="normal">

            </c:if>
            <td>${userMeal.id} </td>
            <td style.normal>${fmtbn:formatLocalDateTime(userMeal.dateTime)}</td>
            <td>${userMeal.description} </td>
            <td>${userMeal.calories} </td>
            <td>${userMeal.exceed} </td>
            <td border="1">

                <form action="userMeals" name="mealsDeleteButton" method="post">
                    <input value="delete" name="action" hidden/>
                    <input value=${userMeal.id}  name="id" hidden/>
                    <button name="delete" type="submit" value="delete">Удалить</button>
                </form>
            </td>
            <td>
                <form action="userMeals" name="mealsUpdateButton" method="post">
                    <input value="addMeal" name="action" hidden/>
                    <input value=${userMeal.id} name="id" />
                    <input value=${userMeal.dateTime} name="mealDateTime" hidden/>
                    <input value=${userMeal.description} name="mealDescription" hidden/>
                    <input value=${userMeal.calories} name="mealCalories" hidden/>
                    <button name="updateButton" type="submit" value="update">Обновить</button>
                </form>

                    <%--<form action="userMeals" method="post">

                              <button type="submit" name="mealId" value="action=delete&id=${userMeal.id}" class="btn-link">Delete</button>
                          </form>--%>
            </td>
            </tr>


            <%-- <fmt: formatDate    value="${parseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
        </c:forEach>

    </table>

</section>

</body>
</html>