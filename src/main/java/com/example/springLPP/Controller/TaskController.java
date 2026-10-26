package com.example.springLPP.Controller;

import com.example.springLPP.Task;
import com.example.springLPP.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TaskController {
    private static final Logger log =  LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTaskId(
            @PathVariable("id") Long id
    ){
        String logText = "Called getTaskedId with id = "+ id;
        log.info(logText);
        return taskService.getId(id);
    }

    @GetMapping
    public HashMap<Long, Task> getTaskMap(){
        log.info("Called getTaskMap with id = ");
        return taskService.getMap();
    }
}
