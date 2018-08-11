package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;

public class OfficeViewSave {

 //   @ApiModelProperty(required = true)
    public String name;

//    @ApiModelProperty(required = true)
    public String address;

    public String phoneOffice;

//    @ApiModelProperty(required = true)
    public boolean isActive;


    @Override
    public String toString() {
        return "OfficeViewLoadById{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneOffice='" + phoneOffice + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
