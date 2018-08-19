package ru.bellintegrator.practice.office.view;


import io.swagger.annotations.ApiModelProperty;

public class OfficeView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public boolean isActive;


    public OfficeView(Long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "OfficeView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
