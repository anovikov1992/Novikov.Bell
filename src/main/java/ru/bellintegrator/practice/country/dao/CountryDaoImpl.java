package ru.bellintegrator.practice.country.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public Country getByCitizenshipCode(String citizenshipCode) {
            CriteriaQuery<Country> criteria = buildCriteria(citizenshipCode);
            TypedQuery<Country> query = em.createQuery(criteria);
            return query.getSingleResult();
        }
        private CriteriaQuery<Country> buildCriteria(String citizenshipCode) {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

            Root<Country> countryRoot = criteria.from(Country.class);
            criteria.where(builder.equal(countryRoot.get("citizenshipCode"), citizenshipCode));
            return criteria;
        }
}

