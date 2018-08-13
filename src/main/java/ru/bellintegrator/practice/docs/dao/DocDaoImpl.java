package ru.bellintegrator.practice.docs.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.docs.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager em;

    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Doc> getAllDocs() {
        TypedQuery<Doc> query = em.createQuery("SELECT p FROM Doc p", Doc.class);
        return query.getResultList();
    }

    @Override
    public Doc getByName(String name) {
        CriteriaQuery<Doc> criteria = buildCriteria(name);
        TypedQuery<Doc> query = em.createQuery(criteria);
        return query.getSingleResult();
    }
    private CriteriaQuery<Doc> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Doc> criteria = builder.createQuery(Doc.class);

        Root<Doc> docRoot = criteria.from(Doc.class);
        criteria.where(builder.equal(docRoot.get("docName"), name));
        return criteria;
    }
}
