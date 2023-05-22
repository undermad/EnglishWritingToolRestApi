package com.ectimel.englishwritingtool.exception;

public class ResourceAlreadyExist extends RuntimeException {
    public ResourceAlreadyExist(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s with %s '%s' already exist.", resourceName, fieldName, fieldValue));
    }
}
