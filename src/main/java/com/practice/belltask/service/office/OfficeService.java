package com.practice.belltask.service.office;

import com.practice.belltask.view.office.OfficeIdView;
import com.practice.belltask.view.office.OfficeListInView;
import com.practice.belltask.view.office.OfficeListOutView;
import com.practice.belltask.view.organization.OrganizationIdView;
import com.practice.belltask.view.organization.OrganizationListInView;
import com.practice.belltask.view.organization.OrganizationListOutView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OfficeService {

    List<OfficeListOutView> filter(OfficeListInView filter);

    OfficeIdView getOfficeById(Integer id);

}
