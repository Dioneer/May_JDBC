package org.example.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {
    private static final UserMapper INSTANCE = new UserMapper();
    public static UserMapper getInstance(){return INSTANCE;}

    @Override
    public UserDto create(User from) {
        return UserDto.builder()
                .id(from.getId())
                .role(from.getRole())
                .gender(from.getGender())
                .name(from.getName())
                .email(from.getEmail())
                .birthday(from.getBirthday())
                .build();
    }
}
