package it.xdu.xzk.citybusmanagersystem.service.serviceImpl;

import it.xdu.xzk.citybusmanagersystem.entity.Car;
import it.xdu.xzk.citybusmanagersystem.service.CarService;
import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class CarServiceImpl implements CarService {

    private static final String INSERT_DRIVER_SQL = "INSERT INTO car (CarId,Rno,Seat,Ctype,Cage) VALUES (?, ?, ?,?,?)";

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
    public boolean saveCar(Car car) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_DRIVER_SQL)) {
            pstmt.setString(1, car.getCarId());
            pstmt.setInt(2, car.getRno());
            pstmt.setInt(3, car.getSeat());
            pstmt.setString(4, car.getCtype());
            pstmt.setInt(5, car.getCage());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
//            return false;
        }
    }

}
