package web;

import service.MainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    public static final MainService mainService = new MainService();

//    public LoginServlet() throws SQLException {
//    }


    public static MainService getMainService() {
        return mainService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

//            request.getRequestDispatcher("home.jsp").forward(request, response);
        // response.sendRedirect("/home");
        if (request.getParameter("signup") != null) {
            if (mainService.registerUser(login, password)) {
                response.sendRedirect("/home");
            } else {
                //response.sendRedirect("/");
            }
        }
        if (request.getParameter("signin") != null) {
            if (mainService.authorizeUser(login, password)) {
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/");
            }
//            }
        }
    }
}
