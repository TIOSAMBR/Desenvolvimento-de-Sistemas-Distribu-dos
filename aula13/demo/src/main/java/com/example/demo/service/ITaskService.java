package com.example.demo.service;

import com.example.demo.model.Task;
import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}