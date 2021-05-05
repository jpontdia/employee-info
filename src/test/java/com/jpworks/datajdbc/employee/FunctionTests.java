package com.jpworks.datajdbc.employee;

import com.jpworks.datajdbc.employee.vo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FunctionTests {

    @LocalServerPort
    int port;

    @Test
    void EmployeeInfoTest(){
        Employee queryEmployee = new Employee();
        queryEmployee.setId(1L);

        given()
                .contentType("application/json")
                .body(queryEmployee)
                .when()
                    .post("http://localhost:" + port + "/employeeInfo")
                .then()
                    .assertThat()
                    .body(containsString("firstName"));
    }

    @Test
    void EmployeeTest(){
        Employee queryEmployee = new Employee();
        queryEmployee.setId(1L);

        given()
                .contentType("application/json")
                .body(queryEmployee)
                .when()
                .post("http://localhost:" + port + "/employeeTest")
                .then()
                .assertThat()
                .body(containsString("This is a demo employee"));
    }

}
