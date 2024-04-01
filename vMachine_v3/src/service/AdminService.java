package service;

import db.DBConn;
import dto.ProductDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminService {
    Scanner sc = new Scanner(System.in);

    public static int insertMenu(ProductDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "insert into product(product_name, price, stock,on_off)" +
                    "values (?,?,?,?)";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, dto.product_name());
            psmt.setInt(2, dto.price());
            psmt.setInt(3, dto.stock());
            psmt.setInt(4,dto.on_off());
            psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static int deleteMenu(int menuId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "update product set on_off = ? where id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, 0);
            psmt.setInt(2,menuId);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static int updateMenu(int id, String changeName, int price, int stock,int on_off) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "UPDATE product SET product_name = ?, price = ?, stock = ?, on_off = ? WHERE  id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, changeName);
            psmt.setInt(2, price);
            psmt.setInt(3, stock);
            psmt.setInt(4,on_off);
            psmt.setInt(5, id);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static int updateStock(int menuId, int stock) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try{
            String query = "UPDATE product SET stock = ? WHERE id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, stock);
            psmt.setInt(2, menuId);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }



    public static int updateUser(int id, int password, String name, String tel, int money, int on_off){
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "UPDATE employee SET password = ?, name = ?, tel = ?, charge_money = ?, on_off = ? WHERE id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, password);
            psmt.setString(2, name);
            psmt.setString(3, tel);
            psmt.setInt(4, money);
            psmt.setInt(5,on_off);
            psmt.setInt(6, id);
            result = psmt.executeUpdate();
            psmt.close();;
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int deleteUser(int id){
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "update employee set on_off = ? where id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,0);
            psmt.setInt(2, id);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }



//    public static void listUser(){
//        Connection conn = DBConn.getConnection();
//        PreparedStatement psmt = null;
//        try {
//            String query = "SELECT * FROM EMPLOYEE";
//            psmt = conn.prepareStatement(query);
//            psmt.executeUpdate();
//            psmt.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}