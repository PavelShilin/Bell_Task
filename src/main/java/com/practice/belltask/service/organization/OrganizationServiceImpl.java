package com.practice.belltask.service.organization;
import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.dto.organization.OrganizationSaveDto;
import com.practice.belltask.dto.organization.OrganizationUpdateDto;
import com.practice.belltask.model.Organization;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.organization.OrganizationIdView;
import com.practice.belltask.view.organization.OrganizationListInView;
import com.practice.belltask.view.organization.OrganizationListOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OrganizationListOutView> filter(OrganizationListInView filter) {
        List<Organization> all = dao.buildCriteria(filter.name, filter.inn, filter.isActive);
        return mapperFacade.mapAsList(all, OrganizationListOutView.class);
    }

    @Override
    public OrganizationIdView getId(Integer id) {
        return mapperFacade.map(dao.loadById(id), OrganizationIdView.class);
    }

    @Override
    public void save(OrganizationSaveDto org) {
        dao.save(mapperFacade.map(org, Organization.class));
    }


    @Override
    @Transactional
    public void update(OrganizationUpdateDto dto) {
        dao.update(dto);
    }
}
