package com.crud.tasks.scheduler;

import org.junit.Test;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void sendInformationEmail() {
        //Given
        Mail mail = new Mail("test@gmail.com", "Temat", "Tekst", "test@gmail.com");

        //When
        when(adminConfig.getAdminMail()).thenReturn(mail.getMailTo());
        when(taskRepository.count()).thenReturn(1L);
        emailScheduler.sendInformationEmail();
        //Then
        verify(taskRepository, times(1)).count();
        verify(simpleEmailService, times(1)).send(any(Mail.class));
        verify(simpleEmailService, times(0)).send(mail);
    }
}