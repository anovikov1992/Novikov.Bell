package ru.bellintegrator.practice.organization.my.exception;

import javax.persistence.NoResultException;

public class OrgOutException extends RuntimeException {

    public OrgOutException() {
    }

    public OrgOutException(String message) {
        super(message);
    }
}
