package com.practice.BellTask.service;

import com.practice.BellTask.dao.organization.OrganizationDao;
import com.practice.BellTask.model.Organization;
import com.practice.BellTask.model.mapper.MapperFacade;
import com.practice.BellTask.view.OrganizationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
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
    public void add(OrganizationView view) {
        Organization newOrg = new Organization(view.name,view.fullName,view.inn,view.kpp,view.address,view.status);
        dao.save(newOrg);
    }

    @Override
    public Organization getOrgById(Integer id) {
        ///Organization orgView = new OrganizationView();
        return dao.loadById(id);
    }

    /**
     * Получить список людей
     *
     * @return {@OrganizationView}
     */
    @Transactional
    @Override
    public List<OrganizationView> organizations() {
        List<Organization> all = dao.all();
        return mapperFacade.mapAsList(all, OrganizationView.class);
    }
}
