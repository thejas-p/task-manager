package com.tp.taskmanager.task_manager.service.impl;

import com.tp.taskmanager.task_manager.dto.TaskDTO;
import com.tp.taskmanager.task_manager.dto.UserDTO;
import com.tp.taskmanager.task_manager.model.Tasks;
import com.tp.taskmanager.task_manager.model.User;
import com.tp.taskmanager.task_manager.repo.TaskRepository;
import com.tp.taskmanager.task_manager.repo.UserRepository;
import com.tp.taskmanager.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskDTO getTasks(Long id){
        Tasks tasks = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDTO(tasks);
    }

    @Override
    public List<TaskDTO> getAllTask(){
        List<Tasks> tasks= taskRepository.findAll();
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserId(long id){
        List<Tasks> tasks= taskRepository.findByUserId(id);
        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Tasks createTask(Tasks tasks) {
//       String userName= SecurityContextHolder.getContext().getAuthentication().getName();
//        User user= userRepository.findByUsername(userName).orElseThrow(()->new RuntimeException("user not found"));
//        tasks.setUser(user);
         // Tasks task=taskRepository.save(tasks);
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

    // Method to convert Task entity to TaskDTO
    public TaskDTO convertToDTO(Tasks task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCompleted(task.isCompleted());

        User user = task.getUser();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        taskDTO.setUser(userDTO);
        return taskDTO;
    }


}
