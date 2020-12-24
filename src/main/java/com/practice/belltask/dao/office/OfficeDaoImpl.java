package com.practice.belltask.dao.office;

import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.dto.office.OfficeUpdateDto;
import com.practice.belltask.model.Office;
import com.practice.belltask.model.Organization;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
    public void save(Office office) {
        Organization o = em.getReference(Organization.class, office.getOrganization().getId());
        office.setOrganization(o);
        em.persist(office);
    }

    @Override
    public void update(Office office) {
        Office updateOffice = em.find(Office.class, office.getId());
        if (updateOffice == null) {
            throw new EntityNotFoundException("Office ID not found");
        } else {
            updateOffice.setId(office.getId());
            updateOffice.setName(office.getName());
            updateOffice.setAddress(office.getAddress());
            updateOffice.setPhone(office.getPhone());
            updateOffice.setIsActive(office.getIsActive());
        }
    }

    @Override
    public Boolean contains(Integer id) {
        Query query = em.createQuery("Select o FROM Office o WHERE o.id = :id");
        query.setParameter("id", id);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
