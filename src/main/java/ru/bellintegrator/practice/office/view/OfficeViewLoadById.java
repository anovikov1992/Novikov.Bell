package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.Column;
import javax.persistence.Version;

public class OfficeViewLoadById {

    @ApiModelProperty(required = true)
    public Long id;

    @ApiModelProperty(required = true)
    public String name;

    @ApiModelProperty(required = true)
    public String address;

    public String phoneOffice;

    @ApiModelProperty(required = true)
    public Boolean isActive;


    @Override
    public String toString() {
        return "OfficeViewLoadById{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneOffice='" + phoneOffice + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
