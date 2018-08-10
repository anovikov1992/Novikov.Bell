package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;


public class OrganizationViewSave {


    public String name;

    public String fullName;

    public String inn;

    public String kpp;

    public String urAddress;

    public String phone;

    public boolean isActive;

    @Override
    public String toString() {
        return "OrganizationViewSave{" +
                "name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", urAddress='" + urAddress + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                '}';
    }
}