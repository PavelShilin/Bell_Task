package com.practice.belltask.dao.citizenship;

import com.practice.belltask.model.Citizenship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
}
