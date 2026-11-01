package com.example.springLPP.service;

import com.example.springLPP.Status;
import com.example.springLPP.Task;
import com.example.springLPP.TaskEntity;
import com.example.springLPP.TaskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo repo;

    TaskService(TaskRepo repo){
        this.repo = repo;
    }

    public List<Task> findAllTask(){
        List<TaskEntity> allEntities = repo.findAll();
        return allEntities.
                stream().
                map(this::toDomainTask).
                toList();
    }

    public Task getId(Long id){
        TaskEntity taskEntity = repo.findById(id).orElseThrow(()->
                new EntityNotFoundException("Not found task by id = "+id));
        return toDomainTask(taskEntity);
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
        TaskEntity entityToSave = new TaskEntity(
                null,
                task.creatorId(),
                task.makerId(),
                Status.CREATED,
                LocalDateTime.now(),
                task.deadlineData(),
                task.priority()
        );
        TaskEntity savedEntity = repo.save(entityToSave);

        return toDomainTask(savedEntity);
    }

    public Task startTask(Long taskId) {
        TaskEntity taskEntity = repo.findById(taskId).orElseThrow(()->
                new EntityNotFoundException("Not found task id = "+taskId));
        Long makerId = taskEntity.getMakerId();
        if(makerId == null){
            throw new IllegalArgumentException("The makerId field must be filled in");
        }
        if(taskEntity.getStatus().equals(Status.IN_PROGRESS)){
            throw new IllegalArgumentException("Status can not be IN_PROGRESS");
        }
        List<TaskEntity> taskEntities = repo.findAllByMakerIdAndStatus(makerId, Status.IN_PROGRESS);
        if (taskEntities.size()>4){
            throw new IllegalArgumentException("The active number of tasks for the user with makerId: " +
                    makerId +
                    " is greater than the allowed limit");
        }
        TaskEntity updateTaskEntity = new TaskEntity(
                taskEntity.getId(),
                taskEntity.getCreatorId(),
                taskEntity.getMakerId(),
                Status.IN_PROGRESS,
                taskEntity.getCreateDataTime(),
                taskEntity.getDeadlineData(),
                taskEntity.getPriority()
        );
        repo.save(updateTaskEntity);
        return toDomainTask(updateTaskEntity);
    }

    public Task endTask(Long taskId){
        TaskEntity taskEntity = repo.findById(taskId).orElseThrow(()->
                new EntityNotFoundException("Not found task id = "+taskId));

        TaskEntity updateTaskEntity = new TaskEntity(
                taskEntity.getId(),
                taskEntity.getCreatorId(),
                taskEntity.getMakerId(),
                Status.DONE,
                taskEntity.getCreateDataTime(),
                taskEntity.getDeadlineData(),
                taskEntity.getPriority()
        );
        repo.save(updateTaskEntity);
        return toDomainTask(updateTaskEntity);
    }




    public Task updateTask(Long taskId, Task updTask){
        TaskEntity taskEntity = repo.findById(taskId).orElseThrow(()->
            new EntityNotFoundException("Not found task id = "+taskId));
        if(!taskId.equals(updTask.id())){
            throw new IllegalArgumentException("The request id and the id in the request body do not match");
        }
        if(taskEntity.getStatus().equals(Status.DONE)){
            throw new IllegalArgumentException("Status cannot be done");
        }
        TaskEntity updateEntity = new TaskEntity(
                taskEntity.getId(),
                taskEntity.getCreatorId(),
                updTask.makerId(),
                taskEntity.getStatus(),
                taskEntity.getCreateDataTime(),
                updTask.deadlineData(),
                updTask.priority()
        );
        repo.save(updateEntity);
        return toDomainTask(updateEntity);
    }

    public void deleteTask(Long taskId){
        if(!repo.existsById(taskId)){
            throw new EntityNotFoundException("Not found task id = "+taskId);
        }
        repo.deleteById(taskId);
    }

    private Task toDomainTask(TaskEntity tEntity){
        return new Task(
                tEntity.getId(),
                tEntity.getCreatorId(),
                tEntity.getMakerId(),
                tEntity.getStatus(),
                tEntity.getCreateDataTime(),
                tEntity.getDeadlineData(),
                tEntity.getPriority()
        );
    }


}
