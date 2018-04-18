package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TrelloConfig.class)
@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        //Given & When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        Assert.assertEquals("https://api.trello.com/1", trelloApiEndpoint);
    }

    @Test
    public void getTrelloAppKey() {
        //Given & When
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        //Then
        Assert.assertEquals("xxx", trelloAppKey);
    }

    @Test
    public void getTrelloToken() {
        //Given & When
        String trelloToken = trelloConfig.getTrelloToken();
        //Then
        Assert.assertEquals("xxx", trelloToken);
    }

    @Test
    public void getUserName() {
        //Given & When
        String username = trelloConfig.getUserName();
        //Then
        Assert.assertEquals("usersname", username);
    }
}