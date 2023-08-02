package com.example.dbconnection.dao;

import com.example.dbconnection.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao{


    List<Employee> getAllEmployee();

    Optional<Employee> getEmployee(int id);

    void save(Employee employee);

    void saveListEmployee(List<Employee> employees);

    void update(int id,Employee employee);

    void delete(String name);

    List<Employee> getGraterThanAge(int age);

    List<Employee> getNameWith(String name);

    List<Employee> getSameNameAndAge(String name,int age);



}
