package com.app.todoapp.services;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTasks(String tittle) {
        Task task=new Task();
        task.setTittle(tittle);
        task.setCompleted(false);
        taskRepository.save(task);


    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {

         Task task = taskRepository.findById(id)
                 .orElseThrow(() ->new IllegalArgumentException("Invalid Task Id"));
         task.setCompleted(!task.isCompleted());
         taskRepository.save(task);
    }
}
