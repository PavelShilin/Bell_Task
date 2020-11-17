package com.practice.belltask.model;

import javax.persistence.*;

@Entity
@Table(name = "Type_document")
public class TypeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Версия
     */

    @Version
    private Integer version;

    /**
     * Код типа документа
     */

    @Column(name = "code", unique = true)
    private Integer codeTypeDocument;

    /**
     * Общее название документа (Псспорт РФ, военный билет, и т. д.)
     */

    @Column(name = "name", nullable = false, length = 60)
    private String nameTypeDocument;

    /**
     * getters and setters
     */

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCodeTypeDocument() {
        return codeTypeDocument;
    }

    public void setCodeTypeDocument(Integer codeTypeDocument) {
        this.codeTypeDocument = codeTypeDocument;
    }

    public String getNameTypeDocument() {
        return nameTypeDocument;
    }

    public void setNameTypeDocument(String nameTypeDocument) {
        this.nameTypeDocument = nameTypeDocument;
    }
}
