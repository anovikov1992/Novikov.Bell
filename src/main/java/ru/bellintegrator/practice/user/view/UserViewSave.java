package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.docs.model.Doc;

import java.sql.Date;


public class UserViewSave {

    @ApiModelProperty(required = true)
    public String firstName;

    public String middleName;

    public String secondName;

    @ApiModelProperty(required = true)
    public String position;

    public String phoneUser;


    public String docName; //берется из справочника по документам

    public String docNumber;

    public Date docDate;

    public String citizenshipCode; //берется из справочника по странам

    public Boolean isIdentified;

    public Long officeId;


    @Override
    public String toString() {
        return "UserViewSave{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", phoneUser='" + phoneUser + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}