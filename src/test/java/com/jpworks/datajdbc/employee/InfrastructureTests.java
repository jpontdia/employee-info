package com.jpworks.datajdbc.employee;

import com.jpworks.datajdbc.employee.vo.Salary;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InfrastructureTests {

    @Test
    void main() {
        EmployeeApplication.main(new String[] {});
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    @Test
    void valueObjects(){
        Salary salary1 = Salary.builder()
                .fromDate(LocalDate.of(2020, 1, 1))
                .toDate(LocalDate.of(2020, 4, 30))
                .wage(150000)
                .build();
        assertThat(salary1.getWage()).isEqualTo(150000);
    }

    private void doNotThrowException(){
        //This method will never throw exception
    }
}
