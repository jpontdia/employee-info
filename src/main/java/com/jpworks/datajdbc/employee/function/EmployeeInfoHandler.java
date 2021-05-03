package com.jpworks.datajdbc.employee.function;

import com.jpworks.datajdbc.employee.vo.Employee;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

@SuppressWarnings("unused")
public class EmployeeInfoHandler extends  FunctionInvoker<Employee, Employee> {

    @FunctionName("employeeInfo")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<Employee>> request,
                    ExecutionContext context) {
        Employee employee = request.getBody()
                .filter((e -> e.getId() != null))
                .orElseGet(() -> Employee.builder().id(
                                    Long.valueOf(request.getQueryParameters().getOrDefault("id", "0"))).build()
                            );
        context.getLogger().info("Azure handler,  Employee id: " + employee.getId() );
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(employee, context))
                .header("Content-Type", "application/json")
                .build();
    }
}
