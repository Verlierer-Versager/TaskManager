package service;

import manager.TaskManager;
import model.Status;
import model.Task;
import model.TaskList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    //private List<Task> allTasks = new ArrayList<>();
    //public final TaskList taskList;
    public final TaskManager taskManager = new TaskManager();

//    public TaskService(TaskList taskList) throws SQLException {
//        this.taskList = taskList;
//    }

//    public TaskService() throws SQLException {
//
//    }

    public Task recreate(Task task, int id) {
        return new Task(id, task.name, task.status, task.description, task.owner_id);
    }

    public Task createTask(String name, Status status, String description, int owner_id) throws SQLException {
        Task task = new Task(name, status, description, owner_id);
        int id = taskManager.createTask(task);
        return recreate(task, id);
    }

    public List<Task> taskList(int user_id) throws SQLException {
        List<Task> list = new ArrayList<>();
        ResultSet resultSet = taskManager.getAllTasks(user_id);
        while (resultSet.next()) {
            list.add(new Task(resultSet.getInt("task_id"),
                resultSet.getString("title"),
                Enum.valueOf(Status.class, resultSet.getString("status")),
                resultSet.getString("description"),
                resultSet.getInt("user_id")));
        }
        return list;
    }

    public void updateTask(String column, String value, int task_id) throws SQLException {
        taskManager.updateTask(column, value, task_id);
    }

    public void deleteTask(int task_id) throws SQLException {
        taskManager.deleteTask(task_id);
    }
}
