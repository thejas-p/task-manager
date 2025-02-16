package com.tp.taskmanager.task_manager.controller;

import com.tp.taskmanager.task_manager.model.Tasks;
import com.tp.taskmanager.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

   // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public List<Tasks> getAllTasks(@PathVariable Long id){
        return taskService.getAllTask(id);
    }

    @PostMapping
    public Tasks createTask(@RequestBody Tasks tasks){
        return taskService.createTask(tasks);

    }
    @DeleteMapping
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("deleted successfully");
    }
}
