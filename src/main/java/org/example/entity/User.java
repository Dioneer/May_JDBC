package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
 private Integer id;
 private String name;
 private LocalDate birthday;
 private String email;
 private String password;
 private Role role;
 private Gender gender;

 public static UserBuilder builder() {
  return new UserBuilder();
 }

 public static class UserBuilder {
  private Integer id;
  private String name;
  private LocalDate birthday;
  private String email;
  private String password;
  private Role role;
  private Gender gender;

  UserBuilder() {
  }

  public UserBuilder id(Integer id) {
   this.id = id;
   return this;
  }

  public UserBuilder name(String name) {
   this.name = name;
   return this;
  }

  public UserBuilder birthday(LocalDate birthday) {
   this.birthday = birthday;
   return this;
  }

  public UserBuilder email(String email) {
   this.email = email;
   return this;
  }

  public UserBuilder password(String password) {
   this.password = password;
   return this;
  }

  public UserBuilder role(Role role) {
   this.role = role;
   return this;
  }

  public UserBuilder gender(Gender gender) {
   this.gender = gender;
   return this;
  }

  public User build() {
   return new User(this.id, this.name, this.birthday, this.email, this.password, this.role, this.gender);
  }

  public String toString() {
   return "User.UserBuilder(id=" + this.id + ", name=" + this.name + ", birthday=" + this.birthday + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ", gender=" + this.gender + ")";
  }
 }
}
