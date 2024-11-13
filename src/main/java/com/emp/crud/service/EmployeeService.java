package com.emp.crud.service;

import com.emp.crud.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(String employeeId);

    List<EmployeeDto> getAllEmployees();

    void deleteEmployee(String employeeId);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, String employeeId);
}