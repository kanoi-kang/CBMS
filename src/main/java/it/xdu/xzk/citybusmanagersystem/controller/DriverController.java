package it.xdu.xzk.citybusmanagersystem.controller;

import it.xdu.xzk.citybusmanagersystem.entity.Driver;
import it.xdu.xzk.citybusmanagersystem.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @PostMapping("/insert/drivers")
    public ResponseEntity<?> submitDriver(@RequestBody Driver driver) {
//        System.out.println(driver);
//        return ResponseEntity.ok("Sage received: " + driver.getSage());
//         尝试保存Driver对象到数据库
        boolean isSaved = driverService.saveDriver(driver);

        // 根据保存结果返回相应的响应
        if (isSaved) {
            // 如果保存成功，返回状态码200和成功消息
            return ResponseEntity.ok("Driver data inserted successfully.");
        } else {
            // 如果保存失败，返回状态码500和错误消息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert driver data.");
        }
    }
    @PostMapping("/search/drivers")
    public ResponseEntity<?> searchDrivers(@RequestBody Driver criteria) {
        // 由于Fname是必须的，我们可以在这里进行校验
        if (criteria.getFname() == null || criteria.getFname().isEmpty()) {
            return ResponseEntity.badRequest().body("车队名称为必填项!");
        }
        // 使用服务层的方法来获取查询结果
        List<Driver> drivers = driverService.getDriversByCriteria(criteria);

        // 如果查询结果不为空，则返回查询到的驾驶员列表
        if (!drivers.isEmpty()) {
            return ResponseEntity.ok(drivers);
        } else {
            // 如果查询结果为空，则返回状态码404和错误消息
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No drivers found with the given criteria.");
        }
    }
}
