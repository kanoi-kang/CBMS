package it.xdu.xzk.citybusmanagersystem.service.serviceImpl;

import it.xdu.xzk.citybusmanagersystem.entity.Driver;
import it.xdu.xzk.citybusmanagersystem.service.DriverService;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private static final String INSERT_DRIVER_SQL = "INSERT INTO drivers (Sno,Rno,Sname,Gender,Stel,Sage,Fname) VALUES (?, ?, ?,?,?,?,?)";

    // 假设这个方法用于获取数据库连接
    private Connection getConnection() throws SQLException {
        // 这里应该返回数据库连接，你可以使用JDBC或连接池（如HikariCP）来管理连接
        // 例如: return DriverManager.getConnection(url, username, password);
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                "kanoi",
                "xzk123456"
        );
    }

    @Override
    public boolean saveDriver(Driver driver) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_DRIVER_SQL)) {
            pstmt.setString(1, driver.getSno());
            pstmt.setInt(2, driver.getRno());
            pstmt.setString(3, driver.getSname());
            pstmt.setString(4, driver.getGender());
            pstmt.setString(5, driver.getStel());
            pstmt.setInt(6, driver.getSage());
            pstmt.setString(7, driver.getFname());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Driver> getDriversByCriteria(Driver criteria) {
        List<Driver> drivers = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM drivers");
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (criteria.getFname() != null && !criteria.getFname().isEmpty()) {
            conditions.add("Fname = ?");
            parameters.add(criteria.getFname());
        }
        // ... 添加其他条件 ...
        if (criteria.getSno() != null && !criteria.getSno().isEmpty()) {
            conditions.add("Sno = ?");
            parameters.add(criteria.getSno());
        }
        if (criteria.getRno() != null) {
            conditions.add("Rno = ?");
            parameters.add(criteria.getRno());
        }
        if (criteria.getSname() != null && !criteria.getSname().isEmpty()) {
            conditions.add("Sname = ?");
            parameters.add(criteria.getSname());
        }
        if (criteria.getGender() != null && !criteria.getGender().isEmpty()) {
            conditions.add("Gender = ?");
            parameters.add(criteria.getGender());
        }
        if (criteria.getStel() != null && !criteria.getStel().isEmpty()) {
            conditions.add("Stel = ?");
            parameters.add(criteria.getStel());
        }
        if (criteria.getSage() != null) {
            conditions.add("Sage = ?");
            parameters.add(criteria.getSage());
        }
        //
        if (!conditions.isEmpty()) {
            queryBuilder.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Driver driver = new Driver();
                    driver.setSno(rs.getString("Sno"));
                    driver.setRno(rs.getInt("Rno"));
                    driver.setSname(rs.getString("Sname"));
                    driver.setGender(rs.getString("Gender"));
                    driver.setStel(rs.getString("Stel"));
                    driver.setSage(rs.getInt("Sage"));
                    driver.setFname(rs.getString("Fname"));
                    drivers.add(driver);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return drivers;
    }

}
