package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;

import java.util.List;

public interface OfficeDao {

    /*
    получить офисы по ID органищации
     */
    Office getOfficeByOrgId (Long orgId, String name/*, String phone*/, Boolean isActive);
    Office loadByCriteria(Long orgId, String name/*, String phone*/, Boolean isActive);

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
