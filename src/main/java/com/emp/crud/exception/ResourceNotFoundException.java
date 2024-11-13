package com.emp.crud.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String resource;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resource, String fieldName, String fieldValue) {
        super(String.format("%s with %s: %s not found", resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
