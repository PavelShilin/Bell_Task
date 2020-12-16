
package com.practice.belltask.dao.document;

import com.practice.belltask.model.Document;
import com.practice.belltask.model.TypeDocument;
import com.practice.belltask.model.User;

import java.util.Date;


public interface DocumentDao {
    Document getById(Integer id);


    void createDocument(Integer id, String number, Date date, String name, Integer code);


    TypeDocument getTypeDocumentByName(String name);

    void updateDocument(Integer id, String number, Date date, String name);
}

