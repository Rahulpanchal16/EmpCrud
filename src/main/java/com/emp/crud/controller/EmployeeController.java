package com.emp.crud.controller;

import com.emp.crud.dto.EmployeeDto;
import com.emp.crud.service.EmployeeService;
import com.emp.crud.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/create")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = this.employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String employeeId) {
        EmployeeDto employeeById = this.employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> allEmployees = this.employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String employeeId) {
        this.employeeService.deleteEmployee(employeeId);
        ApiResponse response = ApiResponse.builder()
                .message("Employee with id " + employeeId + " deleted")
                .success(true)
                .statusCode(HttpStatus.NO_CONTENT)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable String employeeId) {
        EmployeeDto updatedEmployee = this.employeeService.updateEmployee(employeeDto, employeeId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
}