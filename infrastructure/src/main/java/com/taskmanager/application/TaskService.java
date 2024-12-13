package com.taskmanager.application;


import com.taskmanager.domain.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getPendingTasks() {
        return tasks.stream().filter(task -> !task.isCompleted()).toList();
    }

    public Optional<Task> getTaskById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Optional<Task> updateTaskStatus(Long id, boolean completed) {
        Optional<Task> task = getTaskById(id);
        task.ifPresent(t -> t.setCompleted(completed));
        return task;
    }
}
