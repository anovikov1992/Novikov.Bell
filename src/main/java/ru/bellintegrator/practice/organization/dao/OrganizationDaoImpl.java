package ru.bellintegrator.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewLoadById;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
    public List<Organization> getAllOrganization() {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        return query.getResultList();
    }

    /*
    получить организацию по имени
    */
    @Override
    public Organization getOrganizationByName(String name, Long inn, Boolean isActive) {
        return loadByCriteria(name, inn, isActive);
    }

    @Override
    public Organization loadByCriteria(String name, Long inn, Boolean isActive) {
        CriteriaQuery<Organization> criteria = buildCriteria(name, inn, isActive);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    private CriteriaQuery<Organization> buildCriteria(String name, Long inn, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> cq = builder.createQuery(Organization.class);
        CriteriaBuilder qb = em.getCriteriaBuilder();

        Root<Organization> organizationRoot = cq.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(qb.equal(organizationRoot.get("name"), name));
        if (inn != null) {
            predicates.add(qb.equal(organizationRoot.get("inn"), inn));
        }

        if (isActive != null) {
            predicates.add(qb.equal(organizationRoot.get("isActive"), isActive));
        }
        cq.select(organizationRoot).where(predicates.toArray(new Predicate[]{}));

        return cq;
    }


    @Override                                                                       //получить организацию по ID
    public Organization loadById(Long id) {
            Query query = em.createQuery("SELECT o FROM Organization o WHERE o.id = :id");
            query.setParameter("id", id);
            Organization result1 = (Organization)query.getSingleResult();
            return result1;
    }


    @Override                                                                       //добавление организаций
    public void save(Organization organization) {
        em.persist(organization);
    }


    @Override
    public Organization loadByIdCriteria(Long id) {
        CriteriaQuery<Organization> criteria = buildCriteria(id);
        TypedQuery<Organization> query1 = em.createQuery(criteria);
        return query1.getSingleResult();
    }
    private CriteriaQuery<Organization> buildCriteria(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> person = criteria.from(Organization.class);
        criteria.where(builder.equal(person.get("id"), id));

        return criteria;
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("SELECT o FROM Organization o WHERE o.id = :id");
        query.setParameter("id", id);
        Organization orgRemove = (Organization)query.getSingleResult();
        em.remove(orgRemove);

    }
}
