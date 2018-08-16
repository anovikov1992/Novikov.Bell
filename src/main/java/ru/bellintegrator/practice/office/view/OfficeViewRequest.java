package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;

public class OfficeViewRequest {

    @ApiModelProperty(required = true)
    public Long orgId;

    public String name;

    public String phoneOffice;

    public Boolean isActive;


    public OfficeViewRequest() {
    }

    public OfficeViewRequest(Long orgId, String name, String phoneOffice, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.phoneOffice = phoneOffice;
        this.isActive = isActive;
    }


    @Override
    public String toString() {
        return "OfficeViewRequest{" +
                "orgId=" + orgId +
                ", name='" + name + '\'' +
                ", phoneOffice='" + phoneOffice + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
