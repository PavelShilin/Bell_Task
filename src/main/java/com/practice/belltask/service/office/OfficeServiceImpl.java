package com.practice.belltask.service.office;

import com.practice.belltask.dao.office.OfficeDao;
import com.practice.belltask.model.Office;
import com.practice.belltask.model.mapper.MapperFacade;
import com.practice.belltask.view.office.OfficeIdView;
import com.practice.belltask.view.office.OfficeListInView;
import com.practice.belltask.view.office.OfficeListOutView;

import com.practice.belltask.view.organization.OrganizationIdView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OfficeServiceImpl implements  OfficeService{

    private final OfficeDao dao;
    private final MapperFacade mapperFacade;
    private final MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade, MapperFactory mapperFactory) {
        this.dao = dao;
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
        //Office office = dao.loadById(id);
        System.out.println(dao.loadById(id).getNameOffice()+" -  name office-  - -- -"+ dao.loadById(id).getAddressOffice()+"    - address");
        return mapperFactory.getMapperFacade(Office.class, OfficeIdView.class).map(dao.loadById(id));
    }
}
