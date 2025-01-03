package it.xdu.xzk.citybusmanagersystem.controller;

import it.xdu.xzk.citybusmanagersystem.entity.Car;
import it.xdu.xzk.citybusmanagersystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;
    @PostMapping("/insert/cars")
    public ResponseEntity<?> submitCar(@RequestBody Car car) {
//        System.out.println(car);
//        return ResponseEntity.ok("Sage received: " + car.getCage());
//         尝试保存Driver对象到数据库
        boolean isSaved = carService.saveCar(car);
        // 根据保存结果返回相应的响应
        if (isSaved) {
            // 如果保存成功，返回状态码200和成功消息
            return ResponseEntity.ok("Driver data inserted successfully.");
        } else {
            // 如果保存失败，返回状态码500和错误消息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert driver data.");
        }
    }
}
