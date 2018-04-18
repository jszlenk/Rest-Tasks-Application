package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrelloCardTest {
    @Test
    public void getTrelloCard() {
        //Given & When
        TrelloCard trelloCard = new TrelloCard("Test", "Test", "1", "1");

        //Then
        assertEquals("Test", trelloCard.getName());
        assertEquals("1", trelloCard.getPos());
        assertEquals("Test", trelloCard.getDescription());
        assertEquals("1", trelloCard.getListId());
    }
}