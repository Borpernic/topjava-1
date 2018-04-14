<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>


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
<h2>Users</h2>
<script>

    function passwordbyUser() {

        var objSel = document.getElementById("users");
        if (objSel.selectedIndex != 1) {
            return objSel.options[objSel.selectedIndex].value
        }
    }
</script>
<nav>
    <form action="meals">
        <select id="users" name="users[user]" size="1" onchange="passwordbyUser()">
            <option value="user" selected userPassword="321">User</option>
            <option value="admin" userPassword="123">Admin</option>
        </select> = <span id="output">0</span>

        <hr/>
        <label id="emailLabel" for="email">Email</label>
        <input type="text" id="email" name="email" value="User@gmail.com" size="20">  </input>
        <p></p>
        <label id="passwordLabel" for="password">Password</label>
        <input type="text" id="password" name="password" value="passwordbyUser()" size="20">123</input>
        <hr/>
        <button type="submit">Login</button>


    </form>

    <script>
        var select = document.querySelector("select");
        var output = document.querySelector("#output");
        select.addEventListener("change", function() {
            var number = 0;
            for (var i = 0; i < select.options.length; i++) {
                var option = select.options[i];
                if (option.selected)
                    number += Number(option.value);
            }
            output.textContent = number;
        });
    </script>
</nav>

</body>
</html>