package com.epam.employee.service;

import com.epam.employee.domain.Employee;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService implements IEmployeeService {

    private static final double MIN_SALARY_PERCENTAGE = 0.2;
    private static final double MAX_SALARY_PERCENTAGE = 0.5;
    private static final int MAX_SUBORDINATES_LIMIT = 4;

    private final Map<Integer, Employee> employees;

    private final Map<Employee, List<Employee>> managerDepartments = new LinkedHashMap<>();
    private final Map<Employee, Double> highSalaryManagers = new HashMap<>();
    private final Map<Employee, Double> lowSalaryManagers = new HashMap<>();

    public EmployeeService(Map<Integer, Employee> employees) {
        this.employees = employees;
        processEmployees();
    }

    @Override
    public Map<Employee, Double> getHighSalaryManagers() {
        return highSalaryManagers;
    }

    @Override
    public Map<Employee, Double> getLowSalaryManagers() {
        return lowSalaryManagers;
    }

    @Override
    public List<Employee> getWrongDepartmentStructureManagers() {
        return employees.values().stream()
                .filter(employee -> employee.getMangerLevel() > MAX_SUBORDINATES_LIMIT)
                .collect(Collectors.toList());
    }

    private void processEmployees() {
        employees.values().stream().forEach(manager -> {
            var highManager = employees.get(manager.getManagerId());
            managerDepartments.put(manager, employees.values().stream().filter(emp -> emp.getManagerId() == manager.getId()).collect(Collectors.toList()));
            if (highManager != null) manager.setMangerLevel(highManager.getMangerLevel() + 1);
        });


        managerDepartments.entrySet().stream().
                filter(e -> e.getValue().size() != 0).
                forEach(entry -> {
                    var manager = entry.getKey();
                    var subordinates = entry.getValue();

                    var averageSalary = calculateAverageSalary(subordinates);
                    var managerSalary = manager.getSalary();

                    if (managerSalary - averageSalary < MIN_SALARY_PERCENTAGE * averageSalary) {
                        lowSalaryManagers.put(manager, ((MIN_SALARY_PERCENTAGE * averageSalary) - (managerSalary - averageSalary)));
                    } else if (managerSalary - averageSalary > MAX_SALARY_PERCENTAGE * averageSalary) {
                        highSalaryManagers.put(manager, ((managerSalary - averageSalary) - (MAX_SALARY_PERCENTAGE * averageSalary)));
                    }
                });
    }

    private static double calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }
}
