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

    public TaskManager() throws SQLException {
    }

    public boolean createTask(Task task) throws SQLException {
        String create = "INSERT INTO tasks (title, status, description, user_id)  VALUES (?, ?, ?, ?)";
        PreparedStatement createStatement = connection.prepareStatement(create);
        createStatement.setString(1, task.name);
        createStatement.setString(2, String.valueOf(task.status.ordinal()));
        createStatement.setString(3, task.description);
        createStatement.setString(4, String.valueOf(task.owner_id));
        return createStatement.executeUpdate() > 0;
        //return false;
    }

    public boolean deleteTask(int task_id) throws SQLException {
        String delete = "DELETE FROM tasks WHERE task_id=?";
        PreparedStatement deleteStatement = connection.prepareStatement(delete);
        deleteStatement.setString(1, String.valueOf(task_id));
        //System.out.println();
        return deleteStatement.executeUpdate() > 0;
        //return false;
    }

    public ResultSet getTask(int task_id) throws SQLException {
        String find = "SELECT * FROM tasks WHERE task_id=?";
        PreparedStatement findStatement = connection.prepareStatement(find);
        findStatement.setString(1, String.valueOf(task_id));
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
        String find = "SELECT * FROM tasks WHERE user_id=?";
        PreparedStatement findStatement = connection.prepareStatement(find);
        findStatement.setString(1, String.valueOf(user_id));
        return findStatement.executeQuery();
        //resultSet.next();
    }


}
