package com.kdev.todo.service;

import com.kdev.todo.entity.ToDo;
import com.kdev.todo.mapper.ToDoDto;
import com.kdev.todo.repository.ToDoRepository;

import java.util.ArrayList;
import java.util.List;

public interface ToDoService {

    public ToDoDto saveToDo(Long userId , ToDoDto toDoDto);

    public ToDoDto updateToDo(ToDoDto toDoDto);

    public ArrayList<ToDoDto> getToDos();

    public ToDoDto deleteToDo(Long id);

    public ToDoDto completeTodo(Long id);

    public List<ToDoDto> getTodosByUid(Long id);
}
