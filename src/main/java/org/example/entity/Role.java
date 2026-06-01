package org.example.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER, ADMIN;
    public static Optional<Role> find(String role){
        return Arrays.stream(values()).filter(i->i.name().equals(role)).findFirst();
    }
}
