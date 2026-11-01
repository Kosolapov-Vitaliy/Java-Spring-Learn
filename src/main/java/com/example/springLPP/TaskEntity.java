package com.example.springLPP;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "task")
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="creatorId", nullable = false)
    private Long creatorId;
    @Column(name ="makerId", nullable = false)
    private Long makerId;
    @Enumerated(EnumType.STRING)
    @Column(name ="status", nullable = false)
    private Status status;
    @Column(name ="createData", nullable = false)
    private LocalDateTime createDataTime;
    @Column(name ="deadlineData", nullable = false)
    private LocalDateTime deadlineData;
    @Enumerated(EnumType.STRING)
    @Column(name ="priority", nullable = false)
    private Priority priority;

    public TaskEntity() {
    }

    public TaskEntity(Long  id,
                      Long creatorId,
                      Long makerId,
                      Status status,
                      LocalDateTime createDataTime,
                      LocalDateTime deadlineData,
                      Priority priority) {
        this.id = id;
        this.creatorId = creatorId;
        this.makerId = makerId;
        this.status = status;
        this.createDataTime = createDataTime;
        this.deadlineData = deadlineData;
        this.priority = priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getMakerId() {
        return makerId;
    }

    public void setMakerId(Long makerId) {
        this.makerId = makerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateDataTime() {
        return createDataTime;
    }

    public void setCreateDataTime(LocalDateTime createDataTime) {
        this.createDataTime = createDataTime;
    }

    public LocalDateTime getDeadlineData() {
        return deadlineData;
    }

    public void setDeadlineData(LocalDateTime deadlineData) {
        this.deadlineData = deadlineData;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
