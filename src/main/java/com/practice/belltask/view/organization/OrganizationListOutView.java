package com.practice.belltask.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;


@ApiModel(description = "Организация")
public class OrganizationListOutView {

    @NotNull(message = "Невведено название орг-ции")
    @ApiModelProperty(value = "id", example = "232")
    public Integer id;

    @ApiModelProperty(value = "ИНН", example = "272345765658")
    public Long inn;

    @ApiModelProperty(value = "Статус", example = "Работает")
    public boolean status;

    @Override
    public String toString() {
        return "OrganizationListOutView{" +
                "id=" + id +
                ", inn=" + inn +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationListOutView)) return false;
        OrganizationListOutView that = (OrganizationListOutView) o;
        return status == that.status &&
                Objects.equals(id, that.id) &&
                Objects.equals(inn, that.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inn, status);
    }
}
