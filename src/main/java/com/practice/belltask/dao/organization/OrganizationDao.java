package com.practice.belltask.dao.organization;

import com.practice.belltask.model.Organization;

import java.util.List;

public interface OrganizationDao {
    /**
     * Получить все объекты organization
     *
     * @return
     */
    List<Organization> all();

    /**
     * Получить organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Integer id);

    /**
     * Сохранить Person
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * *
     * @param name
     *
     */
    public List<Organization> loadByName(String name);


}
