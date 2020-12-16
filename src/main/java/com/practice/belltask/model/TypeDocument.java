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
    private Integer code;

    /**
     * Общее название документа (Псспорт РФ, военный билет, и т. д.)
     */

    @Column(name = "name", nullable = false, length = 60)
    private String name;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer codeTypeDocument) {
        this.code = codeTypeDocument;
    }

    public String getName() {
        return name;
    }

    public void setNameTypeDocument(String nameTypeDocument) {
        this.name = name;
    }
}
