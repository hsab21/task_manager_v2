package steps;


import com.taskmanager.application.TaskService;
import com.taskmanager.domain.Task;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSteps {

    private TaskService taskService;
    private List<Task> tasks;
    private Task task;

    @Before
    public void setUp() {
        taskService = new TaskService();
        taskService.addTask(new Task(1L, "Task 1", "Description 1", false));
        taskService.addTask(new Task(2L, "Task 2", "Description 2", true));
    }

    @Given("the task list is initialized")
    public void theTaskListIsInitialized() {
        tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
    }

    @When("I retrieve all tasks")
    public void iRetrieveAllTasks() {
        tasks = taskService.getAllTasks();
    }

    @Then("I should see {int} tasks")
    public void iShouldSeeTasks(int taskCount) {
        assertEquals(taskCount, tasks.size());
    }

    @When("I retrieve task with ID {long}")
    public void iRetrieveTaskWithId(Long id) {
        task = taskService.getTaskById(id).orElse(null);
    }

    @Then("I should see {string} as the label")
    public void iShouldSeeAsTheLabel(String label) {
        assertNotNull(task);
        assertEquals(label, task.getLabel());
    }

    @When("I mark the task with ID {long} as completed")
    public void iMarkTheTaskWithIdAsCompleted(Long id) {
        taskService.updateTaskStatus(id, true);
    }

    @Then("the task with ID {long} should be completed")
    public void theTaskWithIdShouldBeCompleted(Long id) {
        Task updatedTask = taskService.getTaskById(id).orElse(null);
        assertNotNull(updatedTask);
        assertTrue(updatedTask.isCompleted());
    }
}
