package com.epam.employee.service;

import com.epam.employee.domain.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {


    Map<Employee, Double> getHighSalaryManagers();
    Map<Employee, Double> getLowSalaryManagers();
    List<Employee> getWrongDepartmentStructureManagers();
}
