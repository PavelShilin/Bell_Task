package com.practice.belltask.dao.office;

import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeDaoImpl(EntityManager em, OrganizationDao organizationDao) {
        this.em = em;
        this.organizationDao = organizationDao;
    }

    @Override
    public List<Office> filter(Integer orgId, String name, String phone, Boolean isActive) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = cb.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        criteriaQuery.select(officeRoot);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(officeRoot.get("organization"), organizationDao.loadById(orgId)));
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
    public Office loadOfficeById(Integer id) {
        return em.find(Office.class, id);
    }


    @Override
    public void save(Office office, Integer orgId) {
        Office tempOffice = new Office();
        tempOffice.setName(office.getName());
        tempOffice.setAddress(office.getAddress());
        tempOffice.setIsActive(office.getIsActive());
        tempOffice.setOrganization(organizationDao.loadById(orgId));
        em.persist(tempOffice);
    }

    @Override
    public Boolean contains(Integer id) {
        Query query = em.createQuery("Select o FROM Office o WHERE o.id = :id");
        query.setParameter("id", id);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
