package pro.sky.skypro_collections.service;

import org.springframework.stereotype.Service;
import pro.sky.skypro_collections.exceptions.*;
import pro.sky.skypro_collections.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List <Employee> employees = new ArrayList<>();
    private static final int EMPLOYEE_MAX_NUMBER = 2;
    public Employee add(String firstName, String lastName) {

        if (employees.size() > EMPLOYEE_MAX_NUMBER) {
            throw new EmployeesStorageIsFull("Массив сотрудников переополнен.");
        }

        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyExist("Такой сотрудник уже есть.");
        }

        employees.add(newEmployee);

        return newEmployee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);

        if (!employees.contains(employeeForRemove)) {
            throw new EmployeeNotFound("Невозможно удалить. Такого сотрудника не существует.");
        } else {
            System.out.println("Сотрудник удален.");
        }

        employees.remove(employeeForRemove);
        return employeeForRemove;
    }

    public Employee get(String firstName, String lastName) {
        Employee employeeForSearch = new Employee(firstName, lastName);

        if (!employees.contains(employeeForSearch)) {
            throw new EmployeeNotFound("Указанный сотрудник не найден.");
        }

        Employee result = null;
        for (Employee employee : employees) {
            if (employee.equals(employeeForSearch)) {
                return employee;
            }
        }
        return result;
    }

    public List<Employee> getAll() {
        return employees;
    }
}
