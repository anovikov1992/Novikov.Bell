package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationViewLoadById;

import java.util.List;


public interface OrganizationDao {

    Organization getOrganizationByName(String name, Long inn, Boolean isActive);        //получить организацию по имени
    Organization loadByCriteria(String name, Long inn, Boolean isActive);

    Organization loadById(Long id);           //получить организацию по ID

    Organization loadByIdCriteria(Long id);           //получить организацию по ID (с использованием criteria)


    void save(Organization organization);       //добавить организацию в список


    List<Organization> getAllOrganization();    //получить весь список организаций


    void delete(Long id);                       //удалить организацию по ID



}
