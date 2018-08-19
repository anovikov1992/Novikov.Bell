package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /*
    получить пользователей по ID офиса
    */
    @Override
    public List<User> getUserByOfficeId(Long officeId, String firstName, String middleName,
                                        String secondName, String position, String docCode,
                                        String country) {
        return loadByCriteriaByOfficeId(officeId, firstName, middleName,
                secondName, position, docCode, country);
    }

    public List<User> loadByCriteriaByOfficeId(Long officeId, String firstName, String middleName,
                                               String secondName, String position, String docCode,
                                               String country) {
        CriteriaQuery<User> criteria = buildCriteriaByOfficeId(officeId, firstName, middleName, secondName, position, docCode, country);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<User> buildCriteriaByOfficeId(Long officeId, String firstName, String middleName,
                                                        String secondName, String position, String docCode,
                                                        String country) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        CriteriaBuilder qb = em.getCriteriaBuilder();

        Root<Office> officeRoot = cq.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(qb.equal(officeRoot.get("id"), officeId));

        Join<Office, User> officeUserJoin = officeRoot.join("users");

        if (firstName != null) {
            predicates.add(qb.equal(officeUserJoin.get("firstName"), firstName));
        }
        if (secondName != null) {
            predicates.add(qb.equal(officeUserJoin.get("secondName"), secondName));
        }
        if (middleName != null) {
            predicates.add(qb.equal(officeUserJoin.get("middleName"), middleName));
        }
        if (position != null) {
            predicates.add(qb.equal(officeUserJoin.get("position"), position));
        }
        if (docCode != null) {
            Join<Doc, User> docUserJoin = officeUserJoin.join("doc");
            predicates.add(qb.equal(docUserJoin.get("docCode"), docCode));
        }
        if (country != null) {
            Join<Country, User> countryUserJoin = officeUserJoin.join("country");
            predicates.add(qb.equal(countryUserJoin.get("citizenshipCode"), country));
        }

        cq.select(officeUserJoin).where(predicates.toArray(new Predicate[]{}));
        return cq;
    }

    /*
    получить пользователя по ID
    */
    @Override
    public User loadById(Long id) {
            Query query = em.createQuery("SELECT o FROM User o WHERE o.id = :id");
            query.setParameter("id", id);
            User result1 = (User)query.getSingleResult();
            return result1;
        }

    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
    public List<User> getAllUser() {
        TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }


    @Override                                                                       //добавление организаций
    public void save(User user) {
        em.persist(user);
    }



    @Override
    public void delete(Long id) {
        Query query = em.createQuery("SELECT o FROM User o WHERE o.id = :id");
        query.setParameter("id", id);
        User userRemove = (User)query.getSingleResult();
        em.remove(userRemove);
    }
}
