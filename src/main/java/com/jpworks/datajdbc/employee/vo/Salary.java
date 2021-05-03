package com.jpworks.datajdbc.employee.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Generated
@Data
@Builder
public class Salary {
    private LocalDate fromDate;
    @Column("salary")
    private long wage;
    private LocalDate toDate;
}
