package com.practice.belltask.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
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
    @NotBlank
    @Column(name = "name", length = 20)
    private String name;

    /**
     * Полное название организации
     */
    @NotBlank
    @Column(name = "full_name", unique = true, length = 50)
    private String fullName;
    /**
     * ИНН организации
     */

    @NotNull
    @Column(name = "inn", unique = true)
    private Long inn;
    /**
     * КПП организации
     */
    @NotNull
    @Column(name = "kpp")
    private Long kpp;
    /**
     * Адрес организации
     */
    @NotBlank
    @Column(name = "address", unique = true, length = 70)
    private String address;
    /**
     * Номер телефона организации
     */
    @Column(name = "phone", length = 20)
    private String phone;
    /**
     * Статус функционирования организации
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    List<Office> offices = new ArrayList<>();


    public Organization() {
    }

    public Organization(String name, String fullName, Long inn, Long kpp, String address, boolean status) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.isActive = status;
    }

    /**
     * getters and setters
     */

/*    public void addOffices(Office office) {
        offices.add(office);
    }

    public void removeOffices(Office office) {
        offices.remove(office);
    }*/
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setStatus(Boolean status) {
        this.isActive = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return isActive == that.isActive &&
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
        return Objects.hash(id, version, name, fullName, inn, kpp, address, phone, isActive);
    }
}
