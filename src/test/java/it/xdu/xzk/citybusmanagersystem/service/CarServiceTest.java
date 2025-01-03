package it.xdu.xzk.citybusmanagersystem.service;

import it.xdu.xzk.citybusmanagersystem.entity.Car;
import it.xdu.xzk.citybusmanagersystem.service.serviceImpl.CarServiceImpl;


public class CarServiceTest {
    public static void main(String[] args) {
        // 创建DriverService实例
        CarServiceImpl carService = new CarServiceImpl();

        // 创建一个Driver对象，使用前端返回的信息进行填充
        Car car = new Car();
        car.setCarId("123");
        car.setRno(123);
        car.setSeat(123);
        car.setCtype("123");
        car.setCage(123); // 假设前端传回的是整数123
        // 调用saveDriver方法
        boolean isSaved = carService.saveCar(car);
        // 检查结果
        if (isSaved) {
            System.out.println("Driver saved successfully.");
        } else {
            System.out.println("Failed to save driver.");
        }
    }
}
