package com.epam.employee.service;

import com.epam.employee.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

    @Test
    void test_higher_salary_employees() {
        //given
        Employee ceo = new Employee(123, "Joe", "Doe", 66000, -1);
        Employee expectedEmployee = new Employee(300, "Alice", "Hasacat", 54400, 123);
        Employee employee = new Employee(305, "Brett", "Hardleaf", 34000, 300);

        Map<Integer, Employee> employees = new LinkedHashMap<>();
        employees.put(ceo.id(), ceo);
        employees.put(expectedEmployee.id(), expectedEmployee);
        employees.put(employee.id(), employee);

        //when
        EmployeeService employeeService = new EmployeeServiceImpl();

        //then
        var actualResult = employeeService.analyzeEmployee(employees);
        assertEquals(1, actualResult.getHighSalaryManagers().size());
        assertEquals(3400, actualResult.getHighSalaryManagers().get(expectedEmployee));
    }

    @Test
    void test_lower_salary_employees() {
        Employee ceo = new Employee(123, "Joe", "Doe", 60000, -1);
        Employee expectedEmployee = new Employee(124, "Martin", "Chekov", 45000, 123);
        Employee employee = new Employee(300, "Alice", "Hasacat", 54400, 124);

        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(ceo.id(), ceo);
        employees.put(expectedEmployee.id(), expectedEmployee);
        employees.put(employee.id(), employee);

        EmployeeService employeeService = new EmployeeServiceImpl();

        var actualResult = employeeService.analyzeEmployee(employees);
        assertEquals(1, actualResult.getLowSalaryManagers().size());
        assertEquals(20280, actualResult.getLowSalaryManagers().get(expectedEmployee));
    }

    @Test
    void test_wrong_managers_line() {
        Employee ceo = new Employee(123, "Joe", "Doe", 66000, -1);
        Employee employee1 = new Employee(300, "Alice", "Hasacat", 54400, 123);
        Employee employee2 = new Employee(305, "Bruce", "Lee", 25000, 300);
        Employee employee3 = new Employee(306, "Etti", "Taylor", 25000, 305);
        Employee expectedEmployee = new Employee(308, "John", "Anderson", 25000, 306);

        Map<Integer, Employee> employees = new LinkedHashMap<>();
        employees.put(ceo.id(), ceo);
        employees.put(employee1.id(), employee1);
        employees.put(employee2.id(), employee2);
        employees.put(employee3.id(), employee3);
        employees.put(expectedEmployee.id(), expectedEmployee);

        EmployeeService employeeService = new EmployeeServiceImpl();

        var actualResult = employeeService.analyzeEmployee(employees);
        assertEquals(1, actualResult.getWrongManagersLineEmployee().size());
        assertEquals(expectedEmployee, actualResult.getWrongManagersLineEmployee().get(0));
    }

    @Test
    void test_empty_employees_tp_process() {
        Map<Integer, Employee> employees = new HashMap<>();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

        var actualResult = employeeService.analyzeEmployee(employees);
        assertEquals(0, actualResult.getHighSalaryManagers().size());
        assertEquals(0, actualResult.getLowSalaryManagers().size());
        assertEquals(0, actualResult.getWrongManagersLineEmployee().size());
    }




}
