package com.epam.employee.service;

import com.epam.employee.domain.Employee;
import com.epam.employee.domain.ReportResult;

import java.util.Map;

public interface EmployeeService {

    ReportResult analyzeEmployee(Map<Integer, Employee> employees);
}
