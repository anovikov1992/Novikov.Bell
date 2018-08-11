package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.Column;
import javax.persistence.Version;

public class OfficeViewList {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public boolean isActive;


    @Override
    public String toString() {
        return "OfficeViewList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
