package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewByOfficeIdRequest;
import ru.bellintegrator.practice.user.view.UserViewByOfficeIdResponse;

import java.util.List;


public interface UserDao {

    /*
    получить пользователя по ID офиса
    */
    List<User> getUserByOfficeId(Long officeId, String firstName, String middleName,
                                 String secondName, String position, String docCode,
                                 String country);

    /*
    получить пользователя по ID
    */
    User loadById(Long id);

    User loadByName(String Name);       //получить организацию по имени


    List<User> getAllUser();    //получить весь список организаций


    void save(User user);       //добавить организацию в список

    /*
    обнулить связь пользователей с офисом
     */
    void setOfficeRelationshipNull(Long id);
}
