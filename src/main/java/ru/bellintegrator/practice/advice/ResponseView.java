package ru.bellintegrator.practice.advice;

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

    public ResponseView(Object data) {
        this.data = data;
    }
}
