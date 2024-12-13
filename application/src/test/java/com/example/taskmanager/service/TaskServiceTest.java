package com.example.taskmanager.service;


import com.taskmanager.application.TaskService;
import com.taskmanager.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
        taskService.addTask(new Task(1L, "Task 1", "Description 1", false));
        taskService.addTask(new Task(2L, "Task 2", "Description 2", true));
    }

    @Test
    void getAllTasks_shouldReturnAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
    }

    @Test
    void getPendingTasks_shouldReturnOnlyPendingTasks() {
        List<Task> pendingTasks = taskService.getPendingTasks();
        assertEquals(1, pendingTasks.size());
        assertFalse(pendingTasks.get(0).isCompleted());
    }

    @Test
    void getTaskById_shouldReturnTaskIfExists() {
        Optional<Task> task = taskService.getTaskById(1L);
        assertTrue(task.isPresent());
        assertEquals("Task 1", task.get().getLabel());
    }

    @Test
    void getTaskById_shouldReturnEmptyIfTaskDoesNotExist() {
        Optional<Task> task = taskService.getTaskById(99L);
        assertTrue(task.isEmpty());
    }

    @Test
    void updateTaskStatus_shouldUpdateTaskStatus() {
        Optional<Task> updatedTask = taskService.updateTaskStatus(1L, true);
        assertTrue(updatedTask.isPresent());
        assertTrue(updatedTask.get().isCompleted());
    }

    @Test
    void addTask_shouldAddNewTask() {
        Task newTask = new Task(3L, "Task 3", "Description 3", false);
        taskService.addTask(newTask);

        List<Task> tasks = taskService.getAllTasks();
        assertEquals(3, tasks.size());
        assertTrue(tasks.contains(newTask));
    }
}
