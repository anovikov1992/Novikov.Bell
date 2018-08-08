package ru.bellintegrator.practice.organization.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.organization.MyException.InnLengthException;
import ru.bellintegrator.practice.organization.MyException.OrgIdException;
import ru.bellintegrator.practice.organization.MyException.OrgNameException;
import ru.bellintegrator.practice.organization.MyException.PhoneFormatException;
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
@RestControllerAdvice
public class OrganizationServiceImpl  extends ResponseEntityExceptionHandler implements OrganizationService {


    private final OrganizationDao organizationDao;

    private final OfficeDao officeDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, OfficeDao officeDao) {
        this.organizationDao = organizationDao;
        this.officeDao = officeDao;
    }


  /*  @Override                                                                       //получить организацию по имени
    public Organization loadByName(String Name) {
        return organizationDao.loadByName(Name);
    }*/


    @Override                                                                       //получить организацию по имени
    @Transactional
    public OrganizationViewList getOrganizationByName(String name, Long inn, Boolean isActive) throws Exception {
        if (inn != null) {
            validateNumberLength(inn);
        }
        OrganizationViewList view = new OrganizationViewList();
        try {
            Organization organizationByName = organizationDao.getOrganizationByName(name, inn, isActive);
            view.id = organizationByName.getId();
            view.name = organizationByName.getName();
            view.isActive = organizationByName.getIsActive();
        } catch (Exception e) {
            throw new OrgNameException();
        }
        return view;
    }




    @Override
    public OrganizationView loadById(Long id) throws RuntimeException {
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
            throw new OrgIdException();
        }
        return organizationView;
    }


    @Override                                                                       //добавить организацию
    public void add(String name, String fullName, Long inn, Long kpp, String urAddress, Long phone, Boolean isActive) {
        Organization organization = null;
     //   if (phone == null) {
     //       organization = new Organization(name, fullName, inn, kpp, urAddress, isActive);
      //  }
      //  else {
            organization = new Organization(name, fullName, inn, kpp, urAddress, phone, isActive);
      //  }
        organizationDao.save(organization);
    }



    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
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




    @Override                                                                       //обновить данные организации
    public void update(OrganizationViewUpdate organization) throws Exception {

        Organization org = organizationDao.loadById(organization.id);
        org.setName(organization.name);
        org.setFullName(organization.fullName);
        org.setInn(organization.inn);
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

    private void validateNumberLength(Long a) throws Exception {
        int count = 1;
        Long b = a / 10;
        while (b >= 1){
            count++;
            b /= 10;
        }
        if (count != 10) {
            throw new InnLengthException();
        }
    }

    private void validate(OrganizationViewUpdate organization) {
        if (organization.id ==  null || organization.name == null){
            throw new OrgIdException();
        }
    }

    @Override
    public void delete(Long id) {
     //  organizationDao.loadByIdCriteria(id);
        officeDao.setOrganizationNull(id);
        organizationDao.delete(id);
    }
}
