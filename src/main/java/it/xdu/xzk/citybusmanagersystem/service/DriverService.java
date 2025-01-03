package it.xdu.xzk.citybusmanagersystem.service;
import it.xdu.xzk.citybusmanagersystem.entity.Driver;

import java.util.List;


public interface DriverService {
    boolean saveDriver(Driver driver);

    List<Driver> getDriversByCriteria(Driver criteria);
}
