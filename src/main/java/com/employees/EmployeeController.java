package com.employees;

import com.employees.error.EmployeeNotFoundException;
import com.employees.error.EmployeeUnSupportedFieldPatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(com.employees.EmployeeController.class);

    @Autowired
    private EmployeeRepository repository;

    // Find
    @GetMapping("/employees")
    List<Employee> findAll() {
        logger.info("We are in findAll employees method");
        return repository.findAll();
    }

    // Save
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    Employee newEmployee(@RequestBody Employee newEmployee) {
        logger.info("We are in create newEmployee method");
        return repository.save(newEmployee);
    }

    // Find
    @GetMapping("/employees/{id}")
    Employee findOne(@PathVariable Long id) {
        logger.info("We are in findOne employee method");
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //Find with constraint
    @GetMapping("/employees/greaterthan/{age}")
    List<Employee> findByAgeGreaterThan(@PathVariable int age) {
        logger.info("We are in findByAge employees method");
        return repository.findByAgeGreaterThan(age);
    }

    // Save or update
    @PutMapping("/employees/{id}")
    Employee saveOrUpdate(@RequestBody Employee newEmployee, @PathVariable Long id) {

        logger.info("We are in Update employees method");
        return repository.findById(id)
                .map(x -> {
                    x.setName(newEmployee.getName());
                    x.setDep(newEmployee.getDep());
                    x.setSalary(newEmployee.getSalary());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    // update department only
    @PatchMapping("/employees/{id}")
    Employee patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String dep = update.get("dep");
                    if (!StringUtils.isEmpty(dep)) {
                        x.setDep(dep);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new EmployeeUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new EmployeeNotFoundException(id);
                });

    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        logger.info("We are in deleteEmployees method");
        repository.deleteById(id);
    }

}
