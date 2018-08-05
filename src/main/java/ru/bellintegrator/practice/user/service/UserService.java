package ru.bellintegrator.practice.user.service;

import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * Сервис
 */
public interface UserService {


    User loadByName(String Name);                           //получить организацию по имени


    List<UserView> getAllUser();                    //получить весь список организаций


    void update(UserView organization) throws Exception;    //обновить данные организации


    void add(UserView view);
}