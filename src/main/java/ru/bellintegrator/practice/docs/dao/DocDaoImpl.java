package ru.bellintegrator.practice.docs.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.docs.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
}
