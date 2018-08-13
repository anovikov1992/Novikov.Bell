package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.office.model.Office;

import java.sql.Date;


public class UserViewLoadById {

    public Long id;

    public String firstName;

    public String middleName;

    public String secondName;

    public String position;

    public String phoneUser;

    public String docName;

    public String docNumber;

    public Date docDate;

    public String citizenshipName;

    public String citizenshipCode;

    public Boolean isIdentified;



    @Override
    public String toString() {
        return "UserViewLoadById{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
           /*     ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", phoneUser=" + phoneUser +*/
             //   ", doc=" + doc +
            //    ", docDate=" + docDate +
            //    ", country=" + country +
                ", citizenshipCode='" + citizenshipCode + '\'' +
             /*   ", isIdentified=" + isIdentified +
                ", office=" + office +*/
                '}';
    }
}