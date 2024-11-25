package com.example.jtm.controllers;

import com.example.jtm.models.dtos.TaskDTO;
import com.example.jtm.models.entities.Task;
import com.example.jtm.services.entity.TaskEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private final TaskEntityService taskEntityService;

    public TaskController(TaskEntityService taskEntityService) {
        this.taskEntityService = taskEntityService;
    }

    @PostMapping("/api/v1/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO.TaskIdRecord create(@RequestBody TaskDTO.TaskCreateRequest request) {

        Task task = TaskDTO.taskRecordToTask(request);

        return new TaskDTO.TaskIdRecord(
                taskEntityService.create(task).getId()
        );
    }

    @GetMapping("/api/v1/tasks")
    @ResponseStatus(HttpStatus.OK)
    public Set<TaskDTO.TaskRecord> getAll() {
        return taskEntityService.getAll()
                .stream().map(TaskDTO::taskToTaskRecord)
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/api/v1/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskEntityService.deleteById(id);
    }

    @PutMapping("/api/v1/tasks/{id}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void complete(@PathVariable Long id) {
        taskEntityService.markAsCompleted(id);
    }
}
