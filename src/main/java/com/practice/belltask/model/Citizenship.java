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
    private Integer code;
    /**
     * Название Государства
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

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
