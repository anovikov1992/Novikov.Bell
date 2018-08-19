package ru.bellintegrator.practice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.dao.DocDao;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.my.exception.OrgOutException;
import ru.bellintegrator.practice.organization.my.exception.OrganisationValidationException;
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
    private final OfficeDao officeDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, DocDao docDao, CountryDao countryDao, OfficeDao officeDao) {
        this.userDao = userDao;
        this.docDao = docDao;
        this.countryDao = countryDao;
        this.officeDao = officeDao;
    }

    /*
    получить список пользователей по ID офиса
    */
    @Override
    public List<UserViewByOfficeIdResponse> getUserByOfficeId(Long officeId, String firstName, String middleName,
                                                              String secondName, String position, String docCode,
                                                              String country) {
        List<User> userList;
        try {
            if (docCode != null) {
                validateDocCode(docCode);
            }
            userList = userDao.getUserByOfficeId(officeId, firstName, middleName,
                                                secondName, position, docCode, country);
        } catch (OrgOutException e) {
            throw new OrgOutException("Пользователей с такой комбинацией параметров нет");
        }
        return userList.stream().map(elem -> new UserViewByOfficeIdResponse(elem.getId(), elem.getFirstName(), elem.getMiddleName(), elem.getSecondName(), elem.getPosition())).collect(Collectors.toList());
    }

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
            if (user.getPhoneUser() != null) {
            userViewLoadById.phoneUser = user.getPhoneUser().toString();
            }
            if (user.getDocNumber() != null) {
                userViewLoadById.docNumber = user.getDocNumber().toString();
            }
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
        }catch (EmptyResultDataAccessException e){
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
        } catch (OrgOutException e) {
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
            } catch (OrganisationValidationException e) {
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
            } catch (OrganisationValidationException e) {
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
        user.setFirstName(userViewSave.firstName);
        if (userViewSave.position == null) {
            throw new OrganisationValidationException("Поле position является обязательным");
        }
        user.setPosition(userViewSave.position);
        if (userViewSave.docName != null) {
            // Находим документ в БД Doc по docName
            Doc doc;
            try {
                doc = docDao.getByName(userViewSave.docName);
            } catch (EmptyResultDataAccessException e) {
                throw new OrganisationValidationException("Документа с таким docName нет в БД Doc. Чтобы " +
                        "присвоить этот документ данному пользователю, его (документ) необходимо сначала добавить в БД Doc.");
            }
            // Присваим документ пользователю
            if (doc != null)
            {
                System.out.println(doc);
                user.setDoc(doc);
                System.out.println(user.getDoc());
            }
        }
        if (userViewSave.citizenshipCode != null) {
            // Находим страну в БД Country по citizenshipCode
            Country country;
            try {
                country = countryDao.getByCitizenshipCode(userViewSave.citizenshipCode);
            } catch (EmptyResultDataAccessException e) {
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
        if (userViewSave.docNumber != null) {
            user.setDocNumber(validateDocNumber(userViewSave.docNumber));
        }
        if (userViewSave.docDate != null) {
            user.setDocDate(userViewSave.docDate);
        }
        if (userViewSave.isIdentified != null) {
            user.setIdentified(userViewSave.isIdentified);
        }

        if (userViewSave.officeId != null) {
            try {
                Office office = officeDao.findById(userViewSave.officeId);
                office.addUser(user);
            } catch (EmptyResultDataAccessException e) {
                throw new OrganisationValidationException("Невозможно привязать пользователя у казанному officeId, т.к. офиса" +
                        " с таким ID нет в БД");
            }
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
            return view;
        };
    }

    /*
    удалить пользователя по ID
    */

    @Override
    public void delete(Long id) {
        try {
            User user = userDao.loadById(id);
            if (user.getOffice() != null) {
                user.getOffice().removeUser(user);
            }
            userDao.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrganisationValidationException("Пользователя с таким ID нет в БД");
        }
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

    private Long validateDocNumber(String a) {
        Long aLong;
        try {
            aLong = Long.parseLong(a);
        } catch (NumberFormatException e) {
            throw new OrganisationValidationException("Номер докумнета должен состоять из цифр");
        }
        return aLong;
    }

    private void validateDocCode(String a) {
        try {
            Long aLong = Long.parseLong(a);
        } catch (NumberFormatException e) {
            throw new OrganisationValidationException("Код документа должен состоять из цифр");
        }
    }
}
