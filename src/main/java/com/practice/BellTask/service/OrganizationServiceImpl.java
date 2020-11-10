package com.practice.BellTask.service;

import com.practice.BellTask.dao.organization.OrganizationDao;
import com.practice.BellTask.model.Organization;
import com.practice.BellTask.model.mapper.MapperFacade;
import com.practice.BellTask.view.organization.OrganizationListInView;
import com.practice.BellTask.view.organization.OrganizationListOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }
    /**
     * Добавить нового человека в БД
     *
     * @param
     */

    @Override
    @Transactional
    public void add(OrganizationListInView view) {
    }

    @Override
    public Organization getOrgById(Integer id) {
        ///Organization orgView = new OrganizationListInView();
        return dao.loadById(id);
    }

    /**
     * Получить список людей
     *
     * @return {@OrganizationView}
     */
    @Transactional
    @Override
    public List<OrganizationListOutView> organizations() {
        List<Organization> all = dao.all();
        return mapperFacade.mapAsList(all, OrganizationListOutView.class);
    }

    @Override
    public List<OrganizationListOutView> getOrgByName(String name) {
        List<Organization> newListOrg = new ArrayList<>(dao.loadByName(name));
        return mapperFacade.mapAsList(newListOrg, OrganizationListOutView.class);

    }

}
