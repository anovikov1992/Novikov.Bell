package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

import java.util.List;


public interface UserDao {

    User loadByName(String Name);       //получить организацию по имени


    List<User> getAllUser();    //получить весь список организаций


    void save(User user);       //добавить организацию в список


    User findById(Long id);             //получить организацию по id
}
