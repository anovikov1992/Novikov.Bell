package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;


public class UserViewUpdate {

    @ApiModelProperty(required = true)
    public Long id;

    @ApiModelProperty(required = true)
    public String firstName;

    public String middleName;

    public String secondName;

    @ApiModelProperty(required = true)
    public String position;

    public String phoneUser;

//    public String docName;

    public String docNumber;

    public Date docDate;

    public String citizenshipCode;

    public Boolean isIdentified;


    @Override
    public String toString() {
        return "UserViewUpdate{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", phoneUser='" + phoneUser + '\'' +
             //   ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}