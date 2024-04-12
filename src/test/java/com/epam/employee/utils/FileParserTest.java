package com.epam.employee.utils;

import com.epam.employee.domain.FileValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileParserTest {

    @Test
    void test_parsing_csv_file() throws IOException, FileValidationException {
        FileParser fileParser = new FileParser();
        var actualResult = fileParser.readEmployeeFromSCVFile("employees.csv");

        assertEquals(10, actualResult.size());
    }

    @Test
    void test_validation_exception() {
        FileParser fileParser = new FileParser();

        assertThrows(FileValidationException.class, () -> fileParser.readEmployeeFromSCVFile("wrong_file_employees.csv"));
    }
}
