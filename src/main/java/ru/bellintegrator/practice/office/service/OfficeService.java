package ru.bellintegrator.practice.office.service;


import ru.bellintegrator.practice.office.view.OfficeViewLoadById;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewRequest;
import ru.bellintegrator.practice.office.view.OfficeViewSave;

import java.util.List;

public interface OfficeService {

    /*
    получить офис по ID организации (с помощью @RequestBody)
    */
    List<OfficeView> getOfficeByOrgId(OfficeViewRequest officeViewRequest) ;

    /*
    получить офис по ID организации (с помощью @RequestParam)
    *//*
    List<OfficeView> getOfficeByOrgId(Long orgId, String name, String phoneOffice, Boolean isActive) throws Exception;*/

    /*
    получить организацию по ID
    */
    OfficeViewLoadById loadById(Long id);

    /*
    обновить данные организации
    */
    void update(OfficeViewLoadById office);

    /*
    добавить организацию
    */
    void add(OfficeViewSave office);

    /*
    получить весь список офисов
    */
    List<OfficeViewLoadById> getAllOffice();
}

