package com.example.testexercise.controller;

import com.example.testexercise.entity.UserEntity;
import com.example.testexercise.exception.EmptyNameFieldException;
import com.example.testexercise.exception.EmptyPassFieldException;
import com.example.testexercise.exception.UserNameAlreadyExistException;
import com.example.testexercise.exception.UserNotExistException;
import com.example.testexercise.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user) {
        logger.trace("Вызван метод addUser c входными параметрами: {}", user.toString());

        try {
            userService.createUser(user);

            logger.trace("Пользователь успешно добавлен в базу данных под id = {}", user.getId());
            return ResponseEntity.ok(String.format("Пользователь успешно создан под id = %d", user.getId()));

        } catch (UserNameAlreadyExistException | EmptyNameFieldException | EmptyPassFieldException e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        logger.trace("Вызван метод getUser c входным параметром id = {}", id);

        try {
            UserEntity user = userService.getUserById(id);
            return ResponseEntity.ok(user);

        } catch (UserNotExistException e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        logger.trace("Вызван метод getAllUsers");

        try {
            return ResponseEntity.ok(userService.getAllUsers());

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user, @PathVariable Long id) {
        logger.trace("Вызван метод updateUser c входными параметрами: id = {} и {}", id, user.toString());

        try {
            return ResponseEntity.ok(userService.updateUserById(user, id));

        } catch (UserNotExistException e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        logger.trace("Вызван метод deleteUser c входным параметром id = {}", id);

        try {
            userService.deleteUserById(id);

            logger.trace("Пользователь под id = {} успешно удален из базы данных", id);
            return ResponseEntity.ok(String.format("Пользователь под id = %d успешно удален", id));

        } catch (UserNotExistException e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
