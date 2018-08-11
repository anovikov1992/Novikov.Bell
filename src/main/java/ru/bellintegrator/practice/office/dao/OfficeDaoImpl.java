package ru.bellintegrator.practice.office.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em1;

    public OfficeDaoImpl(EntityManager em) {
        this.em1 = em;
    }

    /*
    получить офисы по ID организации
    */
    @Override
    public Office getOfficeByOrgId(Long orgId, String name/*, String phone*/, Boolean isActive) {
        return loadByCriteria(orgId, name/*, phone*/, isActive);
    }

    @Override
    public Office loadByCriteria(Long orgId, String name/*, String phone*/, Boolean isActive) {
        CriteriaQuery<Office> criteria = buildCriteria(orgId, name/*, phone*/, isActive);
        TypedQuery<Office> query = em1.createQuery(criteria);
        return query.getSingleResult();
    }

    private CriteriaQuery<Office> buildCriteria(Long orgId, String name/*, String phone*/, Boolean isActive) {
        CriteriaBuilder builder = em1.getCriteriaBuilder();
        CriteriaQuery<Office> cq = builder.createQuery(Office.class);
        CriteriaBuilder qb = em1.getCriteriaBuilder();

        Root<Office> officeRoot = cq.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();
        System.out.println("--DAO---KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        predicates.add(qb.equal(officeRoot.get("organization"), orgId));
        System.out.println("--DAO-----------------------------------");
        if (name != null) {
            predicates.add(qb.equal(officeRoot.get("name"), name));
            System.out.println("--DAO++++++++++++++++++++++++++++++++++");
        }
      /*  if (phone != null) {
            predicates.add(qb.equal(officeRoot.get("phone"), phone));
        }*/
        if (isActive != null) {
            predicates.add(qb.equal(officeRoot.get("isActive"), isActive));
            System.out.println("--DAO--CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        }
        cq.select(officeRoot).where(predicates.toArray(new Predicate[]{}));
        System.out.println("--DAO--HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

        return cq;
    }

    /*                      ПРИМЕР ИЗ ОРГАНИЗАЦИЙ


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
     */

    @Override
    public Office loadById(Long id) {
        Query query = em1.createQuery("SELECT o FROM Office o WHERE o.id = :id");
        query.setParameter("id", id);
        Office result1 = (Office)query.getSingleResult();
        return result1;
    }

    /*
    добавить офис в список
     */
    @Override
    @Transactional
    public void save(Office office) {
        em1.persist(office);
    }

    /*
    получить список всех офисов
     */
    @Override
    public List<Office> getAllOffice() {
        TypedQuery<Office> query = em1.createQuery("SELECT p FROM Office p", Office.class);
        return query.getResultList();
    }
    
    
    /*
    обнуляем связь офиса с организацией перед удалением
    */
    @Override
    public void setOrganizationRelationshipNull(Long id) {
        Organization org = em1.find(Organization.class, id);
        try {
            Query query = em1.createQuery("SELECT o FROM Office o WHERE o.organization = :organization");
            query.setParameter("organization", org);
            Office officeOrgToNull = (Office)query.getSingleResult();
            officeOrgToNull.setOrganization(null);

        } catch (Exception e) {
            System.out.println("на организацию ссылок нет, удаление успешно");
        }
    }
}
