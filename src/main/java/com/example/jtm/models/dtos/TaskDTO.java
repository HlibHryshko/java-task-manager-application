package com.example.jtm.models.dtos;

import com.example.jtm.models.entities.Task;

public class TaskDTO {
    public static Task taskRecordToTask(TaskCreateRequest request) {
        return new Task(request.title);
    }

    public static TaskDTO.TaskRecord taskToTaskRecord(Task task) {
        return new TaskRecord(
                task.getId(),
                task.getTitle(),
                task.isCompleted()
        );
    }

    public record TaskRecord(
            Long id,
            String title,
            boolean isCompleted
    ) { }

    public record TaskCreateRequest(String title) { }

    public record TaskIdRecord(Long id) { }
}
