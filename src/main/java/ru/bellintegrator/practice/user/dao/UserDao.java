package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.user.model.User;

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

    /*
    получить весь список пользователей
    */
    List<User> getAllUser();

    /*
    добавить организацию в список
    */
    void save(User user);

    /*
    удалить пользователя по ID
    */
    void delete(Long id);
}
