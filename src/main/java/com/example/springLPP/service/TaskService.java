package com.example.springLPP.service;

import com.example.springLPP.Priority;
import com.example.springLPP.Status;
import com.example.springLPP.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private HashMap<Long, Task> taskMap;
    TaskService(){
        taskMap = new HashMap<>();
        taskMap.put(10L, new Task(10L,1L,2L,
                Status.CREATED, LocalDateTime.now(),
                LocalDateTime.now().plusDays(7), Priority.HIGH));
        taskMap.put(20L, new Task(20L,1L,2L,
                Status.CREATED, LocalDateTime.now(),
                LocalDateTime.now().plusDays(7), Priority.MEDIUM));
        taskMap.put(30L, new Task(30L,1L,2L,
                Status.CREATED, LocalDateTime.now(),
                LocalDateTime.now().plusDays(7), Priority.LOW));
    }
    public Task getId(Long id){
        if(taskMap.containsKey(id)){
            return taskMap.get(id);
        }else{
            throw new NoSuchElementException("Not found task id = "+id);
        }
    }
    public HashMap<Long, Task> getMap(){
        return taskMap;
    }


}
