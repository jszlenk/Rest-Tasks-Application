package com.crud.tasks;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrelloConfig.class)
@SpringBootTest
public class TasksApplicationTests {
    @Test
    public void contextLoads() {
    }
}