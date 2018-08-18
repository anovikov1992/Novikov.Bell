package ru.bellintegrator.practice.user.model;


import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.*;
import java.sql.Date;

/**
 * User
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;
    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 50, nullable = false)
    private String middleName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "position")
    private String position;

    @Column(name = "phone_user")
    private Long phoneUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @Column(name = "docDate")
    private Date docDate;

    @Column(name = "doc_number")
    private Long docNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "isIdentified")
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "office_id")
    private Office office;


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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(Long phoneUser) {
        this.phoneUser = phoneUser;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long docNumber) {
        this.docNumber = docNumber;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    /**
     * Конструктор для hibernate
     */
    public User() {
    }

    public User(Long id, Integer version, String firstName, String middleName, String secondName, String position,
                Long phoneUser, Doc doc, Date docDate, Country country, Boolean isIdentified, Office office) {
        this.id = id;
        this.version = version;
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.position = position;
        this.phoneUser = phoneUser;
        this.doc = doc;
        this.docDate = docDate;
        this.country = country;
        this.isIdentified = isIdentified;
        this.office = office;
    }

    public User(String firstName, String middleName, String secondName, String position, Long phoneUser,
                Doc doc, Date docDate, Long docNumber, Country country, Boolean isIdentified) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.position = position;
        this.phoneUser = phoneUser;
        this.doc = doc;
        this.docDate = docDate;
        this.docNumber = docNumber;
        this.country = country;
        this.isIdentified = isIdentified;
    }

    public User(String firstName, String middleName) {
        this.firstName = firstName;
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", position='" + position + '\'' +
                ", phoneUser=" + phoneUser +
                ", doc=" + doc +
                ", docDate=" + docDate +
                ", country=" + country +
                ", isIdentified=" + isIdentified +
                ", office=" + office +
                '}';
    }
}
