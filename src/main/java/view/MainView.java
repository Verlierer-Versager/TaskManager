package view;

import model.Status;
import model.Task;
import service.MainService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainView {
    public final MainService mainService = new MainService();
    public static final Scanner scanner = new Scanner(System.in);

    public MainView() throws SQLException {
    }
//попроавить кэйсы
    public void start() {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать!\n" +
                "Выберите действие:\n" +
                "1. Войти\n" +
                "2. Зарегестрироваться");
        String action = scanner.next();
        boolean isCorrect = false;
        do {
            switch (action) {
                case "1":
                    authorization();
                    isCorrect = true;
                    break;
                case "2":
                    registration();
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
                    System.out.println("Попробуйте снова!");
                    action = scanner.next();
                    break;
            }
        } while (!isCorrect);
        action();
    }

    public void action() {
        System.out.println("Выберите действие:\n" +
                "1. Создать новую задачу\n" +
                "2. Отобразить список текущих задач\n" +
                "3. Выйти из системы");
        String action = scanner.next();
        boolean isCorrect = false;
        while (!isCorrect) {
            switch (action) {
                case "1":
                    create();
                    isCorrect = true;
                    break;
                case "2":
                    showAll();
                    isCorrect = true;
                    break;
                case "3":
                    signOut();
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
                    System.out.println("Попробуйте снова!");
                    action = scanner.next();
                    break;
            }
        }
    }

    public void authorization() {
        System.out.print("Введите логин: ");
        String login = scanner.next();
        System.out.print("Введите пароль: ");
        String password = scanner.next();
        if (mainService.authorizeUser(login, password)) {
            System.out.println("Успешный вход!");
        } else {
            System.out.println("Ошибка авторизации\n" +
                    "Для повторной попытки введите 1");
            if ("1".equals(scanner.next())) {
                authorization();
            } else {
                start();
            }
        }
    }

    public void registration() {
        System.out.println("Регистрация!\n" +
                "Длина логина и пароля должна быть 5-10 символов");
        System.out.print("Введите логин: ");
        String login = scanner.next();
        System.out.print("Введите пароль: ");
        String password = scanner.next();
        if (mainService.registerUser(login, password)) {
            System.out.println("Успешная регистрация!");
        } else {
            System.out.println("Ошибка регистрации\n" +
                    "Для повторной попытки введите 1");
            if ("1".equals(scanner.next())) {
                authorization();
            } else {
                start();
            }
        }
    }

    public void create() {
        System.out.println("Введите название задачи");
        String name = scanner.next();
        System.out.println("Введите описание задачи");
        String description = scanner.next();
        mainService.createTask(name, description);
        action();
    }

    public void showAll() {
        System.out.println("Список задач");
        List<Task> list = mainService.getCurrentUserTasks();
        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.name);
            i++;
        }
        System.out.println("Для перехода к конкретной задаче введите 1\n" +
                "Для выхода в меню введите 2");

        String action = scanner.next();
        boolean isCorrect = false;
        while (!isCorrect) {
            switch (action) {
                case "1":
                    System.out.print("Введите номер задачи: ");
                    selectTask(scanner.nextInt() - 1);
                    isCorrect = true;
                    break;
                case "2":
                    action();
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
                    System.out.println("Попробуйте снова!");
                    action = scanner.next();
                    break;
            }
        }
        showAll();
    }

    public void signOut() {
        mainService.signOut();
        start();
    }

    public void selectTask(int index) {
        Task task = mainService.getCurrentUserTasks().get(index);
        System.out.println("Название: " +  task.name + "\n" +
                "Описание: " + task.description + "\n" +
                "Статус: " + task.status);
        //изменит
        //удалить
        System.out.println("Выберите действие:\n" +
                "1. Изменить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Вернуться к списку");
        String action = scanner.next();
        boolean isCorrect = false;
        while (!isCorrect) {
            switch (action) {
                case "1":
                    //System.out.print("Введите номер задачи: ");
                    update(task);
                    isCorrect = true;
                    break;
                case "2":
                    delete(task);
                    isCorrect = true;
                    break;
                case "3":
                    showAll();
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
                    System.out.println("Попробуйте снова!");
                    action = scanner.next();
                    break;
            }
        }
    }

    public void update(Task task) {
        System.out.println("Выберите поле для изменения:\n" +
                "1. Название\n" +
                "2. Описание\n" +
                "3. Статус");
        String action = scanner.next();
        boolean isCorrect = false;
        while (!isCorrect) {
            switch (action) {
                case "1":
                    System.out.print("Введите новое название: ");
                    String name = scanner.next();
                    mainService.updateTask("title", name, task.id);
                    isCorrect = true;
                    break;
                case "2":
                    System.out.print("Введите новое описание: ");
                    String description = scanner.next();
                    mainService.updateTask("description", description, task.id);
                    isCorrect = true;
                    break;
                case "3":
                    status(task);
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
                    System.out.println("Попробуйте снова!");
                    action = scanner.next();
                    break;
            }
        }
    }

    public void delete(Task task) {
        mainService.deleteTask(task.id);
    }

    public void status(Task task) {
        System.out.println("Выберите статус:\n" +
                "1. Приостановлен\n" +
                "2. В процессе\n" +
                "3. Завершен\n" +
                "4. Ожидает выполнения");
        String action = scanner.next();
        boolean isCorrect = false;
        while (!isCorrect) {
            switch (action) {
                case "1":
                    mainService.updateTask("status", "HIATUS", task.id);
                    isCorrect = true;
                    break;
                case "2":
                    mainService.updateTask("status", "IN_PROCESS", task.id);
                    isCorrect = true;
                    break;
                case "3":
                    mainService.updateTask("status", "COMPLETED", task.id);
                    isCorrect = true;
                    break;
                case "4":
                    mainService.updateTask("status", "WAITING_FOR_EXECUTION", task.id);
                    isCorrect = true;
                    break;
                default:
                    System.out.println("Ошибка ввода!");
                    System.out.println("Попробуйте снова!");
                    action = scanner.next();
                    break;
            }
        }
    }
}
