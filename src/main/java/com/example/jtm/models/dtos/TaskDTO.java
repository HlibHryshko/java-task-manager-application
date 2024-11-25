package com.example.jtm.models.dtos;

import com.example.jtm.models.entities.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
            @NotNull Long id,
            @NotBlank String title,
            @NotNull boolean isCompleted
    ) {
    }

    public record TaskCreateRequest(
            @NotBlank String title
    ) {
    }

    public record TaskIdRecord(
            @NotNull Long id
    ) {
    }
}
