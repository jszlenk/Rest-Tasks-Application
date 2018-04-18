package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void validateCard() {
        //Given
        TrelloCard card = new TrelloCard("Test", "Description", "top", "1");

        //When
        trelloValidator.validateCard(card);

        // Then
        assertEquals("Test", card.getName());
        assertEquals("Description", card.getDescription());
        assertEquals("top", card.getPos());
        assertEquals("1", card.getListId());
    }

    @Test
    public void validateTrelloBoards() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Test TrelloLists", false));

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "Test trelloBoardList1", trelloLists));

        // When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoardList);
        // Then
        assertFalse(validatedBoards.isEmpty());
        assertNotNull(validatedBoards);
        assertEquals(1, validatedBoards.size());
        assertEquals("Test TrelloLists", validatedBoards.get(0).getLists().get(0).getName());

        validatedBoards.forEach(trelloBoard -> {
            assertEquals("1", trelloBoard.getId());
            assertEquals("Test trelloBoardList1", trelloBoard.getName());
            assertEquals(1, trelloBoard.getLists().size());
            assertEquals(false, trelloBoard.getLists().isEmpty());
        });
    }
}