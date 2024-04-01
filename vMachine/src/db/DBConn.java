package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static Connection dbConn;

    public static Connection getConnection() {
        if (dbConn == null){
            try {
                String dbDriver = "com.mysql.cj.jdbc.Driver";
                String dbUrl = "jdbc:mysql://localhost:3306/machine";
                String dbUser = "user5";
                String dbPassword = "1111";
                Class.forName(dbDriver);
                dbConn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                //System.out.println("성공~~!");
                //연결확인이기 때문에 굳이 필요하지 않다
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dbConn;
    }
    public static void close(){
        if (dbConn != null){
            try {
                if (! dbConn.isClosed()){
                    dbConn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dbConn = null; //혹시라도 남아있을 연결정보 모두 비우기
        }
    }
}
// DB연결 코드를 짜면 디비 연결이 되는 지 메인에서 확인하기
// main에서 DBConn.getConnection();