package ru.bellintegrator.practice.organization.MyException;

public class OrganisationValidationException extends RuntimeException {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrganisationValidationException(String message) {
        this.message = message;
    }
}
