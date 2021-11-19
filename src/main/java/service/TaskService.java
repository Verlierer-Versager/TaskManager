package service;

import manager.TaskManager;
import model.Task;
import model.TaskList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    //private List<Task> allTasks = new ArrayList<>();
    public final TaskList taskList;
    public final TaskManager taskManager = new TaskManager();

    public TaskService(TaskList taskList) throws SQLException {
        this.taskList = taskList;
    }

    public Task recreate(Task task, int id) {
        return new Task(id, task.name, task.status, task.description, task.owner_id);
    }
}
