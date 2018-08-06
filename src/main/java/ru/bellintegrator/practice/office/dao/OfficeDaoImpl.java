package ru.bellintegrator.practice.office.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> getAllOffice() {
        TypedQuery<Office> query = em.createQuery("SELECT p FROM Office p", Office.class);
        return query.getResultList();
    }

    @Override
    public void setOrganizationNull(Long id) {                                      // обнуляем связь офиса с организацией перед удалением
        Organization org = em.find(Organization.class, id);
        try {
            Query query = em.createQuery("SELECT o FROM Office o WHERE o.organization = :organization");
            query.setParameter("organization", org);
            Office officeOrgToNull = (Office)query.getSingleResult();
            officeOrgToNull.setOrganization(null);

        } catch (Exception e) {
            System.out.println("на организацию ссылок нет, удаление успешно");
        }
    }
}
