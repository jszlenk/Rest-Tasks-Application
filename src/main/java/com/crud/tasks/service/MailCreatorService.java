package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://test.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Good Bye!");
        context.setVariable("company", adminConfig.getCompanyName() + ", E-mail: " + adminConfig.getCompanyMail() + ", Phone no.: " + adminConfig.getCompanyPhone());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_config", companyConfig);
        context.setVariable("preview_message", "Trello Card Created");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyScheduleEmail() {

        List<String> capability = new ArrayList<>();
        capability.add("You can manage your tasks");
        capability.add("Provides connection with Trello Account");
        capability.add("Application allows sending tasks to Trello");

        List<Task> tasksList = taskRepository.findAll();

        List<String> tasks = taskRepository.findAll().stream()
                .map(task -> task.getId() + ". " + task.getTitle())
                .collect(Collectors.toList());

        String ending = (taskRepository.count() != 1) ? "s" : "";

        Context context = new Context();
        context.setVariable("tasks_url", "http://localhost:8080/v1/task/getTasks");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_admin_male", adminConfig.getAdminSex().equals("male"));
        context.setVariable("number_of_tasks", "You have " + taskRepository.count() + " task" + ending + "in a database at the moment.");
        context.setVariable("tasks", tasks);
        context.setVariable("tasks_list", tasksList);
        context.setVariable("task_orientation", capability);
        context.setVariable("is_daily_message", true);
        return templateEngine.process("mail/daily-number-of-tasks-mail", context);
    }
}
