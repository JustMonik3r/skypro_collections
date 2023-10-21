package pro.sky.skypro_collections.service;

import org.junit.jupiter.api.Test;
import pro.sky.skypro_collections.model.Employee;
import pro.sky.skypro_collections.exceptions.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.skypro_collections.service.TestUtils.*;

class EmployeeServiceImplTest {

    private final EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @Test
    void add_shouldAddNewEmployee() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void add_shouldThrowStorageIsFullException_moreThanThreeEmployees() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salary3 = SALARY_3;

        //Ожидаемый результат
        String expectedMessage = "Массив сотрудников переполнен.";

        //Начало теста
        employeeServiceImpl.add(firstName2, lastName2, departmentId, salary2);
        employeeServiceImpl.add(firstName3, lastName3, departmentId, salary3);
        Exception exception = assertThrows(
                EmployeesStorageIsFullException.class,
                () -> employeeServiceImpl.add(firstName, lastName, departmentId, salary)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void add_shouldThrowEmployeeAlreadyExistException() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = "Ivan";
        String lastName2 = "Ivanov";
        double salary2 = SALARY;


        //Ожидаемый результат
        String expectedMessage = "Такой сотрудник уже есть.";

        //Начало теста
        employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        Exception exception = assertThrows(
                EmployeeAlreadyExistException.class,
                () -> employeeServiceImpl.add(firstName2, lastName2, departmentId, salary2)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void add_shouldThrowBadRequestException_firstNameOrLastNameContainsForbiddenSymbols() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = "12356";
        String lastName2 = "!346346";
        double salary2 = SALARY;


        //Ожидаемый результат
        String expectedMessage = "400 Имя пользователя содержит запрещенные символы";

        //Начало теста
        employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        Exception exception = assertThrows(
                BadRequestException.class,
                () -> employeeServiceImpl.add(firstName2, lastName2, departmentId, salary2)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void remove_shouldRemoveExistingEmployee() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        Employee expectedEmployee = null;

        //Начало теста
        Employee actualEmployee = employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        actualEmployee = employeeServiceImpl.remove(firstName, lastName, departmentId, salary);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void remove_shouldThrowEmployeeNotFoundException() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        String expectedMessage = "400 Невозможно удалить. Такого сотрудника не существует.";

        //Начало теста
        employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        employeeServiceImpl.remove(firstName, lastName, departmentId, salary);
        Exception exception = assertThrows(
                BadRequestException.class,
                () -> employeeServiceImpl.remove(firstName, lastName, departmentId, salary)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void get_shouldFindAddedEmployee() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        actualEmployee = employeeServiceImpl.get(firstName, lastName, departmentId, salary);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void get_shouldThrowEmployeeNotFoundException() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        String expectedMessage = "Указанный сотрудник не найден.";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeServiceImpl.get(firstName, lastName, departmentId, salary)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll() {
        //Входные данные
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        int departmentId = FIRST_DEPARTMENT_ID;

        Employee firstEmployee = new Employee(firstName, lastName, departmentId, salary);
        Employee secondEmployee = new Employee(firstName2, lastName2, departmentId, salary2);
        //Подготовка ожидаемого результата
        employeeServiceImpl.add(firstName, lastName, departmentId, salary);
        employeeServiceImpl.add(firstName2, lastName2, departmentId, salary2);
        //Начало теста

        Map<String, Employee> actualEmployees = new LinkedHashMap<>();
        actualEmployees.put(employeeServiceImpl.getKey(firstName, lastName, departmentId, salary), firstEmployee);
        actualEmployees.put(employeeServiceImpl.getKey(firstName2, lastName2, departmentId, salary2), secondEmployee);
        assertIterableEquals(employeeServiceImpl.getAll(), actualEmployees.values());
    }

}