package steps;

import com.taskmanager.domain.Task;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import com.taskmanager.application.TaskManagerApplication;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TaskManagerApplication.class)
public class TaskStepDefinitions {

    private final List<Task> taskList = new ArrayList<>();
    private Task newTask;

    @Given("the following tasks exist:")
    public void theFollowingTasksExist(DataTable dataTable) {
        taskList.clear();
        dataTable.asMaps(String.class, String.class).forEach(row -> {
            Task task = new Task(
                    Long.parseLong(row.get("id")),
                    row.get("label"),
                    row.get("description"),
                    Boolean.parseBoolean(row.get("completed"))
            );
            taskList.add(task);
        });
    }

    @When("I request all tasks")
    public void iRequestAllTasks() {
        assertNotNull(taskList);
    }

    @Then("I receive {int} tasks")
    public void iReceiveTasks(int taskCount) {
        assertEquals(taskCount, taskList.size());
    }

    @Given("no tasks exist")
    public void noTasksExist() {
        taskList.clear();
    }

    @When("I add a task with label {string} and description {string}")
    public void iAddATask(String label, String description) {
        newTask = new Task(1L, label, description, false);
        taskList.add(newTask);
    }

    @Then("I receive a task with id {int} and label {string}")
    public void iReceiveATaskWithIdAndLabel(int id, String label) {
        assertNotNull(newTask);
        assertEquals(id, newTask.getId());
        assertEquals(label, newTask.getLabel());
    }
}
