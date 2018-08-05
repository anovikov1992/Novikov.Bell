package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;


public class OrganizationViewLoadById {

 // @ApiModelProperty(hidden = true)
    public Long id;

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                '}';
    }
}