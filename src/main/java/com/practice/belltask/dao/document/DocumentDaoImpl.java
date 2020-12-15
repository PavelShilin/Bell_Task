
package com.practice.belltask.dao.document;

import com.practice.belltask.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DocumentDaoImpl implements DocumentDao {

    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Document getById(Integer id) {
        return em.find(Document.class, id);
    }
}

