package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.createdTrelloCard.CreatedTrelloCardDto;
import org.junit.Test;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(new TrelloBoardDto("1", "Test", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtoList);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void createTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test", "pos", "1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "Test", "www.test.com");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("test@gmail.com");

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNotNull(result);
        assertEquals(createdCard, result);
        assertEquals("www.test.com", result.getShortUrl());
        assertEquals("1", result.getId());
        assertEquals("Test", result.getName());
        assertEquals("test@gmail.com", adminConfig.getAdminMail());
        assertEquals(createdCard.getShortUrl(), result.getShortUrl());
        verify(simpleEmailService, times(1)).send(any(Mail.class));
    }

    @Test
    public void fetchNoBoards() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> fetchedBoardsDto = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(fetchedBoardsDto);
        assertTrue(fetchedBoardsDto.isEmpty());
    }
}