package com.tp.taskmanager.task_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Cannot be blank")
    private String title;
    private String description;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus; //= TaskStatus.PENDING;

    public enum TaskStatus{
        PENDING, IN_PROGRESS, COMPLETED
    }


}
