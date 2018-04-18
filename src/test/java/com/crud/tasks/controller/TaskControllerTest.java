package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DbService dbService;

    @MockBean
    TaskMapper taskMapper;

    @Test
    public void getTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test task 1", "Test content 1"));
        taskDtoList.add(new TaskDto(2L, "Test task 2", "Test content 2"));
        taskDtoList.add(new TaskDto(3L, "Test task 3", "Test content 3"));

        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test task 1")))
                .andExpect(jsonPath("$[0].content", is("Test content 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test task 2")))
                .andExpect(jsonPath("$[1].content", is("Test content 2")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].title", is("Test task 3")))
                .andExpect(jsonPath("$[2].content", is("Test content 3")));
    }

    @Test
    public void getTask() throws Exception {
        //Given
        TaskDto testTask = new TaskDto(15L, "Test task", "Test content");

        when(dbService.getTask(any())).thenReturn(Optional.of(new Task()));
        when(taskMapper.mapToTaskDto(any())).thenReturn(testTask);

        //When & Then
        mockMvc.perform(get("/v1/tasks/15").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(15)))
                .andExpect(jsonPath("$.title", is("Test task")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        TaskDto taskToDelete = new TaskDto(2L, "Test task 2", "Test content 2");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test task 1", "Test content 1"));
        taskDtoList.add(taskToDelete);
        taskDtoList.add(new TaskDto(3L, "Test task 3", "Test content 3"));

        Long taskId = 2L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                taskDtoList.remove(taskToDelete);
                return null;
            }
        }).when(dbService).deleteTask(taskId);

        //When & then
        mockMvc.perform(delete("/v1/tasks/2")
                .param("taskId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assert.assertEquals(2, taskDtoList.size());
        verify(dbService, times(1)).deleteTask(2L);
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        TaskDto toUpdateTaskDto = new TaskDto(1L, "Updated test task", "Updated test content");

        when(taskMapper.mapToTask(toUpdateTaskDto)).thenReturn(new Task());
        when(dbService.saveTask(any())).thenReturn(new Task());
        when(taskMapper.mapToTaskDto(any())).thenReturn(toUpdateTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(toUpdateTaskDto);

        //When & then
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Updated test task")))
                .andExpect(jsonPath("$.content", is("Updated test content")));
    }

    @Test
    public void createTask() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        TaskDto toCreateTask = new TaskDto(1L, "Create test task", "Created test task");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                tasks.add(toCreateTask);
                return null;
            }
        }).when(dbService).saveTask(any());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(toCreateTask);

        when(taskMapper.mapToTask(toCreateTask)).thenReturn(new Task());
        when(dbService.saveTask(any())).thenReturn(new Task());
        when(taskMapper.mapToTaskDto(any())).thenReturn(toCreateTask);

        //When & then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());

        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(1L, tasks.get(0).getId().longValue());
        Assert.assertEquals("Create test task", tasks.get(0).getTitle());
        Assert.assertEquals("Created test task", tasks.get(0).getContent());
    }
}