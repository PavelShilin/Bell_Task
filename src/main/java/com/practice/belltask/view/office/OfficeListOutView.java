package com.practice.belltask.view.office;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;

@ApiModel
public class OfficeListOutView {

    @NotNull
    public Integer id;

    public String name;

    public Boolean isActive;

}
