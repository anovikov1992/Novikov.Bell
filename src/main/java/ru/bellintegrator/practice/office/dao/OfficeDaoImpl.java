package ru.bellintegrator.practice.office.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeViewRequest;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /*
    получить офисы по ID организации
    */
    @Override
    public List<Office> getOfficeByOrgId(OfficeViewRequest officeViewRequest) {
        return loadByCriteria(officeViewRequest);
    }

    public List<Office> loadByCriteria(OfficeViewRequest officeViewRequest) {
        CriteriaQuery<Office> criteria = buildCriteria(officeViewRequest.orgId, officeViewRequest.name,
                                            officeViewRequest.phoneOffice, officeViewRequest.isActive);
        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<Office> buildCriteria(Long orgId, String name, String phoneOffice, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = builder.createQuery(Office.class);
        CriteriaBuilder qb = em.getCriteriaBuilder();

        Root<Organization> organizationRoot = cq.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(qb.equal(organizationRoot.get("id"), orgId));

        Join<Organization, Office> join = organizationRoot.join("offices");

        if (name != null) {
            predicates.add(qb.equal(join.get("name"), name));
        }
        if (phoneOffice != null) {
            predicates.add(qb.equal(join.get("phoneOffice"), phoneOffice));
        }
        if (isActive != null) {
            predicates.add(qb.equal(join.get("isActive"), isActive));
        }
        cq.select(join).where(predicates.toArray(new Predicate[]{}));
        return cq;
    }

    /*
    получить офис по ID
    */
    @Override
    public Office findById(Long id) {
        Query query = em.createQuery("SELECT o FROM Office o WHERE o.id = :id");
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
        em.persist(office);
    }

    /*
    получить список всех офисов
     */
    @Override
    public List<Office> getAllOffice() {
        TypedQuery<Office> query = em.createQuery("SELECT p FROM Office p", Office.class);
        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("SELECT o FROM Office o WHERE o.id = :id");
        query.setParameter("id", id);
        Office officeRemove = (Office)query.getSingleResult();
        em.remove(officeRemove);
    }

//    /*
//    обнуляем связь офиса с организацией перед удалением организации
//    */
//    @Override
//    public void setOrganizationRelationshipNull(Long id) {
//        Organization org = em.find(Organization.class, id);
//        try {
//            Query query = em.createQuery("SELECT o FROM Office o WHERE o.organization = :organization");
//            query.setParameter("organization", org);
//            Office officeOrgToNull = (Office)query.getSingleResult();
//            System.out.println("нашли организации - " + officeOrgToNull);
//            officeOrgToNull.setOrganization(null);
//        } catch (Exception e) {
//            System.out.println("на организацию ссылок нет, удаление успешно");
//        }
//    }
}
