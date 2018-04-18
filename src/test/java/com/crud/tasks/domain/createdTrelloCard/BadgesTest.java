package com.crud.tasks.domain.createdTrelloCard;

import org.junit.Test;
import static org.junit.Assert.*;

public class BadgesTest {
    @Test
    public void getVotes() {
        //Given & When
        Badges badges = new Badges(1, new AttachmentsByType(new Trello(1, 2)));

        //Then
        assertEquals(1, badges.getVotes());
    }

    @Test
    public void getAttachmentsByType() {
        //Given & When
        Badges badges = new Badges(1, new AttachmentsByType(new Trello(1, 2)));

        //Then
        assertEquals(1, badges.getAttachmentsByType().getTrello().getBoard());
        assertEquals(2, badges.getAttachmentsByType().getTrello().getCard());
    }

    @Test
    public void shouldGetBagde() {
        //Given
        Trello trello = new Trello(2, 3);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(1, attachmentsByType);

        //When
        int resusltBagdeVotes = badges.getVotes();
        Trello resultTrello = attachmentsByType.getTrello();
        int trelloBoard = trello.getBoard();
        int trelloCard = trello.getCard();

        //Then
        assertNotNull(resultTrello);
        assertEquals(1, resusltBagdeVotes);
        assertEquals(2, trelloBoard);
        assertEquals(3, trelloCard);
    }
}