package com.tp.taskmanager.task_manager.service;

import com.tp.taskmanager.task_manager.dto.TaskDTO;
import com.tp.taskmanager.task_manager.model.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    public List<TaskDTO> getAllTask();
    public TaskDTO createTask(Tasks tasks);
    public void deleteTaskById(Long id);
    public TaskDTO updateTask(Long id,Tasks tasks);
    public TaskDTO getTasks(Long id);
    public List<TaskDTO> getTasksByUserId(long id);
}