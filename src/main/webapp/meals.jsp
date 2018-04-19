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
            color: goldenrod;
            background-color: bisque;
            clear: left;
            text-align: center;
        }

        nav {
            float: left;
            max-width: 30%;
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

        table {

            width: 80%
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


    <form action="meals" name="filter">
        <ul>
            <label for="starttime"> Время начало:</label>

            <label for="starttime">Время конец:</label>
        </ul>
        <ul>

            <input id="starttime" type="time" name="sarttime" value="00:00">

            <input id="endtime" type="time" name="endtime" value="23:59">

            <input value="filter" name="action" hidden/>
            <button type="submit" name="Фильтр">Фильтр</button>

        </ul>
    </form>

</header>
<
<nav>

    <c:if test="${user}!=null">
        <jsp:useBean id="user" scope="session" type="ru.javawebinar.topjava.model.User"/>
        <c:set var="user" value="Вы вошли как ${user.name}, ${user.roles}"/>
        <span>"Вы вошли как ${user.name}, ${user.roles}"</span>
    </c:if>
    <c:if test="${user}==null">
        <c:set var="user" value="Вы вошли как незарегистрированный пользователь"/>
        <c:out value="user"/>
        <p>bn</p>
    </c:if>
    <form action="meals">


        <hr/>
        <label id="emailLabel" for="email">Email</label>
        <input type="text" id="email" name="email" value="User@gmail.com" size="20"> </input>
        <p></p>
        <label id="passwordLabel" for="password">Password</label>
        <input type="text" id="password" name="password" value="passwordbyUser()" size="20">123</input>
        <input type="text" id="action" name="action" value="login" hidden></input>
        <hr/>
        <button type="submit">Вход</button>
        <button type="button" onclick="window.history.back()">Отмена</button>
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