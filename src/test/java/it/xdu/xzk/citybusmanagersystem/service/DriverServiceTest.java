package it.xdu.xzk.citybusmanagersystem.service;

import it.xdu.xzk.citybusmanagersystem.entity.Driver;
import it.xdu.xzk.citybusmanagersystem.service.serviceImpl.DriverServiceImpl;

public class DriverServiceTest {
    public static void main(String[] args) {
        // 创建DriverService实例
        DriverServiceImpl driverService = new DriverServiceImpl();

        // 创建一个Driver对象，使用前端返回的信息进行填充
        Driver driver = new Driver();
        driver.setSno("123");
        driver.setRno(123);
        driver.setSname("123");
        driver.setGender("男");
        driver.setStel("123");
        driver.setSage(123); // 假设前端传回的是整数123
        driver.setFname("123");

        // 调用saveDriver方法
        boolean isSaved = driverService.saveDriver(driver);

        // 检查结果
        if (isSaved) {
            System.out.println("Driver saved successfully.");
        } else {
            System.out.println("Failed to save driver.");
        }
    }
}
