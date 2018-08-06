package ru.bellintegrator.practice.organization.view;


import io.swagger.annotations.ApiModelProperty;

public class OrganizationViewUpdate {

    @ApiModelProperty(required = true)
    public Long id;

   @ApiModelProperty(required = true)
    public String name;

   @ApiModelProperty(required = true)
    public String fullName;

   @ApiModelProperty(required = true)
    public Long inn;

   @ApiModelProperty(required = true)
    public Long kpp;

   @ApiModelProperty(required = true)
    public String urAddress;


    public Long phone;

    public Boolean isActive;

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