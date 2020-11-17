package com.practice.belltask.model;

import javax.persistence.*;

@Entity
@Table(name = "Citizenship")
public class Citizenship {

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
     * код гражданства
     */

    @Column(name = "code", unique = true)
    private Integer codeCitizenship;
    /**
     * Название Государства
     */

    @Column(name = "name", nullable = false, length = 60)
    private String nameCitizenship;

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

    public Integer getCodeCitizenship() {
        return codeCitizenship;
    }

    public void setCodeCitizenship(Integer codeCitizenship) {
        this.codeCitizenship = codeCitizenship;
    }

    public String getNameCitizenship() {
        return nameCitizenship;
    }

    public void setNameCitizenship(String nameCitizenship) {
        this.nameCitizenship = nameCitizenship;
    }
}
