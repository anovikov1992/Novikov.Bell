package ru.bellintegrator.practice.organization.model;



import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.*;
import java.util.Set;

/**
 * User
 */
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "full_Name")
    private String fullName;

    @Column(name = "inn")
    private Long inn;

    @Column(name = "kpp")
    private Long kpp;

    @Column(name = "urAddress")
    private String urAddress;

    @Column(name = "phone_org")
    private Long phone;

    @Column(name = "is_Active")
    private Boolean isActive;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;


    /**
     * Конструктор для hibernate
     */
    public Organization() {

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

    public void setName(String Name) {
        this.name = Name;
    }

    public String getFullName() { return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;  }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public String getUrAddress() {
        return urAddress;
    }

    public void setUrAddress(String urAddress) {
        this.urAddress = urAddress;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public Organization(Long id, Integer version, String name, String FullName, Long inn, Long kpp, String urAddress, Long phone, Boolean isActive) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.fullName = FullName;
        this.inn = inn;
        this.kpp = kpp;
        this.urAddress = urAddress;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Organization(String name, String fullName, Long inn, Long kpp, String urAddress, Long phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.urAddress = urAddress;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Organization(String name, String fullName, Long inn, Long kpp, String urAddress, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.urAddress = urAddress;
        this.isActive = isActive;
    }

    public Organization(Long id) {
        this.id = id;
    }

    public Organization(String name, Long inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public Organization(Long id, Integer version, String name, String fullName, Long inn, Long kpp, String urAddress) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.urAddress = urAddress;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", urAddress='" + urAddress + '\'' +
                ", phoneOffice=" + phone +
                ", isActive=" + isActive +
                ", offices=" + offices +
                '}';
    }

}
