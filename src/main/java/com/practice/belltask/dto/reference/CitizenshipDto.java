package com.practice.belltask.dto.reference;

import io.swagger.annotations.ApiModel;

@ApiModel
public class CitizenshipDto {

    String name;

    Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
