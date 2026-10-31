package com.example.springLPP.service;

import com.example.springLPP.Status;
import com.example.springLPP.Task;
import com.example.springLPP.TaskEntity;
import com.example.springLPP.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private HashMap<Long, Task> taskMap;
    AtomicLong counter;

    private final TaskRepo repo;

    TaskService(TaskRepo repo){
        this.repo = repo;
        taskMap = new HashMap<>();
        counter=new AtomicLong(0);
    }

    public List<Task> findAllTask(){
        List<TaskEntity> allEntities = repo.findAll();
        return allEntities.stream().map(it->
                new Task(
                        it.getId(),
                        it.getCreatorId(),
                        it.getMakerId(),
                        it.getStatus(),
                        it.getCreateDataTime(),
                        it.getDeadlineData(),
                        it.getPriority()
                )).toList();
    }



    /// До внедрения БД
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

    public Task createTask(Task task){
        if(task.id()!=null){
            throw new IllegalArgumentException("Id should be Empty");
        }
        if(task.status()!=null){
            throw new IllegalArgumentException("Status should be Empty");
        }
        if(task.createDataTime()!=null){
            throw new IllegalArgumentException("createDataTime should be Empty");
        }
        Task newTask = new Task(
                counter.incrementAndGet(),
                task.creatorId(),
                task.makerId(),
                Status.CREATED,
                LocalDateTime.now(),
                task.deadlineData(),
                task.priority()
        );
        taskMap.put(newTask.id(), newTask);
        return newTask;
    }

    public HashMap<Long, Task> deleteTask(Long taskId){
        if(taskMap.containsKey(taskId)){
            taskMap.remove(taskId);
            return taskMap;
        }
        else{
            throw new NoSuchElementException("Not found task id = "+taskId);
        }
    }

    public Task updateTask(Long taskId, Task updTask){
        if(!taskMap.containsKey(taskId)){
            throw new NoSuchElementException("Not found task id = "+taskId);
        }
        if(!taskId.equals(updTask.id())){
            throw new IllegalArgumentException("The request id and the id in the request body do not match");
        }
        if(taskMap.get(taskId).status().equals(Status.DONE)&&!(updTask.status().equals(Status.IN_PROGRESS))){
            throw new IllegalArgumentException("Status cannot be done");
        }
        Task newTask = new Task(
                taskId,
                updTask.creatorId(),
                updTask.makerId(),
                updTask.status(),
                updTask.createDataTime(),
                updTask.deadlineData(),
                updTask.priority()
        );
        taskMap.put(taskId, updTask);
        return newTask;
    }


}
