package com.ectimel.englishwritingtool.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String resourceField, String fieldValue) {
        super(String.format("%s with %s '%s' not found.", resourceName, resourceField, fieldValue));
    }


}
