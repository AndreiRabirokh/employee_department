package com.epam.employee.service;

import com.epam.employee.domain.Employee;
import com.epam.employee.domain.ReportResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private static final double MIN_SALARY_PERCENTAGE = 0.2;
    private static final double MAX_SALARY_PERCENTAGE = 0.5;
    private static final int MAX_SUBORDINATES_LIMIT = 4;

    public ReportResult analyzeEmployee(Map<Integer, Employee> employees) {
        ReportResult result = new ReportResult();
        calculateWrongSalaryAmounts(groupManagersWithSubordinates(employees, result), result);
        filterWrongManagersLineEmployee(result);
        return result;
    }

    private Map<Employee, List<Employee>> groupManagersWithSubordinates(Map<Integer, Employee> employees, ReportResult result) {
        Map<Employee, List<Employee>> managerDepartments = new LinkedHashMap<>();

        for (Employee manager : employees.values()) {
            var highManager = employees.get(manager.managerId());
            managerDepartments.put(manager, employees.values().stream().filter(emp -> emp.managerId() == manager.id()).collect(Collectors.toList()));
            if (highManager != null) {
                result.putManagersLevel(manager, result.getEmployeeManagersLevel(highManager) + 1);
            } else {
                result.putManagersLevel(manager, 1);
            }
        }
        return managerDepartments;
    }

    private void calculateWrongSalaryAmounts(Map<Employee, List<Employee>> managerDepartments, ReportResult result) {
        for (Map.Entry<Employee, List<Employee>> entry : managerDepartments.entrySet()) {
            var manager = entry.getKey();
            var subordinates = entry.getValue();

            if (!subordinates.isEmpty()) {
                var averageSalary = calculateAverageSalary(subordinates);
                var managerSalary = manager.salary();

                if (managerSalary - averageSalary < MIN_SALARY_PERCENTAGE * averageSalary) {
                    result.putLowSalaryManager(manager, ((MIN_SALARY_PERCENTAGE * averageSalary) - (managerSalary - averageSalary)));
                } else if (managerSalary - averageSalary > MAX_SALARY_PERCENTAGE * averageSalary) {
                    result.putHighSalaryManager(manager, ((managerSalary - averageSalary) - (MAX_SALARY_PERCENTAGE * averageSalary)));
                }
            }
        }
    }

    private void filterWrongManagersLineEmployee(ReportResult report) {
        report.setWrongManagersLineEmployee(report.getManagersLevel().entrySet().stream()
                .filter(entry -> entry.getValue() > MAX_SUBORDINATES_LIMIT)
                .map(employee -> employee.getKey())
                .collect(Collectors.toList()));
    }

    private static double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0);
    }
}
