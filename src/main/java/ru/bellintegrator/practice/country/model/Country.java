package ru.bellintegrator.practice.country.model;


import ru.bellintegrator.practice.user.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    private Integer version;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code")
    private Long countryCode;

    @Column(name = "citizenship_code")
    private String citizenshipCode;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;


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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Country() {}


    public Country(Long id, Integer version, String countryName, Long countryCode, String citizenshipCode) {
        this.id = id;
        this.version = version;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.citizenshipCode = citizenshipCode;
    }
}


