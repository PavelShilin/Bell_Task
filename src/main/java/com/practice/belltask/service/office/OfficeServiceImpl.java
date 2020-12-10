package com.practice.belltask.service.office;

import com.practice.belltask.dao.office.OfficeDao;
import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.dto.office.OfficeSaveDto;
import com.practice.belltask.model.Office;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.office.OfficeIdView;
import com.practice.belltask.view.office.OfficeListInView;
import com.practice.belltask.view.office.OfficeListOutView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao dao;
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;
    private final MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao organizationdao, OrganizationDao organizationDao, MapperFacade mapperFacade, MapperFactory mapperFactory) {
        this.dao = dao;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
        this.mapperFactory = mapperFactory;
    }

    @Override
    public List<OfficeListOutView> filter(OfficeListInView filter) {
        List<Office> all = dao.filter(filter.orgId, filter.name, filter.phone, filter.isActive);
        return mapperFacade.mapAsList(all, OfficeListOutView.class);
    }

    @Override
    @Transactional
    public OfficeIdView getOfficeById(Integer id) {
        return (mapperFacade.map(dao.loadOfficeById(id), OfficeIdView.class));
    }


    @Override
    @Transactional
    public void save(OfficeSaveDto office) {
        Office tempOffice = new Office();
        tempOffice.setName(office.getName());
        tempOffice.setAddress(office.getAddress());
        tempOffice.setIsActive(office.getIsActive());
        tempOffice.setOrganization(organizationDao.loadById(office.getOrgId()));
        dao.save(mapperFacade.map(tempOffice, Office.class));

    }
}
