package com.practice.belltask.dao.office;

import com.practice.belltask.model.Office;

import java.util.List;

public interface OfficeDao {

    List<Office> filter(Integer orgId, String name, String phone, Boolean isActive);

    Office loadById(Integer id);

}
