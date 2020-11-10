package com.practice.BellTask.model;

import javax.persistence.*;
import java.beans.Visibility;
import java.util.Date;
import java.util.Objects;


/**
 * Организация
 */

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    /**
     * Версия
     */

    @Version
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

    public Organization() {
    }

    public Organization(String name, String fullName, Long inn, Long kpp, String address, boolean status) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.status = status;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return status == that.status &&
                Objects.equals(id, that.id) &&
                Objects.equals(version, that.version) &&
                Objects.equals(name, that.name) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(inn, that.inn) &&
                Objects.equals(kpp, that.kpp) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name, fullName, inn, kpp, address, phone, status);
    }
}
