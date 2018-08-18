package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_office")
    private String phoneOffice;

    @Column(name = "is_Active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private Set<User> users;

    public void addUser(User user) {
        getUsers().add(user);
        user.setOffice(this);
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setOffice(null);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneOffice() {
        return phoneOffice;
    }

    public void setPhoneOffice(String phoneOffice) {
        this.phoneOffice = phoneOffice;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }



    public Office() {}

    public Office(String name, String address, String phoneOffice, Boolean isActive) {
        this.name = name;
        this.address = address;
        this.phoneOffice = phoneOffice;
        this.isActive = isActive;
    }

    public Office(String name) {
        this.name = name;
    }

    public Office(Boolean isActive) {
        this.isActive = isActive;
    }

    public Office(Long id, Integer version, String name, String address, Boolean isActive, Organization organization) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.address = address;
        this.isActive = isActive;
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
