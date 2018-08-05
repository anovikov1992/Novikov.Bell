package ru.bellintegrator.practice.organization.service;

import javassist.NotFoundException;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewList;
import ru.bellintegrator.practice.organization.view.OrganizationViewLoadById;
import ru.bellintegrator.practice.organization.view.OrganizationViewSave;

import java.util.List;

/**
 * Сервис
 */
public interface OrganizationService {


    OrganizationViewList getOrganizationByName(String name, Long inn, boolean isActive);    //получить организацию по имени


    OrganizationView loadById(Long id);                                                     //получить организацию по ID

                                                                                            //получить организацию по ID
    void add(String name, String fullName, Long inn, Long kpp,
             String urAddress, Long phone, boolean isActive);


    void update(OrganizationView organization) throws Exception;                            //обновить данные организации




    List<OrganizationView> getAllOrganization();                                            //получить весь список организаций






}