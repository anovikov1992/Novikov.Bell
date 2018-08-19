package ru.bellintegrator.practice.user.view;

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
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", phoneUser=" + phoneUser +
                ", docDate=" + docDate +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}