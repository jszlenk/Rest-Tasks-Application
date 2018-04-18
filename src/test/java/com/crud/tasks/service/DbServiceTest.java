package com.crud.tasks.service;

import org.junit.Test;

import static org.junit.Assert.*;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Task 1", "Content 1"));
        taskList.add(new Task(2L, "Task 2", "Content 2"));
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        //Then
        assertEquals(2, resultList.size());
    }

    @Test
    public void getTask() {
        //Given
        Long id = 1L;
        Task task = new Task(1L, "Task 1", "Content 1");
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        //When
        Optional<Task> resultTask = dbService.getTask(id);
        //Then
        assertEquals("Task 1", resultTask.get().getTitle());
    }

    @Test
    public void deleteTask() {
        //Given
        Long id = 1L;
        //When
        dbService.deleteTask(id);
        //Then
        verify(taskRepository, times(1)).deleteById(id);
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1L, "Task 1", "Content 1");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task resultTask = dbService.saveTask(task);
        //Then
        assertEquals("Task 1", resultTask.getTitle());
    }
}