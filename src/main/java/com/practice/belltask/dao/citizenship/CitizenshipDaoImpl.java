package com.practice.belltask.dao.citizenship;

import com.practice.belltask.model.Citizenship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CitizenshipDaoImpl implements CitizenshipDao {
    final EntityManager em;

    @Autowired
    public CitizenshipDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public Citizenship getCitizenshipByCode(Integer code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Citizenship> criteria = builder.createQuery(Citizenship.class);
        Root<Citizenship> person = criteria.from(Citizenship.class);
        criteria.where(builder.equal(person.get("code"), code));
        TypedQuery<Citizenship> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public Boolean contains(Integer code) {
        Query query = em.createQuery("Select o FROM Citizenship o WHERE o.code = :code");
        query.setParameter("code", code);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
