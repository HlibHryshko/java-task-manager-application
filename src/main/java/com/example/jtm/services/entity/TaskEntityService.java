package com.example.jtm.services.entity;

import com.example.jtm.models.entities.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskEntityService {
    private final JpaRepository<Task, Long> repository;

    @Autowired
    public TaskEntityService(JpaRepository<Task, Long> repository) {
        this.repository = repository;
    }

    @Transactional
    public Set<Task> getAll() {
        return new HashSet<>(repository.findAll());
    }

    @Transactional
    public Task create(Task task) {
        return repository.save(task);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void markAsCompleted(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        repository.save(task);
    }
}
