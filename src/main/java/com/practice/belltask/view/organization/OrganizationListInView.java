package com.practice.belltask.view.organization;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "Организация")
public class OrganizationListInView {

    @NotBlank
    public String name;

    public Long inn;

    public Boolean isActive;

    public String getName() {
        return name;
    }

    public Long getInn() {
        return inn;
    }

    public boolean isStatus() {
        return isActive;
    }

    @Override
    public String toString() {
        return "OrganizationListInView{" +
                "name='" + name + '\'' +
                ", inn=" + inn +
                ", status=" + isActive +
                '}';
    }
}
