package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskServiceImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa nao encontrada. ID: " + id));
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        if (!id.equals(taskDetails.getId())) {
            throw new IllegalArgumentException("Precisa passar o ID da tarefa");
        }
        Task existingTask = getTaskById(id);
        return taskRepository.update(taskDetails);
    }

    @Override
    public void deleteTask(Long id) {
        getTaskById(id); 
        taskRepository.deleteById(id);
    }
}