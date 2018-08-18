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
    получить офис по ID
    */
    OfficeViewLoadById findById(Long id);

    /*
    обновить данные офиса
    */
    void update(OfficeViewLoadById office);

    /*
    добавить офис
    */
    void add(OfficeViewSave office);

    /*
    получить весь список офисов
    */
    List<OfficeViewLoadById> getAllOffice();

    /*
    удалить офис по ID
    */
    void delete (Long id);
}

