package ru.bellintegrator.practice.organization.my.exception;

import javax.persistence.NoResultException;

public class OrganisationValidationException extends NoResultException {

    public OrganisationValidationException(String message) {
        super(message);
    }
}
