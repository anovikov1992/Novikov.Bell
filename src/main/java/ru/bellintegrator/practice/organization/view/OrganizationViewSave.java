package ru.bellintegrator.practice.organization.view;

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
                ", phoneOffice=" + phone +
                ", isActive=" + isActive +
                '}';
    }
}