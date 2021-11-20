package view;

import service.MainService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {
    public final MainService mainService = new MainService();
    public static final Scanner scanner = new Scanner(System.in);

    public MainView() throws SQLException {
    }

    public void start() {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать!\n" +
                "Выберите действие:\n" +
                "1. Войти\n" +
                "2. Зарегестрироваться");
        String action = scanner.next();
        boolean isCorrect = false;
        while (!isCorrect) {
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
                    break;
            }
            System.out.println("Попробуйте снова!");
            action = scanner.next();
        }
        System.out.println("Выберите действие:\n" +
                "1. Создать новую задачу\n" +
                "2. Отобразить список текущих задач\n" +
                "3. Выйти из системы");
        action = scanner.next();
        isCorrect = false;
        while (!isCorrect) {
            switch (action){
                case "1":
                    create();
                    isCorrect =true;
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
                    break;
            }
            System.out.println("Попробуйте снова!");
            action = scanner.next();
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
                "Длина логина и пороля должна быть 5-10 символов");
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

    }

    public void showAll() {

    }

    public void signOut() {
        mainService.signOut();
        start();
    }
}
