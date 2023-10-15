package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
