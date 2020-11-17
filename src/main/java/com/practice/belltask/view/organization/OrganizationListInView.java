package com.practice.belltask.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;


@ApiModel(description = "Организация")
public class OrganizationListInView {

    @NotEmpty(message = "Невведено название орг-ции")
    @ApiModelProperty(value = "name")
    public String name;

    @ApiModelProperty(value = "inn")
    public Long inn;

    @ApiModelProperty(value = "status")
    public boolean status;

    @Override
    public String toString() {
        return "OrganizationListInView{" +
                "name='" + name + '\'' +
                ", inn=" + inn +
                ", status=" + status +
                '}';
    }
}
