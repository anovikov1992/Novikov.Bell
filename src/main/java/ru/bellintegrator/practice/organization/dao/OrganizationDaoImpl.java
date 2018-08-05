package ru.bellintegrator.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationViewLoadById;

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
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

   /* @Override                                                                       //получить организацию по имени
    public Organization loadByName(String name) {
        CriteriaQuery<Organization> criteria = buildCriteria(name);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult();
    }*/



    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
    public List<Organization> getAllOrganization() {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        return query.getResultList();
    }


    @Override                                                                       //получить организацию по имени
    public Organization getOrganizationByName(String name, Long inn, boolean isActive) {
        Organization result1 = new Organization(name, inn, isActive);
        if (result1.getInn() == null && result1.getIsActive() ){
            Query query = em.createQuery("SELECT o FROM Organization o WHERE o.name = :name AND o.isActive = :isActive");
            query.setParameter("name", name);
            //query.setParameter("inn", inn);
            query.setParameter("isActive", isActive);  /*  o.inn = :inn o.isActive = :isActive*/
            result1 = (Organization)query.getSingleResult();
        }    else if (result1.getInn() == null ){
            Query query = em.createQuery("SELECT o FROM Organization o WHERE o.name = :name");
            query.setParameter("name", name);
            //query.setParameter("inn", inn);
            //query.setParameter("isActive", isActive);  /*  o.inn = :inn o.isActive = :isActive*/
            result1 = (Organization)query.getSingleResult();
        }
        if (inn != null && isActive){
            Query query = em.createQuery("SELECT o FROM Organization o WHERE o.name = :name AND o.inn = :inn AND o.isActive = :isActive ");
            query.setParameter("name", name);
            query.setParameter("inn", inn);
            query.setParameter("isActive", isActive);  /*  o.inn = :inn o.isActive = :isActive*/
            result1 = (Organization)query.getSingleResult();
        }    else if (inn != null){
        Query query = em.createQuery("SELECT o FROM Organization o WHERE o.name = :name AND o.inn = :inn");
        query.setParameter("name", name);
        query.setParameter("inn", inn);
        //query.setParameter("isActive", isActive);  /*  o.inn = :inn o.isActive = :isActive*/
        result1 = (Organization)query.getSingleResult();
    }
        return result1;
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
}
