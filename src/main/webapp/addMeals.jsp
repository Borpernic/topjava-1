<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmtbn" uri="http://ru.topjava/functions" %>

<html>
<head>
    <title>Add Meals</title>
</head>
<body>

<section>
    <form action="userMeals" name="addMeals" method="post">
        <jsp:useBean id="userMeal" scope="page" beanName="userMeal"
                     type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <table border="1">
            <thead>
            <tr>
                <th>Data</th>
                <th>Discriptions</th>
                <th>Calories</th>
                <th>Exceed</th>
            </tr>
            </thead>


            <td><input type="datetime-local" value=${now()} id="mealDataTime" name="mealDataTime"/></td>
            <td><input type="text" id="mealDescription" name="mealDescription"/></td>
            <td><input type="number" id="calories" name="calories"/></td>
            <td>
                <p>
                    <button type="submit" value="Сохранить">Удалить</button>
                </p>
            </td>
            </tr>


        </table>
    </form>
</section>

</body>
</html>