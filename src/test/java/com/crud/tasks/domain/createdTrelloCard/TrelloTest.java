package com.crud.tasks.domain.createdTrelloCard;

import org.junit.Assert;
import org.junit.Test;

public class TrelloTest {
    Trello trello = new Trello(1, 11);

    @Test
    public void getBoard() {
        //Given & WWhen
        int board = trello.getBoard();
        //Then
        Assert.assertEquals(1, board);
    }

    @Test
    public void getCard() {
        //Given & When
        int card = trello.getCard();
        //Then
        Assert.assertEquals(11, card);
    }
}