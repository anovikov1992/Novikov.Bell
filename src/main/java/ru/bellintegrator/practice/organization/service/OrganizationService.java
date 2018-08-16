package ru.bellintegrator.practice.organization.service;

import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewList;
import ru.bellintegrator.practice.organization.view.OrganizationViewSave;
import ru.bellintegrator.practice.organization.view.OrganizationViewUpdate;

import java.util.List;

/**
 * Сервис
 */
public interface OrganizationService {

    /*
    получить организацию по имени
    */
    OrganizationViewList getOrganizationByName(String name, Long inn, Boolean isActive);

    /*
    получить организацию по ID
    */
    OrganizationView loadById(Long id);

    /*
    обновить данные организации
    */
    void update(OrganizationViewUpdate organization) ;

    /*
    добавить организацию
    */
    void add(OrganizationViewSave organization) ;

    /*
    получить весь список организаций
    */
    List<OrganizationView> getAllOrganization();

    /*
    удалить организацию по ID
    */
    void delete (Long id);

}