package com.jpworks.datajdbc.employee;

import com.jpworks.datajdbc.employee.function.EmployeeInfo;
import com.jpworks.datajdbc.employee.vo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AzureTestBackup {

    @Test
    void start(){
        Employee queryEmployee = Employee.builder().id(1L).build();

        FunctionInvoker<Employee, Employee> handler = new FunctionInvoker<>(EmployeeInfo.class);
        Employee result = handler.handleRequest(queryEmployee, null);
        handler.close();

        assertThat(result.getId()).isEqualTo(1);
    }
}
