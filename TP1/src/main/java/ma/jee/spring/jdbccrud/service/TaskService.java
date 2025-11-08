package ma.jee.spring.jdbccrud.service;

import ma.jee.spring.jdbccrud.dao.IDao;
import ma.jee.spring.jdbccrud.dao.JDBCDao;
import ma.jee.spring.jdbccrud.entity.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskService {

    private final IDao<Task> taskDao = new JDBCDao<Task>() {

        @Override
        protected String getTableName() {
            return "tasks";
        }

        @Override
        protected String getInsertQuery() {
            return "INSERT INTO tasks (title, description, status) VALUES (?, ?, ?)";
        }

        @Override
        protected void setInsertValues(PreparedStatement ps, Task entity) throws SQLException {
            ps.setString(1, entity.getTitle());
        }

        @Override
        protected String getUpdateQuery() {
            return "UPDATE tasks SET title = ?, description = ?, status = ? WHERE id = ?";
        }

        @Override
        protected void setUpdateValues(PreparedStatement ps, Task entity) throws SQLException {
            ps.setString(1, entity.getTitle());
            ps.setLong(4, entity.getId());
        }

        @Override
        protected Task mapRowToEntity(ResultSet rs) throws SQLException {
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setTitle(rs.getString("title"));
            return task;
        }
    };

    public java.util.List<Task> getAllTasks() {
        return taskDao.getAll();
    }

    public Task getTaskById(Long id) {
        return taskDao.getById(id);
    }

    public void createTask(Task task) {
        taskDao.create(task);
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

    public void deleteTask(Long id) {
        taskDao.delete(id);
    }
}
