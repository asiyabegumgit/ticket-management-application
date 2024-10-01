package com.perscholas.ticketmanagement.service;

import com.perscholas.ticketmanagement.model.Employee;
import com.perscholas.ticketmanagement.model.Task;
import com.perscholas.ticketmanagement.repository.EmployeeRepository;
import com.perscholas.ticketmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Task saveTask(Task task)
   {
       return taskRepository.save(task);
   }
  public List<Task> getAllTasks()
  {
      return taskRepository.findAll();
  }
  public Optional<Task> getTaskById(Long id)
  {
      return taskRepository.findById(id);
  }
   public List<Task> getTasksByEmployee(Employee employee)
   {
       System.out.println(employee.getId());

       List<Task> tasks= taskRepository.findByEmployeeId(employee.getId());
       System.out.println(tasks);
       return tasks;
   }
    public void deleteTask(Long id)
    {
        taskRepository.deleteById(id);
    }
    public Task updateTaskStatus(Long taskId, String status)
    {
        return taskRepository.findById(taskId)
                .map(task->{
                    task.setStatus(status);
                    return taskRepository.save(task);
                }).orElseThrow(()->new RuntimeException("task not found"));
    }
}
