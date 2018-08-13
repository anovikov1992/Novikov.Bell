package ru.bellintegrator.practice.user.view;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.office.model.Office;

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

  //  public Office office;

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
               // ", office=" + office +
                '}';
    }
}