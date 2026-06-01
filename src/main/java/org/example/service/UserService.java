package org.example.service;


import org.example.dao.UserDao;
import org.example.dto.CreateUserDto;
import org.example.entity.User;
import org.example.mapper.CreateUserMapper;

public class UserService {
    private static volatile UserService INSTANCE;
    private final UserDao userDao = UserDao.getINSTANCE();

    private UserService (){}
    public static UserService  getInstance(){
        if(INSTANCE==null){
            synchronized (UserService .class){
                if(INSTANCE==null){
                    return INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public Integer create(CreateUserDto userDto){
        User user = CreateUserMapper.getINSTANCE().create(userDto);
        return userDao.save(user).getId();
    }
}
