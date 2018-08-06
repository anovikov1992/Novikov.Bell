package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;



public class OrganizationView {

 //   @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public String fullName;

    public Long inn;

    public Long kpp;

    public String urAddress;

    public Long phone;

    public boolean isActive;

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", urAddress='" + urAddress + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                '}';
    }
}