package com.practice.belltask.dao.User;

import com.practice.belltask.controller.advice.NotFoundException;
import com.practice.belltask.dao.citizenship.CitizenshipDao;
import com.practice.belltask.dao.document.DocumentDao;
import com.practice.belltask.dao.office.OfficeDao;
import com.practice.belltask.dto.user.UserCreateDto;
import com.practice.belltask.dto.user.UserUpdateDto;
import com.practice.belltask.model.*;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.user.UserIdView;

import com.practice.belltask.view.user.UserListInView;
import com.practice.belltask.view.user.UserListOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;
    private final MapperFacade mapperFacade;


    @Autowired
    public UserDaoImpl(EntityManager em, MapperFacade mapperFacade, OfficeDao officeDao, DocumentDao documentDao, CitizenshipDao citizenshipDao) {
        this.em = em;
        this.mapperFacade = mapperFacade;

    }

    @Override
    public User get(Integer id) {

        User usr = em.find(User.class, id);
        if (usr == null) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return usr;
    }


    @Override
    public List<User> getListUser(Integer officeId, String firstName, String secondName,
                                  String middleName, String position, Integer citizenshipCode, Integer docCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Root<Document> documentRoot = criteriaQuery.from(Document.class);
        criteriaQuery.select(userRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(userRoot.get("office").get("id"), officeId));
        if (firstName != null) {
            predicates.add(cb.equal(userRoot.get("firstName"), firstName));
        }
        if (secondName != null) {
            predicates.add(cb.equal(userRoot.get("secondName"), secondName));
        }
        if (middleName != null) {
            predicates.add(cb.equal(userRoot.get("middleName"), middleName));
        }
        if (position != null) {
            predicates.add(cb.equal(userRoot.get("position"), position));
        }
        if (citizenshipCode != null) {
            predicates.add(cb.equal(userRoot.get("citizenship").get("code"), citizenshipCode));
        }
        if (docCode != null) {
            predicates.add(cb.equal(documentRoot.get("id"), userRoot.get("id")));
            predicates.add(cb.equal(documentRoot.get("typeDocument").get("code"), docCode));
        }
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[]{}))).distinct(true);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();

    }

    @Override
    public void save(User user) {
        if (user.getDocument().getTypeDocument().getCode() != null) {
            TypedQuery<TypeDocument> query2 = em.createQuery("SELECT d FROM TypeDocument d WHERE d.code = ?1", TypeDocument.class);
            query2.setParameter(1, user.getDocument().getTypeDocument().getCode());
            TypeDocument docT;
            try {
                docT = query2.getSingleResult();
                user.getDocument().setTypeDocument(docT);
            } catch (NoResultException e) {
                throw new RuntimeException("Code document not found", e);
            }
        }
        if (user.getCitizenship() != null) {
            TypedQuery<Citizenship> query = em.createQuery("SELECT n FROM Citizenship n WHERE n.code = ?1", Citizenship.class);
            query.setParameter(1, user.getCitizenship().getCode());
            try {
                user.setCitizenship(query.getSingleResult());
            } catch (NoResultException e) {
                throw new RuntimeException("Citizenship code not found", e);
            }
        }

        Office office = em.find(Office.class, user.getOffice().getId());
        if (office == null) {
            throw new RuntimeException("office id not found");
        }
        user.setOffice(office);
        Document doc = user.getDocument();
        user.setDocument(null);
        em.persist(user);
        user.setDocument(doc);
        doc.setUser(user);
        em.persist(user.getDocument());
    }

    @Override
    public void update(User user) {
        User usr = em.find(User.class, user.getId());
        if (usr != null) {
            Office office = em.getReference(Office.class, user.getOffice().getId());
            usr.setOffice(office);
            usr.setFirstName(user.getFirstName());
            usr.setSecondName(user.getSecondName());
            usr.setMiddleName(user.getMiddleName());
            usr.setPosition(user.getPosition());
            usr.setPhone(user.getPhone());
            usr.getDocument().setDocNumber(user.getDocument().getDocNumber());
            usr.getDocument().setDocDate(user.getDocument().getDocDate());
            TypedQuery<TypeDocument> query = em.createQuery("SELECT d FROM TypeDocument d WHERE d.name = ?1", TypeDocument.class);
            query.setParameter(1, user.getDocument().getTypeDocument().getName());
            TypeDocument tDoc;
            try {
                tDoc = query.getSingleResult();
                usr.getDocument().setTypeDocument(tDoc);
            } catch (NoResultException e) {
                throw new RuntimeException("Name docType not found", e);
            }
            TypedQuery<Citizenship> query2 = em.createQuery("SELECT n FROM Citizenship n WHERE n.code = ?1", Citizenship.class);
            query2.setParameter(1, user.getCitizenship().getCode());
            Citizenship citizenship = query2.getSingleResult();
            if (citizenship != null) {
                try {
                    usr.setCitizenship(citizenship);
                } catch (NoResultException e) {
                    throw new RuntimeException("Citizenship code not found", e);
                }
            }
            usr.setIsIdentified(user.getIsIdentified());

        } else {
            throw new EntityNotFoundException("User ID not found");
        }
        em.merge(usr);
    }
}


