Feature: Task Management
  As a user
  I want to manage tasks
  So that I can keep track of my to-dos

  Scenario: Get all tasks
    Given the task list is initialized
    When I retrieve all tasks
    Then I should see 2 tasks

  Scenario: Get a specific task by ID
    Given the task list is initialized
    When I retrieve task with ID 1
    Then I should see "Task 1" as the label

  Scenario: Update a task status
    Given the task list is initialized
    When I mark the task with ID 1 as completed
    Then the task with ID 1 should be completed
