package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepo;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepo) {
		employeeRepo = theEmployeeRepo;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepo.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepo.findById(theId);

		Employee theEmp = null;

		if (result.isPresent()) {
			theEmp = result.get();
		} else {
			// we didn't find employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		return theEmp;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeRepo.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeRepo.deleteById(theId);
	}

}
