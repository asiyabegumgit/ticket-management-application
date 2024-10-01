package com.perscholas.ticketmanagement.controller;

import com.perscholas.ticketmanagement.model.Employee;
import com.perscholas.ticketmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public String viewEmployees(Model model) {
        List<Employee> employees=employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

   @GetMapping("/new")
    public String showCreateEmployeeForm(Model model) {
        Employee employee=new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
   }
   @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
       if (!isAuthenticatedAdmin()) {
           return "error/403";
       }
        employeeService.saveEmployee(employee);
       return "redirect:/employees";
   }
   @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        Optional<Employee> optionalEmployee=employeeService.getEmployeeById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            model.addAttribute("employee", employee);
        }
        return "employee-form";

   }
  @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
  }
    public boolean isAuthenticatedAdmin() {
        return true;
    }

    }

