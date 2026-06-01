package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.entity.Gender;
import org.example.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
