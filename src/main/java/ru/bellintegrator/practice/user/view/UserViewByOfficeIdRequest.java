package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;


public class UserViewByOfficeIdRequest {

    @ApiModelProperty(required = true)
    public Long officeId;

    public String firstName;

    public String middleName;

    public String secondName;

    public String position;

    public String docCode;

    public String citizenshipCode;


    @Override
    public String toString() {
        return "UserViewByOfficeIdRequest{" +
                "officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", docCode=" + docCode +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                '}';
    }
}