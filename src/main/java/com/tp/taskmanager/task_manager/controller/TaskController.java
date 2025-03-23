package com.tp.taskmanager.task_manager.controller;

import com.tp.taskmanager.task_manager.dto.TaskDTO;
import com.tp.taskmanager.task_manager.model.Tasks;
import com.tp.taskmanager.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

   // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable Long id){
        return taskService.getTasks(id);
    }

    @GetMapping("/user/{id}")
    public List<TaskDTO> getTaskByUser(@PathVariable Long id){
        return taskService.getTasksByUserId(id);
    }

    @GetMapping
    public List<TaskDTO> getAllTasks(){
        return taskService.getAllTask();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody @Valid Tasks tasks){
        return taskService.createTask(tasks);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("deleted successfully");
    }

    @PutMapping("/update/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @Valid @RequestBody Tasks tasks){
        return taskService.updateTask(id,tasks);
    }

    @GetMapping("/date/{id}")
    public List<TaskDTO> getTaskByDate(@PathVariable Long id){
        return taskService.getUserTasksSortedByDueDate(id);
    }
}
