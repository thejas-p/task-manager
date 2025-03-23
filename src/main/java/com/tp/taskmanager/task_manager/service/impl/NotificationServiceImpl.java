package com.tp.taskmanager.task_manager.service.impl;

import com.tp.taskmanager.task_manager.model.Tasks;
import com.tp.taskmanager.task_manager.repo.TaskRepository;
import com.tp.taskmanager.task_manager.service.NotificationService;
import org.apache.tomcat.Jar;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender mailSender;
    private final TaskRepository taskRepository;

    public NotificationServiceImpl(JavaMailSender mailSender, TaskRepository taskRepository) {
        this.mailSender = mailSender;
        this.taskRepository = taskRepository;
    }

    @Override
    @Scheduled(cron = "0 0 8 * * ?") //  8 AM
    public void sendTaskReminders() {
        Date tomorrow = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)); // for adding 24hrs
        List<Tasks> dueTasks = taskRepository.findByDueDate(tomorrow);

        for (Tasks task : dueTasks) {
            sendEmail(task.getUser().getEmail(), "Task Due Soon",
                    "Reminder: Your task '" + task.getTitle() + "' is due tomorrow.");
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}


