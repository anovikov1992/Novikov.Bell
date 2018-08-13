package ru.bellintegrator.practice.user.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.docs.model.Doc;

import java.sql.Date;


public class UserViewByOfficeIdResponse {

    public Long id;

    public String firstName;

    public String middleName;

    public String secondName;

    public String position;

    public UserViewByOfficeIdResponse(Long id, String firstName, String middleName, String secondName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.position = position;
    }


    @Override
    public String toString() {
        return "UserViewByOfficeIdResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}