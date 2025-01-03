package it.xdu.xzk.citybusmanagersystem.controller;

import it.xdu.xzk.citybusmanagersystem.entity.SearchViolationByDriver;
import it.xdu.xzk.citybusmanagersystem.entity.Violation;
import it.xdu.xzk.citybusmanagersystem.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ViolationController {

    @Autowired
    private ViolationService ViolationService;


    @PostMapping("/insert/violate")
    public ResponseEntity<?> submitViolation(@RequestBody Violation Violation) {
//        System.out.println(Violation);
//        return ResponseEntity.ok("Sage received: " + Violation.getSage());
//         尝试保存Violation对象到数据库
        boolean isSaved = ViolationService.saveViolation(Violation);

        // 根据保存结果返回相应的响应
        if (isSaved) {
            // 如果保存成功，返回状态码200和成功消息
            return ResponseEntity.ok("Violation data inserted successfully.");
        } else {
            // 如果保存失败，返回状态码500和错误消息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert Violation data.");
        }
    }
    @PostMapping("/search/violate")
    public ResponseEntity<?> searchViolations(@RequestBody SearchViolationByDriver criteria) {

        // 使用服务层的方法来获取查询结果
        List<SearchViolationByDriver> Violations = ViolationService.getViolationsByCriteria(criteria);

        // 如果查询结果不为空，则返回查询到的驾驶员列表
        if (!Violations.isEmpty()) {
            return ResponseEntity.ok(Violations);
        } else {
            // 如果查询结果为空，则返回状态码404和错误消息
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Violations found with the given criteria.");
        }
    }
    @PostMapping("/count/violate")
    public ResponseEntity<?> countViolations(@RequestBody SearchViolationByDriver criteria) {

        // 使用服务层的方法来获取查询结果
        List<SearchViolationByDriver> Violations = ViolationService.countByCriteria(criteria);

        // 如果查询结果不为空，则返回查询到的驾驶员列表
        if (!Violations.isEmpty()) {
            return ResponseEntity.ok(Violations);
        } else {
            // 如果查询结果为空，则返回状态码404和错误消息
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Violations found with the given criteria.");
        }
    }
}
