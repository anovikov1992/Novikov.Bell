package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.organization.model.Organization;

public class OfficeViewSave {

 //   @ApiModelProperty(required = true)
    public String name;

//    @ApiModelProperty(required = true)
    public String address;

    public String phoneOffice;

//    @ApiModelProperty(required = true)
    public boolean isActive;

    public Long orgId;



    @Override
    public String toString() {
        return "OfficeViewLoadById{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneOffice='" + phoneOffice + '\'' +
                ", isActive=" + isActive +
                ", orgId=" + orgId +
                '}';
    }
}
