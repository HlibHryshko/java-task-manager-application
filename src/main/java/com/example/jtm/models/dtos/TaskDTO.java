package com.example.jtm.models.dtos;

import com.example.jtm.models.entities.Task;

public class TaskDTO {
    public static Task taskRecordToTask(TaskCreateRequest request) {
        return new Task(request.title);
    }

    public record TaskCreateRequest(String title) { }

    public record TaskIdRecord(Long id) { }
}
