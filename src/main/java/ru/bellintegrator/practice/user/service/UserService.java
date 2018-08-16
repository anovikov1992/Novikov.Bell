package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.*;

import java.util.List;

/**
 * Сервис
 */
public interface UserService {

    /*
    получить пользователя по ID офиса

    List<UserViewByOfficeIdResponse> getUserByOfficeId(UserViewByOfficeIdRequest userByOfficeId);*/

    /*
    получить пользователя по ID офиса
    */
    List<UserViewByOfficeIdResponse> getUserByOfficeId(Long officeId, String firstName, String middleName,
                                                       String secondName, String position, String docCode,
                                                       String country);

    /*
    получить пользователя по ID
    */
    UserViewLoadById loadById(Long id);

    /*
    обновить данные пользователя
    */
    void update(UserViewUpdate userViewUpdate);

    /*
    добавить нового пользователя
    */
    void add(UserViewSave userViewSave);

    /*
    получить весь список пользователей
    */
    List<UserView> getAllUser();


}