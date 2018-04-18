package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DailyEmailService;
import com.crud.tasks.service.SimpleEmailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private DailyEmailService dailyEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "31 07 16 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String adds = (size > 1) ? "s" : "";
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "Currentyly in database you got: " + size + " task" + adds, null));
    }

    @Scheduled(cron = "31 37 15 * * *")
    public void sendDailyEmail() {
        long size = taskRepository.count();
        String adds = (size > 1) ? "s" : "";
        dailyEmailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "Currentyly in database you got: " + size + " task" + adds, null));
    }
}
