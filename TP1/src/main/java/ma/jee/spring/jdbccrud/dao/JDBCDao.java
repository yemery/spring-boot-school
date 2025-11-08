package ma.jee.spring.jdbccrud.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JDBCDao<E> implements IDao<E> {
    protected abstract String getTableName();

    protected abstract String getInsertQuery();

    protected abstract void setInsertValues(PreparedStatement ps, E entity) throws SQLException;

    protected abstract String getUpdateQuery();

    protected abstract void setUpdateValues(PreparedStatement ps, E entity) throws SQLException;

    protected abstract E mapRowToEntity(ResultSet resultSet) throws SQLException;


    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/jdbc-todo?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "";
            String driver = "com.mysql.cj.jdbc.Driver";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<E> getAll() {
        List<E> list = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                E obj = mapRowToEntity(resultSet);
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public E getById(Long id) {
        E entity = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity = mapRowToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public boolean create(E entity) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            setInsertValues(ps, entity);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(E entity) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            setUpdateValues(ps, entity);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
