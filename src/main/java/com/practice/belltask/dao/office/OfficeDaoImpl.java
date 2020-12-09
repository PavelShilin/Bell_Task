package com.practice.belltask.dao.office;

import com.practice.belltask.model.Office;
import com.practice.belltask.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> filter(Integer orgId, String name, String phone, Boolean isActive) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = cb.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        criteriaQuery.select(officeRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(officeRoot.get("organization.id"), orgId));
        if (name != null) {
            predicates.add(cb.equal(officeRoot.get("name"), name));
        }
        if (phone != null) {
            predicates.add(cb.equal(officeRoot.get("phone"), phone));
        }
        if (isActive != null) {
            predicates.add(cb.equal(officeRoot.get("isActive"), isActive));
        }
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Office loadById(Integer id) {
        return em.find(Office.class, id);
    }


}
