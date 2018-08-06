package ru.bellintegrator.practice.organization.service;

import javassist.NotFoundException;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.*;

import java.util.List;

/**
 * Сервис
 */
public interface OrganizationService {


    OrganizationViewList getOrganizationByName(String name, Long inn, Boolean isActive);    //получить организацию по имени


    OrganizationView loadById(Long id);                                                     //получить организацию по ID

                                                                                            //получить организацию по ID
    void add(String name, String fullName, Long inn, Long kpp,
             String urAddress, Long phone, Boolean isActive);


    void update(OrganizationViewUpdate organization) throws Exception;                      //обновить данные организации




    List<OrganizationView> getAllOrganization();                                            //получить весь список организаций


    void delete (Long id);                                                                  //удалить организацию по ID

}