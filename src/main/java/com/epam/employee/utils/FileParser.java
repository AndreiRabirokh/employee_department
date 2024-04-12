package com.epam.employee.utils;

import com.epam.employee.domain.Employee;
import com.epam.employee.domain.FileValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileParser {


    public static final int FILE_SIZE_LIMIT = 1000;
    public static final String FILE_VALIDATION_MESSAGE = " file contains wrong rows number, should be not more than ";
    public static final String COMMA_SEPARATOR = ",";
    public static final String SLASH_SEPARATOR = "/";

    public Map<Integer, Employee> readEmployeeFromSCVFile(String filename) throws IOException, FileValidationException {
        var fileLines = readLines(filename);
        validateFile(fileLines, filename);
        return parseEmployees(fileLines);

    }

    private List<String> readLines(String filename) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(FileParser.class.getResourceAsStream(SLASH_SEPARATOR + filename))))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }

    private void validateFile(List<String> fileLines, String fileName) throws FileValidationException {
        if (fileLines.size() > FILE_SIZE_LIMIT) {
            throw new FileValidationException(fileName + FILE_VALIDATION_MESSAGE + FILE_SIZE_LIMIT);
        }
    }

    private Map<Integer, Employee> parseEmployees(List<String> fileLines) {
        Map<Integer, Employee> employees = new LinkedHashMap<>();
        fileLines.remove(0) ;
        fileLines.stream().forEach(fileLine -> {
            var rowFields = fileLine.split(COMMA_SEPARATOR);
            var id = Integer.parseInt(rowFields[0]);
            var firstName = rowFields[1];
            var lastName = rowFields[2];
            var salary = rowFields[3].isEmpty() ? 0 : Double.parseDouble(rowFields[3]);
            var managerId = rowFields.length > 4 && !rowFields[4].isEmpty() ? Integer.parseInt(rowFields[4]) : -1;
            employees.put(id, new Employee(id, firstName, lastName, salary, managerId));

        });
        return employees;
    }

}
