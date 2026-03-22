package com.kdev.todo.service.impl;

import com.kdev.todo.entity.ToDo;
import com.kdev.todo.entity.User;
import com.kdev.todo.exception.ResourceNotFoundException;
import com.kdev.todo.mapper.ToDoDto;
import com.kdev.todo.mapper.ToDoMapper;
import com.kdev.todo.repository.ToDoRepository;
import com.kdev.todo.repository.UserRepository;
import com.kdev.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoMapper toDoMapper;

    @Override
    public ToDoDto saveToDo(Long userId,ToDoDto toDoDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User" ,"ID" ,userId ));

        toDoDto.setUser(user);
        ToDo saved = toDoRepository.save(toDoMapper.toToDo(toDoDto));
        return toDoMapper.toToDoDto(saved);
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto) {
        ToDo before = toDoRepository.findById(toDoDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Todo" , "ID" , toDoDto.getId()));
        ToDo after = toDoRepository.save(toDoMapper.toToDo(toDoDto));
        return toDoMapper.toToDoDto(after);
    }

    @Override
    public ArrayList<ToDoDto> getToDos() {
        ArrayList<ToDoDto> res = new ArrayList<>();
        toDoRepository.findAll().forEach(toDo -> res.add(toDoMapper.toToDoDto(toDo)));
        return res;
    }

    @Override
    public ToDoDto deleteToDo(Long id) {
        ToDo deleted = toDoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo" , "ID" , id));
        toDoRepository.deleteById(id);
        return toDoMapper.toToDoDto(deleted);
    }

    @Override
    public ToDoDto completeTodo(Long id) {
        ToDo update = toDoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo" , "ID" , id));
        update.setCompleted(true);
        return toDoMapper.toToDoDto(toDoRepository.save(update));
    }

    @Override
    public List<ToDoDto> getTodosByUid(Long id) {
        if(userRepository.findById(id).isPresent()){
           return
            toDoRepository
                    .findByUserId(id)
                    .stream()
                    .map((todo) -> toDoMapper.toToDoDto(todo)).collect(Collectors.toList());
        }else{
            return Collections.emptyList();
        }

    }
}
