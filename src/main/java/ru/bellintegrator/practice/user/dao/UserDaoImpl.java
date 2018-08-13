package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        Root<User> organization = criteria.from(User.class);
        criteria.where(builder.equal(organization.get("Name"), name));
        return criteria;
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
}
