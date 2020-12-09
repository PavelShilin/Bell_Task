package com.practice.belltask.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;


@ApiModel(description = "Организация-в")
public class OrganizationListOutView {

    @NotNull()
    @ApiModelProperty(value = "id", example = "232")
    public Integer id;

    @ApiModelProperty(value = "ИНН", example = "272345765658")
    public Long inn;

    @ApiModelProperty
    public Boolean isActive = true;

    @Override
    public String toString() {
        return "OrganizationListOutView{" +
                "id=" + id +
                ", inn=" + inn +
                ", status=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationListOutView)) return false;
        OrganizationListOutView that = (OrganizationListOutView) o;
        return isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                Objects.equals(inn, that.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inn, isActive);
    }
}
