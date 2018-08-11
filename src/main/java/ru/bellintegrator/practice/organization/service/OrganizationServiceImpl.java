package ru.bellintegrator.practice.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
       // validate(inn);
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
        }
        org.setName(organization.name);
        org.setFullName(organization.fullName);
        validate(organization.inn);
        org.setInn(validate(organization.inn));
        validateKppNumberLength(organization.kpp);
        org.setKpp(validateKppNumberLength(organization.kpp));
        org.setUrAddress(organization.urAddress);
        if (organization.phone == null && organization.isActive == null){
            organization.phone = phoneToString(org.getPhone());
            organization.isActive = org.getIsActive();
        } else if (organization.phone == null) {
            organization.phone = phoneToString(org.getPhone());
        } else if (organization.isActive == null) {
            organization.isActive = org.getIsActive();
        }
        org.setPhone(phoneToLong(organization.phone));
        org.setActive(organization.isActive);
    }

    /*
    добавить организацию
    */

    @Override
    public void add(OrganizationViewSave organization) throws Exception {
        Organization ogr;
        validate(organization.inn);
        validateKppNumberLength(organization.kpp);
        if (organization.phone == null) {
            ogr = new Organization  (organization.name, organization.fullName, validate(organization.inn),
                                    validateKppNumberLength(organization.kpp),  organization.urAddress,  organization.isActive);
        }
        else {
            ogr = new Organization (organization.name, organization.fullName, validate(organization.inn),
                                    validateKppNumberLength(organization.kpp),  organization.urAddress,
                                    phoneToLong(organization.phone), organization.isActive);
        }
        organizationDao.save(ogr);
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
        officeDao.setOrganizationRelationshipNull(id);
        organizationDao.delete(id);
    }





    private Long validate(String a) throws Exception {
        Long aLong = null;
        try {
            aLong = Long.parseLong(a);
        } catch (Exception e) {
            throw new OrganisationValidationException("ИНН должен состоять из цифр");
        }
        if (aLong != null) {
            int count = 1;
            Long b = aLong / 10;
            while (b >= 1){
                count++;
                b /= 10;
            }
            if (count != 10) {
                throw new OrganisationValidationException("ИНН должен состоять из 10 цифр");
            }
        }
        return aLong;
    }

    private Long validateKppNumberLength(String a) throws Exception {
        Long aLong = null;
        try {
            aLong = Long.parseLong(a);
        } catch (Exception e) {
            throw new OrganisationValidationException("КПП должен состоять из цифр");
        }
        int count = 1;
        Long b = aLong / 10;
        while (b >= 1){
            count++;
            b /= 10;
        }
        if (count != 9) {
            throw new OrganisationValidationException("КПП должен состоять из 9 цифр");
        }
        return aLong;
    }

    private Long phoneToLong (String phone) {
        try {
            Long a = Long.parseLong(phone);
            return a;
        } catch (Exception e) {
            throw new OrganisationValidationException("Телефон должен состоять из цифр");
        }
    }

    private String phoneToString (Long phone) {
        String a = phone.toString();
        return a;
    }
}
