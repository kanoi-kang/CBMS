package it.xdu.xzk.citybusmanagersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.xdu.xzk.citybusmanagersystem.repository"})
public class CityBusManagerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityBusManagerSystemApplication.class, args);
    }

}
