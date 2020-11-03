package com.practice.BellTask.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Организация
 */

@Entity(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    /**
     * Версия
     */

    @Column(name = "version", nullable = false)
    private Integer version;
    /**
     * Краткое название организации
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
    /**
     * ИНН организации
     */
    @Column(name = "inn", nullable = false, unique = true)
    private Long inn;
    /**
     * КПП организации
     */
    @Column(name = "kpp", nullable = false)
    private Long kpp;
    /**
     * Адрес организации
     */
    @Column(name = "address", nullable = false, unique = true)
    private String address;
    /**
     * Номер телефона организации
     */
    @Column(name = "phone")
    private String phone;
    /**
     * Статус функционирования организации
     */
    @Column(name = "is_active")
    private boolean status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
