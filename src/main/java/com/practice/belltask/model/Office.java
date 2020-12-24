package com.practice.belltask.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
    private Set<User> users;

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

    public void addUser(User user) {
        users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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

}
