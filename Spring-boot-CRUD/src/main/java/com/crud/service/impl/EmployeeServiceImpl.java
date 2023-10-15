package com.crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.exception.ResourceNotFoundException;
import com.crud.model.Employee;
import com.crud.repository.EmployeeRepository;
import com.crud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	// this section autowired

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employee = employeeRepository.findById(id);
		 * 
		 * if (employee.isPresent()) { return employee.get(); } else { throw new
		 * ResourceNotFoundException("Employee", "Id", employee); }
		 */

		// we can use above code but we use lambda expression

		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		Employee exitingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

		// set the date come from client
		exitingEmployee.setFirstName(employee.getFirstName());
		exitingEmployee.setLastName(employee.getLastName());
		exitingEmployee.setEmail(employee.getEmail());

		// and update date using above infro

		employeeRepository.save(exitingEmployee);
		return exitingEmployee;

	}

	@Override
	public void deleteEmployee(long id) {

		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);

	}

}
