package org.example.service;


import org.example.dao.UserDao;
import org.example.dto.CreateUserDto;
import org.example.entity.User;
import org.example.exception.ValidationException;
import org.example.mapper.CreateUserMapper;
import org.example.validator.CreateUserValidator;
import org.example.validator.ValidationResult;

public class UserService {
    private static volatile UserService INSTANCE;
    private final UserDao userDao = UserDao.getINSTANCE();
    private final CreateUserValidator userValidator = CreateUserValidator.getInstance();

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
}
