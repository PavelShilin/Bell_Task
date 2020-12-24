package com.practice.belltask.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "name", length = 60)
    private String name;

    @OneToMany(mappedBy = "citizenship")
    Set<User> users = new HashSet<>();

    /**
     * getters and setters
     */

    public void userAdd(User user) {
        users.add(user);
    }

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
