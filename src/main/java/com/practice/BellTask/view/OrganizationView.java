package com.practice.BellTask.view;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@ApiModel(description = "Организация")
public class OrganizationView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор",  example = "1")
    public String id;

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Название", example = "Кооп три лопаты")
    public String name;

    @NotEmpty(message = "fullName cannot be null")
    @ApiModelProperty(value = "Полное название", example = "ООО")
    public String fullName;

    @NotNull(message = "inn cannot be null")
    @Min(2)
    @Max(14)
    @ApiModelProperty(value = "ИНН", example = "272345765658")
    public Long inn;

    @NotNull(message = "kpp cannot be null")
    @Min(2)
    @Max(14)
    @ApiModelProperty(value = "КПП", example = "272345765658")
    public Long kpp;


    @NotEmpty(message = "address cannot be null")
    @ApiModelProperty(value = "Адрес", example = "г.Москва, Ул. Строителей 6")
    public String address;


    @ApiModelProperty(value = "Телефон", example = "89546455463")
    public String phone;

    @NotEmpty(message = "status cannot be null")
    @ApiModelProperty(value = "Статус", example = "Работает")
    public boolean status;

    @Override
    public String toString() {
        return "OrganizationView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + status +
                '}';
    }
}
