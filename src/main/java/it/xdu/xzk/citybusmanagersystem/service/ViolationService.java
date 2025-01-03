package it.xdu.xzk.citybusmanagersystem.service;
import it.xdu.xzk.citybusmanagersystem.entity.SearchViolationByDriver;
import it.xdu.xzk.citybusmanagersystem.entity.Violation;

import java.util.List;


public interface ViolationService {
    boolean saveViolation(Violation Violation);

    List<SearchViolationByDriver> getViolationsByCriteria(SearchViolationByDriver criteria);
    List<SearchViolationByDriver> countByCriteria(SearchViolationByDriver criteria);

}