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
    Office findById(Long id);

    /*
    добавить офис в список
     */
    void save(Office office);

    /*
    получить список всех офисов
     */
    List<Office> getAllOffice();

    /*
    удалить офис по ID
     */
    void delete(Long id);

    void setOrganizationRelationshipNull(Long id);


}
