package manager;

import model.Status;
import model.Task;
import utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskManager {

    public final Connection connection = DatabaseConnector.getConnection();

//    public TaskManager() throws SQLException {
//    }

    public int createTask(Task task) throws SQLException {
        String create = "INSERT INTO tasks (title, status, description, user_id)  VALUES (?, ?, ?, ?) RETURNING task_id";
        PreparedStatement createStatement = connection.prepareStatement(create);
        createStatement.setString(1, task.name);
        createStatement.setString(2, String.valueOf(task.status));
        createStatement.setString(3, task.description);
        createStatement.setInt(4, task.owner_id);
        ResultSet resultSet = createStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
        //return false;
    }

    public boolean deleteTask(int task_id) throws SQLException {
        String delete = "DELETE FROM tasks WHERE task_id=?";
        PreparedStatement deleteStatement = connection.prepareStatement(delete);
        deleteStatement.setInt(1, task_id);
        //System.out.println();
        return deleteStatement.execute();
        //return false;
    }

    //опасно
    public ResultSet getTask(int task_id) throws SQLException {
        String find = "SELECT * FROM tasks WHERE task_id=?";
        PreparedStatement findStatement = connection.prepareStatement(find);
        findStatement.setInt(1, task_id);
        return findStatement.executeQuery();

//        resultSet.next();
//        return new Task(resultSet.getInt(1),
//                resultSet.getString(2),
//                Status.valueOf(resultSet.getString(3)),
//                resultSet.getString(4),
//                resultSet.getInt(5));
        //return null;
    }

    public ResultSet getAllTasks(int user_id) throws SQLException {
        String find = "SELECT * FROM tasks WHERE user_id=? ORDER BY task_id";
        PreparedStatement findStatement = connection.prepareStatement(find);
        findStatement.setInt(1, user_id);
        return findStatement.executeQuery();
        //resultSet.next();
    }

    //проверка пользователя
    public void updateTask(String column, String value, int task_id) throws SQLException {
        String update = "UPDATE tasks SET " + column + "=? WHERE task_id=?";
        PreparedStatement updateStatement = connection.prepareStatement(update);
        //updateStatement.setString(1, column);
        updateStatement.setString(1, value);
        updateStatement.setInt(2, task_id);
        updateStatement.executeUpdate();
    }


}
