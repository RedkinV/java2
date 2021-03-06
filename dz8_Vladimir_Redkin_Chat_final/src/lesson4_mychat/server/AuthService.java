package lesson4_mychat.server;

import java.sql.*;
import java.util.ArrayList;

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
        String sql=String.format("SELECT nickname FROM USERS WHERE login='%s' AND password='%s'",login, password.hashCode());
        try {
            ResultSet rs=statement.executeQuery(sql);
            if (rs.next()) return rs.getString(1); // можно вызвать вместо 1 "nickname"
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void addUser (String login, String password, String nick) throws SQLException {
        String sql=String.format("INSERT INTO USERS (login, password, nickname) VALUES ('%s','%s','%s')",login, password.hashCode(),nick);
        statement.execute(sql);
    }

    public static ArrayList<String> getBlacklistByNick(String nick){
        ArrayList<String> arrayList=null;
        String sql=String.format("SELECT t1.nickname, t2.blacklist FROM USERS t1 JOIN BLACKLIST t2 ON t1.nickname=t2.nickname WHERE t1.nickname='%s'",nick);
        try {
            ResultSet rs=statement.executeQuery(sql);
            arrayList=new ArrayList<>();
           while (rs.next()) {
               arrayList.add(rs.getString(2)); // можно вызвать вместо 2 "blacklist"
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    public static void insertInBlacklist(String fromNick, String blNick ){
        String sql=String.format("INSERT INTO BLACKLIST (nickname, blacklist) VALUES ('%s','%s')",fromNick,blNick);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
