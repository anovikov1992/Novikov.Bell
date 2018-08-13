package ru.bellintegrator.practice.office.service;


import ru.bellintegrator.practice.office.view.OfficeViewLoadById;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewSave;

import java.util.List;

public interface OfficeService {

    /*
    получить офис по ID организации
    */
    List<OfficeView> getOfficeByOrgId(Long orgId, String name, String phoneOffice, Boolean isActive) throws Exception;

    /*
    получить организацию по ID
    */
    OfficeViewLoadById loadById(Long id);

    /*
    обновить данные организации
    */
    void update(OfficeViewLoadById office) throws Exception;

    /*
    добавить организацию
    */
    void add(OfficeViewSave office) throws Exception;

    /*
    получить весь список офисов
    */
    List<OfficeViewLoadById> getAllOffice();
}

