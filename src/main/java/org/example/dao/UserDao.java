package org.example.dao;

import org.example.entity.*;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User, Integer>{
    private static volatile UserDao INSTANCE;
    private UserDao(){}
    public static UserDao getINSTANCE(){
        if(INSTANCE==null){
            synchronized (UserDao.class){
                if(INSTANCE==null){
                    return INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        String sql = """
                insert into users (birthday, birthday, email,password,role,gender) 
                VALUES (?,?,?,?,?,?);
                                                                                                                                        
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement state = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            state.setObject(1, user.getName());
            state.setObject(2, user.getBirthday());
            state.setObject(3, user.getEmail());
            state.setObject(4, user.getPassword());
            state.setObject(5, user.getRole().name());
            state.setObject(6, user.getGender().name());
            state.executeUpdate();
            ResultSet resultSet = state.getGeneratedKeys();
            if(resultSet.next()){
                user.setId(resultSet.getInt("id"));
            }
            return user;
        }catch (SQLException e){
            throw new DaoException("User DAO save exception", e);
        }
    }

    public Optional<User> getByEmailAndPassword(String email, String password){
        String sql = """
                select * from users where email = ? and password = ?;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement state = connection.prepareStatement(sql)){
            state.setObject(1, email);
            state.setObject(1, password);
            ResultSet set = state.executeQuery();
            User user = null;
            if(set.next()){
                user = createItem(set);
            }
            return Optional.ofNullable(user);
        }catch (SQLException e){
            throw new DaoException("User DAO getByEmailAndPassword exception", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean update(User item) {
        return false;
    }

    @Override
    public User createItem(ResultSet set) {
        try {
            return User.builder().id(set.getInt("id"))
                    .role(Role.valueOf(set.getString("role")))
                    .gender(Gender.valueOf(set.getString("gender")))
                    .birthday(set.getDate("birthday").toLocalDate())
                    .email(set.getString("email"))
                    .password(set.getString("password"))
                    .name(set.getString("name"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Ticket DAO create ticket method",e);
        }
    }
}
