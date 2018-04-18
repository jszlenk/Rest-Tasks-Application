package com.crud.tasks.domain.createdTrelloCard;

import org.junit.Assert;
import org.junit.Test;

public class AttachmentsByTypeTest {
    @Test
    public void getTrello() {
        //Given
        Trello newTrello = new Trello(1, 2);
        AttachmentsByType newAttachment = new AttachmentsByType(newTrello);
        Badges Badge = new Badges(3, newAttachment);

        //When & Then
        Assert.assertEquals(Badge.getAttachmentsByType().getTrello().getBoard(), 1);
    }
}