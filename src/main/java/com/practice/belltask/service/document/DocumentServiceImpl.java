package com.practice.belltask.service.document;

import com.practice.belltask.dao.document.DocumentDao;
import com.practice.belltask.model.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    final DocumentDao dao;

    public DocumentServiceImpl(DocumentDao dao) {
        this.dao = dao;
    }

    @Override
    public Document getDocumentById(Integer id) {
       return dao.getById(id) ;
    }
}
