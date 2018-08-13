package ru.bellintegrator.practice.user.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.dao.DocDao;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.organization.MyException.OrgOutException;
import ru.bellintegrator.practice.organization.MyException.OrganisationValidationException;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final DocDao docDao;
    private final CountryDao countryDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, DocDao docDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.docDao = docDao;
        this.countryDao = countryDao;
    }

    /*
    получить пользователя по ID офиса

    @Override
    public List<UserViewByOfficeIdResponse> getUserByOfficeId(UserViewByOfficeIdRequest userByOfficeId) {
        if (userByOfficeId.docCode != null) {
            // Находим документ в БД Doc по docName
            try {
                docDao.getByName(userByOfficeId.docCode.toString());
            } catch (Exception e) {
                throw new OrganisationValidationException("Документа с таким docName нет в БД Doc. Чтобы " +
                        "присвоить этот документ данному пользователю, его (документ) необходимо сначала добавить в БД Doc.");
            }
        }
        List<User> userList;
        try {
            userList = userDao.getUserByOfficeId(userByOfficeId);
        } catch (Exception e) {
            throw new OrgOutException("Организации с такой комбинацией параметров нет");
        }
        return userList.stream().map(elem -> new UserViewByOfficeIdResponse(elem.getId(), elem.getFirstName(), elem.getMiddleName(), elem.getSecondName(), elem.getPosition())).collect(Collectors.toList());
    }*/

    /*
получить пользователя по ID офиса
*/
    @Override
    public List<UserViewByOfficeIdResponse> getUserByOfficeId(Long officeId, String firstName, String middleName,
                                                              String secondName, String position, String docCode,
                                                              String country) {
        List<User> userList;
        try {
            userList = userDao.getUserByOfficeId(officeId, firstName, middleName,
                    secondName, position, docCode, country);
        } catch (Exception e) {
            throw new OrgOutException("Пользователей с такой комбинацией параметров нет");
        }
        return userList.stream().map(elem -> new UserViewByOfficeIdResponse(elem.getId(), elem.getFirstName(), elem.getMiddleName(), elem.getSecondName(), elem.getPosition())).collect(Collectors.toList());
    }
    /*
                                ПРИМЕР ИЗ ОФИСОВ
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
        return officeList.stream().map(elem -> new OfficeView(elem.getId(), elem.getName(), elem.getIsActive())).collect(Collectors.toList());elem.getId(), elem.getFirstName(), elem.getSecondName(), elem.getPosition()
    }
     */

    /*
        получить пользователя по ID
        */
    @Override
    public UserViewLoadById loadById(Long id) {
        UserViewLoadById userViewLoadById = new UserViewLoadById();
        try{
            User user = userDao.loadById(id);
            userViewLoadById.id = user.getId();
            userViewLoadById.firstName = user.getFirstName();
            userViewLoadById.middleName = user.getMiddleName();
            userViewLoadById.secondName = user.getSecondName();
            userViewLoadById.position = user.getPosition();
            userViewLoadById.phoneUser = user.getPhoneUser().toString();
            userViewLoadById.docNumber = user.getDocNumber().toString();
            if (user.getDoc() != null){
                userViewLoadById.docName = user.getDoc().getDocName();
            } else {
                userViewLoadById.docName = null;
            }
            userViewLoadById.docDate = user.getDocDate();
            if (user.getCountry() != null) {
                userViewLoadById.citizenshipName = user.getCountry().getCountryName();
                userViewLoadById.citizenshipCode = user.getCountry().getCitizenshipCode();
            } else {
                userViewLoadById.citizenshipName = null;
                userViewLoadById.citizenshipCode = null;
            }
            userViewLoadById.isIdentified = user.getIdentified();
        }catch (Exception e){
            throw new OrgOutException("Пользователя с таким ID нет в базе данных");
        }
        return userViewLoadById;
    }

    /*
    обновить данные пользователя
    */
    @Override
    public void update(UserViewUpdate userViewUpdate) {
        if (userViewUpdate.id == null) {
            throw new OrganisationValidationException("Поле ID является обязательным");
        }
        if (userViewUpdate.firstName == null) {
            throw new OrganisationValidationException("Поле firstName является обязательным");
        }
        if (userViewUpdate.position == null) {
            throw new OrganisationValidationException("Поле position является обязательным");
        }
        User user;
        try {
           user = userDao.loadById(userViewUpdate.id);
        } catch (Exception e) {
            throw new OrgOutException("Пользователя с таким ID нет в базе данных");
        }
        user.setFirstName(userViewUpdate.firstName);
        if (userViewUpdate.secondName != null) {
            user.setSecondName(userViewUpdate.secondName);
        }
        if (userViewUpdate.middleName != null) {
            user.setMiddleName(userViewUpdate.middleName);
        }
        user.setPosition(userViewUpdate.position);
        if (userViewUpdate.phoneUser != null) {
            user.setPhoneUser(validatePhone(userViewUpdate.phoneUser));
        }
        if (userViewUpdate.docName != null) {
            // Находим документ в БД Doc по docName
            Doc doc;
            try {
                doc = docDao.getByName(userViewUpdate.docName);
            } catch (Exception e) {
                throw new OrganisationValidationException("Документа с таким docName нет в БД Doc. Чтобы " +
                        "присвоить этот документ данному пользователю, его (документ) необходимо сначала добавить в БД Doc.");
            }
            // Присваим документ пользователю
            if (doc != null)
            {
                user.setDoc(doc);
            }
        }
        if (userViewUpdate.docNumber != null) {
            user.setDocNumber(validateDocNumber(userViewUpdate.docNumber));
        }
        if (userViewUpdate.docDate != null) {
            user.setDocDate(userViewUpdate.docDate);
        }
        if (userViewUpdate.citizenshipCode != null) {
            // Находим страну в БД Country по citizenshipCode
            Country country;
            try {
                country = countryDao.getByCitizenshipCode(userViewUpdate.citizenshipCode);
            } catch (Exception e) {
                throw new OrganisationValidationException("Страны с таким citizenshipCode нет в БД Country. Чтобы " +
                        "присвоить этот citizenshipCode данному пользователю, его (citizenshipCode) необходимо сначала добавить в БД Country.");
            }
            // Присваим страну пользователю
            if (country != null)
            {
                user.setCountry(country);
            }
        }

        if (userViewUpdate.isIdentified != null) {
            user.setIdentified(userViewUpdate.isIdentified);
        }
    }

    /*
    добавить нового пользователя
    */
    @Override
    public void add(UserViewSave userViewSave) {
        User user = new User();
        if (userViewSave.firstName == null) {
            throw new OrganisationValidationException("Поле firstName является обязательным");
        }
        if (userViewSave.position == null) {
            throw new OrganisationValidationException("Поле position является обязательным");
        }
        if (userViewSave.docName != null) {
            // Находим документ в БД Doc по docName
            Doc doc;
            try {
                doc = docDao.getByName(userViewSave.docName);
            } catch (Exception e) {
                throw new OrganisationValidationException("Документа с таким docName нет в БД Doc. Чтобы " +
                        "присвоить этот документ данному пользователю, его (документ) необходимо сначала добавить в БД Doc.");
            }
            // Присваим документ пользователю
            if (doc != null)
            {
                user.setDoc(doc);
            }
        }
        if (userViewSave.citizenshipCode != null) {
            // Находим страну в БД Country по citizenshipCode
            Country country;
            try {
                country = countryDao.getByCitizenshipCode(userViewSave.citizenshipCode);
            } catch (Exception e) {
                throw new OrganisationValidationException("Страны с таким citizenshipCode нет в БД Country. Чтобы " +
                        "присвоить этот citizenshipCode данному пользователю, его (citizenshipCode) необходимо сначала добавить в БД Country.");
            }
            // Присваим страну пользователю
            if (country != null)
            {
                user.setCountry(country);
            }
        }
        if (userViewSave.secondName != null) {
            user.setSecondName(userViewSave.secondName);
        }
        if (userViewSave.middleName != null) {
            user.setMiddleName(userViewSave.middleName);
        }
        if (userViewSave.phoneUser != null) {
            user.setPhoneUser(validatePhone(userViewSave.phoneUser));
        }
 // попробуем без указания docCode
        if (userViewSave.docNumber != null) {
            user.setDocNumber(validateDocNumber(userViewSave.docNumber));
        }
        if (userViewSave.docDate != null) {
            user.setDocDate(userViewSave.docDate);
        }
        if (userViewSave.isIdentified != null) {
            user.setIdentified(userViewSave.isIdentified);
        }
        userDao.save(user);
    }

    /*
    получить весь список пользователей
    */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> getAllUser() {
        List<User> all = userDao.getAllUser();

        return all.stream()
                .map(mapUser())
                .collect(Collectors.toList());
    }
    private Function<User, UserView> mapUser() {
        return p -> {

            UserView view = new UserView();
            view.id = Long.valueOf(p.getId());
            view.firstName = p.getFirstName();
            view.middleName = p.getMiddleName();
            view.secondName = p.getSecondName();
            view.position = p.getPosition();
            view.phoneUser = p.getPhoneUser();
            view.docDate = p.getDocDate();
            view.isIdentified = p.getIdentified();
       //     view.office = p.getOffice();
            view.doc = p.getDoc();
        //    view.country = p.getCountry();
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

    private Long validateDocNumber(String a) {
        Long aLong;
        try {
            aLong = Long.parseLong(a);
        } catch (Exception e) {
            throw new OrganisationValidationException("Номер докумнета должен состоять из цифр");
        }
        return aLong;
    }
}
