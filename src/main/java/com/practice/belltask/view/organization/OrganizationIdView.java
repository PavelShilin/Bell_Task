package com.practice.belltask.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel
public class OrganizationIdView  {

    @NotNull()
    public Integer id;

    public String name;

    public String fullName;

    public Long inn;

    public long kpp;

    public String address;

    public String phone;

    public Boolean isActive;

}
