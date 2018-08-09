package ru.bellintegrator.practice.organization.MyException;

public class OrgOutException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrgOutException(String message) {
        this.message = message;
    }

    public OrgOutException() {
    }
}
