<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>
<div>
  <h1>Список дел</h1><br>
    <div>
        <ul>
            <%
                List<Task> tasks = (List<Task>) request.getAttribute("toDoList"); %>
            <%
                for (Task task:
                        tasks) { %>
            <li ><%=task.name%>
            </li>
            <%}%>
        </ul>
    </div>
  <form method="post" action="/home">
    <%
      Object str = request.getAttribute("Error");
      if (str != null) {
        System.out.print(str);
      }
    %>
    <p>
        <button name="exit" type="submit" >Выйти</button>
    </p>
  </form>
</div>
</body>
</html>
