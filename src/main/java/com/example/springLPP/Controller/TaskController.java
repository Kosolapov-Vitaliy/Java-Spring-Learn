package com.example.springLPP.Controller;

import com.example.springLPP.Task;
import com.example.springLPP.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTaskId(
            @PathVariable("id") Long id
    ){
        System.out.println("Called getTasked id with id = " + id);
        return taskService.getId(id);
    }

    @GetMapping
    public HashMap<Long, Task> getTaskMap(){
        System.out.println("Called TaskMap");
        return taskService.getMap();
    }
}
