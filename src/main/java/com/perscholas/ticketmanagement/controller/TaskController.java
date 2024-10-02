package com.perscholas.ticketmanagement.controller;

import com.perscholas.ticketmanagement.model.Employee;
import com.perscholas.ticketmanagement.model.Task;
import com.perscholas.ticketmanagement.service.EmployeeService;
import com.perscholas.ticketmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public String listAllTasks(Model model)
    {
       if(!isAuthenticatedAdmin())
        {
        return "error/403";

        }
        List<Task> tasks=taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
       return "task-list";
    }

    private boolean isAuthenticatedAdmin() {
        return true;
    }

    @GetMapping("/new")
    public String showCreateTaskForm(Model model)
    {
        Task task=new Task();
        List<Employee> employees=employeeService.getAllEmployees();
        model.addAttribute("task", task);
        model.addAttribute("employees", employees);
        return "task-form";
    }
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {

        taskService.saveTask(task);
        return "redirect:/tasks";
    }
@GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model)
{
    Optional<Task> optionalTask=taskService.getTaskById(id);
    if(optionalTask.isPresent())
    {
        Task task=optionalTask.get();
        model.addAttribute("task", task);
    }
    List<Employee> employees=employeeService.getAllEmployees();
    model.addAttribute("employees", employees);
    return "task-form";
}

@GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id)
{
    taskService.deleteTask(id);
    return "redirect:/tasks";
}

    @GetMapping("/employee")
    public String employeeTaskHub(Model model)
    {
        Employee employee = getAuthenticatedEmployee();
        List<Task> tasks = taskService.getTasksByEmployee(employee);
        model.addAttribute("tasks", tasks);
        model.addAttribute("employee", employee);
        return "task-hub";
    }
    @PostMapping("/update-status")
    public String updateTaskStatus(@RequestParam Long taskId, @RequestParam String status) {
        Employee employee = getAuthenticatedEmployee();
        taskService.updateTaskStatus(taskId,status);

        return "redirect:/tasks/employee";
    }

    public Employee getAuthenticatedEmployee()
    {
        return employeeService.getEmployeeById(2L).get();
    }
}

