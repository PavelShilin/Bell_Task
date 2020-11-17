package com.practice.belltask.model;

import javax.persistence.*;

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
     * Организация
     */

    @OneToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    private Organization organization;

    /**
     * Название офиса
     */

    @Column(name = "name", nullable = false, length = 50)
    private String nameOffice;

    /**
     * Телефон офиса
     */

    @Column(name = "phone", length = 20)
    private String phoneOffice;

    /**
     * Адрес офиса
     */
    @Column(name = "address", nullable = false, length = 50)
    private String addressOffice;

    /**
     * Статус офиса
     */

    @Column(name = "is_active", nullable = false)
    private String statusOffice;

    public Integer getId() {
        return id;
    }


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
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
