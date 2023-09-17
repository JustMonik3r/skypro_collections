package pro.sky.skypro_collections.service;

import org.springframework.stereotype.Service;
import pro.sky.skypro_collections.exceptions.*;
import pro.sky.skypro_collections.model.Employee;

import java.util.*;

public interface EmployeeService {
    Map<String, Employee> employees = new LinkedHashMap<>();
    int EMPLOYEE_MAX_NUMBER = 2;
    Employee add(String firstName, String lastName, int department, double salary) ;

    Employee remove(String firstName, String lastName, int department, double salary);

    Employee get(String firstName, String lastName, int department, double salary);

    Collection<Employee> getAll();

    String getKey (String firstName, String lastName, int department, double salary);
}
