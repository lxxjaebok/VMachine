package service;

import db.DBConn;
import dto.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static int setCoin(int id, int money) {
        // 회원 잔돈 반환 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        if (money != 0) {
            try {
                String query = "update employee set charge_money = ? where id = ?";
                psmt = conn.prepareStatement(query);
                psmt.setInt(1, 0);
                psmt.setInt(2, id);
                result = psmt.executeUpdate();
                psmt.close();
                return result;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return result;
            }
        } else {
            System.out.println("잔액이 없습니다.");
        }
        return result;
    }
    public int insertData(EmployeeDto dto){
        // 회원 회원가입 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "insert into employee(password, name, tel, charge_money,on_off)" +
                    "values (?,?,?,?,?)";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,dto.password());
            psmt.setString(2,dto.name());
            psmt.setString(3, dto.tel());
            psmt.setInt(4, dto.charge_money());
            psmt.setInt(5,1);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public EmployeeDto match(int id, String password) {
        // 회원 로그인 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        EmployeeDto dto = null;
        try {
            String query = "select * from employee where id = ? and password=?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,id);
            psmt.setString(2,password);
            rs = psmt.executeQuery();

            while (rs.next()) {
                dto = EmployeeDto.allOf(
                        rs.getInt("id"),
                        rs.getInt("password"),
                        rs.getString("name"),
                        rs.getString("tel"),
                        rs.getInt("charge_money"),
                        rs.getInt("on_off")
                );
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }
    public EmployeeDto selectOne(int id) {
        // 회원 ID를 이용한 찾기 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        // 받을 값, 객체
        EmployeeDto dto = null;
        // 받을 값
        try {
            String query = "select * from employee where id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, id);
            //?에 들어갈 ID
            rs = psmt.executeQuery();
            //받을 게 있으면 executeQuery();
            while (rs.next()) {
                dto = EmployeeDto.allOf(
                        rs.getInt("id"),
                        rs.getInt("password"),
                        rs.getString("name"),
                        rs.getString("tel"),
                        rs.getInt("charge_money"),
                        rs.getInt("on_off")
                );
            }
            psmt.close();
            rs.close();
            // ResultSet은 데이터베이스에서 검색한 결과를 담고 있으며, 그 결과를 순회하고 처리하는 데 사용됩니다.
            //따라서 데이터베이스 작업을 마치고 데이터베이스 리소스를 해제하기 위해 ResultSet을 닫는 것이 중요


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }
    
    public List<ProductDto> allList() {
        // 제품 리스트에 담에 전체 출력 service
        List<ProductDto>product = new ArrayList<>();
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs =null;
        try {
            String query = "select * from product";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()){
                product.add(ProductDto.allOf(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getInt("on_off")
                ));
            }
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }


    public ProductDto selectvo(int id) {
        // 제품 ID를 이용한 찾기 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ProductDto p = null;
        try {
            String query = "select * from product where id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,id);
            rs = psmt.executeQuery();
            while (rs.next()){
                p = ProductDto.Of(
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getInt("on_off")
                );
            }
            psmt.close();
            return p;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public int setStock(int voId, int stock) {
        // 제품 재고 수정 서비스
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
            try {
                String query = "update product set stock = ? where id = ?";
                psmt = conn.prepareStatement(query);
                psmt.setInt(1, stock);
                psmt.setInt(2, voId);
                result = psmt.executeUpdate();
                psmt.close();
                return result;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return result;
    }

    public int eId(SalesDto dto) {
        // 판매 table 값 넣는 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "insert into sales (employee_id,product_id, sell_price)" +
                    " values (?,?,?)";
            psmt=conn.prepareStatement(query);
            psmt.setInt(1,dto.employee_id());
            psmt.setInt(2,dto.product_id());
            psmt.setInt(3,dto.sell_price());
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int setmoney(int nowId, int money) {
        // 회원 금액 수정 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "update employee set charge_money = ? where id =?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,money);
            psmt.setInt(2,nowId);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<EmployeeDto> selectAll() {
        // 회원 리스트에 담에 전체 출력 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<EmployeeDto> dto = new ArrayList<>();
        try {
            String query = "select * from employee";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {
                dto.add(EmployeeDto.allOf(
                        rs.getInt("id"),
                        rs.getInt("password"),
                        rs.getString("name"),
                        rs.getString("tel"),
                        rs.getInt("charge_money"),
                        rs.getInt("on_off")
                ));
            } psmt.close();
            rs.close();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        } return dto;
    }
    public List<SalesADto> aChoice() {
        // 판매관리 제품별 a service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<SalesADto> dto = new ArrayList<>();
        try {
            String query = "SELECT p.product_name AS \"제품명\", COUNT(s.product_id) AS \"판매수량\",\n" +
                    "SUM(s.sell_price) as \"판매금액\"\n" +
                    "FROM product p\n" +
                    "INNER JOIN sales s ON p.id = s.product_id\n" +
                    "GROUP BY p.product_name";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()){
                dto.add(SalesADto.all(
                        rs.getString("제품명"),
                        rs.getInt("판매수량"),
                        rs.getInt("판매금액")
                ));
            }psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return dto;
    }



    public List<SalesBDto> salesUser(){
        // 판매관리 회원별 b service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<SalesBDto> dtos = new ArrayList<>();
        try {
            String query = "SELECT e.id, e.name, sum(s.sell_price), sum(e.charge_money)\n" +
                    "FROM sales s\n" +
                    "INNER JOIN employee e ON s.employee_id = e.id\n" +
                    "INNER JOIN product p ON s.product_id = p.id\n" +
                    "group by e.id, e.name";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next())
            {
                dtos.add(SalesBDto.all(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("sum(s.sell_price)"),
                        rs.getInt("sum(e.charge_money)")
                ));
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dtos;
    }

    public EmployeeDto selectemp(int password) {
        // 회원가입 후 아이디 찾기 service
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        EmployeeDto t = null;
        ResultSet rs = null;
        try {
            String query = "select * from employee where password = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,password);
            rs = psmt.executeQuery();
            while (rs.next()){
                t = EmployeeDto.allOf(
                        rs.getInt("id"),
                        rs.getInt("password"),
                        rs.getString("name"),
                        rs.getString("tel"),
                        rs.getInt("charge_money"),
                        rs.getInt("on_off")
                );
            }
            psmt.close();
            return t;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }
}
