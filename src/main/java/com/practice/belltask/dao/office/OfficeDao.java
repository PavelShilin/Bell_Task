package com.practice.belltask.dao.office;
import com.practice.belltask.model.Office;


import java.util.List;

public interface OfficeDao {

    List<Office> filter(Integer orgId, String name, String phone, Boolean isActive);

    Office loadOfficeById(Integer id);

    Boolean contains(Integer id);

    void save(Office office);

}
