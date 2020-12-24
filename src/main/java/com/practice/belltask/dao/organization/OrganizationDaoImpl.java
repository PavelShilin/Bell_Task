package com.practice.belltask.dao.organization;

import com.practice.belltask.dto.organization.OrganizationUpdateDto;
import com.practice.belltask.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.ArrayList;
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
        CriteriaQuery<Organization> criteria = buildCriteriaByName(name);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Organization> buildCriteria(String name, Long inn, Boolean is_status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(organizationRoot.get("name"), name));
        if (inn != null) {
            predicates.add(cb.equal(organizationRoot.get("inn"), inn));
        }
        if (is_status != null) {
            predicates.add(cb.equal(organizationRoot.get("isActive"), is_status));
        }
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[]{})));
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void update(Organization organization) {
        Organization org = em.find(Organization.class, organization.getId());
        org.setAddress(organization.getAddress());
        org.setName(organization.getName());
        org.setAddress(organization.getAddress());
        org.setFullName(organization.getFullName());
        org.setInn(organization.getInn());
        org.setKpp(organization.getKpp());
        org.setPhone(organization.getPhone());
        org.setStatus(organization.getIsActive());
    }

    private CriteriaQuery<Organization> buildCriteriaByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);
        criteria.where(builder.equal(organizationRoot.get("name"), name));
        return criteria;
    }

}
