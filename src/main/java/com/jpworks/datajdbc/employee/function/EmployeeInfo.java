package com.jpworks.datajdbc.employee.function;

import com.jpworks.datajdbc.employee.vo.Employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
@SuppressWarnings("unused")
public class EmployeeInfo implements Function<Employee, Optional<Employee>> {

    @Autowired
    BuildProperties buildProperties;

    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Employee> apply(Employee employee) {
        log.info("Employee query received in Spring Function {}", employee);
        Optional<Employee> result = employeeRepository.findById(employee.getId());
        String apiVersion = buildProperties.getGroup()
                        + ":" + buildProperties.getArtifact()
                        + ":" +  buildProperties.getName()
                        + ":" +  buildProperties.getVersion()
                        + ", " + buildProperties.getTime();
        result.get().setApiVersion(apiVersion);
        return result;
    }
}
