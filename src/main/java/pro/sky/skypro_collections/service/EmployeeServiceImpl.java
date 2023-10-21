package pro.sky.skypro_collections.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skypro_collections.exceptions.BadRequestException;
import pro.sky.skypro_collections.exceptions.EmployeeAlreadyExistException;
import pro.sky.skypro_collections.exceptions.EmployeeNotFoundException;
import pro.sky.skypro_collections.exceptions.EmployeesStorageIsFullException;
import pro.sky.skypro_collections.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new LinkedHashMap<>();
    private static final int EMPLOYEE_MAX_NUMBER = 2;
    public Employee add(String firstName, String lastName, int department, double salary) {

        String key = getKey(firstName, lastName, department, salary);
        Employee newEmployee = new Employee(firstName, lastName, department, salary);

        if (employees.size() >= EMPLOYEE_MAX_NUMBER) {
            throw new EmployeesStorageIsFullException("Массив сотрудников переполнен.");
        }

        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("Имя пользователя содержит запрещенные символы");
        }

        if (StringUtils.containsIgnoreCase(employees.toString(), key)) {
            throw new EmployeeAlreadyExistException("Такой сотрудник уже есть.");
        }

        employees.put (key, newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName, int department, double salary) {
        String key = getKey(firstName, lastName, department, salary);
        Employee employeeForRemoval = employees.get(key);

        if (!StringUtils.containsIgnoreCase(employees.toString(), key)) {
            throw new BadRequestException("Невозможно удалить. Такого сотрудника не существует.");
        } else {
            employees.remove(key, employeeForRemoval);
            employeeForRemoval = null;
            return employeeForRemoval;
        }
    }

    public Employee get(String firstName, String lastName, int department, double salary) {
        String key = getKey(firstName, lastName, department, salary);

        if (!StringUtils.containsIgnoreCase(employees.toString(), key)) {
            throw new EmployeeNotFoundException("Указанный сотрудник не найден.");
        }
        return employees.get(key);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public String getKey (String firstName, String lastName, int department, double salary) {
        return StringUtils.capitalize(firstName) + StringUtils.capitalize(lastName) + department + salary;
    }
}
