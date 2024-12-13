package com.taskmanager.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    List<Task> findByCompleted(boolean completed);
    Optional<Task> findById(Long id);
    Task save(Task task);
}
