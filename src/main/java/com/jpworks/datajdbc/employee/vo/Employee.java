package com.jpworks.datajdbc.employee.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String phone;
    private Gender gender;
    private EmployeeStatus status;

    @Version
    private Integer version;

    @MappedCollection(keyColumn = "from_date")
    Map<LocalDate, Salary> salaries;

    @MappedCollection(keyColumn = "from_date")
    Map<LocalDate, Address> addresses;

    @Transient
    private String apiVersion;
}
