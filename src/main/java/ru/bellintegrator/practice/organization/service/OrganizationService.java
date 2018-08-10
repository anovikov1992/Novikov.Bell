package ru.bellintegrator.practice.organization.service;

import javassist.NotFoundException;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.*;

import java.util.List;

/**
 * Сервис
 */
public interface OrganizationService {

    /*
    получить организацию по имени
    */
    OrganizationViewList getOrganizationByName(String name, Long inn, Boolean isActive) throws Exception;

    /*
    получить организацию по ID
    */
    OrganizationView loadById(Long id);

    /*
    обновить данные организации
    */
    void update(OrganizationViewUpdate organization) throws Exception;

    /*
    добавить организацию
    */
    void add(String name, String fullName, String inn, String kpp,
             String urAddress, String phone, Boolean isActive) throws Exception;

    /*
    получить весь список организаций
    */
    List<OrganizationView> getAllOrganization();

    /*
    удалить организацию по ID
    */
    void delete (Long id);

}