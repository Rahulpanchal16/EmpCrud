package com.emp.crud.service.impl;


import com.emp.crud.dto.EmployeeDto;
import com.emp.crud.exception.ResourceNotFoundException;
import com.emp.crud.model.Employee;
import com.emp.crud.repo.EmployeeRepository;
import com.emp.crud.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        String randomEmployeeId = UUID.randomUUID().toString();
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setEmployeeId(randomEmployeeId);
        Employee savedEmployee = this.empRepo.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(String employeeId) {
        Employee employeeById = empRepo
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return modelMapper.map(employeeById, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> allEmployees = this.empRepo.findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return allEmployees;
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Employee employeeById = this.empRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        this.empRepo.delete(employeeById);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, String employeeId) {
        Employee newEmployeeData = this.modelMapper.map(employeeDto, Employee.class);
        Employee employeeById = this.empRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeById.setName(newEmployeeData.getName());
        employeeById.setEmail(newEmployeeData.getEmail());
        employeeById.setContactNo(newEmployeeData.getContactNo());
        Employee updatedEmployee = this.empRepo.save(employeeById);
        return this.modelMapper.map(updatedEmployee, EmployeeDto.class);
    }
}