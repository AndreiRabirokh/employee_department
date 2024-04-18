package com.epam.employee.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportResult {

    private final Map<Employee, Double> highSalaryManagers = new HashMap<>();
    private final Map<Employee, Double> lowSalaryManagers = new HashMap<>();
    private final Map<Employee, Integer> managersLevel = new LinkedHashMap<>();
    private final List<Employee> wrongManagersLineEmployee = new ArrayList<>();


    public Map<Employee, Double> getHighSalaryManagers() {
        return new HashMap<>(highSalaryManagers);
    }

    public Map<Employee, Double> getLowSalaryManagers() {
        return new HashMap<>(lowSalaryManagers);
    }

    public Map<Employee, Integer> getManagersLevel() {
        return new LinkedHashMap<>(managersLevel);
    }

    public void putHighSalaryManager(Employee employee, Double amount) {
        highSalaryManagers.put(employee, amount);
    }

    public void putLowSalaryManager(Employee employee, Double amount) {
        lowSalaryManagers.put(employee, amount);
    }

    public void putManagersLevel(Employee employee, Integer managerLevel) {
        managersLevel.put(employee, managerLevel);
    }

    public Integer getEmployeeManagersLevel(Employee employee) {
        return managersLevel.get(employee);
    }

    public List<Employee> getWrongManagersLineEmployee() {
        return new ArrayList<>(wrongManagersLineEmployee);
    }

    public void setWrongManagersLineEmployee(List<Employee> employees) {
        wrongManagersLineEmployee.addAll(employees);
    }

}
