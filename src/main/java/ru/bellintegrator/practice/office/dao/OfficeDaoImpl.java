package ru.bellintegrator.practice.office.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.EntityManager;
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
}
