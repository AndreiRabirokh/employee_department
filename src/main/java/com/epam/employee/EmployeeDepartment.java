package com.epam.employee;

import com.epam.employee.domain.FileValidationException;
import com.epam.employee.domain.ReportResult;
import com.epam.employee.service.EmployeeServiceImpl;
import com.epam.employee.service.EmployeeService;
import com.epam.employee.utils.FileParser;

import java.io.IOException;

public class EmployeeDepartment {

    public static final String FILE_NAME = "employees.csv";

    public static void main(String[] args) {
        FileParser fileParser = new FileParser();

        try {
            var employees = fileParser.readEmployeeFromSCVFile(FILE_NAME);
            EmployeeService employeeService = new EmployeeServiceImpl();

            ReportResult report = employeeService.analyzeEmployee(employees);
            System.out.println("-= Managers with higher salary =-");
            report.getHighSalaryManagers().entrySet().stream()
                    .forEach(entry -> System.out.println(String.format("Manager %d has higher salary on %.1f", entry.getKey().id(), entry.getValue())));
            System.out.println("-= Managers with lower salary =-");
            report.getLowSalaryManagers().entrySet().stream()
                    .forEach(entry -> System.out.println(String.format("Manager %d has lower salary on %.1f", entry.getKey().id(), entry.getValue())));
            System.out.println("-= Employees with long managers line =-");

            report.getWrongManagersLineEmployee().stream()
                    .forEach(employee -> System.out.println(String.format("Employee %d has wrong managers line", employee.id())));


        } catch (IOException | FileValidationException exception) {
            exception.printStackTrace();
        }
    }
}
