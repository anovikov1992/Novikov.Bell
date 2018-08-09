package ru.bellintegrator.practice.organization.MyException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrgOutException.class)
    protected ResponseEntity<MyException> handleOrgNameException(OrgOutException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrganisationValidationException.class)
    protected ResponseEntity<MyException> validationException(OrganisationValidationException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException(ex.getMessage()), HttpStatus.NOT_FOUND);
    }



    private static class MyException {
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public MyException(String error) {
            this.error = error;
        }
    }

}