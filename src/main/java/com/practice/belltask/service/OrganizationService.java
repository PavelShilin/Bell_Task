package com.practice.belltask.service;

import com.practice.belltask.dto.organization.OrganizationSaveDto;
import com.practice.belltask.model.Organization;
import com.practice.belltask.view.organization.OrganizationIdView;
import com.practice.belltask.view.organization.OrganizationListOutView;
import com.practice.belltask.view.organization.OrganizationListInView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OrganizationService {

    List<OrganizationListOutView> filter(OrganizationListInView filter);

    OrganizationIdView getId (Integer id);

    void save(OrganizationSaveDto org);


}

