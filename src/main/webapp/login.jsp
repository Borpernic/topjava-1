<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>


    <style>
        nav {
            float: left;
            max-width: 200px;
            margin: 0;
            padding: 1em;
        }


    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Login</h2>

<nav>
    <form action="meals">
        <select id="users" name="users" size="1" onchange="passwordbyUser()">
            <option value="user" selected userPassword="321">User</option>
            <option value="admin" userPassword="123">Admin</option>
        </select> = <span id="output">0</span>

        <hr/>
        <label id="emailLabel" for="email">Email</label>
        <input type="text" id="email" name="email" value="User@gmail.com" size="20">  </input>
        <p></p>
        <label id="passwordLabel" for="password">Password</label>
        <input type="text" id="password" name="password" value="passwordbyUser()" size="20">123</input>
        <input type="text" id="action" name="action" value="login()" hidden></input>
        <hr/>
        <button type="submit">Вход</button>
        <button type="button" onclick="window.history.back()">Отмена</button>
    </form>

</nav>

</body>
</html>