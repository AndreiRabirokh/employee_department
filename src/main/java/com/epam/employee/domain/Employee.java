package com.epam.employee.domain;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    int managerId;
    int mangerLevel;

    public Employee(int id, String firstName, String lastName, double salary, int managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public int getManagerId() {
        return managerId;
    }

    public int getMangerLevel() {
        return mangerLevel;
    }

    public void setMangerLevel(int mangerLevel) {
        this.mangerLevel = mangerLevel;
    }
}
