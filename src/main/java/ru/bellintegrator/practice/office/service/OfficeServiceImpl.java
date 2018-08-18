package ru.bellintegrator.practice.office.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewLoadById;
import ru.bellintegrator.practice.office.view.OfficeViewRequest;
import ru.bellintegrator.practice.office.view.OfficeViewSave;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.my.exception.OrgOutException;
import ru.bellintegrator.practice.organization.my.exception.OrganisationValidationException;
import ru.bellintegrator.practice.user.dao.UserDao;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;

    private final OrganizationDao organizationDao;

    private final UserDao userDao;

    public OfficeServiceImpl(OfficeDao officeDao, UserDao userDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.userDao = userDao;
        this.organizationDao = organizationDao;
    }

    /*
    получить офис по ID организации
    */
    @Override
    public List<OfficeView> getOfficeByOrgId(OfficeViewRequest officeViewRequest) {
        if (officeViewRequest.orgId == null) {
            throw new OrganisationValidationException("Поле orgId является обязательным");
        }
        if (officeViewRequest.phoneOffice != null) {
            validatePhone(officeViewRequest.phoneOffice);
        }
        List<Office> officeList;
        officeList = officeDao.getOfficeByOrgId(officeViewRequest);

        return officeList.stream().map(elem -> new OfficeView(elem.getId(), elem.getName(), elem.getIsActive())).collect(Collectors.toList());
    }

    /*
    получить офис по ID
    */
    @Override
    public OfficeViewLoadById findById(Long id) {
        OfficeViewLoadById officeViewLoadById = new OfficeViewLoadById();
        try{
            Office office = officeDao.findById(id);
            officeViewLoadById.id = office.getId();
            officeViewLoadById.name = office.getName();
            officeViewLoadById.address = office.getAddress();
            officeViewLoadById.phoneOffice = office.getPhoneOffice();
            officeViewLoadById.isActive = office.getIsActive();
            System.out.println(office.getUsers());
        }catch (OrgOutException e){
            throw new OrgOutException("Офиса с таким ID нет в базе данных");
        }

        return officeViewLoadById;
    }

    /*
    обновить данные офисa
    */
    @Override
    public void update(OfficeViewLoadById office) {
        Office office1;
        try {
             office1 = officeDao.findById(office.id);
        }catch (EmptyResultDataAccessException e) {
            throw new OrgOutException("Офиса с таким ID нет в базе данных");
        }
        if ((office.name == null) || (office.address == null) || (office.isActive == null)) {
            throw new OrgOutException("Заполнены не все обязательные поля");
        }
        System.out.println("0000000000000000000000000000000");
        office1.setName(office.name);
        office1.setAddress(office.address);
        office1.setIsActive(office.isActive);
        System.out.println("1111111111111111111111111111111111111");
        if (office.phoneOffice == null) {
            office.phoneOffice = office1.getPhoneOffice();
            office1.setPhoneOffice(office.phoneOffice);
            System.out.println("2222222222222222222222222222222222222");
        } else {
            validatePhone(office.phoneOffice);
            office1.setPhoneOffice(office.phoneOffice);
            System.out.println("333333333333333333333333333333333333333");
        }
        System.out.println("444444444444444444444444444444444444444444444444");
    }

    /*
    добавить новый офис
    */
    @Override
    public void add(OfficeViewSave office) {
        if (office.phoneOffice != null) {
            validatePhone(office.phoneOffice);
        }
        Office office1 = new Office (office.name, office.address, office.phoneOffice, office.isActive);
        if (office.orgId != null) {
            try {
                Organization organization = organizationDao.loadById(office.orgId);
                organization.addOffice(office1);
            } catch (EmptyResultDataAccessException e) {
                throw new OrganisationValidationException("Невозможно привязать офис у казанному orgId, т.к. организации" +
                        " с таким ID нет в БД");
            }
        }
        officeDao.save(office1);
    }

    /*
    получить список всех офисов
    */
    @Override
    public List<OfficeViewLoadById> getAllOffice() {
        List<Office> all = officeDao.getAllOffice();
        return all.stream()
                .map(mapOffice())
                .collect(Collectors.toList());
    }
    private Function<Office, OfficeViewLoadById> mapOffice() {
        return p -> {

            OfficeViewLoadById view = new OfficeViewLoadById();
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.address = p.getAddress();
            view.isActive = p.getIsActive();
            view.phoneOffice = p.getPhoneOffice();
            return view;
        };
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Office office = officeDao.findById(id);
        if (office.getOrganization() != null) {
            office.getOrganization().removeOffice(office);
        }
            officeDao.delete(id);
    }

    private Long validatePhone(String a) {
        Long aLong;
        try {
            aLong = Long.parseLong(a);
        } catch (NumberFormatException e) {
            throw new OrganisationValidationException("Телефон должен состоять из цифр");
        }
        return aLong;
    }
}

