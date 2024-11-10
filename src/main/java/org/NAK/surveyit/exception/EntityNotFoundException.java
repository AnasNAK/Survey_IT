package org.NAK.surveyit.exception;

public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException(String entityName, String name) {
        super(entityName + " with name '" + name + "' not found.");
    }

    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with ID " + id + " not found.");
    }
}