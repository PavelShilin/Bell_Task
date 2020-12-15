
package com.practice.belltask.dao.document;

import com.practice.belltask.controller.advice.NotFoundException;
import com.practice.belltask.model.Citizenship;
import com.practice.belltask.model.Document;
import com.practice.belltask.model.TypeDocument;
import com.practice.belltask.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;

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

    @Transactional
    @Override
    public void createDocument(Integer id, String number, Date date, String name, Integer code) {
        Document tempDocument = new Document();
        tempDocument.setId(id);
        tempDocument.setUser(em.find(User.class, id));
        tempDocument.setDocNumber(number);
        tempDocument.setDocDate(date);
        if ((name == null && code != null) || (name != null && code != null)) {
            tempDocument.setTypeDocument(getTypeDocumentByCode(code));
        }
        if (name != null && code == null) {
            tempDocument.setTypeDocument(getTypeDocumentByName(name));
        }
        if (name == null && code != null) {
            tempDocument.setTypeDocument(getTypeDocumentByCode(code));
        }
        em.persist(tempDocument);
    }

    @Override
    public TypeDocument getTypeDocumentByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TypeDocument> criteria = builder.createQuery(TypeDocument.class);
        Root<TypeDocument> person = criteria.from(TypeDocument.class);
        criteria.where(builder.equal(person.get("nameTypeDocument"), name));
        TypedQuery<TypeDocument> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    public TypeDocument getTypeDocumentByCode(Integer code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TypeDocument> criteria = builder.createQuery(TypeDocument.class);
        Root<TypeDocument> person = criteria.from(TypeDocument.class);
        criteria.where(builder.equal(person.get("codeTypeDocument"), code));
        TypedQuery<TypeDocument> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

}

