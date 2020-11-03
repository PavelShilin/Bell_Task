package com.practice.BellTask.service;

import com.practice.BellTask.model.Organization;
import com.practice.BellTask.view.OrganizationView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OrganizationService {

    /**
     * Добавить нового человека в БД

     */
    void add(OrganizationView organization);

    /**
     * Получить список людей
     *
     * @return {@Organization}
     */
    List<OrganizationView> organizations();

    Organization getOrgById(Integer id);
}

