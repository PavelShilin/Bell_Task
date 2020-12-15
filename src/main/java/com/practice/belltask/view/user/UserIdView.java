package com.practice.belltask.view.user;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
public class UserIdView {

    @ApiModelProperty(value = "idUser")
    @NotNull()
    public Integer id;

    @ApiModelProperty(value = "firstName")
    @NotBlank
    public String firstName;

    @NotBlank
    @ApiModelProperty(value = "secondName")
    public String secondName;

    @NotBlank
    @ApiModelProperty(value = "middleName")
    public String middleName;

    @ApiModelProperty(value = "position")
    public String position;

    @ApiModelProperty(value = "phone")
    public String phone;

    @ApiModelProperty(value = "docName")
    public String docName;

    @ApiModelProperty(value = "docNumber")
    public String docNumber;

    @ApiModelProperty(value = "docDate")
    public Date docDate;

    @ApiModelProperty(value = "citizenshipName")
    public String citizenshipName;

    @ApiModelProperty(value = "citizenshipCode")
    public Integer citizenshipCode;

    @ApiModelProperty(value = "isIdentified")
    public Boolean isIdentified;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
