<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.jee.spring.jdbccrud.entity.Task" %>
<html>
<head>
    <title>Tasks</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #fff;
            color: #000;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 40px;
        }
        h1 {
            font-size: 1.8em;
            border-bottom: 2px solid #000;
            padding-bottom: 5px;
        }
        table {
            border-collapse: collapse;
            width: 60%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #000;
            padding: 8px 12px;
            text-align: left;
        }
        th {
            background: #f2f2f2;
        }
        tr:hover {
            background: #f9f9f9;
        }
        a {
            text-decoration: none;
            color: #000;
            margin-right: 8px;
        }
        a:hover {
            text-decoration: underline;
        }
        .add-btn {
            border: 1px solid #000;
            padding: 6px 12px;
            margin-top: 15px;
            background: none;
        }
        .add-btn:hover {
            background: #000;
            color: #fff;
        }
    </style>
</head>
<body>
<h1>Tasks</h1>
<a href="addTask" class="add-btn">Add New Task</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
        if (tasks != null) {
            for (Task task : tasks) {
    %>
    <tr>
        <td><%= task.getId() %></td>
        <td><%= task.getTitle() %></td>
        <td>
            <a href="editTask?id=<%= task.getId() %>">Edit</a>
            <a href="deleteTask?id=<%= task.getId() %>" style="color:red;">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>
