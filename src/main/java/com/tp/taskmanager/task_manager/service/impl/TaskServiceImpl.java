package com.tp.taskmanager.task_manager.service.impl;

import com.tp.taskmanager.task_manager.model.Tasks;
import com.tp.taskmanager.task_manager.repo.TaskRepository;
import com.tp.taskmanager.task_manager.repo.UserRepository;
import com.tp.taskmanager.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Tasks> getAllTask(Long id){
        return taskRepository.findAll();
    }

    @Override
    public Tasks createTask(Tasks tasks) {
//       String userName= SecurityContextHolder.getContext().getAuthentication().getName();
//        User user= userRepository.findByUsername(userName).orElseThrow(()->new RuntimeException("user not found"));
//        tasks.setUser(user);
        return taskRepository.save(tasks);
    }

    @Override
    public void deleteTaskById(Long id){
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        taskRepository.deleteById(id);

    }

    @Override
    public Tasks updateTask(Long id,Tasks taskDetails) {
        Tasks tasks=taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        tasks.setTitle(taskDetails.getTitle());
//        tasks.setUser(taskDetails.getUser());
        tasks.setDescription(taskDetails.getDescription());
        tasks.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(tasks);
    }

}
