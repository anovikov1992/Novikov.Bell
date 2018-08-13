package ru.bellintegrator.practice.docs.model;


import javax.persistence.*;

@Entity
@Table(name = "Doc")
public class Doc {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "doc_name", unique = true)
    private String docName;

    @Column(name = "doc_code", unique = true)
    private Long docCode;



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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    public Doc() {}


    public Doc(Long id, Integer version, String docName, Long docCode) {
        this.id = id;
        this.version = version;
        this.docName = docName;
        this.docCode = docCode;
    }
}


