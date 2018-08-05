package ru.bellintegrator.practice.docs.view;

import io.swagger.annotations.ApiModelProperty;

public class DocView {

    @ApiModelProperty(hidden = true)
    public Long id;

    public String docName;

    public Long docCode;

    @Override
    public String toString() {
        return "DocView{" +
                "id=" + id +
                ", docName='" + docName + '\'' +
                ", docCode=" + docCode +
                '}';
    }
}