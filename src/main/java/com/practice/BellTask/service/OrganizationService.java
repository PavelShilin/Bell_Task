package com.practice.BellTask.service;

import com.practice.BellTask.model.Organization;
import com.practice.BellTask.view.organization.OrganizationListOutView;
import com.practice.BellTask.view.organization.OrganizationListInView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OrganizationService {

    /**
     * Добавить нового человека в БД
     */
    void add(OrganizationListInView organization);

    /**
     * Получить список оргпнизаций
     *
     * @return {@Organization}
     */
    List<OrganizationListOutView> organizations();

    Organization getOrgById(Integer id);

    List<OrganizationListOutView> getOrgByName(String name);





}

