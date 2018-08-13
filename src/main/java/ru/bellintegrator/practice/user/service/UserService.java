package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewLoadById;
import ru.bellintegrator.practice.user.view.UserViewSave;
import ru.bellintegrator.practice.user.view.UserViewUpdate;

import java.util.List;

/**
 * Сервис
 */
public interface UserService {

    /*
    получить пользователя по ID
    */
 //   UserViewLoadById loadByOrgId(Long id);

    /*
    получить пользователя по ID
    */
    UserViewLoadById loadById(Long id);

    /*
    обновить данные пользователя
    */
    void update(UserViewUpdate userViewUpdate) throws Exception;

    /*
    добавить нового пользователя
    */
    void add(UserViewSave userViewSave) throws Exception;

    /*
    получить весь список пользователей
    */
    List<UserView> getAllUser();

}