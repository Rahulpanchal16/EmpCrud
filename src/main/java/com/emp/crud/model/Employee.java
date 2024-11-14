package com.emp.crud.model;

import com.emp.crud.util.EncryptionConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_id")
    private String employeeId;  //Will generate a random id instead of auto generating
    private String name;
    private String email;
    @Convert(converter = EncryptionConverter.class)
    private String contactNo;
}
