package org.example.validator;

import org.example.dto.CreateUserDto;
import org.example.entity.Gender;
import org.example.entity.Role;
import org.example.mapper.LocalDateFormatter;

public class CreateUserValidator {
    private static volatile CreateUserValidator INSTANCE;
    private CreateUserValidator (){}
    public static CreateUserValidator  getInstance(){
        if(INSTANCE==null){
            synchronized (CreateUserValidator.class){
                if(INSTANCE==null){
                    return INSTANCE = new CreateUserValidator();
                }
            }
        }
        return INSTANCE;
    }

    public ValidationResult isValid(CreateUserDto createUserDto){
        var validationResult = new ValidationResult();
        if(!LocalDateFormatter.isValid(createUserDto.getBirthday())){
            validationResult.add(Error.of("invalid.birthday", "Birthday is incorrect"));
        }
        if(Gender.find(createUserDto.getGender()).isEmpty()){
            validationResult.add(Error.of("invalid.gender", "Gender is incorrect"));
        }
        if(Role.find(createUserDto.getGender()).isEmpty()){
            validationResult.add(Error.of("invalid.role", "Role is incorrect"));
        }
        return validationResult;
    }
}
