package com;

import com.employees.Employee;
import com.employees.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class StartEmployeeApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartEmployeeApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        System.out.println("Am i here");
        return args -> {
            repository.save(new Employee("Sam",23, "IT", new BigDecimal("15000")));
            repository.save(new Employee("Arjun",27, "Marketing", new BigDecimal("10000")));

        };
    }
}