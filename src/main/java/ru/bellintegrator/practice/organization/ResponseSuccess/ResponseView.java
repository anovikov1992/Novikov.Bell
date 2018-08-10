package ru.bellintegrator.practice.organization.ResponseSuccess;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseView {

    public Object data;
    public String result;

    public ResponseView() {
    }

    public ResponseView(String result) {
        this.result = result;
    }
}
