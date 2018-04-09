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
    <form action="userMeals" name="filter">
        <table border="0">
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
            <tr>
                <td>
                    <button type="submit" value="Добавить еду">Удалить</button>
            </tr>
            </td>
        </table>
    </form>
</section>

<section>
    <form action="userMeals" name="meals" method="post">
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
                    <p>
                        <input value="delete" id="action" name="action" hidden/>
                        <input value=${userMeal.id} id=${userMeal.id} name="id" hidden/>
                        <button formaction="" type="submit" value="Удалить">Удалить</button>
                    </p>

                        <%--<form action="userMeals" method="post">

                            <button type="submit" name="mealId" value="action=delete&id=${userMeal.id}" class="btn-link">Delete</button>
                        </form>--%>
                </td>
                </tr>


                <%-- <fmt: formatDate    value="${parseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
            </c:forEach>


        </table>
    </form>
</section>

</body>
</html>