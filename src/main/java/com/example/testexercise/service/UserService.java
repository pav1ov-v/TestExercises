package com.example.testexercise.service;

import com.example.testexercise.dao.UserRepository;
import com.example.testexercise.entity.UserEntity;
import com.example.testexercise.exception.EmptyNameFieldException;
import com.example.testexercise.exception.EmptyPassFieldException;
import com.example.testexercise.exception.UserNameAlreadyExistException;
import com.example.testexercise.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity user)
            throws UserNameAlreadyExistException, EmptyNameFieldException, EmptyPassFieldException {

        if (user.getName() == null || user.getName().equals("")) {
            throw new EmptyNameFieldException("Введите имя пользователя");
        }

        if (user.getPass() == null || user.getPass().equals("")) {
            throw new EmptyPassFieldException("Введите пароль пользователя");
        }

        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
        } else {
            throw new UserNameAlreadyExistException("Имя пользователя уже занято");
        }
    }

    public UserEntity getUserById(Long id) throws UserNotExistException {
        if (userRepository.findById(id).isPresent()) {
            UserEntity user = userRepository.findById(id).get();

            logger.trace("Экземпляр пользователя успешно получен из базы данных. " +
                    "Печать полей экземпляра: {}", user);

            return user;
        } else {
            throw new UserNotExistException("Пользователя с данным id не существует");
        }
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        logger.trace("Экземпляры пользователей успешно получены из базы данных. " +
                "Печать полей экземпляров: {}", users);

        return users;
    }

    public UserEntity updateUserById(UserEntity newUser, Long id) throws UserNotExistException {
        UserEntity user = getUserById(id);
        if (newUser != null) {

            if (newUser.getName() != null && !newUser.getName().equals(""))
                user.setName(newUser.getName());

            if (newUser.getPass() != null && !newUser.getName().equals(""))
                user.setPass(newUser.getPass());

            if (newUser.getMail() != null && !newUser.getMail().equals("")) {
                user.setMail(newUser.getMail());
            }
            userRepository.save(user);
            logger.trace("Поля экземпляра пользователя под id = {} успешно обновлены. " +
                    "Печать полей экземпляра: {}", id, user);
        }
        return user;
    }

    @Transactional
    public void deleteUserById(Long id) throws UserNotExistException {
        userRepository.delete(getUserById(id));
    }
}
