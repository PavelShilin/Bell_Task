package com.practice.belltask.view.office;
import com.practice.belltask.model.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class OfficeIdView {

    @ApiModelProperty(value = "idOffice")
    @NotNull()
    public Integer id;

    @ApiModelProperty(value = "nameOffice")
    @NotBlank
    public String name;

    @NotBlank
    @ApiModelProperty(value = "phoneOffice")
    public String phone;

    @NotBlank
    @ApiModelProperty(value = "addressOffice")
    public String address;

    @ApiModelProperty(value = "statusOffice")
    public Boolean isActive;


}

