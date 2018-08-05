package ru.bellintegrator.practice.user.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }


    @Override                                                                       //получить организацию по имени
    public User loadByName(String Name) {
        return dao.loadByName(Name);
    }


    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
    @Transactional(readOnly = true)
    public List<UserView> getAllUser() {
        List<User> all = dao.getAllUser();

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
            view.phone = p.getPhone();
            view.docDate = p.getDocDate();
            view.isIdentified = p.getIsIdentified();
            view.office = p.getOffice();
            view.doc = p.getDoc();
            view.country = p.getCountry();
            return view;
        };
    }


    @Override                                                                       //обновить данные организации
    public void update(UserView user) throws Exception {
        validate(user);
        User org = dao.findById(user.id);
        if (org == null) {
            throw new NotFoundException("organization not found");
        }
      /*  org.setFirstname(user.firstname);
        org.setPosition(user.position);
        org.setPhone(user.phone);*/
    }
    private void validate(UserView organization) throws Exception {
        if (organization.id ==  null/* || organization.firstname == null*/){
            throw new Exception("field is null");
        }
    }


    @Override                                                                       //добавить организацию
    public void add(UserView view) {
        User user = new User(/*ввести все поля*/);
        dao.save(user);
    }
}
