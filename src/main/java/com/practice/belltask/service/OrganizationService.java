package com.practice.belltask.service;

import com.practice.belltask.model.Organization;
import com.practice.belltask.view.organization.OrganizationListOutView;
import com.practice.belltask.view.organization.OrganizationListInView;
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

