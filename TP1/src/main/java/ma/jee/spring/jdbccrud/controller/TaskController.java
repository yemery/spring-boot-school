package ma.jee.spring.jdbccrud.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.jee.spring.jdbccrud.entity.Task;
import ma.jee.spring.jdbccrud.service.TaskService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskController", urlPatterns = {"/tasks", "/addTask", "/editTask", "/deleteTask"})
public class TaskController extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/addTask":
                request.getRequestDispatcher("/view/add-task.jsp").forward(request, response);
                break;

            case "/editTask":
                Long id = Long.parseLong(request.getParameter("id"));
                Task existingTask = taskService.getTaskById(id);
                request.setAttribute("task", existingTask);
                request.getRequestDispatcher("/view/edit-task.jsp").forward(request, response);
                break;

            case "/deleteTask":
                Long deleteId = Long.parseLong(request.getParameter("id"));
                taskService.deleteTask(deleteId);
                response.sendRedirect("tasks");
                break;

            default:
                List<Task> tasks = taskService.getAllTasks();
                request.setAttribute("tasks", tasks);
                request.getRequestDispatcher("/view/tasks.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/addTask":
                String title = request.getParameter("title");
                Task newTask = new Task();
                newTask.setTitle(title);
                taskService.createTask(newTask);
                response.sendRedirect("tasks");
                break;

            case "/editTask":
                Long id = Long.parseLong(request.getParameter("id"));
                String updatedTitle = request.getParameter("title");
                Task updatedTask = new Task();
                updatedTask.setId(id);
                updatedTask.setTitle(updatedTitle);
                taskService.updateTask(updatedTask);
                response.sendRedirect("tasks");
                break;

            default:
                response.sendRedirect("tasks");
                break;
        }
    }
}
