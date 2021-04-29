package com.jpworks.datajdbc.employee.function;

import com.jpworks.datajdbc.employee.vo.Employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
public class EmployeeFunction implements Function<Employee, Optional<Employee>> {

    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> apply(Employee employee) {
        log.info("This message is in the function");
        return employeeRepository.findById(employee.getId());
    }
}
