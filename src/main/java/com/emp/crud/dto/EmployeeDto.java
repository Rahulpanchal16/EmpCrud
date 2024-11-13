package com.emp.crud.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private String employeeId;
    private String name;
    private String email;
    private Long contactNo;
}
