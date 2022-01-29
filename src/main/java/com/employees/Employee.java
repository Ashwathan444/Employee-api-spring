package com.employees;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String dep;
    private BigDecimal salary;

    // avoid this "No default constructor for entity"
    public Employee() {
    }

    public Employee(Long id, String name, int age, String dep, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dep = dep;
        this.salary = salary;
    }

    @Builder
    public Employee(String name, int age,String dep, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.dep = dep;
        this.salary = salary;
    }

}