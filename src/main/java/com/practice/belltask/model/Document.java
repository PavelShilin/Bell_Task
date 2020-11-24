package com.practice.belltask.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * Версия
     */

    @Version
    private Integer version;

    /**
     * Номер документа
     */

    @Column(name = "doc_number", length = 50)
    private String docNumber;

    /**
     * Дата выдачи документа
     */

    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /**
     * Тип документа
     */

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private TypeDocument typeDocument;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

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

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }
}
