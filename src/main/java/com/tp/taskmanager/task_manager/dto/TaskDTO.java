package com.tp.taskmanager.task_manager.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private UserDTO user;
    private TaskStatus taskStatus;
    private Date createdAt;


    public enum TaskStatus{
        PENDING, IN_PROGRESS, COMPLETED
    }


}