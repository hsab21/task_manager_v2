package com.example.taskmanager.service;


import com.taskmanager.application.TaskService;
import com.taskmanager.domain.Task;
import com.taskmanager.infrastructure.api.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    private TaskController taskController;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService);
    }

    @Test
    void getAllTasks_shouldReturnAllTasks() {
        when(taskService.getAllTasks()).thenReturn(List.of(
                new Task(1L, "Task 1", "Description 1", false),
                new Task(2L, "Task 2", "Description 2", true)
        ));

        List<Task> tasks = taskController.getAllTasks();
        assertEquals(2, tasks.size());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getTaskById_shouldReturnTaskIfExists() {
        Task task = new Task(1L, "Task 1", "Description 1", false);
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(task));

        ResponseEntity<Task> response = taskController.getTaskById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Task 1", response.getBody().getLabel());
    }

    @Test
    void getTaskById_shouldReturnNotFoundIfTaskDoesNotExist() {
        when(taskService.getTaskById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskController.getTaskById(99L);
        assertEquals(404, response.getStatusCodeValue());
    }
}
