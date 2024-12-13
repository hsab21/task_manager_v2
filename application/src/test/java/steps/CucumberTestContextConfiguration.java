package steps;

import com.taskmanager.application.TaskManagerApplication;
import io.cucumber.spring.CucumberContextConfiguration;

import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = TaskManagerApplication.class)
public class CucumberTestContextConfiguration {

}
