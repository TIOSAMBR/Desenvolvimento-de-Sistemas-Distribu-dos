package com.example.demo.repository;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepositoryImpl implements ITaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public Task save(Task task) {
        task.setId(idCounter.getAndIncrement());
        task.setCreationDate(LocalDate.now());
        tasks.add(task);
        return task;
    }

    @Override
    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public Task update(Task task) {
        Task existingTask = findById(task.getId()).orElseThrow(
            () -> new TaskNotFoundException("Tarefa nao encontrada para o ID: " + task.getId()));
        
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());
        return existingTask;
    }
}