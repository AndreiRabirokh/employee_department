package com.epam.employee.domain;

/**
 * Checked exception for custom business logic validation.
 */
public class FileValidationException extends Exception {

    public FileValidationException(String message) {
        super(message);
    }
}
