package com.kdev.todo.mapper;

import com.kdev.todo.entity.User;

public class ToDoDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isCompleted;
    private User user;

    public ToDoDto() {
    }

    public ToDoDto(Long id, String title, String description, boolean isCompleted, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
