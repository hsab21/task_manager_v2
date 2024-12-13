package com.taskmanager.infrastructure.repository;

import com.taskmanager.domain.Task;
import com.taskmanager.domain.TaskRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> findByCompleted(boolean completed) {
        return tasks.stream()
                .filter(task -> task.isCompleted() == completed)
                .toList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(idGenerator.incrementAndGet());
            tasks.add(task);
        } else {
            tasks.removeIf(existingTask -> existingTask.getId().equals(task.getId()));
            tasks.add(task);
        }
        return task;
    }
}
