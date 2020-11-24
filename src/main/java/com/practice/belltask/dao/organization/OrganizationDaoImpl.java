package com.practice.belltask.dao.organization;

import com.practice.belltask.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;

/**
 * {@inheritDoc}
 */

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    /**
     * {@inheritDoc}
     */

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Integer id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public List<Organization> loadByName(String name) {
        CriteriaQuery<Organization> criteria = buildCriteria(name);
        TypedQuery<Organization> query = em.createQuery(criteria);
        //return query.getSingleResult();
        return query.getResultList();
    }

    @Override
    public List<Organization> buildCriteria(String name, Long inn, Boolean is_status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);
        Predicate cond = null;
        if(inn != null && is_status != null ) {
            cond = cb.and(cb.equal(organizationRoot.get("name"), name), cb.equal(organizationRoot.get("inn"), inn),cb.equal(organizationRoot.get("status"), is_status));
        }
        if (inn != null && is_status == null){
            cond = cb.and(cb.equal(organizationRoot.get("name"), name), cb.equal(organizationRoot.get("inn"), inn));
        }
        if (inn == null && is_status != null){
            cond = cb.and(cb.equal(organizationRoot.get("name"), name), cb.equal(organizationRoot.get("status"), is_status));
        }
        if (inn == null && is_status == null){
            cond = cb.equal(organizationRoot.get("name"), name);
        }
        // criteria.where(cb.equal(organizationRoot.get("name"), name));
        CriteriaQuery<Organization> cq = criteria.select(organizationRoot).where(cond);
        TypedQuery typedQuery = em.createQuery(cq);
        return (List<Organization>) typedQuery.getResultList();
    }

    private CriteriaQuery<Organization> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> organizationRoot = criteria.from(Organization.class);
        criteria.where(builder.equal(organizationRoot.get("name"), name));

        return criteria;
    }




}
