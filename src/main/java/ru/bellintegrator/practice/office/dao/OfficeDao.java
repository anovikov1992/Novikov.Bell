package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

public interface OfficeDao {

    List<Office> getAllOffice();


    void setOrganizationNull(Long id);


}
