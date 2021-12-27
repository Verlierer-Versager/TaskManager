package service;

import lombok.Getter;
import model.Status;
import model.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainService {
    public final TaskService taskService = new TaskService();
    public final Authorization authorization = new Authorization();
    @Getter
    private int currentUserId = -1; //подумать
    @Getter
    private List<Task> currentUserTasks;

    //авторизация юзера --
    // регистрация юзера --
    //получение для него списка тасков --
    //создание таска --
    //обновление списка тасков --
    //удаление --
    //смена статуса --
    //изменение таска --
    //смена пользователя --

//    public MainService() throws SQLException {
//    }

    public boolean authorizeUser(String login, String password) {
        try {
            int id = authorization.authorizeUser(login, password);
            if (id != -1) {
                currentUserId = id;
                getTasks();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(String login, String password) {
        try {
            currentUserId =  authorization.registerUser(login, password);
            //return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUserId > -1;
        //return false;
    }

    public void signOut() {
        currentUserId = -1;
        currentUserTasks = new ArrayList<>();
    }

    public void createTask(String name, String description) {
        try {
            currentUserTasks.add(taskService.createTask(name, Status.WAITING_FOR_EXECUTION, description, currentUserId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //getCurrentUserTasks();
    }

    private void getTasks() {
        try {
            currentUserTasks = taskService.taskList(currentUserId); //где-то должна быть проверка id
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(String column, String value, int task_id) {
        //тут должна быть проверка id владельца таска и id того, кто собирается изменить
        try {
            taskService.updateTask(column, value, task_id);
            getTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int task_id) {
        //тут должна быть проверка id владельца таска и id того, кто собирается удалить
        try {
            taskService.deleteTask(task_id);
            getTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public String getCurrentUserName() {
//
//    }

}
