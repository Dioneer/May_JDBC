package org.example.service;

import org.example.dao.UserDao;
import org.example.dto.CreateUserDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.ValidationException;
import org.example.mapper.CreateUserMapper;
import org.example.mapper.UserMapper;
import org.example.validator.CreateUserValidator;
import org.example.validator.ValidationResult;

import java.util.Optional;

public class UserService {
    private static volatile UserService INSTANCE;
    private final UserDao userDao = UserDao.getINSTANCE();
    private final CreateUserValidator userValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    private UserService (){}
    public static UserService  getInstance(){
        if(INSTANCE==null){
            synchronized (UserService.class){
                if(INSTANCE==null){
                    return INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public Integer create(CreateUserDto userDto) {
        ValidationResult result = userValidator.isValid(userDto);
        if(result.isValid()) {
            User user = CreateUserMapper.getINSTANCE().create(userDto);
            return userDao.save(user).getId();
        }else{
            throw new ValidationException(result.getErrors());
        }
    }
    public Optional<UserDto> login(String email, String password){
        return userDao.getByEmailAndPassword(email,password).map(userMapper::create);
    }
}
