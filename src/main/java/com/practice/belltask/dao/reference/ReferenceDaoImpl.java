package com.practice.belltask.dao.reference;

import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.dto.reference.CitizenshipDto;
import com.practice.belltask.dto.reference.DocumentDto;
import com.practice.belltask.model.Citizenship;
import com.practice.belltask.model.TypeDocument;
import com.practice.belltask.model.mapper.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReferenceDaoImpl implements ReferenceDao {

    private final EntityManager em;
    private final MapperFacade mapperFacade;

    @Autowired
    public ReferenceDaoImpl(EntityManager em, OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.em = em;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<DocumentDto> getListDocument() {
        TypedQuery<TypeDocument> query = em.createQuery("SELECT p FROM TypeDocument p", TypeDocument.class);
        return mapperFacade.mapAsList(query.getResultList(), DocumentDto.class);
    }

    @Override
    public List<CitizenshipDto> getListCitizenship() {
        TypedQuery<Citizenship> query = em.createQuery("SELECT p FROM Citizenship p", Citizenship.class);
        return mapperFacade.mapAsList(query.getResultList(), CitizenshipDto.class);
    }
}
