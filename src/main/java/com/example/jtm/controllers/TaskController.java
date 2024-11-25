package com.example.jtm.controllers;

import com.example.jtm.models.dtos.TaskDTO;
import com.example.jtm.models.entities.Task;
import com.example.jtm.services.entity.TaskEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
