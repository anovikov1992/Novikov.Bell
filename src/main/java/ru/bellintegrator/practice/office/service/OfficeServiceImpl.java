package ru.bellintegrator.practice.office.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewLoadById;
import ru.bellintegrator.practice.office.view.OfficeViewSave;
import ru.bellintegrator.practice.organization.MyException.OrgOutException;
import ru.bellintegrator.practice.organization.MyException.OrganisationValidationException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;

    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    /*
    получить офис по ID организации
    */
    @Override
    public List<OfficeView> getOfficeByOrgId(Long orgId, String name, String phoneOffice, Boolean isActive) {
        List<Office> officeList;
        try {
            officeList = officeDao.getOfficeByOrgId(orgId, name, phoneOffice, isActive);
        } catch (Exception e) {
            if ((name != null) || (isActive != null)) {
                throw new OrgOutException("Организации с такой комбинацией параметров нет");
            } else {
                throw new OrgOutException("Офиса с такой организацией внутри нет");
            }
        }
        return officeList.stream().map(elem -> new OfficeView(elem.getId(), elem.getName(), elem.getIsActive())).collect(Collectors.toList());
    }

    /*
    получить офис по ID
    */
    @Override
    public OfficeViewLoadById loadById(Long id) {
        OfficeViewLoadById officeViewLoadById = new OfficeViewLoadById();
        try{
            Office office = officeDao.loadById(id);
            officeViewLoadById.id = office.getId();
            officeViewLoadById.name = office.getName();
            officeViewLoadById.address = office.getAddress();
            officeViewLoadById.phoneOffice = office.getPhoneOffice();
            officeViewLoadById.isActive = office.getIsActive();
        }catch (Exception e){
            throw new OrgOutException("Офиса с таким ID нет в базе данных");
        }
        return officeViewLoadById;
    }

    /*
    обновить данные офисa
    */
    @Override
    public void update(OfficeViewLoadById office) {
        Office office1 = null;
        try {
            office1 = officeDao.loadById(office.id);
        }catch (Exception e) {
            throw new OrgOutException("Офиса с таким ID нет в базе данных");
        }
        if ((office.name == null) || (office.address == null) || (office.isActive == null)) {
            throw new OrgOutException("Заполнены не все обязательные поля");
        }

        office1.setName(office.name);
        office1.setAddress(office.address);
        office1.setIsActive(office.isActive);
        if (office.phoneOffice == null) {
            office.phoneOffice = office1.getPhoneOffice();
            office1.setPhoneOffice(office.phoneOffice);
        } else {
            validatePhone(office.phoneOffice);
            office1.setPhoneOffice(office.phoneOffice);
        }
        officeDao.save(office1);
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

    private Long validatePhone(String a) {
        Long aLong;
        try {
            aLong = Long.parseLong(a);
        } catch (Exception e) {
            throw new OrganisationValidationException("Телефон должен состоять из цифр");
        }
        return aLong;
    }
}

