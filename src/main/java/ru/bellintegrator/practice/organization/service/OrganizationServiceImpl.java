package ru.bellintegrator.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.organization.MyException.*;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
//@RestControllerAdvice
public class OrganizationServiceImpl  extends ResponseEntityExceptionHandler implements OrganizationService {


    private  OrganizationDao organizationDao;

    private  OfficeDao officeDao;

    public OrganizationServiceImpl() {
    }

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, OfficeDao officeDao) {
        this.organizationDao = organizationDao;
        this.officeDao = officeDao;
    }

    /*
    получить организацию по имени
    */

    @Override
    @Transactional
    public OrganizationViewList getOrganizationByName(String name, Long inn, Boolean isActive) throws Exception {
        validate(inn);
        OrganizationViewList view = new OrganizationViewList();
        try {
            Organization organizationByName = organizationDao.getOrganizationByName(name, inn, isActive);
            view.id = organizationByName.getId();
            view.name = organizationByName.getName();
            view.isActive = organizationByName.getIsActive();
        } catch (Exception e) {
            if ((inn != null) || (isActive != null)) {
                throw  new OrgOutException("Организации с такой комбинацией параметров нет");
            }
            else {
                throw  new OrgOutException("Организации с таким именем нет");
            }
        }
        return view;
    }

    /*
    получить организацию по ID
    */

    @Override
    public OrganizationView loadById(Long id) {
        OrganizationView organizationView = new OrganizationView();
        try {
            Organization organization = organizationDao.loadById(id);
            organizationView.id = organization.getId();
            organizationView.name = organization.getName();
            organizationView.fullName = organization.getFullName();
            organizationView.inn = organization.getInn();
            organizationView.kpp = organization.getKpp();
            organizationView.urAddress = organization.getUrAddress();
            organizationView.phone = organization.getPhone();
            organizationView.isActive = organization.getIsActive();
        } catch (Exception e) {
            throw new OrgOutException("Организации с таким ID нет в базе данных");
        }
        return organizationView;
    }

    /*
    обновить данные организации
    */

    @Override
    public void update(OrganizationViewUpdate organization) throws Exception {
        Organization org = null;
        try {
            org = organizationDao.loadById(organization.id);
        } catch (Exception e) {
            throw new OrgOutException("Организации с таким ID нет в базе данных");
        }
        org.setName(organization.name);
        org.setFullName(organization.fullName);
        validate(organization.inn);
        org.setInn(organization.inn);
        validateKppNumberLength(organization.kpp);
        org.setKpp(organization.kpp);
        org.setUrAddress(organization.urAddress);
        if (organization.phone == null && organization.isActive == null){
            organization.phone = org.getPhone();
            organization.isActive = org.getIsActive();
        } else if (organization.phone == null) {
            organization.phone = org.getPhone();
        } else if (organization.isActive == null) {
            organization.isActive = org.getIsActive();
        }
        org.setPhone(organization.phone);
        org.setActive(organization.isActive);
    }

    /*
    добавить организацию
    */

    @Override
    public void add(String name, String fullName, Long inn, Long kpp, String urAddress, Long phone, Boolean isActive) throws Exception {
        Organization organization = null;
        validate(inn);
        validateKppNumberLength(kpp);
        if (phone == null) {
            organization = new Organization(name, fullName, inn, kpp, urAddress, isActive);
        }
        else {
            organization = new Organization(name, fullName, inn, kpp, urAddress, phone, isActive);
        }
        organizationDao.save(organization);
    }

    /*
    получить весь список организаций
    */

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> getAllOrganization() {
        List<Organization> all = organizationDao.getAllOrganization();

        return all.stream()
                .map(mapOrganization())
                .collect(Collectors.toList());
    }
    private Function<Organization, OrganizationView> mapOrganization() {
        return p -> {
            OrganizationView view = new OrganizationView();
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.fullName = p.getFullName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.urAddress = p.getUrAddress();
            view.phone = p.getPhone();
            view.isActive = p.getIsActive();
            return view;
        };
    }

    /*
    удалить организацию по ID
    */

    @Override
    public void delete(Long id) {
        officeDao.setOrganizationNull(id);
        organizationDao.delete(id);
    }





    private void validate(Long a) throws Exception {
        if (a != null) {
            int count = 1;
            Long b = a / 10;
            while (b >= 1){
                count++;
                b /= 10;
            }
            if (count != 10) {
                throw new OrganisationValidationException("ИНН должен состоять из 10 цифр");
            }
        }
    }

    private void validateKppNumberLength(Long a) throws Exception {
        int count = 1;
        Long b = a / 10;
        while (b >= 1){
            count++;
            b /= 10;
        }
        if (count != 9) {
            throw new OrganisationValidationException("КПП должен состоять из 9 цифр");
        }
    }



}
