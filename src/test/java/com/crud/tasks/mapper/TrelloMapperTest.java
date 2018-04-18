package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(prepareTestDataForBoardMethods());

        //Then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals("4", trelloBoards.get(0).getId());
        Assert.assertEquals("Two", trelloBoards.get(0).getLists().get(1).getName());
        Assert.assertEquals(true, trelloBoards.get(0).getLists().get(2).isClosed());
    }

    @Test
    public void mapToBoardsDto() {
        //When
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(trelloMapper.mapToBoards(prepareTestDataForBoardMethods()));

        //Then
        Assert.assertEquals(2, boardDtos.size());
        Assert.assertEquals("5", boardDtos.get(1).getId());
        Assert.assertEquals("Two", boardDtos.get(0).getLists().get(1).getName());
        Assert.assertEquals(true, boardDtos.get(0).getLists().get(2).isClosed());
    }

    @Test
    public void mapToList() {
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(prepareTestDataForListMethods());

        //Then
        Assert.assertEquals(3, trelloLists.size());
    }

    @Test
    public void mapToListDto() {
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloMapper.mapToList(prepareTestDataForListMethods()));

        //Then
        Assert.assertEquals(3, trelloListDtos.size());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Java", "Test JavaFX", "top", "1");

        //When
        TrelloCardDto resultTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("Java", resultTrelloCardDto.getName());
        Assert.assertEquals("Test JavaFX", resultTrelloCardDto.getDescription());
        Assert.assertEquals("top", resultTrelloCardDto.getPos());
        Assert.assertEquals("1", resultTrelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Java", "Test JavaFX", "top", "1");

        //When
        TrelloCard resultTrelloCard = trelloMapper.mapToCard(cardDto);

        //Then
        Assert.assertEquals("Java", resultTrelloCard.getName());
        Assert.assertEquals("Test JavaFX", resultTrelloCard.getDescription());
        Assert.assertEquals("top", resultTrelloCard.getPos());
        Assert.assertEquals("1", resultTrelloCard.getListId());
    }

    private List<TrelloListDto> prepareTestDataForListMethods() {
        List<TrelloListDto> listOfLists = new ArrayList<>();
        listOfLists.add(new TrelloListDto("1", "One", false));
        listOfLists.add(new TrelloListDto("2", "Two", false));
        listOfLists.add(new TrelloListDto("3", "Three", true));
        return listOfLists;
    }

    private List<TrelloBoardDto> prepareTestDataForBoardMethods() {
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtos1 = prepareTestDataForListMethods();
        List<TrelloListDto> trelloListDtos2 = new ArrayList<>();
        trelloListDtos2.add(new TrelloListDto("1", "one", false));
        trelloListDtos2.add(new TrelloListDto("2", "two", false));
        trelloListDtos2.add(new TrelloListDto("3", "three", false));
        TrelloBoardDto boardDto1 = new TrelloBoardDto("4", "Board1", trelloListDtos1);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("5", "Board2", trelloListDtos2);
        trelloBoardDtoList.add(boardDto1);
        trelloBoardDtoList.add(boardDto2);
        return trelloBoardDtoList;
    }
}