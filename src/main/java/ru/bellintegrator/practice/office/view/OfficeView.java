package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.Column;
import javax.persistence.Version;

public class OfficeView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public String address;

    public boolean isActive;

    public Organization organization;

    @Override
    public String toString() {
        return "OfficeView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urAddress='" + address + '\'' +
                ", isActive=" + isActive +
                ", organization=" + organization +
                '}';
    }
}
