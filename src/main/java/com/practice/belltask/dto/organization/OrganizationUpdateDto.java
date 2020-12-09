package com.practice.belltask.dto.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel
public class OrganizationUpdateDto {

    @NotNull
    @JsonProperty(required = true)
    private Integer id;

    @NotBlank
    @JsonProperty(required = true)
    private String name;

    @NotBlank
    @JsonProperty(required = true)
    private String fullName;

    @NotNull
    @Pattern(regexp = "[0-9]{12}", message = "inn must have 12 symbols")
    @JsonProperty(required = true)
    private Long inn;

    @NotNull
    @JsonProperty(required = true)
    private Long kpp;

    @NotBlank
    @JsonProperty(required = true)
    private String address;

    private String phone;

    @JsonProperty(value = "isActive")
    private Boolean active = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setInn(long inn) {
        this.inn = inn;
    }


    public Long getKpp() {
        return kpp;
    }

    public void setKpp(long kpp) {
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


    public Boolean getStatus() {
        return active;
    }

    public void setStatus(Boolean active) {
        this.active = active;
    }

}
