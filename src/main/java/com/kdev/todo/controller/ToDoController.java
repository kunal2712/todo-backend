package com.kdev.todo.controller;

import com.kdev.todo.mapper.ToDoDto;
import com.kdev.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin(origins = "http://localhost:5173")
public class ToDoController {

    @Autowired
    private ToDoService toDoService ;


    
    @GetMapping("") // This maps to exactly /api/todo
        public ResponseEntity<ArrayList<ToDoDto>> getToDos(){
            return new ResponseEntity<>(toDoService.getToDos(),HttpStatus.FOUND);
        }

    @PostMapping("/{userId}/save")
    public ResponseEntity<ToDoDto> saveToDo(@PathVariable Long userId,@RequestBody ToDoDto toDoDto){
        ToDoDto saved =  toDoService.saveToDo(userId , toDoDto);
        return new ResponseEntity<>(saved , HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoDto> updateToDo(@PathVariable Long id , @RequestBody ToDoDto toDoDto){
        toDoDto.setId(id);
        ToDoDto updated = toDoService.updateToDo(toDoDto);
        return  new ResponseEntity<>(updated,HttpStatus.CREATED);
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ToDoDto> delete(@PathVariable Long id){
        return new ResponseEntity<>(toDoService.deleteToDo(id), HttpStatus.OK);
    }


    @PatchMapping("/{id}/complete")
    public ResponseEntity<ToDoDto> completeTodo(@PathVariable Long id){
        return new ResponseEntity<>(toDoService.completeTodo(id) , HttpStatus.OK);
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<List<ToDoDto>> getTodosForUser(@PathVariable Long userId){
        return new ResponseEntity<>(toDoService.getTodosByUid(userId) , HttpStatus.OK);
    }

}
