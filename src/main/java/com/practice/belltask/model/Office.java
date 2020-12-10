package com.practice.belltask.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity(name = "Office")
public class Office {
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
     * Название офиса
     */


    @Column(name = "name", length = 50)
    private String name;

    /**
     * Телефон офиса
     */

    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Адрес офиса
     */


    @Column(name = "address", length = 50)
    private String address;

    /**
     * Статус офиса
     */

    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Организация
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;

    public Office() {
    }


    public Office(Integer id, Integer version, String name, String phone, String address, Boolean isActive, Organization organization) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return Objects.equals(id, office.id) &&
                Objects.equals(version, office.version) &&
                Objects.equals(name, office.name) &&
                Objects.equals(phone, office.phone) &&
                Objects.equals(address, office.address) &&
                Objects.equals(isActive, office.isActive) &&
                Objects.equals(organization, office.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name, phone, address, isActive, organization);
    }
}
