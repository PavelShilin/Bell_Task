package com.practice.belltask.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

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
     * Офис в котором работает сотрудник
     */

    @OneToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id", nullable = false)
    private Office office;

    /**
     * Имя сотрудника
     */

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * Фамилия сотрудника
     */

    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * отчество сотрудника
     */

    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * должность сотрудника
     */

    @Column(name = "position", nullable = false, length = 70)
    private String position;

    /**
     * номер телефона сотрудника
     */

    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * документ сотрудника
     */

    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    private Document document;

    /**
     * Гражданство
     */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code", referencedColumnName = "id", nullable = false)
    private Citizenship citizenship;

    /**
     * Статус сотрудника
     */

    @Column(name = "is_identified")
    private boolean statusUser;

    /**
     * getters and setters
     */

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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public boolean isStatusUser() {
        return statusUser;
    }

    public void setStatusUser(boolean statusUser) {
        this.statusUser = statusUser;
    }
}
