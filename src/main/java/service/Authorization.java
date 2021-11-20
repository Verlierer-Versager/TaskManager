package service;

import manager.UserManager;

import java.sql.SQLException;

public class Authorization {

    public final UserManager userManager = new UserManager();

    public Authorization() throws SQLException {
    }

    public int authorizeUser(String login, String password) throws SQLException {
        return userManager.authorizeUser(login, password);
    }

    //уточнить как быть с ошибками скл
    public boolean registerUser(String login, String password) throws SQLException {
        if (login.length() >= 5 && password.length() >= 5 && login.length() <= 10 && password.length() <= 10) {
            return userManager.registerUser(login, password); //изменить на хэш
        }
        return false;
    }


//    public boolean login(String login, String password) {
//        //запрос к бд
//        return false;
//    }
//
//    public int registration(String login, String password) {
//        //добавление нового пользователя в бд
//        //возвращает user_id
//        return -1;
//    }
}
