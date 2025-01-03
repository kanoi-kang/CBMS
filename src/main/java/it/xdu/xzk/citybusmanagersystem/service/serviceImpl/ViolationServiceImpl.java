package it.xdu.xzk.citybusmanagersystem.service.serviceImpl;

import it.xdu.xzk.citybusmanagersystem.entity.SearchViolationByDriver;
import it.xdu.xzk.citybusmanagersystem.entity.Violation;
import it.xdu.xzk.citybusmanagersystem.service.ViolationService;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViolationServiceImpl implements ViolationService {

    private static final String INSERT_Violation_SQL = "INSERT INTO trafficviolation (Vno, Sno, Vdate, Location, CarId, Vtype) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_Sno_BY_Sname_SQL = "SELECT Sno FROM drivers WHERE Sname = ?";

    // 假设这个方法用于获取数据库连接
    private Connection getConnection() throws SQLException {
        // 返回数据库连接
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                "kanoi",
                "xzk123456"
        );
    }

    @Override
    public boolean saveViolation(Violation violation) {
        try (Connection conn = getConnection()) {
            // 开启事务
            conn.setAutoCommit(false);

            // 查询对应的Sno
            try (PreparedStatement selectStmt = conn.prepareStatement(SELECT_Sno_BY_Sname_SQL)) {
                selectStmt.setString(1, violation.getSname());
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (!rs.next()) {
                        // 如果没有找到对应的Sno，则抛出异常或返回false
                        throw new SQLException("No driver found with Sname: " + violation.getSname());
                    }
                    String sno = rs.getString("Sno");
                    violation.setSno(sno); // 设置Sno到Violation对象中
                }
            }

            // 插入trafficviolation表
            try (PreparedStatement insertStmt = conn.prepareStatement(INSERT_Violation_SQL)) {
                insertStmt.setString(1, violation.getVno());
                insertStmt.setString(2, violation.getSno()); // 使用查询到的Sno
                insertStmt.setDate(3, violation.getVdate());
                insertStmt.setString(4, violation.getLocation());
                insertStmt.setString(5, violation.getCarId());
                insertStmt.setString(6, violation.getVtype());
                int affectedRows = insertStmt.executeUpdate();
                conn.commit(); // 提交事务
                return affectedRows > 0;
            } catch (SQLException e) {
                conn.rollback(); // 回滚事务
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SearchViolationByDriver> getViolationsByCriteria(SearchViolationByDriver criteria) {
        List<SearchViolationByDriver> violations = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();

        // 构建查询语句，连接drivers、trafficviolation和violationtype表
        queryBuilder.append("SELECT d.Sname, v.*, vt.punish FROM drivers d ");
        queryBuilder.append("JOIN trafficviolation v ON d.Sno = v.Sno ");
        queryBuilder.append("JOIN violationtype vt ON v.Vtype = vt.Vtype "); // 获取punish信息

        // 添加条件
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // 添加司机名称条件
        if (criteria.getSname() != null && !criteria.getSname().isEmpty()) {
            conditions.add("d.Sname = ?");
            parameters.add(criteria.getSname());
        }

        // 添加起始时间条件
        if (criteria.getStartDate() != null) {
            conditions.add("v.Vdate >= ?");
            parameters.add(new java.sql.Date(criteria.getStartDate().getTime()));
        }

        // 添加终止时间条件
        if (criteria.getEndDate() != null) {
            conditions.add("v.Vdate <= ?");
            parameters.add(new java.sql.Date(criteria.getEndDate().getTime()));
        }

        // 如果有条件，则添加到查询中
        if (!conditions.isEmpty()) {
            queryBuilder.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {

            // 设置参数
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            // 执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SearchViolationByDriver violation = new SearchViolationByDriver();
                    violation.setSname(rs.getString("Sname")); // 司机名称
                    violation.setPunish(rs.getString("punish")); // 违章类型对应的惩罚
                    violation.setVno(rs.getString("Vno"));
                    violation.setVdate(rs.getString("Vdate")); // 这里可能需要转换为适当的日期格式
                    violation.setLocation(rs.getString("Location"));
                    violation.setCarId(rs.getString("CarId"));
                    violation.setVtype(rs.getString("Vtype"));
                    // 根据需要添加其他属性的设置
                    violations.add(violation);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return violations;
    }




    @Override
    public List<SearchViolationByDriver> countByCriteria(SearchViolationByDriver criteria) {
        List<SearchViolationByDriver> violations = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT d.Fname, v.Vtype, COUNT(*) AS violationCount ");
        queryBuilder.append("FROM trafficviolation v ");
        queryBuilder.append("JOIN drivers d ON v.Sno = d.Sno "); // 使用Sno作为外键关联司机表
        queryBuilder.append("WHERE d.Fname = ? "); // 假设车队名称是必须的条件

        // 如果需要根据特定时间段筛选，可以添加以下条件
        if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
            queryBuilder.append("AND v.Vdate BETWEEN ? AND ? ");
        }

        queryBuilder.append("GROUP BY d.Fname, v.Vtype "); // 根据车队名称和违章类型分组

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString())) {

            int parameterIndex = 1;
            pstmt.setString(parameterIndex++, criteria.getFname()); // 设置车队名称参数

            // 如果需要根据特定时间段筛选，设置开始和结束日期参数
            if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
                pstmt.setDate(parameterIndex++, new java.sql.Date(criteria.getStartDate().getTime()));
                pstmt.setDate(parameterIndex++, new java.sql.Date(criteria.getEndDate().getTime()));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SearchViolationByDriver violation = new SearchViolationByDriver();
                    violation.setFname(rs.getString("Fname"));
                    violation.setVtype(rs.getString("Vtype")); // 设置违章类型
                    violation.setTime(rs.getInt("violationCount")); // 设置违章次数
                    violations.add(violation);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return violations;
    }


}

