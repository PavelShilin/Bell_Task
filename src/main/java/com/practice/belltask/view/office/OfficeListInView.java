package com.practice.belltask.view.office;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;


@ApiModel
public class OfficeListInView {

    @NotNull
    public Integer orgId;

    public String name;

    public String phone;

    public Boolean isActive;

}
