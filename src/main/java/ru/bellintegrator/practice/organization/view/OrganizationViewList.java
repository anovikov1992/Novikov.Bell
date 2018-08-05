package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;


public class OrganizationViewList {

    @ApiModelProperty(hidden = true)
    public Long id;


    public String name;


    public boolean isActive;

    @Override
    public String toString() {
        return "OrganizationViewList{" +
                "id=" + id +
                ", isActive=" + isActive +
                '}';
    }
}