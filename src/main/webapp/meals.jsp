<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>

    <style>
        div.container {
            width: 100%;
            border: 1px solid gray;
        }

        header, footer {
            padding: 1em;
            color: white;
            background-color: black;
            clear: left;
            text-align: center;
        }

        nav {
            float: left;
            max-width: 160px;
            margin: 0;
            padding: 1em;
        }

        nav ul {
            list-style-type: none;
            padding: 0;
        }

        nav ul a {
            text-decoration: none;
        }

        article {
            margin-left: 1px;
            border-left: 1px solid gray;
            border-right: 1px solid gray;
            padding: 1em;
            overflow: hidden;
        }

        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>

<header>
    <title>Meal list</title>


    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>

    <table border="0">
        <form action="meals" name="filter">
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
                    <input value="filter" name="action" hidden/>
                    <button type="submit" name="Фильтр">Фильтр</button>
                </td>
            </tr>
        </form>
    </table>
</header>
<nav>
    <form action="meals">
        <select name="users" size="3" >
            <option value="user" selected password="321">User</option>
            <option value="admin" password="123">Admin</option>
        </select>

        <input type="submit" name="password" value="321">  </input>
    </form>
</nav>


<article>
    <section>
        <a href="meals?action=create">Add Meal</a>
        <hr/>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
                <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
</article>
</body>
</html>