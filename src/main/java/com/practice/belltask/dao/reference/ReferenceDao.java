package com.practice.belltask.dao.reference;


import com.practice.belltask.dto.reference.CitizenshipDto;
import com.practice.belltask.dto.reference.DocumentDto;
import com.practice.belltask.model.Citizenship;
import com.practice.belltask.model.Document;
import com.practice.belltask.model.TypeDocument;

import java.util.List;

public interface ReferenceDao {
    List<DocumentDto> getListDocument();

    List<CitizenshipDto> getListCitizenship();
}
