package com.jpworks.datajdbc.employee.function;

import com.jpworks.datajdbc.employee.vo.Employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
@SuppressWarnings("unused")
public class EmployeeTest implements Function<Employee, Optional<Employee>> {

    public Optional<Employee> apply(Employee employee) {
        Employee response = new Employee();
        response.setFirstName("John");
        response.setLastName("Test");
        return Optional.of(employee);
    }
}
