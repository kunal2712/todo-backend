package com.kdev.todo.mapper;

import com.kdev.todo.entity.ToDo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ToDoMapper {


    public ToDoDto toToDoDto(ToDo toDo){
        return new ToDoDto(toDo.getId(),toDo.getTitle() ,toDo.getDescription() ,toDo.isCompleted(),toDo.getUser());
    }

    public ToDo toToDo(ToDoDto toDoDto){
        return new ToDo(toDoDto.getId(),toDoDto.getTitle(), toDoDto.getDescription(), toDoDto.isCompleted(),toDoDto.getUser());
    }



}
