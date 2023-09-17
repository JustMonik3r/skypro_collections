package pro.sky.skypro_collections.service;

import org.springframework.stereotype.Service;
import pro.sky.skypro_collections.exceptions.EmployeeAlreadyExist;
import pro.sky.skypro_collections.exceptions.EmployeeNotFound;
import pro.sky.skypro_collections.exceptions.EmployeesStorageIsFull;
import pro.sky.skypro_collections.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new LinkedHashMap<>();
    private static final int EMPLOYEE_MAX_NUMBER = 2;
    public Employee add(String firstName, String lastName, int department, double salary) {

        String key = getKey(firstName, lastName, department, salary);
        Employee newEmployee = new Employee(firstName, lastName, department, salary);

        if (employees.size() > EMPLOYEE_MAX_NUMBER) {
            throw new EmployeesStorageIsFull("Массив сотрудников переполнен.");
        }

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyExist("Такой сотрудник уже есть.");
        }

        employees.put (key, newEmployee);

        return newEmployee;
    }

    public Employee remove(String firstName, String lastName, int department, double salary) {
        String key = getKey(firstName, lastName, department, salary);
        Employee employeeForRemoval = employees.get(key);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFound("Невозможно удалить. Такого сотрудника не существует.");
        } else {
            System.out.println("Сотрудник удален.");
        }

        employees.remove(key);
        return employeeForRemoval;
    }

    public Employee get(String firstName, String lastName, int department, double salary) {
        String key = getKey(firstName, lastName, department, salary);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFound("Указанный сотрудник не найден.");
        }

        return employees.get(key);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public String getKey (String firstName, String lastName, int department, double salary) {
        return firstName + lastName + department + salary;
    }
}
