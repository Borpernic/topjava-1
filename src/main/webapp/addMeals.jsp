<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmtbn" uri="http://ru.topjava/functions" %>

<html>
<head>
    <title>Add Meals</title>
</head>


<body>
<h2>Add meal</h2>
<section>
    <c:set var="meal" value="${meal}"/>
    <jsp:useBean type="ru.javawebinar.topjava.model.UserMeal" id="meal" beanName="meal"
                 scope="page"></jsp:useBean>
    <form action="userMeals" name="addMeals" method="post">
        <table>
            <tr>
                <td>
                    <label for="id" id="id">Id</label>
                </td>
                <td>
                    <label for="dateTime" id="dateTime">Date</label>
                </td>
                <td>
                    <label for="Discriptions" id="Discriptions">Discriptions</label>
                </td>
                <td>
                    <label for="Calories" id="Calories">Calories</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="id" value="${meal.id}"/>
                </td>
                <td>
                    <input name="mealDateTime" type="datetime-local" value="${meal.dateTime}"/>
                </td>
                <td>
                    <input name="mealDescription" type="text" value="${meal.description}"/>
                </td>
                <td>
                    <input name="mealCalories" type="number" value="${meal.calories}"/>
                </td>
                <td>
                    <input name="action" value="save"/>
                </td>
            </tr>
        </table>
        <button type="submit" value="save" name="addButton">Сохранить</button>

    </form>
    <form action="userMeals" name="cancelButton" method="post">
        <button onclick=window.history.back()>Отмена</button>
    </form>
</section>

</body>
</html>