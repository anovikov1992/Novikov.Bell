package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {

    public Long id;

    public String name;

    public String fullName;

    public Long inn;

    public Long kpp;

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
                ", phoneOffice=" + phone +
                ", isActive=" + isActive +
                '}';
    }
}