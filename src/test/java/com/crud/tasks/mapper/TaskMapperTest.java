package com.crud.tasks.mapper;

import static org.junit.Assert.*;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        // Given
        TaskDto taskDto = new TaskDto((long) 1, "Test", "Testing");

        // When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        // Then
        Assert.assertTrue(mappedTask.getClass().equals(Task.class));
        Assert.assertEquals(1, taskDto.getId().longValue());
        Assert.assertTrue(mappedTask.getId() == 1);
        Assert.assertEquals("Test", taskDto.getTitle());
        Assert.assertTrue(mappedTask.getTitle().equals("Test"));
        Assert.assertEquals("Testing", taskDto.getContent());
        Assert.assertTrue(mappedTask.getContent().equals("Testing"));
    }

    @Test
    public void mapToTaskDto() {
        // Given
        Task task = new Task((long) 1, "Test", "Testing");

        // When
        TaskDto mappedTask = taskMapper.mapToTaskDto(task);
        // Then
        Assert.assertTrue(mappedTask.getClass().equals(TaskDto.class));
        Assert.assertEquals(1, task.getId().longValue());
        Assert.assertTrue(mappedTask.getId() == 1);
        Assert.assertEquals("Test", task.getTitle());
        Assert.assertTrue(mappedTask.getTitle().equals("Test"));
        Assert.assertEquals("Testing", task.getContent());
        Assert.assertTrue(mappedTask.getContent().equals("Testing"));
    }

    @Test
    public void mapToTaskDtoList() {
        // Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task((long) 1, "Test1", "Testing1"));
        taskList.add(new Task((long) 2, "Test2", "Testing2"));
        taskList.add(new Task((long) 3, "Test3", "Testing3"));

        // When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertNotNull(mappedList);
        Assert.assertTrue(mappedList.size() == 3);
        Assert.assertTrue(mappedList.get(0).getClass().equals(TaskDto.class));
        Assert.assertTrue(mappedList.get(0).getTitle().equals("Test1"));
        Assert.assertEquals(mappedList.get(0).getContent(), "Testing1");
        Assert.assertEquals("Test3", mappedList.get(2).getTitle());
        Assert.assertEquals(1, mappedList.get(0).getId().longValue());
    }

    @Test
    public void mapToTaskDtoListWhenListIsEmpty() {
        //Given
        List<Task> taskList = new ArrayList<>();

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(0, taskDtoList.size());
    }
}