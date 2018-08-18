package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserViewByOfficeIdRequest;

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

    @Override
    public List<User> getUserByOfficeId(UserViewByOfficeIdRequest userByOfficeId) {
        return loadByCriteriaByOfficeId(userByOfficeId);
    }

    public List<User> loadByCriteriaByOfficeId(UserViewByOfficeIdRequest userByOfficeId) {
        CriteriaQuery<User> criteria = buildCriteriaByOfficeId(userByOfficeId);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<User> buildCriteriaByOfficeId(UserViewByOfficeIdRequest userByOfficeId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        CriteriaBuilder qb = em.getCriteriaBuilder();

        Root<Office> officeRoot = cq.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(qb.equal(officeRoot.get("id"), userByOfficeId.officeId));

        Join<Office, User> join = officeRoot.join("users");

        if (userByOfficeId.firstName != null) {
            predicates.add(qb.equal(join.get("firstName"), userByOfficeId.firstName));
        }
        if (userByOfficeId.secondName != null) {
            predicates.add(qb.equal(join.get("secondName"), userByOfficeId.secondName));
        }
        if (userByOfficeId.middleName != null) {
            predicates.add(qb.equal(join.get("middleName"), userByOfficeId.middleName));
        }
        if (userByOfficeId.position != null) {
            predicates.add(qb.equal(join.get("position"), userByOfficeId.position));
        }
        if (userByOfficeId.docCode != null) {
            predicates.add(qb.equal(join.get("docCode"), userByOfficeId.docCode));
        }
        if (userByOfficeId.citizenshipCode != null) {
            predicates.add(qb.equal(join.get("citizenshipCode"), userByOfficeId.citizenshipCode));
        }

        cq.select(join).where(predicates.toArray(new Predicate[]{}));
        return cq;
    }*/

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


    @Override                                                                       //получить организацию по имени
    public User loadByName(String name) {
        CriteriaQuery<User> criteria = buildCriteria(name);
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getSingleResult();
    }
    private CriteriaQuery<User> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get("Name"), name));
        return criteria;
    }


    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
    public List<User> getAllUser() {
        TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        System.out.println("8888888888888888888888888888888888888888888888f");
        return query.getResultList();
    }


    @Override                                                                       //добавление организаций
    public void save(User user) {
        em.persist(user);
    }

//    @Override
//    public void setOfficeRelationshipNull(Long id) {
//        Office office = em.find(Office.class, id);
//        try {
//            Query query = em.createQuery("SELECT o FROM User o WHERE o.office = :office");
//            query.setParameter("office", office);
//            User userOrgToNull = (User)query.getSingleResult();
//            userOrgToNull.setOffice(null);
//        } catch (Exception e) {
//            System.out.println("на организацию ссылок нет, удаление успешно");
//        }
//    }


    @Override
    public void delete(Long id) {
        Query query = em.createQuery("SELECT o FROM User o WHERE o.id = :id");
        query.setParameter("id", id);
        User userRemove = (User)query.getSingleResult();
        em.remove(userRemove);
    }
}
