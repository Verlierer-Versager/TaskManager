package service;

import model.Task;
import model.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    //private List<Task> allTasks = new ArrayList<>();
    public final TaskList taskList;

    public TaskService(TaskList taskList) {
        this.taskList = taskList;
    }
}
