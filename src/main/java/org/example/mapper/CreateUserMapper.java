package org.example.mapper;

import org.example.dto.CreateUserDto;
import org.example.entity.Gender;
import org.example.entity.Role;
import org.example.entity.User;



public class CreateUserMapper implements Mapper<User, CreateUserDto>{
    private static volatile CreateUserMapper INSTANCE;
    private CreateUserMapper(){}
    public static CreateUserMapper getINSTANCE(){
        if(INSTANCE==null){
            synchronized (CreateUserMapper.class){
                if(INSTANCE==null){
                    return INSTANCE = new CreateUserMapper();
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public User create(CreateUserDto from) {
        return User.builder()
                .name(from.getName())
                .birthday(LocalDateFormatter.format(from.getBirthday()))
                .email(from.getEmail())
                .password(from.getPassword())
                .gender(Gender.valueOf(from.getGender()))
                .role(Role.valueOf(from.getRole()))
                .build();
    }
}
