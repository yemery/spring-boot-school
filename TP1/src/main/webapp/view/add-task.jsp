<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Task</title>
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
        form {
            display: flex;
            flex-direction: column;
            width: 250px;
            margin-top: 20px;
        }
        input[type="text"] {
            border: 1px solid #000;
            padding: 8px;
            margin-bottom: 12px;
        }
        button {
            border: 1px solid #000;
            background: none;
            padding: 8px;
        }
        button:hover {
            background: #000;
            color: #fff;
        }
        a {
            margin-top: 15px;
            color: #000;
        }
    </style>
</head>
<body>
<h1>Add New Task</h1>
<form action="addTask" method="post">
    <input type="text" name="title" placeholder="Task Title" required>
    <button type="submit">Save</button>
</form>
<a href="tasks">← Back to list</a>
</body>
</html>
