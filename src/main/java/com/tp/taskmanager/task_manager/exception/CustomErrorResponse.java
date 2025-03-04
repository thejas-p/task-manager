package com.tp.taskmanager.task_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorResponse {
    // Getters and Setters
    private LocalDateTime timestamp;
    private String message;
    private String details;

}
