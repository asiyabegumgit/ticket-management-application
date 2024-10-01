package com.perscholas.ticketmanagement.service;
import com.perscholas.ticketmanagement.repository.EmployeeRepository;
import com.perscholas.ticketmanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee)
    {
    return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeById(Long id)
    {
        return employeeRepository.findById(id);
    }
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
