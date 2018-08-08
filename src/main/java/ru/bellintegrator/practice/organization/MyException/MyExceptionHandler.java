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

    @ExceptionHandler(OrgIdException.class)
    protected ResponseEntity<MyException> handleOrgIdException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException("Организации с таким ID нет в базе данных"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrgNameException.class)
    protected ResponseEntity<MyException> handleOrgNameException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException("Организации с таким именем нет в базе данных"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InnLengthException.class)
    protected ResponseEntity<MyException> handleNumberLengthException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException("ИНН должен состоять из 10 цифр"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(KppLenghtException.class)
    protected ResponseEntity<MyException> handleKppLenghtException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException("КПП должен состоять из 9 цифр"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrgInnException.class)
    protected ResponseEntity<MyException> handleOrgInnException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException("Организации с таким ИНН нет в базе данных"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneFormatException.class)
    protected ResponseEntity<MyException> handlePhoneFormatException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MyException("Телефон должен состоять только из цифр"), HttpStatus.NOT_FOUND);
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