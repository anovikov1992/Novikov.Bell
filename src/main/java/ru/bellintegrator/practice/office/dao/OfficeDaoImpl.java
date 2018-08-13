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
        predicates.add(qb.equal(officeRoot.get("organization"), orgId));
        if (name != null) {
            predicates.add(qb.equal(officeRoot.get("name"), name));
        }
      /*  if (phone != null) {
            predicates.add(qb.equal(officeRoot.get("phone"), phone));
        }*/
        if (isActive != null) {
            predicates.add(qb.equal(officeRoot.get("isActive"), isActive));
        }
        cq.select(officeRoot).where(predicates.toArray(new Predicate[]{}));
        return cq;
    }

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
