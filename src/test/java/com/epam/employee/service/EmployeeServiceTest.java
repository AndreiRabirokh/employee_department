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

        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(ceo.getId(), ceo);
        employees.put(expectedEmployee.getId(), expectedEmployee);
        employees.put(employee.getId(), employee);

        //when
        IEmployeeService employeeService = new EmployeeService(employees);

        //then
        var actualResult = employeeService.getHighSalaryManagers();
        assertEquals(1, actualResult.size());
        assertEquals(3400, actualResult.get(expectedEmployee));
    }

    @Test
    void test_lower_salary_employees() {
        Employee ceo = new Employee(123, "Joe", "Doe", 60000, -1);
        Employee expectedEmployee = new Employee(124, "Martin", "Chekov", 45000, 123);
        Employee employee = new Employee(300, "Alice", "Hasacat", 54400, 124);

        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(ceo.getId(), ceo);
        employees.put(expectedEmployee.getId(), expectedEmployee);
        employees.put(employee.getId(), employee);

        IEmployeeService employeeService = new EmployeeService(employees);

        var actualResult = employeeService.getLowSalaryManagers();
        assertEquals(1, actualResult.size());
        assertEquals(20280, actualResult.get(expectedEmployee));
    }

    @Test
    void test_wrong_managers_line() {
        Employee ceo = new Employee(123, "Joe", "Doe", 66000, -1);
        Employee employee1 = new Employee(300, "Alice", "Hasacat", 54400, 123);
        Employee employee2 = new Employee(305, "Bruce", "Lee", 25000, 300);
        Employee employee3 = new Employee(306, "Etti", "Taylor", 25000, 305);
        Employee employee4 = new Employee(307, "Steve", "Martin", 25000, 306);
        Employee expectedEmployee = new Employee(308, "John", "Anderson", 25000, 307);

        Map<Integer, Employee> employees = new LinkedHashMap<>();
        employees.put(ceo.getId(), ceo);
        employees.put(employee1.getId(), employee1);
        employees.put(employee2.getId(), employee2);
        employees.put(employee3.getId(), employee3);
        employees.put(employee4.getId(), employee4);
        employees.put(expectedEmployee.getId(), expectedEmployee);

        IEmployeeService employeeService = new EmployeeService(employees);

        var actualResult = employeeService.getWrongDepartmentStructureManagers();
        assertEquals(1, actualResult.size());
        assertEquals(expectedEmployee, actualResult.get(0));


    }

    @Test
    void test_empty_employees_tp_process() {
        Map<Integer, Employee> employees = new HashMap<>();
        EmployeeService employeeService = new EmployeeService(employees);

        assertEquals(0, employeeService.getHighSalaryManagers().size());
        assertEquals(0, employeeService.getLowSalaryManagers().size());
        assertEquals(0, employeeService.getWrongDepartmentStructureManagers().size());
    }




}