package com.practice.belltask.service.office;

import com.practice.belltask.controller.advice.NotFoundException;
import com.practice.belltask.dao.office.OfficeDao;
import com.practice.belltask.dao.organization.OrganizationDao;
import com.practice.belltask.dto.office.OfficeSaveDto;
import com.practice.belltask.dto.office.OfficeUpdateDto;
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

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;


    @Autowired
    public OfficeServiceImpl(OfficeDao dao, OrganizationDao organizationdao, OrganizationDao organizationDao, MapperFacade mapperFacade, MapperFactory mapperFactory) {
        this.officeDao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OfficeListOutView> filter(OfficeListInView filter) {
        if (!officeDao.contains(filter.orgId)) {
            throw new NotFoundException("Organization with id: " + filter.orgId + " not found");
        }
        List<Office> all = officeDao.filter(filter.orgId, filter.name, filter.phone, filter.isActive);
        return mapperFacade.mapAsList(all, OfficeListOutView.class);
    }

    @Override
    @Transactional
    public OfficeIdView getOfficeById(Integer id) {
        return (mapperFacade.map(officeDao.loadOfficeById(id), OfficeIdView.class));
    }

    @Override
    @Transactional
    public void save(OfficeSaveDto office) {
        checkOrganization(office.getOrgId());
        officeDao.save(mapperFacade.map(office, Office.class), office.getOrgId());
    }

    @Override
    public void update(OfficeUpdateDto office) {
        checkOrganization(office.getId());
        officeDao.update(office);
    }

    public void checkOrganization(Integer orgId) {
        if (orgId != null && !officeDao.contains(orgId)) {
            throw new NotFoundException("Organization/office with id: " + orgId + " not found");
        }
    }
}
