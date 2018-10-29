package lesson4_mychat.server;

import java.sql.*;

public class AuthService {
    public static Connection connection;
    public static Statement statement;

    public static void connect () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC"); // непонятно, что делает команда и что и кому она передает???
        connection= DriverManager.getConnection("jdbc:sqlite:myDB.db");
        statement=connection.createStatement();
    }


    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String password){
        String sql=String.format("SELECT nickname FROM USERS WHERE login='%s' AND password='%s'",login, password);
        try {
            ResultSet rs=statement.executeQuery(sql);
            if (rs.next()) return rs.getString(1); // можно вызвать вместо 1 "nickname"
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
