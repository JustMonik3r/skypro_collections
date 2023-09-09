package pro.sky.skypro_collections.service;

import org.springframework.stereotype.Service;
import pro.sky.skypro_collections.exceptions.*;
import pro.sky.skypro_collections.model.Employee;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new LinkedHashMap<>();
    private static final int EMPLOYEE_MAX_NUMBER = 2;
    public Employee add(String firstName, String lastName) {

        String key = getKey(firstName, lastName);
        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.size() > EMPLOYEE_MAX_NUMBER) {
            throw new EmployeesStorageIsFull("Массив сотрудников переполнен.");
        }

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyExist("Такой сотрудник уже есть.");
        }

        employees.put (key, newEmployee);

        return newEmployee;
    }

    public Employee remove(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employeeForRemoval = employees.get(key);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFound("Невозможно удалить. Такого сотрудника не существует.");
        } else {
            System.out.println("Сотрудник удален.");
        }

        employees.remove(key);
        return employeeForRemoval;
    }

    public Employee get(String firstName, String lastName) {
        String key = getKey(firstName, lastName);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFound("Указанный сотрудник не найден.");
        }

        return employees.get(key);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    private String getKey (String firstName, String lastName) {
        return firstName + lastName;
    }
}
