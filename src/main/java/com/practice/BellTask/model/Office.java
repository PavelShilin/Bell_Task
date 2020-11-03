package com.practice.BellTask.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    /**
     * Версия
     */

    @Column(name = "version", nullable = false)
    private Date version;

    /**
     * Организация
     */

    @OneToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    private Organization organization;

    /**
     * Название офиса
     */

    @Column(name = "name", nullable = false)
    private String nameOffice;

    /**
     * Телефон офиса
     */

    @Column(name = "phone")
    private String phoneOffice;

    /**
     * Адрес офиса
     */
    @Column(name = "address", nullable = false)
    private String addressOffice;

    /**
     * Статус офиса
     */

    @Column(name = "is_active", nullable = false)
    private String statusOffice;

    public Integer getId() {
        return id;
    }


    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getNameOffice() {
        return nameOffice;
    }

    public void setNameOffice(String nameOffice) {
        this.nameOffice = nameOffice;
    }

    public String getPhoneOffice() {
        return phoneOffice;
    }

    public void setPhoneOffice(String phoneOffice) {
        this.phoneOffice = phoneOffice;
    }

    public String getAddressOffice() {
        return addressOffice;
    }

    public void setAddressOffice(String addressOffice) {
        this.addressOffice = addressOffice;
    }

    public String getStatusOffice() {
        return statusOffice;
    }

    public void setStatusOffice(String statusOffice) {
        this.statusOffice = statusOffice;
    }
}
