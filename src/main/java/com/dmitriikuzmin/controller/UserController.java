package com.dmitriikuzmin.controller;

import com.dmitriikuzmin.dto.ResponseResult;
import com.dmitriikuzmin.model.User;
import com.dmitriikuzmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<ResponseResult<User>> add(@RequestBody User user) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(
                    null, this.userService.add(user)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(
                    e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseResult<User>> get(@PathVariable int id) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(
                    null, this.userService.get(id)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(
                    e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    ResponseEntity<ResponseResult<User>> update(@RequestBody User user) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(
                    null, this.userService.update(user)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(
                    e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseResult<User>> delete(@PathVariable int id) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(
                    null, this.userService.delete(id)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(
                    e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
