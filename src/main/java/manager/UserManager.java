package manager;

import utils.DatabaseConnector;

import java.sql.*;

public class UserManager {
    public final Connection connection = DatabaseConnector.getConnection();


    public UserManager() throws SQLException {
    }

    public int authorizeUser(String login, String password) throws SQLException {
        String authorize = "SELECT * FROM users WHERE login=? AND password=?";
        PreparedStatement authorizeStatement = connection.prepareStatement(authorize);

        authorizeStatement.setString(1, login);
        authorizeStatement.setString(2, password);
        ResultSet resultSet = authorizeStatement.executeQuery();
        if (resultSet.next()) {
            //resultSet.next();
            return resultSet.getInt("user_id");
        }
        return -1;
    }

    public int registerUser(String login, String password) throws SQLException {
        String register = "INSERT INTO users (login, password)  VALUES (?, ?) RETURNING user_id";
        PreparedStatement registerStatement = connection.prepareStatement(register);
        registerStatement.setString(1, login);
        registerStatement.setString(2, password);
        //System.out.println(registerStatement);
        if(!findByLogin(login)) {
            ResultSet resultSet = registerStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
            //return true;
        }
        return -1;
    }

    public boolean findByLogin(String login) throws SQLException {
        String find = "SELECT * FROM users WHERE login=?";
        PreparedStatement findStatement = connection.prepareStatement(find);
        findStatement.setString(1, login);
        ResultSet resultSet = findStatement.executeQuery();
        return resultSet.next();
    }

//    public function findUserByLogin($login)
//    {
//        return $this->db->executeQueryWithResult(
//                "SELECT * FROM users WHERE login='{$login}'")->fetchRow();
//    }

//    public int getUserById(int id) {
//
//    }
//    public final static List<Task> tasks =
//    private Connection connection = DatabaseConnector.getConnection();
//
//    public UserManager() throws SQLException {
//    }


//
//    public UserManager() throws SQLException {
//    }

//    try (
//    Connection connection = DatabaseConnector.getConnection();
//    Statement statement = connection.createStatement())
//    {
//        ResultSet rs = statement.executeQuery("SELECT * FROM table_name");
//        while (rs.next()) {
//            /*
//
//             */
//        }
//        rs.close();
//    } catch (
//    SQLException e)
//    {
//        System.out.println(e.getMessage());
//    }
}
