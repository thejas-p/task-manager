package com.tp.taskmanager.task_manager.repo;

import com.tp.taskmanager.task_manager.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUserId(Long userId);
    List<Tasks> findByTaskStatus(Tasks.TaskStatus taskStatus);
    List<Tasks> findByUserIdOrderByDueDateAsc(Long userId);

}
