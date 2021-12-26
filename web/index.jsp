<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Вход в систему / Регистрация</h1><br>
    <form method="post" action="">
        <input type="text" required placeholder="Логин" name="login"><br>
        <input type="password" required placeholder="Пароль" name="password"><br><br>
        <%
            Object str = request.getAttribute("Error");
            if (str != null) {
                System.out.print(str);
            }
        %>
        <p>
            <button name="signin" type="submit" >Войти</button>
            <button  name="signup" type="submit">Зарегистрироваться</button>
        </p>
    </form>
</div>
</body>
</html>
