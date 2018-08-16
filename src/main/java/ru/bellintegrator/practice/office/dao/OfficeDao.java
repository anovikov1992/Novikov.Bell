package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeViewRequest;

import java.util.List;

public interface OfficeDao {

    /*
    получить офисы по ID органищации
     */
    List<Office> getOfficeByOrgId (OfficeViewRequest officeViewRequest);

    /*
    получить офис по ID
     */
    Office loadById(Long id);

    /*
    добавить офис в список
     */
    void save(Office office);


    List<Office> getAllOffice();


    void setOrganizationRelationshipNull(Long id);


}
