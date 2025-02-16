package com.tp.taskmanager.task_manager.service;

import com.tp.taskmanager.task_manager.model.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    public List<Tasks> getAllTask(Long id);
    public Tasks createTask(Tasks tasks);
    public void deleteTaskById(Long id);
    public Tasks updateTask(Long id,Tasks tasks);
}