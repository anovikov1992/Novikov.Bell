package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;

import java.sql.Date;


public class UserView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public Integer version;

    public String firstName;

    public String middleName;

    public String secondName;

    public String position;

    public Long phoneUser;

    public Doc doc;

    public Date docDate;

    public Country country;

    public Boolean isIdentified;


    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", phoneUser=" + phoneUser +
                ", doc=" + doc +
                ", docDate=" + docDate +
                ", country=" + country +
                ", isIdentified=" + isIdentified +
                '}';
    }
}