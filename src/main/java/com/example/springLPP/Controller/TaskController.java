package com.example.springLPP.Controller;

import com.example.springLPP.Task;
import com.example.springLPP.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger log =  LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(
            @PathVariable("id") Long id
    ){
        String logText = "Called getTaskedId with id = "+ id;
        log.info(logText);
        return ResponseEntity.status(HttpStatus.OK)
                        .body(taskService.getId(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(){
        log.info("Called getTaskMap with id = ");
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.findAllTask());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task taskToCreate){
        log.info("Called createTask");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(taskToCreate));
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<Task> startTask(@PathVariable("id") Long id){
        log.info("Called startTask");
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.startTask(id));
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<Task> endTask(@PathVariable("id") Long id){
        log.info("Called endTask");
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.endTask(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        log.info("Called deleteTask");
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id,
                                           @RequestBody Task taskToUpdate){
        log.info("Called updateTask");
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.updateTask(id, taskToUpdate));
    }
}
