package ru.bellintegrator.practice.country.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.dao.DocDao;
import ru.bellintegrator.practice.docs.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Country> getAllCountry() {
        TypedQuery<Country> query = em.createQuery("SELECT p FROM Country p", Country.class);
        return query.getResultList();
    }
}
