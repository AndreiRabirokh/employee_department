package com.epam.employee;

import com.epam.employee.domain.FileValidationException;
import com.epam.employee.service.EmployeeService;
import com.epam.employee.service.IEmployeeService;
import com.epam.employee.utils.FileParser;

import java.io.IOException;

public class EmployeeDepartment {

    public static final String FILE_NAME = "employees.csv";

    public static void main(String[] args) {
        FileParser fileParser = new FileParser();

        try {
            var employees = fileParser.readEmployeeFromSCVFile(FILE_NAME);
            IEmployeeService employeeService = new EmployeeService(employees);

            System.out.println("-= Managers with higher salary =-");
            employeeService.getHighSalaryManagers().entrySet().stream()
                    .forEach(entry -> System.out.println(String.format("Manager %d has higher salary on %.1f", entry.getKey().getId(), entry.getValue())));
            System.out.println("-= Managers with lower salary =-");
            employeeService.getLowSalaryManagers().entrySet().stream()
                    .forEach(entry -> System.out.println(String.format("Manager %d has lower salary on %.1f", entry.getKey().getId(), entry.getValue())));
            System.out.println("-= Employees with long managers line =-");
            employeeService.getWrongDepartmentStructureManagers().stream()
                    .forEach(employee -> System.out.println(String.format("Employee %d has wrong managers line", employee.getId())));
        } catch (IOException | FileValidationException exception) {
            exception.printStackTrace();
        }
    }
}
