package ru.bellintegrator.practice.user.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.MyException.OrgOutException;
import ru.bellintegrator.practice.organization.MyException.OrganisationValidationException;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewLoadById;
import ru.bellintegrator.practice.user.view.UserViewSave;
import ru.bellintegrator.practice.user.view.UserViewUpdate;

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

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
    получить организацию по ID
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

        // сделать с docName

        if (userViewUpdate.docNumber != null) {
            user.setDocNumber(validateDocNumber(userViewUpdate.docNumber));
        }
        if (userViewUpdate.docDate != null) {
            user.setDocDate(userViewUpdate.docDate);
        }
        if (userViewUpdate.citizenshipCode != null) {
            user.getCountry().setCitizenshipCode(userViewUpdate.citizenshipCode);
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
        //String firstName, String middleName, String secondName, String position, Long phoneUser,
        //                Doc doc, Date docDate, Long docNumber, Country country, Boolean isIdentified
        User user = new User(userViewSave.firstName, userViewSave.middleName);
/*        if (organization.phone == null) {
            ogr = new Organization  (organization.name, organization.fullName, validate(organization.inn),
                    validateKppNumberLength(organization.kpp),  organization.urAddress,  organization.isActive);
        }
        else {
            ogr = new Organization (organization.name, organization.fullName, validate(organization.inn),
                    validateKppNumberLength(organization.kpp),  organization.urAddress,
                    phoneToLong(organization.phone), organization.isActive);
        }*/
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
